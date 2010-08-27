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
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.InnerJoinEq;
import com.runwaysdk.query.InnerJoinGtEq;
import com.runwaysdk.query.InnerJoinLtEq;
import com.runwaysdk.query.QueryException;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableSQL;
import com.runwaysdk.query.SelectableSQLCharacter;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.system.metadata.MdBusiness;

import dss.vector.solutions.Property;
import dss.vector.solutions.PropertyInfo;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.query.IncidencePopulationException;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.util.QueryUtil;

public class IRSQuery implements Reloadable
{
  private ValueQuery                        valueQuery;

  private Map<String, GeneratedEntityQuery> queryMap;

  private JSONObject                        queryConfig;

  private String                            xml;

  private boolean                           isGrouped;

  private static final String               RESOURCE_TARGET_VIEW = "resourceTargetView";

  private static final String               GEO_TARGET_VIEW      = "geoTargetView";

  private static final String               SPRAY_VIEW           = "sprayView";

  private static final String               INSECTICIDE_VIEW     = "insecticideView";

  private Set<Selectable>                   selectables;

  private AbstractSprayQuery                abstractSprayQuery;

  private InsecticideBrandQuery             insecticideQuery;
  
  private String abstractSprayAlias;
  
  protected String           idCol;

  public IRSQuery(ValueQuery valueQuery, Map<String, GeneratedEntityQuery> queryMap,
      JSONObject queryConfig, String xml)
  {
    this.valueQuery = valueQuery;
    this.queryMap = queryMap;
    this.queryConfig = queryConfig;
    this.xml = xml;

    this.abstractSprayQuery = (AbstractSprayQuery) queryMap.get(AbstractSpray.CLASS);
    this.insecticideQuery = (InsecticideBrandQuery) queryMap.get(InsecticideBrand.CLASS);

    this.abstractSprayAlias = abstractSprayQuery.getTableAlias();
    
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
//    return isGrouped; FIXME is this necessary?
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

    QueryUtil.setTermRestrictions(valueQuery, queryMap);

    QueryUtil.setNumericRestrictions(valueQuery, queryConfig);

    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap);

    setWithQuerySQL();
    
    setAbstractSprayAttributes();
    
    String avilableUnits = "(CASE WHEN spray_unit = 'ROOM' THEN rooms  WHEN spray_unit = 'STRUCTURE' THEN structures WHEN spray_unit = 'HOUSEHOLD' THEN households END )";
    String sprayedUnits = "(CASE WHEN spray_unit = 'ROOM' THEN sprayedRooms  WHEN spray_unit = 'STRUCTURE' THEN sprayedStructures WHEN spray_unit = 'HOUSEHOLD' THEN sprayedHouseholds END )";
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

    calculatePlannedCoverage(sprayedUnits);
    setTargetManagmentCalculations();
  }
  
  /**
   * The AbstractSpray attributes are treated as Selectable SQL pass-throughs because
   * the values are unioned with the AbstractSpray subtypes.
   */
  private void setAbstractSprayAttributes()
  {
    String sprayDate = QueryUtil.getColumnName(AbstractSpray.getSprayDateMd());
    String geoEntity = QueryUtil.getColumnName(AbstractSpray.getGeoEntityMd());
    String sprayMethod = QueryUtil.getColumnName(AbstractSpray.getSprayMethodMd());
    String surfaceType = QueryUtil.getColumnName(AbstractSpray.getSurfaceTypeMd());
    
    // Spray Date
    QueryUtil.setSelectabeSQL(valueQuery, AbstractSpray.SPRAYDATE, abstractSprayAlias+"."+sprayDate);
    
    // Geo Entity
    if(valueQuery.hasSelectableRef(AbstractSpray.GEOENTITY))
    {
      SelectableSQLCharacter subGeo = (SelectableSQLCharacter) valueQuery.getSelectableRef(AbstractSpray.GEOENTITY);
      QueryUtil.subselectGeoDisplayLabels(subGeo, AbstractSpray.CLASS, AbstractSpray.GEOENTITY, abstractSprayAlias + "." + idCol);
    }
    
    // Spray Method (enum)
    if(valueQuery.hasSelectableRef(AbstractSpray.SPRAYMETHOD))
    {
      String sql = "(" + QueryUtil.getEnumerationDisplayLabelSubSelect(AbstractSpray.CLASS, AbstractSpray.SPRAYMETHOD) + ")";
      String subSelect = "sprayEnumSubSel";
      String table = MdBusiness.getMdBusiness(AbstractSpray.CLASS).getTableName();
      valueQuery.AND(new InnerJoinEq(idCol, table, abstractSprayAlias, idCol, sql, subSelect));
      
      QueryUtil.setSelectabeSQL(valueQuery, AbstractSpray.SPRAYMETHOD, "sprayMethod_displayLabel");
    }

    // Surface Type
    if(valueQuery.hasSelectableRef(AbstractSpray.SURFACETYPE))
    {
      valueQuery.getSelectableRef(AbstractSpray.SURFACETYPE).setColumnAlias("surfaceType_displayLabel");
      
      QueryUtil.leftJoinTermDisplayLabels(valueQuery, abstractSprayQuery, abstractSprayAlias+"."+idCol);
    }
  }

  private String createUnion()
  {
    IRSUnionIF[] unions = new IRSUnionIF[] {
      // Planned
//      new PlannedAreaTarget(), new PlannedSprayTeamTarget(), new PlannedOperatorTarget(),
        
      // Actual
      new ActualOperatorSprayTarget(), new ActualTeamSprayTarget(), new ActualZoneSprayTarget()
    };

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
    calculateOperatorActualTargets();
    calculateTeamActualTargets();

    // planned targets
    calculateAreaPlannedTargets();
    calculateOperatorPlannedTargets();
    calculateTeamPlannedTargets();

    // planned coverage
    calculateAreaPlannedCoverage();
    calculateOperatorPlannedCoverage();
    calculateTeamPlannedCoverage();

    // targeted coverage
    calculateOperatorTargetedCoverage();
    calculateTeamTargetedCoverage();

    // divergence
    calculateTeamTargetDivergence();
    calculateOperatorTargetDivergence();
  }

  private void setWithQuerySQL()
  {
    String tableName = abstractSprayQuery.getMdClassIF().getTableName();
    String tableAlias = abstractSprayQuery.getTableAlias();

    String resourceTargetViewQuery = ResourceTarget.getTempTableSQL();
    String geoTargetViewQuery = GeoTarget.getTempTableSQL();
    String insecticideBrandQuery = InsecticideBrand.getTempTableSQL();

    // String sprayQuery = OperatorSpray.getTempTableSQL(RESOURCE_TARGET_VIEW,
    // isGrouped);
    // sprayQuery += "\nUNION\n" +
    // TeamSpray.getTempTableSQL(RESOURCE_TARGET_VIEW);
    // sprayQuery += "\nUNION\n" +
    // ZoneSpray.getTempTableSQL(RESOURCE_TARGET_VIEW);
    String sprayQuery = createUnion();

    String sql = "WITH ";

    sql += " " + RESOURCE_TARGET_VIEW + " AS \n";
    sql += "(" + resourceTargetViewQuery + ")\n";

    sql += ", " + GEO_TARGET_VIEW + " AS \n";
    sql += "(" + geoTargetViewQuery + ")\n";

    sql += ", " + INSECTICIDE_VIEW + " AS \n";
    sql += "(" + insecticideBrandQuery + ")\n";

    sql += "," + SPRAY_VIEW + " AS \n";
    sql += "(" + sprayQuery + ")\n";

    valueQuery.setSqlPrefix(sql);

    /*
     * IMPORTANT: We set the alias of the spray view to the generated alias for the AbstractSpray query
     * because the generated SQL references the AbstractSpray alias but we want it to reference the spray
     * view instead. It is currently not possible (or at least very non-trivial) to tell the query API to
     * use the spray view, so instead we use the AliasSpoofJoin class to force the alias swap.
     */
    valueQuery.WHERE(new AliasSpoofJoin(idCol, SPRAY_VIEW, SPRAY_VIEW, idCol, tableName, tableAlias));
//    valueQuery.WHERE(new InnerJoinEq(idCol, tableName, tableAlias, idCol, SPRAY_VIEW, SPRAY_VIEW));

    String brandCol = QueryUtil.getColumnName(abstractSprayQuery.getMdClassIF(), AbstractSpray.BRAND);
    String sprayDateCol = QueryUtil.getColumnName(abstractSprayQuery.getMdClassIF(),
        AbstractSpray.SPRAYDATE);

    String diseaseCol = QueryUtil.getColumnName(InsecticideBrand.getDiseaseMd());
    
    valueQuery.WHERE(new InnerJoinEq(brandCol, tableName, tableAlias, idCol, INSECTICIDE_VIEW,
        INSECTICIDE_VIEW));
    valueQuery.WHERE(new InnerJoinGtEq(sprayDateCol, tableName, tableAlias, "start_date",
        INSECTICIDE_VIEW, INSECTICIDE_VIEW));
    valueQuery.WHERE(new InnerJoinLtEq(sprayDateCol, tableName, tableAlias, "end_date",
        INSECTICIDE_VIEW, INSECTICIDE_VIEW));
    valueQuery.WHERE(new InnerJoinGtEq(sprayDateCol, tableName, tableAlias, "nozzleStart",
        INSECTICIDE_VIEW, INSECTICIDE_VIEW));
    valueQuery.WHERE(new InnerJoinLtEq(sprayDateCol, tableName, tableAlias, "nozzleEnd",
        INSECTICIDE_VIEW, INSECTICIDE_VIEW));
    valueQuery.WHERE(new InnerJoinEq(diseaseCol, tableName, tableAlias, "disease", INSECTICIDE_VIEW,
        INSECTICIDE_VIEW));
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

  private void calculateOperatorPlannedTargets()
  {
    SelectableSQL calc;
    if (valueQuery.hasSelectableRef("operator_planned_target"))
    {
      calc = (SelectableSQL) valueQuery.getSelectableRef("operator_planned_target");
    }
    else
    {
      return;
    }

    String sql = QueryUtil.sumColumnForId(abstractSprayAlias, idCol, abstractSprayAlias, calc.getUserDefinedAlias());
    calc.setSQL(sql);
  }

  private void calculateTeamPlannedTargets()
  {
    SelectableSQL calc;
    if (valueQuery.hasSelectableRef("team_planned_target"))
    {
      calc = (SelectableSQL) valueQuery.getSelectableRef("team_planned_target");
    }
    else
    {
      return;
    }

    String sql = QueryUtil.sumColumnForId(abstractSprayAlias, idCol, abstractSprayAlias, calc.getUserDefinedAlias());
    calc.setSQL(sql);
  }

  private void calculateOperatorTargetDivergence()
  {
    SelectableSQL calc;
    if (valueQuery.hasSelectableRef("operator_target_divergence"))
    {
      calc = (SelectableSQL) valueQuery.getSelectableRef("operator_target_divergence");
    }
    else
    {
      return;
    }

    String sql = "SUM(" + calc.getUserDefinedAlias() + ")";
    calc.setSQL(sql);
  }

  private void calculateTeamTargetDivergence()
  {
    SelectableSQL calc;
    if (valueQuery.hasSelectableRef("team_target_divergence"))
    {
      calc = (SelectableSQL) valueQuery.getSelectableRef("team_target_divergence");
    }
    else
    {
      return;
    }

    String sql = "SUM(" + calc.getUserDefinedAlias() + ")";
    calc.setSQL(sql);
  }

  private void calculateOperatorTargetedCoverage()
  {
    SelectableSQL calc;
    if (valueQuery.hasSelectableRef("operator_targeted_coverage"))
    {
      calc = (SelectableSQL) valueQuery.getSelectableRef("operator_targeted_coverage");
    }
    else
    {
      return;
    }

    String sql = "SUM(" + calc.getUserDefinedAlias() + ")";
    calc.setSQL(sql);
  }

  private void calculateTeamTargetedCoverage()
  {
    SelectableSQL calc;
    if (valueQuery.hasSelectableRef("team_targeted_coverage"))
    {
      calc = (SelectableSQL) valueQuery.getSelectableRef("team_targeted_coverage");
    }
    else
    {
      return;
    }

    String sql = "SUM(" + calc.getUserDefinedAlias() + ")";
    calc.setSQL(sql);
  }

  private void calculateOperatorPlannedCoverage()
  {
    SelectableSQL calc;
    if (valueQuery.hasSelectableRef("operator_planned_coverage"))
    {
      calc = (SelectableSQL) valueQuery.getSelectableRef("operator_planned_coverage");
    }
    else
    {
      return;
    }

    String sql = "SUM(" + calc.getUserDefinedAlias() + ")";
    calc.setSQL(sql);
  }

  private void calculateTeamPlannedCoverage()
  {
    SelectableSQL calc;
    if (valueQuery.hasSelectableRef("team_planned_coverage"))
    {
      calc = (SelectableSQL) valueQuery.getSelectableRef("team_planned_coverage");
    }
    else
    {
      return;
    }

    String sql = "SUM(" + calc.getUserDefinedAlias() + ")";
    calc.setSQL(sql);
  }

  private void calculateAreaPlannedCoverage()
  {
    SelectableSQL calc;
    if (valueQuery.hasSelectableRef("area_planned_coverage"))
    {
      calc = (SelectableSQL) valueQuery.getSelectableRef("area_planned_coverage");
    }
    else
    {
      return;
    }

    String sql = "SUM(" + calc.getUserDefinedAlias() + ")";
    calc.setSQL(sql);
  }

  private void calculateOperatorActualTargets()
  {
    SelectableSQL calc;
    if (valueQuery.hasSelectableRef("operator_actual_target"))
    {
      calc = (SelectableSQL) valueQuery.getSelectableRef("operator_actual_target");
    }
    else
    {
      return;
    }

    String sql = QueryUtil.sumColumnForId(abstractSprayAlias, idCol, abstractSprayAlias, calc.getUserDefinedAlias());
    calc.setSQL(sql);
  }

  private void calculateTeamActualTargets()
  {
    SelectableSQL calc;
    if (valueQuery.hasSelectableRef("team_actual_target"))
    {
      calc = (SelectableSQL) valueQuery.getSelectableRef("team_actual_target");
    }
    else
    {
      return;
    }

    String sql = QueryUtil.sumColumnForId(abstractSprayAlias, idCol, abstractSprayAlias, calc.getUserDefinedAlias());
    calc.setSQL(sql);
  }

  private void calculateAreaPlannedTargets()
  {
    SelectableSQL calc;
    if (valueQuery.hasSelectableRef("area_planned_target"))
    {
      calc = (SelectableSQL) valueQuery.getSelectableRef("area_planned_target");
    }
    else
    {
      return;
    }

    String geoType = getGeoType(AbstractSpray.CLASS + '.' + AbstractSpray.GEOENTITY);

    String sql = "NULL";

    int startDay = Property.getInt(PropertyInfo.EPI_WEEK_PACKAGE, PropertyInfo.EPI_START_DAY);

    MdEntityDAOIF geoEntityMd = MdEntityDAO.getMdEntityDAO(GeoEntity.CLASS);
    String geoEntityTable = geoEntityMd.getTableName();
    String geoIdCol = QueryUtil.getColumnName(geoEntityMd, GeoEntity.GEOID);

    String sprayDateCol = QueryUtil.getColumnName(AbstractSpray.CLASS, AbstractSpray.SPRAYDATE);

    if (valueQuery.hasSelectableRef(geoType))
    {
      Selectable s = valueQuery.getSelectableRef(geoType);

      String columnAlias = s.getDbQualifiedName();

      // area calc
      // sql = "SUM(" + this.abstractSprayQuery.getTableAlias() +
      // ".weekly_target)";

      sql = "get_seasonal_spray_target_by_geoEntityId_and_seasonId_and_tar(";
      sql += "MAX((SELECT id FROM " + geoEntityTable + " g WHERE g." + geoIdCol + " = " + columnAlias
          + ")), ";
      sql += "MAX(spray_season), ";
      sql += "'target_'||( get_epiWeek_from_date(MAX(" + sprayDateCol + ")," + startDay + ")-1))";

      calc.setSQL(sql);
    }
    else
    {
      throw new IncidencePopulationException();
    }

  }

  private void calculatePlannedCoverage(String sprayedUnits)
  {
    SelectableSQL calc;
    if (valueQuery.hasSelectableRef("area_planned_coverage"))
    {
      calc = (SelectableSQL) valueQuery.getSelectableRef("area_planned_coverage");
    }
    else
    {
      return;
    }

    String geoType = getGeoType(AbstractSpray.CLASS + '.' + AbstractSpray.GEOENTITY);
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
    sql = "SUM(" + sprayedUnits
        + ")::float / get_seasonal_spray_target_by_geoEntityId_and_seasonId_and_tar(";

    sql += "MAX((SELECT id FROM " + geoEntityTable + " g WHERE g." + geoIdCol + " = " + columnAlias
        + ")), ";
    sql += "MAX(spray_season), ";
    sql += "'target_'||( get_epiWeek_from_date(MAX(" + sprayDateCol + ")," + startDay + ")-1))";

    calc.setSQL(sql);
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
}
