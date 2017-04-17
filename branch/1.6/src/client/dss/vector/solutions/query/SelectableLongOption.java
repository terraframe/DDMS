package dss.vector.solutions.query;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttributeLongDTO;
import com.runwaysdk.transport.attributes.AttributeLongDTO;

public class SelectableLongOption extends SelectableAttributeOption implements Reloadable
{

  public SelectableLongOption(MdAttributeLongDTO mdAttribute, String suffix, String type)
  {
    super(mdAttribute, suffix, type);
  }

  @Override
  public String getDTOType()
  {
    return AttributeLongDTO.class.getName();
  }

}
