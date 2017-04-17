/**
 * 
 */
package dss.vector.solutions.form;

import java.math.BigDecimal;
import java.util.Locale;

import com.runwaysdk.format.AbstractFormatFactory;
import com.runwaysdk.format.Format;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;
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
    Locale locale = Session.getCurrentLocale();
    Format<BigDecimal> f = AbstractFormatFactory.getFormatFactory().getFormat(BigDecimal.class);
    
    MdAttributeDecimal mdAttributeDecimal = (MdAttributeDecimal) mdAttribute;

    MdWebDecimal mdWebDecimal = this.getMdField();

    mdAttributeDecimal.setDatabaseLength(mdWebDecimal.getDecPrecision());
    mdAttributeDecimal.setDatabaseDecimal(mdWebDecimal.getDecScale());

    String start = mdWebDecimal.getStartRange();
    BigDecimal startVal = f.parse(start, locale);
    mdAttributeDecimal.setStartRange(startVal);

    String end = mdWebDecimal.getEndRange();
    BigDecimal endVal = f.parse(end, locale);
    mdAttributeDecimal.setEndRange(endVal);
    
    // reset the string values to store as English locale
    mdWebDecimal.setStartRange(f.format(startVal, Locale.ENGLISH));
    mdWebDecimal.setEndRange(f.format(endVal, Locale.ENGLISH));

    super.updateMdAttribute(mdAttribute);
  }
}