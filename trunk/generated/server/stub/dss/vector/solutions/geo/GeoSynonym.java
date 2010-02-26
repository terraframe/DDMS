package dss.vector.solutions.geo;

import dss.vector.solutions.geo.generated.GeoEntity;

public class GeoSynonym extends GeoSynonymBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1248321233376L;

  public GeoSynonym()
  {
    super();
  }

  public static void createSynonym(java.lang.String entityName, java.lang.String geoId)
  {
    GeoEntity.searchByGeoId(geoId).addSynonym(entityName);
  }

  @Override
  protected String buildKey()
  {
    // TODO: Naifeh needs to define this key
    return this.getId();
  }

  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: " + this.getClassDisplayLabel();
    }
    else
    {
      return this.getClassDisplayLabel();
    }
  }
}
