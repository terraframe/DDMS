package dss.vector.solutions.irs;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.InnerJoinEq;
import com.runwaysdk.query.InnerJoinGtEq;
import com.runwaysdk.query.InnerJoinLtEq;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.QueryUtil;

public abstract class AbstractSpray extends AbstractSprayBase implements com.runwaysdk.generation.loader.Reloadable
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

    QueryUtil.setTermRestrictions(valueQuery, queryMap);

    QueryUtil.setNumericRestrictions(valueQuery, queryConfig);
    
    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap);
    
    AbstractSpray.setWithQuerySQL(abstractSprayQuery, valueQuery);
    String avilableUnits = "(CASE WHEN spray_unit = 'ROOM' THEN rooms  WHEN spray_unit = 'STRUCTURE' THEN structures WHEN spray_unit = 'HOUSEHOLD' THEN households END )";
    String sprayedUnits = "(CASE WHEN spray_unit = 'ROOM' THEN sprayedrooms  WHEN spray_unit = 'STRUCTURE' THEN sprayedstructures WHEN spray_unit = 'HOUSEHOLD' THEN sprayedhouseholds END )";
    String unsprayedUnits = "(CASE WHEN spray_unit = 'ROOM' THEN (room_unsprayed)  WHEN spray_unit = 'STRUCTURE' THEN (structure_unsprayed) WHEN spray_unit = 'HOUSEHOLD' THEN (household_unsprayed)  END )";
    String unit_operational_coverage = "SUM("+sprayedUnits+")) / (SUM("+avilableUnits+")";
    String unit_application_rate = "(refills::FLOAT * active_ingredient_per_can) / ("+sprayedUnits+" * unitarea)";
    String unit_application_ratio = "(("+unit_application_rate+") / standard_application_rate)";

    
    QueryUtil.setSelectabeSQL(valueQuery, "sprayedunits", sprayedUnits);
    QueryUtil.setSelectabeSQL(valueQuery, "unit_unsprayed" , unsprayedUnits);
    
    QueryUtil.setSelectabeSQL(valueQuery, "unit_application_rate", "SUM(" + unit_application_rate + ")");
    QueryUtil.setSelectabeSQL(valueQuery, "unit_application_rate_mg",  "1000.0 *" +"SUM(" + unit_application_rate + ")");
    QueryUtil.setSelectabeSQL(valueQuery, "unit_application_ratio", "SUM("+sprayedUnits+"*"+unit_application_ratio+") / SUM("+sprayedUnits+")");
    QueryUtil.setSelectabeSQL(valueQuery, "unit_operational_coverage", unit_operational_coverage );
    
    QueryUtil.setSelectabeSQL(valueQuery, "calculated_rooms_sprayed" , "(" + unit_operational_coverage+") * SUM(rooms)");
    QueryUtil.setSelectabeSQL(valueQuery, "calculated_structures_sprayed" ,"(" +  unit_operational_coverage+") * SUM(structures)");
    QueryUtil.setSelectabeSQL(valueQuery, "calculated_households_sprayed" ,"(" + unit_operational_coverage+") * SUM(households)");

    
    return valueQuery;
  }
 
  
  public static boolean queryIsGrouped(ValueQuery valueQuery)
  {
    for (Selectable s : valueQuery.getSelectableRefs())
    {
      if(s.isAggregateFunction())
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
    
    String targetViewQuery =  ResourceTarget.getTempTableSQL();
    String insecticideBrandQuery = InsecticideBrand.getTempTableSQL();
    
    String  sprayQuery = OperatorSpray.getTempTableSQL(targetView,queryIsGrouped(valueQuery));
    sprayQuery += "\nUNION\n"+TeamSpray.getTempTableSQL(targetView);
    sprayQuery += "\nUNION\n"+ZoneSpray.getTempTableSQL(targetView);
    
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
    valueQuery.WHERE(new InnerJoinGtEq(AbstractSpray.SPRAYDATE, tableName, tableAlias, "nozzleStart", insecticideView, insecticideView));
    valueQuery.WHERE(new InnerJoinLtEq(AbstractSpray.SPRAYDATE, tableName, tableAlias, "nozzleEnd", insecticideView, insecticideView));
  }
  

 
}
