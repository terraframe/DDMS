package dss.vector.solutions.geo;

import dss.vector.solutions.geo.SpatialMasterBase;

public class SpatialMaster extends SpatialMasterBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236133815691L;
  
  public SpatialMaster()
  {
    super();
  }
  
  
  @Override
  protected String buildKey()
  {
    //TODO: Naifeh needs to define this key
    return this.getId();
  }
}
