package dss.vector.solutions.irs;

import java.util.Date;
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
import com.runwaysdk.query.InnerJoinEq;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableSQL;
import com.runwaysdk.query.SelectableSQLCharacter;
import com.runwaysdk.query.ValueQuery;
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
  private static final String OPERATOR_ACTUAL_TARGET = "operator_actual_target";
  
  private static final String TEAM_ACTUAL_TARGET = "team_actual_target";
  
  private static final String OPERATOR_TARGETED_COVERAGE = "operator_targeted_coverage";

  private static final String TEAM_TARGETED_COVERAGE = "team_targeted_coverage";
  
  private static final String TEAM_PLANNED_TARGET = "team_planned_target";
  
  private static final String OPERATOR_PLANNED_TARGET = "operator_planned_target";
  
  private static final String OPERATOR_PLANNED_COVERAGE = "operator_planned_coverage";
  
  private static final String TEAM_PLANNED_COVERAGE = "team_planned_coverage";
  
  private static final String AREA_PLANNED_COVERAGE = "area_planned_coverage";
  
  private static final String AREA_PLANNED_TARGET = "area_planned_target";
  
  private static final String TEAM_TARGET_DIVERGENCE = "team_target_divergence";

  private static final String OPERATOR_TARGET_DIVERGENCE = "operator_target_divergence";
  
  private ValueQuery                        valueQuery;

  private Map<String, GeneratedEntityQuery> queryMap;

  private JSONObject                        queryConfig;

  private String                            xml;

  private boolean                           isGrouped;

  public static final String                RESOURCE_TARGET_VIEW = "resourceTargetView";

  public static final String                GEO_TARGET_VIEW      = "geoTargetView";

  public static final String               SPRAY_VIEW    = "sprayView";

//  private static final String               PLANNED_SPRAY_VIEW   = "plannedSprayView";

  public static final String               INSECTICIDE_VIEW     = "insecticideView";
  
  public static final String TARGET_WEEK = "target_week";

  public static final String WEEKLY_TARGET = "weekly_target";
  
  private Set<Selectable>                   selectables;

  private AbstractSprayQuery                abstractSprayQuery;

  private InsecticideBrandQuery             insecticideQuery;

  private String                            abstractSprayAlias;

  protected String                          idCol;
  
  private String sprayedUnits;
  
  private boolean hasTargetManagement;

  public IRSQuery(ValueQuery valueQuery, Map<String, GeneratedEntityQuery> queryMap,
      JSONObject queryConfig, String xml)
  {
    sprayedUnits = null;
    
    this.valueQuery = valueQuery;
    this.queryMap = queryMap;
    this.queryConfig = queryConfig;
    this.xml = xml;

    this.abstractSprayQuery = (AbstractSprayQuery) queryMap.get(AbstractSpray.CLASS);
    this.insecticideQuery = (InsecticideBrandQuery) queryMap.get(InsecticideBrand.CLASS);

    this.abstractSprayAlias = abstractSprayQuery.getTableAlias();

    this.hasTargetManagement = false;
    this.isGrouped = false;

    selectables = new HashSet<Selectable>(valueQuery.getSelectableRefs());

    for (Selectable s : selectables)
    {
      if (s.isAggregateFunction())
      {
        isGrouped = true;
        break;
      }
    }

    this.idCol = QueryUtil.getIdColumn();
  }

  boolean isGrouped()
  {
    // return isGrouped; FIXME is this necessary?
    return false;
  }

  public void populate()
  {
    if (insecticideQuery != null)
    {
      valueQuery.WHERE(abstractSprayQuery.getBrand().EQ(insecticideQuery));

      QueryUtil.joinEnumerationDisplayLabels(valueQuery, InsecticideBrand.CLASS, insecticideQuery);
      QueryUtil.joinTermAllpaths(valueQuery, InsecticideBrand.CLASS, insecticideQuery);
    }

    setWithQuerySQL();

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
    
    QueryUtil.setTermRestrictions(valueQuery, queryMap);

    QueryUtil.setNumericRestrictions(valueQuery, queryConfig);

    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap);

    joinMainQueryTables();
    
    /* POST PROCESSING */
    if(this.hasTargetManagement)
    {
      // Supplement the original date grouping to account for the planning target union rows,
      // which don't have a date but an epi-week.
      if(valueQuery.hasSelectableRef(QueryUtil.DATEGROUP_EPIWEEK))
      {
        SelectableSQL sel = (SelectableSQL) valueQuery.getSelectableRef(QueryUtil.DATEGROUP_EPIWEEK);
        String original = sel.getSQL();
        
        String caseStmt = "CASE WHEN "+abstractSprayAlias+"."+Alias.SPRAY_DATE+
        " IS NOT NULL THEN "+original+" ELSE "+abstractSprayAlias+"."+Alias.TARGET_WEEK+" END";
        sel.setSQL(caseStmt);
      }
      
      // Set the other date groupings to null for the planning rows
      String[] groups = new String[]{QueryUtil.DATEGROUP_EPIYEAR,QueryUtil.DATEGROUP_MONTH,
          QueryUtil.DATEGROUP_QUARTER,QueryUtil.DATEGROUP_SEASON};
      
      for(String group : groups)
      {
        if(valueQuery.hasSelectableRef(group))
        {
          SelectableSQL sel = (SelectableSQL) valueQuery.getSelectableRef(group);
          String original = sel.getSQL();
          
          String caseStmt = "CASE WHEN "+abstractSprayAlias+"."+Alias.SPRAY_DATE+
          " IS NOT NULL THEN "+original+" ELSE NULL END";
          sel.setSQL(caseStmt);
        }
      }
    }
  }
  
  /**
   * Joins the necessary tables to make the main query work (this also includes setting
   * the proper WHERE criteria on dates).
   */
  private void joinMainQueryTables()
  {
    String tableName = abstractSprayQuery.getMdClassIF().getTableName();
    String tableAlias = abstractSprayQuery.getTableAlias();
    
    /*
     * IMPORTANT: We set the alias of the spray view to the generated alias for
     * the AbstractSpray query because the generated SQL references the
     * AbstractSpray alias but we want it to reference the spray view instead.
     * It is currently not possible (or at least very non-trivial) to tell the
     * query API to use the spray view, so instead we use the IRSSpoofJoin
     * class to force the alias swap.
     */
    valueQuery.WHERE(new IRSSpoofJoin(idCol, SPRAY_VIEW, SPRAY_VIEW, idCol, tableName,
        tableAlias));

    // restrict by disease
    valueQuery.WHERE(valueQuery.aSQLCharacter(SPRAY_VIEW+"_disease", abstractSprayAlias+"."+Alias.DISEASE).
        EQ(valueQuery.aSQLCharacter(INSECTICIDE_VIEW+"_disease", INSECTICIDE_VIEW+".disease")));
    
    // restrict by dates
    valueQuery.AND(valueQuery.aSQLDate(SPRAY_VIEW+"_sd", abstractSprayAlias+"."+Alias.SPRAY_DATE).
        GE(valueQuery.aSQLDate(INSECTICIDE_VIEW+"_sd", INSECTICIDE_VIEW+".start_date")));
    
    valueQuery.AND(valueQuery.aSQLDate(SPRAY_VIEW+"_ed", abstractSprayAlias+"."+Alias.SPRAY_DATE).
        LE(valueQuery.aSQLDate(INSECTICIDE_VIEW+"_ed", INSECTICIDE_VIEW+".end_date")));
    
    valueQuery.AND(valueQuery.aSQLDate(SPRAY_VIEW+"_ns", abstractSprayAlias+"."+Alias.SPRAY_DATE).
        GE(valueQuery.aSQLDate(INSECTICIDE_VIEW+"_ns", INSECTICIDE_VIEW+".nozzleStart")));
    
    valueQuery.AND(valueQuery.aSQLDate(SPRAY_VIEW+"_ne", abstractSprayAlias+"."+Alias.SPRAY_DATE).
        LE(valueQuery.aSQLDate(INSECTICIDE_VIEW+"_ne", INSECTICIDE_VIEW+".nozzleEnd")));
    
    // Special case to include rows from the planned targets, which have sprayDate == null
    if(this.hasTargetManagement)
    {
      valueQuery.OR(valueQuery.aSQLDate(SPRAY_VIEW+"_planned", abstractSprayAlias+"."+Alias.SPRAY_DATE).EQ((Date) null));
    }
  }
  
  /**
   * The AbstractSpray attributes are treated as Selectable SQL pass-throughs
   * because the values are unioned with the AbstractSpray subtypes.
   */
  private void setAbstractSprayAttributes()
  {
    String sprayDate = QueryUtil.getColumnName(AbstractSpray.getSprayDateMd());

    // Spray Date
    QueryUtil.setSelectabeSQL(valueQuery, AbstractSpray.SPRAYDATE, abstractSprayAlias + "." + sprayDate);

    // Geo Entity
    if (valueQuery.hasSelectableRef(AbstractSpray.GEOENTITY))
    {
      SelectableSQLCharacter subGeo = (SelectableSQLCharacter) valueQuery
          .getSelectableRef(AbstractSpray.GEOENTITY);
      QueryUtil.subselectGeoDisplayLabels(subGeo, AbstractSpray.CLASS, AbstractSpray.GEOENTITY,
          abstractSprayAlias + "." + idCol);
    }

    // Spray Method (enum)
    if (valueQuery.hasSelectableRef(AbstractSpray.SPRAYMETHOD))
    {
      String sql = "("
          + QueryUtil
              .getEnumerationDisplayLabelSubSelect(AbstractSpray.CLASS, AbstractSpray.SPRAYMETHOD) + ")";
      String subSelect = "sprayEnumSubSel";
      String table = MdBusiness.getMdBusiness(AbstractSpray.CLASS).getTableName();
      valueQuery.AND(new InnerJoinEq(idCol, table, abstractSprayAlias, idCol, sql, subSelect));

      QueryUtil.setSelectabeSQL(valueQuery, AbstractSpray.SPRAYMETHOD, "sprayMethod_displayLabel");
    }

    // Surface Type
    if (valueQuery.hasSelectableRef(AbstractSpray.SURFACETYPE))
    {
      valueQuery.getSelectableRef(AbstractSpray.SURFACETYPE).setColumnAlias("surfaceType_displayLabel");

      QueryUtil.leftJoinTermDisplayLabels(valueQuery, abstractSprayQuery, abstractSprayAlias + "."
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
    // actual targets
    this.hasTargetManagement = calculateOperatorActualTargets() || this.hasTargetManagement;
    this.hasTargetManagement = calculateTeamActualTargets() || this.hasTargetManagement;

    // planned targets
    this.hasTargetManagement = calculateAreaPlannedTargets() || this.hasTargetManagement;
    this.hasTargetManagement = calculateOperatorPlannedTargets() || this.hasTargetManagement;
    this.hasTargetManagement = calculateTeamPlannedTargets() || this.hasTargetManagement;

    // planned coverage
    this.hasTargetManagement = calculateAreaPlannedCoverage() || this.hasTargetManagement;
    this.hasTargetManagement = calculateOperatorPlannedCoverage() || this.hasTargetManagement;
    this.hasTargetManagement = calculateTeamPlannedCoverage() || this.hasTargetManagement;

    // targeted coverage
    this.hasTargetManagement = calculateOperatorTargetedCoverage() || this.hasTargetManagement;
    this.hasTargetManagement = calculateTeamTargetedCoverage() || this.hasTargetManagement;

    // divergence
    this.hasTargetManagement = calculateTeamTargetDivergence() || this.hasTargetManagement;
    this.hasTargetManagement = calculateOperatorTargetDivergence() || this.hasTargetManagement;
  }
  
  String getAbstractSprayAlias()
  {
    return abstractSprayAlias;
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

    sql += "," + SPRAY_VIEW + " AS \n";
    sql += "("
        + createUnion(new IRSUnionIF[] { new ActualOperatorSprayTarget(), new ActualTeamSprayTarget(),
            new ActualZoneSprayTarget(),
            new PlannedAreaTarget(), new PlannedSprayTeamTarget(),
            new PlannedOperatorTarget()
        }) + ")\n";

    valueQuery.setSqlPrefix(sql);
  }

  private boolean calculateOperatorPlannedTargets()
  {
    SelectableSQL calc;
    if (valueQuery.hasSelectableRef(OPERATOR_PLANNED_TARGET))
    {
      calc = (SelectableSQL) valueQuery.getSelectableRef(OPERATOR_PLANNED_TARGET);
    }
    else
    {
      return false;
    }

    String sql = this.sumOperatorPlannedTargets();
    calc.setSQL(sql);
    
    return true;
  }

  private boolean calculateTeamPlannedTargets()
  {
    SelectableSQL calc;
    if (valueQuery.hasSelectableRef(TEAM_PLANNED_TARGET))
    {
      calc = (SelectableSQL) valueQuery.getSelectableRef(TEAM_PLANNED_TARGET);
    }
    else
    {
      return false;
    }

    String sql = this.sumTeamPlannedTargets();
    calc.setSQL(sql);
    
    return true;
  }

  private boolean calculateOperatorTargetDivergence()
  {
    SelectableSQL calc;
    if (valueQuery.hasSelectableRef(OPERATOR_TARGET_DIVERGENCE))
    {
      calc = (SelectableSQL) valueQuery.getSelectableRef(OPERATOR_TARGET_DIVERGENCE);
    }
    else
    {
      return false;
    }

    String sql = "(" + this.sumOperatorActualTargets() + "/NULLIF("+this.sumOperatorPlannedTargets()+",0))*100.0";
    calc.setSQL(sql);
    
    return true;
  }

  private boolean calculateTeamTargetDivergence()
  {
    SelectableSQL calc;
    if (valueQuery.hasSelectableRef(TEAM_TARGET_DIVERGENCE))
    {
      calc = (SelectableSQL) valueQuery.getSelectableRef(TEAM_TARGET_DIVERGENCE);
    }
    else
    {
      return false;
    }

    String sql = "(" + this.sumTeamActualTargets() + "/NULLIF("+this.sumTeamPlannedTargets()+",0))*100.0";
    calc.setSQL(sql);
    
    return true;
  }
  
  private String sumOperatorActualTargets()
  {
    return QueryUtil.sumColumnForId(abstractSprayAlias, idCol, abstractSprayAlias, OPERATOR_ACTUAL_TARGET);
  }
  
  private String sumTeamActualTargets()
  {
    return QueryUtil.sumColumnForId(abstractSprayAlias, idCol, abstractSprayAlias, TEAM_ACTUAL_TARGET);
  }

  private String sumOperatorPlannedTargets()
  {
    return "SUM("+OPERATOR_PLANNED_TARGET+")";
  }
  
  private String sumTeamPlannedTargets()
  {
    return "SUM("+TEAM_PLANNED_TARGET+")";
  }

  private String sumAreaPlannedTargets()
  {
    return "SUM("+AREA_PLANNED_TARGET+")";
  }

  private boolean calculateOperatorTargetedCoverage()
  {
    SelectableSQL calc;
    if (valueQuery.hasSelectableRef(OPERATOR_TARGETED_COVERAGE))
    {
      calc = (SelectableSQL) valueQuery.getSelectableRef(OPERATOR_TARGETED_COVERAGE);
    }
    else
    {
      return false;
    }
    
    
    String sql = "(SUM(" + this.sprayedUnits + ")/NULLIF("+this.sumOperatorActualTargets()+",0))*100.0";
    calc.setSQL(sql);
    
    return true;
  }

  private boolean calculateTeamTargetedCoverage()
  {
    SelectableSQL calc;
    if (valueQuery.hasSelectableRef(TEAM_TARGETED_COVERAGE))
    {
      calc = (SelectableSQL) valueQuery.getSelectableRef(TEAM_TARGETED_COVERAGE);
    }
    else
    {
      return false;
    }

    String sql = "(SUM(" + this.sprayedUnits + ")/NULLIF("+this.sumTeamActualTargets()+",0))*100.0";
    calc.setSQL(sql);
    
    return true;
  }

  private boolean calculateOperatorPlannedCoverage()
  {
    SelectableSQL calc;
    if (valueQuery.hasSelectableRef(OPERATOR_PLANNED_COVERAGE))
    {
      calc = (SelectableSQL) valueQuery.getSelectableRef(OPERATOR_PLANNED_COVERAGE);
    }
    else
    {
      return false;
    }

    String sql = "(SUM(" + this.sprayedUnits + ")/NULLIF("+this.sumOperatorPlannedTargets()+",0))*100.0";
    calc.setSQL(sql);
    
    return true;
  }

  private boolean calculateTeamPlannedCoverage()
  {
    SelectableSQL calc;
    if (valueQuery.hasSelectableRef(TEAM_PLANNED_COVERAGE))
    {
      calc = (SelectableSQL) valueQuery.getSelectableRef(TEAM_PLANNED_COVERAGE);
    }
    else
    {
      return false;
    }

    String sql = "(SUM(" + this.sprayedUnits + ")/NULLIF("+this.sumTeamPlannedTargets()+",0))*100.0";
    calc.setSQL(sql);
    
    return true;
  }

  private boolean calculateAreaPlannedCoverage()
  {
    SelectableSQL calc;
    if (valueQuery.hasSelectableRef(AREA_PLANNED_COVERAGE))
    {
      calc = (SelectableSQL) valueQuery.getSelectableRef(AREA_PLANNED_COVERAGE);
    }
    else
    {
      return false;
    }

    String sql = "(SUM(" + this.sprayedUnits + ")/NULLIF("+this.sumAreaPlannedTargets()+",0))*100.0";
    calc.setSQL(sql);
    
    return true;
  }
  
  private boolean calculateOperatorActualTargets()
  {
    SelectableSQL calc;
    if (valueQuery.hasSelectableRef(OPERATOR_ACTUAL_TARGET))
    {
      calc = (SelectableSQL) valueQuery.getSelectableRef(OPERATOR_ACTUAL_TARGET);
    }
    else
    {
      return false;
    }

    String sql = this.sumOperatorActualTargets();
    calc.setSQL(sql);
    
    return true;
  }

  private boolean calculateTeamActualTargets()
  {
    SelectableSQL calc;
    if (valueQuery.hasSelectableRef(TEAM_ACTUAL_TARGET))
    {
      calc = (SelectableSQL) valueQuery.getSelectableRef(TEAM_ACTUAL_TARGET);
    }
    else
    {
      return false;
    }

    String sql = this.sumTeamActualTargets();
    calc.setSQL(sql);
    
    return true;
  }

  private boolean calculateAreaPlannedTargets()
  {
    SelectableSQL calc;
    if (valueQuery.hasSelectableRef(AREA_PLANNED_TARGET))
    {
      calc = (SelectableSQL) valueQuery.getSelectableRef(AREA_PLANNED_TARGET);
    }
    else
    {
      return false;
    }

    String geoType = getGeoType(AbstractSpray.CLASS + '.' + AbstractSpray.GEOENTITY);

    if (valueQuery.hasSelectableRef(geoType))
    {
      String sql = "SUM(" + this.abstractSprayQuery.getTableAlias() +"."+Alias.AREA_PLANNED_TARGET+")";

//    sql += "MAX((SELECT id FROM " + geoEntityTable + " g WHERE g." + geoIdCol + " = " + columnAlias
//    + ")), ";
//sql += "MAX(spray_season), ";
//sql += "'target_'||( get_epiWeek_from_date(MAX(" + sprayDateCol + ")," + startDay + ")-1))";
      
      calc.setSQL(sql);
    }
    else
    {
      throw new IncidencePopulationException();
    }

    return true;
  }

//  private boolean calculatePlannedCoverage(String sprayedUnits)
//  {
//    SelectableSQL calc;
//    if (valueQuery.hasSelectableRef("area_planned_coverage"))
//    {
//      calc = (SelectableSQL) valueQuery.getSelectableRef("area_planned_coverage");
//    }
//    else
//    {
//      return false;
//    }
//
//    String geoType = getGeoType(AbstractSpray.CLASS + '.' + AbstractSpray.GEOENTITY);
//    Selectable s;
//
//    try
//    {
//      s = valueQuery.getSelectableRef(geoType);
//    }
//    catch (QueryException e)
//    {
//      throw new IncidencePopulationException(e);
//    }
//
//    String sql = "NULL";
//
//    int startDay = Property.getInt(PropertyInfo.EPI_WEEK_PACKAGE, PropertyInfo.EPI_START_DAY);
//
//    MdEntityDAOIF geoEntityMd = MdEntityDAO.getMdEntityDAO(GeoEntity.CLASS);
//    String geoEntityTable = geoEntityMd.getTableName();
//    String geoIdCol = QueryUtil.getColumnName(geoEntityMd, GeoEntity.GEOID);
//
//    String sprayDateCol = QueryUtil.getColumnName(AbstractSpray.CLASS, AbstractSpray.SPRAYDATE);
//
//    String columnAlias = s.getDbQualifiedName();
//    sql = "SUM(" + sprayedUnits
//        + ")::float / get_seasonal_spray_target_by_geoEntityId_and_seasonId_and_tar(";
//
//    sql += "MAX((SELECT id FROM " + geoEntityTable + " g WHERE g." + geoIdCol + " = " + columnAlias
//        + ")), ";
//    sql += "MAX(spray_season), ";
//    sql += "'target_'||( get_epiWeek_from_date(MAX(" + sprayDateCol + ")," + startDay + ")-1))";
//
//    calc.setSQL(sql);
//    
//    return true;
//  }

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

    sql += "FROM \n  " + union.from() + " \n";

    String where = union.where();
    if (where.length() > 0)
    {
      sql += "WHERE \n  " + where;
    }

    return sql;
  }
  
  private String getGeoTargetView()
  {
    MdEntityDAOIF geoTargetMd = MdEntityDAO.getMdEntityDAO(GeoTarget.CLASS);
    String geoTargetTable = geoTargetMd.getTableName();
    String seasonCol = QueryUtil.getColumnName(GeoTarget.getSeasonMd());
    String geoEntityTargetCol = QueryUtil.getColumnName(GeoTarget.getGeoEntityMd());
    String diseaseCol = QueryUtil.getColumnName(GeoTarget.getDiseaseMd());
    
    String sql = "SELECT \n";
    sql += "id AS id, \n";
    sql += "tar."+diseaseCol+" AS disease, \n";
    sql += "tar."+geoEntityTargetCol+" AS "+geoEntityTargetCol+", \n";
    sql += "tar."+seasonCol+" AS season, \n";
    sql += "i AS "+TARGET_WEEK+", \n";
    sql += "target_array[i] AS weekly_target \n";
    sql += "FROM (SELECT id, "+diseaseCol+", "+geoEntityTargetCol+", "+seasonCol+", ARRAY[target_0, \n";
    sql += "target_1,target_2,target_3,target_4,target_5,target_6,target_7,target_8,target_9,target_10, \n";
    sql += "target_11,target_12,target_13,target_14,target_15,target_16,target_17,target_18,target_19,target_20, \n";
    sql += "target_21,target_22,target_23,target_24,target_25,target_26,target_27,target_28,target_29,target_30, \n";
    sql += "target_31,target_32,target_33,target_34,target_35,target_36,target_37,target_38,target_39,target_40, \n";
    sql += "target_41,target_42,target_43,target_44,target_45,target_46,target_47,target_48,target_49,target_50, \n";
    sql += "target_51,target_52] AS target_array FROM "+geoTargetTable+") AS tar CROSS JOIN generate_series(1, " + ( EpiWeek.NUMBER_OF_WEEKS + 1 ) + ") AS i \n";
    
    return sql;
  }
  
  private String getTargetResourceView()
  {
    String tableName = MdBusiness.getMdBusiness(ResourceTarget.CLASS).getTableName();
    String targeter = QueryUtil.getColumnName(ResourceTarget.getTargeterMd());
    
    String diseaseCol = QueryUtil.getColumnName(GeoTarget.getDiseaseMd());
    
    String weeks = "";
    for (Integer i = 0; i < EpiWeek.NUMBER_OF_WEEKS; i++)
    {
      weeks += "target_" + i + ",";
      if (i % 10 == 0)
        weeks += "\n";
    }
    weeks = weeks.substring(0, weeks.length() - 1);

    String select = "SELECT id AS id, disease AS disease, tar."+targeter+" AS "+targeter+",\n";
    select += "tar.season AS season_id,\n";
    select += "i AS target_week,\n";
    select += "target_array[i] AS weekly_target,\n";

    String from = "FROM ";
    from += "(SELECT id, "+diseaseCol+", "+targeter+" AS "+targeter+", season, ARRAY[" + weeks + "] AS target_array FROM " + tableName + ") AS tar ";
    from += "CROSS JOIN generate_series(1, " + ( EpiWeek.NUMBER_OF_WEEKS + 1 ) + ") AS i \n";

    select = select.substring(0, select.length() - 2);
    from = from.substring(0, from.length() - 2);

    return select + "\n" + from + "\n";
  }
  
  
  private String getInsecticideView() {
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
    String unitsPerApplicationCol = QueryUtil.getColumnName(insectBrandMd, InsecticideBrand.UNITSPERAPPLICATION);
    String unitQuantifierCol = QueryUtil.getColumnName(insectBrandMd, InsecticideBrand.UNITQUANTIFIER);
    String concentrationQuantifierCol = QueryUtil.getColumnName(insectBrandMd, InsecticideBrand.CONCENTRATIONQUANTIFIER);
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
    String unitAreaNozzleCovCol = QueryUtil.getColumnName(areaStandardsMd, AreaStandards.UNITNOZZLEAREACOVERAGE);
    String targetUnitCol = QueryUtil.getColumnName(areaStandardsMd, AreaStandards.TARGETUNIT);

    
    
    String select = "SELECT " + insectBrandTable + ".id,\n";
    select += "COALESCE(start_date,'1900-01-01'::date) start_date,\n";
    select += insectBrandTable+"."+diseaseCol +" disease,\n";
    select += "COALESCE(end_Date,'2100-01-01'::date) end_date, \n";
    select += "COALESCE((SELECT i." + configDateCol + " FROM " + insectNozzleTable + " i WHERE " + insectNozzleTable + "." + RelationshipDAOIF.PARENT_ID_COLUMN + " = i." + RelationshipDAOIF.PARENT_ID_COLUMN + " \n";
    select += "AND " + insectNozzleTable + "." + RelationshipDAOIF.CHILD_ID_COLUMN + " = i." + RelationshipDAOIF.CHILD_ID_COLUMN + "  AND " + insectNozzleTable + "." + configDateCol + " < i." + configDateCol + " ORDER BY i." + configDateCol + " DESC LIMIT 1 ),'1900-01-01'::date) nozzleStart, \n";
    select += "COALESCE((SELECT i." + configDateCol + " FROM " + insectNozzleTable + " i WHERE " + insectNozzleTable + "." + RelationshipDAOIF.PARENT_ID_COLUMN + " = i." + RelationshipDAOIF.PARENT_ID_COLUMN + " \n";
    select += "AND " + insectNozzleTable + "." + RelationshipDAOIF.CHILD_ID_COLUMN + " = i." + RelationshipDAOIF.CHILD_ID_COLUMN + "  AND " + insectNozzleTable + "." + configDateCol + " > i." + configDateCol + "  ORDER BY i." + configDateCol + " ASC LIMIT 1),'2100-01-01'::date) nozzleEnd, \n";
    // --% active ingredient in sachet (2) * weight of sachet (3) * number
    // of sachets in can refill using nozzle 8002 (4) * Nozzle type ratio
    // (6)
    // select += "insecticidebrand.brandname,\n";
    select += "" + unitQuantifierCol + "*" + unitsPerApplicationCol + "*ratio*(" + concentrationQuantifierCol + "/100.0) AS active_ingredient_per_can,\n";
    select += "" + nozzleTable + "." + ratioCol + " AS nozzle_ratio,\n";
    select += "" + nozzleTable + "." + nozzleDisLabelCol + " AS nozzle_defaultLocale,\n";
    select += "" + insectNozzleTable + "." + enabledCol + ",\n";
    select += "" + enumNameCol + " spray_unit,\n";
    select += "(SELECT " + defaultLocaleCol + " FROM " + disLabelTable + " md WHERE " + enumMasterTable + "." + disLabelCol + " = md.id) targetUnit_displayLabel,\n";

    select += "(CASE WHEN " + enumNameCol + " = '" + TargetUnit.ROOM.name() + "' THEN " + roomCol + "  WHEN " + enumNameCol + " = '" + TargetUnit.STRUCTURE.name() + "' THEN " + structureAreaCol + " WHEN " + enumNameCol + " = '" + TargetUnit.HOUSEHOLD.name() + "' THEN " + householdCol + " END ) AS unitarea,\n";
    select += "" + unitAreaNozzleCovCol + " " + unitAreaNozzleCovCol + ",\n";
    select += "((" + unitQuantifierCol + "*" + unitsPerApplicationCol + "*(" + concentrationQuantifierCol + "/100.0)) / " + unitAreaNozzleCovCol + " )  AS standard_application_rate,\n";
    select += "(1000.0 * (" + unitQuantifierCol + "*" + unitsPerApplicationCol + "*(" + concentrationQuantifierCol + "/100.0)) / " + unitAreaNozzleCovCol + " ) AS standard_application_rate_mg,\n";
    select += "ratio * " + unitAreaNozzleCovCol + "/(CASE WHEN " + enumNameCol + " = '" + TargetUnit.ROOM.name() + "' THEN " + roomCol + " WHEN " + enumNameCol + " = '" + TargetUnit.STRUCTURE.name() + "' THEN " + structureAreaCol + " WHEN " + enumNameCol + " = '" + TargetUnit.HOUSEHOLD.name() + "' THEN " + householdCol + " END ) AS units_per_can,\n";

    String from = "FROM ";
    from += areaStandardsTable + " AS " + areaStandardsTable + ",\n";
    from += insectBrandTable + " AS " + insectBrandTable + ",\n";
    from += nozzleTable + " AS " + nozzleTable + ",\n";
    from += insectNozzleTable + " AS " + insectNozzleTable + "\n,";
    from += "" + enumMasterTable + " " + enumMasterTable + ",\n";

    String where = "";
    where += "AND " + insectNozzleTable + "." + RelationshipDAOIF.PARENT_ID_COLUMN + " = " + insectBrandTable + ".id \n";
    where += "AND " + insectNozzleTable + "." + RelationshipDAOIF.CHILD_ID_COLUMN + " = " + nozzleTable + ".id \n";
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
