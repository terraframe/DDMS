package dss.vector.solutions.intervention.monitor;

import java.util.Set;
import java.util.TreeSet;

import com.terraframe.mojo.dataaccess.MdAttributeReferenceDAOIF;
import com.terraframe.mojo.dataaccess.attributes.InvalidReferenceException;
import com.terraframe.mojo.dataaccess.transaction.AttributeNotificationMap;
import com.terraframe.mojo.dataaccess.transaction.Transaction;

import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.HealthFacility;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.surveillance.GridComparator;

public class ITNDistributionView extends ITNDistributionViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1255545119443L;
  
  public ITNDistributionView()
  {
    super();
  }
  
  public void populateView(ITNDistribution concrete)
  {
    this.setConcreteId(concrete.getId());

    if (concrete.getFacility() != null)
    {
      this.setFacility(concrete.getFacility().getGeoId());
    }
    
    this.setBatchNumber(concrete.getBatchNumber());
    this.setCurrencyReceived(concrete.getCurrencyReceived());
    this.setDistributionDate(concrete.getDistributionDate());
    this.setDistributorName(concrete.getDistributorName());
    this.setDistributorSurname(concrete.getDistributorSurname());
    this.setNet(concrete.getNet());
    this.setNumberSold(concrete.getNumberSold());
    this.setRecipient(concrete.getRecipient());
    this.setService(concrete.getService());    
  }

  private void populateConcrete(ITNDistribution concrete)
  {
    String facility = this.getFacility();

    if (facility != null && !facility.equals(""))
    {
      GeoEntity geoEntity = HealthFacility.searchByGeoId(facility);

      if (geoEntity instanceof HealthFacility)
      {
        concrete.setFacility((HealthFacility) geoEntity);
      }
      else
      {
        // No results = the geoId is invalid.
        throw new InvalidReferenceException("[" + facility + "] is not a valid Health Facility geo id.", (MdAttributeReferenceDAOIF) ITNDistribution.getFacilityMd());
      }
    }

    concrete.setBatchNumber(this.getBatchNumber());
    concrete.setCurrencyReceived(this.getCurrencyReceived());
    concrete.setDistributionDate(this.getDistributionDate());
    concrete.setDistributorName(this.getDistributorName());
    concrete.setDistributorSurname(this.getDistributorSurname());
    concrete.setNet(this.getNet());
    concrete.setNumberSold(this.getNumberSold());
    concrete.setRecipient(this.getRecipient());
    concrete.setService(this.getService());
  }

  private void buildAttributeMap(ITNDistribution concrete)
  {
    new AttributeNotificationMap(concrete, ITNDistribution.FACILITY, this, ITNDistributionView.FACILITY);
    new AttributeNotificationMap(concrete, ITNDistribution.BATCHNUMBER, this, ITNDistributionView.BATCHNUMBER);
    new AttributeNotificationMap(concrete, ITNDistribution.CURRENCYRECEIVED, this, ITNDistributionView.CURRENCYRECEIVED);
    new AttributeNotificationMap(concrete, ITNDistribution.DISTRIBUTIONDATE, this, ITNDistributionView.DISTRIBUTIONDATE);
    new AttributeNotificationMap(concrete, ITNDistribution.DISTRIBUTORNAME, this, ITNDistributionView.DISTRIBUTORNAME);
    new AttributeNotificationMap(concrete, ITNDistribution.DISTRIBUTORSURNAME, this, ITNDistributionView.DISTRIBUTORSURNAME);
    new AttributeNotificationMap(concrete, ITNDistribution.NET, this, ITNDistributionView.NET);
    new AttributeNotificationMap(concrete, ITNDistribution.NUMBERSOLD, this, ITNDistributionView.NUMBERSOLD);
    new AttributeNotificationMap(concrete, ITNDistribution.RECIPIENT, this, ITNDistributionView.RECIPIENT);
    new AttributeNotificationMap(concrete, ITNDistribution.SERVICE, this, ITNDistributionView.SERVICE);
  } 

  @Override
  public void apply()
  {
    ITNDistribution concrete = new ITNDistribution();

    if (this.hasConcrete())
    {
      concrete = ITNDistribution.get(this.getConcreteId());
    }

    // Build the attribute map between ITNDistribution and
    // ITNDistributionView for error handling
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
      ITNDistribution.get(this.getConcreteId()).delete();
    }
  }

  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }

  
  @Override
  @Transaction
  public void applyAll(ITNDistributionTargetGroup[] targetGroups)
  {
    boolean newCase = !this.hasConcrete();

    this.apply();

    if (newCase)
    {
      this.updateParentIds(targetGroups);
    }
    
    for (ITNDistributionTargetGroup targetGroup : targetGroups)
    {
      targetGroup.apply();
    }
  }
  
  private void updateParentIds(ITNDistributionTargetGroup[] targetGroups)
  {
    for (int i = 0; i < targetGroups.length; i++)
    {
      targetGroups[i] = targetGroups[i].clone(this);
    }
  }

  @Override
  public ITNDistributionTargetGroup[] getDistributionTargetGroups()
  {
    Set<ITNDistributionTargetGroup> set = new TreeSet<ITNDistributionTargetGroup>(new GridComparator());

    for (Term d : Term.getRootChildren(ITNDistributionView.getTargetGroupsMd()))
    {
      set.add(new ITNDistributionTargetGroup(this.getId(), d.getId()));
    }

    if (this.hasConcrete())
    {
      ITNDistribution concrete = ITNDistribution.get(this.getConcreteId());

      for (ITNDistributionTargetGroup d : concrete.getAllTargetGroupsRel())
      {
        // We will only want grid options methods which are active
        // All active methods are already in the set. Thus, if
        // the set already contains an entry for the Grid Option
        // replace the default relationship with the actual
        // relationship
        if (set.contains(d))
        {
          set.remove(d);
          set.add(d);
        }
      }
    }

    return set.toArray(new ITNDistributionTargetGroup[set.size()]);
  }

  
}
