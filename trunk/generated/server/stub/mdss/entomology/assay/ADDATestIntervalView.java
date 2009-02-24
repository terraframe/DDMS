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
      
      this.setIntervalId(interval.getId());
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
  
  public static mdss.entomology.assay.ADDATestIntervalView[] saveAll(mdss.entomology.assay.ADDATestIntervalView[] array)
  {
    for(ADDATestIntervalView view : array)
    {
      view.apply();
    }
    
    return array;
  }

}
