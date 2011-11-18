package dss.vector.solutions.query;

import java.util.Map;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttributeReferenceDTO;
import com.runwaysdk.transport.attributes.AttributeReferenceDTO;

public class SelectableTermOption extends SelectableAttributeOption implements Reloadable
{

  public SelectableTermOption(MdAttributeReferenceDTO mdAttribute, String suffix, String type)
  {
    super(mdAttribute, suffix, type);
  }

  @Override
  public String getDTOType()
  {
    return AttributeReferenceDTO.class.getName();
  }

  @Override
  protected Map<String, String> getSerializationMap()
  {
    Map<String, String> map = super.getSerializationMap();
    map.put("isTerm", "true");
    return map;
  }
}