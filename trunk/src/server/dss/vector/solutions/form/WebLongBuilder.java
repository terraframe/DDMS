/**
 * 
 */
package dss.vector.solutions.form;

import com.runwaysdk.generation.loader.Reloadable;
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
    MdAttributeLong mdAttributeLong = (MdAttributeLong) mdAttribute;
    MdWebLong mdWebLong = this.getMdField();

    String start = mdWebLong.getStartRange();
    if (start != null && start.trim().length() != 0)
    {
      mdAttributeLong.setStartRange(Long.parseLong(start));
    }

    String end = mdWebLong.getEndRange();
    if (end != null && end.trim().length() != 0)
    {
      mdAttributeLong.setEndRange(Long.parseLong(end));
    }

    super.updateMdAttribute(mdAttributeLong);
  }
}