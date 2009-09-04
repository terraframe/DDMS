package dss.vector.solutions.query;

public class DefinesLayers extends DefinesLayersBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1241158175465L;
  
  public DefinesLayers(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public DefinesLayers(dss.vector.solutions.query.SavedSearch parent, dss.vector.solutions.query.UniversalLayer child)
  {
    this(parent.getId(), child.getId());
  }
  
  
  @Override
  protected String buildKey()
  {
    //TODO: Naifeh needs to define this key
    return this.getId();
  }
}
