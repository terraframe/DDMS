/**
 * 
 */
package dss.vector.solutions.form;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdWebComment;

public class WebCommentBuilder extends WebFieldBuilder implements Reloadable
{
  public WebCommentBuilder()
  {
    super();
  }

  public WebCommentBuilder(MdWebComment mdWebComment)
  {
    super(mdWebComment);
  }

  @Override
  public WebFieldBuilder copy()
  {
    return new WebCommentBuilder();
  }

  @Override
  public MdWebComment getMdField()
  {
    return (MdWebComment) super.getMdField();
  }
}