package dss.vector.solutions.kaleidoscope.wrapper;

import com.runwaysdk.generation.loader.Reloadable;

public interface Component extends Reloadable
{
  public void accepts(MapVisitor visitor);
  
  public String getId();
  
//  public void setName(String name);
  
  public String getName();
}
