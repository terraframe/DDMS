/**
 * 
 */
package dss.vector.solutions.form;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
import com.runwaysdk.system.metadata.MdAttributeDouble;
import com.runwaysdk.system.metadata.MdWebDouble;

public class WebDoubleBuilder extends WebPrimitiveBuilder implements Reloadable
{
  public WebDoubleBuilder()
  {
    super();
  }

  public WebDoubleBuilder(MdWebDouble mdWebDouble)
  {
    super(mdWebDouble);
  }

  @Override
  public WebFieldBuilder copy()
  {
    return new WebDoubleBuilder();
  }

  @Override
  public MdWebDouble getMdField()
  {
    return (MdWebDouble) super.getMdField();
  }

  @Override
  protected void updateMdAttribute(MdAttributeConcrete mdAttributeConcrete)
  {
    MdWebDouble mdWebDouble = this.getMdField();
    MdAttributeDouble mdAttributeDouble = (MdAttributeDouble) mdAttributeConcrete;

    mdAttributeDouble.setDatabaseLength(mdWebDouble.getDecPrecision());
    mdAttributeDouble.setDatabaseDecimal(mdWebDouble.getDecScale());

    String start = mdWebDouble.getStartRange();
    if (start != null && start.trim().length() != 0)
    {
      mdAttributeDouble.setStartRange(Double.parseDouble(start));
    }

    String end = mdWebDouble.getEndRange();
    if (end != null && end.trim().length() != 0)
    {
      mdAttributeDouble.setEndRange(Double.parseDouble(end));
    }

    super.updateMdAttribute(mdAttributeDouble);
  }

}