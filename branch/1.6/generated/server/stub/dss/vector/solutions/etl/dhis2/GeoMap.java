package dss.vector.solutions.etl.dhis2;

public class GeoMap extends GeoMapBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 891743714;
  
  public GeoMap()
  {
    super();
  }
  
  @Override
  public String buildKey()
  {
    return this.getGeoEntity().getId();
  }
}
