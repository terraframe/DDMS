package dss.vector.solutions;

import java.util.HashMap;

import com.terraframe.mojo.business.generation.view.ContentListener;
import com.terraframe.mojo.business.generation.view.ViewComponentListener;
import com.terraframe.mojo.constants.GeneratedActions;
import com.terraframe.mojo.dataaccess.MdAttributeDAOIF;
import com.terraframe.mojo.dataaccess.MdAttributeReferenceDAOIF;
import com.terraframe.mojo.dataaccess.MdBusinessDAOIF;
import com.terraframe.mojo.dataaccess.MdClassDAOIF;
import com.terraframe.mojo.dataaccess.MdEntityDAOIF;
import com.terraframe.mojo.generation.loader.Reloadable;

public class MDSSViewComponentListener extends ViewComponentListener implements ContentListener, Reloadable
{
  public MDSSViewComponentListener(MdEntityDAOIF mdEntity)
  {
    super(mdEntity);
  }

  @Override
  public void header()
  {
    writeIncludes();

    MDSSGenerationUtility.writePageTitle(getWriter(), "View_" + this.getMdEntity().getTypeName());

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
  protected void generateReference(MdAttributeDAOIF mdAttribute)
  {
    MdAttributeReferenceDAOIF mdReference = (MdAttributeReferenceDAOIF) mdAttribute;
    MdBusinessDAOIF mdBusiness = mdReference.getReferenceMdBusinessDAO();

    String componentName = this.getComponentName();
    String attributeName = mdAttribute.definesAttribute();

    // By default the referenced display value is the id. However, this is very
    // uninformative and will always be changed, so check if the referenced
    // business defines a display label or is a Geo Entity and if so use those
    // value instead of id.
    if (MDSSGenerationUtility.isAGeoEntity(mdBusiness))
    {
      writeDT(attributeName);
      getWriter().writeValue("${" + componentName + "." + attributeName + ".geoId}");
      getWriter().closeTag();
    }
    else if (MDSSGenerationUtility.definesAttribute(mdBusiness, "displayLabel"))
    {
      writeDT(attributeName);
      getWriter().writeValue("${" + componentName + "." + attributeName + ".displayLabel}");
      getWriter().closeTag();
    }
    else
    {
      super.generateReference(mdAttribute);
    }
  }
  
  @Override
  protected void generateMoment(MdAttributeDAOIF mdAttribute)
  {
    String componentName = this.getComponentName();
    String attributeName = mdAttribute.definesAttribute();

    HashMap<String, String> spanAttributes = new HashMap<String, String>();
    spanAttributes.put("class", "formatDate");

    writeDT(attributeName);
    getWriter().openTag("span", spanAttributes);

    getWriter().writeValue("${" + componentName + "." + attributeName + "}");

    getWriter().closeTag();
    getWriter().closeTag();
  }
  
  @Override
  public void afterCloseForm()
  {
    // Close list tag
    getWriter().closeTag();

    // Generate links for relationship
    MdClassDAOIF mdClass = this.getMdEntity();
    String type = mdClass.definesType();

    // Generate convience ViewAll link
    String link = type + CONTROLLER_SUFFIX + "." + GeneratedActions.VIEW_ALL_ACTION.getName() + "." + MOJO_SUFFIX;
    String linkName = type + "." + GeneratedActions.VIEW_ALL_ACTION.getName() + ".link";
    String linkDisplay = "View All";

    writeCommandLinkWithNoProperties(link, linkName, linkDisplay);
  }
}
