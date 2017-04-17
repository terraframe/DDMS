/**
 * 
 */
package dss.vector.solutions.form;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
import com.runwaysdk.system.metadata.MdAttributeText;
import com.runwaysdk.system.metadata.MdWebText;

public class WebTextBuilder extends WebPrimitiveBuilder implements Reloadable
{
  public WebTextBuilder()
  {
    super();
  }

  public WebTextBuilder(MdWebText mdWebText)
  {
    super(mdWebText);
  }

  @Override
  public WebFieldBuilder copy()
  {
    return new WebTextBuilder();
  }

  @Override
  public MdWebText getMdField()
  {
    return (MdWebText) super.getMdField();
  }

  @Override
  protected void updateMdAttribute(MdAttributeConcrete mdAttr)
  {
    MdAttributeText md = (MdAttributeText) mdAttr;

    super.updateMdAttribute(md);
  }
}