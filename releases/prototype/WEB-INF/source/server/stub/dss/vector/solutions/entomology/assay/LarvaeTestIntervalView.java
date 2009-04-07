package dss.vector.solutions.entomology.assay;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.generation.loader.Reloadable;

public class LarvaeTestIntervalView extends LarvaeTestIntervalViewBase implements TestIntervalIF, Reloadable
{
  private static final long serialVersionUID = 1237579407778L;
  
  public LarvaeTestIntervalView()
  {
    super();
  }

  @Override
  public void apply()
  {
    LarvaeTestInterval interval = null;
    
    if(this.getIntervalId() == null || this.getIntervalId().equals(""))
    {
      interval = new LarvaeTestInterval();
      interval.setAssay(this.getAssay());
      interval.setPeriod(this.getPeriod());
      interval.setQuantityDead(this.getQuantityDead());
      interval.apply();
      
      this.setIntervalId(interval.getId());
    }
    else
    {
      interval = LarvaeTestInterval.lock(this.getIntervalId());
      interval.setAssay(this.getAssay());
      interval.setPeriod(this.getPeriod());
      interval.setQuantityDead(this.getQuantityDead());
      interval.apply();      
    }
    
    this.setIntervalTime(interval.getIntervalTime());
  }
  
  @Transaction
  public static dss.vector.solutions.entomology.assay.LarvaeTestIntervalView[] saveAll(dss.vector.solutions.entomology.assay.LarvaeTestIntervalView[] array)
  {
    for(LarvaeTestIntervalView view : array)
    {
      view.apply();
    }
    
    return array;
  }

  public Integer getValue()
  {
    return this.getQuantityDead();
  }  

}
