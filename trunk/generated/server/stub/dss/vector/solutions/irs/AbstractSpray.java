package dss.vector.solutions.irs;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.InnerJoinEq;
import com.runwaysdk.query.InnerJoinGtEq;
import com.runwaysdk.query.InnerJoinLtEq;
import com.runwaysdk.query.QueryException;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableSQL;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.Property;
import dss.vector.solutions.PropertyInfo;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.query.IncidencePopulationException;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.util.QueryUtil;

public abstract class AbstractSpray extends AbstractSprayBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 507539322;

  public AbstractSpray()
  {
    super();
  }

  @Override
  public void apply()
  {
	this.validateBrand();
	
    super.apply();
  }
  
	@Override
	public void validateBrand() {
		super.validateBrand();
		
		if (this.getBrand() != null) {
			if (!this.getBrand().getInsecticideUse().contains(InsecticideBrandUse.IRS)) {
				InvalidInsecticideBrandUseProblem p = new InvalidInsecticideBrandUseProblem();
				p.setNotification(this, BRAND);
				p.throwIt();
			}
		}
	}
	
  /**
   * Takes in an XML string and returns a ValueQuery representing the structured
   * query in the XML.
   * 
   * @param xml
   * @return
   */
  public static ValueQuery xmlToValueQuery(String xml, String config, Layer layer)
  {
    JSONObject queryConfig;
    try
    {
      queryConfig = new JSONObject(config);
    }
    catch (JSONException e1)
    {
      throw new ProgrammingErrorException(e1);
    }

    QueryFactory queryFactory = new QueryFactory();

    ValueQuery valueQuery = new ValueQuery(queryFactory);

    // IMPORTANT: Required call for all query screens.
    Map<String, GeneratedEntityQuery> queryMap = QueryUtil.joinQueryWithGeoEntities(queryFactory, valueQuery, xml, queryConfig, layer);

    AbstractSprayQuery abstractSprayQuery = (AbstractSprayQuery) queryMap.get(AbstractSpray.CLASS);

    if (abstractSprayQuery != null)
    {
      QueryUtil.joinGeoDisplayLabels(valueQuery, AbstractSpray.CLASS, abstractSprayQuery);
      QueryUtil.joinTermAllpaths(valueQuery, AbstractSpray.CLASS, abstractSprayQuery);
      QueryUtil.joinEnumerationDisplayLabels(valueQuery, AbstractSpray.CLASS, abstractSprayQuery);
    }

    InsecticideBrandQuery insecticideQuery = (InsecticideBrandQuery) queryMap.get(InsecticideBrand.CLASS);

    if (insecticideQuery != null)
    {
      valueQuery.WHERE(abstractSprayQuery.getBrand().EQ(insecticideQuery));

      QueryUtil.joinEnumerationDisplayLabels(valueQuery,  InsecticideBrand.CLASS, insecticideQuery);
      QueryUtil.joinTermAllpaths(valueQuery, InsecticideBrand.CLASS, insecticideQuery);
    }

    QueryUtil.setTermRestrictions(valueQuery, queryMap);

    QueryUtil.setNumericRestrictions(valueQuery, queryConfig);

    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap);

    AbstractSpray.setWithQuerySQL(abstractSprayQuery, valueQuery);
    
    
    // TODO use constants to represent the table and column aliases since the class of the actual columns may
    // be mapped to by more than one class (e.g., OperatorSpray and HouseholdSprayStatus).
    String avilableUnits = "(CASE WHEN spray_unit = 'ROOM' THEN rooms  WHEN spray_unit = 'STRUCTURE' THEN structures WHEN spray_unit = 'HOUSEHOLD' THEN households END )";
    String sprayedUnits = "(CASE WHEN spray_unit = 'ROOM' THEN sprayedRooms  WHEN spray_unit = 'STRUCTURE' THEN sprayedStructures WHEN spray_unit = 'HOUSEHOLD' THEN sprayedHouseholds END )";
    String unsprayedUnits = "(CASE WHEN spray_unit = 'ROOM' THEN (room_unsprayed)  WHEN spray_unit = 'STRUCTURE' THEN (structure_unsprayed) WHEN spray_unit = 'HOUSEHOLD' THEN (household_unsprayed)  END )";
    String shareOfCans = "(CASE WHEN spray_unit = 'ROOM' THEN (sprayedrooms_share)  WHEN spray_unit = 'STRUCTURE' THEN (sprayedstructures_share) WHEN spray_unit = 'HOUSEHOLD' THEN (sprayedhouseholds_share)  END )";

    String unit_operational_coverage = "SUM(" + sprayedUnits + "))::float / nullif(SUM(" + avilableUnits + "),0";

    String unit_application_rate = "SUM(refills::FLOAT * " + shareOfCans + " * active_ingredient_per_can) / nullif(SUM(" + sprayedUnits + " * unitarea),0)";
    String unit_application_ratio = "((" + unit_application_rate + ") / AVG(standard_application_rate))";

    QueryUtil.setSelectabeSQL(valueQuery, "sprayedunits", sprayedUnits);
    QueryUtil.setSelectabeSQL(valueQuery, "unit_unsprayed", unsprayedUnits);
    QueryUtil.setSelectabeSQL(valueQuery, "refills * " + shareOfCans, unsprayedUnits);

    QueryUtil.setSelectabeSQL(valueQuery, "unit_application_rate", "(" + unit_application_rate + ")");
    QueryUtil.setSelectabeSQL(valueQuery, "unit_application_rate_mg", "1000.0 *" + "(" + unit_application_rate + ")");
    QueryUtil.setSelectabeSQL(valueQuery, "unit_application_ratio", unit_application_ratio);
    QueryUtil.setSelectabeSQL(valueQuery, "unit_operational_coverage", unit_operational_coverage);

    QueryUtil.setSelectabeSQL(valueQuery, "calculated_rooms_sprayed", "(" + unit_operational_coverage + ") * SUM(rooms)");
    QueryUtil.setSelectabeSQL(valueQuery, "calculated_structures_sprayed", "(" + unit_operational_coverage + ") * SUM(structures)");
    QueryUtil.setSelectabeSQL(valueQuery, "calculated_households_sprayed", "(" + unit_operational_coverage + ") * SUM(households)");

    calculatePlannedCoverage(valueQuery, queryConfig, xml, sprayedUnits);
    calculateAreaTargets(valueQuery, queryConfig, xml);

    return valueQuery;
  }

  public static boolean queryIsGrouped(ValueQuery valueQuery)
  {
    for (Selectable s : valueQuery.getSelectableRefs())
    {
      if (s.isAggregateFunction())
      {
        return true;
      }
    }
    return false;
  }

  public static void setWithQuerySQL(AbstractSprayQuery abstractSprayQuery, ValueQuery valueQuery)
  {
    String sprayView = "sprayView";
    String insecticideView = "insecticideView";
    String targetView = "targetView";
    String tableName = abstractSprayQuery.getMdClassIF().getTableName();
    String tableAlias = abstractSprayQuery.getTableAlias();

    String targetViewQuery = ResourceTarget.getTempTableSQL();
    String insecticideBrandQuery = InsecticideBrand.getTempTableSQL();

    String sprayQuery = OperatorSpray.getTempTableSQL(targetView, queryIsGrouped(valueQuery));
    sprayQuery += "\nUNION\n" + TeamSpray.getTempTableSQL(targetView);
    sprayQuery += "\nUNION\n" + ZoneSpray.getTempTableSQL(targetView);

    String sql = "WITH ";

    sql += " " + targetView + " AS \n";
    sql += "(" + targetViewQuery + ")\n";

    sql += ", " + insecticideView + " AS \n";
    sql += "(" + insecticideBrandQuery + ")\n";

    sql += "," + sprayView + " AS \n";
    sql += "(" + sprayQuery + ")\n";

    valueQuery.setSqlPrefix(sql);

    String id = QueryUtil.getIdColumn();
    
    valueQuery.WHERE(new InnerJoinEq(id, tableName, tableAlias, id, sprayView, sprayView));

    String brandCol = QueryUtil.getColumnName(abstractSprayQuery.getMdClassIF(), AbstractSpray.BRAND);
    String sprayDateCol = QueryUtil.getColumnName(abstractSprayQuery.getMdClassIF(), AbstractSpray.SPRAYDATE);
    
    String diseaseCol = QueryUtil.getColumnName(InsecticideBrand.getDiseaseMd());
    
    valueQuery.WHERE(new InnerJoinEq(brandCol, tableName, tableAlias, id, insecticideView, insecticideView));
    valueQuery.WHERE(new InnerJoinGtEq(sprayDateCol, tableName, tableAlias, "start_date", insecticideView, insecticideView));
    valueQuery.WHERE(new InnerJoinLtEq(sprayDateCol, tableName, tableAlias, "end_date", insecticideView, insecticideView));
    valueQuery.WHERE(new InnerJoinGtEq(sprayDateCol, tableName, tableAlias, "nozzleStart", insecticideView, insecticideView));
    valueQuery.WHERE(new InnerJoinLtEq(sprayDateCol, tableName, tableAlias, "nozzleEnd", insecticideView, insecticideView));
    
    
    valueQuery.WHERE(new InnerJoinEq(diseaseCol, sprayView, sprayView, "disease", insecticideView, insecticideView));
  }

  private static String getGeoType(ValueQuery valueQuery, JSONObject queryConfig, String xml, String attrib)
  {

    String geoType = null;
    try
    {
      String attributeKey = null;

      String[] selectedUniversals = null;

      JSONObject selectedUniMap = queryConfig.getJSONObject(QueryConstants.SELECTED_UNIVERSALS);
      Iterator<?> keys = selectedUniMap.keys();
      while (keys.hasNext())
      {
        attributeKey = (String) keys.next();

        JSONArray universals = selectedUniMap.getJSONArray(attributeKey);
        if (universals.length() > 0 && attributeKey.equals(attrib))
        {
          selectedUniversals = new String[universals.length()];
          for (int i = 0; i < universals.length(); i++)
          {
            selectedUniversals[i] = universals.getString(i);
          }
          // dss_vector_solutions_intervention_monitor_IndividualCase_probableSource__district_geoId
          geoType = GeoHierarchy.getMostChildishUniversialType(selectedUniversals);
          geoType = geoType.substring(geoType.lastIndexOf('.')).toLowerCase();
          geoType = attributeKey + '.' + geoType + '.' + GeoEntity.GEOID;
          geoType = geoType.replace('.', '_');
        }
      }
    }

    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }

    return geoType;
  }

  private static void calculateAreaTargets(ValueQuery valueQuery, JSONObject queryConfig, String xml)
  {
    SelectableSQL calc;
    if (valueQuery.hasSelectableRef("planed_area_target"))
    {
      calc = (SelectableSQL) valueQuery.getSelectableRef("planed_area_target");
    }
    else
    {
      return;
    }

    String geoType = getGeoType(valueQuery, queryConfig, xml, AbstractSpray.CLASS + '.' + AbstractSpray.GEOENTITY);

    String sql = "NULL";

    Selectable s;
    int startDay = Property.getInt(PropertyInfo.EPI_WEEK_PACKAGE, PropertyInfo.EPI_START_DAY);

    MdEntityDAOIF geoEntityMd = MdEntityDAO.getMdEntityDAO(GeoEntity.CLASS);
    String geoEntityTable = geoEntityMd.getTableName();
    String geoIdCol = QueryUtil.getColumnName(geoEntityMd, GeoEntity.GEOID);
    
    String sprayDateCol = QueryUtil.getColumnName(AbstractSpray.CLASS, AbstractSpray.SPRAYDATE);
    
    if (valueQuery.hasSelectableRef(geoType))
    {
      s = valueQuery.getSelectableRef(geoType);
      String columnAlias = s.getDbQualifiedName();
      sql = "get_seasonal_spray_target_by_geoEntityId_and_seasonId_and_tar(";
      sql += "MAX((SELECT id FROM "+geoEntityTable+" g WHERE g."+geoIdCol+" = " + columnAlias + ")), ";
      sql += "MAX(spray_season), ";
      sql += "'target_'||( get_epiWeek_from_date(MAX("+sprayDateCol+")," + startDay + ")-1))";

    }
    calc.setSQL(sql);
  }

  private static void calculatePlannedCoverage(ValueQuery valueQuery, JSONObject queryConfig, String xml, String sprayedUnits)
  {
    SelectableSQL calc;
    if (valueQuery.hasSelectableRef("planned_coverage"))
    {
      calc = (SelectableSQL) valueQuery.getSelectableRef("planned_coverage");
    }
    else
    {
      return;
    }

    String geoType = getGeoType(valueQuery, queryConfig, xml, AbstractSpray.CLASS + '.' + AbstractSpray.GEOENTITY);
    Selectable s;

    try
    {
      s = valueQuery.getSelectableRef(geoType);
    }
    catch (QueryException e)
    {
      throw new IncidencePopulationException(e);
    }

    String sql = "NULL";

    int startDay = Property.getInt(PropertyInfo.EPI_WEEK_PACKAGE, PropertyInfo.EPI_START_DAY);

    MdEntityDAOIF geoEntityMd = MdEntityDAO.getMdEntityDAO(GeoEntity.CLASS);
    String geoEntityTable = geoEntityMd.getTableName();
    String geoIdCol = QueryUtil.getColumnName(geoEntityMd, GeoEntity.GEOID);
    
    String sprayDateCol = QueryUtil.getColumnName(AbstractSpray.CLASS, AbstractSpray.SPRAYDATE);
    
    String columnAlias = s.getDbQualifiedName();
    sql = "SUM(" + sprayedUnits + ")::float / get_seasonal_spray_target_by_geoEntityId_and_seasonId_and_tar(";

    sql += "MAX((SELECT id FROM "+geoEntityTable+" g WHERE g."+geoIdCol+" = " + columnAlias + ")), ";
    sql += "MAX(spray_season), ";
    sql += "'target_'||( get_epiWeek_from_date(MAX("+sprayDateCol+")," + startDay + ")-1))";

    calc.setSQL(sql);
  }
}
