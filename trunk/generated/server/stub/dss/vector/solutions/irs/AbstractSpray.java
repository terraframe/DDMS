package dss.vector.solutions.irs;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.query.GeneratedEntityQuery;
import com.terraframe.mojo.query.InnerJoinEq;
import com.terraframe.mojo.query.InnerJoinGtEq;
import com.terraframe.mojo.query.InnerJoinLtEq;
import com.terraframe.mojo.query.QueryException;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.SelectableSQL;
import com.terraframe.mojo.query.SelectableSQLDouble;
import com.terraframe.mojo.query.ValueQuery;

import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.QueryUtil;

public abstract class AbstractSpray extends AbstractSprayBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 507539322;

  public AbstractSpray()
  {
    super();
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
      
      QueryUtil.joinTermAllpaths(valueQuery, InsecticideBrand.CLASS, insecticideQuery);
    }


    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap);

    QueryUtil.setQueryRatio(xml, valueQuery, "COUNT(*)");

    QueryUtil.setTermRestrictions(valueQuery, queryMap);

    QueryUtil.setNumericRestrictions(valueQuery, queryConfig);
    
    AbstractSpray.setWithQuerySQL(abstractSprayQuery, valueQuery);
    String avilableUnits = "(CASE WHEN spray_unit = 'ROOM' THEN rooms  WHEN spray_unit = 'STRUCTURE' THEN structures WHEN spray_unit = 'HOUSEHOLD' THEN households END )";
    String sprayedUnits = "(CASE WHEN spray_unit = 'ROOM' THEN sprayedrooms  WHEN spray_unit = 'STRUCTURE' THEN sprayedstructures WHEN spray_unit = 'HOUSEHOLD' THEN sprayedhouseholds END )";
    String unsprayedUnits = "(CASE WHEN spray_unit = 'ROOM' THEN (rooms - sprayedrooms)  WHEN spray_unit = 'STRUCTURE' THEN (structures - sprayedstructures) WHEN spray_unit = 'HOUSEHOLD' THEN (households - sprayedhouseholds)  END )";
    
    try
    { 
      SelectableSQL calc = (SelectableSQL) valueQuery.getSelectableRef("sprayedunits");
      String sql = sprayedUnits;
      calc.setSQL(sql);
    }
    catch (QueryException e)
    {
    }
    
    try
    {
      SelectableSQL calc = (SelectableSQL) valueQuery.getSelectableRef("unit_unsprayed");
      String sql = unsprayedUnits;
      calc.setSQL(sql);
    }
    catch (QueryException e)
    {
    }
    
    try
    { 
      SelectableSQLDouble calc = (SelectableSQLDouble) valueQuery.getSelectableRef("unit_application_rate");
      String sql = "SUM(refills_for_calc::FLOAT * active_ingredient_per_can ) / SUM("+sprayedUnits+"::FLOAT * unitarea)";
      calc.setSQL(sql);
    }
    catch (QueryException e)
    {
    }
    
    try
    {
      SelectableSQLDouble calc = (SelectableSQLDouble) valueQuery.getSelectableRef("unit_application_rate_mg");
      String sql = "1000.0 * SUM(refills_for_calc::FLOAT * active_ingredient_per_can ) / SUM("+sprayedUnits+"::FLOAT * unitarea)";
      calc.setSQL(sql);
    }
    catch (QueryException e)
    {
    }
    
    try
    {
      SelectableSQLDouble calc = (SelectableSQLDouble) valueQuery.getSelectableRef("unit_operational_coverage");
      String sql = "SUM("+avilableUnits+") / SUM("+sprayedUnits+")";
      calc.setSQL(sql);
    }
    catch (QueryException e)
    {
    }
    
    try
    {
      SelectableSQLDouble calc = (SelectableSQLDouble) valueQuery.getSelectableRef("unit_application_ratio");
      String sql = "(units_per_can)::FLOAT/(SUM("+sprayedUnits+")/SUM(refills_for_calc)) ";
      calc.setSQL(sql);
    }
    catch (QueryException e)
    {
    }
    
    return valueQuery;
  }
  
  public static void setWithQuerySQL(AbstractSprayQuery abstractSprayQuery, ValueQuery valueQuery)
  {

    String sprayView = "sprayView";
    String insecticideView = "insecticideView";
    String targetView = "targetView";
    String tableName = abstractSprayQuery.getMdClassIF().getTableName();
    String tableAlias = abstractSprayQuery.getTableAlias();
    
    String sprayQuery = OperatorSpray.getTempTableSQL(targetView);
    String insecticideBrandQuery = InsecticideBrand.getTempTableSQL();
    String targetViewQuery =  ResourceTarget.getTempTableSQL();
    
    String sql = "WITH ";
    
    sql += " " + targetView + " AS \n";
    sql += "(" + targetViewQuery + ")\n";
    
    sql += ", "+ insecticideView + " AS \n";
    sql += "(" + insecticideBrandQuery + ")\n";
    
    sql += "," + sprayView + " AS \n";
    sql += "(" + sprayQuery + ")\n";
    
    valueQuery.setSqlPrefix(sql);
    
    valueQuery.WHERE(new InnerJoinEq("id", tableName, tableAlias, "id", sprayView, sprayView));
    
    valueQuery.WHERE(new InnerJoinEq(AbstractSpray.BRAND, tableName, tableAlias, "id", insecticideView, insecticideView));
    valueQuery.WHERE(new InnerJoinGtEq(AbstractSpray.SPRAYDATE, tableName, tableAlias, "startdate", insecticideView, insecticideView));
    valueQuery.WHERE(new InnerJoinLtEq(AbstractSpray.SPRAYDATE, tableName, tableAlias, "enddate", insecticideView, insecticideView));
  }
  
  /*
  public static String getTempTableSQL()
  {
    String select = "SELECT spraystatus.id,\n";

    // insecticide stuff
    // select += "active_ingredient_per_can_view.active_ingredient_per_can,\n";
    select += "active_ingredient_per_can_view.active_ingredient_per_can /  areastandards.unitnozzleareacoverage AS standard_application_rate,\n";
    select += "active_ingredient_per_can_view.active_ingredient_per_can AS active_ingredient_per_can,\n";
    select += "(1000.0 * active_ingredient_per_can_view.active_ingredient_per_can) /  areastandards.unitnozzleareacoverage AS standard_application_rate_mg,\n";
    select += "nozzle_defaultLocale AS nozzle_defaultLocale,\n";
    select += "nozzle_ratio AS nozzle_ratio,\n";

    // --application rate is:
    // --(# can refills * amount of active ingredient per can refill) / (# units
    // sprayed *average size of unit).\n";
    select += "(CAST(refills AS float) * active_ingredient_per_can_view.active_ingredient_per_can * (spraystatus.sprayedrooms / room_total)) / NULLIF(spraystatus.sprayedrooms * areastandards.room , 0) AS room_application_rate,\n";
    select += "(CAST(refills AS float) * active_ingredient_per_can_view.active_ingredient_per_can * (spraystatus.sprayedstructures/structure_total)) / NULLIF(spraystatus.sprayedstructures * areastandards.structurearea, 0) AS structure_application_rate,\n";
    select += "(CAST(refills AS float) * active_ingredient_per_can_view.active_ingredient_per_can * (spraystatus.sprayedhouseholds/ household_total)) / NULLIF(spraystatus.sprayedhouseholds * areastandards.household, 0) AS household_application_rate,\n";

    select += "(CAST(refills AS float) * 1000.0 * active_ingredient_per_can_view.active_ingredient_per_can * (spraystatus.sprayedrooms / room_total)) / NULLIF(spraystatus.sprayedrooms * areastandards.room , 0) AS room_application_rate_mg,\n";
    select += "(CAST(refills AS float) * 1000.0 * active_ingredient_per_can_view.active_ingredient_per_can * (spraystatus.sprayedstructures/structure_total)) / NULLIF(spraystatus.sprayedstructures * areastandards.structurearea, 0) AS structure_application_rate_mg,\n";
    select += "(CAST(refills AS float) * 1000.0 * active_ingredient_per_can_view.active_ingredient_per_can * (spraystatus.sprayedhouseholds/ household_total)) / NULLIF(spraystatus.sprayedhouseholds * areastandards.household, 0) AS household_application_rate_mg,\n";
    
    // --application ratio is:\n";
    // --"--(# can refills (30)* ave m_ per can refill (10)*nozzle ratio (6)) / (total units sprayed (11, 12 or 13)*ave m_ per unit (38, 37 or 36))\n";
    select += "((CAST(refills AS float) * (spraystatus.sprayedrooms / room_total)) * areastandards.unitnozzleareacoverage * active_ingredient_per_can_view.nozzle_ratio) / NULLIF(spraystatus.sprayedrooms      * areastandards.room, 0) AS room_application_ratio,\n";
    select += "((CAST(refills AS float) * (spraystatus.sprayedstructures/structure_total)) * areastandards.unitnozzleareacoverage * active_ingredient_per_can_view.nozzle_ratio) / NULLIF(spraystatus.sprayedstructures * areastandards.structurearea, 0) AS structure_application_ratio,\n";
    select += "((CAST(refills AS float) * (spraystatus.sprayedhouseholds/ household_total)) * areastandards.unitnozzleareacoverage * active_ingredient_per_can_view.nozzle_ratio) / NULLIF(spraystatus.sprayedhouseholds * areastandards.household, 0) AS household_application_ratio,\n";

    // --operational coverage is:\n";
    // --(Total units sprayed / Total units available) *100 (to calculate
    // percentage of units sprayed). \n";
    select += "CAST(spraystatus.sprayedrooms      AS float) / NULLIF(spraystatus.rooms      ,0) * 100 AS room_operational_coverage,\n";
    select += "CAST(spraystatus.sprayedstructures AS float) / NULLIF(spraystatus.structures ,0) * 100 AS structure_operational_coverage,\n";
    select += "CAST(spraystatus.sprayedhouseholds AS float) / NULLIF(spraystatus.households ,0) * 100 AS household_operational_coverage,\n";

    // unsprayed
    select += "(spraystatus.rooms - spraystatus.sprayedrooms) AS room_unsprayed,\n";
    select += "(spraystatus.structures - spraystatus.sprayedstructures) AS structure_unsprayed,\n";
    select += "(spraystatus.households - spraystatus.sprayedhouseholds) AS household_unsprayed,\n";

    String from = " FROM ";
    // get the main tables
    from += MdBusiness.getMdBusiness(AbstractSpray.CLASS).getTableName() + " AS abstractspray,\n";
    from += MdBusiness.getMdBusiness(AreaStandards.CLASS).getTableName() + " AS areastandards,\n";
    from += "";

    // get views
    from += "(" + InsecticideBrand.getTempTableSQL() + ") AS active_ingredient_per_can_view,\n";
    //from += "(" + ActorSpray.getUnitTotalsSQL() + ") AS unit_totals_view,\n";

    String where = "";
    // join main tables
    where += "AND spraydata.id = abstractspray.spraydata \n";
    where += "AND spraystatus.spray = abstractspray.id \n";
    where += "AND abstractspray.id = actorspray.id \n";
    // join views
    where += "AND spraydata.brand = active_ingredient_per_can_view.id \n";
    where += "AND actorspray.id = unit_totals_view.id \n";

    select = select.substring(0, select.length() - 2);
    from = from.substring(0, from.length() - 2);
    where = "WHERE " + where.substring(3, where.length() - 2);

    return select + "\n" + from + "\n" + where;
  }
*/
  
 
}
