package dss.vector.solutions.entomology.assay;

public class LarvaeTestInterval extends LarvaeTestIntervalBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1237579426622L;
  
  public LarvaeTestInterval()
  {
    super();
  }
  
  @Override
  public void validatePeriod()
  {
    super.validatePeriod();

    if (this.getAssay() != null)
    {
      int maxPeriod = this.getAssay().calculatePeriod();

      if (!(this.getPeriod() < maxPeriod))
      {
        String msg = "Interval time * period is larger than exposure time";
        InvalidPeriodProblem p = new InvalidPeriodProblem(msg);
        p.setPeriod(this.getPeriod());
        p.setNotification(this, PERIOD);
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  public void validateQuantityDead()
  {
    super.validateQuantityDead();

    if (this.getAssay() != null)
    {
      Integer quantityTested = this.getAssay().getQuantityTested();

      if (this.getQuantityDead() > quantityTested)
      {
        String msg = "It is impossible to have more mosquitos knocked down then mosquitos tested";

        InvalidKnockDownQuantityProblem p = new InvalidKnockDownQuantityProblem(msg);
        p.setQuantityKnockDown(this.getQuantityDead());
        p.setQuantityTested(quantityTested);
        p.setNotification(this, QUANTITYDEAD);
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  public void apply()
  {
    validateAssay();
    validatePeriod();
    validateQuantityDead();

    super.apply();
  }

  
  public Integer getIntervalTime()
  {
    CollectionAssay assay = this.getAssay();
    
    return (Integer) Math.min(this.getPeriod() * assay.getIntervalTime(), assay.getExposureTime());
  }
}
