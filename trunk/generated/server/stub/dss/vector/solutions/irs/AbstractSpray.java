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
import com.terraframe.mojo.query.Selectable;
import com.terraframe.mojo.query.SelectableSQL;
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
    
    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap);
    
    AbstractSpray.setWithQuerySQL(abstractSprayQuery, valueQuery);
    String avilableUnits = "(CASE WHEN spray_unit = 'ROOM' THEN rooms  WHEN spray_unit = 'STRUCTURE' THEN structures WHEN spray_unit = 'HOUSEHOLD' THEN households END )";
    String sprayedUnits = "(CASE WHEN spray_unit = 'ROOM' THEN sprayedrooms  WHEN spray_unit = 'STRUCTURE' THEN sprayedstructures WHEN spray_unit = 'HOUSEHOLD' THEN sprayedhouseholds END )";
    String unsprayedUnits = "(CASE WHEN spray_unit = 'ROOM' THEN (room_unsprayed)  WHEN spray_unit = 'STRUCTURE' THEN (structure_unsprayed) WHEN spray_unit = 'HOUSEHOLD' THEN (household_unsprayed)  END )";
    String unit_operational_coverage = "SUM("+sprayedUnits+")) / (SUM("+avilableUnits+")";
    String unit_application_rate = "(refills::FLOAT * active_ingredient_per_can) / ("+sprayedUnits+" * unitarea)";
    String unit_application_ratio = "(("+unit_application_rate+") / standard_application_rate)";

    
    AbstractSpray.setSelectabeSQL(valueQuery, "sprayedunits", sprayedUnits);
    AbstractSpray.setSelectabeSQL(valueQuery, "unit_unsprayed" , unsprayedUnits);
    
    AbstractSpray.setSelectabeSQL(valueQuery, "unit_application_rate", "SUM(" + unit_application_rate + ")");
    AbstractSpray.setSelectabeSQL(valueQuery, "unit_application_rate_mg",  "1000.0 *" +"SUM(" + unit_application_rate + ")");
    AbstractSpray.setSelectabeSQL(valueQuery, "unit_application_ratio", "SUM("+sprayedUnits+"*"+unit_application_ratio+") / SUM("+sprayedUnits+")");
    AbstractSpray.setSelectabeSQL(valueQuery, "unit_operational_coverage", unit_operational_coverage );
    
    AbstractSpray.setSelectabeSQL(valueQuery, "calculated_rooms_sprayed" , "(" + unit_operational_coverage+") * SUM(rooms)");
    AbstractSpray.setSelectabeSQL(valueQuery, "calculated_structures_sprayed" ,"(" +  unit_operational_coverage+") * SUM(structures)");
    AbstractSpray.setSelectabeSQL(valueQuery, "calculated_households_sprayed" ,"(" + unit_operational_coverage+") * SUM(households)");

    
    return valueQuery;
  }
  
  public static void setSelectabeSQL(ValueQuery valueQuery ,String ref, String sql)
  {
    try
    {
      SelectableSQL calc = (SelectableSQL) valueQuery.getSelectableRef(ref);
      calc.setSQL(sql);
    }
    catch (QueryException e)
    {
    }
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
  }
  

 
}
