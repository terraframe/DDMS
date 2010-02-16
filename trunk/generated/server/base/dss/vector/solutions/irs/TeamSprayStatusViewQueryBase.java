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
  public com.terraframe.mojo.query.SelectableInteger getBedNets()
  {
    return getBedNets(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getBedNets(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.BEDNETS, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getBedNets(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.BEDNETS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableChar getConcreteId()
  {
    return getConcreteId(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getConcreteId(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.CONCRETEID, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getConcreteId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.CONCRETEID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getHouseholds()
  {
    return getHouseholds(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getHouseholds(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.HOUSEHOLDS, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getHouseholds(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.HOUSEHOLDS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getId(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.ID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getLocked()
  {
    return getLocked(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getLocked(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.LOCKED, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getLocked(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.LOCKED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getOther()
  {
    return getOther(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getOther(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.OTHER, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getOther(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.OTHER, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getPeople()
  {
    return getPeople(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getPeople(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.PEOPLE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getPeople(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.PEOPLE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getPrevSprayedHouseholds()
  {
    return getPrevSprayedHouseholds(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getPrevSprayedHouseholds(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.PREVSPRAYEDHOUSEHOLDS, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getPrevSprayedHouseholds(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.PREVSPRAYEDHOUSEHOLDS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getPrevSprayedStructures()
  {
    return getPrevSprayedStructures(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getPrevSprayedStructures(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.PREVSPRAYEDSTRUCTURES, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getPrevSprayedStructures(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.PREVSPRAYEDSTRUCTURES, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getReceived()
  {
    return getReceived(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getReceived(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.RECEIVED, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getReceived(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.RECEIVED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getRefills()
  {
    return getRefills(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getRefills(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.REFILLS, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getRefills(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.REFILLS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getRefused()
  {
    return getRefused(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getRefused(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.REFUSED, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getRefused(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.REFUSED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getReturned()
  {
    return getReturned(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getReturned(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.RETURNED, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getReturned(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.RETURNED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getRooms()
  {
    return getRooms(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getRooms(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.ROOMS, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getRooms(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.ROOMS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getRoomsWithBedNets()
  {
    return getRoomsWithBedNets(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getRoomsWithBedNets(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.ROOMSWITHBEDNETS, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getRoomsWithBedNets(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.ROOMSWITHBEDNETS, alias, displayLabel);

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
  public com.terraframe.mojo.query.SelectableInteger getSprayedHouseholds()
  {
    return getSprayedHouseholds(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getSprayedHouseholds(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.SPRAYEDHOUSEHOLDS, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getSprayedHouseholds(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.SPRAYEDHOUSEHOLDS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getSprayedRooms()
  {
    return getSprayedRooms(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getSprayedRooms(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.SPRAYEDROOMS, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getSprayedRooms(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.SPRAYEDROOMS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getSprayedStructures()
  {
    return getSprayedStructures(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getSprayedStructures(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.SPRAYEDSTRUCTURES, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getSprayedStructures(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.SPRAYEDSTRUCTURES, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getStructures()
  {
    return getStructures(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getStructures(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.STRUCTURES, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getStructures(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.STRUCTURES, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getTarget()
  {
    return getTarget(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getTarget(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.TARGET, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getTarget(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.TARGET, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableChar getTeamLabel()
  {
    return getTeamLabel(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getTeamLabel(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.TEAMLABEL, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getTeamLabel(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.TEAMLABEL, alias, displayLabel);

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
  public com.terraframe.mojo.query.SelectableInteger getTeamSprayWeek()
  {
    return getTeamSprayWeek(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getTeamSprayWeek(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.TEAMSPRAYWEEK, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getTeamSprayWeek(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.TEAMSPRAYWEEK, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getUsed()
  {
    return getUsed(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getUsed(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.USED, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getUsed(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.TeamSprayStatusView.USED, alias, displayLabel);

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
