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
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.SelectablePrimitive;

import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.surveillance.GridComparator;
import dss.vector.solutions.surveillance.OptionComparator;

public class ITNCommunityDistributionView extends ITNCommunityDistributionViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1252612685812L;

  public ITNCommunityDistributionView()
  {
    super();
  }

  public void populateView(ITNCommunityDistribution concrete)
  {
    this.setConcreteId(concrete.getId());
    this.setStartDate(concrete.getStartDate());
    this.setEndDate(concrete.getEndDate());
    this.setAgentFirstName(concrete.getAgentFirstName());
    this.setAgentSurname(concrete.getAgentSurname());
    this.setHasBatchNumber(concrete.getBatchNumber() != null);
    this.setBatchNumber(concrete.getBatchNumber());
    this.setEntryType(concrete.getEntryType());
    this.setSold(concrete.getSold());
    this.setCurrencyReceived(concrete.getCurrencyReceived());
    this.setRetrieved(concrete.getRetrieved());
    this.setNumberRetrieved(concrete.getNumberRetrieved());
    this.setPretreated(concrete.getPretreated());
    this.setHouseholdName(concrete.getHouseholdName());
    this.setHouseholdSurname(concrete.getHouseholdSurname());
    this.setResidents(concrete.getResidents());

    if (concrete.getHouseholdAddress() != null)
    {
      this.setHouseholdAddress(concrete.getHouseholdAddress().getGeoId());
    }
    
    if (concrete.getDistributionLocation() != null)
    {
      this.setDistributionLocation(concrete.getDistributionLocation().getGeoId());
    }
  }

  private void populateConcrete(ITNCommunityDistribution concrete)
  {
    concrete.setStartDate(this.getStartDate());
    concrete.setEndDate(this.getEndDate());
    concrete.setAgentFirstName(this.getAgentFirstName());
    concrete.setAgentSurname(this.getAgentSurname());
    concrete.setBatchNumber(this.getBatchNumber());
    concrete.setEntryType(this.getEntryType());
    concrete.setSold(this.getSold());
    concrete.setCurrencyReceived(this.getCurrencyReceived());
    concrete.setRetrieved(this.getRetrieved());
    concrete.setNumberRetrieved(this.getNumberRetrieved());
    concrete.setPretreated(this.getPretreated());
    concrete.setHouseholdName(this.getHouseholdName());
    concrete.setHouseholdSurname(this.getHouseholdSurname());
    concrete.setResidents(this.getResidents());

    if (this.getHouseholdAddress() != null && !this.getHouseholdAddress().equals(""))
    {
      concrete.setHouseholdAddress(GeoEntity.searchByGeoId(this.getHouseholdAddress()));
    }
    else
    {
      concrete.setHouseholdAddress(null);
    }

    if (this.getDistributionLocation() != null && !this.getDistributionLocation().equals(""))
    {
      concrete.setDistributionLocation(GeoEntity.searchByGeoId(this.getDistributionLocation()));
    }
    else
    {
      concrete.setDistributionLocation(null);
    }
  }

  private void buildAttributeMap(ITNCommunityDistribution concrete)
  {
    new AttributeNotificationMap(concrete, ITNCommunityDistribution.AGENTFIRSTNAME, this, ITNCommunityDistributionView.AGENTFIRSTNAME);
    new AttributeNotificationMap(concrete, ITNCommunityDistribution.AGENTSURNAME, this, ITNCommunityDistributionView.AGENTSURNAME);
    new AttributeNotificationMap(concrete, ITNCommunityDistribution.BATCHNUMBER, this, ITNCommunityDistributionView.BATCHNUMBER);
    new AttributeNotificationMap(concrete, ITNCommunityDistribution.CURRENCYRECEIVED, this, ITNCommunityDistributionView.CURRENCYRECEIVED);
    new AttributeNotificationMap(concrete, ITNCommunityDistribution.DISTRIBUTIONLOCATION, this, ITNCommunityDistributionView.DISTRIBUTIONLOCATION);
    new AttributeNotificationMap(concrete, ITNCommunityDistribution.ENDDATE, this, ITNCommunityDistributionView.ENDDATE);
    new AttributeNotificationMap(concrete, ITNCommunityDistribution.ENTRYTYPE, this, ITNCommunityDistributionView.ENTRYTYPE);
    new AttributeNotificationMap(concrete, ITNCommunityDistribution.HOUSEHOLDADDRESS, this, ITNCommunityDistributionView.HOUSEHOLDADDRESS);
    new AttributeNotificationMap(concrete, ITNCommunityDistribution.HOUSEHOLDNAME, this, ITNCommunityDistributionView.HOUSEHOLDNAME);
    new AttributeNotificationMap(concrete, ITNCommunityDistribution.HOUSEHOLDSURNAME, this, ITNCommunityDistributionView.HOUSEHOLDSURNAME);
    new AttributeNotificationMap(concrete, ITNCommunityDistribution.NUMBERRETRIEVED, this, ITNCommunityDistributionView.NUMBERRETRIEVED);
    new AttributeNotificationMap(concrete, ITNCommunityDistribution.PRETREATED, this, ITNCommunityDistributionView.PRETREATED);
    new AttributeNotificationMap(concrete, ITNCommunityDistribution.RESIDENTS, this, ITNCommunityDistributionView.RESIDENTS);
    new AttributeNotificationMap(concrete, ITNCommunityDistribution.RETRIEVED, this, ITNCommunityDistributionView.RETRIEVED);
    new AttributeNotificationMap(concrete, ITNCommunityDistribution.SOLD, this, ITNCommunityDistributionView.SOLD);
    new AttributeNotificationMap(concrete, ITNCommunityDistribution.STARTDATE, this, ITNCommunityDistributionView.STARTDATE);
  }

  @Override
  public void apply()
  {
    ITNCommunityDistribution concrete = new ITNCommunityDistribution();

    if (this.hasConcrete())
    {
      concrete = ITNCommunityDistribution.get(this.getConcreteId());
    }

    // Build the attribute map between ITNCommunityDistribution and
    // ITNCommunityDistributionView for error handling
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
      ITNCommunityDistribution.get(this.getConcreteId()).delete();
    }
  }

  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }

  @Transaction
  public void applyAll(ITNCommunityNet[] nets, ITNCommunityTargetGroup[] targetGroups)
  {
    boolean newCase = !this.hasConcrete();

    this.apply();

    if (newCase)
    {
      this.updateParentIds(nets, targetGroups);
    }

    for (ITNCommunityNet net : nets)
    {
      net.apply();
    }

    for (ITNCommunityTargetGroup group : targetGroups)
    {
      group.apply();
    }
  }

  @Transaction
  private void updateParentIds(ITNCommunityNet[] nets, ITNCommunityTargetGroup[] targetGroups)
  {
    for (int i = 0; i < nets.length; i++)
    {
      nets[i] = nets[i].clone(this);
    }

    for (int i = 0; i < targetGroups.length; i++)
    {
      targetGroups[i] = targetGroups[i].clone(this);
    }
  }

  @Override
  public ITNCommunityNet[] getITNCommunityNets()
  {
    // A list of all this household nets plus nets that it doesn't have
    Map<String, ITNCommunityNet> nets = loadNets();
    List<ITNCommunityNet> list = new LinkedList<ITNCommunityNet>();
    Stack<Net> stack = new Stack<Net>();

    for (Net root : Net.getRoots())
    {
      stack.push(root);
    }

    while (!stack.empty())
    {
      // Create a stack to provide sorting on the nets
      // Sort by the net name
      Set<Net> set = new TreeSet<Net>(new OptionComparator());

      Net net = stack.pop();

      set.addAll(net.getAllChildNets().getAll());

      for (Net child : set)
      {
        stack.push(child);
      }

      list.add(nets.get(net.getId()));
    }

    return list.toArray(new ITNCommunityNet[list.size()]);
  }

  private Map<String, ITNCommunityNet> loadNets()
  {
    Map<String, ITNCommunityNet> map = new HashMap<String, ITNCommunityNet>();

    if (this.hasConcrete())
    {
      ITNCommunityDistribution concrete = ITNCommunityDistribution.get(this.getConcreteId());

      for (ITNCommunityNet householdNet : concrete.getAllNetsRel())
      {
        map.put(householdNet.getChildId(), householdNet);
      }
    }

    for (Net net : Net.getAll())
    {
      if (!map.containsKey(net.getId()))
      {
        map.put(net.getId(), new ITNCommunityNet(this.getConcreteId(), net.getId()));
      }
    }

    return map;
  }

  @Override
  public ITNCommunityTargetGroup[] getITNCommunityTargetGroups()
  {
    Set<ITNCommunityTargetGroup> set = new TreeSet<ITNCommunityTargetGroup>(new GridComparator());

    for (TargetGroupGrid d : TargetGroupGrid.getAll())
    {
      set.add(new ITNCommunityTargetGroup(this.getConcreteId(), d.getId()));
    }

    if (this.hasConcrete())
    {
      ITNCommunityDistribution concrete = ITNCommunityDistribution.get(this.getConcreteId());

      for (ITNCommunityTargetGroup d : concrete.getAllTargetGroupsRel())
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

    return set.toArray(new ITNCommunityTargetGroup[set.size()]);
  }

  public static ITNCommunityDistributionViewQuery getPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    ITNCommunityDistributionViewQuery query = new ITNCommunityDistributionViewQuery(new QueryFactory());

    if (sortAttribute == null)
    {
      sortAttribute = STARTDATE;
    }

    SelectablePrimitive selectable = (SelectablePrimitive) query.getComponentQuery().getSelectable(sortAttribute);

    if (isAscending)
    {
      query.ORDER_BY_ASC(selectable);
    }
    else
    {
      query.ORDER_BY_DESC(selectable);
    }

    if (pageSize != 0 && pageNumber != 0)
    {
      query.restrictRows(pageSize, pageNumber);
    }

    return query;
  }

}
