package dss.vector.solutions;

import java.util.HashMap;

import com.terraframe.mojo.business.generation.view.AttributeEventIF;
import com.terraframe.mojo.business.generation.view.ContentListener;
import com.terraframe.mojo.business.generation.view.FormListener;
import com.terraframe.mojo.constants.ElementInfo;
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
  protected void writeIncludes()
  {
    super.writeIncludes();

    getWriter().writeValue("<%@ taglib uri=\"http://java.sun.com/jsp/jstl/fmt\" prefix=\"fmt\"%>");

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
      generateGeoEntityReference(mdAttribute);
    }
    else if (MDSSGenerationUtility.isATerm(mdBusiness))
    {
      generateTermReference(mdAttribute);
    }
    else
    {
      String displayLabel = "displayLabel";
      // Determine if the referenced mdBusiness defines a displayLabel
      // attribute. If it does then use the displayLabel instead of the id.
      if (MDSSGenerationUtility.definesAttribute(mdBusiness, displayLabel))
      {
        generateDisplayLabelReference(mdAttribute, mdBusiness, displayLabel);
      }
      else
      {
        super.generateReference(mdAttribute);
      }
    }
  }

  private void generateTermReference(MdAttributeDAOIF mdAttribute)
  {
    String attributeName = mdAttribute.definesAttribute();

    //    <mjl:dt attribute="generation">
    writeDT(attributeName);
    
    HashMap<String, String> spanMap = new HashMap<String, String>();
    spanMap.put("class", "clickable");
    spanMap.put("id", attributeName + "Btn");
    
    //    <span class="clickable" id="generationBtn"> 
    getWriter().openTag("span", spanMap);

    HashMap<String, String> messageMap = new HashMap<String, String>();
    messageMap.put("key", "Browser");
    
    //    <fmt:message key="Browser"/>
    getWriter().writeEmptyTag("fmt:message", messageMap);
    
    // CLOSING SPAN
    //    </span>
    getWriter().closeTag();
    
    HashMap<String, String> divMap = new HashMap<String, String>();
    divMap.put("class", "ontologyDisplay");
    divMap.put("id", attributeName + "Display");

    //    <div id="generationDisplay" class="ontologyDisplay">
    getWriter().openTag("div", divMap);

    //    ${item.generation.displayLabel}
    String display = "${" + attributeName + " != null ? " + attributeName + ".displayLabel : ''}";
    getWriter().writeValue(display);

    //CLOSING DIV
    //    </div>
    getWriter().closeTag();
        
    // <mjl:input type="hidden" param="generation" id="generation" value="${generation != null ? generation.id : ''}" />
    String value = "${" + attributeName + " != null ? " + attributeName + ".id : ''}";

    HashMap<String, String> inputMap = new HashMap<String, String>();
    inputMap.put("param", attributeName);
    inputMap.put("type", "hidden");
    inputMap.put("id", attributeName);
    inputMap.put("value", value);

    getWriter().writeEmptyTag(INPUT_TAG, inputMap);

    // CLOSING DT Tag
    //    </mjl:dt>      
    getWriter().closeTag();
    
  }

  private void generateDisplayLabelReference(MdAttributeDAOIF mdAttribute, MdBusinessDAOIF mdBusiness, String displayLabel)
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

  private void generateGeoEntityReference(MdAttributeDAOIF mdAttribute)
  {
    String attributeName = mdAttribute.definesAttribute();
    String value = "${" + this.getComponentName() + "." + attributeName + " != null ? " + this.getComponentName() + "." + attributeName + ".geoId : ''}";

    writeDT(attributeName);

    HashMap<String, String> geoIdMap = new HashMap<String, String>();
    geoIdMap.put("param", attributeName + "Id");
    geoIdMap.put("type", "text");
    geoIdMap.put("id", "geoIdEl");
    geoIdMap.put("classes", "geoInput");
    geoIdMap.put("value", value);

    getWriter().writeEmptyEscapedTag(INPUT_TAG, geoIdMap);

    HashMap<String, String> geoEntityMap = new HashMap<String, String>();
    geoEntityMap.put("param", attributeName);
    geoEntityMap.put("type", "hidden");
    geoEntityMap.put("id", "geoEntityId");

    getWriter().writeEmptyEscapedTag(INPUT_TAG, geoEntityMap);

    // Close the dt tag
    getWriter().closeTag();
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
