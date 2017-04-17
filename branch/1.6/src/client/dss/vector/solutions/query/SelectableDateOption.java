package dss.vector.solutions.query;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttributeDateDTO;
import com.runwaysdk.transport.attributes.AttributeDateDTO;

public class SelectableDateOption extends SelectableAttributeOption implements Reloadable
{

  public SelectableDateOption(MdAttributeDateDTO mdAttribute, String suffix, String type)
  {
    super(mdAttribute, suffix, type);
  }

  @Override
  public String getDTOType()
  {
    return AttributeDateDTO.class.getName();
  }

}
