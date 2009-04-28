package dss.vector.solutions.irs;

import java.util.Date;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.geo.generated.GeoEntity;

public class SprayData extends SprayDataBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240853361391L;

  public SprayData()
  {
    super();
  }

  public static synchronized SprayData search(InsecticideBrand brand, GeoEntity geoEntity, Date date,
      SprayMethod... sprayMethods)
  {
    SprayDataQuery query = new SprayDataQuery(new QueryFactory());

    query.WHERE(query.getBrand().EQ(brand));
    query.AND(query.getGeoEntity().EQ(geoEntity));
    query.AND(query.getSprayDate().EQ(date));
    query.AND(query.getSprayMethod().containsAny(sprayMethods));

    OIterator<? extends SprayData> it = query.getIterator();

    try
    {
      if(it.hasNext())
      {
        return it.next();
      }

      return null;
    }
    finally
    {
      it.close();
    }
  }

  public static synchronized SprayData get(InsecticideBrand brand, GeoEntity geoEntity, Date date, SprayMethod... sprayMethods)
  {
    SprayData search = SprayData.search(brand, geoEntity, date, sprayMethods);

    if(search == null)
    {
      search = new SprayData();
      search.setBrand(brand);
      search.setGeoEntity(geoEntity);
      search.setSprayDate(date);

      for(SprayMethod method : sprayMethods)
      {
        search.addSprayMethod(method);
      }
    }

    return search;
  }
}
