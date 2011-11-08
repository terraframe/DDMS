/**
 * 
 */
package dss.vector.solutions.form;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdWebGroup;

public class WebGroupBuilder extends WebFieldBuilder implements Reloadable
{
  public WebGroupBuilder()
  {
    super();
  }

  public WebGroupBuilder(MdWebGroup mdWebGroup)
  {
    super(mdWebGroup);
  }

  @Override
  public WebFieldBuilder copy()
  {
    return new WebGroupBuilder();
  }

  @Override
  public MdWebGroup getMdField()
  {
    return (MdWebGroup) super.getMdField();
  }
}