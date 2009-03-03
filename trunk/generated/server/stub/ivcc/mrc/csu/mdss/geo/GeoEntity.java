package ivcc.mrc.csu.mdss.geo;

import ivcc.mrc.csu.mdss.geo.GeoEntityBase;
import ivcc.mrc.csu.mdss.geo.GeoEntityQuery;

import com.terraframe.mojo.dataaccess.InvalidIdException;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

public class GeoEntity extends GeoEntityBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234288139462L;
  
  public GeoEntity()
  {
    super();
  }
  
  public static ivcc.mrc.csu.mdss.geo.GeoEntity searchByGeoId(java.lang.String geoId)
  {
    GeoEntity geoEntity = null;
    QueryFactory factory = new QueryFactory();
    GeoEntityQuery query = new GeoEntityQuery(factory);    

    query.getGeoId().EQ(geoId);
    
    OIterator<? extends GeoEntity> iterator = query.getIterator();
    
    if(iterator.hasNext())
    {
      geoEntity = iterator.next();      
    }
    
    iterator.close();
    
    if(geoEntity == null)
    {
      String msg = "A GeoEntity with the geoId [" + geoId + "] does not exist";
      throw new InvalidIdException(msg, geoId);
    }
    
    return geoEntity;
  }
  
}
