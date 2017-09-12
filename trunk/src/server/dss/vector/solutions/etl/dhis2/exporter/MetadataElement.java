package dss.vector.solutions.etl.dhis2.exporter;

import org.json.JSONException;
import org.json.JSONObject;

public class MetadataElement
{
  private static final String append = " ";
  
  private JSONObject json;
  
  public MetadataElement()
  {
    this.json = new JSONObject();
  }
  
  public JSONObject put(String key, Object value) throws JSONException
  {
    return this.json.put(key, value);
  }
  
  public JSONObject setName(String name) throws JSONException
  {
    return this.json.put("name", name + append);
  }
  
  public JSONObject setCode(String code) throws JSONException
  {
    if (code.length() > (50 - append.length()))
    {
      code = code.substring(0, (50 - append.length()));
    }
    
    code = code + append;
    
    return this.json.put("code", code);
  }
  
  public JSONObject setShortName(String shortName) throws JSONException
  {
    if (shortName.length() > (50 - append.length()))
    {
      shortName = shortName.substring(0, (50 - append.length()));
    }
    
    shortName = shortName + append;
    
    return this.json.put("shortName", shortName);
  }
  
  public JSONObject setId(String id) throws JSONException
  {
    return this.json.put("id", id);
  }
  
  public String toString()
  {
    return this.json.toString();
  }
  
  public JSONObject getJSON()
  {
    return this.json;
  }
}
