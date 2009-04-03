package dss.vector.solutions.entomology;

import java.util.List;

import junit.framework.TestCase;

import com.terraframe.mojo.dataaccess.cache.DataNotFoundException;

import dss.vector.solutions.geo.generated.GeoEntity;

public abstract class GeoTest extends TestCase
{
  /**
   * Deletes all the given GeoEntities if they exist in the database.
   * 
   * @param geos
   */
  protected void deleteAll(List<GeoEntity> geos)
  {
    for(GeoEntity geo : geos)
    {
      if(exists(geo))
      {
        geo.delete();
      }
    }
  }
  
  /**
   * Checks if the given GeoEntity exits.
   * @param geo
   * @return
   */
  protected boolean exists(GeoEntity geo)
  {
    try
    {
      GeoEntity.get(geo.getId());
      
      return true;
    }
    catch(DataNotFoundException e)
    {
      return false;
    }
  }
  
  public String genGeoId()
  {
    String r = String.valueOf(Math.random());
    return r.substring(r.length()-6);
  }
  
}
