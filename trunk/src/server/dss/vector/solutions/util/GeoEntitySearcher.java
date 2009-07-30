package dss.vector.solutions.util;

import java.util.Iterator;
import java.util.List;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.AmbigiousGeoEntityException;
import dss.vector.solutions.UnknownGeoEntityException;
import dss.vector.solutions.geo.generated.GeoEntity;

public class GeoEntitySearcher implements Reloadable
{
  public GeoEntity                 current;

  public List<SearchableHierarchy> list;

  public GeoEntitySearcher(List<SearchableHierarchy> list)
  {
    this.list = list;
    this.current = null;
  }

  public GeoEntity getGeoEntity(List<String> geoEntityNames)
  {
    Iterator<String> it = geoEntityNames.iterator();

    for (SearchableHierarchy hierarchy : list)
    {
      if (it.hasNext())
      {
        String entityName = it.next();

        if (entityName != null && !entityName.equals(""))
        {
          List<GeoEntity> results = hierarchy.searchGeoEntity(entityName, current);

          if (results.size() == 0)
          {
            String msg = "Unknown Geo Entity [" + entityName + "]";
            UnknownGeoEntityException e =  new UnknownGeoEntityException(msg);
            e.setEntityName(entityName);
            e.apply();
            throw e;
          }
          else if (results.size() != 1)
          {
            String msg = "Geo Entity ending with [" + entityName + "] is ambiguous (It has more than one possible solution)";
            AmbigiousGeoEntityException e = new AmbigiousGeoEntityException(msg);
            e.setEntityName(entityName);
            e.apply();
            throw e;
          }

          current = results.get(0);
        }
      }
    }

    return current;
  }
}
