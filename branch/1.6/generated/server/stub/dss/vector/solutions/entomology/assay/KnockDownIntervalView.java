package dss.vector.solutions.entomology.assay;

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;

public class KnockDownIntervalView extends KnockDownIntervalViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -835783771;

  public KnockDownIntervalView()
  {
    super();
  }

  public static KnockDownIntervalView build(KnockDownAssay assay, int time)
  {
    KnockDownIntervalView interval = new KnockDownIntervalView();
    interval.setIntervalTime(time);

    if (!assay.isNew())
    {
      interval.setAssay(assay);
    }

    return interval;
  }

  @Override
  public void apply()
  {
    KnockDownInterval concrete = this.getConcrete();

    // Build the attribute map between the concrete object and the view for
    // error handling.
    this.buildAttributeMap(concrete);

    this.populationConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }

  private void populateView(KnockDownInterval concrete)
  {
    this.setConcreteId(concrete.getId());
    this.setAssay(concrete.getAssay());
    this.setIntervalTime(concrete.getIntervalTime());
    this.setAmount(concrete.getAmount());
  }

  private void populationConcrete(KnockDownInterval concrete)
  {
    concrete.setAssay(this.getAssay());
    concrete.setIntervalTime(this.getIntervalTime());
    concrete.setAmount(this.getAmount());
  }

  private void buildAttributeMap(KnockDownInterval concrete)
  {
    new AttributeNotificationMap(concrete, KnockDownInterval.ID, this, KnockDownIntervalView.CONCRETEID);
    new AttributeNotificationMap(concrete, KnockDownInterval.ASSAY, this, KnockDownIntervalView.ASSAY);
    new AttributeNotificationMap(concrete, KnockDownInterval.INTERVALTIME, this, KnockDownIntervalView.INTERVALTIME);
    new AttributeNotificationMap(concrete, KnockDownInterval.AMOUNT, this, KnockDownIntervalView.AMOUNT);
  }

  public KnockDownInterval getConcrete()
  {
    if (this.getConcreteId() != null && this.getConcreteId().length() > 0)
    {
      return KnockDownInterval.lock(this.getConcreteId());
    }
    else
    {
      return new KnockDownInterval();
    }
  }
}
