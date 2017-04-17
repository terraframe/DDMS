package dss.vector.solutions.entomology;

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.entomology.assay.UniqueAssay;
import dss.vector.solutions.entomology.assay.UniqueAssayUtil;

public class TimeResponseAssayView extends TimeResponseAssayViewBase implements
    com.runwaysdk.generation.loader.Reloadable, UniqueAssay
{
  private static final long serialVersionUID = 482486261;

  public TimeResponseAssayView()
  {
    super();
  }

  public void populateView(TimeResponseAssay concrete)
  {
    this.setUniqueAssayId(concrete.getUniqueAssayId());
    this.setConcreteId(concrete.getId());
    this.setCollection(concrete.getCollection());
    this.setAssay(concrete.getAssay());
    this.setActiveIngredient(concrete.getActiveIngredient());
    this.setSpecies(concrete.getSpecies());
    this.setLifeStage(concrete.getLifeStage());
    this.setSynergist(concrete.getSynergist());
    this.setTestStrainResult(concrete.getTestStrainResult());
    this.setReferenceStrainResult(concrete.getReferenceStrainResult());
  }

  private void populateConcrete(TimeResponseAssay concrete)
  {
    concrete.setUniqueAssayId(this.getUniqueAssayId());

    if (UniqueAssayUtil.allowAttributeUpdate(this, concrete, COLLECTION))
    {
      concrete.setCollection(this.getCollection());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, concrete, ASSAY))
    {
      concrete.setAssay(this.getAssay());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, concrete, ACTIVEINGREDIENT))
    {
      concrete.setActiveIngredient(this.getActiveIngredient());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, concrete, SPECIES))
    {
      concrete.setSpecies(this.getSpecies());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, concrete, LIFESTAGE))
    {
      concrete.setLifeStage(this.getLifeStage());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, concrete, SYNERGIST))
    {
      concrete.setSynergist(this.getSynergist());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, concrete, TESTSTRAINRESULT))
    {
      concrete.setTestStrainResult(this.getTestStrainResult());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, concrete, REFERENCESTRAINRESULT))
    {
      concrete.setReferenceStrainResult(this.getReferenceStrainResult());
    }
  }

  private void buildAttributeMap(TimeResponseAssay concrete)
  {
    new AttributeNotificationMap(concrete, TimeResponseAssay.UNIQUEASSAYID, this,
        TimeResponseAssayView.UNIQUEASSAYID);
    new AttributeNotificationMap(concrete, TimeResponseAssay.ID, this, TimeResponseAssayView.CONCRETEID);
    new AttributeNotificationMap(concrete, TimeResponseAssay.COLLECTION, this,
        TimeResponseAssayView.COLLECTION);
    new AttributeNotificationMap(concrete, TimeResponseAssay.ASSAY, this, TimeResponseAssayView.ASSAY);
    new AttributeNotificationMap(concrete, TimeResponseAssay.ACTIVEINGREDIENT, this,
        TimeResponseAssayView.ACTIVEINGREDIENT);
    new AttributeNotificationMap(concrete, TimeResponseAssay.SPECIES, this,
        TimeResponseAssayView.SPECIES);
    new AttributeNotificationMap(concrete, TimeResponseAssay.LIFESTAGE, this,
        TimeResponseAssayView.LIFESTAGE);
    new AttributeNotificationMap(concrete, TimeResponseAssay.SYNERGIST, this,
        TimeResponseAssayView.SYNERGIST);
    new AttributeNotificationMap(concrete, TimeResponseAssay.TESTSTRAINRESULT, this,
        TimeResponseAssayView.TESTSTRAINRESULT);
    new AttributeNotificationMap(concrete, TimeResponseAssay.REFERENCESTRAINRESULT, this,
        TimeResponseAssayView.REFERENCESTRAINRESULT);
  }

  @Override
  public void apply()
  {
    TimeResponseAssay concrete = UniqueAssayUtil.getOrCreateAssay(TimeResponseAssay.class,
        this.getUniqueAssayId());
    if (!concrete.isNew())
    {
      concrete.appLock();
    }

    if (this.hasConcrete())
    {
      concrete = TimeResponseAssay.lock(this.getConcreteId());
    }

    // Build the attribute map between TimeResponseAssay and
    // TimeResponseAssayView for error handling
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
      TimeResponseAssay.get(this.getConcreteId()).delete();
    }
  }

  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }

  @Transaction
  public static TimeResponseAssayView[] applyAll(TimeResponseAssayView[] views)
  {
    UniqueAssayUtil.validateUniqueAssayIds(views);
    
    for (TimeResponseAssayView view : views)
    {
      view.apply();
    }

    return views;
  }

}
