package dss.vector.solutions.query;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttributeIntegerDTO;
import com.runwaysdk.transport.attributes.AttributeIntegerDTO;

public class SelectableIntegerOption extends SelectableAttributeOption implements Reloadable
{

  public SelectableIntegerOption(MdAttributeIntegerDTO mdAttribute, String suffix, String type)
  {
    super(mdAttribute, suffix, type);
  }

  @Override
  public String getDTOType()
  {
    return AttributeIntegerDTO.class.getName();
  }

}
