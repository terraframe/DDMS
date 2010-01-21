package dss.vector.solutions.irs;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.query.GeneratedEntityQuery;
import com.terraframe.mojo.query.QueryFactory;
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

//    String viewName = Layer.GEO_VIEW_PREFIX + System.currentTimeMillis();
//    ResourceTarget.createDatabaseView(viewName);
//
//    String coverageCalculationsView = "spray_data_view";
//    String sprayCaluclationsSQL = "(" + SprayStatus.getTempTableSQL() + ")";
//    valueQuery.FROM(sprayCaluclationsSQL, coverageCalculationsView);
//    valueQuery.WHERE(new InnerJoinEq("id", sprayStatusQuery.getMdClassIF().getTableName(), sprayStatusQuery.getTableAlias(), "id", sprayCaluclationsSQL, coverageCalculationsView));
//
//    String unionView = "all_levels_spray_view";
//    String unionSQL = "(" + AbstractSpray.getSubquerySql(viewName) + ")";
//    valueQuery.FROM(unionSQL, unionView);
//    valueQuery.WHERE(new InnerJoinEq("id", sprayStatusQuery.getMdClassIF().getTableName(), sprayStatusQuery.getTableAlias(), "id", unionSQL, unionView));

    /*
     * String targetView = "unit_totals_view"; String targetViewSQL = "(" +
     * ResourceTarget.getTempTableSQL() +")"; RawLeftJoinEq lj = new
     * RawLeftJoinEq("targetable_id", unionSQL, unionView, "target_id",
     * targetViewSQL, targetView);
     * 
     * lj.setSql(unionView+".targetable_id = "+targetView+".target_id AND " +
     * unionView+".spray_season = "+targetView+".season_id AND " +
     * unionView+".spray_week = "+targetView+".target_week");
     * valueQuery.WHERE(lj);
     */

    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap);

    QueryUtil.setQueryRatio(xml, valueQuery, "COUNT(*)");

    QueryUtil.setTermRestrictions(valueQuery, queryMap);

    QueryUtil.setNumericRestrictions(valueQuery, queryConfig);

    
    return valueQuery;
  }
}
