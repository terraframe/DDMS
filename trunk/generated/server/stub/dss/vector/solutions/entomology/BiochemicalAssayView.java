package dss.vector.solutions.entomology;

import com.terraframe.mojo.dataaccess.transaction.AttributeNotificationMap;
import com.terraframe.mojo.dataaccess.transaction.Transaction;

public class BiochemicalAssayView extends BiochemicalAssayViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1386315237;
  
  public BiochemicalAssayView()
  {
    super();
  }
  
  public void populateView(BiochemicalAssay concrete)
  {
    this.setConcreteId(concrete.getId());
    this.setCollection(concrete.getCollection());
    this.setIdentMethod(concrete.getIdentMethod());
    this.setIsofemale(concrete.getIsofemale());
    this.setMosquitoId(concrete.getMosquitoId());
    this.setNumberElevated(concrete.getNumberElevated());
    this.setNumberTested(concrete.getNumberTested());
    this.setGeneration(concrete.getGeneration());
    this.setSex(concrete.getSex());
    this.setSpecies(concrete.getSpecies());
    this.setAssay(concrete.getAssay());
  }

  private void populateConcrete(BiochemicalAssay concrete)
  {
    concrete.setCollection(this.getCollection());
    concrete.setIdentMethod(this.getIdentMethod());
    concrete.setIsofemale(this.getIsofemale());
    concrete.setMosquitoId(this.getMosquitoId());
    concrete.setNumberElevated(this.getNumberElevated());
    concrete.setNumberTested(this.getNumberTested());
    concrete.setGeneration(this.getGeneration());
    concrete.setSex(this.getSex());
    concrete.setSpecies(this.getSpecies());
    concrete.setAssay(this.getAssay());
  }

  private void buildAttributeMap(BiochemicalAssay concrete)
  {
    new AttributeNotificationMap(concrete, BiochemicalAssay.ID, this, BiochemicalAssayView.CONCRETEID);
    new AttributeNotificationMap(concrete, BiochemicalAssay.COLLECTION, this, BiochemicalAssayView.COLLECTION);
    new AttributeNotificationMap(concrete, BiochemicalAssay.IDENTMETHOD, this, BiochemicalAssayView.IDENTMETHOD);
    new AttributeNotificationMap(concrete, BiochemicalAssay.ISOFEMALE, this, BiochemicalAssayView.ISOFEMALE);
    new AttributeNotificationMap(concrete, BiochemicalAssay.MOSQUITOID, this, BiochemicalAssayView.MOSQUITOID);
    new AttributeNotificationMap(concrete, BiochemicalAssay.NUMBERELEVATED, this, BiochemicalAssayView.NUMBERELEVATED);
    new AttributeNotificationMap(concrete, BiochemicalAssay.NUMBERTESTED, this, BiochemicalAssayView.NUMBERTESTED);
    new AttributeNotificationMap(concrete, BiochemicalAssay.GENERATION, this, BiochemicalAssayView.GENERATION);
    new AttributeNotificationMap(concrete, BiochemicalAssay.SEX, this, BiochemicalAssayView.SEX);
    new AttributeNotificationMap(concrete, BiochemicalAssay.SPECIES, this, BiochemicalAssayView.SPECIES);
    new AttributeNotificationMap(concrete, BiochemicalAssay.ASSAY, this, BiochemicalAssayView.ASSAY);
  }

  @Override
  public void apply()
  {
    BiochemicalAssay concrete = new BiochemicalAssay();

    if (this.hasConcrete())
    {
      concrete = BiochemicalAssay.lock(this.getConcreteId());
    }

    // Build the attribute map between BiochemicalAssay and
    // BiochemicalAssayView for error handling
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
      BiochemicalAssay.get(this.getConcreteId()).delete();
    }
  }

  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  } 

  @Transaction
  public static BiochemicalAssayView[] applyAll(BiochemicalAssayView[] views)
  {
    for(BiochemicalAssayView view : views)
    {
      view.apply();
    }
    
    return views;
  }

  
}
