/**
 * 
 */
package dss.vector.solutions.form;

import com.runwaysdk.generation.loader.Reloadable;
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
    MdAttributeInteger mdAttributeInteger = (MdAttributeInteger) mdAttribute;
    MdWebInteger mdWebInteger = this.getMdField();

    String start = mdWebInteger.getStartRange();
    if (start != null && start.trim().length() != 0)
    {
      mdAttributeInteger.setStartRange(Integer.parseInt(start));
    }

    String end = mdWebInteger.getEndRange();
    if (end != null && end.trim().length() != 0)
    {
      mdAttributeInteger.setEndRange(Integer.parseInt(end));
    }

    super.updateMdAttribute(mdAttributeInteger);
  }

}