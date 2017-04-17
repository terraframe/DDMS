/**
 * 
 */
package dss.vector.solutions.form;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdWebHeader;

public class WebHeaderBuilder extends WebFieldBuilder implements Reloadable
{
  public WebHeaderBuilder()
  {
    super();
  }

  public WebHeaderBuilder(MdWebHeader mdWebHeader)
  {
    super(mdWebHeader);
  }

  @Override
  public WebFieldBuilder copy()
  {
    return new WebHeaderBuilder();
  }

  @Override
  public MdWebHeader getMdField()
  {
    return (MdWebHeader) super.getMdField();
  }
}