package dss.vector.solutions.util.yui;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.generation.loader.Reloadable;

public class YUITextEditor extends YUIEditor implements Reloadable
{

  @Override
  public List<String> getOptions()
  {
    return new LinkedList<String>();
  }

  @Override
  public String getType()
  {
    return TEXTBOX_EDITOR;
  }

  @Override
  public String getDefaultValue(Object value)
  {
    return "'" + value.toString() + "'"; 
  }
}
