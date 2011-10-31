package dss.vector.solutions.query;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttributeCharacterDTO;
import com.runwaysdk.transport.attributes.AttributeCharacterDTO;

public class SelectableCharacterOption extends SelectableAttributeOption implements Reloadable
{
  public SelectableCharacterOption(MdAttributeCharacterDTO mdAttribute, String suffix, String type)
  {
    super(mdAttribute, suffix, type);
  }

  @Override
  public String getDTOType()
  {
    return AttributeCharacterDTO.class.getName();
  }
}
