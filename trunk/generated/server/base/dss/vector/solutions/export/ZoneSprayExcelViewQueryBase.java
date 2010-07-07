package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = 137002498)
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
private static final long serialVersionUID = 137002498;

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
 
  public com.runwaysdk.query.SelectableInteger getSprayWeek()
  {
    return getSprayWeek(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getSprayWeek(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.SPRAYWEEK, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getSprayWeek(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.SPRAYWEEK, alias, displayLabel);

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
 
  public com.runwaysdk.query.SelectableInteger getTarget()
  {
    return getTarget(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.TARGET, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTarget(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.TARGET, alias, displayLabel);

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
 
  public com.runwaysdk.query.SelectableInteger getTeamSprayWeek()
  {
    return getTeamSprayWeek(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTeamSprayWeek(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.TEAMSPRAYWEEK, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getTeamSprayWeek(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ZoneSprayExcelView.TEAMSPRAYWEEK, alias, displayLabel);

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
