package dss.vector.solutions.query;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttributeDecimalDTO;
import com.runwaysdk.transport.attributes.AttributeDecimalDTO;

public class SelectableDecimalOption extends SelectableAttributeOption implements Reloadable
{

  public SelectableDecimalOption(MdAttributeDecimalDTO mdAttribute, String suffix, String type)
  {
    super(mdAttribute, suffix, type);
  }

  @Override
  public String getDTOType()
  {
    return AttributeDecimalDTO.class.getName();
  }

}
