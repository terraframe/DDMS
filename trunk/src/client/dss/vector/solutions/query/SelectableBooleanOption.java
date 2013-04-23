package dss.vector.solutions.query;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttributeBooleanDTO;
import com.runwaysdk.transport.attributes.AttributeBooleanDTO;

public class SelectableBooleanOption extends SelectableAttributeOption implements Reloadable
{
  private String positiveLabel;

  private String negativeLabel;

  public SelectableBooleanOption(MdAttributeBooleanDTO mdAttribute, String suffix, String type)
  {
    super(mdAttribute, suffix, type);

    this.positiveLabel = mdAttribute.getPositiveDisplayLabel().getValue();
    this.negativeLabel = mdAttribute.getNegativeDisplayLabel().getValue();
  }

  @Override
  public String getDTOType()
  {
    return AttributeBooleanDTO.class.getName();
  }

  @Override
  protected JSONObject getSerializationMap() throws JSONException
  {
    JSONObject map = super.getSerializationMap();
    
    JSONObject ddMap = new JSONObject();
    ddMap.put("true", positiveLabel);
    ddMap.put("false", negativeLabel);
    
    map.put("dropDownMap", ddMap);
    
//    map.put("dropDownMap", "{'true':'" + positiveLabel + "','false':'" + negativeLabel + "'}");

    return map;
  }
}
