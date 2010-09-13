package dss.vector.solutions.irs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.RelationshipDAOIF;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.OR;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableSQL;
import com.runwaysdk.query.SelectableSQLCharacter;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.system.EnumerationMaster;
import com.runwaysdk.system.metadata.MetadataDisplayLabel;

import dss.vector.solutions.Property;
import dss.vector.solutions.PropertyInfo;
import dss.vector.solutions.general.EpiWeek;
import dss.vector.solutions.geo.AllPathsQuery;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.query.IncidencePopulationException;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.util.QueryUtil;

public class IRSQuery implements Reloadable
{
  private static final String   TEAM_TARGET_DIVERGENCE     = "team_target_divergence";

  private static final String   TEAM_PLANNED_COVERAGE      = "team_planned_coverage";

  private static final String   TEAM_PLANNED_TARGET        = "team_planned_target";

  private static final String   TEAM_TARGETED_COVERAGE     = "team_targeted_coverage";

  private static final String   TEAM_ACTUAL_TARGET         = "team_actual_target";

  private static final String   AREA_PLANNED_COVERAGE      = "area_planned_coverage";

  private static final String   AREA_PLANNED_TARGET        = "area_planned_target";

  private static final String   OPERATOR_TARGET_DIVERGENCE = "operator_target_divergence";

  private static final String   OPERATOR_ACTUAL_TARGET     = "operator_actual_target";

  private static final String   OPERATOR_TARGETED_COVERAGE = "operator_targeted_coverage";

  private static final String   OPERATOR_PLANNED_TARGET    = "operator_planned_target";

  private static final String   OPERATOR_PLANNED_COVERAGE  = "operator_planned_coverage";

  // The union of actual and planned targets must include the minimum set
  // required to make the query work so as to not include superfluous rows.
  private boolean               needsAreaPlanned;

  private boolean               needsAreaActual;

  private boolean               needsTeamsPlanned;

  private boolean               needsTeamsActual;

  private boolean               needsOperatorPlanned;

  private boolean               needsOperatorActual;

  /**
   * If this is true then all actual targets must be included in the query,
   * regardless of whether or not the individual flags for actual are set to
   * false (e.g., needsAreaActual).
   */
  private boolean               needsAllActual;

  /**
   * Set to true if any of the planned targets are included in the query.
   */
  private boolean               hasPlannedTargets;

  private boolean               hasLeftJoinVQ;

  private boolean               hasSprayEnumOrTerm;

  private ValueQuery            irsVQ;

  private ValueQuery            sprayVQ;

  private JSONObject            queryConfig;

  private String                xml;

  public static final String    RESOURCE_TARGET_VIEW       = "resourceTargetView";

  public static final String    GEO_TARGET_VIEW            = "geoTargetView";
  
  public static final String    SPRAY_VIEW                 = "sprayView";

  public static final String    INSECTICIDE_VIEW           = "insecticideView";

  public static final String    TARGET_WEEK                = "target_week";

  public static final String    WEEKLY_TARGET              = "weekly_target";

  private AbstractSprayQuery    abstractSprayQuery;

  private InsecticideBrandQuery insecticideQuery;

  private String                sprayViewAlias;

  private String              idCol;

  private String                sprayedUnits;

  private Layer                 layer;

  private ValueQuery            insecticideVQ;

  private String                targeter;
  private String                geoEntity;
  
  private int startDay;
  
  private boolean hasGeoSelection;
  
  public IRSQuery(String config, String xml, Layer layer)
  {
    hasGeoSelection = false;
    
    startDay = Property.getInt(PropertyInfo.EPI_WEEK_PACKAGE, PropertyInfo.EPI_START_DAY);
    
    try
    {
      this.queryConfig = new JSONObject(config);
    }
    catch (JSONException e1)
    {
      throw new ProgrammingErrorException(e1);
    }
    QueryFactory queryFactory = new QueryFactory();

    this.sprayVQ = new ValueQuery(queryFactory);
    this.irsVQ = new ValueQuery(queryFactory);
    this.insecticideVQ = new ValueQuery(queryFactory);

    this.hasLeftJoinVQ = false;
    this.hasSprayEnumOrTerm = false;

    this.needsAreaPlanned = false;
    this.needsAreaActual = false;
    this.needsOperatorPlanned = false;
    this.needsOperatorActual = false;
    this.needsTeamsPlanned = false;
    this.needsTeamsActual = false;
    this.needsAllActual = false;

    this.hasPlannedTargets = false;

    this.xml = xml;

    this.abstractSprayQuery = null;
    this.insecticideQuery = null;

    this.sprayViewAlias = null;

    this.sprayedUnits = null;

    this.idCol = QueryUtil.getIdColumn();
    this.targeter = QueryUtil.getColumnName(ResourceTarget.getTargeterMd());
    this.geoEntity = QueryUtil.getColumnName(ZoneSpray.getGeoEntityMd());
  }
  
  public String getGeoEntity()
  {
    return this.geoEntity;
  }
  
  public String getTargeter()
  {
    return this.targeter;
  }
  
  int getStartDay()
  {
    return startDay;
  }

  public String getSprayViewAlias()
  {
    return this.sprayViewAlias;
  }

  public boolean hasPlannedTargets()
  {
    return this.hasPlannedTargets;
  }

  public ValueQuery getInsecticideVQ()
  {
    return this.insecticideVQ;
  }

  private void addFROM()
  {

  }

  private void filterSelectables()
  {
    List<String> spraySQLs = Arrays.asList(new String[] { AbstractSpray.SPRAYMETHOD + "_spray",
        AbstractSpray.SURFACETYPE + "_spray" });

    String insecticideTable = MdEntityDAO.getMdEntityDAO(InsecticideBrand.CLASS).getTableName();
    List<String> insecticideSQLs = new LinkedList<String>();

    // insecticide calculations
    insecticideSQLs.addAll(Arrays.asList(new String[] { "nozzle_defaultLocale", "nozzle_ratio",
        "active_ingredient_per_can", "standard_application_rate", "standard_application_rate_mg",
        "units_per_can" }));

    // insecticide terms and enums
    for (String termAttr : Term.getTermAttributes(InsecticideBrand.CLASS))
    {
      insecticideSQLs.add(termAttr + QueryUtil.DISPLAY_LABEL_SUFFIX);
    }

    for (String enumAttr : QueryUtil.getEnumAttributes(InsecticideBrand.CLASS))
    {
      insecticideSQLs.add(enumAttr + QueryUtil.DISPLAY_LABEL_SUFFIX);
    }

    // remove insecticide selectables from the irsQuery
    List<Selectable> irsSels = new LinkedList<Selectable>();
    List<Selectable> insecticideSels = new LinkedList<Selectable>();
    List<Selectable> spraySels = new LinkedList<Selectable>();
    for (Selectable sel : irsVQ.getSelectableRefs())
    {
      String alias = sel.getUserDefinedAlias();
      String name = sel._getAttributeName();

      if (sel.getDefiningTableName().equals(insecticideTable) || insecticideSQLs.contains(alias)
          || insecticideSQLs.contains(name))
      {
        Selectable iSel = insecticideVQ.getSelectableRef(alias);
        iSel.setColumnAlias(iSel.getColumnAlias() + "_i"); // namespace to
        // avoid
        // a bug in grouping
        insecticideSels.add(iSel);
      }
      else if (spraySQLs.contains(alias))
      {
        Selectable sSel = sprayVQ.getSelectableRef(alias);
        sSel.setColumnAlias(sSel.getColumnAlias() + "_s"); // namespace to
        // avoid
        // a bug in grouping
        spraySels.add(sSel);
      }
      else
      {
        irsSels.add(irsVQ.getSelectableRef(alias));
      }
    }

    irsVQ.clearSelectClause();

    insecticideVQ = new ValueQuery(irsVQ.getQueryFactory());
    sprayVQ = new ValueQuery(irsVQ.getQueryFactory());

    irsVQ.SELECT(irsSels.toArray(new Selectable[irsSels.size()]));

    if (insecticideQuery != null)
    {
      insecticideVQ.SELECT(insecticideSels.toArray(new Selectable[insecticideSels.size()]));
      insecticideVQ.SELECT(insecticideQuery.getId(InsecticideBrand.ID));

      for (Selectable sel : insecticideSels)
      {
        irsVQ.SELECT(irsVQ.aSQLCharacter(sel.getColumnAlias(), insecticideQuery.getTableAlias() + "."
            + sel.getColumnAlias(), sel.getUserDefinedAlias()));
      }
    }

    if (spraySels.size() > 0)
    {
      this.hasSprayEnumOrTerm = true;

      sprayVQ.SELECT(spraySels.toArray(new Selectable[spraySels.size()]));
      sprayVQ.SELECT(abstractSprayQuery.getId(AbstractSpray.ID));

      for (Selectable sel : spraySels)
      {
        irsVQ.SELECT(irsVQ.aSQLCharacter(sel.getColumnAlias(), abstractSprayQuery.getTableAlias() + "."
            + sel.getColumnAlias(), sel.getUserDefinedAlias()));
      }
    }
  }
  
  /**
   * Detects if this query contains any geo entity restrictions or universal columns.
   * 
   * @param queryMap
   */
  private void checkForGeoSelection(Map<String, GeneratedEntityQuery> queryMap)
  {
    // look for columns
    try
    {
      JSONObject selectedUniMap = queryConfig.getJSONObject(QueryConstants.SELECTED_UNIVERSALS);
      Iterator<?> keys = selectedUniMap.keys();
      while (keys.hasNext())
      {
        String attributeKey = (String) keys.next();

        JSONArray universals = selectedUniMap.getJSONArray(attributeKey);
        if(universals.length() > 0)
        {
          hasGeoSelection = true;
          return;
        }
      }
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
    
    // look for restrictions
    for(GeneratedEntityQuery query : queryMap.values())
    {
      if(query instanceof AllPathsQuery)
      {
        hasGeoSelection = true;
        return;
      }
    }
  }

  /**
   * Populates the ValueQuery with the necessary selects, joins, and criteria to
   * make the IRS query work correctly. ORDER IS IMPORTANT. Do not change the
   * calls within this method unless you know what you are doing! There are many
   * boolean flags set within those calls that dictate how the query will
   * behave.
   */
  public ValueQuery populate()
  {
    QueryFactory queryFactory = this.irsVQ.getQueryFactory();

    // IMPORTANT: Required call for all query screens.
    Map<String, GeneratedEntityQuery> queryMap1 = QueryUtil.joinQueryWithGeoEntities(queryFactory,
        irsVQ, xml, queryConfig, layer);
    Map<String, GeneratedEntityQuery> queryMap2 = QueryUtil.joinQueryWithGeoEntities(queryFactory,
        insecticideVQ, xml, queryConfig, layer);
    Map<String, GeneratedEntityQuery> queryMap3 = QueryUtil.joinQueryWithGeoEntities(queryFactory,
        sprayVQ, xml, queryConfig, layer);

    checkForGeoSelection(queryMap1);
    
    this.sprayViewAlias = queryMap1.get(AbstractSpray.CLASS).getTableAlias();
    this.insecticideQuery = (InsecticideBrandQuery) queryMap2.get(InsecticideBrand.CLASS);
    this.abstractSprayQuery = (AbstractSprayQuery) queryMap3.get(AbstractSpray.CLASS);

    filterSelectables();

    if (insecticideQuery != null)
    {
      QueryUtil.joinEnumerationDisplayLabels(insecticideVQ, InsecticideBrand.CLASS, insecticideQuery);
      QueryUtil.joinTermAllpaths(insecticideVQ, InsecticideBrand.CLASS, insecticideQuery);
      QueryUtil.setTermRestrictions(insecticideVQ, queryMap1);
      QueryUtil.setNumericRestrictions(insecticideVQ, queryConfig);
    }

    String sprayDate = QueryUtil.getColumnName(AbstractSpray.getSprayDateMd());

    // Spray Date
    QueryUtil.setSelectabeSQL(irsVQ, AbstractSpray.SPRAYDATE, sprayViewAlias + "." + sprayDate);

    // Geo Entity
    if (irsVQ.hasSelectableRef(AbstractSpray.GEOENTITY))
    {
      SelectableSQLCharacter subGeo = (SelectableSQLCharacter) irsVQ
          .getSelectableRef(AbstractSpray.GEOENTITY);
      QueryUtil.subselectGeoDisplayLabels(subGeo, AbstractSpray.CLASS, AbstractSpray.GEOENTITY,
          sprayViewAlias + "." + idCol);
    }

    if (this.hasSprayEnumOrTerm)
    {
      QueryUtil.joinEnumerationDisplayLabels(sprayVQ, AbstractSpray.CLASS, abstractSprayQuery);
      QueryUtil.joinTermAllpaths(sprayVQ, AbstractSpray.CLASS, abstractSprayQuery);
      QueryUtil.setTermRestrictions(sprayVQ, queryMap3);
    }

    // setAbstractSprayAttributes();

    String avilableUnits = "(CASE WHEN spray_unit = 'ROOM' THEN rooms  WHEN spray_unit = 'STRUCTURE' THEN structures WHEN spray_unit = 'HOUSEHOLD' THEN households END )";
    sprayedUnits = "(CASE WHEN spray_unit = 'ROOM' THEN sprayedRooms  WHEN spray_unit = 'STRUCTURE' THEN sprayedStructures WHEN spray_unit = 'HOUSEHOLD' THEN sprayedHouseholds END )";
    String unsprayedUnits = "(CASE WHEN spray_unit = 'ROOM' THEN (room_unsprayed)  WHEN spray_unit = 'STRUCTURE' THEN (structure_unsprayed) WHEN spray_unit = 'HOUSEHOLD' THEN (household_unsprayed)  END )";
    String shareOfCans = "(CASE WHEN spray_unit = 'ROOM' THEN (sprayedrooms_share)  WHEN spray_unit = 'STRUCTURE' THEN (sprayedstructures_share) WHEN spray_unit = 'HOUSEHOLD' THEN (sprayedhouseholds_share)  END )";

    String unit_operational_coverage = "SUM(" + sprayedUnits + ")::float / nullif(SUM(" + avilableUnits
        + "),0)";

    String unit_application_rate = "SUM(refills::FLOAT * " + shareOfCans
        + " * active_ingredient_per_can) / nullif(SUM(" + sprayedUnits + " * unitarea),0)";
    String unit_application_ratio = "((" + unit_application_rate + ") / AVG(standard_application_rate))";

    QueryUtil.setSelectabeSQL(irsVQ, "sprayedunits", sprayedUnits);
    QueryUtil.setSelectabeSQL(irsVQ, "unit_unsprayed", unsprayedUnits);
    QueryUtil.setSelectabeSQL(irsVQ, "refills * " + shareOfCans, unsprayedUnits);

    QueryUtil.setSelectabeSQL(irsVQ, "unit_application_rate", "(" + unit_application_rate + ")");
    QueryUtil.setSelectabeSQL(irsVQ, "unit_application_rate_mg", "1000.0 *" + "("
        + unit_application_rate + ")");
    QueryUtil.setSelectabeSQL(irsVQ, "unit_application_ratio", unit_application_ratio);
    QueryUtil
        .setSelectabeSQL(irsVQ, "unit_operational_coverage", unit_operational_coverage + " * 100.0");

    QueryUtil.setSelectabeSQL(irsVQ, "calculated_rooms_sprayed", "(" + unit_operational_coverage
        + ") * SUM(rooms)");
    QueryUtil.setSelectabeSQL(irsVQ, "calculated_structures_sprayed", "(" + unit_operational_coverage
        + ") * SUM(structures)");
    QueryUtil.setSelectabeSQL(irsVQ, "calculated_households_sprayed", "(" + unit_operational_coverage
        + ") * SUM(households)");

    setTargetManagmentCalculations();

    // Note: setWithQueyrSQL() must go after setTargetManagementCalculations()
    // so the target management flags will be set correctly.
    setWithQuerySQL();

    QueryUtil.setTermRestrictions(irsVQ, queryMap1);

    QueryUtil.setNumericRestrictions(irsVQ, queryConfig);

    QueryUtil.setQueryDates(xml, irsVQ, queryConfig, queryMap1, true);

    addPlannedTargetDateCriteria();

    joinMainQueryTables();

    /* POST PROCESSING */
    if (this.hasPlannedTargets)
    {
      // Supplement the original date grouping to account for the planning
      // target union rows,
      // which don't have a date but an epi-week.
      if (irsVQ.hasSelectableRef(QueryUtil.DATEGROUP_EPIWEEK))
      {
        SelectableSQL sel = (SelectableSQL) irsVQ.getSelectableRef(QueryUtil.DATEGROUP_EPIWEEK);
        String original = sel.getSQL();

        String caseStmt = "CASE WHEN " + sprayViewAlias + "." + Alias.SPRAY_DATE + " IS NOT NULL THEN "
            + original + " ELSE " + sprayViewAlias + "." + Alias.TARGET_WEEK + " END";
        sel.setSQL(caseStmt);
      }

      // Set the other date groupings to null for the planning rows
      String[] groups = new String[] { QueryUtil.DATEGROUP_EPIYEAR, QueryUtil.DATEGROUP_MONTH,
          QueryUtil.DATEGROUP_QUARTER, QueryUtil.DATEGROUP_SEASON };

      for (String group : groups)
      {
        if (irsVQ.hasSelectableRef(group))
        {
          SelectableSQL sel = (SelectableSQL) irsVQ.getSelectableRef(group);
          String original = sel.getSQL();

          String caseStmt = "CASE WHEN " + sprayViewAlias + "." + Alias.SPRAY_DATE
              + " IS NOT NULL THEN " + original + " ELSE NULL END";
          sel.setSQL(caseStmt);
        }
      }
    }

    addFROM();

    return this.irsVQ;
  }

  private void addPlannedTargetDateCriteria()
  {
    int startDay = this.getStartDay();

    try
    {
      JSONObject dateObj = queryConfig.getJSONObject(QueryUtil.DATE_ATTRIBUTE);

      if (dateObj.has("start") && !dateObj.isNull("start") && !dateObj.getString("start").equals("null"))
      {
        String startValue = dateObj.getString("start");

        Condition or = OR.get(irsVQ.aSQLDate("epi_start_week",
            this.sprayViewAlias + "." + Alias.TARGET_WEEK).GE(
            irsVQ.aSQLDate("epi_start_val", "get_epiWeek_from_date('" + startValue + "'," + startDay
                + ")")), irsVQ.aSQLDate("start_date", this.sprayViewAlias + "." + Alias.SPRAY_DATE).GE(
            irsVQ.aSQLDate("start_val", "'" + startValue + "'")));

        irsVQ.AND(or);
      }
      if (dateObj.has("end") && !dateObj.isNull("end") && !dateObj.getString("start").equals("null"))
      {
        String endValue = dateObj.getString("end");

        Condition or = OR.get(
            irsVQ.aSQLDate("epi_end_week", this.sprayViewAlias + "." + Alias.TARGET_WEEK).LE(
                irsVQ.aSQLDate("epi_end_val", "get_epiWeek_from_date('" + endValue + "'," + startDay
                    + ")")), irsVQ.aSQLDate("end_date", this.sprayViewAlias + "." + Alias.SPRAY_DATE)
                .LE(irsVQ.aSQLDate("end_val", "'" + endValue + "'")));

        irsVQ.AND(or);
      }
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  /**
   * Joins the necessary tables to make the main query work (this also includes
   * setting the proper WHERE criteria on dates).
   */
  private void joinMainQueryTables()
  {
    String abstractSprayTable = MdEntityDAO.getMdEntityDAO(AbstractSpray.CLASS).getTableName();
    IRSSpoofJoin join = new IRSSpoofJoin(idCol, abstractSprayTable, this.sprayViewAlias, idCol,
        abstractSprayTable, this.sprayViewAlias);
    irsVQ.AND(join);

    StringBuffer str = new StringBuffer();
    String idCol = QueryUtil.getIdColumn();
    String diseaseCol = QueryUtil.getColumnName(InsecticideBrand.getDiseaseMd());

    String leftTable = IRSQuery.SPRAY_VIEW;
    String leftAlias = this.getSprayViewAlias();
    String insecticideView = IRSQuery.INSECTICIDE_VIEW;

    // TODO refactor this to use more of the query API instead of string
    // concatenation.
    // Push InsecticideView into a ValueQuery
    // String start = ( str.toString().trim().equals("FROM") ? "" : "," ) +
    // " \n";
    str.append("");
    str.append(leftTable + " " + leftAlias);

    if (insecticideQuery != null)
    {
      String insecticideId = insecticideVQ.getSelectableRef(InsecticideBrand.ID).getColumnAlias();

      insecticideVQ.FROM(INSECTICIDE_VIEW, INSECTICIDE_VIEW);
      insecticideVQ.AND(insecticideVQ.aSQLCharacter("i_vq",
          insecticideQuery.getTableAlias() + "." + idCol).EQ(
          insecticideVQ.aSQLCharacter("i_view", INSECTICIDE_VIEW + "." + idCol)));

      String joinType = this.hasPlannedTargets ? "LEFT JOIN" : "INNER JOIN";
      str.append(" " + joinType + " (" + insecticideVQ.getSQL() + ") "
          + insecticideQuery.getTableAlias() + " ON " + leftAlias + "." + Alias.BRAND + " = "
          + insecticideQuery.getTableAlias() + "." + insecticideId + " \n");
    }

    if (this.hasSprayEnumOrTerm)
    {
      String sprayId = sprayVQ.getSelectableRef(AbstractSpray.ID).getColumnAlias();

      String joinType = this.hasPlannedTargets ? "LEFT JOIN" : "INNER JOIN";
      str.append(" " + joinType + " (" + sprayVQ.getSQL() + ") " + abstractSprayQuery.getTableAlias()
          + " ON " + leftAlias + "." + Alias.ID + " = " + abstractSprayQuery.getTableAlias() + "."
          + sprayId + " \n");
    }

    // always join on the insecticide view
    str.append(" LEFT JOIN " + insecticideView + " " + insecticideView + " ON " + leftAlias + "."
        + Alias.BRAND + " = " + insecticideView + "." + idCol + " AND \n" + insecticideView + "."
        + diseaseCol + " = " + leftAlias + "." + Alias.DISEASE + "  \n");

    // restrict by dates
    str.append("AND ((((" + leftAlias + "." + Alias.SPRAY_DATE + ") >= (" + insecticideView
        + ".start_date) \n");
    str.append("AND (" + leftAlias + "." + Alias.SPRAY_DATE + ") <= (" + insecticideView
        + ".end_date)) \n");
    str.append("AND (" + leftAlias + "." + Alias.SPRAY_DATE + ") >= (" + insecticideView
        + ".nozzleStart)) \n");
    str.append("AND (" + leftAlias + "." + Alias.SPRAY_DATE + ") <= (" + insecticideView
        + ".nozzleEnd)) \n");

    // Special case to include rows from the planned targets, which have no
    // dates
    if (this.hasPlannedTargets)
    {
      str.append("OR " + leftAlias + "." + Alias.SPRAY_DATE + " IS NULL \n");
    }

    str.append("\n");

    irsVQ.FROM(str.toString(), "");
  }

  /**
   * The AbstractSpray attributes are treated as Selectable SQL pass-throughs
   * because the values are unioned with the AbstractSpray subtypes.
   */
  private void setAbstractSprayAttributes()
  {

    //    
    // // Spray Method (enum)
    // if (irsVQ.hasSelectableRef(AbstractSpray.SPRAYMETHOD+"_displayLabel"))
    // {
    // String sql = "("
    // + QueryUtil
    // .leftJoinEnumerationDisplayLabels(irsVQ, AbstractSpray.CLASS,
    // abstractSprayQuery, AbstractSpray.SPRAYMETHOD) + ")";
    // String subSelect = "sprayEnumSubSel";
    // String table =
    // MdBusiness.getMdBusiness(AbstractSpray.CLASS).getTableName();
    // irsVQ.AND(new InnerJoinEq(idCol, table, sprayViewAlias, idCol, sql,
    // subSelect));
    // QueryUtil
    // .leftJoinEnumerationDisplayLabels(irsVQ, AbstractSpray.CLASS,
    // abstractSprayQuery, AbstractSpray.SPRAYMETHOD);
    // QueryUtil.setSelectabeSQL(irsVQ, AbstractSpray.SPRAYMETHOD,
    // "sprayMethod_displayLabel");
    // }
    //
    // // Surface Type
    // if (irsVQ.hasSelectableRef(AbstractSpray.SURFACETYPE))
    // {
    // irsVQ.getSelectableRef(AbstractSpray.SURFACETYPE).setColumnAlias("surfaceType_displayLabel");
    //
    // QueryUtil.leftJoinTermDisplayLabels(irsVQ, abstractSprayQuery,
    // sprayViewAlias + "." + idCol);
    // }
  }

  private String createUnion(IRSUnionIF[] unions)
  {
    String sql = "";
    int count = 0;
    for (IRSUnionIF union : unions)
    {
      union.setIRSQuery(this);
      sql += getUnionSQL(union);

      if (count < unions.length - 1)
      {
        sql += "\nUNION\n";
      }

      count++;
    }

    return sql;
  }

  private void setTargetManagmentCalculations()
  {
    calculateAreaPlannedTargets();
    calculateAreaPlannedCoverage();

    calculateTeamActualTargets();
    calculateTeamPlannedTargets();
    calculateTeamPlannedCoverage();
    calculateTeamTargetedCoverage();
    calculateTeamTargetDivergence();

    calculateOperatorActualTargets();
    calculateOperatorPlannedTargets();
    calculateOperatorPlannedCoverage();
    calculateOperatorTargetedCoverage();
    calculateOperatorTargetDivergence();

    this.hasPlannedTargets = this.needsAreaPlanned || this.needsOperatorPlanned
        || this.needsTeamsPlanned;
  }

  String getAbstractSprayAlias()
  {
    return sprayViewAlias;
  }

  private void setWithQuerySQL()
  {
    String resourceTargetViewQuery = this.getTargetResourceView();
    String geoTargetViewQuery = this.getGeoTargetView();
    String insecticideBrandQuery = this.getInsecticideView();

    String sql = "WITH ";

    sql += " " + RESOURCE_TARGET_VIEW + " AS \n";
    sql += "(" + resourceTargetViewQuery + ")\n";

    sql += ", " + GEO_TARGET_VIEW + " AS \n";
    sql += "(" + geoTargetViewQuery + ")\n";

    sql += ", " + INSECTICIDE_VIEW + " AS \n";
    sql += "(" + insecticideBrandQuery + ")\n";

    Set<IRSUnionIF> unions = new HashSet<IRSUnionIF>();

    // This is the final check to see if all actual targets are
    // required in the query. This occurs when any non-target management
    // calculations are selected (so no actual or planned targets flags are
    // true pertaining)
    this.needsAllActual = !this.hasPlannedTargets && !this.needsTeamsActual && !this.needsAreaActual
        && !this.needsOperatorActual;

    // Note that operator actual targets are included if team actual targets
    // are included. This is because of the aggregation where team actuals have
    // estimates based off operator actuals.
    if (this.needsAllActual || this.needsOperatorActual || this.needsTeamsActual)
    {
      unions.add(new ActualOperatorSprayTarget());
    }

    if (this.needsAllActual || this.needsTeamsActual)
    {
      unions.add(new ActualTeamSprayTarget());
    }

    if (this.needsAllActual || this.needsAreaActual)
    {
      unions.add(new ActualZoneSprayTarget());
    }

    if (this.needsAreaPlanned)
    {
      unions.add(new PlannedAreaTarget());
    }

    if (this.needsTeamsPlanned)
    {
      unions.add(new PlannedSprayTeamTarget());
    }

    if (this.needsOperatorPlanned)
    {
      unions.add(new PlannedOperatorTarget());
    }

    sql += "," + SPRAY_VIEW + " AS \n";
    sql += "(" + createUnion(unions.toArray(new IRSUnionIF[unions.size()])) + ")\n";

    irsVQ.setSqlPrefix(sql);
  }

  private String sumOperatorActualTargets()
  {
    this.needsOperatorActual = true;

    return QueryUtil.sumColumnForId(sprayViewAlias, idCol, sprayViewAlias, OPERATOR_ACTUAL_TARGET);
  }

  private String sumTeamActualTargets()
  {
    this.needsTeamsActual = true;

    return QueryUtil.sumColumnForId(sprayViewAlias, idCol, sprayViewAlias, TEAM_ACTUAL_TARGET);
  }
  
  private String sumAreaPlannedTargetsSubSelect()
  {
    String sql = "";
    sql += "( \n";
    sql += "  SELECT gtv."+WEEKLY_TARGET+" \n";
    sql += "  FROM "+GEO_TARGET_VIEW+" gtv \n";
    sql += "  WHERE "+sprayViewAlias+"."+Alias.SPRAY_DATE+" IS NOT NULL \n";
    sql += "  AND gtv."+TARGET_WEEK+" = get_epiWeek_from_date("+sprayViewAlias+"."+Alias.SPRAY_DATE+", "+startDay+") \n";
    sql += "  AND gtv."+geoEntity+" = "+sprayViewAlias+"."+Alias.GEO_ENTITY+" \n";
    sql += ") \n";
    
    return QueryUtil.sumColumnForId(sprayViewAlias, idCol, null, sql);
  }
  
  private String sumOperatorPlannedTargetsSubSelect()
  {
    String sql = "";
    sql += "( \n";
    sql += "  SELECT rtv."+WEEKLY_TARGET+" \n";
    sql += "  FROM "+RESOURCE_TARGET_VIEW+" rtv \n";
    sql += "  WHERE "+sprayViewAlias+"."+Alias.SPRAY_DATE+" IS NOT NULL \n";
    sql += "  AND rtv."+TARGET_WEEK+" = get_epiWeek_from_date("+sprayViewAlias+"."+Alias.SPRAY_DATE+", "+startDay+") \n";
    sql += "  AND rtv."+targeter+" = "+sprayViewAlias+"."+Alias.SPRAY_OPERATOR+" \n";
    sql += ") \n";
    
    return QueryUtil.sumColumnForId(sprayViewAlias, idCol, null, sql);
  }

  private String sumOperatorPlannedTargets()
  {
    this.needsOperatorPlanned = true;

    return QueryUtil.sumColumnForId(sprayViewAlias, idCol, sprayViewAlias, OPERATOR_PLANNED_TARGET);
  }
  
  private String sumTeamPlannedTargetsSubSelect()
  {
    String sql = "";
    sql += "( \n";
    sql += "  SELECT rtv."+WEEKLY_TARGET+" \n";
    sql += "  FROM "+RESOURCE_TARGET_VIEW+" rtv \n";
    sql += "  WHERE "+sprayViewAlias+"."+Alias.SPRAY_DATE+" IS NOT NULL \n";
    sql += "  AND rtv."+TARGET_WEEK+" = get_epiWeek_from_date("+sprayViewAlias+"."+Alias.SPRAY_DATE+", "+startDay+") \n";
    sql += "  AND rtv."+targeter+" = "+sprayViewAlias+"."+Alias.SPRAY_TEAM+" \n";
    sql += ") \n";
    
    return QueryUtil.sumColumnForId(sprayViewAlias, idCol, null, sql);
  }

  private String sumTeamPlannedTargets()
  {
    this.needsTeamsPlanned = true;

    return QueryUtil.sumColumnForId(sprayViewAlias, idCol, sprayViewAlias, TEAM_PLANNED_TARGET);
  }

  private String sumAreaPlannedTargets()
  {
    this.needsAreaPlanned = true;

    return QueryUtil.sumColumnForId(sprayViewAlias, idCol, sprayViewAlias, AREA_PLANNED_TARGET);
  }

  private void calculateOperatorPlannedTargets()
  {
    if (irsVQ.hasSelectableRef(OPERATOR_PLANNED_TARGET))
    {
      SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(OPERATOR_PLANNED_TARGET);
      String sql = hasGeoSelection ? this.sumOperatorPlannedTargetsSubSelect() : this.sumOperatorPlannedTargets();
      calc.setSQL(sql);
    }
  }

  private void calculateTeamPlannedTargets()
  {
    if (irsVQ.hasSelectableRef(TEAM_PLANNED_TARGET))
    {
      SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(TEAM_PLANNED_TARGET);
      String sql = hasGeoSelection ? this.sumTeamPlannedTargetsSubSelect() : this.sumTeamPlannedTargets();
      calc.setSQL(sql);
    }
  }

  private void calculateOperatorTargetDivergence()
  {
    if (irsVQ.hasSelectableRef(OPERATOR_TARGET_DIVERGENCE))
    {
      SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(OPERATOR_TARGET_DIVERGENCE);
      String sql = "(" + this.sumOperatorActualTargets() + "/NULLIF(" + this.sumOperatorPlannedTargetsSubSelect() + ",0))*100.0";
      calc.setSQL(sql);
    }
  }

  private void calculateTeamTargetDivergence()
  {
    if (irsVQ.hasSelectableRef(TEAM_TARGET_DIVERGENCE))
    {
      SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(TEAM_TARGET_DIVERGENCE);

      String sql = "(" + this.sumTeamActualTargets() + "/NULLIF(" + this.sumTeamPlannedTargetsSubSelect()
          + ",0))*100.0";
      calc.setSQL(sql);
    }
  }

  private void calculateOperatorTargetedCoverage()
  {
    if (irsVQ.hasSelectableRef(OPERATOR_TARGETED_COVERAGE))
    {
      SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(OPERATOR_TARGETED_COVERAGE);
      String sql = "(SUM(" + this.sprayedUnits + ")/NULLIF(" + this.sumOperatorActualTargets()
          + ",0))*100.0";
      calc.setSQL(sql);
    }
  }

  private void calculateTeamTargetedCoverage()
  {
    if (irsVQ.hasSelectableRef(TEAM_TARGETED_COVERAGE))
    {
      SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(TEAM_TARGETED_COVERAGE);
      String sql = "(SUM(" + this.sprayedUnits + ")/NULLIF(" + this.sumTeamActualTargets()
          + ",0))*100.0";
      calc.setSQL(sql);
    }
  }

  private void calculateOperatorPlannedCoverage()
  {
    if (irsVQ.hasSelectableRef(OPERATOR_PLANNED_COVERAGE))
    {
      SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(OPERATOR_PLANNED_COVERAGE);
      String sql = "(SUM(" + this.sprayedUnits + ")/NULLIF(" + this.sumOperatorPlannedTargetsSubSelect()
          + ",0))*100.0";
      calc.setSQL(sql);

      this.needsOperatorActual = true;
    }
  }

  private void calculateTeamPlannedCoverage()
  {
    if (irsVQ.hasSelectableRef(TEAM_PLANNED_COVERAGE))
    {
      SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(TEAM_PLANNED_COVERAGE);
      String sql = "(SUM(" + this.sprayedUnits + ")/NULLIF(" + this.sumTeamPlannedTargetsSubSelect()
          + ",0))*100.0";
      calc.setSQL(sql);

      this.needsTeamsActual = true;
    }
  }

  private void calculateAreaPlannedCoverage()
  {
    if (irsVQ.hasSelectableRef(AREA_PLANNED_COVERAGE))
    {
      String geoType = getGeoType(AbstractSpray.CLASS + '.' + AbstractSpray.GEOENTITY);

      if (irsVQ.hasSelectableRef(geoType))
      {
        SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(AREA_PLANNED_COVERAGE);
        String sql = "(SUM(" + this.sprayedUnits + ")/NULLIF(" + this.sumAreaPlannedTargetsSubSelect()
            + ",0))*100.0";
        calc.setSQL(sql);

        this.needsAreaActual = true;
      }
      else
      {
        throw new IncidencePopulationException();
      }
    }
  }

  private void calculateOperatorActualTargets()
  {
    if (irsVQ.hasSelectableRef(OPERATOR_ACTUAL_TARGET))
    {
      SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(OPERATOR_ACTUAL_TARGET);
      String sql = this.sumOperatorActualTargets();
      calc.setSQL(sql);
    }
  }

  private void calculateTeamActualTargets()
  {
    if (irsVQ.hasSelectableRef(TEAM_ACTUAL_TARGET))
    {
      SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(TEAM_ACTUAL_TARGET);
      String sql = this.sumTeamActualTargets();
      calc.setSQL(sql);
    }
  }

  private void calculateAreaPlannedTargets()
  {
    if (irsVQ.hasSelectableRef(AREA_PLANNED_TARGET))
    {
      SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(AREA_PLANNED_TARGET);

      String geoType = getGeoType(AbstractSpray.CLASS + '.' + AbstractSpray.GEOENTITY);

      if (irsVQ.hasSelectableRef(geoType))
      {
//        String sql = this.sumAreaPlannedTargets();
        String sql = hasGeoSelection ? this.sumAreaPlannedTargetsSubSelect() : this.sumAreaPlannedTargets();
        calc.setSQL(sql);
        this.needsAreaPlanned = true;
        this.needsAreaActual = true; // include actual as well because it cannot be included as standalone
      }
      else
      {
        throw new IncidencePopulationException();
      }
    }
  }

  /**
   * Combines the union columns in the correct order and returns the SQL.
   * 
   * @param union
   * @return
   */
  private String getUnionSQL(IRSUnionIF union)
  {
    String sql = "SELECT \n";
    sql += union.setId(Alias.ID) + ", \n";
    sql += union.setAggregationLevel(Alias.AGGREGATION_LEVEL) + ", \n";
    sql += union.setSprayDate(Alias.SPRAY_DATE) + ", \n";
    sql += union.setTargetWeek(Alias.TARGET_WEEK) + ", \n";
    sql += union.setGeoEntity(Alias.GEO_ENTITY) + ", \n";
    sql += union.setSprayOperator(Alias.SPRAY_OPERATOR) + ", \n";
    sql += union.setSprayTeam(Alias.SPRAY_TEAM) + ", \n";
    sql += union.setSprayMethod(Alias.SPRAY_METHOD) + ", \n";
    sql += union.setSurfaceType(Alias.SURFACE_TYPE) + ", \n";
    sql += union.setBrand(Alias.BRAND) + ", \n";
    sql += union.setOperatorActualTarget(Alias.OPERATORY_ACTUAL_TARGET) + ", \n";
    sql += union.setTeamActualTarget(Alias.TEAM_ACTUAL_TARGET) + ", \n";
    sql += union.setOperatorPlannedTarget(Alias.OPERATOR_PLANNED_TARGET) + ", \n";
    sql += union.setTeamPlannedTarget(Alias.TEAM_PLANNED_TARGET) + ", \n";
    sql += union.setAreaPlannedTarget(Alias.AREA_PLANNED_TARGET) + ", \n";
    sql += union.setSprayOperatorDefaultLocale(Alias.SPRAY_OPERATOR_DEFAULT_LOCALE) + ", \n";
    sql += union.setSprayTeamDefaultLocale(Alias.SPRAY_TEAM_DEFAULT_LOCALE) + ", \n";
    sql += union.setSprayLeaderDefaultLocale(Alias.SPRAY_LEADER_DEFAULT_LOCALE) + ", \n";
    sql += union.setZoneSuperVisorDefaultLocale(Alias.ZONE_SUPERVISOR_DEFAULT_LOCALE) + ", \n";
    sql += union.setStructureId(Alias.STRUCTURE_ID) + ", \n";
    sql += union.setHouseholdId(Alias.HOUSEHOLD_ID) + ", \n";
    sql += union.setSpraySeason(Alias.SPRAY_SEASON) + ", \n";
    sql += union.setRooms(Alias.ROOMS) + ", \n";
    sql += union.setStructures(Alias.STRUCTURES) + ", \n";
    sql += union.setHouseholds(Alias.HOUSEHOLDS) + ", \n";
    sql += union.setSprayedRooms(Alias.SPRAYED_ROOMS) + ", \n";
    sql += union.setSprayedStructures(Alias.SPRAYED_STRUCTURES) + ", \n";
    sql += union.setSprayedHouseholds(Alias.SPRAYED_HOUSEHOLDS) + ", \n";
    sql += union.setPrevSprayedStructures(Alias.PREV_SPRAYED_STRUCTURES) + ", \n";
    sql += union.setPrevSprayedHouseholds(Alias.PREV_SPRAYED_HOUSEHOLDS) + ", \n";
    sql += union.setPeople(Alias.PEOPLE) + ", \n";
    sql += union.setBedNets(Alias.BEDNETS) + ", \n";
    sql += union.setRoomsWithBedNets(Alias.ROOMS_WITH_BED_NETS) + ", \n";
    sql += union.setLocked(Alias.LOCKED) + ", \n";
    sql += union.setRefused(Alias.REFUSED) + ", \n";
    sql += union.setOther(Alias.OTHER) + ", \n";
    sql += union.setDisease(Alias.DISEASE) + ", \n";
    sql += union.setReceived(Alias.RECEIVED) + ", \n";
    sql += union.setUsed(Alias.USED) + ", \n";
    sql += union.setRefills(Alias.REFILLS) + ", \n";
    sql += union.setReturned(Alias.RETURNED) + ", \n";
    sql += union.setRoomUnsprayed(Alias.ROOMS_UNSPRAYED) + ", \n";
    sql += union.setStructureUnsprayed(Alias.STRUCTURES_UNSPRAYED) + ", \n";
    sql += union.setHouseholdUnsprayed(Alias.HOUSEHOLDS_UNSPRAYED) + ", \n";
    sql += union.setSprayedRoomsShare(Alias.SPRAYED_ROOMS_SHARE) + ", \n";
    sql += union.setSprayedStructuresShare(Alias.SPRAYED_STRUCTURES_SHARE) + ", \n";
    sql += union.setSprayedHouseholdsShare(Alias.SPRAYED_HOUSEHOLDS_SHARE) + " \n";

    String from = union.from();
    if (from.length() > 0)
    {
      sql += "FROM \n  " + from + " \n";
    }

    String where = union.where();
    if (where.length() > 0)
    {
      sql += "WHERE \n  " + where;
    }

    return sql;
  }

  private String generateEpiWeekSeriesView(boolean isResource)
  {
    String weeks = "";
    for (Integer i = 0; i < EpiWeek.NUMBER_OF_WEEKS; i++)
    {
      weeks += "target_" + i + ",";
      if (i % 10 == 0)
        weeks += "\n";
    }
    weeks = weeks.substring(0, weeks.length() - 1);

    String select = "SELECT " + idCol + " AS " + idCol + ",\n";
    select += "i AS " + Alias.TARGET_WEEK + ",\n";
    if (isResource)
    {
      select += this.targeter + " AS " + this.targeter + ", \n";
    }
    else
    {
      select += this.geoEntity + " AS " + this.geoEntity + ", \n";
    }
    
    select += "target_array[i] AS " + WEEKLY_TARGET + " \n";

    String from = "FROM ";
    from += "(SELECT id, ";
    if (isResource)
    {
      from += this.targeter + ", ";
    }
    else
    {
      from += this.geoEntity + ", ";
    }

    String sourceTable;
    if (isResource)
    {
      sourceTable = MdEntityDAO.getMdEntityDAO(ResourceTarget.CLASS).getTableName();
    }
    else
    {
      sourceTable = MdEntityDAO.getMdEntityDAO(GeoTarget.CLASS).getTableName();
    }

    from += "ARRAY[" + weeks + "] AS target_array FROM " + sourceTable + ") AS tar ";
    from += "CROSS JOIN generate_series(1, " + ( EpiWeek.NUMBER_OF_WEEKS + 1 )
        + ") AS i WHERE target_array[i] IS NOT NULL \n";

    return select + from;
  }

  private String getGeoTargetView()
  {
    return generateEpiWeekSeriesView(false);
  }

  private String getTargetResourceView()
  {
    return generateEpiWeekSeriesView(true);
  }

  private String getInsecticideView()
  {
    MdEntityDAOIF insectNozzleMd = MdEntityDAO.getMdEntityDAO(InsecticideNozzle.CLASS);
    String insectNozzleTable = insectNozzleMd.getTableName();
    String configDateCol = QueryUtil.getColumnName(insectNozzleMd, InsecticideNozzle.CONFIGURATIONDATE);
    String enabledCol = QueryUtil.getColumnName(insectNozzleMd, InsecticideNozzle.ENABLED);

    MdEntityDAOIF enumMasterMD = MdEntityDAO.getMdEntityDAO(EnumerationMaster.CLASS);
    String enumMasterTable = enumMasterMD.getTableName();
    String enumNameCol = QueryUtil.getColumnName(enumMasterMD, EnumerationMaster.ENUMNAME);
    String disLabelCol = QueryUtil.getColumnName(enumMasterMD, EnumerationMaster.DISPLAYLABEL);

    MdEntityDAOIF disLabelMd = MdEntityDAO.getMdEntityDAO(MetadataDisplayLabel.CLASS);
    String disLabelTable = disLabelMd.getTableName();
    String defaultLocaleCol = QueryUtil.getColumnName(disLabelMd, MetadataDisplayLabel.DEFAULTLOCALE);

    MdEntityDAOIF insectBrandMd = MdEntityDAO.getMdEntityDAO(InsecticideBrand.CLASS);
    String insectBrandTable = insectBrandMd.getTableName();
    String unitsPerApplicationCol = QueryUtil.getColumnName(insectBrandMd,
        InsecticideBrand.UNITSPERAPPLICATION);
    String unitQuantifierCol = QueryUtil.getColumnName(insectBrandMd, InsecticideBrand.UNITQUANTIFIER);
    String concentrationQuantifierCol = QueryUtil.getColumnName(insectBrandMd,
        InsecticideBrand.CONCENTRATIONQUANTIFIER);
    String diseaseCol = QueryUtil.getColumnName(InsecticideBrand.getDiseaseMd());

    MdEntityDAOIF nozzleMd = MdEntityDAO.getMdEntityDAO(Nozzle.CLASS);
    String nozzleTable = nozzleMd.getTableName();
    String ratioCol = QueryUtil.getColumnName(nozzleMd, Nozzle.RATIO);
    String nozzleDisLabelCol = QueryUtil.getColumnName(nozzleMd, Nozzle.DISPLAYLABEL);

    MdEntityDAOIF areaStandardsMd = MdEntityDAO.getMdEntityDAO(AreaStandards.CLASS);
    String areaStandardsTable = areaStandardsMd.getTableName();
    String structureAreaCol = QueryUtil.getColumnName(areaStandardsMd, AreaStandards.STRUCTUREAREA);
    String roomCol = QueryUtil.getColumnName(areaStandardsMd, AreaStandards.ROOM);
    String householdCol = QueryUtil.getColumnName(areaStandardsMd, AreaStandards.HOUSEHOLD);
    String unitAreaNozzleCovCol = QueryUtil.getColumnName(areaStandardsMd,
        AreaStandards.UNITNOZZLEAREACOVERAGE);
    String targetUnitCol = QueryUtil.getColumnName(areaStandardsMd, AreaStandards.TARGETUNIT);

    String select = "SELECT " + insectBrandTable + ".id,\n";
    select += "COALESCE(start_date,'1900-01-01'::date) start_date,\n";
    select += insectBrandTable + "." + diseaseCol + " disease,\n";
    select += "COALESCE(end_Date,'2100-01-01'::date) end_date, \n";
    select += "COALESCE((SELECT i." + configDateCol + " FROM " + insectNozzleTable + " i WHERE "
        + insectNozzleTable + "." + RelationshipDAOIF.PARENT_ID_COLUMN + " = i."
        + RelationshipDAOIF.PARENT_ID_COLUMN + " \n";
    select += "AND " + insectNozzleTable + "." + RelationshipDAOIF.CHILD_ID_COLUMN + " = i."
        + RelationshipDAOIF.CHILD_ID_COLUMN + "  AND " + insectNozzleTable + "." + configDateCol
        + " < i." + configDateCol + " ORDER BY i." + configDateCol
        + " DESC LIMIT 1 ),'1900-01-01'::date) nozzleStart, \n";
    select += "COALESCE((SELECT i." + configDateCol + " FROM " + insectNozzleTable + " i WHERE "
        + insectNozzleTable + "." + RelationshipDAOIF.PARENT_ID_COLUMN + " = i."
        + RelationshipDAOIF.PARENT_ID_COLUMN + " \n";
    select += "AND " + insectNozzleTable + "." + RelationshipDAOIF.CHILD_ID_COLUMN + " = i."
        + RelationshipDAOIF.CHILD_ID_COLUMN + "  AND " + insectNozzleTable + "." + configDateCol
        + " > i." + configDateCol + "  ORDER BY i." + configDateCol
        + " ASC LIMIT 1),'2100-01-01'::date) nozzleEnd, \n";
    // --% active ingredient in sachet (2) * weight of sachet (3) * number
    // of sachets in can refill using nozzle 8002 (4) * Nozzle type ratio
    // (6)
    // select += "insecticidebrand.brandname,\n";
    select += "" + unitQuantifierCol + "*" + unitsPerApplicationCol + "*ratio*("
        + concentrationQuantifierCol + "/100.0) AS active_ingredient_per_can,\n";
    select += "" + nozzleTable + "." + ratioCol + " AS nozzle_ratio,\n";
    select += "" + nozzleTable + "." + nozzleDisLabelCol + " AS nozzle_defaultLocale,\n";
    select += "" + insectNozzleTable + "." + enabledCol + ",\n";
    select += "" + enumNameCol + " spray_unit,\n";
    select += "(SELECT " + defaultLocaleCol + " FROM " + disLabelTable + " md WHERE " + enumMasterTable
        + "." + disLabelCol + " = md.id) targetUnit_displayLabel,\n";

    select += "(CASE WHEN " + enumNameCol + " = '" + TargetUnit.ROOM.name() + "' THEN " + roomCol
        + "  WHEN " + enumNameCol + " = '" + TargetUnit.STRUCTURE.name() + "' THEN " + structureAreaCol
        + " WHEN " + enumNameCol + " = '" + TargetUnit.HOUSEHOLD.name() + "' THEN " + householdCol
        + " END ) AS unitarea,\n";
    select += "" + unitAreaNozzleCovCol + " " + unitAreaNozzleCovCol + ",\n";
    select += "((" + unitQuantifierCol + "*" + unitsPerApplicationCol + "*("
        + concentrationQuantifierCol + "/100.0)) / " + unitAreaNozzleCovCol
        + " )  AS standard_application_rate,\n";
    select += "(1000.0 * (" + unitQuantifierCol + "*" + unitsPerApplicationCol + "*("
        + concentrationQuantifierCol + "/100.0)) / " + unitAreaNozzleCovCol
        + " ) AS standard_application_rate_mg,\n";
    select += "ratio * " + unitAreaNozzleCovCol + "/(CASE WHEN " + enumNameCol + " = '"
        + TargetUnit.ROOM.name() + "' THEN " + roomCol + " WHEN " + enumNameCol + " = '"
        + TargetUnit.STRUCTURE.name() + "' THEN " + structureAreaCol + " WHEN " + enumNameCol + " = '"
        + TargetUnit.HOUSEHOLD.name() + "' THEN " + householdCol + " END ) AS units_per_can,\n";

    String from = "FROM ";
    from += areaStandardsTable + " AS " + areaStandardsTable + ",\n";
    from += insectBrandTable + " AS " + insectBrandTable + ",\n";
    from += nozzleTable + " AS " + nozzleTable + ",\n";
    from += insectNozzleTable + " AS " + insectNozzleTable + "\n,";
    from += "" + enumMasterTable + " " + enumMasterTable + ",\n";

    String where = "";
    where += "AND " + insectNozzleTable + "." + RelationshipDAOIF.PARENT_ID_COLUMN + " = "
        + insectBrandTable + ".id \n";
    where += "AND " + insectNozzleTable + "." + RelationshipDAOIF.CHILD_ID_COLUMN + " = " + nozzleTable
        + ".id \n";
    where += "AND " + enumMasterTable + ".id = " + targetUnitCol + "_c \n";

    select = select.substring(0, select.length() - 2);
    where = "WHERE " + where.substring(3, where.length() - 2);
    from = from.substring(0, from.length() - 2);

    return select + "\n" + from + "\n" + where;
    // --nozzle.enabled,
    // --nozzle.lastupdatedate,
  }

  // FIXME make sure this works with multiple universals
  private String getGeoType(String attrib)
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
}
