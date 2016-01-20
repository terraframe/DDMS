package dss.vector.solutions.entomology;

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.entomology.assay.UniqueAssay;
import dss.vector.solutions.entomology.assay.UniqueAssayUtil;

public class InfectionAssayView extends InfectionAssayViewBase implements com.runwaysdk.generation.loader.Reloadable, UniqueAssay
{
  private static final long serialVersionUID = -236995216;
  
  public InfectionAssayView()
  {
    super();
  }
  
  public void populateView(InfectionAssay concrete)
  {
    this.setUniqueAssayId(concrete.getUniqueAssayId());
    this.setConcreteId(concrete.getId());
    this.setCollection(concrete.getCollection());
    this.setIdentMethod(concrete.getIdentMethod());
    this.setInfected(concrete.getInfected());
    this.setMosquitoId(concrete.getMosquitoId());
    this.setNumberPositive(concrete.getNumberPositive());
    this.setNumberTested(concrete.getNumberTested());
    this.setParasite(concrete.getParasite());
    this.setSex(concrete.getSex());
    this.setSpecies(concrete.getSpecies());
    this.setTestMethod(concrete.getTestMethod());
    this.setDisease(concrete.getDisease());
  }

  private void populateConcrete(InfectionAssay concrete)
  {
    concrete.setUniqueAssayId(this.getUniqueAssayId());

    concrete.setCollection(this.getCollection());
    concrete.setIdentMethod(this.getIdentMethod());
    concrete.setInfected(this.getInfected());
    concrete.setMosquitoId(this.getMosquitoId());
    concrete.setNumberPositive(this.getNumberPositive());
    concrete.setNumberTested(this.getNumberTested());
    concrete.setParasite(this.getParasite());
    concrete.setSex(this.getSex());
    concrete.setSpecies(this.getSpecies());
    concrete.setTestMethod(this.getTestMethod());
    
    // TODO: Disease is always null!? What is the point of this?? Is it old logic thats no longer needed?
    if (this.getDisease() != null) {
    	concrete.setDisease(this.getDisease());
    }
  }

  private void buildAttributeMap(InfectionAssay concrete)
  {
    new AttributeNotificationMap(concrete, InfectionAssay.UNIQUEASSAYID, this, InfectionAssayView.UNIQUEASSAYID);
    new AttributeNotificationMap(concrete, InfectionAssay.ID, this, InfectionAssayView.CONCRETEID);
    new AttributeNotificationMap(concrete, InfectionAssay.COLLECTION, this, InfectionAssayView.COLLECTION);
    new AttributeNotificationMap(concrete, InfectionAssay.IDENTMETHOD, this, InfectionAssayView.IDENTMETHOD);
    new AttributeNotificationMap(concrete, InfectionAssay.INFECTED, this, InfectionAssayView.INFECTED);
    new AttributeNotificationMap(concrete, InfectionAssay.MOSQUITOID, this, InfectionAssayView.MOSQUITOID);
    new AttributeNotificationMap(concrete, InfectionAssay.NUMBERPOSITIVE, this, InfectionAssayView.NUMBERPOSITIVE);
    new AttributeNotificationMap(concrete, InfectionAssay.NUMBERTESTED, this, InfectionAssayView.NUMBERTESTED);
    new AttributeNotificationMap(concrete, InfectionAssay.PARASITE, this, InfectionAssayView.PARASITE);
    new AttributeNotificationMap(concrete, InfectionAssay.SEX, this, InfectionAssayView.SEX);
    new AttributeNotificationMap(concrete, InfectionAssay.SPECIES, this, InfectionAssayView.SPECIES);
    new AttributeNotificationMap(concrete, InfectionAssay.TESTMETHOD, this, InfectionAssayView.TESTMETHOD);
    new AttributeNotificationMap(concrete, InfectionAssay.DISEASE, this, InfectionAssayView.DISEASE);
  }

  @Override
  public void apply()
  {
    InfectionAssay concrete = UniqueAssayUtil.getOrCreateAssay(InfectionAssay.class, this.getUniqueAssayId());
    if(!concrete.isNew())
    {
      concrete.appLock();
    }
    
    if (this.hasConcrete())
    {
      concrete = InfectionAssay.lock(this.getConcreteId());
    }

    // Build the attribute map between InfectionAssay and
    // InfectionAssayView for error handling
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
      InfectionAssay.get(this.getConcreteId()).delete();
    }
  }

  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  } 

  @Transaction
  public static InfectionAssayView[] applyAll(InfectionAssayView[] views)
  {
    UniqueAssayUtil.validateUniqueAssayIds(views);
    
    for(InfectionAssayView view : views)
    {
      view.apply();
    }
    
    return views;
  }

}
