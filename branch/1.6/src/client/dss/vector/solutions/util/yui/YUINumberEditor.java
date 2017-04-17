package dss.vector.solutions.util.yui;

import com.runwaysdk.generation.loader.Reloadable;

public class YUINumberEditor extends YUITextEditor implements Reloadable
{
  private boolean isDecimal;
  
  public YUINumberEditor(boolean isDecimal)
  {
    this.isDecimal = isDecimal;
  }
  
  public boolean isDecimal()
  {
    return isDecimal;
  }
  
  @Override
  public String getType()
  {
    return NUMBER_EDITOR;
  }

}
