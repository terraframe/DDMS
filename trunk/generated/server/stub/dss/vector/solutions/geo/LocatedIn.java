package dss.vector.solutions.geo;

import dss.vector.solutions.geo.LocatedInBase;
import dss.vector.solutions.geo.generated.GeoEntity;

public class LocatedIn extends LocatedInBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236133821075L;
  
  public LocatedIn(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public LocatedIn(dss.vector.solutions.geo.generated.GeoEntity parent, dss.vector.solutions.geo.generated.GeoEntity child)
  {
    this(parent.getId(), child.getId());
  }
  
  @Override
  public void apply()
  {
    super.apply();
    
    GeoEntity.updateAllPathForGeoEntity(this.getChildId());
  }
}
