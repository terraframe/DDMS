package dss.vector.solutions.irs;

import java.lang.reflect.Method;

import com.terraframe.mojo.ApplicationException;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.session.Session;

import dss.vector.solutions.general.MalariaSeason;
import dss.vector.solutions.geo.generated.GeoEntity;

public class GeoTargetView extends GeoTargetViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240267397803L;

  public GeoTargetView()
  {
    super();
  }

  @Override
  public void apply()
  {
    GeoTarget target = new GeoTarget();

    if (hasConcrete())
    {
      target = GeoTarget.lock(this.getTargetId());
    }

    target.setGeoEntity(this.getGeoEntity());
    target.setSeason(this.getSeason());

    for (int i = 0; i < 53; i++)
    {
      String setterName = "setTarget_" + i;
      String getterName = "getTarget_" + i;

      try
      {
        Method setter = GeoTarget.class.getMethod(setterName, Integer.class);
        Method getter = GeoTargetView.class.getMethod(getterName);

        setter.invoke(target, (Integer) getter.invoke(this));
      }
      catch (Exception e)
      {
        throw new ApplicationException(e);
      }
    }

    target.apply();

    populateView(target);
  }

  private boolean hasConcrete()
  {
    return this.getTargetId() != null && !this.getTargetId().equals("");
  }
  
  public void setEntityName(GeoEntity entity)
  {
    String universal = entity.getMdClass().getDisplayLabel(Session.getCurrentLocale());
    String geoEntityName = entity.getEntityName();
    
    this.setEntityName(geoEntityName + " (" + universal + ")");    
  }
  
  public void populateView(GeoTarget target)
  {
    this.setTargetId(target.getId());    
    this.setGeoEntity(target.getGeoEntity());
    this.setEntityName(target.getGeoEntity());
    this.setSeason(target.getSeason());

    for (int i = 0; i < 53; i++)
    {
      String setterName = "setTarget_" + i;
      String getterName = "getTarget_" + i;

      try
      {
        Method setter = GeoTargetView.class.getMethod(setterName, Integer.class);
        Method getter = GeoTarget.class.getMethod(getterName);

        setter.invoke(this, (Integer) getter.invoke(target));
      }
      catch (Exception e)
      {
        throw new ApplicationException(e);
      }
    }
  }

  @Override
  public void deleteConcrete()
  {
    if (hasConcrete())
    {
      GeoTarget.get(this.getTargetId()).delete();
    }
  }

  @Transaction
  public GeoTargetView unlock()
  {
    if (this.getTargetId() == null || this.getTargetId().equals(""))
    {
      return this;
    }

    return GeoTarget.unlock(this.getTargetId()).getView();
  }

  @Transaction
  public GeoTargetView lock()
  {
    if (this.getTargetId() == null || this.getTargetId().equals(""))
    {
      return this;
    }

    return GeoTarget.lock(this.getTargetId()).getView();
  }

  @Transaction
  public static GeoTargetView[] lockAll(GeoTargetView[] views)
  {
    GeoTargetView[] locked = new GeoTargetView[views.length];

    for (int i = 0; i < views.length; i++)
    {
      locked[i] = views[i].lock();
    }

    return locked;
  }

  @Transaction
  public static GeoTargetView[] unlockAll(GeoTargetView[] views)
  {
    GeoTargetView[] unlocked = new GeoTargetView[views.length];

    for (int i = 0; i < views.length; i++)
    {
      unlocked[i] = views[i].unlock();
    }

    return unlocked;
  }

  @Transaction
  public static GeoTargetView[] applyAll(GeoTargetView[] views)
  {
    for (GeoTargetView view : views)
    {
      view.apply();
    }

    return views;
  }

  @Transaction
  public static GeoTargetView[] saveAll(GeoTargetView[] array)
  {
      GeoTargetView.lockAll(array);
      GeoTargetView.applyAll(array);

    return array;
  }

  @Transaction
  public static GeoTargetView[] getGeoTargets(String[] geoEntityIds, MalariaSeason season)
  {
    GeoTargetView[] views = new GeoTargetView[geoEntityIds.length];

    for(int i = 0; i < geoEntityIds.length; i++)
    {
      views[i] = GeoTarget.findByGeoEntityIdAndSeason(geoEntityIds[i],season);
    }

    return views;
  }

}
