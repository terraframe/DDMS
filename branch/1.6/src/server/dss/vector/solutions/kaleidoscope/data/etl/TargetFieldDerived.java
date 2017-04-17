package dss.vector.solutions.kaleidoscope.data.etl;

import com.runwaysdk.business.Transient;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.metadata.MdBusinessDAO;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ST_WITHIN;
import com.runwaysdk.system.metadata.MdAttribute;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Point;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.util.QueryUtil;

public class TargetFieldDerived extends TargetFieldCoordinate implements TargetFieldIF
{
  private GeoEntity       country;

  private GeoHierarchy    universal;

  private MdBusinessDAOIF mdBusiness;

  public GeoEntity getCountry()
  {
    return country;
  }

  public void setCountry(GeoEntity country)
  {
    this.country = country;
  }

  public GeoHierarchy getUniversal()
  {
    return universal;
  }

  public void setUniversal(GeoHierarchy universal)
  {
    this.universal = universal;
    this.mdBusiness = MdBusinessDAO.get(universal.getGeoEntityClassId());
  }

  @Override
  public FieldValue getValue(MdAttributeConcreteDAOIF mdAttribute, Transient source)
  {
    Coordinate coord = super.getCoordinate(mdAttribute, source);

    if (coord != null)
    {
      Point point = this.getGeometryFactory().createPoint(coord);

      GeoEntityQuery query = (GeoEntityQuery) QueryUtil.getQuery(mdBusiness, new QueryFactory());
      query.WHERE(new ST_WITHIN(point, query.getGeoMultiPolygon()));

      OIterator<? extends GeoEntity> it = query.getIterator();

      try
      {
        if (it.hasNext())
        {
          GeoEntity entity = it.next();

          return new FieldValue(entity.getId());
        }
      }
      finally
      {
        it.close();
      }
    }

    return new FieldValue(this.country.getId(), true);
  }

  @Override
  public void persist(TargetBinding binding)
  {
    MdAttribute latitudeAttribute = MdAttribute.getByKey(binding.getSourceView().definesType() + "." + this.getLatitudeSourceAttributeName());
    MdAttribute longitudeAttribute = MdAttribute.getByKey(binding.getSourceView().definesType() + "." + this.getLongitudeSourceAttributeName());
    MdAttribute targetAttribute = MdAttribute.getByKey(this.getKey());

    TargetFieldDerivedBinding field = new TargetFieldDerivedBinding();
    field.setTarget(binding);
    field.setTargetAttribute(targetAttribute);
    field.setLatitudeAttribute(latitudeAttribute);
    field.setLongitudeAttribute(longitudeAttribute);
    field.setGeoEntity(this.country);
    field.setUniversal(this.getUniversal());
    field.setColumnLabel(this.getLabel());
    field.apply();
  }
}
