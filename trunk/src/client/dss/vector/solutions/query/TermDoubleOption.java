package dss.vector.solutions.query;

import com.runwaysdk.generation.loader.Reloadable;

public class TermDoubleOption extends SelectableOption implements Reloadable
{

  public TermDoubleOption(String attributeName, String displayLabel, String key)
  {
    super(attributeName, displayLabel, key);
  }

  @Override
  public String getDTOType()
  {
    return "AttributeDoubleDTO";
  }

  @Override
  public String getType()
  {
    return "sqldouble";
  }

}
