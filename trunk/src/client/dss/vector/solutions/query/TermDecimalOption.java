package dss.vector.solutions.query;

import com.runwaysdk.generation.loader.Reloadable;

public class TermDecimalOption extends SelectableOption implements Reloadable
{

  public TermDecimalOption(String attributeName, String displayLabel, String key)
  {
    super(attributeName, displayLabel, key);
  }

  @Override
  public String getDTOType()
  {
    return "AttributeDecimalDTO";
  }

  @Override
  public String getType()
  { 
    return "sqldouble";
  }

}
