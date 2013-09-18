package dss.vector.solutions.irs;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.runwaysdk.ConfigurationException;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.session.Session;

import dss.vector.solutions.general.MalariaSeason;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;

public class GeoTargetView extends GeoTargetViewBase implements com.runwaysdk.generation.loader.Reloadable
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
        throw new ConfigurationException(e);
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
    String geoEntityName = entity.getEntityLabel().getValue();

    this.setEntityLabel(geoEntityName + " (" + universal + ")");
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
        throw new ConfigurationException(e);
      }
    }
  }

  public int getTotal()
  {
    int total = 0;

    Integer[] targets = GeoTarget.getCalculatedTargets(this.getGeoEntityId(), this.getSeasonId());

    for (int i = 0; i < 53; i++)
    {
      String getterName = "getTarget_" + i;

      try
      {
        Method getter = GeoTargetView.class.getMethod(getterName);

        Integer weekTarget = (Integer) getter.invoke(this);

        /*
         * If this has a direct target use it, otherwise use the rolled up target
         */
        if (weekTarget != null)
        {
          total += weekTarget;
        }
        else if(targets[i] != null)
        {
          total += targets[i];
        }
      }
      catch (Exception e)
      {
        throw new ConfigurationException(e);
      }
    }

    return total;
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

    for (int i = 0; i < geoEntityIds.length; i++)
    {
      views[i] = GeoTarget.findByGeoEntityIdAndSeason(geoEntityIds[i], season);
    }

    return views;
  }

  @Override
  public Integer[] getCalculatedTargets()
  {
    return GeoTarget.getCalculatedTargets(this.getGeoEntity().getId(), this.getSeason().getId());
  }

  @Transaction
  public static GeoTargetView[] getGeoTargetViews(String geoId, MalariaSeason season)
  {
    GeoEntity entity = GeoEntity.searchByGeoId(geoId);

    validateSearchEntity(entity);

    List<String> geoEntityIds = new ArrayList<String>();

    // Get Immediate Spray Children returns all of the immediate children which
    // can have spray targets associated, and the individual target
    for (GeoEntity child : entity.getImmediateSprayChildren())
    {
      geoEntityIds.add(child.getId());
    }

    geoEntityIds.add(entity.getId());

    String[] array = geoEntityIds.toArray(new String[geoEntityIds.size()]);

    return GeoTargetView.getGeoTargets(array, season);
  }

  private static void validateSearchEntity(GeoEntity entity)
  {
    GeoHierarchy geoHierarchy = GeoHierarchy.getGeoHierarchyFromType(entity.getType());

    if (!geoHierarchy.getSprayTargetAllowed())
    {
      String label = entity.getLabel();

      String msg = "The Geo Entity [" + label + "] does not allow spray target values";

      NotASprayEntityException e = new NotASprayEntityException(msg);
      e.setEntityLabel(label);
      e.apply();

      throw e;
    }
  }

}
