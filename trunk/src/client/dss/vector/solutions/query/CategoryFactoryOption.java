package dss.vector.solutions.query;

import com.runwaysdk.business.ValueObjectDTO;
import com.runwaysdk.generation.loader.Reloadable;

/**
 * This class is used instead of ValueObjectDTOs because those are tricky
 * within EL.
 */
public class CategoryFactoryOption implements Reloadable
{
  private String type;
  private String display;
  
  public CategoryFactoryOption(ValueObjectDTO vo)
  {
    this.type = vo.getValue(QueryConstants.CATEGORY_FACTORY_TYPE);
    this.display = vo.getValue(QueryConstants.CATEGORY_FACTORY_DISPLAY);
  }
  
  public String getType()
  {
    return type;
  }
  
  public String getDisplay()
  {
    return display;
  }
}
