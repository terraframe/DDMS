package dss.vector.solutions.query;

public class HasTextElement extends HasTextElementBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1188609229;
  
  public HasTextElement(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public HasTextElement(dss.vector.solutions.query.SavedMap parent, dss.vector.solutions.query.TextElement child)
  {
    this(parent.getId(), child.getId());
  }
  
}
