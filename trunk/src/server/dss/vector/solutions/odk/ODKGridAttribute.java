package dss.vector.solutions.odk;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;

import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermRootCache;
import dss.vector.solutions.util.ReadableAttributeView;

public class ODKGridAttribute extends ODKMetadataAttribute implements Reloadable
{
  public static final String GRID_ATTR_PREFIX = "_GRID_";

  List<ODKAttribute>         gridAttrs        = new ArrayList<ODKAttribute>();

  public ODKGridAttribute(ODKForm containingForm, MdAttributeDAOIF sourceMdAttr, MdAttributeDAOIF viewMdAttr, MdAttributeDAOIF mdAttributeDAO)
  {
    super(containingForm, sourceMdAttr, viewMdAttr);

    constructGridAttrs(sourceMdAttr, viewMdAttr, mdAttributeDAO);
  }

  protected void constructGridAttrs(MdAttributeDAOIF sourceMdAttr, MdAttributeDAOIF viewMdAttr, MdAttributeDAOIF mdAttributeDAO)
  {
    boolean isVisible = ReadableAttributeView.isVisible(sourceMdAttr);

    for (Term term : TermRootCache.getRoots(sourceMdAttr))
    {
      MdAttributeConcreteDAOIF mdAttributeConcrete = mdAttributeDAO.getMdAttributeConcrete();

      String name = GRID_ATTR_PREFIX + sourceMdAttr.definesAttribute() + ODKCompositeGridAttribute.DELIMETER + term.getKey();
      String label = mdAttributeDAO.getDisplayLabel(Session.getCurrentLocale()) + " " + term.getTermDisplayLabel().getValue();
      String type = ODKMetadataAttribute.getODKType(mdAttributeConcrete);

      if (mdAttributeConcrete instanceof MdAttributeBooleanDAOIF)
      {
        gridAttrs.add(new ODKAttributeBoolean(this.getContainingForm(), (MdAttributeBooleanDAOIF) mdAttributeConcrete, mdAttributeDAO, type, name, label, label, sourceMdAttr.isRequired()));
      }
      else
      {
        gridAttrs.add(new ODKAttribute(this.getContainingForm(), type, name, label, label, sourceMdAttr.isRequired(), isVisible));
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
