package ivcc.mrc.csu.mdss.entomology;

import ivcc.mrc.csu.mdss.entomology.MosquitoCollectionPointBase;
import ivcc.mrc.csu.mdss.entomology.MosquitoCollectionPointQuery;
import ivcc.mrc.csu.mdss.geo.Terrain;

import java.util.List;


import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

public class MosquitoCollectionPoint extends MosquitoCollectionPointBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234288154522L;
  
  public MosquitoCollectionPoint()
  {
    super();
  }
  
  @Override
  public void apply()
  {
    validateGeoEntity();
    
    super.apply();
  }
  
  @Override
  public void validateGeoEntity()
  {
    super.validateGeoEntity();
    
    List<Terrain> list = this.getGeoEntity().getTerrain();
    
    if(list.size() != 0)
    {
      Terrain terrain = list.get(0);
      
      if(!(terrain.equals(Terrain.FIXED_TRAP) || terrain.equals(Terrain.PERMANENT_WATER_BODY)))
      {
        String msg = "The geoEntity of a mosquito collection must be a fixed trap or a permenent water body";
        
        InvalidMosquitoCollectionPointGeoEntityException e = new InvalidMosquitoCollectionPointGeoEntityException(msg);
        e.setGeoId(this.getGeoEntity().getGeoId());
        e.apply();
        
        throw e;
      }
    }
  }
  
  public static ivcc.mrc.csu.mdss.entomology.MosquitoCollectionPoint searchByGeoEntityAndDate(ivcc.mrc.csu.mdss.geo.GeoEntity geoEntity, java.util.Date collectionDate)
  {
    MosquitoCollectionPoint collection = null;
    
    QueryFactory factory = new QueryFactory();
    MosquitoCollectionPointQuery query = new MosquitoCollectionPointQuery(factory);    

    query.AND(query.getGeoEntity().getId().EQ(geoEntity.getId()));
    query.AND(query.getDateCollected().EQ(collectionDate));
    
    OIterator<? extends MosquitoCollectionPoint> iterator = query.getIterator();
    
    if(iterator.hasNext())
    {
      collection = iterator.next();
    }
    
    iterator.close();
    
    return collection;
  }  

}
