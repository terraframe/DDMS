package dss.vector.solutions.querybuilder.irs;

import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.Metadata;

import dss.vector.solutions.querybuilder.IRSQB;
import dss.vector.solutions.util.QueryUtil;

public enum Alias implements Reloadable
{
  
  ID("id", IRSUnionIF.VARCHAR),
  CREATE_DATE(Metadata.getCreateDateMd(), IRSUnionIF.DATETIME),
  LAST_UPDATE_DATE(Metadata.getLastUpdateDateMd(), IRSUnionIF.DATETIME),
  CREATED_BY(Metadata.getCreatedByMd(), IRSUnionIF.VARCHAR),
  LAST_UPDATED_BY(Metadata.getLastUpdatedByMd(), IRSUnionIF.VARCHAR),
  SPRAY_DATE("spray_date", IRSUnionIF.DATE),
  PLANNED_DATE("planned_date", IRSUnionIF.DATE),
  TARGET_WEEK("target_week", IRSUnionIF.FLOAT),
  AGGREGATION_LEVEL("aggregation_level", IRSUnionIF.TEXT),
  GEO_ENTITY("geo_entity", IRSUnionIF.VARCHAR),
  SPRAY_OPERATOR("spray_operator", IRSUnionIF.TEXT),
  SPRAY_TEAM("spray_team", IRSUnionIF.TEXT),
  SPRAY_METHOD("spray_method", IRSUnionIF.TEXT),
  SURFACE_TYPE("surface_type", IRSUnionIF.TEXT),
  BRAND("brand", IRSUnionIF.VARCHAR),
  HOUSEHOLD_ID("household_id", IRSUnionIF.VARCHAR),
  STRUCTURE_ID("structure_id", IRSUnionIF.VARCHAR),
  SPRAY_OPERATOR_DEFAULT_LOCALE("sprayoperator_defaultLocale", IRSUnionIF.TEXT),
  OPERATOR_ACTUAL_TARGET(IRSQB.OPERATOR_ACTUAL_TARGET, IRSUnionIF.FLOAT),
  SPRAY_TEAM_DEFAULT_LOCALE("sprayteam_defaultLocale", IRSUnionIF.TEXT),
  SPRAY_LEADER_DEFAULT_LOCALE("sprayleader_defaultLocale", IRSUnionIF.TEXT),
  TEAM_ACTUAL_TARGET(IRSQB.TEAM_ACTUAL_TARGET, IRSUnionIF.FLOAT),
  ZONE_SUPERVISOR_DEFAULT_LOCALE("zone_supervisor_defaultLocale", IRSUnionIF.TEXT),
  SPRAY_SEASON("spray_season", IRSUnionIF.VARCHAR),
  OPERATOR_PLANNED_TARGET(IRSQB.OPERATOR_PLANNED_TARGET, IRSUnionIF.FLOAT),
  TEAM_PLANNED_TARGET(IRSQB.TEAM_PLANNED_TARGET, IRSUnionIF.FLOAT),
  AREA_PLANNED_TARGET(IRSQB.AREA_PLANNED_TARGET, IRSUnionIF.FLOAT),
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
  WRONG_SURFACE("wrongSurface", IRSUnionIF.FLOAT),
  DISEASE("disease", IRSUnionIF.VARCHAR),    
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
  
  private Alias(MdAttributeDAOIF mdAttr, String type)
  {
    this.alias = QueryUtil.getColumnName(mdAttr);
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
