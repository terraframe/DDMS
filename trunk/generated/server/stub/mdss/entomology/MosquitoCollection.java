package mdss.entomology;

import java.util.List;

import mdss.test.Terrain;

public class MosquitoCollection extends MosquitoCollectionBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234285245712L;
  
  public MosquitoCollection()
  {
    super();
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
        String msg = "The geoEntity of a mosquito collection must be a sentinel or non sentinel site";
        throw new RuntimeException(msg);
      }
    }
  }
}
