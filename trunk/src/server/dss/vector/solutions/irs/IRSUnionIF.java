package dss.vector.solutions.irs;

import com.runwaysdk.generation.loader.Reloadable;

public interface IRSUnionIF extends Reloadable
{
  public enum ALIAS 
  {
    ID("id"),
    SPRAY_DATE("spray_date"),
    AGGREGATION_LEVEL("aggregation_level"),
    HOUSEHOLD_ID("household_id"),
    STRUCTURE_ID("structure_id"),
    SPRAY_OPERATOR("sprayedoprator"),
    SPRAY_OPERATOR_DEFAULT_LOCALE("sprayoperator_defaultLocale"),
    OPERATORY_ACTUAL_TARGET("operator_actual_target"),
    SPRAY_TEAM("sprayteam"),
    SPRAY_TEAM_DEFAULT_LOCALE("sprayteam_defaultLocale"),
    SPRAY_LEADER("sprayleader"),
    SPRAY_LEADER_DEFAULT_LOCALE("sprayleader_defaultLocale"),
    TEAM_ACTUAL_TARGET("team_actual_target"),
    ZONE_SUPERVISOR("zone_supervisor"),
    ZONE_SUPERVISOR_DEFAULT_LOCALE("zone_supervisor_defaultLocale"),
    ZONE_ACTUAL_TARGET("zone_actual_target"),
    SPRAY_SEASON("spray_season"),
    OPERATOR_PLANNED_TARGET("operator_planned_target"),
    TEAM_PLANNED_TARGET("team_planned_target"),
    AREA_PLANNED_TARGET("area_planned_target"),
    ROOMS("rooms"),
    STRUCTURES("structures"),
    HOUSEHOLDS("households"),
    SPRAYED_ROOMS("sprayedRooms"),
    SPRAYED_STRUCTURES("sprayedStructures"),
    SPRAYED_HOUSEHOLDS("sprayedHouseholds"),
    PREV_SPRAYED_STRUCTURES("prevSprayedStructures"),
    PREV_SPRAYED_HOUSEHOLDS("prevSprayedHouseholds"),
    PEOPLE("people"),
    BEDNETS("bedNets"),
    ROOMS_WITH_BED_NETS("roomsWithBedNets"),
    LOCKED("locked"),
    REFUSED("refused"),
    OTHER("other"),
    DISEASE("disease"),    
    RECEIVED("received"),
    USED("used"),
    REFILLS("refills"),
    RETURN("return"),
    ROOMS_UNSPRAYED("room_unsprayed"),
    STRUCTURES_UNSPRAYED("AS structure_unsprayed"),
    HOUSEHOLDS_UNSPRAYED("household_unsprayed"),
    SPRAYED_ROOMS_SHARE("sprayedrooms_share"),
    SPRAYED_STRUCTURES_SHARE("sprayedstructures_share"),
    SPRAYED_HOUSEHOLDS_SHARE("sprayedhouseholds_share");
    
    private String alias;
    
    private ALIAS(String alias)
    {
      this.alias = alias;
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
  
  // get/set owning IRSQuery
  void setIRSQuery(IRSQuery irsQuery);
  
  // template methods
  String setId(ALIAS alias);
  String setSprayDate(ALIAS alias);
  String setAggregationLevel(ALIAS alias);
  String setHouseholdId(ALIAS alias);
  String setStructureId(ALIAS alias);
  String setSprayOperator(ALIAS alias);
  String setSprayOperatorDefaultLocale(ALIAS alias);
  String setOperatorActualTarget(ALIAS alias);
  String setSprayTeam(ALIAS alias);
  String setSprayTeamDefaultLocale(ALIAS alias);
  String setSprayLeader(ALIAS alias);
  String setSprayLeaderDefaultLocale(ALIAS alias);
  String setTeamActualTarget(ALIAS alias);
  String setZoneSuperVisor(ALIAS alias);
  String setZoneSuperVisorDefaultLocale(ALIAS alias);
  String setZoneActualTarget(ALIAS alias);
  String setSpraySeason(ALIAS alias);
  String setOperatorPlannedTarget(ALIAS alias);
  String setTeamPlannedTarget(ALIAS alias);
  String setAreaPlannedTarget(ALIAS alias);
  String setRooms(ALIAS alias);
  String setStructures(ALIAS alias);
  String setHouseholds(ALIAS alias);
  String setSprayedRooms(ALIAS alias);
  String setSprayedStructures(ALIAS alias);
  String setSprayedHouseholds(ALIAS alias);
  String setPrevSprayedStructures(ALIAS alias);
  String setPrevSprayedHouseholds(ALIAS alias);
  String setPeople(ALIAS alias);
  String setBedNets(ALIAS alias);
  String setRoomsWithBedNets(ALIAS alias);
  String setLocked(ALIAS alias);
  String setRefused(ALIAS alias);
  String setOther(ALIAS alias);
  String setDisease(ALIAS alias);
  String setReceived(ALIAS alias);
  String setUsed(ALIAS alias);
  String setRefills(ALIAS alias);
  String setReturn(ALIAS alias);
  String setRoomUnsprayed(ALIAS alias);
  String setStructureUnsprayed(ALIAS alias);
  String setHouseholdUnsprayed(ALIAS alias);
  String setSprayedRoomsShare(ALIAS alias);
  String setSprayedStructuresShare(ALIAS alias);
  String setSprayedHouseholdsShare(ALIAS alias);
  
  String from();
  String where();
}
