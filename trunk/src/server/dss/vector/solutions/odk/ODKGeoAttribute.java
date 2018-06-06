package dss.vector.solutions.odk;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;

import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.geo.generated.GeoEntity;

public class ODKGeoAttribute extends ODKMetadataAttribute implements Reloadable
{
  public static final String PREFIX = "_GEOLIST_";
  
  public ODKGeoAttribute(ODKForm containingForm, MdAttributeDAOIF sourceMdAttr, MdAttributeDAOIF viewMdAttr)
  {
    super(containingForm, sourceMdAttr, viewMdAttr);
  }
  
  @Override
  public void writeInstance(Element parent, Document document, String title, int maxDepth)
  {
    for (int i = 0; i <= maxDepth; ++i)
    {
      Element geolist = document.createElement(viewMdAttr.definesAttribute() + PREFIX + i);
      parent.appendChild(geolist);
    }
  }
  
  @Override
  public void writeTranslation(Element parent, Document document, String title, int maxDepth)
  {
    for (int i = 0; i <= maxDepth; ++i)
    {
      Element text = document.createElement("text");
      
      text.setAttribute("id", "/" + title + "/" + viewMdAttr.definesAttribute() + PREFIX + i + ":label");
      
      Element value = document.createElement("value");
      value.setTextContent(viewMdAttr.getDisplayLabel(Session.getCurrentLocale()) + " Level " + (i+1));
      text.appendChild(value);
      
      parent.appendChild(text);
    }
  }
  
  @Override
  public void writeBind(Element parent, Document document, String title, int maxDepth)
  {
    Element bind0 = document.createElement("bind");
    bind0.setAttribute("nodeset", "/" + title + "/" + viewMdAttr.definesAttribute() + PREFIX +"0");
    bind0.setAttribute("type", "select1");
    parent.appendChild(bind0);
    
    if (this.isRequired())
    {
      bind0.setAttribute("required", "true()");
    }
    
    for (int i = 1; i <= maxDepth; ++i)
    {
      Element bind = document.createElement("bind");
      bind.setAttribute("nodeset", "/" + title + "/" + viewMdAttr.definesAttribute() + PREFIX + i);
      bind.setAttribute("type", "string");
      parent.appendChild(bind);
    }
    
    Element bindGeoId = document.createElement("bind");
    bindGeoId.setAttribute("calculate", "concat('uuid:', uuid())");
    bindGeoId.setAttribute("nodeset", "/" + title + "/meta/instanceID");
    bindGeoId.setAttribute("readonly", "true()");
    bindGeoId.setAttribute("type", "string");
    parent.appendChild(bindGeoId);
  }
  
  @Override
  public void writeBody(Element parent, Document document, String title, int maxDepth)
  {
    Element select1 = document.createElement("select1");
    select1.setAttribute("ref", "/" + title + "/" + viewMdAttr.definesAttribute() + PREFIX +"0");
    parent.appendChild(select1);
    
    Element geolist0Label = document.createElement("label");
    geolist0Label.setAttribute("ref", "jr:itext('/" + title + "/" + viewMdAttr.definesAttribute() + PREFIX + "0:label')");
    select1.appendChild(geolist0Label);
    
    GeoEntity earth = Earth.getEarthInstance();
    List<? extends GeoEntity> countries = ODKFormExporter.getOrderedChildren(earth);
    for (GeoEntity country : countries)
    {
      MdClassDAOIF universal = country.getMdClass();
      
      Element item = document.createElement("item");
      select1.appendChild(item);
      
      Element label = document.createElement("label");
      label.setTextContent(country.getEntityLabel().getValue() + " (" + universal.getDisplayLabel(Session.getCurrentLocale()) + ")");
      item.appendChild(label);
      
      Element value = document.createElement("value");
      value.setTextContent(country.getGeoId() + "##" + country.getMdClass().getId());
      item.appendChild(value);
    }
    
    for (int i = 1; i <= maxDepth; ++i)
    {
      ArrayList<String> queries = new ArrayList<String>();
      for (int listIndex = 0; listIndex < i; ++listIndex)
      {
        queries.add(PREFIX + listIndex + "= /" + title + "/" + viewMdAttr.definesAttribute() + PREFIX + listIndex);
      }
      
      Element input = document.createElement("input");
      input.setAttribute("query", "instance('" + PREFIX + i + "')/root/item[" + StringUtils.join(queries, " and ") + "]");
      input.setAttribute("ref", "/" + title + "/" + viewMdAttr.definesAttribute() + PREFIX + i);
      parent.appendChild(input);
      
      Element label = document.createElement("label");
      input.appendChild(label);
      label.setAttribute("ref", "jr:itext('/" + title + "/" + viewMdAttr.definesAttribute() + PREFIX + i + ":label')");
    }
  }
}
