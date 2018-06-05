package dss.vector.solutions.odk;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.metadata.MdAttributeDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.metadata.MdWebPrimitive;

import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermRootCache;

public class ODKCompositeGridAttribute extends ODKMetadataAttribute implements Reloadable
{
  public static final String GRID_ATTR_PREFIX = "_GRID_";

  private List<ODKAttribute> gridAttrs        = new ArrayList<ODKAttribute>();

  public ODKCompositeGridAttribute(MdAttributeDAOIF sourceMdAttr, MdAttributeDAOIF viewMdAttr, MdWebPrimitive... fields)
  {
    super(sourceMdAttr, viewMdAttr, 0);

    this.constructGridAttrs(sourceMdAttr, viewMdAttr, fields);
  }

  protected void constructGridAttrs(MdAttributeDAOIF sourceMdAttr, MdAttributeDAOIF viewMdAttr, MdWebPrimitive... fields)
  {
    for (Term term : TermRootCache.getRoots(sourceMdAttr))
    {
      for (MdWebPrimitive field : fields)
      {
        MdAttributeDAOIF mdAttributeDAO = MdAttributeDAO.get(field.getDefiningMdAttributeId());

        String name = GRID_ATTR_PREFIX + sourceMdAttr.definesAttribute() + "." + term.getKey() + "." + mdAttributeDAO.definesAttribute();
        String label = mdAttributeDAO.getDisplayLabel(Session.getCurrentLocale()) + " " + term.getTermDisplayLabel().getValue();
        String type = ODKMetadataAttribute.getODKType(mdAttributeDAO);

        gridAttrs.add(new ODKAttribute(type, name, label, label, 0, sourceMdAttr.isRequired()));
      }
    }
  }

  public ODKCompositeGridAttribute(MdAttributeDAOIF sourceMdAttr, MdAttributeDAOIF viewMdAttr, MdAttributeDAOIF... mdAttributes)
  {
    super(sourceMdAttr, viewMdAttr, 0);

    this.constructGridAttrs(sourceMdAttr, viewMdAttr, mdAttributes);
  }

  protected void constructGridAttrs(MdAttributeDAOIF sourceMdAttr, MdAttributeDAOIF viewMdAttr, MdAttributeDAOIF... mdAttributes)
  {
    for (Term term : TermRootCache.getRoots(sourceMdAttr))
    {
      for (MdAttributeDAOIF mdAttributeDAO : mdAttributes)
      {
        String name = GRID_ATTR_PREFIX + sourceMdAttr.definesAttribute() + "." + term.getKey() + "." + mdAttributeDAO.definesAttribute();
        String label = mdAttributeDAO.getDisplayLabel(Session.getCurrentLocale()) + " " + term.getTermDisplayLabel().getValue();
        String type = ODKMetadataAttribute.getODKType(mdAttributeDAO);

        gridAttrs.add(new ODKAttribute(type, name, label, label, 0, sourceMdAttr.isRequired()));
      }
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
