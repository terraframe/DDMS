package dss.vector.solutions.util;

import com.runwaysdk.generation.loader.Reloadable;

public class RowSetup implements Reloadable
{
  private String getter;
  
  public RowSetup(String getter)
  {
    this.getter = getter;
  }

  public String getGetter()
  {
    return getter;
  }

  public void setGetter(String getter)
  {
    this.getter = getter;
  }
}
