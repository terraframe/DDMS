package dss.vector.solutions.entomology.assay;


import com.terraframe.mojo.dataaccess.transaction.Transaction;

import dss.vector.solutions.entomology.assay.DDATestIntervalView;




public class DDATestIntervalView extends DDATestIntervalViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234906559542L;
  
  public DDATestIntervalView()
  {
    super();
  }

  @Override
  public void apply()
  {
    DDATestInterval interval = null;
    
    if(this.getIntervalId() == null || this.getIntervalId().equals(""))
    {
      interval = new DDATestInterval();
      interval.setAssay(this.getAssay());
      interval.setPeriod(this.getPeriod());
      interval.setKnockedDown(this.getKnockedDown());
      interval.apply();
      
      this.setIntervalId(interval.getId());
    }
    else
    {
      interval = DDATestInterval.lock(this.getIntervalId());
      interval.setAssay(this.getAssay());
      interval.setPeriod(this.getPeriod());
      interval.setKnockedDown(this.getKnockedDown());
      interval.apply();      
    }
    
    this.setIntervalTime(interval.getIntervalTime());
  }
  
  @Transaction
  public static dss.vector.solutions.entomology.assay.DDATestIntervalView[] saveAll(dss.vector.solutions.entomology.assay.DDATestIntervalView[] array)
  {
    for(DDATestIntervalView view : array)
    {
      view.apply();
    }
    
    return array;
  }  
}
