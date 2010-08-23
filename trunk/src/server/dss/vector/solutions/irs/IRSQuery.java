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
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.InnerJoinEq;
import com.runwaysdk.query.InnerJoinGtEq;
import com.runwaysdk.query.InnerJoinLtEq;
import com.runwaysdk.query.QueryException;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableSQL;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.Person;
import dss.vector.solutions.Property;
import dss.vector.solutions.PropertyInfo;
import dss.vector.solutions.general.MalariaSeason;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.irs.IRSUnionIF.ALIAS;
import dss.vector.solutions.query.IncidencePopulationException;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.util.QueryUtil;

public class IRSQuery
{
    private ValueQuery valueQuery;
    private Map<String, GeneratedEntityQuery> queryMap;
    private JSONObject queryConfig;
    private String xml;
    
    private boolean resourceQuery;
    private boolean areaQuery;
    private boolean areaAndResourceQuery;
    private boolean mixedQuery;
    
    private boolean isGrouped;
    
    private static final String RESOURCE_TARGET_VIEW = "resourceTargetView";
    private static final String GEO_TARGET_VIEW = "geoTargetView";
    private static final String SPRAY_VIEW = "sprayView";
    private static final String INSECTICIDE_VIEW = "insecticideView";
    
    private Set<Selectable> selectables;

    private AbstractSprayQuery abstractSprayQuery;
    private InsecticideBrandQuery insecticideQuery;
    
    String idCol;
    
    String sprayDate;
    
    String operSprayTable;

    String sprayOperatorCol;

    String sprayTeamCol;

    String teamLeaderCol;

    String targetCol;

    String receivedCol;

    String usedCol;

    String refillsCol;

    String returnCol;

    String householdSprayStatusTable;

    String sprayCol;

    String householdIdCol;

    String structureIdCol;

    String roomsCol;

    String structuresCol;

    String householdsCol;

    String sprayedRoomsCol;

    String sprayedStructuresCol;

    String sprayedHouseholdsCol;

    String prevSprayedStructuresCol;

    String prevSprayedHouseholdsCol;

    String peopleCol;

    String bedNetsCol;

    String roomsWithBedNetsCol;

    String lockedCol;

    String refusedCol;

    String otherCol;

    String personTable;

    String lastNameCol;

    String firstNameCol;

    String teamMemberTable;

    String memberIdCol;

    String personCol;

    String teamId;

    String sprayTeamTable;

    String abstractSprayTable;

    String geoEntityCol;

    String sprayDateCol;

    String malariaSeasonTable;

    String startDateCol;

    String endDateCol;
    
    public IRSQuery(ValueQuery valueQuery, Map<String, GeneratedEntityQuery> queryMap, JSONObject queryConfig, String xml)
    {
      this.valueQuery = valueQuery;
      this.queryMap = queryMap;
      this.queryConfig = queryConfig;
      this.xml = xml;
      
      this.resourceQuery = false;
      this.areaQuery = false;
      this.areaAndResourceQuery = false;
      this.mixedQuery = true;
      
      this.abstractSprayQuery = (AbstractSprayQuery) queryMap.get(AbstractSpray.CLASS);
      this.insecticideQuery = (InsecticideBrandQuery) queryMap.get(InsecticideBrand.CLASS);
      
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
      
      setSharedColumns();
    }
    
    boolean isGrouped()
    {
      return isGrouped;
    }
    
    /**
     * Sets the values of the most commonly used columns and aliases for
     * the union classes to use.
     */
    private void setSharedColumns()
    {
      this.idCol = QueryUtil.getIdColumn();
      
      this.sprayDate = QueryUtil.getColumnName(AbstractSpray.getSprayDateMd());
      
      MdEntityDAOIF operSprayMd = MdEntityDAO.getMdEntityDAO(OperatorSpray.CLASS);
      this.operSprayTable = operSprayMd.getTableName();
      this.sprayOperatorCol = QueryUtil.getColumnName(operSprayMd, OperatorSpray.SPRAYOPERATOR);
      this.teamLeaderCol = QueryUtil.getColumnName(operSprayMd, OperatorSpray.TEAMLEADER);
      this.sprayTeamCol = QueryUtil.getColumnName(operSprayMd, OperatorSpray.SPRAYTEAM);
      this.targetCol = QueryUtil.getColumnName(operSprayMd, OperatorSpray.TARGET);
      this.receivedCol = QueryUtil.getColumnName(operSprayMd, OperatorSpray.RECEIVED);
      this.usedCol = QueryUtil.getColumnName(operSprayMd, OperatorSpray.USED);
      this.refillsCol = QueryUtil.getColumnName(operSprayMd, OperatorSpray.REFILLS);
      this.returnCol = QueryUtil.getColumnName(operSprayMd, OperatorSpray.RETURNED);    
      
      MdEntityDAOIF householdSprayStatusMd = MdEntityDAO.getMdEntityDAO(HouseholdSprayStatus.CLASS);
      this.householdSprayStatusTable = householdSprayStatusMd.getTableName();
      this.sprayCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.SPRAY);
      this.householdIdCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.HOUSEHOLDID);
      this.structureIdCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.STRUCTUREID);
      this.roomsCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.ROOMS);
      this.structuresCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.STRUCTURES);
      this.householdsCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.HOUSEHOLDS);
      this.sprayedRoomsCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.SPRAYEDROOMS);
      this.sprayedStructuresCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.SPRAYEDSTRUCTURES);
      this.sprayedHouseholdsCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.SPRAYEDHOUSEHOLDS);
      this.prevSprayedStructuresCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.PREVSPRAYEDSTRUCTURES);
      this.prevSprayedHouseholdsCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.PREVSPRAYEDHOUSEHOLDS);
      this.peopleCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.PEOPLE);
      this.bedNetsCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.BEDNETS);
      this.roomsWithBedNetsCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.ROOMSWITHBEDNETS);
      this.lockedCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.LOCKED);
      this.refusedCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.REFUSED);
      this.otherCol = QueryUtil.getColumnName(householdSprayStatusMd, HouseholdSprayStatus.OTHER);
      
      MdEntityDAOIF personMd = MdEntityDAO.getMdEntityDAO(Person.CLASS);
      this.personTable = personMd.getTableName();
      this.firstNameCol = QueryUtil.getColumnName(personMd, Person.FIRSTNAME);
      this.lastNameCol = QueryUtil.getColumnName(personMd, Person.LASTNAME);
      
      MdEntityDAOIF teamMemberMd = MdEntityDAO.getMdEntityDAO(TeamMember.CLASS);
      this.teamMemberTable = teamMemberMd.getTableName();
      this.memberIdCol = QueryUtil.getColumnName(teamMemberMd, TeamMember.MEMBERID);
      this.personCol = QueryUtil.getColumnName(teamMemberMd, TeamMember.PERSON);
      
      MdEntityDAOIF sprayTeamMd = MdEntityDAO.getMdEntityDAO(SprayTeam.CLASS);
      this.teamId = QueryUtil.getColumnName(sprayTeamMd, SprayTeam.TEAMID);
      this.sprayTeamTable = sprayTeamMd.getTableName();
      
      MdEntityDAOIF abstractSprayMd = MdEntityDAO.getMdEntityDAO(AbstractSpray.CLASS);
      this.abstractSprayTable = abstractSprayMd.getTableName();
      this.geoEntityCol = QueryUtil.getColumnName(abstractSprayMd, AbstractSpray.GEOENTITY);
      this.sprayDateCol = QueryUtil.getColumnName(abstractSprayMd, AbstractSpray.SPRAYDATE);
      
      MdEntityDAOIF malariaSeasonMd = MdEntityDAO.getMdEntityDAO(MalariaSeason.CLASS);
      this.malariaSeasonTable = malariaSeasonMd.getTableName();
      this.startDateCol = QueryUtil.getColumnName(malariaSeasonMd, MalariaSeason.STARTDATE);
      this.endDateCol = QueryUtil.getColumnName(malariaSeasonMd, MalariaSeason.ENDDATE);
    }
    
    public void populate()
    {
      if (abstractSprayQuery != null)
      {
        QueryUtil.joinGeoDisplayLabels(valueQuery, AbstractSpray.CLASS, abstractSprayQuery);
        QueryUtil.joinTermAllpaths(valueQuery, AbstractSpray.CLASS, abstractSprayQuery);
        QueryUtil.joinEnumerationDisplayLabels(valueQuery, AbstractSpray.CLASS, abstractSprayQuery);
      }

      if (insecticideQuery != null)
      {
        valueQuery.WHERE(abstractSprayQuery.getBrand().EQ(insecticideQuery));

        QueryUtil.joinEnumerationDisplayLabels(valueQuery,  InsecticideBrand.CLASS, insecticideQuery);
        QueryUtil.joinTermAllpaths(valueQuery, InsecticideBrand.CLASS, insecticideQuery);
      }

      QueryUtil.setTermRestrictions(valueQuery, queryMap);

      QueryUtil.setNumericRestrictions(valueQuery, queryConfig);

      QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap);

      setWithQuerySQL();
      
      
      String avilableUnits = "(CASE WHEN spray_unit = 'ROOM' THEN rooms  WHEN spray_unit = 'STRUCTURE' THEN structures WHEN spray_unit = 'HOUSEHOLD' THEN households END )";
      String sprayedUnits = "(CASE WHEN spray_unit = 'ROOM' THEN sprayedRooms  WHEN spray_unit = 'STRUCTURE' THEN sprayedStructures WHEN spray_unit = 'HOUSEHOLD' THEN sprayedHouseholds END )";
      String unsprayedUnits = "(CASE WHEN spray_unit = 'ROOM' THEN (room_unsprayed)  WHEN spray_unit = 'STRUCTURE' THEN (structure_unsprayed) WHEN spray_unit = 'HOUSEHOLD' THEN (household_unsprayed)  END )";
      String shareOfCans = "(CASE WHEN spray_unit = 'ROOM' THEN (sprayedrooms_share)  WHEN spray_unit = 'STRUCTURE' THEN (sprayedstructures_share) WHEN spray_unit = 'HOUSEHOLD' THEN (sprayedhouseholds_share)  END )";

      String unit_operational_coverage = "SUM(" + sprayedUnits + "))::float / nullif(SUM(" + avilableUnits + "),0";

      String unit_application_rate = "SUM(refills::FLOAT * " + shareOfCans + " * active_ingredient_per_can) / nullif(SUM(" + sprayedUnits + " * unitarea),0)";
      String unit_application_ratio = "((" + unit_application_rate + ") / AVG(standard_application_rate))";

      QueryUtil.setSelectabeSQL(valueQuery, "sprayedunits", sprayedUnits);
      QueryUtil.setSelectabeSQL(valueQuery, "unit_unsprayed", unsprayedUnits);
      QueryUtil.setSelectabeSQL(valueQuery, "refills * " + shareOfCans, unsprayedUnits);

      QueryUtil.setSelectabeSQL(valueQuery, "unit_application_rate", "(" + unit_application_rate + ")");
      QueryUtil.setSelectabeSQL(valueQuery, "unit_application_rate_mg", "1000.0 *" + "(" + unit_application_rate + ")");
      QueryUtil.setSelectabeSQL(valueQuery, "unit_application_ratio", unit_application_ratio);
      QueryUtil.setSelectabeSQL(valueQuery, "unit_operational_coverage", unit_operational_coverage);

      QueryUtil.setSelectabeSQL(valueQuery, "calculated_rooms_sprayed", "(" + unit_operational_coverage + ") * SUM(rooms)");
      QueryUtil.setSelectabeSQL(valueQuery, "calculated_structures_sprayed", "(" + unit_operational_coverage + ") * SUM(structures)");
      QueryUtil.setSelectabeSQL(valueQuery, "calculated_households_sprayed", "(" + unit_operational_coverage + ") * SUM(households)");

      calculatePlannedCoverage(sprayedUnits);
      setTargetManagmentCalculations();
    }
    
    private String createUnion()
    {
      IRSUnionIF[] unions = new IRSUnionIF[]{
        // Planned
        
        // Actual
        new ActualTeamSprayTarget()
          
      };
      
      String sql = "";
      int count = 0;
      for(IRSUnionIF union : unions)
      {
        union.setIRSQuery(this);
        
        if(count != unions.length-1)
        {
          sql += "\nUNION\n";
        }
        
        sql += getUnionSQL(union);
      }
      
      return sql;
    }
    
    private void setTargetManagmentCalculations()
    {
      // actual targets
      
      // planned targets
      calculateAreaPlannedTargets();      
      calculateOperatorPlannedTargets();
      calculateTeamPlannedTargets();
    }
    
    private void setWithQuerySQL()
    {
      String tableName = abstractSprayQuery.getMdClassIF().getTableName();
      String tableAlias = abstractSprayQuery.getTableAlias();

      String resourceTargetViewQuery = ResourceTarget.getTempTableSQL();
      String geoTargetViewQuery = GeoTarget.getTempTableSQL();
      String insecticideBrandQuery = InsecticideBrand.getTempTableSQL();

      String sprayQuery = OperatorSpray.getTempTableSQL(RESOURCE_TARGET_VIEW, isGrouped);
      sprayQuery += "\nUNION\n" + TeamSpray.getTempTableSQL(RESOURCE_TARGET_VIEW);
      sprayQuery += "\nUNION\n" + ZoneSpray.getTempTableSQL(RESOURCE_TARGET_VIEW);

      String sql = "WITH ";
      
      sql += " " + RESOURCE_TARGET_VIEW + " AS \n";
      sql += "(" + resourceTargetViewQuery + ")\n";

      sql += ", " + GEO_TARGET_VIEW + " AS \n";
      sql += "(" + geoTargetViewQuery + ")\n";
      
      if(!this.areaQuery)
      {
        sql += ", " + INSECTICIDE_VIEW + " AS \n";
        sql += "(" + insecticideBrandQuery + ")\n";

        sql += "," + SPRAY_VIEW + " AS \n";
        sql += "(" + sprayQuery + ")\n";
      }
      
      sql += ", " + "targeterView" + " AS \n";
      sql += "(" + getTargeterView() + ")\n";

      valueQuery.setSqlPrefix(sql);
      
      if(this.areaQuery)
      {
        String asAlias = this.abstractSprayQuery.getTableAlias();
        String asTable = this.abstractSprayQuery.getMdClassIF().getTableName();
        valueQuery.WHERE(new AliasSpoofJoin("false_id", GEO_TARGET_VIEW, GEO_TARGET_VIEW, "id", asTable, asAlias));
      }
      else
      {
        // Mixed query
        String id = QueryUtil.getIdColumn();
        
        valueQuery.WHERE(new InnerJoinEq(id, tableName, tableAlias, id, SPRAY_VIEW, SPRAY_VIEW));
        
        String brandCol = QueryUtil.getColumnName(abstractSprayQuery.getMdClassIF(), AbstractSpray.BRAND);
        String sprayDateCol = QueryUtil.getColumnName(abstractSprayQuery.getMdClassIF(), AbstractSpray.SPRAYDATE);
        
        String diseaseCol = QueryUtil.getColumnName(InsecticideBrand.getDiseaseMd());
        
        valueQuery.WHERE(new InnerJoinEq(brandCol, tableName, tableAlias, id, INSECTICIDE_VIEW, INSECTICIDE_VIEW));
        valueQuery.WHERE(new InnerJoinGtEq(sprayDateCol, tableName, tableAlias, "start_date", INSECTICIDE_VIEW, INSECTICIDE_VIEW));
        valueQuery.WHERE(new InnerJoinLtEq(sprayDateCol, tableName, tableAlias, "end_date", INSECTICIDE_VIEW, INSECTICIDE_VIEW));
        valueQuery.WHERE(new InnerJoinGtEq(sprayDateCol, tableName, tableAlias, "nozzleStart", INSECTICIDE_VIEW, INSECTICIDE_VIEW));
        valueQuery.WHERE(new InnerJoinLtEq(sprayDateCol, tableName, tableAlias, "nozzleEnd", INSECTICIDE_VIEW, INSECTICIDE_VIEW));
        valueQuery.WHERE(new InnerJoinEq(diseaseCol, SPRAY_VIEW, SPRAY_VIEW, "disease", INSECTICIDE_VIEW, INSECTICIDE_VIEW));
      }
    }
    
    private String getTargeterView()
    {
      String sql = TeamMember.getTempTableSQL();
      return sql;
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
      
      String sql = "";
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

      Selectable s;
      int startDay = Property.getInt(PropertyInfo.EPI_WEEK_PACKAGE, PropertyInfo.EPI_START_DAY);

      MdEntityDAOIF geoEntityMd = MdEntityDAO.getMdEntityDAO(GeoEntity.CLASS);
      String geoEntityTable = geoEntityMd.getTableName();
      String geoIdCol = QueryUtil.getColumnName(geoEntityMd, GeoEntity.GEOID);
      
      String sprayDateCol = QueryUtil.getColumnName(AbstractSpray.CLASS, AbstractSpray.SPRAYDATE);
      
      if (valueQuery.hasSelectableRef(geoType))
      {
        s = valueQuery.getSelectableRef(geoType);
        
        
        String columnAlias = s.getDbQualifiedName();
        
        if(areaQuery)
        {
          sql = "SUM("+this.abstractSprayQuery.getTableAlias()+".weekly_target)";
        }
        else
        {
          sql = "get_seasonal_spray_target_by_geoEntityId_and_seasonId_and_tar(";
          sql += "MAX((SELECT id FROM "+geoEntityTable+" g WHERE g."+geoIdCol+" = " + columnAlias + ")), ";
          sql += "MAX(spray_season), ";
          sql += "'target_'||( get_epiWeek_from_date(MAX("+sprayDateCol+")," + startDay + ")-1))";
        }

      }
      else
      {
        // FIXME throw error?
      }
      
      calc.setSQL(sql);
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
      sql = "SUM(" + sprayedUnits + ")::float / get_seasonal_spray_target_by_geoEntityId_and_seasonId_and_tar(";

      sql += "MAX((SELECT id FROM "+geoEntityTable+" g WHERE g."+geoIdCol+" = " + columnAlias + ")), ";
      sql += "MAX(spray_season), ";
      sql += "'target_'||( get_epiWeek_from_date(MAX("+sprayDateCol+")," + startDay + ")-1))";

      calc.setSQL(sql);
    }
    
    /**
     * Combines the union columns in the correct order and returns
     * the SQL.
     * 
     * @param union
     * @return
     */
    private String getUnionSQL(IRSUnionIF union)
    {
      String sql = "SELECT \n";
      sql += union.setId(ALIAS.ID)+", \n";
      sql += union.setSprayDate(ALIAS.SPRAY_DATE)+", \n";
      sql += union.setAggregationLevel(ALIAS.AGGREGATION_LEVEL)+", \n";
      sql += union.setHouseholdId(ALIAS.HOUSEHOLD_ID)+", \n";
      sql += union.setStructureId(ALIAS.STRUCTURE_ID)+", \n";
      sql += union.setSprayOperator(ALIAS.SPRAY_OPERATOR)+", \n";
      sql += union.setSprayOperatorDefaultLocale(ALIAS.SPRAY_OPERATOR_DEFAULT_LOCALE)+", \n";
      sql += union.setOperatorActualTarget(ALIAS.OPERATORY_ACTUAL_TARGET)+", \n";
      sql += union.setSprayTeam(ALIAS.SPRAY_TEAM)+", \n";
      sql += union.setSprayTeamDefaultLocale(ALIAS.SPRAY_TEAM_DEFAULT_LOCALE)+", \n";
      sql += union.setSprayLeader(ALIAS.SPRAY_LEADER)+", \n";
      sql += union.setSprayLeaderDefaultLocale(ALIAS.SPRAY_LEADER_DEFAULT_LOCALE)+", \n";
      sql += union.setTeamActualTarget(ALIAS.TEAM_ACTUAL_TARGET)+", \n";
      sql += union.setZoneSuperVisor(ALIAS.ZONE_SUPERVISOR)+", \n";
      sql += union.setZoneSuperVisorDefaultLocale(ALIAS.ZONE_SUPERVISOR_DEFAULT_LOCALE)+", \n";
      sql += union.setZoneActualTarget(ALIAS.ZONE_ACTUAL_TARGET)+", \n";
      sql += union.setSpraySeason(ALIAS.SPRAY_SEASON)+", \n";
      sql += union.setOperatorPlannedTarget(ALIAS.OPERATOR_PLANNED_TARGET)+", \n";
      sql += union.setTeamPlannedTarget(ALIAS.TEAM_PLANNED_TARGET)+", \n";
      sql += union.setAreaPlannedTarget(ALIAS.AREA_PLANNED_TARGET)+", \n";
      sql += union.setRooms(ALIAS.ROOMS)+", \n";
      sql += union.setStructures(ALIAS.STRUCTURES)+", \n";
      sql += union.setHouseholds(ALIAS.HOUSEHOLDS)+", \n";
      sql += union.setSprayedRooms(ALIAS.SPRAYED_ROOMS)+", \n";
      sql += union.setSprayedStructures(ALIAS.SPRAYED_STRUCTURES)+", \n";
      sql += union.setSprayedHouseholds(ALIAS.SPRAYED_HOUSEHOLDS)+", \n";
      sql += union.setPrevSprayedStructures(ALIAS.PREV_SPRAYED_STRUCTURES)+", \n";
      sql += union.setPrevSprayedHouseholds(ALIAS.PREV_SPRAYED_HOUSEHOLDS)+", \n";
      sql += union.setPeople(ALIAS.PEOPLE)+", \n";
      sql += union.setBedNets(ALIAS.BEDNETS)+", \n";
      sql += union.setRoomsWithBedNets(ALIAS.ROOMS_WITH_BED_NETS)+", \n";
      sql += union.setLocked(ALIAS.LOCKED)+", \n";
      sql += union.setRefused(ALIAS.REFUSED)+", \n";
      sql += union.setOther(ALIAS.OTHER)+", \n";
      sql += union.setDisease(ALIAS.DISEASE)+", \n";
      sql += union.setReceived(ALIAS.RECEIVED)+", \n";
      sql += union.setUsed(ALIAS.USED)+", \n";
      sql += union.setRefills(ALIAS.REFILLS)+", \n";
      sql += union.setReturn(ALIAS.RETURN)+", \n";
      sql += union.setRoomUnsprayed(ALIAS.ROOMS_UNSPRAYED)+", \n";
      sql += union.setStructureUnsprayed(ALIAS.STRUCTURES_UNSPRAYED)+", \n";
      sql += union.setHouseholdUnsprayed(ALIAS.HOUSEHOLDS_UNSPRAYED)+", \n";
      sql += union.setSprayedRoomsShare(ALIAS.SPRAYED_ROOMS_SHARE)+", \n";
      sql += union.setSprayedStructuresShare(ALIAS.SPRAYED_STRUCTURES_SHARE)+", \n";
      sql += union.setSprayedHouseholdsShare(ALIAS.SPRAYED_HOUSEHOLDS_SHARE)+" \n";
      
      sql += "FROM \n  "+union.from()+" \n";
      
      String where = union.where();
      if(where.length() > 0)
      {
        sql += "WHERE \n  "+where;
      }
      
      return sql;
    }
}
