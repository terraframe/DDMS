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
    Double startVal = f.parse(start, locale);
    mdAttributeDouble.setStartRange(startVal);

    String end = mdWebDouble.getEndRange();
    Double endVal = f.parse(end, locale);
    mdAttributeDouble.setEndRange(endVal);
    
    // reset the string values to store as English locale
    mdWebDouble.setStartRange(f.format(startVal, Locale.ENGLISH));
    mdWebDouble.setEndRange(f.format(endVal, Locale.ENGLISH));

    super.updateMdAttribute(mdAttributeDouble);
  }

}