package dss.vector.solutions;

import com.terraframe.mojo.business.generation.view.ContentListener;
import com.terraframe.mojo.business.generation.view.UpdateComponentListener;
import com.terraframe.mojo.dataaccess.MdEntityDAOIF;
import com.terraframe.mojo.generation.loader.Reloadable;

public class MDSSUpdateComponentListener extends UpdateComponentListener implements ContentListener, Reloadable
{
  public MDSSUpdateComponentListener(MdEntityDAOIF mdEntity)
  {
    super(mdEntity);
  }

  @Override
  public void header()
  {
    writeIncludes();

    MDSSGenerationUtility.writePageTitle(getWriter(), "Edit_" + this.getMdEntity().getTypeName());

    // Display all Warnings at the top of the page
    writeMessages();
  }

  @Override
  protected void writeIncludes()
  {
    super.writeIncludes();

    MDSSGenerationUtility.writeFMTIncludes(getWriter());
  }

  @Override
  protected void writeCommandLink(String action, String name, String display)
  {
    MDSSGenerationUtility.writeCommandLink(getWriter(), COMMAND_LINK_TAG, action, name, display);
  }

  @Override
  protected void writeCommandLinkWithNoProperties(String action, String name, String display)
  {
    MDSSGenerationUtility.writeCommandLinkWithNoProperties(getWriter(), COMMAND_LINK_TAG, action, name, display);
  }

}
