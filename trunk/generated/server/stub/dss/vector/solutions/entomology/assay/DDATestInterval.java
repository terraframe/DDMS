package dss.vector.solutions.entomology.assay;

import dss.vector.solutions.entomology.assay.DDATestIntervalBase;

public class DDATestInterval extends DDATestIntervalBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234543764302L;

  public DDATestInterval()
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
        p.setIntervalId(this.getId());
        p.throwIt();
      }
    }
  }

  @Override
  public void validateKnockedDown()
  {
    super.validateKnockedDown();

    if (this.getAssay() != null)
    {
      Integer quantityTested = this.getAssay().getQuantityTested();

      if (this.getKnockedDown() > quantityTested)
      {
        String msg = "It is impossible to have more mosquitos knocked down then mosquitos tested";

        InvalidKnockDownQuantityProblem p = new InvalidKnockDownQuantityProblem(msg);
        p.setQuantityKnockDown(this.getKnockedDown());
        p.setQuantityTested(quantityTested);
        p.setIntervalId(this.getId());
        p.throwIt();
      }
    }
  }

  @Override
  public void apply()
  {
    validateAssay();
    validatePeriod();
    validateKnockedDown();

    super.apply();
  }

  
  public Integer getIntervalTime()
  {
    CollectionAssay assay = this.getAssay();
    
    return (Integer) Math.min(this.getPeriod() * assay.getIntervalTime(), assay.getExposureTime());
  }

}
