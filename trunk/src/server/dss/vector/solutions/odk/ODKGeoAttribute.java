package dss.vector.solutions.odk;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;

import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.geo.generated.GeoEntity;

public class ODKGeoAttribute extends AttributeColumn implements Reloadable
{
  public ODKGeoAttribute(MdAttributeDAOIF sourceMdAttr, MdAttributeDAOIF viewMdAttr)
  {
    super(sourceMdAttr, viewMdAttr);
  }
  
  @Override
  public void writeInstance(Element parent, Document document, String title, int maxDepth)
  {
    for (int i = 0; i <= maxDepth; ++i)
    {
      Element geolist = document.createElement(viewMdAttr.definesAttribute() + "_geolist_" + i);
      parent.appendChild(geolist);
    }
  }
  
  @Override
  public void writeTranslation(Element parent, Document document, String title, int maxDepth)
  {
    for (int i = 0; i <= maxDepth; ++i)
    {
      Element text = document.createElement("text");
      
      text.setAttribute("id", "/" + title + "/" + viewMdAttr.definesAttribute() + "_geolist_" + i + ":label");
      
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
    bind0.setAttribute("nodeset", "/" + title + "/" + viewMdAttr.definesAttribute() + "_geolist_0");
    bind0.setAttribute("type", "select1");
    parent.appendChild(bind0);
    
    for (int i = 1; i <= maxDepth; ++i)
    {
      Element bind = document.createElement("bind");
      bind.setAttribute("nodeset", "/" + title + "/" + viewMdAttr.definesAttribute() + "_geolist_" + i);
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
    select1.setAttribute("ref", "/" + title + "/" + viewMdAttr.definesAttribute() + "_geolist_0");
    parent.appendChild(select1);
    
    Element geolist0Label = document.createElement("label");
    geolist0Label.setAttribute("ref", "jr:itext('/" + title + "/" + viewMdAttr.definesAttribute() + "_geolist_0:label')");
    select1.appendChild(geolist0Label);
    
    GeoEntity earth = Earth.getEarthInstance();
    List<GeoEntity> countries = earth.getImmediateChildren();
    for (GeoEntity country : countries)
    {
      Element item = document.createElement("item");
      select1.appendChild(item);
      
      Element label = document.createElement("label");
      label.setTextContent(country.getEntityLabel().getValue());
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
        queries.add(viewMdAttr.definesAttribute() + "_geolist_" + listIndex + "= /" + title + "/" + viewMdAttr.definesAttribute() + "_geolist_" + listIndex);
      }
      
      Element input = document.createElement("input");
      input.setAttribute("query", "instance('" + viewMdAttr.definesAttribute() + "_geolist_" + i + "')/root/item[" + StringUtils.join(queries, " and ") + "]");
      input.setAttribute("ref", "/" + title + "/" + viewMdAttr.definesAttribute() + "_geolist_" + i);
      parent.appendChild(input);
      
      Element label = document.createElement("label");
      input.appendChild(label);
      label.setAttribute("ref", "jr:itext('/" + title + "/" + viewMdAttr.definesAttribute() + "_geolist_" + i + ":label')");
    }
  }
}
