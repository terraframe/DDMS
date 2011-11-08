/**
 * 
 */
package dss.vector.solutions.form;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
import com.runwaysdk.system.metadata.MdAttributeFloat;
import com.runwaysdk.system.metadata.MdWebFloat;

public class WebFloatBuilder extends WebPrimitiveBuilder implements Reloadable
{
  public WebFloatBuilder()
  {
    super();
  }

  public WebFloatBuilder(MdWebFloat mdWebFloat)
  {
    super(mdWebFloat);
  }

  @Override
  public WebFieldBuilder copy()
  {
    return new WebFloatBuilder();
  }

  @Override
  public MdWebFloat getMdField()
  {
    return (MdWebFloat) super.getMdField();
  }

  @Override
  protected void updateMdAttribute(MdAttributeConcrete mdAttribute)
  {
    MdAttributeFloat mdAttributeFloat = (MdAttributeFloat) mdAttribute;

    MdWebFloat mdWebFloat = this.getMdField();

    mdAttributeFloat.setDatabaseLength(mdWebFloat.getDecPrecision());
    mdAttributeFloat.setDatabaseDecimal(mdWebFloat.getDecScale());

    String start = mdWebFloat.getStartRange();
    if (start != null && start.trim().length() != 0)
    {
      mdAttributeFloat.setStartRange(Float.parseFloat(start));
    }

    String end = mdWebFloat.getEndRange();
    if (end != null && end.trim().length() != 0)
    {
      mdAttributeFloat.setEndRange(Float.parseFloat(end));
    }

    super.updateMdAttribute(mdAttributeFloat);
  }

}