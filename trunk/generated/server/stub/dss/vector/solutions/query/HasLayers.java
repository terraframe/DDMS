package dss.vector.solutions.query;

public class HasLayers extends HasLayersBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 837099421;
  
  public HasLayers(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public HasLayers(dss.vector.solutions.query.SavedMap parent, dss.vector.solutions.query.Layer child)
  {
    this(parent.getId(), child.getId());
  }
  
}
