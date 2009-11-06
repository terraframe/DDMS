package dss.vector.solutions.irs;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONException;
import org.json.JSONObject;

import com.terraframe.mojo.business.rbac.Authenticate;
import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.dataaccess.database.Database;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.GeneratedEntityQuery;
import com.terraframe.mojo.query.InnerJoinEq;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.Selectable;
import com.terraframe.mojo.query.SelectableSQL;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.query.ValueQueryCSVExporter;
import com.terraframe.mojo.query.ValueQueryExcelExporter;

import dss.vector.solutions.ontology.AllPathsQuery;
import dss.vector.solutions.query.MapUtil;
import dss.vector.solutions.query.NoThematicLayerException;
import dss.vector.solutions.query.SavedSearch;
import dss.vector.solutions.query.SavedSearchRequiredException;
import dss.vector.solutions.query.ThematicLayer;
import dss.vector.solutions.util.QueryUtil;

public abstract class AbstractSpray extends AbstractSprayBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240597926952L;

  public AbstractSpray()
  {
    super();
  }

  @Override
  public void delete()
  {
    SprayData data = this.getSprayData();

    super.delete();

    try
    {
      data.delete();
    }
    catch (Exception e)
    {
      // The spray data is still being referenced by some other spray so do not
      // delete it
    }
  }

  public abstract SprayStatusView getSprayStatusView();

  public void populateView(AbstractSprayView view)
  {
    SprayData data = this.getSprayData();
    view.setBrand(data.getBrand());
    view.setGeoEntity(data.getGeoEntity());
    view.setSprayDate(data.getSprayDate());
    view.setSurfaceType(data.getSurfaceType());

    view.clearSprayMethod();

    for (SprayMethod method : data.getSprayMethod())
    {
      view.addSprayMethod(method);
    }

    view.setSprayData(data);
    view.setSprayId(this.getId());
  }

  /**
   * Takes in an XML string and returns a ValueQuery representing the structured
   * query in the XML.
   * 
   * @param xml
   * @return
   */
  @Authenticate
  public static ValueQuery xmlToValueQuery(String xml, String config, boolean includeGeometry, ThematicLayer thematicLayer)
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
    Map<String, GeneratedEntityQuery> queryMap = QueryUtil.joinQueryWithGeoEntities(queryFactory, valueQuery, xml, queryConfig, thematicLayer, includeGeometry, SprayData.CLASS, SprayData.GEOENTITY);

    SprayStatusQuery sprayStatusQuery = (SprayStatusQuery) queryMap.get(SprayStatus.CLASS);

    AbstractSprayQuery abstractSprayQuery = (AbstractSprayQuery) queryMap.get(AbstractSpray.CLASS);
    if (abstractSprayQuery != null)
    {
      valueQuery.WHERE(sprayStatusQuery.getSpray().EQ(abstractSprayQuery));
    }

    SprayDataQuery sprayDataQuery = (SprayDataQuery) queryMap.get(SprayData.CLASS);
    if (sprayDataQuery != null)
    {
      valueQuery.WHERE(abstractSprayQuery.getSprayData().EQ(sprayDataQuery));
    }

    InsecticideBrandQuery insecticideQuery = (InsecticideBrandQuery) queryMap.get(InsecticideBrand.CLASS);
    if (abstractSprayQuery != null)
    {
      valueQuery.WHERE(sprayDataQuery.getBrand().EQ(insecticideQuery));
    }

    ActorSprayQuery actorSprayQuery = (ActorSprayQuery) queryMap.get(ActorSpray.CLASS);
    if (actorSprayQuery != null)
    {
      valueQuery.WHERE(abstractSprayQuery.getId().EQ(actorSprayQuery.getId()));
    }

    String viewName = ThematicLayer.GEO_VIEW_PREFIX + System.currentTimeMillis();
    ResourceTarget.createDatabaseView(viewName);
    
    for(Entry<String, GeneratedEntityQuery> e : queryMap.entrySet()) {
      if (e.getValue() instanceof AllPathsQuery)
      {
        String key = e.getKey();
        AllPathsQuery allPathsQuery = (AllPathsQuery) e.getValue();
        
        int index1 = key.indexOf("__");
        int index2 = key.lastIndexOf("__");
        String attrib = key.substring(0, index1);
        String klass = key.substring(index1+2, index2).replace("_", ".");
        String attrib2 = key.substring(index2+2,key.length());
        Selectable term  = valueQuery.getSelectable(attrib2);
        
        
        valueQuery.WHERE(new InnerJoinEq("id", term.getDefiningTableName(), term.getDefiningTableAlias(), "childTerm", allPathsQuery.getMdClassIF().getTableName(), allPathsQuery.getTableAlias()));
      }
    }

    String coverageCalculationsView = "spray_data_view";
    String sprayCaluclationsSQL = "(" + SprayStatus.getTempTableSQL() + ")";
    valueQuery.FROM(sprayCaluclationsSQL, coverageCalculationsView);
    valueQuery.WHERE(new InnerJoinEq("id", sprayStatusQuery.getMdClassIF().getTableName(), sprayStatusQuery.getTableAlias(), "id", sprayCaluclationsSQL, coverageCalculationsView));

    String unionView = "all_levels_spray_view";
    String unionSQL = "(" + AbstractSpray.getSubquerySql(viewName) + ")";
    valueQuery.FROM(unionSQL, unionView);
    valueQuery.WHERE(new InnerJoinEq("id", sprayStatusQuery.getMdClassIF().getTableName(), sprayStatusQuery.getTableAlias(), "id", unionSQL, unionView));

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

    // set all the spray selectable sql to match up with the temp table columns
    for (Selectable s : Arrays.asList(valueQuery.getSelectables()))
    {
      if (s instanceof SelectableSQL)
      {
        ( (SelectableSQL) s ).setSQL(s.getUserDefinedAlias());
      }
    }

    valueQuery = QueryUtil.setQueryDates(xml, valueQuery, SprayData.SPRAYDATE);
    valueQuery = QueryUtil.setQueryRatio(xml, valueQuery, "COUNT(*)");
    return valueQuery;
  }

  /**
   * Queries for Mosquitos.
   * 
   * @param xml
   */
  @Transaction
  @Authenticate
  public static com.terraframe.mojo.query.ValueQuery queryIRS(String queryXML, String config, String sortBy, Boolean ascending, Integer pageNumber, Integer pageSize)
  {
    ValueQuery valueQuery = xmlToValueQuery(queryXML, config, false, null);

    valueQuery.restrictRows(pageSize, pageNumber);

    System.out.println(valueQuery.getSQL());

    return valueQuery;
  }

  @Transaction
  public static String mapQuery(String xml, String config, String[] universalLayers, String savedSearchId)
  {
    if (savedSearchId == null || savedSearchId.trim().length() == 0)
    {
      String error = "Cannot map a query without a current SavedSearch instance.";
      SavedSearchRequiredException ex = new SavedSearchRequiredException(error);
      throw ex;
    }

    SavedSearch search = SavedSearch.get(savedSearchId);
    ThematicLayer thematicLayer = search.getThematicLayer();

    if (thematicLayer == null || thematicLayer.getGeoHierarchy() == null)
    {
      String error = "Cannot create a map for search [" + search.getQueryName() + "] without having selected a thematic layer.";
      NoThematicLayerException ex = new NoThematicLayerException(error);
      throw ex;
    }

    // Update ThematicLayer if the thematic layer type has changed or
    // if one has not yet been defined.
    String thematicLayerType = thematicLayer.getGeoHierarchy().getGeoEntityClass().definesType();
    if (thematicLayer.getGeometryStyle() == null || !thematicLayer.getGeoHierarchy().getQualifiedType().equals(thematicLayerType))
    {
      thematicLayer.changeLayerType(thematicLayerType);
    }

    ValueQuery query = xmlToValueQuery(xml, config, true, thematicLayer);

    System.out.println(query.getSQL());

    String layers = MapUtil.generateLayers(universalLayers, query, search, thematicLayer);

    return layers;
  }

  @Transaction
  public static InputStream exportQueryToExcel(String queryXML, String config, String savedSearchId)
  {
    if (savedSearchId == null || savedSearchId.trim().length() == 0)
    {
      String error = "Cannot export to Excel without a current SavedSearch instance.";
      SavedSearchRequiredException ex = new SavedSearchRequiredException(error);
      throw ex;
    }

    SavedSearch search = SavedSearch.get(savedSearchId);

    ValueQuery query = xmlToValueQuery(queryXML, config, false, null);

    ValueQueryExcelExporter exporter = new ValueQueryExcelExporter(query, search.getQueryName());
    return exporter.exportStream();
  }

  @Transaction
  public static InputStream exportQueryToCSV(String queryXML, String config, String savedSearchId)
  {
    if (savedSearchId == null || savedSearchId.trim().length() == 0)
    {
      String error = "Cannot export to CSV without a current SavedSearch instance.";
      SavedSearchRequiredException ex = new SavedSearchRequiredException(error);
      throw ex;
    }

    ValueQuery query = xmlToValueQuery(queryXML, config, false, null);

    ValueQueryCSVExporter exporter = new ValueQueryCSVExporter(query);
    return exporter.exportStream();
  }

  public static void createTempTable(String tableName, String viewName)
  {
    // InsecticideBrand.createTempTable(insecticideTable);

    String sql = "DROP TABLE IF EXISTS " + tableName + ";\n";
    sql += "CREATE TEMP TABLE " + tableName + " AS \n";
    sql += AbstractSpray.getSubquerySql(viewName);
    sql += ";\n";
    // System.out.println(sql);
    Database.parseAndExecute(sql);
  }

  public static String getSubquerySql(String viewName)
  {
    String sql = "";
    sql += OperatorSpray.getTempTableSQL(viewName);
    sql += "\n UNION \n";
    sql += TeamSpray.getTempTableSQL(viewName);
    sql += "\n UNION \n";
    sql += ZoneSpray.getTempTableSQL(viewName);
    sql += "\n";

    return sql;
  }

}
