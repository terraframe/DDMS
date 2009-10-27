package dss.vector.solutions.general;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.terraframe.mojo.business.rbac.Authenticate;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
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

  /**
   * @param entity
   *          GeoEntity
   * @param date
   *          Date
   * 
   * @return An array of the Thresholds [T1, T2] for a given GeoEntity on the
   *         give Date. If thresholds are not defined then null is returned.
   */
  public static WeeklyThreshold getThresholds(GeoEntity entity, Date date)
  {
    QueryFactory factory = new QueryFactory();

    MalariaSeasonQuery season = MalariaSeason.getSeasonQueryByDate(date, factory);

    ThresholdDataQuery threshold = new ThresholdDataQuery(factory);
    threshold.WHERE(AND.get(threshold.getGeoEntity().EQ(entity), threshold.getSeason().EQ(season)));

    List<? extends ThresholdData> list = threshold.getIterator().getAll();

    if (list.size() > 0)
    {
      EpiDate epiDate = EpiDate.getEpiWeek(date);
      EpiWeek week = EpiWeek.getEpiWeek(epiDate.getPeriod(), epiDate.getEpiYear());

      if (week != null)
      {
        ThresholdData thresholdData = list.get(0);

        return week.getThresholdsRel(thresholdData);
      }
    }
    
    return null;
  }

  @Transaction
  @Authenticate
  public static void checkThresholdViolation(Date date, GeoEntity entity, long count)
  {
    WeeklyThreshold threshold = ThresholdData.getThresholds(entity, date);

    if (threshold != null)
    {
      Integer notification = threshold.getNotification();
      Integer identification = threshold.getIdentification();

      if (notification != null && count >= notification && !threshold.performedNotificationAlert())
      {
        String alertType = entity.getOutbreakAlert();
        String thresholdType = ResourceBundle.getBundle("MDSS").getString("Alert");
        String label = entity.getLabel();

        OutbreakAlert alert = new OutbreakAlert();
        alert.setAlertType(alertType);
        alert.setThresholdType(thresholdType);
        alert.setEntityLabel(label);
        alert.setThreshold(notification);
        alert.setTotalCases(count);
        alert.apply();

        alert.throwIt();

//        threshold.updateLastNotification();
      }

      if (identification != null && count >= identification && !threshold.performedIdentificationAlert())
      {
        String alertType = entity.getOutbreakAlert();
        String thresholdType = ResourceBundle.getBundle("MDSS").getString("Identification");
        String label = entity.getLabel();

        OutbreakAlert alert = new OutbreakAlert();
        alert.setAlertType(alertType);
        alert.setThresholdType(thresholdType);
        alert.setEntityLabel(label);
        alert.setThreshold(identification);
        alert.setTotalCases(count);
        alert.apply();

        alert.throwIt();

//        threshold.updateLastIdentification();
      }
    }
  }

}
