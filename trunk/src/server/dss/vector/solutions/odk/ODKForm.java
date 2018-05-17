package dss.vector.solutions.odk;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.metadata.MdAttributeDAO;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.dataaccess.metadata.MetadataDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.metadata.MdView;

import dss.vector.solutions.geo.GeoFilterCriteria;

public class ODKForm implements Reloadable
{
  protected MdClassDAOIF        base;

  protected GeoFilterCriteria   gfc;

  protected MdClassDAOIF        target;

  protected Map<String, String> mapping;
  
  protected Map<String,LinkedList<ODKAttribute>> repeats;
  
  protected LinkedList<ODKAttribute> baseAttrs;
  
  /*
   * Global Map of all the exported term ids. This is used to prevent the same
   * term from being translated multiple times even if the term is an item of
   * multiple different attributes.
   */
  protected Set<String> exportedTerms = new TreeSet<String>();
  
  public ODKForm(MdClassDAOIF base)
  {
    this(base, null, null);
  }
  
  public ODKForm(String base, GeoFilterCriteria gfc)
  {
    this(MdClassDAO.getMdClassDAO(base), null, gfc);
  }

  public ODKForm(MdClassDAOIF base, GeoFilterCriteria gfc)
  {
    this(base, null, gfc);
  }

  public ODKForm(MdClassDAOIF base, MdClassDAOIF target, GeoFilterCriteria gfc)
  {
    this.base = base;
    this.target = target;
    this.gfc = gfc;
    this.baseAttrs = new LinkedList<ODKAttribute>();
    this.mapping = new HashMap<String, String>(1);

    this.init();
  }

  private final void init()
  {
    init(this.target);
  }

  private final void init(MdClassDAOIF target)
  {
    /*
     * Initialize default mapping with a simple name match
     */
    if (target != null)
    {
      this.populateMapping(this.base, target);

      for (ODKForm repeat : this.repeats)
      {
        repeat.init(target);
      }
    }
    
    mapAttributes();
  }

  private void populateMapping(MdClassDAOIF sourceClass, MdClassDAOIF target)
  {
    List<? extends MdAttributeDAOIF> mdAttributes = sourceClass.getAllDefinedMdAttributes();

    for (MdAttributeDAOIF mdAttribute : mdAttributes)
    {
      if (!mdAttribute.isSystem())
      {
        String sourceName = mdAttribute.definesAttribute();
        MdAttributeDAOIF targetAttribute = target.definesAttribute(sourceName);

        if (targetAttribute != null)
        {
          this.mapping.put(sourceName, targetAttribute.definesAttribute());
        }
      }
    }
  }

  public void setMapping(String sourceAttribute, String targetAttribute)
  {
    this.mapping.put(sourceAttribute, targetAttribute);
  }

  public void setTarget(MdClassDAOIF target)
  {
    this.target = target;
  }

  public MdClassDAOIF getTarget()
  {
    return target;
  }

  public void setMapping(Map<String, String> mapping)
  {
    this.mapping = mapping;
  }

  public Map<String, String> getMapping()
  {
    return mapping;
  }

  public void setGeoFilterCriteria(GeoFilterCriteria geoFilters)
  {
    this.gfc = geoFilters;
  }

  public GeoFilterCriteria getGeoFilterCriteria()
  {
    return this.gfc;
  }

  public MdClassDAOIF getBase()
  {
    return base;
  }

  public void setBase(MdClassDAOIF base)
  {
    this.base = base;
  }

  public String getFormName()
  {
    return ODKForm.dataTypeToFormName(this.getBase().definesType());
  }

  public void writeTranslation(Element parent, Document document, String title, int maxDepth)
  {
    for (ODKAttribute attr : this.getBaseAttrs())
    {
      attr.writeTranslation(parent, document, title, maxDepth);
    }

    for (String sourceDataType : repeats.keySet())
    {
      for (ODKAttribute odkAttr : repeats.get(sourceDataType))
      {
        odkAttr.writeTranslation(parent, document, title + "/" + ODKForm.dataTypeToFormName(sourceDataType), maxDepth);
      }
    }
  }

  public void writeBind(Element parent, Document document, String title, int maxDepth)
  {
    for (ODKAttribute attr : this.getBaseAttrs())
    {
      attr.writeBind(parent, document, title, maxDepth);
    }

    for (String sourceDataType : repeats.keySet())
    {
      for (ODKAttribute odkAttr : repeats.get(sourceDataType))
      {
        odkAttr.writeBind(parent, document, title + "/" + ODKForm.dataTypeToFormName(sourceDataType), maxDepth);
      }
    }
  }

  public void writeBody(Element parent, Document document, String title, int maxDepth)
  {
    for (ODKAttribute attr : this.getBaseAttrs())
    {
      attr.writeBody(parent, document, title, maxDepth);
    }

    for (String sourceDataType : repeats.keySet())
    {
      MdClassDAOIF sourceMdClass = MdClassDAO.getMdClassDAO(sourceDataType);
      
      for (ODKAttribute odkAttr : repeats.get(sourceDataType))
      {
        String repeatFormName = ODKForm.dataTypeToFormName(sourceDataType);
        
        Element group = document.createElement("group");
        parent.appendChild(group);
        group.setAttribute("ref", "/" + title + "/" + repeatFormName);
  
        Element label = document.createElement("label");
        group.appendChild(label);
        label.setTextContent(sourceMdClass.getDisplayLabel(Session.getCurrentLocale()));
  
        Element repeatEl = document.createElement("repeat");
        group.appendChild(repeatEl);
        repeatEl.setAttribute("nodeset", "/" + title + "/" + repeatFormName);
  
        odkAttr.writeBody(repeatEl, document, title + "/" + repeatFormName, maxDepth);
      }
    }
  }
  
  public void writeInstance(Element parent, Document document, String title, int maxDepth)
  {
    for (ODKAttribute attr : this.baseAttrs)
    {
      attr.writeInstance(parent, document, title, maxDepth);
    }
    
    for (String repeatDatatype : repeats.keySet())
    {
      String repeatFormName = ODKForm.dataTypeToFormName(repeatDatatype);
      
      Element repeatRoot = document.createElement(repeatFormName);
      repeatRoot.setAttribute("id", repeatFormName);
      parent.appendChild(repeatRoot);
      
      for (ODKAttribute attr : repeats.get(repeatDatatype))
      {
        attr.writeInstance(repeatRoot, document, repeatFormName, maxDepth);
      }
    }
  }
  
  public static String dataTypeToFormName(String dataType)
  {
    return dataType.replaceAll("\\.", "_");
  }
  
  private void mapAttributes()
  {
    MobileImportViewIF mobileView = ((MobileImportViewIF)this.base);
    Map<String,String[]> sourceMap = mobileView.getAttributeSourceMap();
    this.repeats = new HashMap<String, LinkedList<ODKAttribute>>();
    LinkedList<String> attrOrder = mobileView.getAttributeOrder();

    for (String sourceDatatype : sourceMap.keySet())
    {
      String[] saMdAttr = sourceMap.get(sourceDatatype);
      
      LinkedList<ODKAttribute> repeatAttrs = new LinkedList<ODKAttribute>();
      
      for (String sMdAttr : saMdAttr)
      {
        MdAttributeDAOIF mdAttr = MdAttributeDAO.getByKey(sourceDatatype + "." + sMdAttr);
        ODKAttribute odkAttr = ODKAttribute.factory(mdAttr, exportedTerms);
        
        if (sourceDatatype.equals(this.base.definesType()))
        {
          this.baseAttrs.add(odkAttr);
        }
        else
        {
          repeatAttrs.add(odkAttr);
        }
      }
      
      if (!sourceDatatype.equals(this.base.definesType()))
      {
        this.repeats.put(sourceDatatype, repeatAttrs);
      }
    }
    
    Comparator<ODKAttribute> sorter = new Comparator<ODKAttribute>(){
      @Override
      public int compare(ODKAttribute one,ODKAttribute two)
      {
        int oneI = attrOrder.indexOf(one.getAttributeName());
        int twoI = attrOrder.indexOf(two.getAttributeName());
        
        if (oneI < twoI) { return -1; }
        else if (oneI == twoI) { return 0; }
        else { return 1; }
      }
    };
    
    this.baseAttrs.sort(sorter);
    
    for (String sourceDatatype : this.repeats.keySet())
    {
      this.repeats.get(sourceDatatype).sort(sorter);
    }
  }
  
  public List<ODKAttribute> getBaseAttrs()
  {
    return this.baseAttrs;
  }

  public Map<String,LinkedList<ODKAttribute>> getRepeatAttrs()
  {
    return this.repeats;
  }

  public boolean isGeoAttribute(String attributeName)
  {
    return attributeName.contains("_geolist_");
  }

  public boolean isRepeatable(String attributeName)
  {
    return ( this.getRepeatable(attributeName) != null );
  }
  
  public ODKForm getRepeatable(String attributeName)
  {
    if (attributeName.contains("_"))
    {
      String type = attributeName.replaceAll("_", ".");

      for (ODKForm repeat : repeats)
      {
        if (repeat.getBase().definesType().equals(type))
        {
          return repeat;
        }
      }
    }

    return null;
  }

  public boolean hasSourceAttribute(String sourceAttribute)
  {
    return mapping.containsKey(sourceAttribute);
  }

  public String getTargetAttribute(String sourceAttribute)
  {
    return mapping.get(sourceAttribute);
  }
}
