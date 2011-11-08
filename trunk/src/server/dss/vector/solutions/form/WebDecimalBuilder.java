/**
 * 
 */
package dss.vector.solutions.form;

import java.math.BigDecimal;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
import com.runwaysdk.system.metadata.MdAttributeDecimal;
import com.runwaysdk.system.metadata.MdWebDecimal;

public class WebDecimalBuilder extends WebPrimitiveBuilder implements Reloadable
{
  public WebDecimalBuilder()
  {
    super();
  }

  public WebDecimalBuilder(MdWebDecimal mdWebDecimal)
  {
    super(mdWebDecimal);
  }

  @Override
  public WebFieldBuilder copy()
  {
    return new WebDecimalBuilder();
  }

  @Override
  public MdWebDecimal getMdField()
  {
    return (MdWebDecimal) super.getMdField();
  }

  @Override
  protected void updateMdAttribute(MdAttributeConcrete mdAttribute)
  {
    MdAttributeDecimal mdAttributeDecimal = (MdAttributeDecimal) mdAttribute;

    MdWebDecimal mdWebDecimal = this.getMdField();

    mdAttributeDecimal.setDatabaseLength(mdWebDecimal.getDecPrecision());
    mdAttributeDecimal.setDatabaseDecimal(mdWebDecimal.getDecScale());

    String start = mdWebDecimal.getStartRange();
    if (start != null && start.trim().length() != 0)
    {
      mdAttributeDecimal.setStartRange(new BigDecimal(start));
    }

    String end = mdWebDecimal.getEndRange();
    if (end != null && end.trim().length() != 0)
    {
      mdAttributeDecimal.setEndRange(new BigDecimal(end));
    }

    super.updateMdAttribute(mdAttribute);
  }
}