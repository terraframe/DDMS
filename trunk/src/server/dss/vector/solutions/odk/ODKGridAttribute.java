package dss.vector.solutions.odk;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;

import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermRootCache;

public class ODKGridAttribute extends ODKMetadataAttribute implements Reloadable
{
  public static final String GRID_ATTR_PREFIX = "_GRID_";
  
  List<ODKAttribute> gridAttrs = new ArrayList<ODKAttribute>();
  
  public ODKGridAttribute(MdAttributeDAOIF sourceMdAttr, MdAttributeDAOIF viewMdAttr, String type)
  {
    super(sourceMdAttr, viewMdAttr, 0);
    
    constructGridAttrs(sourceMdAttr, viewMdAttr, type);
  }
  
  protected void constructGridAttrs(MdAttributeDAOIF sourceMdAttr, MdAttributeDAOIF viewMdAttr, String type)
  {
    for (Term term : TermRootCache.getRoots(sourceMdAttr))
    {
      gridAttrs.add(new ODKAttribute(type, GRID_ATTR_PREFIX + sourceMdAttr.definesAttribute() + "." + term.getKey(), term.getTermDisplayLabel().getValue(), term.getTermDisplayLabel().getValue(), 0, sourceMdAttr.isRequired()));
    }
  }
  
  @Override
  public void writeBind(Element parent, Document document, String title, int maxDepth)
  {
    for (ODKAttribute structAttr : gridAttrs)
    {
      structAttr.writeBind(parent, document, title, maxDepth);
    }
  }
  
  @Override
  public void writeTranslation(Element parent, Document document, String title, int maxDepth)
  {
    super.writeTranslation(parent, document, title, maxDepth);
    
    for (ODKAttribute structAttr : gridAttrs)
    {
      structAttr.writeTranslation(parent, document, title, maxDepth);
    }
  }
  
  @Override
  public void writeInstance(Element parent, Document document, String title, int maxDepth)
  {
    for (ODKAttribute structAttr : gridAttrs)
    {
      structAttr.writeInstance(parent, document, title, maxDepth);
    }
  }

  @Override
  public void writeBody(Element parent, Document document, String title, int maxDepth)
  {
    Element group = document.createElement("group");
    parent.appendChild(group);
    
    Element label = document.createElement("label");
    group.appendChild(label);
    label.setTextContent(this.sourceMdAttr.getDisplayLabel(Session.getCurrentLocale()));
    
    for (ODKAttribute structAttr : gridAttrs)
    {
      structAttr.writeBody(group, document, title, maxDepth);
    }
  }

  @Override
  public String getODKType()
  {
    return "group";
  }
}
