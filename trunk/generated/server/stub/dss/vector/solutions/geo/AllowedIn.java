package dss.vector.solutions.geo;


public class AllowedIn extends AllowedInBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236133819821L;

  public AllowedIn(String parentId, String childId)
  {
    super(parentId, childId);
  }

  public AllowedIn(dss.vector.solutions.geo.GeoHierarchy parent, dss.vector.solutions.geo.GeoHierarchy child)
  {
    this(parent.getId(), child.getId());
  }

  protected String buildKey()
  {
    return this.getChild().getGeoEntityClass().getTypeName()+"In"+this.getParent().getGeoEntityClass().getTypeName();
  }

}
