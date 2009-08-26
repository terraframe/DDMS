package dss.vector.solutions.irs;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

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

}
