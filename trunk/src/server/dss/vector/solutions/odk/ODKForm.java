package dss.vector.solutions.odk;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.MdAttributeStructDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.MdStructDAOIF;
import com.runwaysdk.dataaccess.io.excel.DefaultExcelAttributeFilter;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.dataaccess.io.excel.MdAttributeFilter;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;

import dss.vector.solutions.entomology.MosquitoCollectionView;
import dss.vector.solutions.geo.GeoFilterCriteria;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.ontology.Term;

public class ODKForm implements Reloadable
{
  protected MdClassDAOIF base;
  
  protected ODKForm[] repeats;
  
  protected GeoFilterCriteria gfc;
  
  public ODKForm(MdClassDAOIF base, GeoFilterCriteria gfc, ODKForm ... repeats)
  {
    this.base = base;
    this.repeats = repeats;
    this.gfc = gfc;
  }
  
  public ODKForm(MdClassDAOIF base, ODKForm ... repeats)
  {
    this.base = base;
    this.repeats = repeats;
  }
  
  public void setGeoFilterCriteria(GeoFilterCriteria geoFilters)
  {
    this.gfc = geoFilters;
  }
  
  public GeoFilterCriteria getGeoFilterCriteria()
  {
    return this.gfc;
  }

  public MdClassDAOIF getBase() {
    return base;
  }

  public void setBase(MdClassDAOIF base) {
    this.base = base;
  }
  
  public String getFormName()
  {
    return this.getBase().definesType().replaceAll("\\.", "_");
  }
  
  public ODKForm[] getRepeats() {
    return repeats;
  }

  public void setRepeats(ODKForm[] repeats) {
    this.repeats = repeats;
  }
  
  public void writeTranslation(Element parent, Document document, String title, int maxDepth)
  {
    for (ODKAttribute attr : this.getBaseAttrs())
    {
      attr.writeTranslation(parent, document, title, maxDepth);
    }
    
    for (ODKForm repeat : repeats)
    {
      repeat.writeTranslation(parent, document, title + "/" + repeat.getFormName(), maxDepth);
    }
  }
  
  public void writeBind(Element parent, Document document, String title, int maxDepth)
  {
    for (ODKAttribute attr : this.getBaseAttrs())
    {
      attr.writeBind(parent, document, title, maxDepth);
    }
    
    for (ODKForm repeat : repeats)
    {
      repeat.writeBind(parent, document, title + "/" + repeat.getFormName(), maxDepth);
    }
  }
  
  public void writeBody(Element parent, Document document, String title, int maxDepth)
  {
    for (ODKAttribute attr : this.getBaseAttrs())
    {
      attr.writeBody(parent, document, title, maxDepth);
    }
    
    for (ODKForm repeat : repeats)
    {
      Element group = document.createElement("group");
      parent.appendChild(group);
      group.setAttribute("ref", "/" + title + "/" + repeat.getFormName());
      
      Element label = document.createElement("label");
      group.appendChild(label);
      label.setTextContent(repeat.getBase().getDisplayLabel(Session.getCurrentLocale()));
      
      Element repeatEl = document.createElement("repeat");
      group.appendChild(repeatEl);
      repeatEl.setAttribute("nodeset", "/" + title + "/" + repeat.getFormName());
      
      repeat.writeBody(repeatEl, document, title + "/" + repeat.getFormName(), maxDepth);
    }
  }
  
  public List<ODKAttribute> getBaseAttrs()
  {
    ArrayList<ODKAttribute> attrs = new ArrayList<ODKAttribute>();
    
    MdClassDAOIF mdc = this.getBase();

    /*
     * Global Map of all the exported term ids. This is used to prevent the same
     * term from being translated multiple times even if the term is an item of
     * multiple different attributes.
     */
    Set<String> items = new TreeSet<String>();

    List<? extends MdAttributeDAOIF> mdAttributeDAOs = ExcelUtil.getAttributes(mdc, new Filter());

    // Store relevant information about all the attributes
    for (MdAttributeDAOIF mdAttribute : mdAttributeDAOs)
    {
      MdAttributeConcreteDAOIF mdAttributeConcrete = mdAttribute.getMdAttributeConcrete();

      if (mdAttributeConcrete instanceof MdAttributeStructDAOIF)
      {
        MdAttributeStructDAOIF struct = (MdAttributeStructDAOIF) mdAttributeConcrete;
        MdStructDAOIF mdStruct = struct.getMdStructDAOIF();
        List<? extends MdAttributeDAOIF> structAttributes = ExcelUtil.getAttributes(mdStruct, new Filter());

        for (MdAttributeDAOIF structAttribute : structAttributes)
        {
          attrs.add(new StructColumn(struct, structAttribute));
        }
      }
      else if (mdAttributeConcrete instanceof MdAttributeReferenceDAOIF)
      {
        MdBusinessDAOIF referenceMdBusiness = ( (MdAttributeReferenceDAOIF) mdAttributeConcrete ).getReferenceMdBusinessDAO();
        if (referenceMdBusiness.definesType().equals(GeoEntity.CLASS))
        {
          attrs.add(new ODKGeoAttribute(mdAttribute));
        }
        else
        {
          attrs.add(new ODKTermAttribute(mdAttribute, items));
        }
      }
      else
      {
        attrs.add(new AttributeColumn(mdAttribute));
      }
    }
    
    return attrs;
  }
  
  public List<ODKAttribute> getRepeatAttrs()
  {
    ArrayList<ODKAttribute> attrs = new ArrayList<ODKAttribute>();
    
    for (ODKForm repeat : repeats)
    {
      attrs.addAll(repeat.getBaseAttrs());
    }
    
    return attrs;
  }
  
  private static class Filter extends DefaultExcelAttributeFilter implements MdAttributeFilter, Reloadable
  {

    @Override
    public boolean accept(MdAttributeDAOIF mdAttribute)
    {
      if (mdAttribute instanceof MdAttributeReferenceDAOIF)
      {
        MdBusinessDAOIF referenceMdBusiness = ( (MdAttributeReferenceDAOIF) mdAttribute ).getReferenceMdBusinessDAO();

        String type = referenceMdBusiness.definesType();

        return type.equals(Term.CLASS) || type.equals(GeoEntity.CLASS);
      }

      if (mdAttribute.definesAttribute().equals(MosquitoCollectionView.CONCRETEID))
      {
        return false;
      }

      return super.accept(mdAttribute);
    }

  }
}
