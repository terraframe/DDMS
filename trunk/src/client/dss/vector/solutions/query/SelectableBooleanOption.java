package dss.vector.solutions.query;

import java.util.Map;

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
  protected Map<String, String> getSerializationMap()
  {
    Map<String, String> map = super.getSerializationMap();
    map.put("dropDownMap", "{'true':'" + positiveLabel + "','false':'" + negativeLabel + "'}");

    return map;
  }
}
