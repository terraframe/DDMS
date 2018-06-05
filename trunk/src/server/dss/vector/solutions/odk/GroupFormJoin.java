package dss.vector.solutions.odk;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;

public class GroupFormJoin extends ODKFormJoin implements Reloadable
{
  private boolean standalone;

  public GroupFormJoin(ODKForm parent, ODKForm child)
  {
    this(parent, child, false);
  }

  public GroupFormJoin(ODKForm parent, ODKForm child, boolean standalone)
  {
    super(parent, child);

    this.standalone = standalone;
  }

  public boolean isStandalone()
  {
    return standalone;
  }

  public void writeTranslation(Element parent, Document document, String context, int maxDepth)
  {
    ODKForm child = this.getChild();

    child.writeTranslation(parent, document, context + "/" + child.getFormId(), maxDepth);
  }

  public void writeBind(Element parent, Document document, String context, int maxDepth)
  {
    ODKForm child = this.getChild();

    child.writeBind(parent, document, context + "/" + child.getFormId(), maxDepth);
  }

  public void writeBody(Element parent, Document document, String context, int maxDepth)
  {
    ODKForm child = this.getChild();

    Element group = document.createElement("group");
    parent.appendChild(group);
    group.setAttribute("ref", "/" + context + "/" + child.getFormId());

    Element label = document.createElement("label");
    group.appendChild(label);
    label.setTextContent(child.getViewMd().getDisplayLabel(Session.getCurrentLocale()));

    child.writeBody(group, document, context + "/" + child.getFormId(), maxDepth);
  }

  public void writeInstance(Element parent, Document document, String context, int maxDepth)
  {
    ODKForm child = this.getChild();

    Element group = document.createElement(child.getFormId());
    group.setAttribute("id", child.getFormId());
    parent.appendChild(group);

    child.writeInstance(group, document, child.getFormId(), maxDepth);
  }
}
