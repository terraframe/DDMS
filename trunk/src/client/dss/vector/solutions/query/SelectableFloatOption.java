package dss.vector.solutions.query;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttributeFloatDTO;
import com.runwaysdk.transport.attributes.AttributeFloatDTO;

public class SelectableFloatOption extends SelectableAttributeOption implements Reloadable
{

  public SelectableFloatOption(MdAttributeFloatDTO mdAttribute, String suffix, String type)
  {
    super(mdAttribute, suffix, type);
  }

  @Override
  public String getDTOType()
  {
    return AttributeFloatDTO.class.getName();
  }

}
