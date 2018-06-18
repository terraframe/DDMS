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
import dss.vector.solutions.util.LocalizationFacade;

public class ODKMultiTermAttribute extends ODKMetadataAttribute implements Reloadable
{
  public static final String GRID_ATTR_PREFIX = "_GRID_";

  List<ODKAttribute>         gridAttrs        = new ArrayList<ODKAttribute>();

  public ODKMultiTermAttribute(ODKForm containingForm, MdAttributeDAOIF sourceMdAttr, MdAttributeDAOIF viewMdAttr)
  {
    super(containingForm, sourceMdAttr, viewMdAttr);

    constructGridAttrs(sourceMdAttr, viewMdAttr);
  }

  protected void constructGridAttrs(MdAttributeDAOIF sourceMdAttr, MdAttributeDAOIF viewMdAttr)
  {
    String positiveLabel = LocalizationFacade.getFromBundles("Choice_Yes");
    String negativeLabel = LocalizationFacade.getFromBundles("Choice_No");

    for (Term term : TermRootCache.getRoots(sourceMdAttr))
    {
      String name = GRID_ATTR_PREFIX + sourceMdAttr.definesAttribute() + ODKCompositeGridAttribute.DELIMETER + term.getKey();
      String label = viewMdAttr.getDisplayLabel(Session.getCurrentLocale()) + " " + term.getTermDisplayLabel().getValue();
      String type = "boolean";
      boolean required = ODKMetadataAttribute.calculateRequired(sourceMdAttr, viewMdAttr);

      gridAttrs.add(new ODKAttributeBoolean(this.getContainingForm(), sourceMdAttr, viewMdAttr, type, name, label, label, required, positiveLabel, negativeLabel));
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
