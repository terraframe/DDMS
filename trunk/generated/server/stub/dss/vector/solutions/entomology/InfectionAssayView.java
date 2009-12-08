package dss.vector.solutions.entomology;

import com.terraframe.mojo.dataaccess.transaction.AttributeNotificationMap;
import com.terraframe.mojo.dataaccess.transaction.Transaction;

public class InfectionAssayView extends InfectionAssayViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = -236995216;
  
  public InfectionAssayView()
  {
    super();
  }
  
  public void populateView(InfectionAssay concrete)
  {
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
  }

  private void populateConcrete(InfectionAssay concrete)
  {
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
  }

  private void buildAttributeMap(InfectionAssay concrete)
  {
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
  }

  @Override
  public void apply()
  {
    InfectionAssay concrete = new InfectionAssay();

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
    for(InfectionAssayView view : views)
    {
      view.apply();
    }
    
    return views;
  }

}
