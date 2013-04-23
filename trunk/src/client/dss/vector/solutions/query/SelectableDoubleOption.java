package dss.vector.solutions.query;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttributeDoubleDTO;
import com.runwaysdk.transport.attributes.AttributeDoubleDTO;

public class SelectableDoubleOption extends SelectableAttributeOption implements Reloadable
{

  public SelectableDoubleOption(MdAttributeDoubleDTO mdAttribute, String suffix, String type)
  {
    super(mdAttribute, suffix, type);
  }

  @Override
  public String getDTOType()
  {
    return AttributeDoubleDTO.class.getName();
  }

}
