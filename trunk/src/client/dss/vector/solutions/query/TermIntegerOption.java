package dss.vector.solutions.query;

import com.runwaysdk.generation.loader.Reloadable;

public class TermIntegerOption extends SelectableOption implements Reloadable
{

  public TermIntegerOption(String attributeName, String displayLabel, String key)
  {
    super(attributeName, displayLabel, key);
  }

  @Override
  public String getDTOType()
  {
    return "AttributeIntegerDTO";
  }

  @Override
  public String getType()
  {
    return "sqlinteger";
  }

}
