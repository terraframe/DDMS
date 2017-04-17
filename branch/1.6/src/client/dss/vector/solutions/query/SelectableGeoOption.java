package dss.vector.solutions.query;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttributeConcreteDTO;
import com.runwaysdk.transport.attributes.AttributeCharacterDTO;

public class SelectableGeoOption extends SelectableOption implements Reloadable
{

  public SelectableGeoOption(MdAttributeConcreteDTO mdAttribute, String suffix, String type)
  {
    super(mdAttribute.getAttributeName() + "_displayLabel", mdAttribute.getDisplayLabel().getValue(), mdAttribute.getAttributeName());
  }

  @Override
  public String getDTOType()
  {
    return AttributeCharacterDTO.class.getName();
  }

  @Override
  public String getType()
  {
    return "sqlcharacter";
  }

  @Override
  protected JSONObject getSerializationMap() throws JSONException
  {
    JSONObject map = super.getSerializationMap();
    
    map.put("isGeoEntity", true);
    
    return map;
  }
}
