package mdss.entomology.assay;

public class ADDATestInterval extends ADDATestIntervalBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234543764302L;

  public ADDATestInterval()
  {
    super();
  }

  @Override
  public void validatePeriod()
  {
    super.validatePeriod();

    if (this.getAssay() != null)
    {
      double exposureTime = (double) this.getAssay().getExposureTime();
      Integer intervalTime = this.getAssay().getIntervalTime();

      if (this.getPeriod() > (int) Math.ceil(exposureTime / intervalTime))
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

}
