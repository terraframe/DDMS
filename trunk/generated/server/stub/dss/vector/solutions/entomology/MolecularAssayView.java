package dss.vector.solutions.entomology;

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.entomology.assay.UniqueAssay;
import dss.vector.solutions.entomology.assay.UniqueAssayUtil;

public class MolecularAssayView extends MolecularAssayViewBase implements
    com.runwaysdk.generation.loader.Reloadable, UniqueAssay
{
  private static final long serialVersionUID = -1676081339;

  public MolecularAssayView()
  {
    super();
  }

  public void populateView(MolecularAssay concrete)
  {
    this.setUniqueAssayId(concrete.getUniqueAssayId());
    this.setConcreteId(concrete.getId());
    this.setCollection(concrete.getCollection());
    this.setIdentMethod(concrete.getIdentMethod());
    this.setIsofemale(concrete.getIsofemale());
    this.setMosquitoId(concrete.getMosquitoId());
    this.setNumberRR(concrete.getNumberRR());
    this.setNumberRS(concrete.getNumberRS());
    this.setNumberSS(concrete.getNumberSS());
    this.setGeneration(concrete.getGeneration());
    this.setSex(concrete.getSex());
    this.setSpecies(concrete.getSpecies());
    this.setAssayMethod(concrete.getAssayMethod());
    this.setTarget(concrete.getTarget());
  }

  private void populateConcrete(MolecularAssay concrete)
  {
    concrete.setUniqueAssayId(this.getUniqueAssayId());
    concrete.setCollection(this.getCollection());
    concrete.setIdentMethod(this.getIdentMethod());
    concrete.setIsofemale(this.getIsofemale());
    concrete.setMosquitoId(this.getMosquitoId());
    concrete.setNumberRR(this.getNumberRR());
    concrete.setNumberRS(this.getNumberRS());
    concrete.setNumberSS(this.getNumberSS());
    concrete.setGeneration(this.getGeneration());
    concrete.setSex(this.getSex());
    concrete.setSpecies(this.getSpecies());
    concrete.setAssayMethod(this.getAssayMethod());
    concrete.setTarget(this.getTarget());
  }

  private void buildAttributeMap(MolecularAssay concrete)
  {
    new AttributeNotificationMap(concrete, MolecularAssay.UNIQUEASSAYID, this,
        MolecularAssayView.UNIQUEASSAYID);
    new AttributeNotificationMap(concrete, MolecularAssay.ID, this, MolecularAssayView.CONCRETEID);
    new AttributeNotificationMap(concrete, MolecularAssay.COLLECTION, this,
        MolecularAssayView.COLLECTION);
    new AttributeNotificationMap(concrete, MolecularAssay.IDENTMETHOD, this,
        MolecularAssayView.IDENTMETHOD);
    new AttributeNotificationMap(concrete, MolecularAssay.ISOFEMALE, this, MolecularAssayView.ISOFEMALE);
    new AttributeNotificationMap(concrete, MolecularAssay.MOSQUITOID, this,
        MolecularAssayView.MOSQUITOID);
    new AttributeNotificationMap(concrete, MolecularAssay.NUMBERRR, this, MolecularAssayView.NUMBERRR);
    new AttributeNotificationMap(concrete, MolecularAssay.NUMBERRS, this, MolecularAssayView.NUMBERRS);
    new AttributeNotificationMap(concrete, MolecularAssay.NUMBERSS, this, MolecularAssayView.NUMBERSS);
    new AttributeNotificationMap(concrete, MolecularAssay.GENERATION, this,
        MolecularAssayView.GENERATION);
    new AttributeNotificationMap(concrete, MolecularAssay.SEX, this, MolecularAssayView.SEX);
    new AttributeNotificationMap(concrete, MolecularAssay.SPECIES, this, MolecularAssayView.SPECIES);
    new AttributeNotificationMap(concrete, MolecularAssay.ASSAYMETHOD, this,
        MolecularAssayView.ASSAYMETHOD);
    new AttributeNotificationMap(concrete, MolecularAssay.TARGET, this, MolecularAssayView.TARGET);
  }

  @Override
  public void apply()
  {
    MolecularAssay concrete = UniqueAssayUtil.getOrCreateAssay(MolecularAssay.class,
        this.getUniqueAssayId());
    if (!concrete.isNew())
    {
      concrete.appLock();
    }

    if (this.hasConcrete())
    {
      concrete = MolecularAssay.lock(this.getConcreteId());
    }

    // Build the attribute map between MolecularAssay and
    // MolecularAssayView for error handling
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
      MolecularAssay.get(this.getConcreteId()).delete();
    }
  }

  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }

  @Transaction
  public static MolecularAssayView[] applyAll(MolecularAssayView[] views)
  {
    UniqueAssayUtil.validateUniqueAssayIds(views);
    
    for (MolecularAssayView view : views)
    {
      view.apply();
    }

    return views;
  }

}
