package dss.vector.solutions.irs;

import com.runwaysdk.generation.loader.Reloadable;

public enum Alias implements Reloadable
{
  
  ID("id", IRSUnionIF.TEXT),
  SPRAY_DATE("spray_date", IRSUnionIF.DATE),
  PLANNED_DATE("planned_date", IRSUnionIF.DATE),
  TARGET_WEEK("target_week", IRSUnionIF.FLOAT),
  AGGREGATION_LEVEL("aggregation_level", IRSUnionIF.TEXT),
  GEO_ENTITY("geo_entity", IRSUnionIF.TEXT),
  SPRAY_OPERATOR("spray_operator", IRSUnionIF.TEXT),
  SPRAY_TEAM("spray_team", IRSUnionIF.TEXT),
  SPRAY_METHOD("spray_method", IRSUnionIF.TEXT),
  SURFACE_TYPE("surface_type", IRSUnionIF.TEXT),
  BRAND("brand", IRSUnionIF.TEXT),
  HOUSEHOLD_ID("household_id", IRSUnionIF.TEXT),
  STRUCTURE_ID("structure_id", IRSUnionIF.TEXT),
  SPRAY_OPERATOR_DEFAULT_LOCALE("sprayoperator_defaultLocale", IRSUnionIF.TEXT),
  OPERATOR_ACTUAL_TARGET(IRSQuery.OPERATOR_ACTUAL_TARGET, IRSUnionIF.FLOAT),
  SPRAY_TEAM_DEFAULT_LOCALE("sprayteam_defaultLocale", IRSUnionIF.TEXT),
  SPRAY_LEADER_DEFAULT_LOCALE("sprayleader_defaultLocale", IRSUnionIF.TEXT),
  TEAM_ACTUAL_TARGET(IRSQuery.TEAM_ACTUAL_TARGET, IRSUnionIF.FLOAT),
  ZONE_SUPERVISOR_DEFAULT_LOCALE("zone_supervisor_defaultLocale", IRSUnionIF.TEXT),
  SPRAY_SEASON("spray_season", IRSUnionIF.TEXT),
  OPERATOR_PLANNED_TARGET(IRSQuery.OPERATOR_PLANNED_TARGET, IRSUnionIF.FLOAT),
  TEAM_PLANNED_TARGET(IRSQuery.TEAM_PLANNED_TARGET, IRSUnionIF.FLOAT),
  AREA_PLANNED_TARGET(IRSQuery.AREA_PLANNED_TARGET, IRSUnionIF.FLOAT),
  ROOMS("rooms", IRSUnionIF.FLOAT),
  STRUCTURES("structures", IRSUnionIF.FLOAT),
  HOUSEHOLDS("households", IRSUnionIF.FLOAT),
  SPRAYED_ROOMS("sprayedRooms", IRSUnionIF.FLOAT),
  SPRAYED_STRUCTURES("sprayedStructures", IRSUnionIF.FLOAT),
  SPRAYED_HOUSEHOLDS("sprayedHouseholds", IRSUnionIF.FLOAT),
  PREV_SPRAYED_STRUCTURES("prevSprayedStructures", IRSUnionIF.FLOAT),
  PREV_SPRAYED_HOUSEHOLDS("prevSprayedHouseholds", IRSUnionIF.FLOAT),
  PEOPLE("people", IRSUnionIF.FLOAT),
  BEDNETS("bedNets", IRSUnionIF.FLOAT),
  ROOMS_WITH_BED_NETS("roomsWithBedNets", IRSUnionIF.FLOAT),
  LOCKED("locked", IRSUnionIF.FLOAT),
  REFUSED("refused", IRSUnionIF.FLOAT),
  OTHER("other", IRSUnionIF.FLOAT),
  DISEASE("disease", IRSUnionIF.TEXT),    
  RECEIVED("received", IRSUnionIF.FLOAT),
  USED("used", IRSUnionIF.FLOAT),
  REFILLS("refills", IRSUnionIF.FLOAT),
  RETURNED("returned", IRSUnionIF.FLOAT),
  ROOMS_UNSPRAYED("room_unsprayed", IRSUnionIF.FLOAT),
  STRUCTURES_UNSPRAYED("structure_unsprayed", IRSUnionIF.FLOAT),
  HOUSEHOLDS_UNSPRAYED("household_unsprayed", IRSUnionIF.FLOAT),
  SPRAYED_ROOMS_SHARE("sprayedrooms_share", IRSUnionIF.FLOAT),
  SPRAYED_STRUCTURES_SHARE("sprayedstructures_share", IRSUnionIF.FLOAT),
  SPRAYED_HOUSEHOLDS_SHARE("sprayedhouseholds_share", IRSUnionIF.FLOAT);
  
  private String alias;
  
  private String type;
  
  private Alias(String alias, String type)
  {
    this.alias = alias;
    this.type = type;
  }
  
  public String getType()
  {
    return type;
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
