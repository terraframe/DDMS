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
    mdAttributeLong.setStartRange(f.parse(start, locale));

    String end = mdWebLong.getEndRange();
    mdAttributeLong.setEndRange(f.parse(end, locale));

    super.updateMdAttribute(mdAttributeLong);
  }
}