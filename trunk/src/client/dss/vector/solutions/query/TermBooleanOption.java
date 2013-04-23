package dss.vector.solutions.query;

import org.json.JSONException;
import org.json.JSONObject;

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
  protected JSONObject getSerializationMap() throws JSONException
  {
    JSONObject map = super.getSerializationMap();
    
    JSONObject ddMap = new JSONObject();
    ddMap.put("true", positiveLabel);
    ddMap.put("false", negativeLabel);
    
    map.put("dropDownMap", ddMap);
    
    // TODO this old code not only uses ints instead of booleans but the logic appears inverted.
    // Has this always been broken?
//    map.put("dropDownMap", "{'0':'" + positiveLabel + "','1':'" + negativeLabel + "'}");

    return map;
  }
}
