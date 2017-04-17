package dss.vector.solutions.entomology;

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;

public class SubCollectionView extends SubCollectionViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 954759339;

  public SubCollectionView()
  {
    super();
  }

  public void populateView(SubCollection concrete)
  {
    this.setConcreteId(concrete.getId());
    this.setCollection(concrete.getCollection());
    this.setEggs(concrete.getEggs());
    this.setFemalesFed(concrete.getFemalesFed());
    this.setFemalesGravid(concrete.getFemalesGravid());
    this.setFemalesHalfGravid(concrete.getFemalesHalfGravid());
    this.setFemalesUnfed(concrete.getFemalesUnfed());
    this.setFemalesUnknown(concrete.getFemalesUnknown());
    this.setFemalesTotal(concrete.getFemalesTotal());
    this.setIdentMethod(concrete.getIdentMethod());
    this.setLarvae(concrete.getLarvae());
    this.setMale(concrete.getMale());
    this.setPupae(concrete.getPupae());
    this.setTaxon(concrete.getTaxon());
    this.setTotal(concrete.getTotal());
    this.setUnknowns(concrete.getUnknowns());
    this.setSubCollectionId(concrete.getSubCollectionId());
    this.setParous(concrete.getParous());
    this.setDisected(concrete.getDisected());
  }

  private void populateConcrete(SubCollection concrete)
  {
    concrete.setCollection(this.getCollection());
    concrete.setEggs(this.getEggs());
    concrete.setFemalesFed(this.getFemalesFed());
    concrete.setFemalesGravid(this.getFemalesGravid());
    concrete.setFemalesHalfGravid(this.getFemalesHalfGravid());
    concrete.setFemalesUnfed(this.getFemalesUnfed());
    concrete.setFemalesUnknown(this.getFemalesUnknown());
    concrete.setFemalesTotal(this.getFemalesTotal());
    concrete.setIdentMethod(this.getIdentMethod());
    concrete.setLarvae(this.getLarvae());
    concrete.setMale(this.getMale());
    concrete.setPupae(this.getPupae());
    concrete.setTaxon(this.getTaxon());
    concrete.setTotal(this.getTotal());
    concrete.setUnknowns(this.getUnknowns());
    concrete.setSubCollectionId(this.getSubCollectionId());
    concrete.setParous(this.getParous());
    concrete.setDisected(this.getDisected());
  }

  private void buildAttributeMap(SubCollection concrete)
  {
    new AttributeNotificationMap(concrete, SubCollection.ID, this, SubCollectionView.CONCRETEID);
    new AttributeNotificationMap(concrete, SubCollection.COLLECTION, this, SubCollectionView.COLLECTION);
    new AttributeNotificationMap(concrete, SubCollection.EGGS, this, SubCollectionView.EGGS);
    new AttributeNotificationMap(concrete, SubCollection.FEMALESFED, this, SubCollectionView.FEMALESFED);
    new AttributeNotificationMap(concrete, SubCollection.FEMALESGRAVID, this, SubCollectionView.FEMALESGRAVID);
    new AttributeNotificationMap(concrete, SubCollection.FEMALESHALFGRAVID, this, SubCollectionView.FEMALESHALFGRAVID);
    new AttributeNotificationMap(concrete, SubCollection.FEMALESUNFED, this, SubCollectionView.FEMALESUNFED);
    new AttributeNotificationMap(concrete, SubCollection.FEMALESUNKNOWN, this, SubCollectionView.FEMALESUNKNOWN);
    new AttributeNotificationMap(concrete, SubCollection.FEMALESTOTAL, this, SubCollectionView.FEMALESTOTAL);
    new AttributeNotificationMap(concrete, SubCollection.IDENTMETHOD, this, SubCollectionView.IDENTMETHOD);
    new AttributeNotificationMap(concrete, SubCollection.LARVAE, this, SubCollectionView.LARVAE);
    new AttributeNotificationMap(concrete, SubCollection.MALE, this, SubCollectionView.MALE);
    new AttributeNotificationMap(concrete, SubCollection.PUPAE, this, SubCollectionView.PUPAE);
    new AttributeNotificationMap(concrete, SubCollection.SUBCOLLECTIONID, this, SubCollectionView.SUBCOLLECTIONID);
    new AttributeNotificationMap(concrete, SubCollection.TAXON, this, SubCollectionView.TAXON);
    new AttributeNotificationMap(concrete, SubCollection.TOTAL, this, SubCollectionView.TOTAL);
    new AttributeNotificationMap(concrete, SubCollection.UNKNOWNS, this, SubCollectionView.UNKNOWNS);
    new AttributeNotificationMap(concrete, SubCollection.PAROUS, this, SubCollectionView.PAROUS);
    new AttributeNotificationMap(concrete, SubCollection.DISECTED, this, SubCollectionView.DISECTED);
  }

  @Override
  public void apply()
  {
    SubCollection concrete = new SubCollection();

    if (this.hasConcrete())
    {
      concrete = SubCollection.lock(this.getConcreteId());
    }

    // Build the attribute map between SubCollection and
    // SubCollectionView for error handling
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
      SubCollection.get(this.getConcreteId()).delete();
    }
  }

  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }
}
