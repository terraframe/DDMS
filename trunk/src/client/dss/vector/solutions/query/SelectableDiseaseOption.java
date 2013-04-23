package dss.vector.solutions.query;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttributeReferenceDTO;
import com.runwaysdk.transport.attributes.AttributeReferenceDTO;

public class SelectableDiseaseOption extends SelectableAttributeOption implements Reloadable
{
  public SelectableDiseaseOption(MdAttributeReferenceDTO mdAttribute, String suffix, String type)
  {
    super(mdAttribute, suffix, type);
  }

  @Override
  public String getDTOType()
  {
    return AttributeReferenceDTO.class.getName();
  }

  @Override
  protected JSONObject getSerializationMap() throws JSONException
  {
    JSONObject map = super.getSerializationMap();
    map.put("isDisease", true);
    return map;
  }
}
