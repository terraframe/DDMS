/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.general;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.runwaysdk.business.rbac.Authenticate;
import com.runwaysdk.constants.MdTypeInfo;
import com.runwaysdk.dataaccess.RelationshipDAOIF;
import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.AND;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.metadata.MdType;

import dss.vector.solutions.geo.AllowedIn;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.LocatedIn;
import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.util.MDSSProperties;
import dss.vector.solutions.util.QueryUtil;

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
        String geoEntityName = entity.getEntityLabel().getValue();

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
  public static Double getCalculatedValue(GeoEntity entity, EpiWeek week, String attribute)
  {
    if (entity.getType().equals(Earth.CLASS))
    {
      return null;
    }

    String geoHierarchyTable = MdEntityDAO.getMdEntityDAO(GeoHierarchy.CLASS).getTableName();
    String geoEntityClassCol = QueryUtil.getColumnName(GeoHierarchy.getGeoEntityClassMd());
    String politicalCol = QueryUtil.getColumnName(GeoHierarchy.getPoliticalMd());
    String sprayTargetAllowedCol = QueryUtil.getColumnName(GeoHierarchy.getSprayTargetAllowedMd());
    String populationAllowedCol = QueryUtil.getColumnName(GeoHierarchy.getPopulationAllowedMd());

    String mdTypeTable = MdEntityDAO.getMdEntityDAO(MdTypeInfo.CLASS).getTableName();
    String pckNameCol = QueryUtil.getColumnName(MdType.getPackageNameMd());
    String nameCol = QueryUtil.getColumnName(MdType.getTypeNameMd());

    String allowedInTable = MdEntityDAO.getMdEntityDAO(AllowedIn.CLASS).getTableName();

    String geoEntityTable = MdEntityDAO.getMdEntityDAO(GeoEntity.CLASS).getTableName();
    String typeCol = QueryUtil.getColumnName(GeoEntity.getTypeMd());

    String weeklyThresholdTable = MdEntityDAO.getMdEntityDAO(WeeklyThreshold.CLASS).getTableName();

    String malariaSeasonTable = MdEntityDAO.getMdEntityDAO(MalariaSeason.CLASS).getTableName();

    String thresholdDataTable = MdEntityDAO.getMdEntityDAO(ThresholdData.CLASS).getTableName();
    String geoEntityCol = QueryUtil.getColumnName(ThresholdData.getGeoEntityMd());

    String locatedInTable = MdEntityDAO.getMdEntityDAO(LocatedIn.CLASS).getTableName();

    String idCol = QueryUtil.getIdColumn();

    QueryFactory queryFactory = new QueryFactory();
    ValueQuery valueQuery = new ValueQuery(queryFactory);
    Double sum = 0d;

    valueQuery.SELECT(valueQuery.aSQLDouble("summed_value", "summed_value"));

    String sql = "(WITH RECURSIVE geohierarchy_flags AS(";
    sql += " SELECT  (t1." + pckNameCol + " || '.' || t1." + nameCol + ") AS parent_type,";
    sql += "  g1." + politicalCol + " AS parent_political,";
    sql += "  g1." + sprayTargetAllowedCol + " AS parent_spraytargetallowed,";
    sql += "  g1." + populationAllowedCol + " AS parent_populationallowed,";
    sql += "  (t2." + pckNameCol + " || '.' || t2." + nameCol + ") AS child_type,";
    sql += "  g2." + politicalCol + " AS child_political,";
    sql += "  g2." + sprayTargetAllowedCol + " AS child_spraytargetallowed,";
    sql += "  g2." + populationAllowedCol + " AS child_populationallowed";
    sql += " FROM " + allowedInTable + " ,";
    sql += "  " + geoHierarchyTable + " g1,";
    sql += "  " + geoHierarchyTable + " g2,";
    sql += "  " + mdTypeTable + " t1 ,";
    sql += "  " + mdTypeTable + " t2 ";
    sql += " WHERE  " + allowedInTable + "." + RelationshipDAOIF.PARENT_ID_COLUMN + " = g1.id";
    sql += "  AND " + allowedInTable + "." + RelationshipDAOIF.CHILD_ID_COLUMN + " = g2.id";
    sql += "  AND t1.id = g1." + geoEntityClassCol + "";
    sql += "  AND t2.id = g2." + geoEntityClassCol + "";
    sql += " )";
    sql += " , recursive_rollup AS (";
    sql += " SELECT " + RelationshipDAOIF.CHILD_ID_COLUMN + ", " + RelationshipDAOIF.PARENT_ID_COLUMN + ", 0 AS depth , " + geoEntityTable + "." + typeCol + " , ";
    sql += " COALESCE((";
    // this is the table with the sumable value
    sql += " SELECT " + attribute + " FROM " + weeklyThresholdTable + ", " + thresholdDataTable + ", " + malariaSeasonTable + " ";
    sql += "    WHERE " + weeklyThresholdTable + "." + RelationshipDAOIF.CHILD_ID_COLUMN + " = '" + week.getId() + "'";
    sql += "    AND " + thresholdDataTable + "." + geoEntityCol + " = " + locatedInTable + "." + RelationshipDAOIF.CHILD_ID_COLUMN + "";
    sql += "    AND " + weeklyThresholdTable + "." + RelationshipDAOIF.PARENT_ID_COLUMN + " = " + thresholdDataTable + "." + idCol + "";
    sql += "    AND geohierarchy_flags.parent_populationallowed = 1";
    sql += "    AND " + thresholdDataTable + "." + "season" + " = " + malariaSeasonTable + "." + "id";
    sql += "    AND " + malariaSeasonTable + "." + "disease" + " = " + "'" + Disease.getCurrent().getId() + "'";
    sql += "  ),0)as sumvalue";
    sql += "  FROM " + locatedInTable + ", geohierarchy_flags, " + geoEntityTable + "";
    // --zambia";
    sql += " WHERE " + RelationshipDAOIF.PARENT_ID_COLUMN + " = '" + entity.getId() + "'";
    sql += "  AND " + locatedInTable + "." + RelationshipDAOIF.CHILD_ID_COLUMN + " = " + geoEntityTable + "." + idCol + "";
    sql += "  AND " + geoEntityTable + "." + typeCol + " = geohierarchy_flags.parent_type";
    sql += "  AND geohierarchy_flags.parent_political = 1";
    sql += " UNION";
    sql += " SELECT b." + RelationshipDAOIF.CHILD_ID_COLUMN + ", b." + RelationshipDAOIF.PARENT_ID_COLUMN + ", a.depth+1 , " + geoEntityTable + "." + typeCol + " , ";
    // this is how we sum. We only sum if population is allowed";
    sql += " a.sumvalue +  COALESCE((";
    sql += "  SELECT " + attribute + " FROM " + weeklyThresholdTable + ", " + thresholdDataTable + ", " + malariaSeasonTable + "  ";
    sql += "    WHERE " + weeklyThresholdTable + "." + RelationshipDAOIF.CHILD_ID_COLUMN + " = '" + week.getId() + "'";
    sql += "    AND " + thresholdDataTable + "." + geoEntityCol + " = b." + RelationshipDAOIF.CHILD_ID_COLUMN + "";
    sql += "    AND " + weeklyThresholdTable + "." + RelationshipDAOIF.PARENT_ID_COLUMN + " = " + thresholdDataTable + "." + idCol + "";
    sql += "    AND geohierarchy_flags.parent_populationallowed = 1";
    sql += "    AND " + thresholdDataTable + "." + "season" + " = " + malariaSeasonTable + "." + "id";
    sql += "    AND " + malariaSeasonTable + "." + "disease" + " = " + "'" + Disease.getCurrent().getId() + "'";
    sql += " ),0)";
    sql += " FROM recursive_rollup a, " + locatedInTable + " b , geohierarchy_flags, " + geoEntityTable + " ";
    sql += " WHERE a." + RelationshipDAOIF.CHILD_ID_COLUMN + " = b." + RelationshipDAOIF.PARENT_ID_COLUMN + "";
    sql += " AND b." + RelationshipDAOIF.CHILD_ID_COLUMN + " = " + geoEntityTable + "." + idCol + "";
    sql += " AND " + geoEntityTable + "." + typeCol + " = geohierarchy_flags.parent_type";
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
        sum += Double.parseDouble(value);
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
  public static void checkThresholdViolation(Date date, GeoEntity entity, double count, Date symptomOnsetDate)
  {
    if (entity.getType().equals(Earth.CLASS))
    {
      return;
    }

    ThresholdData.checkThreshold(WeeklyThreshold.IDENTIFICATION, date, entity, count, false, symptomOnsetDate);
    ThresholdData.checkThreshold(WeeklyThreshold.NOTIFICATION, date, entity, count, false, symptomOnsetDate);
  }

  @Transaction
  @Authenticate
  public static void checkFacilityThresholdViolation(Date date, GeoEntity entity, double count)
  {
    if (entity.getType().equals(Earth.CLASS))
    {
      return;
    }

    ThresholdData.checkThreshold(WeeklyThreshold.FACILITYIDENTIFICATION, date, entity, count, true, date);
    ThresholdData.checkThreshold(WeeklyThreshold.FACILITYNOTIFICATION, date, entity, count, true, date);
  }

  private static void checkThreshold(String accessor, Date date, GeoEntity entity, double cases, boolean facility, Date messageDate)
  {
    WeeklyThreshold threshold = ThresholdData.getThresholds(entity, date);
    EpiWeek week = EpiWeek.getEpiWeek(date);

    Double count = null;

    if (threshold != null)
    {
      count = threshold.getThreshold(accessor);
    }

    if (count == null && !facility)
    {
      count = ThresholdData.getCalculatedValue(entity, week, accessor);
    }

    if (count != null && Math.round(100d * cases) >= Math.round(100d * count))
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

        // Perform the alert
        performAlert(accessor, entity, count, cases, date, week, messageDate);

        threshold.reachedThreshold(accessor, count);
      }
    }
  }

  private static SystemAlertType getSystemAlertType(String accessor)
  {
    if (accessor.equals(WeeklyThreshold.IDENTIFICATION))
    {
      return SystemAlertType.SOURCE_OUTBREAK_IDENTIFICATION;
    }
    else if (accessor.equals(WeeklyThreshold.NOTIFICATION))
    {
      return SystemAlertType.SOURCE_OUTBREAK_NOTIFICATION;
    }
    else if (accessor.equals(WeeklyThreshold.FACILITYIDENTIFICATION))
    {
      return SystemAlertType.FACILITY_OUTBREAK_IDENTIFICATION;
    }
    else if (accessor.equals(WeeklyThreshold.FACILITYNOTIFICATION))
    {
      return SystemAlertType.FACILITY_OUTBREAK_NOTIFICATION;
    }

    return null;
  }

  private static String formatDateForMessage(Date messageDate)
  {
    if (messageDate != null)
    {
      DateFormat format = SimpleDateFormat.getDateInstance(DateFormat.SHORT, Session.getCurrentLocale());
      String formattedDate = format.format(messageDate);

      return formattedDate;
    }

    return "";
  }

  private static void performAlert(String accessor, GeoEntity entity, double threshold, double count, Date date, EpiWeek week, Date messageDate)
  {
    SystemAlertType alertType = ThresholdData.getSystemAlertType(accessor);

    String alertLevel = MDSSProperties.getString("Outbreak");
    ThresholdAlertCalculationType config = ThresholdAlertCalculationType.getCurrent();
    String formattedDate = ThresholdData.formatDateForMessage(messageDate);

    if (config.getEpidemicUniversal().equals(GeoHierarchy.getGeoHierarchyFromType(entity.getType())))
    {
      alertLevel = MDSSProperties.getString("Epidemic");
    }
    else
    {
      for (GeoHierarchy u : config.getEpidemicUniversal().getAllParents())
      {
        if (u.equals(GeoHierarchy.getGeoHierarchyFromType(entity.getType())))
        {
          alertLevel = MDSSProperties.getString("Epidemic");
          break;
        }
      }
    }

    SystemAlert systemAlert = SystemAlert.get(alertType);
    String label = entity.getLabel();
    boolean emailSent = false;

    HashMap<String, Object> data = new HashMap<String, Object>();
    data.put("alertType", alertType.getDisplayLabel());
    data.put("alertLevel", alertLevel);
    data.put("thresholdType", accessor);
    data.put("thresholdValue", String.format(OutbreakAlert.VALUE_FORMAT, threshold));
    data.put("actualValue", String.format(OutbreakAlert.VALUE_FORMAT, count));
    data.put("geoEntity", label);

    // Note: EpiWeek is 0 based, thus 1 needs to be added for the message
    data.put("epiWeek", week.getPeriod() + 1);

    if (alertType.equals(SystemAlertType.SOURCE_OUTBREAK_IDENTIFICATION) || alertType.equals(SystemAlertType.SOURCE_OUTBREAK_NOTIFICATION))
    {
      data.put("symptomOnsetDate", formattedDate);
    }

    if (alertType.equals(SystemAlertType.FACILITY_OUTBREAK_IDENTIFICATION) || alertType.equals(SystemAlertType.FACILITY_OUTBREAK_NOTIFICATION))
    {
      data.put("visitDate", formattedDate);
    }

    if (systemAlert.getDisease() != null)
    {
      data.put("disease", systemAlert.getDisease().getDisplayLabel());
    }
    else
    {
      data.put("disease", MDSSProperties.getString("All_Diseases"));
    }

    if (systemAlert.getIsEmailActive())
    {
      emailSent = systemAlert.sendEmail(data);
    }

    if (systemAlert.getIsOnscreenActive())
    {
      String message = systemAlert.getTemplate(data);

      OutbreakAlert alert = new OutbreakAlert();
      alert.setMessageText(message);

      if (systemAlert.getIsEmailActive() & !emailSent)
      {
        alert.setEmailFailure(true);
      }

      alert.apply();

      alert.throwIt();
    }
  }
}
