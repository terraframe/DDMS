package dss.vector.solutions.query;

public class HasCategories extends HasCategoriesBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -945255942;
  
  public HasCategories(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public HasCategories(dss.vector.solutions.query.Layer parent, dss.vector.solutions.query.AbstractCategory child)
  {
    this(parent.getId(), child.getId());
  }
  
}
