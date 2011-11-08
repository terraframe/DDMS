/**
 * 
 */
package dss.vector.solutions.form;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
import com.runwaysdk.system.metadata.MdWebReference;

public class WebReferenceBuilder extends WebAttributeBuilder implements Reloadable
{
  public WebReferenceBuilder()
  {
    super();
  }

  public WebReferenceBuilder(MdWebReference mdWebReference)
  {
    super(mdWebReference);
  }

  @Override
  public WebFieldBuilder copy()
  {
    return new WebReferenceBuilder();
  }

  @Override
  public MdWebReference getMdField()
  {
    return (MdWebReference) super.getMdField();
  }

  @Override
  public MdAttributeConcrete newMdAttribute()
  {
    return null;
  }
}