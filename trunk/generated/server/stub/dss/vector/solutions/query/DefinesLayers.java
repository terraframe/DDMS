package dss.vector.solutions.query;

public class DefinesLayers extends DefinesLayersBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240901017839L;
  
  public DefinesLayers(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public DefinesLayers(dss.vector.solutions.query.SavedSearch parent, dss.vector.solutions.query.Layer child)
  {
    this(parent.getId(), child.getId());
  }
  
}
