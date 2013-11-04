package dss.vector.solutions.querybuilder.irs;

import java.util.HashMap;
import java.util.Map;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.irs.AbstractSpray;
import dss.vector.solutions.querybuilder.IRSQB;

public enum Alias implements Reloadable
{
  
  ID("id", "setId", SQLProvider.VARCHAR),
  UNIQUE_SPRAY_ID("unique_spray_id", "setUniqueSprayId", SQLProvider.VARCHAR),
  UNIQUE_PLANNED_ID("unique_planned_id", "setUniquePlannedId", SQLProvider.VARCHAR),
  CREATE_DATE("create_date", "setCreateDate", SQLProvider.DATETIME),
  LAST_UPDATE_DATE("last_update_date", "setLastUpdateDate", SQLProvider.DATETIME),
  CREATED_BY("created_by", "setCreatedBy", SQLProvider.VARCHAR),
  LAST_UPDATED_BY("last_updated_by", "setLastUpdatedBy", SQLProvider.VARCHAR),
  SPRAY_DATE("spray_date", "setSprayDate", SQLProvider.DATE, AbstractSpray.SPRAYDATE),
  PLANNED_DATE("planned_date", "setPlannedDate", SQLProvider.DATE),
  TARGET_WEEK("target_week", "setTargetWeek", SQLProvider.FLOAT),
  TARGET("target", "setTarget", SQLProvider.VARCHAR),
  AGGREGATION_LEVEL("aggregation_level", "setAggregationLevel", SQLProvider.VARCHAR),
  GEO_ENTITY("geo_entity", "setGeoEntity", SQLProvider.VARCHAR, AbstractSpray.GEOENTITY),
  PARENT_GEO_ENTITY("parent_geo_entity", "setParentGeoEntity", SQLProvider.VARCHAR),
  SPRAY_OPERATOR("spray_operator", "setSprayOperator", SQLProvider.TEXT),
  SPRAY_TEAM("spray_team", "setSprayTeam", SQLProvider.TEXT),
  SPRAY_METHOD("spray_method", "setSprayMethod", SQLProvider.TEXT, "sprayMethod_spray"),
  SURFACE_TYPE("surface_type", "setSurfaceType", SQLProvider.TEXT, "surfaceType_spray"),
  BRAND("brand", "setBrand", SQLProvider.VARCHAR),
  HOUSEHOLD_ID("household_id", "setHouseholdId", SQLProvider.VARCHAR),
  STRUCTURE_ID("structure_id", "setStructureId", SQLProvider.VARCHAR),
  SPRAY_OPERATOR_DEFAULT_LOCALE("sprayoperator_defaultLocale", "setSprayOperatorDefaultLocale", SQLProvider.TEXT),
  SPRAY_OPERATOR_PERSON_ID("sprayoperator_personId", "setSprayOperatorPersonId", SQLProvider.TEXT),
  SPRAY_OPERATOR_PERSON("sprayoperator_person", "setSprayOperatorPerson", SQLProvider.VARCHAR),
  SPRAY_OPERATOR_BIRTHDATE("sprayoperator_birthdate", "setSprayOperatorBirthdate", SQLProvider.DATE),
  SPRAY_OPERATOR_SEX("sprayoperator_sex", "setSprayOperatorSex", SQLProvider.TEXT),
  OPERATOR_ACTUAL_TARGET(IRSQB.OPERATOR_ACTUAL_TARGET, "setOperatorActualTarget", SQLProvider.FLOAT),
  SPRAY_TEAM_DEFAULT_LOCALE("sprayteam_defaultLocale", "setSprayTeamDefaultLocale", SQLProvider.TEXT),
  SPRAY_LEADER_DEFAULT_LOCALE("sprayleader_defaultLocale", "setSprayLeaderDefaultLocale", SQLProvider.TEXT),
  SPRAY_LEADER_PERSON_ID("sprayleader_personId", "setSprayLeaderPersonId", SQLProvider.TEXT),
  SPRAY_LEADER_PERSON("sprayleader_person", "setSprayLeaderPerson", SQLProvider.VARCHAR),
  SPRAY_LEADER_BIRTHDATE("sprayleader_birthdate", "setSprayLeaderBirthdate", SQLProvider.DATE),
  SPRAY_LEADER_SEX("sprayleader_sex", "setSprayLeaderSex", SQLProvider.TEXT),
  TEAM_ACTUAL_TARGET(IRSQB.TEAM_ACTUAL_TARGET, "setTeamActualTarget", SQLProvider.FLOAT),
  ZONE_SUPERVISOR_DEFAULT_LOCALE("zone_supervisor_defaultLocale", "setZoneSuperVisorDefaultLocale", SQLProvider.TEXT),
  ZONE_SUPERVISOR_PERSON_ID("zone_supervisor_personId", "setZoneSuperVisorPersonId", SQLProvider.TEXT),
  ZONE_SUPERVISOR_PERSON("zone_supervisor_person", "setZoneSuperVisorPerson", SQLProvider.VARCHAR),
  ZONE_SUPERVISOR_BIRTHDATE("zone_supervisor_birthdate", "setZoneSuperVisorBirthdate", SQLProvider.DATE),
  ZONE_SUPERVISOR_SEX("zone_supervisor_sex", "setZoneSuperVisorSex", SQLProvider.TEXT),
  SPRAY_SEASON("spray_season", "setSpraySeason", SQLProvider.VARCHAR),
  OPERATOR_PLANNED_TARGET(IRSQB.OPERATOR_PLANNED_TARGET, "setOperatorPlannedTarget", SQLProvider.FLOAT),
  TEAM_PLANNED_TARGET(IRSQB.TEAM_PLANNED_TARGET, "setTeamPlannedTarget", SQLProvider.FLOAT),
  AREA_PLANNED_TARGET(IRSQB.AREA_PLANNED_TARGET, "setAreaPlannedTarget", SQLProvider.FLOAT),
  ROOMS("rooms", "setRooms", SQLProvider.FLOAT),
  STRUCTURES("structures", "setStructures", SQLProvider.FLOAT),
  HOUSEHOLDS("households", "setHouseholds", SQLProvider.FLOAT),
  SPRAYED_ROOMS("sprayedRooms", "setSprayedRooms", SQLProvider.FLOAT),
  SPRAYED_STRUCTURES("sprayedStructures", "setSprayedStructures", SQLProvider.FLOAT),
  SPRAYED_HOUSEHOLDS("sprayedHouseholds", "setSprayedHouseholds", SQLProvider.FLOAT),
  PREV_SPRAYED_STRUCTURES("prevSprayedStructures", "setPrevSprayedStructures", SQLProvider.FLOAT),
  PREV_SPRAYED_HOUSEHOLDS("prevSprayedHouseholds", "setPrevSprayedHouseholds", SQLProvider.FLOAT),
  PEOPLE("people", "setPeople", SQLProvider.FLOAT),
  BEDNETS("bedNets", "setBedNets", SQLProvider.FLOAT),
  ROOMS_WITH_BED_NETS("roomsWithBedNets", "setRoomsWithBedNets", SQLProvider.FLOAT),
  LOCKED("locked", "setLocked", SQLProvider.FLOAT),
  REFUSED("refused", "setRefused", SQLProvider.FLOAT),
  OTHER("other", "setOther", SQLProvider.FLOAT),
  WRONG_SURFACE("wrongSurface", "setWrongSurface", SQLProvider.FLOAT),
  DISEASE("disease", "setDisease", SQLProvider.VARCHAR),    
  RECEIVED("received", "setReceived", SQLProvider.FLOAT),
  USED("used", "setUsed", SQLProvider.FLOAT),
  REFILLS("refills", "setRefills", SQLProvider.FLOAT),
  RETURNED("returned", "setReturned", SQLProvider.FLOAT),
  ROOMS_UNSPRAYED("room_unsprayed", "setRoomUnsprayed", SQLProvider.FLOAT),
  STRUCTURES_UNSPRAYED("structure_unsprayed", "setStructureUnsprayed", SQLProvider.FLOAT),
  HOUSEHOLDS_UNSPRAYED("household_unsprayed", "setHouseholdUnsprayed", SQLProvider.FLOAT),
  SPRAYED_ROOMS_SHARE("sprayedrooms_share", "setSprayedRoomsShare", SQLProvider.FLOAT),
  SPRAYED_STRUCTURES_SHARE("sprayedstructures_share", "setSprayedStructuresShare", SQLProvider.FLOAT),
  SPRAYED_HOUSEHOLDS_SHARE("sprayedhouseholds_share", "setSprayedHouseholdsShare", SQLProvider.FLOAT),
  
  // CUSTOM (not specified on any level as a column)
  
  // InsecticideView
  SPRAY_UNIT("spray_unit", "setSprayUnit", SQLProvider.VARCHAR),
  UNIT_AREA("unit_area", "setUnitArea", SQLProvider.VARCHAR),
  
  
  // Calculation
  SPRAYED_UNITS("sprayedunits", null, SQLProvider.FLOAT),
  UNITS_UNSPRAYED("unit_unsprayed", null, SQLProvider.FLOAT);
  
  
  private String alias;
  
  /**
   * The method to execute to add the column and its alias to the query. If the value
   * is null that means don't execute anything as somewhere else in the code is probably handling
   * the logic (eg, it could be a calculation or passthrough).
   */
  private String method;
  
  private String type;
  
  private String xmlAlias;
  
  /**
   * Map of selectable aliases and their Alias enum object.
   */
  private static Map<String, Alias> aliasMap;
  
  static
  {
    aliasMap = new HashMap<String, Alias>();
    
    for(Alias alias : Alias.values())
    {
      aliasMap.put(alias.getXMLAlias(), alias);
    }
  }
  
  public static Alias get(String alias)
  {
    return aliasMap.get(alias);
  }
  
  /**
   * Sets the xml alias to the value as the query alias (they should be the same, but the QB can't be
   * changed without breaking old saved queries).
   * 
   * @param alias
   * @param method
   * @param type
   */
  private Alias(String alias, String method, String type)
  {
    this(alias, method, type, alias);
  }
  
  /**
   * Sets the xml alias as a different value than the query alias.
   * 
   * @param alias
   * @param method
   * @param type
   * @param xmlAlias
   */
  private Alias(String alias, String method, String type, String xmlAlias)
  {
    this.alias = alias;
    this.method = method;
    this.type = type;
    this.xmlAlias = xmlAlias;
  }
  
  public String getType()
  {
    return type;
  }
  
  public String getMethod()
  {
    return method;
  }
  
  private String getXMLAlias()
  {
    return this.xmlAlias;
  }
  
  public String getAlias()
  {
    return alias;
  }
  
  @Override
  public String toString()
  {
    return getAlias();
  }
}
