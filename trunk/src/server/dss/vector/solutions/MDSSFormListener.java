package dss.vector.solutions;

import java.util.HashMap;

import com.terraframe.mojo.business.generation.view.AttributeEventIF;
import com.terraframe.mojo.business.generation.view.ContentListener;
import com.terraframe.mojo.business.generation.view.FormListener;
import com.terraframe.mojo.constants.ElementInfo;
import com.terraframe.mojo.dataaccess.MdAttributeDAOIF;
import com.terraframe.mojo.dataaccess.MdAttributeReferenceDAOIF;
import com.terraframe.mojo.dataaccess.MdBusinessDAOIF;
import com.terraframe.mojo.dataaccess.MdClassDAOIF;
import com.terraframe.mojo.dataaccess.MdEntityDAOIF;
import com.terraframe.mojo.dataaccess.metadata.MdClassDAO;
import com.terraframe.mojo.generation.loader.Reloadable;
import com.terraframe.mojo.gis.dataaccess.MdAttributeGeometryDAOIF;

import dss.vector.solutions.geo.generated.GeoEntity;

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
  protected void generateAttribute(MdAttributeDAOIF attribute)
  {
    // don't generate anything for spatial attributes
    if(!(attribute instanceof MdAttributeGeometryDAOIF))
    {
      super.generateAttribute(attribute);
    }
  }
  
  @Override
  protected void writeIncludes()
  {
    super.writeIncludes();
    
    getWriter().writeValue("<%@ taglib uri=\"/WEB-INF/tlds/mdssLib.tld\" prefix=\"mdss\"%>");

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

    //    <mdss:mo param="term" value="${term}" script="false"/>    
    HashMap<String, String> moMap = new HashMap<String, String>();
    moMap.put("param", attributeName);
    moMap.put("value", "${" + attributeName + "}");
    
    MdClassDAOIF mdClass = mdAttribute.definedByClass();
    MdClassDAOIF mdGeoEntity = MdClassDAO.getMdClassDAO(GeoEntity.CLASS);
    
    if(mdClass.getSuperClasses().contains(mdGeoEntity))
    {
      moMap.put("script", "false");
    }
    
    getWriter().writeEmptyTag("mdss:mo", moMap);

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
    String value = "${" + this.getComponentName() + "." + attributeName + "}";

    writeDT(attributeName);

    //    <mdss:geo param="term" value="${term}"/>    
    HashMap<String, String> map = new HashMap<String, String>();
    map.put("param", attributeName);
    map.put("value", value);

    getWriter().writeEmptyTag("mdss:geo", map);

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
