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
    mdAttributeDecimal.setStartRange(f.parse(start, locale));

    String end = mdWebDecimal.getEndRange();
    mdAttributeDecimal.setEndRange(f.parse(end, locale));

    super.updateMdAttribute(mdAttribute);
  }
}