package dss.vector.solutions.query;

import com.runwaysdk.generation.loader.Reloadable;

public class TermLongOption extends SelectableOption implements Reloadable
{

  public TermLongOption(String attributeName, String displayLabel, String key)
  {
    super(attributeName, displayLabel, key);
  }

  @Override
  public String getDTOType()
  {
    return "AttributeLongDTO";
  }

  @Override
  public String getType()
  {
    return "sqlbigint";
  }

}
