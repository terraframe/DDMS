package dss.vector.solutions.query;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.transport.conversion.ConversionExceptionDTO;
import com.runwaysdk.web.view.html.EscapeUtil;

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

  protected JSONObject getSerializationMap() throws JSONException
  {
    JSONObject map = new JSONObject();
    
    map.put("attributeName", this.getAttributeName());
    map.put("displayLabel", EscapeUtil.escapeHTMLAndJS(this.getDisplayLabel()));
    map.put("dtoType", this.getDTOType());
    map.put("key",this.getKey());
    map.put("type",this.getType()); 

    return map;
  }

  public final JSONObject serialize()
  {
    try
    {
      return this.getSerializationMap();
    }
    catch (JSONException e)
    {
      throw new ConversionExceptionDTO("Error converting instance of ["+this.getClass().getName()+"] to JSON.", e);
    }
  }

  public abstract String getDTOType();

  public abstract String getType();
}
