package dss.vector.solutions;

import java.util.HashMap;

import com.runwaysdk.business.generation.view.AttributeEventIF;
import com.runwaysdk.business.generation.view.ContentListener;
import com.runwaysdk.business.generation.view.ViewAllComponentListener;
import com.runwaysdk.constants.ElementInfo;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.generation.loader.Reloadable;

public class MDSSViewAllComponentListener extends ViewAllComponentListener implements ContentListener, Reloadable
{

  public MDSSViewAllComponentListener(MdEntityDAOIF mdEntity)
  {
    super(mdEntity);
  }

  @Override
  public void header()
  {
    writeIncludes();

    MDSSGenerationUtility.writePageTitle(getWriter(), "View_All_" + this.getMdEntity().getTypeName());

    // Display all Warnings at the top of the page
    writeMessages();
  }

  @Override
  public void attribute(AttributeEventIF event)
  {
    MdAttributeDAOIF mdAttribute = event.getMdAttribute();
    String attributeName = mdAttribute.definesAttribute();

    if (!attributeName.equals(ElementInfo.KEY))
    {
      super.attribute(event);
    }
  }

  @Override
  public void form()
  {
    HashMap<String, String> tableMap = new HashMap<String, String>();
    tableMap.put("query", "${query}");
    tableMap.put("var", "item");
    tableMap.put("classes", "displayTable");
    tableMap.put("even", "evenRow");
    tableMap.put("odd", "oddRow");

    getWriter().openTag(TABLE_TAG, tableMap);
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

  protected void generateMoment(MdAttributeDAOIF mdAttribute)
  {
    String componentName = this.getComponentName();
    String attributeName = mdAttribute.definesAttribute();

    HashMap<String, String> attributes = new HashMap<String, String>();
    attributes.put("value", "${" + componentName + "." + attributeName + "}");
    attributes.put("pattern", "${dateFormatPattern}");

    writeAttributeColumn(attributeName);
    getWriter().openTag(ROW_TAG);
    getWriter().writeEmptyTag("fmt:formatDate", attributes);
    getWriter().closeTag();
    getWriter().closeTag();
  }

  protected void generateReference(MdAttributeDAOIF mdAttribute)
  {
    MdAttributeReferenceDAOIF mdReference = (MdAttributeReferenceDAOIF) mdAttribute;
    MdBusinessDAOIF mdBusiness = mdReference.getReferenceMdBusinessDAO();

    String componentName = this.getComponentName();
    String attributeName = mdAttribute.definesAttribute();

    writeAttributeColumn(attributeName);

    // By default the referenced display value is the id. However, this is very
    // uninformative and will always be changed, so check if the referenced
    // business defines a display label or is a Geo Entity and if so use those
    // value instead of id.
    if (MDSSGenerationUtility.isAGeoEntity(mdBusiness))
    {
      getWriter().openTag(ROW_TAG);
      getWriter().writeValue("${" + componentName + "." + attributeName + ".geoId}");
      getWriter().closeTag();
    }
    else if (MDSSGenerationUtility.definesAttribute(mdBusiness, "displayLabel") || MDSSGenerationUtility.isATerm(mdBusiness))
    {
      getWriter().openTag(ROW_TAG);
      getWriter().writeValue("${" + componentName + "." + attributeName + ".displayLabel}");
      getWriter().closeTag();
    }
    else
    {
      getWriter().openTag(ROW_TAG);
      getWriter().writeValue("${" + componentName + "." + attributeName + ".keyName}");
      getWriter().closeTag();
    }

    getWriter().closeTag();
  }

  @Override
  protected void writeCommand(String action, String name, String value)
  {
    String localizedValue = value + "_Localize";

    MDSSGenerationUtility.writeLocalizeTag(getWriter(), value, localizedValue);

    HashMap<String, String> updateMap = new HashMap<String, String>();
    updateMap.put("action", action);
    updateMap.put("name", name);
    updateMap.put("value", "${" + localizedValue + "}");

    getWriter().writeEmptyEscapedTag(COMMAND_TAG, updateMap);
  }
}
