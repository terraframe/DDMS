package dss.vector.solutions.entomology.assay;

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;

public class AdultDiscriminatingDoseIntervalView extends AdultDiscriminatingDoseIntervalViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1984642489;

  public AdultDiscriminatingDoseIntervalView()
  {
    super();
  }

  public static AdultDiscriminatingDoseIntervalView build(AdultDiscriminatingDoseAssay assay, int time)
  {
    AdultDiscriminatingDoseIntervalView interval = new AdultDiscriminatingDoseIntervalView();
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
    AdultDiscriminatingDoseInterval concrete = this.getConcrete();

    // Build the attribute map between the concrete object and the view for
    // error handling.
    this.buildAttributeMap(concrete);

    this.populationConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }

  private void populateView(AdultDiscriminatingDoseInterval concrete)
  {
    this.setConcreteId(concrete.getId());
    this.setAssay(concrete.getAssay());
    this.setIntervalTime(concrete.getIntervalTime());
    this.setAmount(concrete.getAmount());
  }

  private void populationConcrete(AdultDiscriminatingDoseInterval concrete)
  {
    concrete.setAssay(this.getAssay());
    concrete.setIntervalTime(this.getIntervalTime());
    concrete.setAmount(this.getAmount());
  }

  private void buildAttributeMap(AdultDiscriminatingDoseInterval concrete)
  {
    new AttributeNotificationMap(concrete, AdultDiscriminatingDoseInterval.ID, this, AdultDiscriminatingDoseIntervalView.CONCRETEID);
    new AttributeNotificationMap(concrete, AdultDiscriminatingDoseInterval.ASSAY, this, AdultDiscriminatingDoseIntervalView.ASSAY);
    new AttributeNotificationMap(concrete, AdultDiscriminatingDoseInterval.INTERVALTIME, this, AdultDiscriminatingDoseIntervalView.INTERVALTIME);
    new AttributeNotificationMap(concrete, AdultDiscriminatingDoseInterval.AMOUNT, this, AdultDiscriminatingDoseIntervalView.AMOUNT);
  }

  public AdultDiscriminatingDoseInterval getConcrete()
  {
    if (this.getConcreteId() != null && this.getConcreteId().length() > 0)
    {
      return AdultDiscriminatingDoseInterval.lock(this.getConcreteId());
    }
    else
    {
      return new AdultDiscriminatingDoseInterval();
    }
  }
}
