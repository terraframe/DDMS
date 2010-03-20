package dss.vector.solutions.general;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.runwaysdk.business.generation.GenerationUtil;
import com.runwaysdk.business.rbac.Authenticate;
import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.AND;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Session;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.util.MDSSProperties;

public class ThresholdData extends ThresholdDataBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1256068147836L;

  public ThresholdData()
  {
    super();
  }

  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: " + this.getClassDisplayLabel();
    }
    else if (this.getGeoEntity() != null && this.getSeason() != null)
    {
      return this.buildKey();
    }

    return super.toString();
  }

  @Override
  protected String buildKey()
  {
    return this.getGeoEntity().getKey() + " - " + this.getSeason().getKey();
  }

  public ThresholdDataView getView(boolean thresholdType)
  {
    ThresholdDataView view = new ThresholdDataView();
    view.setThresholdType(thresholdType);
    view.populateView(this);

    return view;
  }

  public ThresholdDataView getView()
  {
    return this.getView(true);
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
    ThresholdData data = ThresholdData.getThresholdData(entity, date);

    if (data != null)
    {
      EpiWeek week = EpiWeek.getEpiWeek(date);

      if (week != null)
      {
        return week.getThresholdsRel(data);
      }
    }

    return null;
  }

  public static ThresholdData getThresholdData(GeoEntity entity, Date date)
  {
    QueryFactory factory = new QueryFactory();

    MalariaSeasonQuery season = MalariaSeason.getSeasonQueryByDate(date, factory);

    ThresholdDataQuery threshold = new ThresholdDataQuery(factory);
    threshold.WHERE(AND.get(threshold.getGeoEntity().EQ(entity), threshold.getSeason().EQ(season)));

    List<? extends ThresholdData> list = threshold.getIterator().getAll();

    if (list.size() > 0)
    {
      return list.get(0);
    }

    return null;
  }

  public static ThresholdData getThresholdOrCreate(GeoEntity entity, Date date)
  {
    ThresholdData data = ThresholdData.getThresholdData(entity, date);

    if (data == null)
    {
      MalariaSeason season = MalariaSeason.getSeasonByDate(date);

      data = new ThresholdData();
      data.setGeoEntity(entity);
      data.setSeason(season);
      data.apply();
    }

    return data;
  }

  public static ThresholdData getThresholdData(GeoEntity entity, MalariaSeason season)
  {
    QueryFactory factory = new QueryFactory();

    ThresholdDataQuery threshold = new ThresholdDataQuery(factory);
    threshold.WHERE(AND.get(threshold.getGeoEntity().EQ(entity), threshold.getSeason().EQ(season)));

    List<? extends ThresholdData> list = threshold.getIterator().getAll();

    if (list.size() > 0)
    {
      return list.get(0);
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
    if (entity.getType().equals(Earth.CLASS))
    {
      return null;
    }

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
    sql += "    AND geohierarchy_flags.parent_populationallowed = 1";
    sql += "  ),0)as sumvalue";
    sql += "  FROM locatedin, geohierarchy_flags, geoentity";
    // --zambia";
    sql += " WHERE parent_id = '" + entity.getId() + "'";
    sql += "  AND locatedin.child_id = geoentity.id";
    sql += "  AND geoentity.type = geohierarchy_flags.parent_type";
    sql += "  AND geohierarchy_flags.parent_political = 1";
    sql += " UNION";
    sql += " SELECT b.child_id, b.parent_id, a.depth+1 , geoentity.type , ";
    // this is how we sum.  We only sum if population is allowed";
    sql += " a.sumvalue +  COALESCE((";
    sql += "  SELECT " + attribute + " FROM weeklythreshold, thresholddata  ";
    sql += "    WHERE weeklythreshold.child_id = '" + week.getId() + "'";
    sql += "    AND thresholddata.geoentity = b.child_id";
    sql += "    AND weeklythreshold.parent_id = thresholddata.id";
    sql += "    AND geohierarchy_flags.parent_populationallowed = 1";
    sql += " ),0)";
    sql += " FROM recursive_rollup a, locatedin b , geohierarchy_flags, geoentity ";
    sql += " WHERE a.child_id = b.parent_id";
    sql += " AND b.child_id = geoentity.id";
    sql += " AND geoentity.type = geohierarchy_flags.parent_type";
    // --must have political and populated allowed set to be counted";
    // --however recursion does not stop when political = true and
    // --population allowed = false";
    sql += " AND geohierarchy_flags.parent_political = 1";
    // --this will stop the recursion as soon as sumvalue is not null";
    sql += " AND a.sumvalue = 0";
    sql += " )";
    sql += " select sum(sumvalue) as summed_value from recursive_rollup ";
    sql += " )";
    valueQuery.FROM(sql, "rr");

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
    if (entity.getType().equals(Earth.CLASS))
    {
      return;
    }

    ThresholdData.checkThreshold(WeeklyThreshold.IDENTIFICATION, date, entity, count, false);
    ThresholdData.checkThreshold(WeeklyThreshold.NOTIFICATION, date, entity, count, false);
  }
  
  @Transaction
  @Authenticate
  public static void checkFacilityThresholdViolation(Date date, GeoEntity entity, long count)
  {
    if (entity.getType().equals(Earth.CLASS))
    {
      return;
    }

    ThresholdData.checkThreshold(WeeklyThreshold.FACILITYIDENTIFICATION, date, entity, count, true);
    ThresholdData.checkThreshold(WeeklyThreshold.FACILITYNOTIFICATION, date, entity, count, true);    
  }
  
  private static void checkThreshold(String accessor, Date date, GeoEntity entity, long cases, boolean facility)
  {    
    WeeklyThreshold threshold = ThresholdData.getThresholds(entity, date);
    EpiWeek week = EpiWeek.getEpiWeek(date);    
    
    Integer count = null;

    if (threshold != null)
    {
      count = threshold.getThreshold(accessor);
    }

    if (count == null && !facility)
    {
      count = ThresholdData.getCalculatedValue(entity, week, accessor);
    }

    if (count != null && cases >= count)
    {
      if (threshold == null)
      {
        ThresholdData data = ThresholdData.getThresholdOrCreate(entity, date);
        threshold = data.addEpiWeeks(week);
        threshold.apply();
      }

      boolean performedAlert = threshold.getPerformedAlert(accessor);
      
      if (!performedAlert)
      {
        String alertKey = "PoliticalOutbreak" + GenerationUtil.upperFirstCharacter(accessor);
        
        if(facility)
        {
          alertKey = GenerationUtil.upperFirstCharacter(accessor).replace("Facility", "FacilityOutbreak");
        }
        
        // Perform the alert
        performAlert(alertKey, entity, count, cases);

        threshold.reachedThreshold(accessor, count);
      }
    }
  }
  
  private static void performAlert(String alertKey, GeoEntity entity, int threshold, long count)
  {
    SystemAlert systemAlert = SystemAlert.getByKey(alertKey);
    String alertType = entity.getOutbreakAlert();
    String thresholdType = MDSSProperties.getString(alertKey);
    String label = entity.getLabel();
    boolean emailSent = false;

    if (systemAlert.getIsEmailActive())
    {
      HashMap<String, Object> data = new HashMap<String, Object>();
      data.put("alertType", alertType);
      data.put("thresholdType", thresholdType);
      data.put("entityLabel", label);
      data.put("threshold", threshold);
      data.put("totalCases", count);
      emailSent = systemAlert.sendEmail(data);
    }

    if (systemAlert.getIsOnscreenActive())
    {
      OutbreakAlert alert = new OutbreakAlert();
      alert.setAlertType(alertType);
      alert.setThresholdType(thresholdType);
      alert.setEntityLabel(label);
      alert.setThreshold(threshold);
      alert.setTotalCases(count);
      if (systemAlert.getIsEmailActive() & !emailSent)
      {
        alert.setEmailFailure(true);
      }
      alert.apply();

      alert.throwIt();
    }
  }  
}
