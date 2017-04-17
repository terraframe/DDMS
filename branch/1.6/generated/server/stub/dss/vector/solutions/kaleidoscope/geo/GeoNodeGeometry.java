package dss.vector.solutions.kaleidoscope.geo;

import com.runwaysdk.system.metadata.MdAttribute;

public class GeoNodeGeometry extends GeoNodeGeometryBase
{
  private static final long serialVersionUID = -693301817;

  public GeoNodeGeometry()
  {
    super();
  }

  @Override
  protected String buildKey()
  {
    MdAttribute attribute = this.getGeometryAttribute();

    if (attribute != null)
    {
      return attribute.getKey();
    }

    return this.getId();
  }
}
