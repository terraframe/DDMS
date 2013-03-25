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
import com.runwaysdk.system.metadata.MdAttributeInteger;
import com.runwaysdk.system.metadata.MdWebInteger;

public class WebIntegerBuilder extends WebPrimitiveBuilder implements Reloadable
{
  public WebIntegerBuilder()
  {
    super();
  }

  public WebIntegerBuilder(MdWebInteger mdWebInteger)
  {
    super(mdWebInteger);
  }

  @Override
  public WebFieldBuilder copy()
  {
    return new WebIntegerBuilder();
  }

  @Override
  public MdWebInteger getMdField()
  {
    return (MdWebInteger) super.getMdField();
  }

  @Override
  protected void updateMdAttribute(MdAttributeConcrete mdAttribute)
  {
    Locale locale = Session.getCurrentLocale();
    Format<Integer> f = AbstractFormatFactory.getFormatFactory().getFormat(Integer.class);
    
    MdAttributeInteger mdAttributeInteger = (MdAttributeInteger) mdAttribute;
    MdWebInteger mdWebInteger = this.getMdField();
    
    String start = mdWebInteger.getStartRange();
    Integer startVal = f.parse(start, locale);
    mdAttributeInteger.setStartRange(startVal);

    
    String end = mdWebInteger.getEndRange();
    Integer endVal = f.parse(end, locale);
    mdAttributeInteger.setEndRange(endVal);
    
    // reset the string values to store as English locale
    mdWebInteger.setStartRange(f.format(startVal, Locale.ENGLISH));
    mdWebInteger.setEndRange(f.format(endVal, Locale.ENGLISH));

    super.updateMdAttribute(mdAttributeInteger);
  }

}