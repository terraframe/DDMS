package dss.vector.solutions.kaleidoscope.geo;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.system.gis.metadata.MdAttributeMultiPolygon;
import com.runwaysdk.system.gis.metadata.MdAttributePoint;
import com.runwaysdk.system.metadata.MdAttribute;

import dss.vector.solutions.geo.generated.GeoEntity;

public class GeoNodeEntity extends GeoNodeEntityBase implements Reloadable
{
  private static final long serialVersionUID = 517026585;

  public GeoNodeEntity()
  {
    super();
  }

  @Override
  public MdAttribute getIdentifierAttribute()
  {
    return MdAttribute.get(GeoEntity.getGeoIdMd().getId());
  }

  @Override
  public MdAttribute getDisplayLabelAttribute()
  {
    return MdAttribute.get(GeoEntity.getEntityLabelMd().getId());
  }

  @Override
  public MdAttribute getGeometryAttribute()
  {
    return null;
  }

  @Override
  public MdAttributeMultiPolygon getMultiPolygonAttribute()
  {
    return MdAttributeMultiPolygon.get(GeoEntity.getGeoMultiPolygonMd().getId());
  }

  @Override
  public MdAttributePoint getPointAttribute()
  {
    return MdAttributePoint.get(GeoEntity.getGeoPointMd().getId());
  }

  public static List<GeoNodeEntity> getByEntityAttribute(String mdAttributeId)
  {
    GeoNodeEntityQuery query = new GeoNodeEntityQuery(new QueryFactory());
    query.WHERE(query.getGeoEntityAttribute().EQ(mdAttributeId));

    List<? extends GeoNodeEntity> list = query.getIterator().getAll();

    return new LinkedList<GeoNodeEntity>(list);
  }

}
