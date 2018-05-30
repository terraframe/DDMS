package dss.vector.solutions.odk;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

abstract public class ODKFormJoin
{
  private ODKForm parent;

  private ODKForm child;

  public ODKFormJoin(ODKForm parent, ODKForm child)
  {
    this.parent = parent;
    this.child = child;
  }

  public ODKForm getParent()
  {
    return parent;
  }

  public void setParent(ODKForm parent)
  {
    this.parent = parent;
  }

  public ODKForm getChild()
  {
    return child;
  }

  public void setChild(ODKForm child)
  {
    this.child = child;
  }

  abstract public void writeTranslation(Element parent, Document document, String context, int maxDepth);

  abstract public void writeBind(Element parent, Document document, String context, int maxDepth);

  abstract public void writeBody(Element parent, Document document, String context, int maxDepth);

  abstract public void writeInstance(Element parent, Document document, String context, int maxDepth);

  public boolean isStandalone()
  {
    return false;
  }
}
