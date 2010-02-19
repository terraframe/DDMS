package dss.vector.solutions;

import com.terraframe.mojo.business.generation.view.ContentListener;
import com.terraframe.mojo.business.generation.view.CreateComponentListener;
import com.terraframe.mojo.constants.GeneratedActions;
import com.terraframe.mojo.dataaccess.MdBusinessDAOIF;
import com.terraframe.mojo.dataaccess.MdEntityDAOIF;
import com.terraframe.mojo.generation.loader.Reloadable;

public class MDSSCreateComponentListener extends CreateComponentListener implements ContentListener, Reloadable
{
  public MDSSCreateComponentListener(MdEntityDAOIF mdEntity)
  {
    super(mdEntity);
  }

  @Override
  public void header()
  {
    writeIncludes();

    MDSSGenerationUtility.writePageTitle(getWriter(), "Create_" + this.getMdEntity().getTypeName());

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
  
  @Override
  public void beforeCloseForm()
  {
    super.beforeCloseForm();
    
    // Write a cancel button for all GeoEntity subclasses
    MdEntityDAOIF mdEntity = this.getMdEntity();
    if(mdEntity instanceof MdBusinessDAOIF && MDSSGenerationUtility.isAGeoEntity((MdBusinessDAOIF) mdEntity))
    {
      String type = mdEntity.definesType();
      String link = type + CONTROLLER_SUFFIX + "." + GeneratedActions.CANCEL_ACTION.getName() + "." + MOJO_SUFFIX;
      String linkName = type + ".form.cancel.button";
      String linkDisplay = "Cancel";

      writeCommand(link, linkName, linkDisplay);
    }
  }
}
