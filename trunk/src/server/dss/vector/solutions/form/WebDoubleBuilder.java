/**
 * 
 */
package dss.vector.solutions.form;

import java.util.Locale;

import com.runwaysdk.format.AbstractFormatFactory;
import com.runwaysdk.format.Format;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;
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
    Locale locale = Session.getCurrentLocale();
    Format<Double> f = AbstractFormatFactory.getFormatFactory().getFormat(Double.class);
    
    MdWebDouble mdWebDouble = this.getMdField();
    MdAttributeDouble mdAttributeDouble = (MdAttributeDouble) mdAttributeConcrete;

    mdAttributeDouble.setDatabaseLength(mdWebDouble.getDecPrecision());
    mdAttributeDouble.setDatabaseDecimal(mdWebDouble.getDecScale());

    String start = mdWebDouble.getStartRange();
    mdAttributeDouble.setStartRange(f.parse(start, locale));

    String end = mdWebDouble.getEndRange();
    mdAttributeDouble.setEndRange(f.parse(end, locale));

    super.updateMdAttribute(mdAttributeDouble);
  }

}