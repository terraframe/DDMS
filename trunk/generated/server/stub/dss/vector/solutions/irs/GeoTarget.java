package dss.vector.solutions.irs;

import java.lang.reflect.Method;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.geo.generated.GeoEntity;

public class GeoTarget extends GeoTargetBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240267420514L;

  public GeoTarget()
  {
    super();
  }

  public GeoTargetView getView()
  {
    GeoTargetView view = new GeoTargetView();
    view.setGeoEntity(this.getGeoEntity());
    view.setTargetYear(this.getTargetYear());
    view.setTargetId(this.getId());

    for (int i = 0; i < 53; i++)
    {
      String setterName = "setTarget_" + i;
      String getterName = "getTarget_" + i;

      try
      {
        Method setter = GeoTargetView.class.getMethod(setterName, Integer.class);
        Method getter = GeoTarget.class.getMethod(getterName);

        setter.invoke(view, (Integer) getter.invoke(this));
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }

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

}
