package dss.vector.solutions.irs;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import com.terraframe.mojo.business.rbac.Authenticate;
import com.terraframe.mojo.dataaccess.database.Database;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.query.ValueQueryCSVExporter;
import com.terraframe.mojo.query.ValueQueryExcelExporter;

import dss.vector.solutions.query.Layer;
import dss.vector.solutions.query.SavedSearch;
import dss.vector.solutions.query.SavedSearchRequiredException;

public class ZoneSpray extends ZoneSprayBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240860676906L;

  public ZoneSpray()
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

    return this.getClassDisplayLabel();
  }

  @Override
  protected String buildKey()
  {
    if (this.getBrand() != null && this.getGeoEntity() != null && this.getSprayDate() != null && this.getSprayMethodForIndex() != null)
    {
      DateFormat format = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT);
      String dateFormat = format.format(this.getSprayDate());
      String methodName = this.getSprayMethodForIndex();

      return this.getBrand().getKey() + "." + this.getGeoEntity().getGeoId() + "." + dateFormat + "." + methodName;
    }

    return this.getId();
  }

  @Override
  public void apply()
  {
    List<SprayMethod> _sprayMethod = this.getSprayMethod();

    if (_sprayMethod.size() > 0)
    {
      this.setSprayMethodForIndex(_sprayMethod.get(0).getEnumName());
    }
    
    this.setGeoEntityForIndex(this.getGeoEntity());
    this.setBrandForIndex(this.getBrand());
    this.setSprayDateForIndex(this.getSprayDate());

    super.apply();
  }

  public ZoneSprayView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  @Override
  public ZoneSprayView lockView()
  {
    this.lock();

    return this.getView();
  }

  public ZoneSprayView getView()
  {
    ZoneSprayView view = new ZoneSprayView();

    view.populateView(this);

    return view;
  }

  public static void createTempTable(String tableName, String viewName)
  {
    String sql = "DROP TABLE IF EXISTS " + tableName + ";\n";
    sql += "CREATE TEMP TABLE " + tableName + " AS ";
    sql += ZoneSpray.getTempTableSQL(viewName) + ";\n";
    System.out.println(sql);
    Database.parseAndExecute(sql);
  }

  public static String getTempTableSQL(String viewName)
  {
    // String select = "SELECT spraystatus.id,\n";
    //
    // select += "'3' AS aggregation_level,\n";
    //    
    // // operator stuff
    // select += "'' AS household_id,\n";
    // select += "'' AS structure_id,\n";
    // select += "'' AS sprayoperator,\n";
    // select += "'' AS sprayoperator_defaultLocale,\n";
    // select += "NULL AS operator_week,\n";
    // select += "NULL AS operator_target,\n";
    //    
    // // team stuff
    // select += "sprayteam." + SprayTeam.ID + " AS sprayteam,\n";
    // select += "sprayteam." + SprayTeam.TEAMID +
    // " AS sprayteam_defaultLocale,\n";
    // select += "actorspray." + TeamSprayStatus.TEAMLEADER +
    // " AS sprayleader,\n";
    // select +=
    // "sprayleader.operatorid || ' - ' || person.firstname || ' ' || person.lastname AS sprayleader_defaultLocale,\n";
    // select += "actorspray." + ActorSpray.TEAMSPRAYWEEK + " AS team_week,\n";
    // select += "actorspray." + ActorSpray.TARGET + " AS team_target,\n";
    //
    // // zone stuff
    // select += "zonespray." + ZoneSpray.SUPERVISOR +" AS zone_supervisor,\n";
    // select +=
    // "(SELECT person.firstname || ' ' || person.lastname FROM "+MdBusiness.getMdBusiness(Person.CLASS).getTableName()
    // + " person " + " WHERE zonespray." + ZoneSpray.SUPERVISOR +"  = person."+
    // Person.SUPERVISORDELEGATE+") AS zone_supervisor_defaultLocale,\n";
    // select += "zonespray." + ZoneSpray.SPRAYWEEK + " AS zone_week,\n";
    // select += "zonespray." + ZoneSpray.TARGET + " AS zone_target,\n";
    // // target stuff
    // // select += "sprayseason.id  AS spray_season,\n";
    // select += "NULL  AS planed_operator_target,\n";
    // select += "(SELECT weekly_target FROM " + viewName +
    // " AS  spray_target_view WHERE " +
    // "spray_target_view.target_id = sprayteam.id \n" +
    // "AND spray_target_view.season_id = sprayseason.id \n" +
    // "AND spray_target_view.target_week = actorspray." +
    // ActorSpray.TEAMSPRAYWEEK
    // + ") AS planed_team_target,\n";
    // select += "(SELECT weekly_target FROM " + viewName +
    // " AS  spray_target_view WHERE " +
    // "spray_target_view.target_id = spraydata.geoentity  \n" +
    // "AND spray_target_view.season_id = sprayseason.id \n" +
    // "AND spray_target_view.target_week = EXTRACT(WEEK FROM spraydata.spraydate)"
    // + ") AS planed_area_target,\n";
    //
    // String from = " FROM ";
    // from += MdBusiness.getMdBusiness(ZoneSpray.CLASS).getTableName() +
    // " AS zonespray,\n";
    // from += MdBusiness.getMdBusiness(TeamSpray.CLASS).getTableName() +
    // " AS teamspray,\n";
    // from += MdBusiness.getMdBusiness(SprayTeam.CLASS).getTableName() +
    // " AS sprayteam,\n";
    // from += "inteam AS inteam,\n";
    // from += MdBusiness.getMdBusiness(ActorSpray.CLASS).getTableName() +
    // " AS actorspray,\n";
    // from += MdBusiness.getMdBusiness(AbstractSpray.CLASS).getTableName() +
    // " AS abstractspray_team,\n";
    // from += MdBusiness.getMdBusiness(AbstractSpray.CLASS).getTableName() +
    // " AS abstractspray_zone,\n";
    // from += MdBusiness.getMdBusiness(SprayStatus.CLASS).getTableName() +
    // " AS spraystatus,\n";
    // from += MdBusiness.getMdBusiness(SprayOperator.CLASS).getTableName() +
    // " AS sprayleader,\n";
    // from += MdBusiness.getMdBusiness(Person.CLASS).getTableName() +
    // " AS person,\n";
    // from += MdBusiness.getMdBusiness(SprayData.CLASS).getTableName() +
    // " AS spraydata \n";
    // from += " LEFT JOIN ";
    // from += MdBusiness.getMdBusiness(MalariaSeason.CLASS).getTableName() +
    // " AS sprayseason ";
    // from +=
    // "ON spraydata.spraydate BETWEEN sprayseason.startdate AND sprayseason.enddate,\n";
    //
    // String where = "";
    // // join the team spray to the operator spray
    // where +=
    // "AND abstractspray_team.spraydata = abstractspray_zone.spraydata \n";
    //
    // // join the teamspray to the team
    // where += "AND teamspray.sprayteam = sprayteam.id \n";
    // where += "AND teamspray.id = actorspray.id  \n";
    // where += "AND sprayteam.id = inteam.parent_id  \n";
    // where += "AND sprayleader.id = inteam.child_id \n";
    //
    // // join abstractspray_team to the team spray
    // where += "AND teamspray.id = abstractspray_team.id \n";
    // where += "AND teamspray.id = actorspray.id \n";
    // where += "AND spraydata.id = abstractspray_team.spraydata \n";
    //
    // // join abstractspray_zone
    // where += "AND abstractspray_zone.id = zonespray.id \n";
    // where += "AND spraystatus.spray = teamspray.id \n";
    //
    // // join the people
    // where += "AND actorspray.teamleader = sprayleader.id \n";
    // where += "AND person.id = sprayleader.person \n";
    //
    // select = select.substring(0, select.length() - 2);
    // from = from.substring(0, from.length() - 2);
    // where = "WHERE " + where.substring(3, where.length() - 2);
    //
    // return select + "\n" + from + "\n" + where;

    return "";
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
    // JSONObject queryConfig;
    // try
    // {
    // queryConfig = new JSONObject(config);
    // }
    // catch (JSONException e1)
    // {
    // throw new ProgrammingErrorException(e1);
    // }
    //    
    QueryFactory queryFactory = new QueryFactory();

    ValueQuery valueQuery = new ValueQuery(queryFactory);
    //
    // // IMPORTANT: Required call for all query screens.
    // Map<String, GeneratedEntityQuery> queryMap =
    // QueryUtil.joinQueryWithGeoEntities(queryFactory, valueQuery, xml,
    // queryConfig, layer);
    //
    // SprayStatusQuery sprayStatusQuery = (SprayStatusQuery)
    // queryMap.get(SprayStatus.CLASS);
    //
    // AbstractSprayQuery abstractSprayQuery = (AbstractSprayQuery)
    // queryMap.get(AbstractSpray.CLASS);
    // if (abstractSprayQuery != null)
    // {
    // valueQuery.WHERE(sprayStatusQuery.getSpray().EQ(abstractSprayQuery));
    // }
    //
    // SprayDataQuery sprayDataQuery = (SprayDataQuery)
    // queryMap.get(SprayData.CLASS);
    // if (sprayDataQuery != null)
    // {
    // valueQuery.WHERE(abstractSprayQuery.getSprayData().EQ(sprayDataQuery));
    // }
    //
    // InsecticideBrandQuery insecticideQuery = (InsecticideBrandQuery)
    // queryMap.get(InsecticideBrand.CLASS);
    // if (abstractSprayQuery != null)
    // {
    // valueQuery.WHERE(sprayDataQuery.getBrand().EQ(insecticideQuery));
    // }
    //
    // ActorSprayQuery actorSprayQuery = (ActorSprayQuery)
    // queryMap.get(ActorSpray.CLASS);
    // if (actorSprayQuery != null)
    // {
    // valueQuery.WHERE(abstractSprayQuery.getId().EQ(actorSprayQuery.getId()));
    // }
    //
    // String viewName = Layer.GEO_VIEW_PREFIX + System.currentTimeMillis();
    // ResourceTarget.createDatabaseView(viewName);
    //    
    // for(Entry<String, GeneratedEntityQuery> e : queryMap.entrySet()) {
    // if (e.getValue() instanceof AllPathsQuery)
    // {
    // String key = e.getKey();
    // AllPathsQuery allPathsQuery = (AllPathsQuery) e.getValue();
    //        
    // // int index1 = key.indexOf("__");
    // int index2 = key.lastIndexOf("__");
    // // String attrib = key.substring(0, index1);
    // // String klass = key.substring(index1+2, index2).replace("_", ".");
    // String attrib2 = key.substring(index2+2,key.length());
    // Selectable term = valueQuery.getSelectable(attrib2);
    //        
    //        
    // valueQuery.WHERE(new InnerJoinEq("id", term.getDefiningTableName(),
    // term.getDefiningTableAlias(), "childTerm",
    // allPathsQuery.getMdClassIF().getTableName(),
    // allPathsQuery.getTableAlias()));
    // }
    // }
    //
    // String coverageCalculationsView = "spray_data_view";
    // String sprayCaluclationsSQL = "(" + SprayStatus.getTempTableSQL() + ")";
    // valueQuery.FROM(sprayCaluclationsSQL, coverageCalculationsView);
    // valueQuery.WHERE(new InnerJoinEq("id",
    // sprayStatusQuery.getMdClassIF().getTableName(),
    // sprayStatusQuery.getTableAlias(), "id", sprayCaluclationsSQL,
    // coverageCalculationsView));
    //
    // String unionView = "all_levels_spray_view";
    // String unionSQL = "(" + AbstractSpray.getSubquerySql(viewName) + ")";
    // valueQuery.FROM(unionSQL, unionView);
    // valueQuery.WHERE(new InnerJoinEq("id",
    // sprayStatusQuery.getMdClassIF().getTableName(),
    // sprayStatusQuery.getTableAlias(), "id", unionSQL, unionView));
    //
    // /*
    // * String targetView = "unit_totals_view"; String targetViewSQL = "(" +
    // * ResourceTarget.getTempTableSQL() +")"; RawLeftJoinEq lj = new
    // * RawLeftJoinEq("targetable_id", unionSQL, unionView, "target_id",
    // * targetViewSQL, targetView);
    // *
    // * lj.setSql(unionView+".targetable_id = "+targetView+".target_id AND " +
    // * unionView+".spray_season = "+targetView+".season_id AND " +
    // * unionView+".spray_week = "+targetView+".target_week");
    // * valueQuery.WHERE(lj);
    // */
    //
    // // set all the spray selectable sql to match up with the temp table
    // columns
    // for (Selectable s : Arrays.asList(valueQuery.getSelectables()))
    // {
    // if (s instanceof SelectableSQL)
    // {
    // ( (SelectableSQL) s ).setSQL(s.getUserDefinedAlias());
    // }
    // }
    //
    // valueQuery = QueryUtil.setQueryDates(xml, valueQuery,
    // SprayData.SPRAYDATE);
    // valueQuery = QueryUtil.setQueryRatio(xml, valueQuery, "COUNT(*)");
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
    ValueQuery valueQuery = xmlToValueQuery(queryXML, config, null);

    valueQuery.restrictRows(pageSize, pageNumber);

    return valueQuery;
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

    ValueQuery query = xmlToValueQuery(queryXML, config, null);

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

    ValueQuery query = xmlToValueQuery(queryXML, config, null);

    ValueQueryCSVExporter exporter = new ValueQueryCSVExporter(query);
    return exporter.exportStream();
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

  public static String getUnitTotalsSQL()
  {
    /*
     * switch (targetUnit) { case HOUSEHOLD: select +=
     * "(spraystatus.sprayedrooms / spraystatus.rooms) AS operational_coverage,\n"
     * ; break; case STRUCTURE: select +=
     * "(spraystatus.sprayedrooms / spraystatus.rooms) AS operational_coverage,\n"
     * ; break; case ROOM: select +=
     * "(spraystatus.sprayedrooms / spraystatus.rooms) AS operational_coverage,\n"
     * ; break; }
     */

    String select = "SELECT actorspray.id AS id,\n";
    // we use NULLIF before the CAST because float zero != integer zero
    select += "(SELECT CAST(NULLIF(SUM(sprayedhouseholds),0) AS float) FROM  spraystatus WHERE  spraystatus.spray = actorspray.id) as household_total,\n";
    select += "(SELECT CAST(NULLIF(SUM(sprayedstructures),0) AS float) FROM  spraystatus WHERE  spraystatus.spray = actorspray.id) as structure_total,\n";
    select += "(SELECT CAST(NULLIF(SUM(sprayedrooms),0) AS float)      FROM  spraystatus WHERE  spraystatus.spray = actorspray.id) as room_total,\n";

    String from = "FROM ";
    // from += MdBusiness.getMdBusiness(ActorSpray.CLASS).getTableName() +
    // " AS actorspray,\n";

    select = select.substring(0, select.length() - 2);
    from = from.substring(0, from.length() - 2);

    return select + "\n" + from + "\n";
  }
}
