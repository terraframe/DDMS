package dss.vector.solutions.general;

import java.util.Date;
import java.util.List;

import com.terraframe.mojo.ApplicationException;
import com.terraframe.mojo.query.AND;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.session.Session;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;

public class ThresholdData extends ThresholdDataBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1256068147836L;

  public ThresholdData()
  {
    super();
  }

  @Override
  protected String buildKey()
  {
    return this.getGeoEntity().getKey() + " - " + this.getSeason().getKey();
  }

  public ThresholdDataView getView()
  {
    ThresholdDataView view = new ThresholdDataView();
    view.populateView(this);

    return view;
  }

  @Override
  public ThresholdDataView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  @Override
  public ThresholdDataView lockView()
  {
    this.lock();

    return this.getView();
  }

  @Override
  public void apply()
  {
    this.validateGeoEntity();

    super.apply();
  }

  @Override
  public void validateGeoEntity()
  {
    GeoEntity entity = this.getGeoEntity();

    if (entity != null)
    {
      GeoHierarchy geoHierarchy = GeoHierarchy.getGeoHierarchyFromType(entity.getType());

      if (!geoHierarchy.getPopulationAllowed())
      {
        String universal = entity.getMdClass().getDisplayLabel(Session.getCurrentLocale());
        String geoEntityName = entity.getEntityName();

        String label = geoEntityName + " (" + universal + ")";

        String msg = "The Geo Entity [" + label + "] does not allow population data.";

        GeoEntityPopulationProblem p = new GeoEntityPopulationProblem(msg);
        p.setEntityLabel(label);
        p.apply();
        p.throwIt();
      }
    }
  }

  private int getIndex(Date date)
  {
    EpiDate week = this.getSeason().getEpiWeek(date);

    int index = ( week.getPeriod() % week.getNumberOfEpiWeeks() );
    return index;
  }

  public void setThresholds(Date date, Integer t1, Integer t2)
  {
    int index = this.getIndex(date);

    this.setThresholds(index, t1, t2);
  }

  private void setThresholds(int index, Integer t1, Integer t2)
  {
    try
    {
      this.getClass().getMethod("setOutbreak_" + index, Integer.class).invoke(this, t1);
      this.getClass().getMethod("setIdentification_" + index, Integer.class).invoke(this, t2);
    }
    catch (Exception e)
    {
      throw new ApplicationException(e);
    }
  }

  public Integer[] getThresholds(Date date)
  {
    int index = this.getIndex(date);

    return this.getThresholds(index);
  }

  private Integer[] getThresholds(int index)
  {
    try
    {
      Integer t1 = (Integer) this.getClass().getMethod("getOutbreak_" + index).invoke(this);
      Integer t2 = (Integer) this.getClass().getMethod("getIdentification_" + index).invoke(this);

      return new Integer[] { t1, t2 };
    }
    catch (Exception e)
    {
      throw new ApplicationException(e);
    }
  }

  /**
   * @param entity
   *          GeoEntity
   * @param date
   *          Date
   * 
   * @return An array of the Thresholds [T1, T2] for a given GeoEntity on the
   *         give Date. If thresholds are not defined then null is returned.
   */
  public static Integer[] getThresholds(GeoEntity entity, Date date)
  {
    QueryFactory factory = new QueryFactory();

    MalariaSeasonQuery season = MalariaSeason.getSeasonQueryByDate(date, factory);

    ThresholdDataQuery query = new ThresholdDataQuery(factory);
    query.WHERE(AND.get(query.getGeoEntity().EQ(entity), query.getSeason().EQ(season)));

    List<? extends ThresholdData> list = query.getIterator().getAll();

    if (list.size() > 0)
    {
      return list.get(0).getThresholds(date);
    }

    return null;
  }
}
