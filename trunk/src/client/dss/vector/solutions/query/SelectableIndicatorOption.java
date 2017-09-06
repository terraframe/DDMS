package dss.vector.solutions.query;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttributeIndicatorDTO;
import com.runwaysdk.transport.attributes.AttributeIndicatorDTO;

public class SelectableIndicatorOption extends SelectableAttributeOption implements Reloadable
{
  public SelectableIndicatorOption(MdAttributeIndicatorDTO mdAttribute, String suffix, String type)
  {
    super(mdAttribute, suffix, type);
  }

  @Override
  public String getDTOType()
  {
    return AttributeIndicatorDTO.class.getName();
  }

}
