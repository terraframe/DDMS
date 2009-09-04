package dss.vector.solutions.geo;

public class HasSynonym extends HasSynonymBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1248321245257L;
  
  public HasSynonym(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public HasSynonym(dss.vector.solutions.geo.generated.GeoEntity parent, dss.vector.solutions.geo.GeoSynonym child)
  {
    this(parent.getId(), child.getId());
  }
 
  
  @Override
  protected String buildKey()
  {
    //TODO: Nathan needs to define this key
    return this.getId();
  }

}
