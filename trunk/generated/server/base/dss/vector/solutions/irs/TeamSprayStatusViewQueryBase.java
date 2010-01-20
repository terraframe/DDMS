package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = -1933320879)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to TeamSprayStatusView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class TeamSprayStatusViewQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -1933320879;

  public TeamSprayStatusViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public TeamSprayStatusViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.irs.TeamSprayStatusView.CLASS;
  }
  public com.terraframe.mojo.query.AttributeInteger getBedNets()
  {
    return getBedNets(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getBedNets(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.BEDNETS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getBedNets(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.BEDNETS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getConcreteId()
  {
    return getConcreteId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getConcreteId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.CONCRETEID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getConcreteId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.CONCRETEID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getHouseholds()
  {
    return getHouseholds(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getHouseholds(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.HOUSEHOLDS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getHouseholds(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.HOUSEHOLDS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.ID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getLocked()
  {
    return getLocked(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getLocked(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.LOCKED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getLocked(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.LOCKED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getOther()
  {
    return getOther(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getOther(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.OTHER, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getOther(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.OTHER, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getPeople()
  {
    return getPeople(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getPeople(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.PEOPLE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getPeople(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.PEOPLE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getPrevSprayedHouseholds()
  {
    return getPrevSprayedHouseholds(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getPrevSprayedHouseholds(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.PREVSPRAYEDHOUSEHOLDS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getPrevSprayedHouseholds(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.PREVSPRAYEDHOUSEHOLDS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getPrevSprayedStructures()
  {
    return getPrevSprayedStructures(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getPrevSprayedStructures(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.PREVSPRAYEDSTRUCTURES, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getPrevSprayedStructures(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.PREVSPRAYEDSTRUCTURES, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getReceived()
  {
    return getReceived(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getReceived(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.RECEIVED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getReceived(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.RECEIVED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getRefills()
  {
    return getRefills(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getRefills(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.REFILLS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getRefills(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.REFILLS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getRefused()
  {
    return getRefused(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getRefused(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.REFUSED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getRefused(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.REFUSED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getReturned()
  {
    return getReturned(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getReturned(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.RETURNED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getReturned(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.RETURNED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getRooms()
  {
    return getRooms(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getRooms(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.ROOMS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getRooms(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.ROOMS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getRoomsWithBedNets()
  {
    return getRoomsWithBedNets(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getRoomsWithBedNets(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.ROOMSWITHBEDNETS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getRoomsWithBedNets(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.ROOMSWITHBEDNETS, alias, displayLabel);

  }
 
  public dss.vector.solutions.irs.ZoneSprayQuery.ZoneSprayQueryReferenceIF getSpray()
  {
    return getSpray(null);

  }
 
  public dss.vector.solutions.irs.ZoneSprayQuery.ZoneSprayQueryReferenceIF getSpray(String alias)
  {

    return (dss.vector.solutions.irs.ZoneSprayQuery.ZoneSprayQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.SPRAY, alias, null);

  }
 
  public dss.vector.solutions.irs.ZoneSprayQuery.ZoneSprayQueryReferenceIF getSpray(String alias, String displayLabel)
  {

    return (dss.vector.solutions.irs.ZoneSprayQuery.ZoneSprayQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.SPRAY, alias, displayLabel);

  }
  public dss.vector.solutions.irs.SprayTeamQuery.SprayTeamQueryReferenceIF getSprayTeam()
  {
    return getSprayTeam(null);

  }
 
  public dss.vector.solutions.irs.SprayTeamQuery.SprayTeamQueryReferenceIF getSprayTeam(String alias)
  {

    return (dss.vector.solutions.irs.SprayTeamQuery.SprayTeamQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.SPRAYTEAM, alias, null);

  }
 
  public dss.vector.solutions.irs.SprayTeamQuery.SprayTeamQueryReferenceIF getSprayTeam(String alias, String displayLabel)
  {

    return (dss.vector.solutions.irs.SprayTeamQuery.SprayTeamQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.SPRAYTEAM, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getSprayedHouseholds()
  {
    return getSprayedHouseholds(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getSprayedHouseholds(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.SPRAYEDHOUSEHOLDS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getSprayedHouseholds(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.SPRAYEDHOUSEHOLDS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getSprayedRooms()
  {
    return getSprayedRooms(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getSprayedRooms(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.SPRAYEDROOMS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getSprayedRooms(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.SPRAYEDROOMS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getSprayedStructures()
  {
    return getSprayedStructures(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getSprayedStructures(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.SPRAYEDSTRUCTURES, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getSprayedStructures(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.SPRAYEDSTRUCTURES, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getStructures()
  {
    return getStructures(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getStructures(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.STRUCTURES, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getStructures(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.STRUCTURES, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getTarget()
  {
    return getTarget(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getTarget(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.TARGET, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getTarget(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.TARGET, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getTeamLabel()
  {
    return getTeamLabel(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getTeamLabel(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.TEAMLABEL, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getTeamLabel(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.TEAMLABEL, alias, displayLabel);

  }
 
  public dss.vector.solutions.irs.TeamMemberQuery.TeamMemberQueryReferenceIF getTeamLeader()
  {
    return getTeamLeader(null);

  }
 
  public dss.vector.solutions.irs.TeamMemberQuery.TeamMemberQueryReferenceIF getTeamLeader(String alias)
  {

    return (dss.vector.solutions.irs.TeamMemberQuery.TeamMemberQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.TEAMLEADER, alias, null);

  }
 
  public dss.vector.solutions.irs.TeamMemberQuery.TeamMemberQueryReferenceIF getTeamLeader(String alias, String displayLabel)
  {

    return (dss.vector.solutions.irs.TeamMemberQuery.TeamMemberQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.TEAMLEADER, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeInteger getTeamSprayWeek()
  {
    return getTeamSprayWeek(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getTeamSprayWeek(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.TEAMSPRAYWEEK, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getTeamSprayWeek(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.TEAMSPRAYWEEK, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getUsed()
  {
    return getUsed(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getUsed(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.USED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getUsed(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.USED, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends TeamSprayStatusView> getIterator()
  {
    com.terraframe.mojo.query.ValueIterator valueIterator;
    if (_pageSize != null && _pageNumber != null)
    {
      valueIterator = (com.terraframe.mojo.query.ValueIterator<com.terraframe.mojo.dataaccess.ValueObject>)this.getComponentQuery().getIterator(_pageSize, _pageNumber);
    }
    else
    {
      valueIterator = (com.terraframe.mojo.query.ValueIterator<com.terraframe.mojo.dataaccess.ValueObject>)this.getComponentQuery().getIterator();
    }
    return new com.terraframe.mojo.query.ViewIterator<TeamSprayStatusView>(this.getMdClassIF(), valueIterator);
  }

}
