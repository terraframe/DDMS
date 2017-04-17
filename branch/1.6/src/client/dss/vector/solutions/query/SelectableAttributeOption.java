package dss.vector.solutions.query;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttributeConcreteDTO;

public abstract class SelectableAttributeOption extends SelectableOption implements Reloadable
{
  private String type;

  public SelectableAttributeOption(MdAttributeConcreteDTO mdAttribute, String suffix, String type)
  {
    super(mdAttribute.getAttributeName(), mdAttribute.getDisplayLabel().getValue(), mdAttribute.getAttributeName() + suffix);

    this.type = type;
  }

  @Override
  public String getType()
  {
    return type;
  }
}
