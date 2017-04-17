/**
 * 
 */
package dss.vector.solutions.form;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdWebBreak;

public class WebBreakBuilder extends WebFieldBuilder implements Reloadable
{
  public WebBreakBuilder()
  {
    super();
  }

  public WebBreakBuilder(MdWebBreak mdWebBreak)
  {
    super(mdWebBreak);
  }

  @Override
  public WebFieldBuilder copy()
  {
    return new WebBreakBuilder();
  }

  @Override
  public MdWebBreak getMdField()
  {
    return (MdWebBreak) super.getMdField();
  }
}