package dss.vector.solutions.entomology.assay;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.generation.loader.Reloadable;

public class AdultTestIntervalView extends AdultTestIntervalViewBase implements TestIntervalIF,  Reloadable
{
  private static final long serialVersionUID = 1237579420788L;
  
  public AdultTestIntervalView()
  {
    super();
  }
  
  
  @Override
  public void apply()
  {
    AdultTestInterval interval = null;
    
    if(this.getIntervalId() == null || this.getIntervalId().equals(""))
    {
      interval = new AdultTestInterval();
      interval.setAssay(this.getAssay());
      interval.setPeriod(this.getPeriod());
      interval.setKnockedDown(this.getKnockedDown());
      interval.apply();
      
      this.setIntervalId(interval.getId());
    }
    else
    {
      interval = AdultTestInterval.lock(this.getIntervalId());
      interval.setAssay(this.getAssay());
      interval.setPeriod(this.getPeriod());
      interval.setKnockedDown(this.getKnockedDown());
      interval.apply();      
    }
    
    this.setIntervalTime(interval.getIntervalTime());
  }
  
  @Transaction
  public static AdultTestIntervalView[] saveAll(AdultTestIntervalView[] array)
  {
    for(AdultTestIntervalView view : array)
    {
      view.apply();
    }
    
    return array;
  }

  public Integer getValue()
  {
    return this.getKnockedDown();
  }    
}
