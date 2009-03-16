package dss.vector.solutions.geo;

import dss.vector.solutions.geo.AllowedInBase;

public class AllowedIn extends AllowedInBase implements com.terraframe.mojo.generation.loader.Reloadable
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
  
}