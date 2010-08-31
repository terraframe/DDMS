package dss.vector.solutions.irs;

import java.util.HashSet;
import java.util.Iterator;
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
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.GeoProxyValueQuery;
import com.runwaysdk.query.IRSValueQuery;
import com.runwaysdk.query.InnerJoinEq;
import com.runwaysdk.query.SelectableSQL;
import com.runwaysdk.query.SelectableSQLCharacter;
import com.runwaysdk.system.EnumerationMaster;
import com.runwaysdk.system.metadata.MdBusiness;
import com.runwaysdk.system.metadata.MetadataDisplayLabel;

import dss.vector.solutions.general.EpiWeek;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.query.IncidencePopulationException;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.util.QueryUtil;

public class IRSQuery implements Reloadable
{
  private static final String               TEAM_TARGET_DIVERGENCE     = "team_target_divergence";

  private static final String               TEAM_PLANNED_COVERAGE      = "team_planned_coverage";

  private static final String               TEAM_PLANNED_TARGET        = "team_planned_target";

  private static final String               TEAM_TARGETED_COVERAGE     = "team_targeted_coverage";

  private static final String               TEAM_ACTUAL_TARGET         = "team_actual_target";

  private static final String               AREA_PLANNED_COVERAGE      = "area_planned_coverage";

  private static final String               AREA_PLANNED_TARGET        = "area_planned_target";

  private static final String               OPERATOR_TARGET_DIVERGENCE = "operator_target_divergence";

  private static final String               OPERATOR_ACTUAL_TARGET     = "operator_actual_target";

  private static final String               OPERATOR_TARGETED_COVERAGE = "operator_targeted_coverage";

  private static final String               OPERATOR_PLANNED_TARGET    = "operator_planned_target";

  private static final String               OPERATOR_PLANNED_COVERAGE  = "operator_planned_coverage";

  // The union of actual and planned targets must include the minimum set
  // required to make the query work so as to not include superfluous rows.
  private boolean                           needsAreaPlanned;

  private boolean                           needsAreaActual;

  private boolean                           needsTeamsPlanned;

  private boolean                           needsTeamsActual;

  private boolean                           needsOperatorPlanned;

  private boolean                           needsOperatorActual;

  /**
   * If this is true then all actual targets must be included in the query,
   * regardless of whether or not the individual flags for actual are set to
   * false (e.g., needsAreaActual).
   */
  private boolean                           needsAllActual;

  /**
   * Set to true if any of the planned targets are included in the query.
   */
  private boolean                           hasPlannedTargets;

  private IRSValueQuery                        valueQuery;
  
  private GeoProxyValueQuery geoProxyVQ;

  private Map<String, GeneratedEntityQuery> queryMap;

  private JSONObject                        queryConfig;

  private String                            xml;

  public static final String                RESOURCE_TARGET_VIEW       = "resourceTargetView";

  public static final String                GEO_TARGET_VIEW            = "geoTargetView";

  public static final String                SPRAY_VIEW                 = "sprayView";

  public static final String                INSECTICIDE_VIEW           = "insecticideView";

  public static final String                TARGET_WEEK                = "target_week";

  public static final String                WEEKLY_TARGET              = "weekly_target";

  private AbstractSprayQuery                abstractSprayQuery;

  private InsecticideBrandQuery             insecticideQuery;

  private String                            sprayViewAlias;

  protected String                          idCol;

  private String                            sprayedUnits;

  public IRSQuery(IRSValueQuery valueQuery, Map<String, GeneratedEntityQuery> queryMap,
      JSONObject queryConfig, String xml, GeoProxyValueQuery geoProxyVQ)
  {
    this.geoProxyVQ = geoProxyVQ;
    
    this.needsAreaPlanned = false;
    this.needsAreaActual = false;
    this.needsOperatorPlanned = false;
    this.needsOperatorActual = false;
    this.needsTeamsPlanned = false;
    this.needsTeamsActual = false;
    this.needsAllActual = false;

    this.hasPlannedTargets = false;

    this.valueQuery = valueQuery;
    this.queryMap = queryMap;
    this.queryConfig = queryConfig;
    this.xml = xml;

    this.abstractSprayQuery = (AbstractSprayQuery) queryMap.get(AbstractSpray.CLASS);
    this.insecticideQuery = (InsecticideBrandQuery) queryMap.get(InsecticideBrand.CLASS);

    this.sprayViewAlias = abstractSprayQuery.getTableAlias();

    this.sprayedUnits = null;

    this.idCol = QueryUtil.getIdColumn();
  }
  
  public String getSprayViewAlias()
  {
    return this.sprayViewAlias;
  }
  
  public boolean hasPlannedTargets()
  {
    return this.hasPlannedTargets;
  }

  /**
   * Populates the ValueQuery with the necessary selects, joins, and criteria to
   * make the IRS query work correctly. ORDER IS IMPORTANT. Do not change the
   * calls within this method unless you know what you are doing! There are many
   * boolean flags set within those calls that dictate how the query will
   * behave.
   */
  public void populate()
  {
    if (insecticideQuery != null)
    {
//      valueQuery.WHERE(abstractSprayQuery.getBrand().EQ(insecticideQuery));
      QueryUtil.joinEnumerationDisplayLabels(valueQuery, InsecticideBrand.CLASS, insecticideQuery);
      QueryUtil.joinTermAllpaths(valueQuery, InsecticideBrand.CLASS, insecticideQuery);
    }

    setAbstractSprayAttributes();

    String avilableUnits = "(CASE WHEN spray_unit = 'ROOM' THEN rooms  WHEN spray_unit = 'STRUCTURE' THEN structures WHEN spray_unit = 'HOUSEHOLD' THEN households END )";
    sprayedUnits = "(CASE WHEN spray_unit = 'ROOM' THEN sprayedRooms  WHEN spray_unit = 'STRUCTURE' THEN sprayedStructures WHEN spray_unit = 'HOUSEHOLD' THEN sprayedHouseholds END )";
    String unsprayedUnits = "(CASE WHEN spray_unit = 'ROOM' THEN (room_unsprayed)  WHEN spray_unit = 'STRUCTURE' THEN (structure_unsprayed) WHEN spray_unit = 'HOUSEHOLD' THEN (household_unsprayed)  END )";
    String shareOfCans = "(CASE WHEN spray_unit = 'ROOM' THEN (sprayedrooms_share)  WHEN spray_unit = 'STRUCTURE' THEN (sprayedstructures_share) WHEN spray_unit = 'HOUSEHOLD' THEN (sprayedhouseholds_share)  END )";

    String unit_operational_coverage = "SUM(" + sprayedUnits + "))::float / nullif(SUM(" + avilableUnits
        + "),0";

    String unit_application_rate = "SUM(refills::FLOAT * " + shareOfCans
        + " * active_ingredient_per_can) / nullif(SUM(" + sprayedUnits + " * unitarea),0)";
    String unit_application_ratio = "((" + unit_application_rate + ") / AVG(standard_application_rate))";

    QueryUtil.setSelectabeSQL(valueQuery, "sprayedunits", sprayedUnits);
    QueryUtil.setSelectabeSQL(valueQuery, "unit_unsprayed", unsprayedUnits);
    QueryUtil.setSelectabeSQL(valueQuery, "refills * " + shareOfCans, unsprayedUnits);

    QueryUtil.setSelectabeSQL(valueQuery, "unit_application_rate", "(" + unit_application_rate + ")");
    QueryUtil.setSelectabeSQL(valueQuery, "unit_application_rate_mg", "1000.0 *" + "("
        + unit_application_rate + ")");
    QueryUtil.setSelectabeSQL(valueQuery, "unit_application_ratio", unit_application_ratio);
    QueryUtil.setSelectabeSQL(valueQuery, "unit_operational_coverage", unit_operational_coverage);

    QueryUtil.setSelectabeSQL(valueQuery, "calculated_rooms_sprayed", "(" + unit_operational_coverage
        + ") * SUM(rooms)");
    QueryUtil.setSelectabeSQL(valueQuery, "calculated_structures_sprayed", "("
        + unit_operational_coverage + ") * SUM(structures)");
    QueryUtil.setSelectabeSQL(valueQuery, "calculated_households_sprayed", "("
        + unit_operational_coverage + ") * SUM(households)");

    setTargetManagmentCalculations();

    // Note: setWithQueyrSQL() must go after setTargetManagementCalculations()
    // so the target management flags will be set correctly.
    setWithQuerySQL();

    QueryUtil.setTermRestrictions(valueQuery, queryMap);

    QueryUtil.setNumericRestrictions(valueQuery, queryConfig);

    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap);

    joinMainQueryTables();

    /* POST PROCESSING */
    if (this.hasPlannedTargets)
    {
      // Supplement the original date grouping to account for the planning
      // target union rows,
      // which don't have a date but an epi-week.
      if (valueQuery.hasSelectableRef(QueryUtil.DATEGROUP_EPIWEEK))
      {
        SelectableSQL sel = (SelectableSQL) valueQuery.getSelectableRef(QueryUtil.DATEGROUP_EPIWEEK);
        String original = sel.getSQL();

        String caseStmt = "CASE WHEN " + sprayViewAlias + "." + Alias.SPRAY_DATE
            + " IS NOT NULL THEN " + original + " ELSE " + sprayViewAlias + "." + Alias.TARGET_WEEK
            + " END";
        sel.setSQL(caseStmt);
      }

      // Set the other date groupings to null for the planning rows
      String[] groups = new String[] { QueryUtil.DATEGROUP_EPIYEAR, QueryUtil.DATEGROUP_MONTH,
          QueryUtil.DATEGROUP_QUARTER, QueryUtil.DATEGROUP_SEASON };

      for (String group : groups)
      {
        if (valueQuery.hasSelectableRef(group))
        {
          SelectableSQL sel = (SelectableSQL) valueQuery.getSelectableRef(group);
          String original = sel.getSQL();

          String caseStmt = "CASE WHEN " + sprayViewAlias + "." + Alias.SPRAY_DATE
              + " IS NOT NULL THEN " + original + " ELSE NULL END";
          sel.setSQL(caseStmt);
        }
      }
    }
  }

  /**
   * Joins the necessary tables to make the main query work (this also includes
   * setting the proper WHERE criteria on dates).
   */
  private void joinMainQueryTables()
  {
    // The logic used to join the primary table (sprayView) with universals
    // needs to be altered such that the universals are left joined with the
    // primary table. This is to allow nulls in place of the geo entity column
    // for actual/operator planned targets, which do not have geo entity information.
    
    /*
     * IMPORTANT: We set the alias of the spray view to the generated alias for
     * the AbstractSpray query because the generated SQL references the
     * AbstractSpray alias but we want it to reference the spray view instead.
     * It is currently not possible (or at least very non-trivial) to tell the
     * query API to use the spray view, so instead we use the IRSSpoofJoin class
     * to force the alias swap.
     */
    valueQuery.LEFT_JOIN(this.geoProxyVQ, this);
  }

  /**
   * The AbstractSpray attributes are treated as Selectable SQL pass-throughs
   * because the values are unioned with the AbstractSpray subtypes.
   */
  private void setAbstractSprayAttributes()
  {
    String sprayDate = QueryUtil.getColumnName(AbstractSpray.getSprayDateMd());

    // Spray Date
    QueryUtil.setSelectabeSQL(valueQuery, AbstractSpray.SPRAYDATE, sprayViewAlias + "." + sprayDate);

    // Geo Entity
    if (valueQuery.hasSelectableRef(AbstractSpray.GEOENTITY))
    {
      SelectableSQLCharacter subGeo = (SelectableSQLCharacter) valueQuery
          .getSelectableRef(AbstractSpray.GEOENTITY);
      QueryUtil.subselectGeoDisplayLabels(subGeo, AbstractSpray.CLASS, AbstractSpray.GEOENTITY,
          sprayViewAlias + "." + idCol);
    }

    // Spray Method (enum)
    if (valueQuery.hasSelectableRef(AbstractSpray.SPRAYMETHOD))
    {
      String sql = "("
          + QueryUtil
              .getEnumerationDisplayLabelSubSelect(AbstractSpray.CLASS, AbstractSpray.SPRAYMETHOD) + ")";
      String subSelect = "sprayEnumSubSel";
      String table = MdBusiness.getMdBusiness(AbstractSpray.CLASS).getTableName();
      valueQuery.AND(new InnerJoinEq(idCol, table, sprayViewAlias, idCol, sql, subSelect));

      QueryUtil.setSelectabeSQL(valueQuery, AbstractSpray.SPRAYMETHOD, "sprayMethod_displayLabel");
    }

    // Surface Type
    if (valueQuery.hasSelectableRef(AbstractSpray.SURFACETYPE))
    {
      valueQuery.getSelectableRef(AbstractSpray.SURFACETYPE).setColumnAlias("surfaceType_displayLabel");

      QueryUtil.leftJoinTermDisplayLabels(valueQuery, abstractSprayQuery, sprayViewAlias + "."
          + idCol);
    }
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

    valueQuery.setSqlPrefix(sql);
  }

  private String sumOperatorActualTargets()
  {
    this.needsOperatorActual = true;

    return QueryUtil.sumColumnForId(sprayViewAlias, idCol, sprayViewAlias,
        OPERATOR_ACTUAL_TARGET);
  }

  private String sumTeamActualTargets()
  {
    this.needsTeamsActual = true;

    return QueryUtil.sumColumnForId(sprayViewAlias, idCol, sprayViewAlias, TEAM_ACTUAL_TARGET);
  }

  private String sumOperatorPlannedTargets()
  {
    this.needsOperatorPlanned = true;

    return QueryUtil.sumColumnForId(sprayViewAlias, idCol, sprayViewAlias,
        OPERATOR_PLANNED_TARGET);
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
    if (valueQuery.hasSelectableRef(OPERATOR_PLANNED_TARGET))
    {
      SelectableSQL calc = (SelectableSQL) valueQuery.getSelectableRef(OPERATOR_PLANNED_TARGET);
      String sql = this.sumOperatorPlannedTargets();
      calc.setSQL(sql);
    }
  }

  private void calculateTeamPlannedTargets()
  {
    if (valueQuery.hasSelectableRef(TEAM_PLANNED_TARGET))
    {
      SelectableSQL calc = (SelectableSQL) valueQuery.getSelectableRef(TEAM_PLANNED_TARGET);
      String sql = this.sumTeamPlannedTargets();
      calc.setSQL(sql);
    }
  }

  private void calculateOperatorTargetDivergence()
  {
    if (valueQuery.hasSelectableRef(OPERATOR_TARGET_DIVERGENCE))
    {
      SelectableSQL calc = (SelectableSQL) valueQuery.getSelectableRef(OPERATOR_TARGET_DIVERGENCE);
      String sql = "(" + this.sumOperatorActualTargets() + "/NULLIF(" + this.sumOperatorPlannedTargets()
          + ",0))*100.0";
      calc.setSQL(sql);
    }
  }

  private void calculateTeamTargetDivergence()
  {
    if (valueQuery.hasSelectableRef(TEAM_TARGET_DIVERGENCE))
    {
      SelectableSQL calc = (SelectableSQL) valueQuery.getSelectableRef(TEAM_TARGET_DIVERGENCE);

      String sql = "(" + this.sumTeamActualTargets() + "/NULLIF(" + this.sumTeamPlannedTargets()
          + ",0))*100.0";
      calc.setSQL(sql);
    }
  }

  private void calculateOperatorTargetedCoverage()
  {
    if (valueQuery.hasSelectableRef(OPERATOR_TARGETED_COVERAGE))
    {
      SelectableSQL calc = (SelectableSQL) valueQuery.getSelectableRef(OPERATOR_TARGETED_COVERAGE);
      String sql = "(SUM(" + this.sprayedUnits + ")/NULLIF(" + this.sumOperatorActualTargets()
          + ",0))*100.0";
      calc.setSQL(sql);
    }
  }

  private void calculateTeamTargetedCoverage()
  {
    if (valueQuery.hasSelectableRef(TEAM_TARGETED_COVERAGE))
    {
      SelectableSQL calc = (SelectableSQL) valueQuery.getSelectableRef(TEAM_TARGETED_COVERAGE);
      String sql = "(SUM(" + this.sprayedUnits + ")/NULLIF(" + this.sumTeamActualTargets()
          + ",0))*100.0";
      calc.setSQL(sql);
    }
  }

  private void calculateOperatorPlannedCoverage()
  {
    if (valueQuery.hasSelectableRef(OPERATOR_PLANNED_COVERAGE))
    {
      SelectableSQL calc = (SelectableSQL) valueQuery.getSelectableRef(OPERATOR_PLANNED_COVERAGE);
      String sql = "(SUM(" + this.sprayedUnits + ")/NULLIF(" + this.sumOperatorPlannedTargets()
          + ",0))*100.0";
      calc.setSQL(sql);
    }
  }

  private void calculateTeamPlannedCoverage()
  {
    if (valueQuery.hasSelectableRef(TEAM_PLANNED_COVERAGE))
    {
      SelectableSQL calc = (SelectableSQL) valueQuery.getSelectableRef(TEAM_PLANNED_COVERAGE);
      String sql = "(SUM(" + this.sprayedUnits + ")/NULLIF(" + this.sumTeamPlannedTargets()
          + ",0))*100.0";
      calc.setSQL(sql);
    }
  }

  private void calculateAreaPlannedCoverage()
  {
    if (valueQuery.hasSelectableRef(AREA_PLANNED_COVERAGE))
    {
      SelectableSQL calc = (SelectableSQL) valueQuery.getSelectableRef(AREA_PLANNED_COVERAGE);
      String sql = "(SUM(" + this.sprayedUnits + ")/NULLIF(" + this.sumAreaPlannedTargets()
          + ",0))*100.0";
      calc.setSQL(sql);
    }
  }

  private void calculateOperatorActualTargets()
  {
    if (valueQuery.hasSelectableRef(OPERATOR_ACTUAL_TARGET))
    {
      SelectableSQL calc = (SelectableSQL) valueQuery.getSelectableRef(OPERATOR_ACTUAL_TARGET);
      String sql = this.sumOperatorActualTargets();
      calc.setSQL(sql);
    }
  }

  private void calculateTeamActualTargets()
  {
    if (valueQuery.hasSelectableRef(TEAM_ACTUAL_TARGET))
    {
      SelectableSQL calc = (SelectableSQL) valueQuery.getSelectableRef(TEAM_ACTUAL_TARGET);
      String sql = this.sumTeamActualTargets();
      calc.setSQL(sql);
    }
  }

  private void calculateAreaPlannedTargets()
  {
    if (valueQuery.hasSelectableRef(AREA_PLANNED_TARGET))
    {
      SelectableSQL calc = (SelectableSQL) valueQuery.getSelectableRef(AREA_PLANNED_TARGET);

      String geoType = getGeoType(AbstractSpray.CLASS + '.' + AbstractSpray.GEOENTITY);

      if (valueQuery.hasSelectableRef(geoType))
      {
        String sql = "SUM(" + this.abstractSprayQuery.getTableAlias() + "." + Alias.AREA_PLANNED_TARGET
            + ")";

        calc.setSQL(sql);
        this.needsAreaPlanned = true;
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
  
  private String generateEpiWeekSeriesView(String sourceTable)
  {
    String weeks = "";
    for (Integer i = 0; i < EpiWeek.NUMBER_OF_WEEKS; i++)
    {
      weeks += "target_" + i + ",";
      if (i % 10 == 0)
        weeks += "\n";
    }
    weeks = weeks.substring(0, weeks.length() - 1);

    String select = "SELECT id AS id,\n";
    select += "i AS target_week,\n";
    select += "target_array[i] AS weekly_target\n";

    String from = "FROM ";
    from += "(SELECT id, ARRAY["
        + weeks + "] AS target_array FROM " + sourceTable + ") AS tar ";
    from += "CROSS JOIN generate_series(1, " + ( EpiWeek.NUMBER_OF_WEEKS + 1 ) + ") AS i WHERE target_array[i] IS NOT NULL \n";

    return select + from;
  }

  private String getGeoTargetView()
  {
    return generateEpiWeekSeriesView(MdEntityDAO.getMdEntityDAO(GeoTarget.CLASS).getTableName());
  }

  private String getTargetResourceView()
  {
    return generateEpiWeekSeriesView(MdEntityDAO.getMdEntityDAO(ResourceTarget.CLASS).getTableName());
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
