package dss.vector.solutions.query;

import com.runwaysdk.generation.loader.Reloadable;

public class TermFloatOption extends SelectableOption implements Reloadable
{

  public TermFloatOption(String attributeName, String displayLabel, String key)
  {
    super(attributeName, displayLabel, key);
  }

  @Override
  public String getDTOType()
  {
    return "AttributeFloatDTO";
  }

  @Override
  public String getType()
  {
    return "sqlfloat";
  }

}
