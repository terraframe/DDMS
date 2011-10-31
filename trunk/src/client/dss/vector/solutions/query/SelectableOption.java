package dss.vector.solutions.query;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.runwaysdk.generation.loader.Reloadable;

public abstract class SelectableOption implements Reloadable
{
  private String attributeName;

  private String displayLabel;

  private String key;

  public SelectableOption(String attributeName, String displayLabel, String key)
  {
    this.attributeName = attributeName;
    this.displayLabel = displayLabel;
    this.key = key;
  }

  protected String getAttributeName()
  {
    return attributeName;
  }

  protected String getDisplayLabel()
  {
    return displayLabel;
  }

  protected String getKey()
  {
    return key;
  }

  protected Map<String, String> getSerializationMap()
  {
    Map<String, String> map = new HashMap<String, String>();

    map.put("attributeName", "'" + this.getAttributeName() + "'");
    map.put("displayLabel", "'" + this.getDisplayLabel() + "'");
    map.put("dtoType", "'" + this.getDTOType() + "'");
    map.put("key", "'" + this.getKey() + "'");
    map.put("type", "'" + this.getType() + "'"); 

    return map;
  }

  public final String serialize()
  {
    Map<String, String> map = this.getSerializationMap();
    StringBuffer buffer = new StringBuffer();
    buffer.append("{");

    Set<String> keys = map.keySet();

    for (String key : keys)
    {
      buffer.append("," + key + ":" + map.get(key));
    }

    buffer.append("}");

    return buffer.toString().replaceFirst(",", "");
  }

  public abstract String getDTOType();

  public abstract String getType();
}
