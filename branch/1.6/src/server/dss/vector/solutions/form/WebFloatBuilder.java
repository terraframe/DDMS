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
    Locale locale = Session.getCurrentLocale();
    Format<Float> f = AbstractFormatFactory.getFormatFactory().getFormat(Float.class);
    
    MdAttributeFloat mdAttributeFloat = (MdAttributeFloat) mdAttribute;

    MdWebFloat mdWebFloat = this.getMdField();

    mdAttributeFloat.setDatabaseLength(mdWebFloat.getDecPrecision());
    mdAttributeFloat.setDatabaseDecimal(mdWebFloat.getDecScale());

    String start = mdWebFloat.getStartRange();
    Float startVal = f.parse(start, locale);
    mdAttributeFloat.setStartRange(startVal);

    String end = mdWebFloat.getEndRange();
    Float endVal = f.parse(end, locale);
    mdAttributeFloat.setEndRange(endVal);
    
    // reset the string values to store as English locale
    mdWebFloat.setStartRange(f.format(startVal, Locale.ENGLISH));
    mdWebFloat.setEndRange(f.format(endVal, Locale.ENGLISH));

    super.updateMdAttribute(mdAttributeFloat);
  }

}