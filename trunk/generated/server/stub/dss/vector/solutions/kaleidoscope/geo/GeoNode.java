package dss.vector.solutions.kaleidoscope.geo;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.gis.metadata.MdAttributeMultiPolygon;
import com.runwaysdk.system.gis.metadata.MdAttributePoint;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdAttributeReference;

public abstract class GeoNode extends GeoNodeBase implements Reloadable
{
  private static final long serialVersionUID = 1975242459;

  public GeoNode()
  {
    super();
  }

  @Override
  protected String buildKey()
  {
    MdAttributeReference attribute = this.getGeoEntityAttribute();

    if (attribute != null)
    {
      return attribute.getKey();
    }

    return this.getId();
  }

  public abstract MdAttribute getIdentifierAttribute();

  public abstract MdAttribute getDisplayLabelAttribute();

  public abstract MdAttribute getGeometryAttribute();

  public abstract MdAttributeMultiPolygon getMultiPolygonAttribute();

  public abstract MdAttributePoint getPointAttribute();
}
