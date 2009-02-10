package mdss.test;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

public class GeoEntity extends GeoEntityBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234288139462L;
  
  public GeoEntity()
  {
    super();
  }
  
  public static GeoEntity searchByGeoId(String geoId)
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
      throw new RuntimeException(msg);
    }
    
    return geoEntity;
  }
  
}
