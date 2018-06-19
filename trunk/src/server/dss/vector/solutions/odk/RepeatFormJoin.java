package dss.vector.solutions.odk;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;

public class RepeatFormJoin extends ODKFormJoin implements Reloadable
{
  private boolean standalone;

  public RepeatFormJoin(ODKForm parent, ODKForm child)
  {
    this(parent, child, false);
  }

  public RepeatFormJoin(ODKForm parent, ODKForm child, boolean standalone)
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
    label.setTextContent(child.getFormTitle());

    Element repeatEl = document.createElement("repeat");
    group.appendChild(repeatEl);
    repeatEl.setAttribute("nodeset", "/" + context + "/" + child.getFormId());

    child.writeBody(repeatEl, document, context + "/" + child.getFormId(), maxDepth);
  }

  public void writeInstance(Element parent, Document document, String context, int maxDepth)
  {
    ODKForm child = this.getChild();

    Element repeatRoot = document.createElement(child.getFormId());
    repeatRoot.setAttribute("id", child.getFormId());
    repeatRoot.setAttribute("jr:template", "");
    parent.appendChild(repeatRoot);

    child.writeInstance(repeatRoot, document, child.getFormId(), maxDepth);
  }
}
