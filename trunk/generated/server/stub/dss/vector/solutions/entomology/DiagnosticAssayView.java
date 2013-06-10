package dss.vector.solutions.entomology;

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.entomology.assay.UniqueAssayUtil;

public class DiagnosticAssayView extends DiagnosticAssayViewBase implements
    com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1057045498;

  public DiagnosticAssayView()
  {
    super();
  }

  public void populateView(DiagnosticAssay concrete)
  {
    this.setUniqueAssayId(concrete.getUniqueAssayId());
    this.setConcreteId(concrete.getId());
    this.setCollection(concrete.getCollection());
    this.setActiveIngredient(concrete.getActiveIngredient());
    this.setSpecies(concrete.getSpecies());
    this.setLifeStage(concrete.getLifeStage());
    this.setSynergist(concrete.getSynergist());
    this.setOutcome(concrete.getOutcome());
  }

  private void populateConcrete(DiagnosticAssay concrete)
  {
    concrete.setUniqueAssayId(this.getUniqueAssayId());

    if (UniqueAssayUtil.allowAttributeUpdate(this, concrete, COLLECTION))
    {
      concrete.setCollection(this.getCollection());
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

    if (UniqueAssayUtil.allowAttributeUpdate(this, concrete, OUTCOME))
    {
      concrete.setOutcome(this.getOutcome());
    }
  }

  private void buildAttributeMap(DiagnosticAssay concrete)
  {
    new AttributeNotificationMap(concrete, DiagnosticAssay.UNIQUEASSAYID, this,
        DiagnosticAssayView.UNIQUEASSAYID);
    new AttributeNotificationMap(concrete, DiagnosticAssay.ID, this, DiagnosticAssayView.CONCRETEID);
    new AttributeNotificationMap(concrete, DiagnosticAssay.COLLECTION, this,
        DiagnosticAssayView.COLLECTION);
    new AttributeNotificationMap(concrete, DiagnosticAssay.ACTIVEINGREDIENT, this,
        DiagnosticAssayView.ACTIVEINGREDIENT);
    new AttributeNotificationMap(concrete, DiagnosticAssay.SPECIES, this, DiagnosticAssayView.SPECIES);
    new AttributeNotificationMap(concrete, DiagnosticAssay.LIFESTAGE, this,
        DiagnosticAssayView.LIFESTAGE);
    new AttributeNotificationMap(concrete, DiagnosticAssay.SYNERGIST, this,
        DiagnosticAssayView.SYNERGIST);
    new AttributeNotificationMap(concrete, DiagnosticAssay.OUTCOME, this, DiagnosticAssayView.OUTCOME);
  }

  @Override
  public void apply()
  {
    DiagnosticAssay concrete = UniqueAssayUtil.getOrCreateAssay(DiagnosticAssay.class,
        this.getUniqueAssayId());
    if (!concrete.isNew())
    {
      concrete.appLock();
    }

    if (this.hasConcrete())
    {
      concrete = DiagnosticAssay.lock(this.getConcreteId());
    }

    // Build the attribute map between DiagnosticAssay and
    // DiagnosticAssayView for error handling
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
      DiagnosticAssay.get(this.getConcreteId()).delete();
    }
  }

  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }

  @Transaction
  public static DiagnosticAssayView[] applyAll(DiagnosticAssayView[] views)
  {
    for (DiagnosticAssayView view : views)
    {
      view.apply();
    }

    return views;
  }

}
