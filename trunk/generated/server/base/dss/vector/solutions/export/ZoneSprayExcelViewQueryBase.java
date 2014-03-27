package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = 652481364)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ZoneSprayExcelView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class ZoneSprayExcelViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public ZoneSprayExcelViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public ZoneSprayExcelViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.export.ZoneSprayExcelView.CLASS;
  }
  public com.runwaysdk.query.SelectableInteger getBedNets()
  {
    return getBedNets(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getBedNets(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.BEDNETS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getBedNets(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.BEDNETS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getCattleSheds()
  {
    return getCattleSheds(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getCattleSheds(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.CATTLESHEDS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getCattleSheds(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.CATTLESHEDS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getCattleShedsLocked()
  {
    return getCattleShedsLocked(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getCattleShedsLocked(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.CATTLESHEDSLOCKED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getCattleShedsLocked(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.CATTLESHEDSLOCKED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getCattleShedsOther()
  {
    return getCattleShedsOther(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getCattleShedsOther(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.CATTLESHEDSOTHER, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getCattleShedsOther(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.CATTLESHEDSOTHER, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getCattleShedsRefused()
  {
    return getCattleShedsRefused(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getCattleShedsRefused(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.CATTLESHEDSREFUSED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getCattleShedsRefused(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.CATTLESHEDSREFUSED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getCattleShedsSprayed()
  {
    return getCattleShedsSprayed(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getCattleShedsSprayed(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.CATTLESHEDSSPRAYED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getCattleShedsSprayed(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.CATTLESHEDSSPRAYED, alias, displayLabel);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity()
  {
    return getGeoEntity(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.GEOENTITY, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias, String displayLabel)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.GEOENTITY, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getHouseholds()
  {
    return getHouseholds(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getHouseholds(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.HOUSEHOLDS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getHouseholds(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.HOUSEHOLDS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.ID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getInsecticideTerm()
  {
    return getInsecticideTerm(null);

  }
 
  public com.runwaysdk.query.SelectableChar getInsecticideTerm(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.INSECTICIDETERM, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getInsecticideTerm(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.INSECTICIDETERM, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getLeaderId()
  {
    return getLeaderId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getLeaderId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.LEADERID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getLeaderId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.LEADERID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getLocked()
  {
    return getLocked(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getLocked(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.LOCKED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getLocked(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.LOCKED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNozzlesUsed()
  {
    return getNozzlesUsed(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNozzlesUsed(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.NOZZLESUSED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNozzlesUsed(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.NOZZLESUSED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getOther()
  {
    return getOther(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getOther(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.OTHER, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getOther(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.OTHER, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getPeople()
  {
    return getPeople(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPeople(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.PEOPLE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPeople(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.PEOPLE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getPrevSprayedHouseholds()
  {
    return getPrevSprayedHouseholds(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPrevSprayedHouseholds(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.PREVSPRAYEDHOUSEHOLDS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPrevSprayedHouseholds(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.PREVSPRAYEDHOUSEHOLDS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getPrevSprayedStructures()
  {
    return getPrevSprayedStructures(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPrevSprayedStructures(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.PREVSPRAYEDSTRUCTURES, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPrevSprayedStructures(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.PREVSPRAYEDSTRUCTURES, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getPumpsUsed()
  {
    return getPumpsUsed(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPumpsUsed(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.PUMPSUSED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPumpsUsed(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.PUMPSUSED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getRefused()
  {
    return getRefused(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getRefused(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.REFUSED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getRefused(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.REFUSED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getRooms()
  {
    return getRooms(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getRooms(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.ROOMS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getRooms(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.ROOMS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getRoomsWithBedNets()
  {
    return getRoomsWithBedNets(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getRoomsWithBedNets(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.ROOMSWITHBEDNETS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getRoomsWithBedNets(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.ROOMSWITHBEDNETS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableMoment getSprayDate()
  {
    return getSprayDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getSprayDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.SPRAYDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getSprayDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.SPRAYDATE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getSprayMethod()
  {
    return getSprayMethod(null);

  }
 
  public com.runwaysdk.query.SelectableChar getSprayMethod(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.SPRAYMETHOD, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getSprayMethod(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.SPRAYMETHOD, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getSprayTeam()
  {
    return getSprayTeam(null);

  }
 
  public com.runwaysdk.query.SelectableChar getSprayTeam(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.SPRAYTEAM, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getSprayTeam(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.SPRAYTEAM, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getSprayedHouseholds()
  {
    return getSprayedHouseholds(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getSprayedHouseholds(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.SPRAYEDHOUSEHOLDS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getSprayedHouseholds(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.SPRAYEDHOUSEHOLDS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getSprayedRooms()
  {
    return getSprayedRooms(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getSprayedRooms(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.SPRAYEDROOMS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getSprayedRooms(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.SPRAYEDROOMS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getSprayedStructures()
  {
    return getSprayedStructures(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getSprayedStructures(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.SPRAYEDSTRUCTURES, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getSprayedStructures(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.SPRAYEDSTRUCTURES, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getStructures()
  {
    return getStructures(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getStructures(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.STRUCTURES, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getStructures(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.STRUCTURES, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getSupervisorName()
  {
    return getSupervisorName(null);

  }
 
  public com.runwaysdk.query.SelectableChar getSupervisorName(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.SUPERVISORNAME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getSupervisorName(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.SUPERVISORNAME, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getSupervisorSurname()
  {
    return getSupervisorSurname(null);

  }
 
  public com.runwaysdk.query.SelectableChar getSupervisorSurname(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.SUPERVISORSURNAME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getSupervisorSurname(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.SUPERVISORSURNAME, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getSurfaceType()
  {
    return getSurfaceType(null);

  }
 
  public com.runwaysdk.query.SelectableChar getSurfaceType(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.SURFACETYPE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getSurfaceType(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.SURFACETYPE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTeamReceived()
  {
    return getTeamReceived(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTeamReceived(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.TEAMRECEIVED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTeamReceived(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.TEAMRECEIVED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTeamRefills()
  {
    return getTeamRefills(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTeamRefills(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.TEAMREFILLS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTeamRefills(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.TEAMREFILLS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTeamReturned()
  {
    return getTeamReturned(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTeamReturned(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.TEAMRETURNED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTeamReturned(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.TEAMRETURNED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTeamTarget()
  {
    return getTeamTarget(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTeamTarget(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.TEAMTARGET, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTeamTarget(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.TEAMTARGET, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getTeamUsed()
  {
    return getTeamUsed(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTeamUsed(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.TEAMUSED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTeamUsed(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.TEAMUSED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getVerandas()
  {
    return getVerandas(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getVerandas(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.VERANDAS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getVerandas(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.VERANDAS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getVerandasLocked()
  {
    return getVerandasLocked(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getVerandasLocked(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.VERANDASLOCKED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getVerandasLocked(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.VERANDASLOCKED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getVerandasOther()
  {
    return getVerandasOther(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getVerandasOther(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.VERANDASOTHER, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getVerandasOther(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.VERANDASOTHER, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getVerandasRefused()
  {
    return getVerandasRefused(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getVerandasRefused(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.VERANDASREFUSED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getVerandasRefused(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.VERANDASREFUSED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getVerandasSprayed()
  {
    return getVerandasSprayed(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getVerandasSprayed(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.VERANDASSPRAYED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getVerandasSprayed(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.VERANDASSPRAYED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getWrongSurface()
  {
    return getWrongSurface(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getWrongSurface(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.WRONGSURFACE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getWrongSurface(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.WRONGSURFACE, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends ZoneSprayExcelView> getIterator()
  {
    com.runwaysdk.query.ValueIterator valueIterator;
    if (_pageSize != null && _pageNumber != null)
    {
      valueIterator = (com.runwaysdk.query.ValueIterator<com.runwaysdk.dataaccess.ValueObject>)this.getComponentQuery().getIterator(_pageSize, _pageNumber);
    }
    else
    {
      valueIterator = (com.runwaysdk.query.ValueIterator<com.runwaysdk.dataaccess.ValueObject>)this.getComponentQuery().getIterator();
    }
    return new com.runwaysdk.query.ViewIterator<ZoneSprayExcelView>(this.getMdClassIF(), valueIterator);
  }

}
