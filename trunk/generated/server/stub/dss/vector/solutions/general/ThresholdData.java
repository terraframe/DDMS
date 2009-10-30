package dss.vector.solutions.general;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.terraframe.mojo.business.rbac.Authenticate;
import com.terraframe.mojo.dataaccess.ValueObject;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.AND;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.ValueQuery;
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
   * @return A WeeklyThreshold for a given GeoEntity on the give Date. If
   *         thresholds are not defined then null is returned.
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
      EpiWeek week = EpiWeek.getEpiWeek(date);
      
      if (week != null)
      {
        ThresholdData thresholdData = list.get(0);

        WeeklyThreshold weekly = week.getThresholdsRel(thresholdData);

        if (weekly == null)
        {
          weekly = new WeeklyThreshold(thresholdData, week);
        }

        return weekly;
      }
    }

    return null;
  }

  /**
   * @param entity
   *          GeoEntity
   * @param date
   *          Date
   * 
   * @return A calulated value for outbreak
   */
  public static Integer getCalculatedValue(GeoEntity entity, EpiWeek week, String attribute)
  {

    QueryFactory queryFactory = new QueryFactory();
    ValueQuery valueQuery = new ValueQuery(queryFactory);
    Integer sum = 0;

    valueQuery.SELECT(valueQuery.aSQLInteger("summed_value", "summed_value"));

    String sql = "(WITH RECURSIVE geohierarchy_flags AS(";
    sql += " SELECT  (t1.packagename || '.' || t1.typename) AS parent_type,";
    sql += "  g1.political AS parent_political,";
    sql += "  g1.spraytargetallowed AS parent_spraytargetallowed,";
    sql += "  g1.populationallowed AS parent_populationallowed,";
    sql += "  (t2.packagename || '.' || t2.typename) AS child_type,";
    sql += "  g2.political AS child_political,";
    sql += "  g2.spraytargetallowed AS child_spraytargetallowed,";
    sql += "  g2.populationallowed AS child_populationallowed";
    sql += " FROM allowedin ,";
    sql += "  geohierarchy g1,";
    sql += "  geohierarchy g2,";
    sql += "  md_type t1 ,";
    sql += "  md_type t2 ";
    sql += " WHERE  allowedin.parent_id = g1.id";
    sql += "  AND allowedin.child_id = g2.id";
    sql += "  AND t1.id = g1.geoentityclass";
    sql += "  AND t2.id = g2.geoentityclass";
    sql += " )";
    sql += " , recursive_rollup AS (";
    sql += " SELECT child_id, parent_id, 0 AS depth , geoentity.type , ";
    sql += " COALESCE((";
    // this is the table with the sumable value
    sql += " SELECT " + attribute + " FROM weeklythreshold, thresholddata ";
    sql += "    WHERE weeklythreshold.child_id = '" + week.getId() + "'";
    sql += "    AND thresholddata.geoentity = locatedin.child_id";
    sql += "    AND weeklythreshold.parent_id = thresholddata.id";
    sql += "  ),0)as sumvalue";
    sql += "  FROM locatedin, geohierarchy_flags, geoentity";
    // --zambia";
    sql += " WHERE parent_id = '" + entity.getId() + "'";
    sql += "  AND locatedin.child_id = geoentity.id";
    sql += "  AND geoentity.type = geohierarchy_flags.parent_type";
    sql += "  AND geohierarchy_flags.parent_political = 1";
    sql += "  AND geohierarchy_flags.parent_populationallowed = 1 ";
    sql += " UNION";
    sql += " SELECT b.child_id, b.parent_id, a.depth+1 , geoentity.type , ";
    // this is how we sum";
    sql += " a.sumvalue +  COALESCE((";
    sql += "  SELECT " + attribute + " FROM weeklythreshold, thresholddata, epiweek  ";
    sql += "    WHERE weeklythreshold.child_id = '" + week.getId() + "'";
    sql += "    AND thresholddata.geoentity = b.child_id";
    sql += "    AND weeklythreshold.parent_id = thresholddata.id";
    sql += " ),0)";
    sql += " FROM recursive_rollup a, locatedin b , geohierarchy_flags, geoentity ";
    sql += " WHERE a.child_id = b.parent_id";
    sql += " AND b.child_id = geoentity.id";
    sql += " AND geoentity.type = geohierarchy_flags.parent_type";
    // --must have political and populated allowed set to be counted";
    sql += " AND geohierarchy_flags.parent_political = 1";
    sql += " AND geohierarchy_flags.parent_populationallowed = 1";
    // --this will stop the recursion as soon as sumvalue is not null";
    sql += " AND a.sumvalue = 0";
    sql += " )";
    sql += " select sum(sumvalue) as summed_value from recursive_rollup ";
    sql += " )";
    valueQuery.FROM(sql, "rr");

    System.out.println(valueQuery.getSQL());

    List<ValueObject> valueObjectList = valueQuery.getIterator().getAll();

    for (ValueObject valueObject : valueObjectList)
    {
      String value = valueObject.getValue("summed_value");
      if (!value.equals(""))
      {
        sum += Integer.parseInt(value);
      }
    }

    if (sum == 0)
    {
      return null;
    }

    return sum;
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

      EpiWeek week = EpiWeek.getEpiWeek(date);

      if(notification == null)
      {
        notification = ThresholdData.getCalculatedValue(entity, week, WeeklyThreshold.NOTIFICATION);
      }
      
      if(identification == null)
      {
        identification = ThresholdData.getCalculatedValue(entity, week, WeeklyThreshold.IDENTIFICATION);
      }

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

        threshold.updateLastNotification();
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

        threshold.updateLastIdentification();
      }
    }

  }

}
