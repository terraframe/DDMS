package dss.vector.solutions.intervention.monitor;

import java.util.Set;
import java.util.TreeSet;

import com.terraframe.mojo.dataaccess.transaction.AttributeNotificationMap;
import com.terraframe.mojo.dataaccess.transaction.Transaction;

import dss.vector.solutions.general.EpiDate;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.surveillance.GridComparator;

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
      nets[i].overwriteParentId(this.getConcreteId());
    }

    for (int i = 0; i < targetGroups.length; i++)
    {
      targetGroups[i].overwriteParentId(this.getConcreteId());
    }

    for (int i = 0; i < services.length; i++)
    {
      services[i].overwriteParentId(this.getConcreteId());
    }
  }

  @Override
  public ITNNet[] getITNNets()
  {
    Set<ITNNet> set = new TreeSet<ITNNet>(new GridComparator());
    
    for (Term d : Term.getRootChildren(ITNDataView.getDisplayNetsMd()))
    {
      set.add(new ITNNet(this.getConcreteId(), d.getId()));
    }
    
    if (this.hasConcrete())
    {
      ITNData concrete = ITNData.get(this.getConcreteId());
      
      for (ITNNet d : concrete.getAllNetsRel())
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
    
    return set.toArray(new ITNNet[set.size()]);
  }
  
  @Override
  public ITNTargetGroup[] getITNTargetGroups()
  {
    Set<ITNTargetGroup> set = new TreeSet<ITNTargetGroup>(new GridComparator());

    for (Term d : Term.getRootChildren(ITNDataView.getDisplayTargetGroupsMd()))
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

    for (Term d : Term.getRootChildren(ITNDataView.getDisplayServicesMd()))
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
