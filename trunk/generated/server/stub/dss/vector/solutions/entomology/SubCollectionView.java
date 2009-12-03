package dss.vector.solutions.entomology;

import com.terraframe.mojo.dataaccess.transaction.AttributeNotificationMap;

public class SubCollectionView extends SubCollectionViewBase implements com.terraframe.mojo.generation.loader.Reloadable
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
    this.setFemale(concrete.getFemale());
    this.setIdentMethod(concrete.getIdentMethod());
    this.setLarvae(concrete.getLarvae());
    this.setMale(concrete.getMale());
    this.setPupae(concrete.getPupae());
    this.setTaxon(concrete.getTaxon());
    this.setTotal(concrete.getTotal());
    this.setUnknowns(concrete.getUnknowns());
    this.setSubCollectionId(concrete.getSubCollectionId());
  }

  private void populateConcrete(SubCollection concrete)
  {
    concrete.setCollection(this.getCollection());    
    concrete.setEggs(this.getEggs());
    concrete.setFemale(this.getFemale());
    concrete.setIdentMethod(this.getIdentMethod());
    concrete.setLarvae(this.getLarvae());
    concrete.setMale(this.getMale());
    concrete.setPupae(this.getPupae());
    concrete.setTaxon(this.getTaxon());
    concrete.setTotal(this.getTotal());
    concrete.setUnknowns(this.getUnknowns());
    concrete.setSubCollectionId(this.getSubCollectionId());
  }

  private void buildAttributeMap(SubCollection concrete)
  {
    new AttributeNotificationMap(concrete, SubCollection.ID, this, SubCollectionView.CONCRETEID);
    new AttributeNotificationMap(concrete, SubCollection.COLLECTION, this, SubCollectionView.COLLECTION);
    new AttributeNotificationMap(concrete, SubCollection.EGGS, this, SubCollectionView.EGGS);
    new AttributeNotificationMap(concrete, SubCollection.FEMALE, this, SubCollectionView.FEMALE);
    new AttributeNotificationMap(concrete, SubCollection.IDENTMETHOD, this, SubCollectionView.IDENTMETHOD);
    new AttributeNotificationMap(concrete, SubCollection.LARVAE, this, SubCollectionView.LARVAE);
    new AttributeNotificationMap(concrete, SubCollection.MALE, this, SubCollectionView.MALE);
    new AttributeNotificationMap(concrete, SubCollection.PUPAE, this, SubCollectionView.PUPAE);
    new AttributeNotificationMap(concrete, SubCollection.SUBCOLLECTIONID, this, SubCollectionView.SUBCOLLECTIONID);
    new AttributeNotificationMap(concrete, SubCollection.TAXON, this, SubCollectionView.TAXON);
    new AttributeNotificationMap(concrete, SubCollection.TOTAL, this, SubCollectionView.TOTAL);
    new AttributeNotificationMap(concrete, SubCollection.UNKNOWNS, this, SubCollectionView.UNKNOWNS);
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
