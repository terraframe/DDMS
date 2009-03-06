package csu.mrc.ivcc.mdss.geo;

public class AllowedIn extends AllowedInBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236133819821L;
  
  public AllowedIn(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public AllowedIn(csu.mrc.ivcc.mdss.geo.GeoHierarchy parent, csu.mrc.ivcc.mdss.geo.GeoHierarchy child)
  {
    this(parent.getId(), child.getId());
  }
  
}
