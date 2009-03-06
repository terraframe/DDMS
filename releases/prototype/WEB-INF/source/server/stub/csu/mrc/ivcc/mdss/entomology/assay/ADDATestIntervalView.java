package csu.mrc.ivcc.mdss.entomology.assay;


import com.terraframe.mojo.dataaccess.transaction.Transaction;

import csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalViewBase;




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
  
  @Transaction
  public static csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalView[] saveAll(csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalView[] array)
  {
    for(ADDATestIntervalView view : array)
    {
      view.apply();
    }
    
    return array;
  }  
}