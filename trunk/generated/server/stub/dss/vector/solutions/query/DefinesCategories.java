package dss.vector.solutions.query;

public class DefinesCategories extends DefinesCategoriesBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1241158115026L;
  
  public DefinesCategories(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public DefinesCategories(dss.vector.solutions.query.ThematicLayer parent, dss.vector.solutions.query.AbstractCategory child)
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
