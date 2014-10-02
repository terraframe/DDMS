package dss.vector.solutions.query;

public class HasImage extends HasImageBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -608891671;
  
  public HasImage(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public HasImage(dss.vector.solutions.query.SavedMap parent, dss.vector.solutions.query.MapImage child)
  {
    this(parent.getId(), child.getId());
  }
  
}
