package dss.vector.solutions.geo;

import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;

public class GeoSynonym extends GeoSynonymBase implements com.runwaysdk.generation.loader.Reloadable
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
  
  public static GeoSynonym getByNameAndGeo(String geoId, String name)
  {
    QueryFactory qf = new QueryFactory();
    
    GeoEntityQuery geq = new GeoEntityQuery(qf);
    
    GeoSynonymQuery gsq = new GeoSynonymQuery(qf);
    gsq.WHERE(gsq.getEntityName().EQ(name));
    gsq.AND(gsq.geoEntity(geq));
    
    geq.WHERE(geq.getGeoId().EQ(geoId));
    
    OIterator<? extends GeoSynonym> it = gsq.getIterator();
    if (!it.hasNext())
    {
      return null;
    }
    else
    {
      return it.next();
    }
  }

  @Override
  protected String buildKey()
  {
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
