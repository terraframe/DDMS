package mdss.ivcc.mrc.csu.entomology.assay;




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
    ADDATestInterval interval = null;
    
    if(this.getIntervalId() == null || this.getIntervalId().equals(""))
    {
      interval = new ADDATestInterval();
      interval.setAssay(AdultDiscriminatingDoseAssay.get(this.getAssayId()));
      interval.setPeriod(this.getPeriod());
      interval.setKnockedDown(this.getKnockedDown());
      interval.apply();
      
      this.setIntervalId(interval.getId());
    }
    else
    {
      interval = ADDATestInterval.lock(this.getIntervalId());
      interval.setAssay(AdultDiscriminatingDoseAssay.get(this.getAssayId()));
      interval.setPeriod(this.getPeriod());
      interval.setKnockedDown(this.getKnockedDown());
      interval.apply();      
    }
    
    this.setIntervalTime(interval.getIntervalTime());
  }
  
  public static mdss.ivcc.mrc.csu.entomology.assay.ADDATestIntervalView[] saveAll(mdss.ivcc.mrc.csu.entomology.assay.ADDATestIntervalView[] array)
  {
    for(ADDATestIntervalView view : array)
    {
      view.apply();
    }
    
    return array;
  }  
}
