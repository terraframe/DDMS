package dss.vector.solutions.intervention.monitor;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.general.EpiDate;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermRootCache;
import dss.vector.solutions.surveillance.GridComparator;
import dss.vector.solutions.surveillance.PeriodType;

public class ITNDataView extends ITNDataViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1245774473120L;

  public ITNDataView()
  {
    super();
  }

  public void populateView(ITNData concrete)
  {
    this.clearPeriodType();
    this.setConcreteId(concrete.getId());
    this.setGeoId(concrete.getGeoEntity().getGeoId());
    this.setBatchNumber(concrete.getBatchNumber());
    this.setReceivedForTargetGroups(concrete.getReceivedForTargetGroups());
    this.setReceivedForCommunityResponse(concrete.getReceivedForCommunityResponse());
    this.setNumberDistributed(concrete.getNumberDistributed());
    this.setNumberSold(concrete.getNumberSold());
    this.setCurrencyReceived(concrete.getCurrencyReceived());
    this.setStartDate(concrete.getStartDate());
    this.setEndDate(concrete.getEndDate());
  }

  private void populateConcrete(ITNData concrete)
  {
    GeoEntity geoEntity = GeoEntity.searchByGeoId(this.getGeoId());

    Date _startDate = this.getStartDate();
    Date _endDate = this.getEndDate();

    if (_startDate == null || _endDate == null)
    {
      PeriodType pt = this.getPeriodType().get(0);
      
      // IMPORTANT: WEEK is 0 based while MONTH and QUARTER are 1 based. Thus we
      // need to offset the 'period' for WEEK
      Integer _period = ( pt.equals(PeriodType.WEEK) ? this.getPeriod() - 1 : this.getPeriod() );

      EpiDate date = EpiDate.getInstanceByPeriod(pt, _period, this.getPeriodYear());
      
      _startDate = date.getStartDate();
      _endDate = date.getEndDate();
    }

    concrete.setGeoEntity(geoEntity);
    concrete.setStartDate(_startDate);
    concrete.setEndDate(_endDate);
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
    new AttributeNotificationMap(concrete, ITNData.STARTDATE, this, ITNDataView.STARTDATE);
    new AttributeNotificationMap(concrete, ITNData.ENDDATE, this, ITNDataView.ENDDATE);
  }

  @Override
  public void apply()
  {
    ITNData concrete = new ITNData();

    if (this.hasConcrete())
    {
      concrete = ITNData.get(this.getConcreteId());
    }

    // Build the attribute map between ITNData and ITNDataView for error
    // handling
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

  public boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }

  @Transaction
  public void applyAll(ITNNet[] nets, ITNTargetGroup[] targetGroups, ITNService[] services)
  {
    this.apply();

    for (ITNNet net : nets)
    {
      net.overwriteParentId(this.getConcreteId());
      net.apply();
    }

    for (ITNTargetGroup group : targetGroups)
    {
      group.overwriteParentId(this.getConcreteId());
      group.apply();
    }

    for (ITNService dose : services)
    {
      dose.overwriteParentId(this.getConcreteId());
      dose.apply();
    }
  }

  @Override
  public ITNNet[] getITNNets()
  {
    Set<ITNNet> set = new TreeSet<ITNNet>(new GridComparator());

    for (Term d : TermRootCache.getRoots(ITNDataView.getDisplayNetsMd()))
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

    for (Term d : TermRootCache.getRoots(ITNDataView.getDisplayTargetGroupsMd()))
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

    for (Term d : TermRootCache.getRoots(ITNDataView.getDisplayServicesMd()))
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

  @Override
  public ITNDataView searchByView()
  {
    GeoEntity entity = GeoEntity.searchByGeoId(this.getGeoId());

    if (this.getSearchType())
    {
      return ITNData.searchByDate(entity, this.getStartDate(), this.getEndDate());
    }
    else
    {
      PeriodType _periodType = this.getPeriodType().get(0);

      return ITNData.searchByGeoEntityAndEpiDate(entity, _periodType, this.getPeriod(), this.getPeriodYear());
    }
  }
}
