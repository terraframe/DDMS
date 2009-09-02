package dss.vector.solutions;

import java.util.HashMap;

import com.terraframe.mojo.business.generation.view.ContentListener;
import com.terraframe.mojo.business.generation.view.FormListener;
import com.terraframe.mojo.dataaccess.MdAttributeDAOIF;
import com.terraframe.mojo.dataaccess.MdAttributeReferenceDAOIF;
import com.terraframe.mojo.dataaccess.MdBusinessDAOIF;
import com.terraframe.mojo.dataaccess.MdEntityDAOIF;
import com.terraframe.mojo.generation.loader.Reloadable;

public class MDSSFormListener extends FormListener implements ContentListener, Reloadable
{

  public MDSSFormListener(MdEntityDAOIF mdEntity)
  {
    super(mdEntity);
  }

  @Override
  protected void writeIncludes()
  {
    super.writeIncludes();

    if (MDSSGenerationUtility.hasGeoEntityReference(this.getMdEntity()))
    {
      HashMap<String, String> attributes = new HashMap<String, String>();
      attributes.put("page", "/WEB-INF/selectSearch.jsp");

      getWriter().writeEmptyTag(INCLUDE_TAG, attributes);
    }
  }

  @Override
  protected void generateReference(MdAttributeDAOIF mdAttribute)
  {
    MdAttributeReferenceDAOIF mdReference = (MdAttributeReferenceDAOIF) mdAttribute;
    MdBusinessDAOIF mdBusiness = mdReference.getReferenceMdBusinessDAO();

    if (MDSSGenerationUtility.isAGeoEntity(mdBusiness))
    {
      String attributeName = mdAttribute.definesAttribute();

      writeDT(attributeName);

      HashMap<String, String> geoIdMap = new HashMap<String, String>();
      geoIdMap.put("param", attributeName + "Id");
      geoIdMap.put("type", "text");
      geoIdMap.put("id", "geoIdEl");
      geoIdMap.put("classes", "geoInput");

      getWriter().writeEmptyEscapedTag(INPUT_TAG, geoIdMap);

      HashMap<String, String> geoEntityMap = new HashMap<String, String>();
      geoEntityMap.put("param", attributeName);
      geoEntityMap.put("type", "hidden");
      geoEntityMap.put("id", "geoEntityId");

      getWriter().writeEmptyEscapedTag(INPUT_TAG, geoEntityMap);

      // Close the dt tag
      getWriter().closeTag();
    }
    else
    {
      String displayLabel = "displayLabel";
      // Determine if the referenced mdBusiness defines a displayLabel
      // attribute. If it does then use the displayLabel instead of the id.
      if (MDSSGenerationUtility.definesAttribute(mdBusiness, displayLabel))
      {
        mdBusiness.definesAttribute(displayLabel);

        String attributeName = mdAttribute.definesAttribute();

        writeDT(attributeName);
        writeSelect("${" + attributeName + "}", "current", attributeName, "id");

        writeOption("${current." + displayLabel + "}");

        // Close the select tag
        getWriter().closeTag();

        // Close the dt tag
        getWriter().closeTag();
      }
      else
      {
        super.generateReference(mdAttribute);
      }
    }
  }

  @Override
  protected void generateMoment(MdAttributeDAOIF mdAttribute)
  {
    HashMap<String, String> attributes = new HashMap<String, String>();
    attributes.put("attribute", mdAttribute.definesAttribute());
    attributes.put("type", "text");
    attributes.put("classes", "DatePick");

    getWriter().writeEmptyTag(MOJO_DT_TAG, attributes);
  }
}
