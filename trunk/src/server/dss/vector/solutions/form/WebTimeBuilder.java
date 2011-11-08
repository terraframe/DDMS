/**
 * 
 */
package dss.vector.solutions.form;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
import com.runwaysdk.system.metadata.MdAttributeTime;
import com.runwaysdk.system.metadata.MdWebTime;

public class WebTimeBuilder extends WebPrimitiveBuilder implements Reloadable
{
  public WebTimeBuilder()
  {
    super();
  }

  public WebTimeBuilder(MdWebTime mdWebTime)
  {
    super(mdWebTime);
  }

  @Override
  public WebFieldBuilder copy()
  {
    return new WebTimeBuilder();
  }

  @Override
  public MdWebTime getMdField()
  {
    return (MdWebTime) super.getMdField();
  }

  @Override
  protected void updateMdAttribute(MdAttributeConcrete mdAttr)
  {
    MdAttributeTime md = (MdAttributeTime) mdAttr;

    super.updateMdAttribute(md);
  }
}