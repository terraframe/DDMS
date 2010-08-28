package dss.vector.solutions.irs;

import com.runwaysdk.generation.loader.Reloadable;

public interface IRSUnionIF extends Reloadable
{
  static final String RESOURCE_TARGET_VIEW = "resourceTargetView";
  
  public static final String INTEGER = "integer";
  public static final String TEXT = "text";
  public static final String DATE = "date";
  
  // get/set owning IRSQuery
  void setIRSQuery(IRSQuery irsQuery);
  
  // template methods
  String setId(Alias alias);
  String setSprayDate(Alias alias);
  String setTargetWeek(Alias alias);
  String setBrand(Alias alias);
  String setGeoEntity(Alias alias);
  String setSprayMethod(Alias alias);
  String setSurfaceType(Alias alias);
  String setAggregationLevel(Alias alias);
  String setHouseholdId(Alias alias);
  String setStructureId(Alias alias);
  String setSprayOperatorDefaultLocale(Alias alias);
  String setOperatorActualTarget(Alias alias);
  String setSprayTeamDefaultLocale(Alias alias);
  String setSprayLeaderDefaultLocale(Alias alias);
  String setTeamActualTarget(Alias alias);
  String setZoneSuperVisorDefaultLocale(Alias alias);
  String setSpraySeason(Alias alias);
  String setOperatorPlannedTarget(Alias alias);
  String setTeamPlannedTarget(Alias alias);
  String setAreaPlannedTarget(Alias alias);
  String setRooms(Alias alias);
  String setStructures(Alias alias);
  String setHouseholds(Alias alias);
  String setSprayedRooms(Alias alias);
  String setSprayedStructures(Alias alias);
  String setSprayedHouseholds(Alias alias);
  String setPrevSprayedStructures(Alias alias);
  String setPrevSprayedHouseholds(Alias alias);
  String setPeople(Alias alias);
  String setBedNets(Alias alias);
  String setRoomsWithBedNets(Alias alias);
  String setLocked(Alias alias);
  String setRefused(Alias alias);
  String setOther(Alias alias);
  String setDisease(Alias alias);
  String setReceived(Alias alias);
  String setUsed(Alias alias);
  String setRefills(Alias alias);
  String setReturned(Alias alias);
  String setRoomUnsprayed(Alias alias);
  String setStructureUnsprayed(Alias alias);
  String setHouseholdUnsprayed(Alias alias);
  String setSprayedRoomsShare(Alias alias);
  String setSprayedStructuresShare(Alias alias);
  String setSprayedHouseholdsShare(Alias alias);
  
  String from();
  String where();
}
