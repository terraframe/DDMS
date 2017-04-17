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
import com.runwaysdk.system.metadata.MdAttributeLong;
import com.runwaysdk.system.metadata.MdWebLong;

public class WebLongBuilder extends WebPrimitiveBuilder implements Reloadable
{
  public WebLongBuilder()
  {
    super();
  }

  public WebLongBuilder(MdWebLong mdWebLong)
  {
    super(mdWebLong);
  }

  @Override
  public WebFieldBuilder copy()
  {
    return new WebLongBuilder();
  }

  @Override
  public MdWebLong getMdField()
  {
    return (MdWebLong) super.getMdField();
  }
  
  @Override
  protected void updateMdAttribute(MdAttributeConcrete mdAttribute)
  {
    Locale locale = Session.getCurrentLocale();
    Format<Long> f = AbstractFormatFactory.getFormatFactory().getFormat(Long.class);
    
    MdAttributeLong mdAttributeLong = (MdAttributeLong) mdAttribute;
    MdWebLong mdWebLong = this.getMdField();

    String start = mdWebLong.getStartRange();
    Long startVal = f.parse(start, locale);
    
    mdAttributeLong.setStartRange(startVal);

    String end = mdWebLong.getEndRange();
    Long endVal = f.parse(end, locale);
    mdAttributeLong.setEndRange(endVal);

    // reset the string values to store as English locale
    mdWebLong.setStartRange(f.format(startVal, Locale.ENGLISH));
    mdWebLong.setEndRange(f.format(endVal, Locale.ENGLISH));
    
    super.updateMdAttribute(mdAttributeLong);
  }
}