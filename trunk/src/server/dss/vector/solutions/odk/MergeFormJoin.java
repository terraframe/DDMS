package dss.vector.solutions.odk;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class MergeFormJoin extends ODKFormJoin
{
  public MergeFormJoin(ODKForm parent, ODKForm child) {
    super(parent, child);
  }
  
  public void writeTranslation(Element parent, Document document, String context, int maxDepth)
  {
    ODKForm child = this.getChild();
    
    child.writeTranslation(parent, document, context, maxDepth);
  }
  
  public void writeBind(Element parent, Document document, String context, int maxDepth)
  {
    ODKForm child = this.getChild();
    
    child.writeBind(parent, document, context, maxDepth);
  }
  
  public void writeBody(Element parent, Document document, String context, int maxDepth)
  {
    ODKForm child = this.getChild();
    
    child.writeBody(parent, document, context, maxDepth);
  }
  
  public void writeInstance(Element parent, Document document, String context, int maxDepth)
  {
    ODKForm child = this.getChild();
    
    child.writeInstance(parent, document, context, maxDepth);
  }
}
