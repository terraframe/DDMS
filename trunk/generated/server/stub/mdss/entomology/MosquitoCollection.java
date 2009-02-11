package mdss.entomology;

import java.text.DateFormat;
import java.util.List;

import mdss.test.Terrain;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

public class MosquitoCollection extends MosquitoCollectionBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234285245712L;
  
  public MosquitoCollection()
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
  protected String buildKey()
  {
    //TODO The date format needs to be localizable
    DateFormat format = DateFormat.getDateInstance();
    return format.format(this.getDateCollected()) + " - " + this.getGeoEntity().getGeoId();
  }

  @Override
  public void validateGeoEntity()
  {
    super.validateGeoEntity();
    
    List<Terrain> list = this.getGeoEntity().getTerrain();
    
    if(list.size() != 0)
    {
      Terrain terrain = list.get(0);
      
      if(!(terrain.equals(Terrain.NON_SENTINEL_SITE) || terrain.equals(Terrain.SENTINEL_SITE)))
      {
        //TODO Write the localizable template for this exception
        String msg = "The geoEntity of a mosquito collection must be a (non)sentinel site";
        throw new InvalidMosquitoCollectionGeoEntityException(msg);
      }
    }
  }
  
  public static mdss.entomology.MosquitoCollection searchByGeoEntityAndDate(mdss.test.GeoEntity geoEntity, java.util.Date collectionDate)
  {
    MosquitoCollection collection = null;
    
    QueryFactory factory = new QueryFactory();
    MosquitoCollectionQuery query = new MosquitoCollectionQuery(factory);    

    query.getGeoEntity().getId().EQ(geoEntity.getId());
    query.getDateCollected().EQ(collectionDate);
    
    OIterator<? extends MosquitoCollection> iterator = query.getIterator();
    
    if(iterator.hasNext())
    {
      collection = iterator.next();
    }
    
    iterator.close();
    
    return collection;
  }  
}
