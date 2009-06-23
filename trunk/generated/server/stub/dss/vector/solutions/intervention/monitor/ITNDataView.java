package dss.vector.solutions.intervention.monitor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import com.terraframe.mojo.dataaccess.transaction.AttributeNotificationMap;
import com.terraframe.mojo.dataaccess.transaction.Transaction;

import dss.vector.solutions.general.EpiDate;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.surveillance.GridComparator;
import dss.vector.solutions.surveillance.OptionComparator;

public class ITNDataView extends ITNDataViewBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1245774473120L;

  public ITNDataView()
  {
    super();
  }

  public void populateView(ITNData concrete)
  {
    EpiDate date = EpiDate.getInstanceByDate(concrete.getStartDate(), concrete.getEndDate());

    this.clearPeriodType();
    this.setConcreteId(concrete.getId());
    this.setGeoId(concrete.getGeoEntity().getGeoId());
    this.setPeriod(date.getPeriod());
    this.setPeriodYear(date.getYear());
    this.addPeriodType(date.getEpiPeriodType());
    this.setBatchNumber(concrete.getBatchNumber());
    this.setReceivedForTargetGroups(concrete.getReceivedForTargetGroups());
    this.setReceivedForCommunityResponse(concrete.getReceivedForCommunityResponse());
    this.setNumberDistributed(concrete.getNumberDistributed());
    this.setNumberSold(concrete.getNumberSold());
    this.setCurrencyReceived(concrete.getCurrencyReceived());
  }

  private void populateConcrete(ITNData concrete)
  {
    EpiDate date = EpiDate.getInstanceByPeriod(this.getPeriodType().get(0), this.getPeriod(), this
        .getPeriodYear());
    GeoEntity geoEntity = GeoEntity.searchByGeoId(this.getGeoId());

    concrete.setGeoEntity(geoEntity);
    concrete.setStartDate(date.getStartDate());
    concrete.setEndDate(date.getEndDate());
    concrete.setBatchNumber(this.getBatchNumber());
    concrete.setReceivedForTargetGroups(this.getReceivedForTargetGroups());
    concrete.setReceivedForCommunityResponse(this.getReceivedForCommunityResponse());
    concrete.setNumberDistributed(this.getNumberDistributed());
    concrete.setNumberSold(this.getNumberSold());
    concrete.setCurrencyReceived(this.getCurrencyReceived());
  }
  
  private void buildAttributeMap(ITNData concrete)
  {
    new AttributeNotificationMap(concrete, ITNData.BATCHNUMBER, this, ITNDataView.BATCHNUMBER);
    new AttributeNotificationMap(concrete, ITNData.RECEIVEDFORCOMMUNITYRESPONSE, this, ITNDataView.RECEIVEDFORCOMMUNITYRESPONSE);
    new AttributeNotificationMap(concrete, ITNData.RECEIVEDFORTARGETGROUPS, this, ITNDataView.RECEIVEDFORTARGETGROUPS);
    new AttributeNotificationMap(concrete, ITNData.NUMBERDISTRIBUTED, this, ITNDataView.NUMBERDISTRIBUTED);
    new AttributeNotificationMap(concrete, ITNData.NUMBERSOLD, this, ITNDataView.NUMBERSOLD);
    new AttributeNotificationMap(concrete, ITNData.CURRENCYRECEIVED, this, ITNDataView.CURRENCYRECEIVED);
  }

  @Override
  public void apply()
  {
    ITNData concrete = new ITNData();

    if (this.hasConcrete())
    {
      concrete = ITNData.get(this.getConcreteId());
    }

    //Build the attribute map between ITNData and ITNDataView for error handling
    this.buildAttributeMap(concrete);

    this.populateConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }

  @Override
  public void deleteConcrete()
  {
    if (this.hasConcrete())
    {
      ITNData.get(this.getConcreteId()).delete();
    }
  }

  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }

  @Transaction
  public void applyAll(ITNNet[] nets, ITNTargetGroup[] targetGroups, ITNService[] services)
  {
    boolean newCase = !this.hasConcrete();

    this.apply();

    if (newCase)
    {
      this.updateParentIds(nets, targetGroups, services);
    }

    for (ITNNet net : nets)
    {
      net.apply();
    }

    for (ITNTargetGroup group : targetGroups)
    {
      group.apply();
    }

    for (ITNService dose : services)
    {
      dose.apply();
    }
  }

  @Transaction
  private void updateParentIds(ITNNet[] nets, ITNTargetGroup[] targetGroups, ITNService[] services)
  {
    for (int i = 0; i < nets.length; i++)
    {
      nets[i] = nets[i].clone(this);
    }

    for (int i = 0; i < targetGroups.length; i++)
    {
      targetGroups[i] = targetGroups[i].clone(this);
    }

    for (int i = 0; i < services.length; i++)
    {
      services[i] = services[i].clone(this);
    }
  }

  @Override
  public ITNNet[] getITNNets()
  {
    // A list of all this household nets plus nets that it doesn't have
    Map<String, ITNNet> nets = loadNets();
    List<ITNNet> list = new LinkedList<ITNNet>();
    Stack<Net> stack = new Stack<Net>();

    for (Net root : Net.getRoots())
    {
      stack.push(root);
    }

    while (!stack.empty())
    {
      //Create a stack to provide sorting on the nets
      //Sort by the net name 
      Set<Net> set = new TreeSet<Net>(new OptionComparator());

      Net net = stack.pop();

      set.addAll(net.getAllChildNets().getAll());

      for (Net child : set)
      {
        stack.push(child);
      }

      list.add(nets.get(net.getId()));
    }

    return list.toArray(new ITNNet[list.size()]);
  }

  private Map<String, ITNNet> loadNets()
  {
    Map<String, ITNNet> map = new HashMap<String, ITNNet>();

    if (this.hasConcrete())
    {
      ITNData concrete = ITNData.get(this.getConcreteId());

      for (ITNNet householdNet : concrete.getAllNetsRel())
      {
        map.put(householdNet.getChildId(), householdNet);
      }
    }
    
    for (Net net : Net.getAll())
    {
      if (!map.containsKey(net.getId()))
      {
        map.put(net.getId(), new ITNNet(this.getConcreteId(), net.getId()));
      }
    }

    return map;
  }

  @Override
  public ITNTargetGroup[] getITNTargetGroups()
  {
    Set<ITNTargetGroup> set = new TreeSet<ITNTargetGroup>(new GridComparator());

    for (TargetGroupGrid d : TargetGroupGrid.getAll())
    {
      set.add(new ITNTargetGroup(this.getConcreteId(), d.getId()));
    }

    if (this.hasConcrete())
    {
      ITNData concrete = ITNData.get(this.getConcreteId());

      for (ITNTargetGroup d : concrete.getAllTargetGroupsRel())
      {
        // We will only want grid options methods which are active
        // All active methods are already in the set. Thus, if
        // the set already contains an entry for the Grid Option
        // replace the default relationship with the actaul
        // relationship
        if (set.contains(d))
        {
          set.remove(d);
          set.add(d);
        }
      }
    }

    return set.toArray(new ITNTargetGroup[set.size()]);
  }

  @Override
  public ITNService[] getITNServices()
  {
    Set<ITNService> set = new TreeSet<ITNService>(new GridComparator());

    for (ServiceGrid d : ServiceGrid.getAll())
    {
      set.add(new ITNService(this.getConcreteId(), d.getId()));
    }

    if (this.hasConcrete())
    {
      ITNData concrete = ITNData.get(this.getConcreteId());

      for (ITNService d : concrete.getAllServicesRel())
      {
        // We will only want grid options methods which are active
        // All active methods are already in the set. Thus, if
        // the set already contains an entry for the Grid Option
        // replace the default relationship with the actaul
        // relationship
        if (set.contains(d))
        {
          set.remove(d);
          set.add(d);
        }
      }
    }

    return set.toArray(new ITNService[set.size()]);
  }

}
