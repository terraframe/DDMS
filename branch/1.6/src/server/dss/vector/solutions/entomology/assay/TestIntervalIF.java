package dss.vector.solutions.entomology.assay;

import com.runwaysdk.generation.loader.Reloadable;

public interface TestIntervalIF extends Reloadable
{
  public Integer getIntervalTime();
  
  public CollectionAssay getAssay();
  
  public Integer getPeriod();
  
  public Integer getValue();
}
