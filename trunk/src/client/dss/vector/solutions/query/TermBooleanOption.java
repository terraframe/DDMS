package dss.vector.solutions.query;

import java.util.Map;

import com.runwaysdk.generation.loader.Reloadable;

public class TermBooleanOption extends SelectableOption implements Reloadable
{
  private String positiveLabel;

  private String negativeLabel;

  public TermBooleanOption(String attributeName, String displayLabel, String key, String positiveLabel, String negativeLabel)
  {
    super(attributeName, displayLabel, key);

    this.positiveLabel = positiveLabel;
    this.negativeLabel = negativeLabel;
  }

  @Override
  public String getDTOType()
  {
    return "AttributeBooleanDTO";
  }

  @Override
  public String getType()
  {
    return "sqlinteger";
  }
  
  @Override
  protected Map<String, String> getSerializationMap()
  {
    Map<String, String> map = super.getSerializationMap();
    map.put("dropDownMap", "{'0':'" + positiveLabel + "','1':'" + negativeLabel + "'}");

    return map;
  }
}
