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
        throw new RuntimeException(msg);
      }
    }
  }

  @Override
  public void validateKnockedDown()
  {
    super.validateKnockedDown();

    if (this.getAssay() != null && this.getKnockedDown() > this.getAssay().getQuantityTested())
    {
      String msg = "It is impossible to have more mosquitos knocked down then mosquitos tested";
      throw new RuntimeException(msg);
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
