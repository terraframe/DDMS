package dss.vector.solutions.irs;

import com.terraframe.mojo.dataaccess.ValueObject;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.SelectableSQLInteger;
import com.terraframe.mojo.query.ValueQuery;

import dss.vector.solutions.general.MalariaSeason;
import dss.vector.solutions.geo.generated.GeoEntity;

public class GeoTarget extends GeoTargetBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240267420514L;

  public GeoTarget()
  {
    super();
  }
  
  @Override
  protected String buildKey()
  {
    if(this.getGeoEntity() != null && this.getSeason() != null)
    {
      return this.getGeoEntity().getGeoId() + "." + this.getSeason().getKey();
    }
    return this.getId();
  }

  public GeoTargetView getView()
  {    
    GeoTargetView view = new GeoTargetView();
    
    view.populateView(this);
    
    return view;
  }

  public static GeoTargetView getView(String id)
  {
    return GeoTarget.get(id).getView();
  }

  public static GeoTargetView find(GeoEntity resource)
  {
    GeoTargetQuery query = new GeoTargetQuery(new QueryFactory());
    query.WHERE(query.getGeoEntity().EQ(resource));

    OIterator<? extends GeoTarget> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next().getView();
      }
      else
      {
        GeoTargetView view = new GeoTargetView();
        view.setGeoEntity(resource);

        return view;
      }
    }
    finally
    {
      it.close();
    }
  }

  public static GeoTargetView findByGeoEntityIdAndSeason(String resource, MalariaSeason season)
  {
    GeoTargetQuery query = new GeoTargetQuery(new QueryFactory());
    query.WHERE(query.getGeoEntity().getId().EQ(resource));
    query.AND(query.getSeason().EQ(season));

    OIterator<? extends GeoTarget> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next().getView();
      }
      else
      {
        GeoEntity ge = GeoEntity.get(resource);

        GeoTargetView view = new GeoTargetView();
        view.setGeoEntity(ge);
        view.setEntityName(ge);
        view.setSeason(season);
        
        return view;
      }
    }
    finally
    {
      it.close();
    }
  }
  
 
  
  public static Integer[] getCalculatedTargets(String geoid, String malariaSeasonId)
  {
    QueryFactory queryFactory = new QueryFactory();
    ValueQuery valueQuery = new ValueQuery(queryFactory);
    SelectableSQLInteger[] selectables = new SelectableSQLInteger[53];
    String sql = "(SELECT " ;
    
    for(int i=0; i<selectables.length; i++)
    {
      selectables[i] = valueQuery.aSQLInteger("target_" + i,"t" + i, "target_" + i);
      sql += "SUM(target_" + i + ") AS t" + i + ", ";
    }
    
    valueQuery.SELECT(selectables);
    
    sql = sql.substring(0, sql.length()-2);
    sql += " FROM geotarget, allpaths ";
    sql += " WHERE season = '" + malariaSeasonId + "'";
    sql += " AND geotarget.geoentity != '" + geoid + "'";
    sql += " AND allpaths.parentgeoentity = '" + geoid + "'";
    sql += " AND geotarget.geoentity = allpaths.childgeoentity)";

    valueQuery.FROM(sql, "caluations");
    
   
    System.out.println(valueQuery.getSQL());
    
    
    Integer[] results = new Integer[53];
    
    
    
    for(int i=0; i<results.length; i++)
    { 
      for (ValueObject v : valueQuery.getIterator())
      {
        String value = v.getValue("target_"+i);
        if(value != null && value.length()>0)
        {
          results[i] = Integer.parseInt(value);
        }
      }
    }
    return results;
  }

}
