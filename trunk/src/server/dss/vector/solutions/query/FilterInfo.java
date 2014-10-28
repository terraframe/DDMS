package dss.vector.solutions.query;

import com.runwaysdk.generation.loader.Reloadable;

public class FilterInfo implements Reloadable
{
  private String geoId;

  private String entityName;

  public FilterInfo(String geoId, String entityName)
  {
    this.geoId = geoId;
    this.entityName = entityName;
  }

  public String getEntityName()
  {
    return entityName;
  }

  public String getGeoId()
  {
    return geoId;
  }

}
