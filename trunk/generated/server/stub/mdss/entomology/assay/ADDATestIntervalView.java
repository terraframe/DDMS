package mdss.entomology.assay;


public class ADDATestIntervalView extends ADDATestIntervalViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234906559542L;
  
  public ADDATestIntervalView()
  {
    super();
  }

  @Override
  public void apply()
  {
    if(this.getIntervalId() == null || this.getIntervalId().equals(""))
    {
      ADDATestInterval interval = new ADDATestInterval();
      interval.setAssay(AdultDiscriminatingDoseAssay.get(this.getAssayId()));
      interval.setPeriod(this.getPeriod());
      interval.setKnockedDown(this.getKnockedDown());
      interval.apply();
    }
    else
    {
      ADDATestInterval interval = ADDATestInterval.get(this.getIntervalId());
      interval.setAssay(AdultDiscriminatingDoseAssay.get(this.getAssayId()));
      interval.setPeriod(this.getPeriod());
      interval.setKnockedDown(this.getKnockedDown());
      interval.apply();
    }
  }
}
