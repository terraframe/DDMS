package dss.vector.solutions.querybuilder.irs;

import org.apache.commons.lang.ArrayUtils;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.irs.AbstractSpray;
import dss.vector.solutions.irs.HouseholdSprayStatus;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.querybuilder.IRSQB.View;
import dss.vector.solutions.util.QueryUtil;

public enum Alias implements Reloadable, AliasIF
{
  
  ID("id", "setId", SQLProvider.VARCHAR),
  UNIQUE_SPRAY_ID("unique_spray_id", "setUniqueSprayId", SQLProvider.VARCHAR),
  UNIQUE_PLANNED_ID("unique_planned_id", "setUniquePlannedId", SQLProvider.VARCHAR),
  CREATE_DATE("create_date", "setCreateDate", SQLProvider.DATETIME),
  LAST_UPDATE_DATE("last_update_date", "setLastUpdateDate", SQLProvider.DATETIME),
  CREATED_BY("created_by", "setCreatedBy", SQLProvider.VARCHAR),
  LAST_UPDATED_BY("last_updated_by", "setLastUpdatedBy", SQLProvider.VARCHAR),
  SPRAY_DATE("spray_date", "setSprayDate", SQLProvider.DATE, AbstractSpray.SPRAYDATE, new View[]{View.SPRAY_VIEW}),
  PLANNED_DATE("planned_date", "setPlannedDate", SQLProvider.DATE, new View[]{View.RESOURCE_TARGET_VIEW}),
  TARGET_WEEK("target_week", "setTargetWeek", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  TARGET("target", "setTarget", SQLProvider.VARCHAR, new View[]{View.ALL_ACTUALS}),
  AGGREGATION_LEVEL("aggregation_level", "setAggregationLevel", SQLProvider.VARCHAR),
  GEO_ENTITY("geo_entity", "setGeoEntity", SQLProvider.VARCHAR, AbstractSpray.GEOENTITY),
  CHILD_GEO_ENTITY("child_geo_entity", "setChildGeoEntity", SQLProvider.VARCHAR),
  PARENT_GEO_ENTITY("parent_geo_entity", "setParentGeoEntity", SQLProvider.VARCHAR),
  SPRAY_OPERATOR("spray_operator", "setSprayOperator", SQLProvider.TEXT, new View[]{View.SPRAY_VIEW}),
  SPRAY_TEAM("spray_team", "setSprayTeam", SQLProvider.TEXT, new View[]{View.SPRAY_VIEW}),
  SPRAY_METHOD("spray_method", "setSprayMethod", SQLProvider.TEXT, "sprayMethod_spray", new View[]{View.ALL_ACTUALS}),
  SURFACE_TYPE("surface_type", "setSurfaceType", SQLProvider.TEXT, "surfaceType_spray", new View[]{View.ALL_ACTUALS}),
  BRAND("brand", "setBrand", SQLProvider.VARCHAR, new View[]{View.ALL_ACTUALS}),
  HOUSEHOLD_ID("household_id", "setHouseholdId", SQLProvider.VARCHAR, new View[]{View.ALL_ACTUALS}),
  STRUCTURE_ID("structure_id", "setStructureId", SQLProvider.VARCHAR, new View[]{View.ALL_ACTUALS}),
  SPRAY_OPERATOR_DEFAULT_LOCALE("sprayoperator_defaultLocale", "setSprayOperatorDefaultLocale", SQLProvider.TEXT, new View[]{View.SPRAY_VIEW, View.PLANNED_OPERATOR, View.PLANNED_TEAM, View.PLANNED_AREA}),
  SPRAY_OPERATOR_PERSON_ID("sprayoperator_personId", "setSprayOperatorPersonId", SQLProvider.TEXT, new View[]{View.SPRAY_VIEW, View.PLANNED_OPERATOR, View.PLANNED_TEAM, View.PLANNED_AREA}),
  SPRAY_OPERATOR_PERSON("sprayoperator_person", "setSprayOperatorPerson", SQLProvider.VARCHAR, new View[]{View.SPRAY_VIEW, View.PLANNED_OPERATOR, View.PLANNED_TEAM, View.PLANNED_AREA}),
  SPRAY_OPERATOR_BIRTHDATE("sprayoperator_birthdate", "setSprayOperatorBirthdate", SQLProvider.DATE, new View[]{View.SPRAY_VIEW, View.PLANNED_OPERATOR, View.PLANNED_TEAM, View.PLANNED_AREA}),
  SPRAY_OPERATOR_SEX("sprayoperator_sex", "setSprayOperatorSex", SQLProvider.TEXT, new View[]{View.SPRAY_VIEW, View.PLANNED_OPERATOR, View.PLANNED_TEAM, View.PLANNED_AREA}),
  OPERATOR_ACTUAL_TARGET("operator_actual_target", "setOperatorActualTarget", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  SPRAY_TEAM_DEFAULT_LOCALE("sprayteam_defaultLocale", "setSprayTeamDefaultLocale", SQLProvider.TEXT, new View[]{View.SPRAY_VIEW, View.PLANNED_OPERATOR, View.PLANNED_TEAM, View.PLANNED_AREA}),
  SPRAY_LEADER_DEFAULT_LOCALE("sprayleader_defaultLocale", "setSprayLeaderDefaultLocale", SQLProvider.TEXT, new View[]{View.SPRAY_VIEW, View.ALL_ACTUALS}),
  SPRAY_LEADER_PERSON_ID("sprayleader_personId", "setSprayLeaderPersonId", SQLProvider.TEXT, new View[]{View.SPRAY_VIEW, View.ALL_ACTUALS}),
  SPRAY_LEADER_PERSON("sprayleader_person", "setSprayLeaderPerson", SQLProvider.VARCHAR, new View[]{View.SPRAY_VIEW, View.ALL_ACTUALS}),
  SPRAY_LEADER_BIRTHDATE("sprayleader_birthdate", "setSprayLeaderBirthdate", SQLProvider.DATE, new View[]{View.SPRAY_VIEW, View.ALL_ACTUALS}),
  SPRAY_LEADER_SEX("sprayleader_sex", "setSprayLeaderSex", SQLProvider.TEXT, new View[]{View.SPRAY_VIEW, View.ALL_ACTUALS}),
  TEAM_ACTUAL_TARGET("team_actual_target", "setTeamActualTarget", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  ZONE_SUPERVISOR_DEFAULT_LOCALE("zone_supervisor_defaultLocale", "setZoneSuperVisorDefaultLocale", SQLProvider.TEXT, new View[]{View.SPRAY_VIEW}),
  ZONE_SUPERVISOR_PERSON_ID("zone_supervisor_personId", "setZoneSuperVisorPersonId", SQLProvider.TEXT, new View[]{View.SPRAY_VIEW}),
  ZONE_SUPERVISOR_PERSON("zone_supervisor_person", "setZoneSuperVisorPerson", SQLProvider.VARCHAR, new View[]{View.SPRAY_VIEW}),
  ZONE_SUPERVISOR_BIRTHDATE("zone_supervisor_birthdate", "setZoneSuperVisorBirthdate", SQLProvider.DATE, new View[]{View.SPRAY_VIEW}),
  ZONE_SUPERVISOR_SEX("zone_supervisor_sex", "setZoneSuperVisorSex", SQLProvider.TEXT, new View[]{View.SPRAY_VIEW}),
  SPRAY_SEASON("spray_season", "setSpraySeason", SQLProvider.VARCHAR, new View[]{View.SPRAY_VIEW, View.PLANNED_OPERATOR, View.PLANNED_TEAM, View.PLANNED_AREA}),
  OPERATOR_PLANNED_TARGET("operator_planned_target", "setOperatorPlannedTarget", SQLProvider.FLOAT, new View[]{View.SPRAY_VIEW, View.PLANNED_OPERATOR}),
  TEAM_PLANNED_TARGET("team_planned_target", "setTeamPlannedTarget", SQLProvider.FLOAT, new View[]{View.SPRAY_VIEW, View.PLANNED_TEAM}),
  AREA_PLANNED_TARGET("area_planned_target", "setAreaPlannedTarget", SQLProvider.FLOAT, new View[]{View.SPRAY_VIEW, View.PLANNED_AREA}),
  ROOMS("rooms", "setRooms", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  STRUCTURES("structures", "setStructures", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  HOUSEHOLDS("households", "setHouseholds", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  SPRAYED_ROOMS("sprayedRooms", "setSprayedRooms", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  SPRAYED_STRUCTURES("sprayedStructures", "setSprayedStructures", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  SPRAYED_HOUSEHOLDS("sprayedHouseholds", "setSprayedHouseholds", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  PREV_SPRAYED_STRUCTURES("prevSprayedStructures", "setPrevSprayedStructures", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  PREV_SPRAYED_HOUSEHOLDS("prevSprayedHouseholds", "setPrevSprayedHouseholds", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  PEOPLE("people", "setPeople", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  BEDNETS("bedNets", "setBedNets", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  NOZZLES_USED("nozzlesUsed", "setNozzlesUsed", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  PUMPS_USED("pumpsUsed", "setPumpsUsed", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  ROOMS_WITH_BED_NETS("roomsWithBedNets", "setRoomsWithBedNets", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  LOCKED("locked", "setLocked", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  REFUSED("refused", "setRefused", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  OTHER("other", "setOther", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  VERANDAS("verandas", "setVerandas", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  VERANDAS_SPRAYED("verandasSprayed", "setVerandasSprayed", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  VERANDAS_LOCKED("verandasLocked", "setVerandasLocked", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  VERANDAS_REFUSED("verandasRefused", "setVerandasRefused", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  VERANDAS_OTHER("verandasOther", "setVerandasOther", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  CATTLESHEDS("cattleSheds", "setCattleSheds", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  CATTLESHEDS_SPRAYED("cattleShedsSprayed", "setCattleShedsSprayed", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  CATTLESHEDS_LOCKED("cattleShedsLocked", "setCattleShedsLocked", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  CATTLESHEDS_REFUSED("cattleShedsRefused", "setCattleShedsRefused", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  CATTLESHEDS_OTHER("cattleShedsOther", "setCattleShedsOther", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  NUMBER_OF_PEOPLE("numberOfPeople", "setNumberOfPeople", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  WRONG_SURFACE("wrongSurface", "setWrongSurface", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  DISEASE("disease", "setDisease", SQLProvider.VARCHAR, new View[]{View.SPRAY_VIEW, View.PLANNED_OPERATOR, View.PLANNED_TEAM, View.PLANNED_AREA}),
  RECEIVED("received", "setReceived", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  USED("used", "setUsed", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  REFILLS("refills", "setRefills", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  RETURNED("returned", "setReturned", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  ROOMS_UNSPRAYED("room_unsprayed", "setRoomUnsprayed", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  STRUCTURES_UNSPRAYED("structure_unsprayed", "setStructureUnsprayed", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  HOUSEHOLDS_UNSPRAYED("household_unsprayed", "setHouseholdUnsprayed", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  SPRAYED_ROOMS_SHARE("sprayedrooms_share", "setSprayedRoomsShare", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  SPRAYED_STRUCTURES_SHARE("sprayedstructures_share", "setSprayedStructuresShare", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  SPRAYED_HOUSEHOLDS_SHARE("sprayedhouseholds_share", "setSprayedHouseholdsShare", SQLProvider.FLOAT, new View[]{View.ALL_ACTUALS}),
  STRUCTURE_TYPE(HouseholdSprayStatus.STRUCTURETYPE, "setStructureType", SQLProvider.TEXT, new View[]{View.ALL_ACTUALS}),
  REASON_NOT_SPRAYED(HouseholdSprayStatus.REASONNOTSPRAYED, "setReasonNotSprayed", SQLProvider.TEXT, new View[]{View.ALL_ACTUALS}),
  
  // New details as of 3792:
  STRUCTURES_NOT_SPRAYED_SICK(HouseholdSprayStatus.STRUCTURESNOTSPRAYEDSICK, "setStructuresNotSprayedSick", SQLProvider.INTEGER, new View[]{View.ALL_ACTUALS}),
  STRUCTURES_NOT_SPRAYED_LOCKED(HouseholdSprayStatus.STRUCTURESNOTSPRAYEDLOCKED, "setStructuresNotSprayedLocked", SQLProvider.INTEGER, new View[]{View.ALL_ACTUALS}),
  STRUCTURES_NOT_SPRAYED_FUNERAL(HouseholdSprayStatus.STRUCTURESNOTSPRAYEDFUNERAL, "setStructuresNotSprayedFuneral", SQLProvider.INTEGER, new View[]{View.ALL_ACTUALS}),
  STRUCTURES_NOT_SPRAYED_REFUSED(HouseholdSprayStatus.STRUCTURESNOTSPRAYEDREFUSED, "setStructuresNotSprayedRefused", SQLProvider.INTEGER, new View[]{View.ALL_ACTUALS}),
  STRUCTURES_NOT_SPRAYED_NO_ONE_HOME(HouseholdSprayStatus.STRUCTURESNOTSPRAYEDNOONEHOME, "setStructuresNotSprayedNoOneHome", SQLProvider.INTEGER, new View[]{View.ALL_ACTUALS}),
  STRUCTURES_NOT_SPRAYED_OTHER(HouseholdSprayStatus.STRUCTURESNOTSPRAYEDOTHER, "setStructuresNotSprayedOther", SQLProvider.INTEGER, new View[]{View.ALL_ACTUALS}),
  NUMBER_MALES_PROTECTED(HouseholdSprayStatus.NUMBERMALESPROTECTED, "setNumberMalesProtected", SQLProvider.INTEGER, new View[]{View.ALL_ACTUALS}),
  NUMBER_FEMALES_PROTECTED(HouseholdSprayStatus.NUMBERFEMALESPROTECTED, "setNumberFemalesProtected", SQLProvider.INTEGER, new View[]{View.ALL_ACTUALS}),
  NUMBER_PREGNANT_WOMEN_PROTECTED(HouseholdSprayStatus.NUMBERPREGNANTWOMENPROTECTED, "setNumberPregnantWomenProtected", SQLProvider.INTEGER, new View[]{View.ALL_ACTUALS}),
  NUMBER_CHILDREN_UNDER_FIVE_PROTECTED(HouseholdSprayStatus.NUMBERCHILDRENUNDERFIVEPROTECTED, "setNumberChildrenUnderFiveProtected", SQLProvider.INTEGER, new View[]{View.ALL_ACTUALS}),
  NUMBER_ROOMS_NOT_SPAYED_SICK(HouseholdSprayStatus.NUMBERROOMSNOTSPRAYEDSICK, "setNumberRoomsNotSprayedSick", SQLProvider.INTEGER, new View[]{View.ALL_ACTUALS}),
  NUMBER_ITNS_IN_USE(HouseholdSprayStatus.NUMBERITNSINUSE, "setNumberItnsInUse", SQLProvider.INTEGER, new View[]{View.ALL_ACTUALS}),
  NUMBER_PEOPLE_SLEEPING_UNDER_ITNS(HouseholdSprayStatus.NUMBERPEOPLESLEEPINGUNDERITNS, "setNumberPeopleSleepingUnderItns", SQLProvider.INTEGER, new View[]{View.ALL_ACTUALS}),
  NUMBER_PREGNANT_WOMEN_SLEEPING_UNDER_ITNS(HouseholdSprayStatus.NUMBERPREGNANTWOMENSLEEPINGUNDERITNS, "setNumberPregnantWomenSleepingUnderItns", SQLProvider.INTEGER, new View[]{View.ALL_ACTUALS}),
  NUMBER_CHILDREN_UNDER_FIVE_SLEEPING_UNDER_ITNS(HouseholdSprayStatus.NUMBERCHILDRENUNDERFIVESLEEPINGUNDERITNS, "setNumberChildrenUnderFiveSleepingUnderItns", SQLProvider.INTEGER, new View[]{View.ALL_ACTUALS}),
  
  /*
   *  CUSTOM (not specified on any level as a column)
   */
  
  // operator planned targets
  OPERATOR_PLANNED_COVERAGE("operator_planned_coverage", null, SQLProvider.FLOAT),
  OPERATOR_TARGET_DIVERGENCE("operator_target_divergence", null, SQLProvider.FLOAT),
  OPERATOR_TARGETED_COVERAGE("operator_targeted_coverage", null, SQLProvider.FLOAT),
  
  // area planned targets
  AREA_PLANNED_COVERAGE("area_planned_coverage", null, SQLProvider.FLOAT),
  
  // team planned target
  TEAM_TARGET_DIVERGENCE("team_target_divergence", null, SQLProvider.FLOAT),
  TEAM_PLANNED_COVERAGE("team_planned_coverage", null, SQLProvider.FLOAT),
  TEAM_TARGETED_COVERAGE("team_targeted_coverage", null, SQLProvider.FLOAT),
  
  
  // time grouping
  DATEGROUP_SEASON(QueryUtil.DATEGROUP_SEASON, "setDategroupSeason", SQLProvider.VARCHAR),
  DATEGROUP_YEAR(QueryUtil.DATEGROUP_CALENDARYEAR, "setDategroupYear", SQLProvider.VARCHAR),
  DATEGROUP_QUARTER(QueryUtil.DATEGROUP_QUARTER, "setDategroupQuarter", SQLProvider.VARCHAR),
  DATEGROUP_MONTH(QueryUtil.DATEGROUP_MONTH, "setDategroupMonth", SQLProvider.VARCHAR),
  DATEGROUP_EPIYEAR(QueryUtil.DATEGROUP_EPIYEAR, "setDategroupEpiYear", SQLProvider.VARCHAR),
  DATEGROUP_EPIWEEK(QueryUtil.DATEGROUP_EPIWEEK, "setDategroupEpiWeek", SQLProvider.VARCHAR),
  RATIO(QueryUtil.RATIO, null, SQLProvider.VARCHAR),
  COUNT("dss_vector_solutions_irs_AbstractSpray__id", null, SQLProvider.VARCHAR),
  
  // Audit
  AUDIT_CREATE_DATE(QueryConstants.AUDIT_CREATE_DATE_ALIAS, null, SQLProvider.DATE),
  AUDIT_LAST_UPDATE_DATE(QueryConstants.AUDIT_LAST_UPDATE_DATE_ALIAS, null, SQLProvider.DATE),
  AUDIT_CREATED_BY(QueryConstants.AUDIT_CREATED_BY_ALIAS, null, SQLProvider.VARCHAR),
  AUDIT_LAST_UPDATED_BY(QueryConstants.AUDIT_LAST_UPDATED_BY_ALIAS, null, SQLProvider.VARCHAR),
  AUDIT_IMPORTED(QueryConstants.AUDIT_IMPORTED_ALIAS, "setAuditImported", SQLProvider.FLOAT),
  
  SPRAYED_UNITS("sprayedunits", null, SQLProvider.FLOAT),
  UNITS_UNSPRAYED("unit_unsprayed", null, SQLProvider.FLOAT),
  
  // Insecticide
  PRODUCT_NAME("productName_spray", null, SQLProvider.VARCHAR),
  ACTIVE_INGREDIANT("activeIngredient_spray", null, SQLProvider.VARCHAR),
  CONCENTRATION_QUANTIFIER("concentrationQuantifier_spray", null, SQLProvider.FLOAT),
  CONCENTRATION_QUALIFIER("concentrationQualifier_spray", null, SQLProvider.VARCHAR),
  INSECTICIDE_USE("insecticideUse_spray", null, SQLProvider.VARCHAR),
  USE_DETAIL("useDetail_spray", null, SQLProvider.VARCHAR),
  UNITS_PER_APPLICATION("unitsPerApplication_spray", null, SQLProvider.FLOAT),
  UNIT_QUANTIFIER("unitQuantifier_spray", null, SQLProvider.VARCHAR),
  UNIT_QUALIFIER("unitQualifier_spray", null, SQLProvider.VARCHAR),
  ACTIVE_INGREDIENT_PER_CAN("active_ingredient_per_can", null, SQLProvider.VARCHAR),
  STANDARD_APPLICATION_RATE("standard_application_rate", null, SQLProvider.VARCHAR),
  STANDARD_APPLICATION_RAGE_MG("standard_application_rate_mg", null, SQLProvider.VARCHAR),
  UNITS_PER_CAN("units_per_can", null, SQLProvider.VARCHAR),

  // InsecticideView
  SPRAY_UNIT("spray_unit", "setSprayUnit", SQLProvider.VARCHAR),
  UNIT_AREA("unit_area", "setUnitArea", SQLProvider.VARCHAR),
  
  
  // Calculations
  UNIT_APPLICATION_RATE("unit_application_rate", null, SQLProvider.FLOAT),
  UNIT_APPLICATION_RATE_MG("unit_application_rate_mg", null, SQLProvider.FLOAT),
  UNIT_APPLICATION_RATIO("unit_application_ratio", null, SQLProvider.FLOAT),
  UNIT_OPERATIONAL_COVERAGE("unit_operational_coverage", null, SQLProvider.FLOAT),
  CALCULATED_ROOMS_SPRAYED("calculated_rooms_sprayed", null, SQLProvider.FLOAT),
  CALCULATED_STRUCTURES_SPRAYED("calculated_structures_sprayed", null, SQLProvider.FLOAT),
  CALCULATED_HOUSEHOLDS_SPRAYED("calculated_households_sprayed", null, SQLProvider.FLOAT);

  
  private String alias;
  
  /**
   * The method to execute to add the column and its alias to the query. If the value
   * is null that means don't execute anything as somewhere else in the code is probably handling
   * the logic (eg, it could be a calculation or passthrough).
   */
  private String method;
  
  private String type;
  
  private String xmlAlias;
  
  private View[] associatedView;
  
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
  
  private Alias(String alias, String method, String type, View[] view)
  {
    this(alias, method, type, alias, view);
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
    this.associatedView = null;
  }
  
  private Alias(String alias, String method, String type, String xmlAlias, View[] view)
  {
    this.alias = alias;
    this.method = method;
    this.type = type;
    this.xmlAlias = xmlAlias;
    this.associatedView = view;
  }
  
  public View[] getViews()
  {
    return this.associatedView;
  }
  
  public boolean hasView(View view) {
    if (this.getViews() == null) { return false; }
    
    return ArrayUtils.contains(this.getViews(), view);
  }
  
  public String getXmlAlias()
  {
    return xmlAlias;
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
