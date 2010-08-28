package dss.vector.solutions.irs;

import com.runwaysdk.generation.loader.Reloadable;

public enum Alias implements Reloadable
{
  
  ID("id"),
  SPRAY_DATE("spray_date", IRSUnionIF.DATE),
  TARGET_WEEK("target_week", IRSUnionIF.INTEGER),
  AGGREGATION_LEVEL("aggregation_level", IRSUnionIF.TEXT),
  GEO_ENTITY("geo_entity"),
  SPRAY_METHOD("spray_method"),
  SURFACE_TYPE("surface_type"),
  BRAND("brand"),
  HOUSEHOLD_ID("household_id"),
  STRUCTURE_ID("structure_id"),
  SPRAY_OPERATOR("sprayoperator"),
  SPRAY_OPERATOR_DEFAULT_LOCALE("sprayoperator_defaultLocale"),
  OPERATORY_ACTUAL_TARGET("operator_actual_target", IRSUnionIF.INTEGER),
  SPRAY_TEAM("sprayteam"),
  SPRAY_TEAM_DEFAULT_LOCALE("sprayteam_defaultLocale"),
  SPRAY_LEADER("sprayleader"),
  SPRAY_LEADER_DEFAULT_LOCALE("sprayleader_defaultLocale"),
  TEAM_ACTUAL_TARGET("team_actual_target", IRSUnionIF.INTEGER),
  ZONE_SUPERVISOR("zone_supervisor"),
  ZONE_SUPERVISOR_DEFAULT_LOCALE("zone_supervisor_defaultLocale"),
  SPRAY_SEASON("spray_season"),
  OPERATOR_PLANNED_TARGET("operator_planned_target", IRSUnionIF.INTEGER),
  TEAM_PLANNED_TARGET("team_planned_target", IRSUnionIF.INTEGER),
  AREA_PLANNED_TARGET("area_planned_target", IRSUnionIF.INTEGER),
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
  RETURNED("returned"),
  ROOMS_UNSPRAYED("room_unsprayed"),
  STRUCTURES_UNSPRAYED("structure_unsprayed"),
  HOUSEHOLDS_UNSPRAYED("household_unsprayed"),
  SPRAYED_ROOMS_SHARE("sprayedrooms_share"),
  SPRAYED_STRUCTURES_SHARE("sprayedstructures_share"),
  SPRAYED_HOUSEHOLDS_SHARE("sprayedhouseholds_share");
  
  private String alias;
  
  private String type;
  
  private Alias(String alias)
  {
    this.alias = alias;
    this.type = null;
  }
  
  private Alias(String alias, String type)
  {
    this(alias);
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
