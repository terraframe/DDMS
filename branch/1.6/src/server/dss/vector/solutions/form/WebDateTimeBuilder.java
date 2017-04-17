/**
 * 
 */
package dss.vector.solutions.form;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
import com.runwaysdk.system.metadata.MdAttributeDateTime;
import com.runwaysdk.system.metadata.MdWebDateTime;

public class WebDateTimeBuilder extends WebPrimitiveBuilder implements Reloadable
{
  public WebDateTimeBuilder()
  {
    super();
  }

  public WebDateTimeBuilder(MdWebDateTime mdWebDateTime)
  {
    super(mdWebDateTime);
  }

  @Override
  public WebFieldBuilder copy()
  {
    return new WebDateTimeBuilder();
  }

  @Override
  public MdWebDateTime getMdField()
  {
    return (MdWebDateTime) super.getMdField();
  }

  @Override
  protected void updateMdAttribute(MdAttributeConcrete mdAttr)
  {
    MdAttributeDateTime md = (MdAttributeDateTime) mdAttr;

    super.updateMdAttribute(md);
  }
}