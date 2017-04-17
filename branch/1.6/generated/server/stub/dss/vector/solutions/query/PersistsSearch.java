package dss.vector.solutions.query;

public class PersistsSearch extends PersistsSearchBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1241158123251L;
  
  public PersistsSearch(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public PersistsSearch(dss.vector.solutions.MDSSUser parent, dss.vector.solutions.query.SavedSearch child)
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
