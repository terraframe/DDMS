package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = -1276675585)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to OperatorSprayView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class OperatorSprayViewQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -1276675585;

  public OperatorSprayViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public OperatorSprayViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.irs.OperatorSprayView.CLASS;
  }
  public com.terraframe.mojo.query.SelectableChar getAssignedOperator()
  {
    return getAssignedOperator(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getAssignedOperator(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.OperatorSprayView.ASSIGNEDOPERATOR, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getAssignedOperator(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.OperatorSprayView.ASSIGNEDOPERATOR, alias, displayLabel);

  }
 
  public dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF getBrand()
  {
    return getBrand(null);

  }
 
  public dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF getBrand(String alias)
  {

    return (dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.OperatorSprayView.BRAND, alias, null);

  }
 
  public dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF getBrand(String alias, String displayLabel)
  {

    return (dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.OperatorSprayView.BRAND, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableChar getConcreteId()
  {
    return getConcreteId(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getConcreteId(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.OperatorSprayView.CONCRETEID, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getConcreteId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.OperatorSprayView.CONCRETEID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableChar getFloatingOperator()
  {
    return getFloatingOperator(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getFloatingOperator(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.OperatorSprayView.FLOATINGOPERATOR, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getFloatingOperator(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.OperatorSprayView.FLOATINGOPERATOR, alias, displayLabel);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity()
  {
    return getGeoEntity(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.OperatorSprayView.GEOENTITY, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias, String displayLabel)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.OperatorSprayView.GEOENTITY, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getId(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.OperatorSprayView.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.OperatorSprayView.ID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getOperatorSprayWeek()
  {
    return getOperatorSprayWeek(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getOperatorSprayWeek(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayView.OPERATORSPRAYWEEK, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getOperatorSprayWeek(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayView.OPERATORSPRAYWEEK, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getReceived()
  {
    return getReceived(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getReceived(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayView.RECEIVED, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getReceived(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayView.RECEIVED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getRefills()
  {
    return getRefills(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getRefills(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayView.REFILLS, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getRefills(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayView.REFILLS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getReturned()
  {
    return getReturned(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getReturned(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayView.RETURNED, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getReturned(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayView.RETURNED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableMoment getSprayDate()
  {
    return getSprayDate(null);

  }
 
  public com.terraframe.mojo.query.SelectableMoment getSprayDate(String alias)
  {
    return (com.terraframe.mojo.query.SelectableMoment)this.getSelectable(dss.vector.solutions.irs.OperatorSprayView.SPRAYDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableMoment getSprayDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableMoment)this.getSelectable(dss.vector.solutions.irs.OperatorSprayView.SPRAYDATE, alias, displayLabel);

  }
 
  public dss.vector.solutions.irs.SprayMethodMasterQuery.SprayMethodQueryIF getSprayMethod()
  {
    return getSprayMethod(null);

  }
 
  public dss.vector.solutions.irs.SprayMethodMasterQuery.SprayMethodQueryIF getSprayMethod(String alias)
  {
    return (dss.vector.solutions.irs.SprayMethodMasterQuery.SprayMethodQueryIF)this.getSelectable(dss.vector.solutions.irs.OperatorSprayView.SPRAYMETHOD, alias, null);

  }
 
  public dss.vector.solutions.irs.SprayMethodMasterQuery.SprayMethodQueryIF getSprayMethod(String alias, String displayLabel)
  {
    return (dss.vector.solutions.irs.SprayMethodMasterQuery.SprayMethodQueryIF)this.getSelectable(dss.vector.solutions.irs.OperatorSprayView.SPRAYMETHOD, alias, displayLabel);

  }
  public dss.vector.solutions.irs.TeamMemberQuery.TeamMemberQueryReferenceIF getSprayOperator()
  {
    return getSprayOperator(null);

  }
 
  public dss.vector.solutions.irs.TeamMemberQuery.TeamMemberQueryReferenceIF getSprayOperator(String alias)
  {

    return (dss.vector.solutions.irs.TeamMemberQuery.TeamMemberQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.OperatorSprayView.SPRAYOPERATOR, alias, null);

  }
 
  public dss.vector.solutions.irs.TeamMemberQuery.TeamMemberQueryReferenceIF getSprayOperator(String alias, String displayLabel)
  {

    return (dss.vector.solutions.irs.TeamMemberQuery.TeamMemberQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.OperatorSprayView.SPRAYOPERATOR, alias, displayLabel);

  }
  public dss.vector.solutions.irs.SprayTeamQuery.SprayTeamQueryReferenceIF getSprayTeam()
  {
    return getSprayTeam(null);

  }
 
  public dss.vector.solutions.irs.SprayTeamQuery.SprayTeamQueryReferenceIF getSprayTeam(String alias)
  {

    return (dss.vector.solutions.irs.SprayTeamQuery.SprayTeamQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.OperatorSprayView.SPRAYTEAM, alias, null);

  }
 
  public dss.vector.solutions.irs.SprayTeamQuery.SprayTeamQueryReferenceIF getSprayTeam(String alias, String displayLabel)
  {

    return (dss.vector.solutions.irs.SprayTeamQuery.SprayTeamQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.OperatorSprayView.SPRAYTEAM, alias, displayLabel);

  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSurfaceType()
  {
    return getSurfaceType(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSurfaceType(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.OperatorSprayView.SURFACETYPE, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSurfaceType(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.OperatorSprayView.SURFACETYPE, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableInteger getTarget()
  {
    return getTarget(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getTarget(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayView.TARGET, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getTarget(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayView.TARGET, alias, displayLabel);

  }
 
  public dss.vector.solutions.irs.TeamMemberQuery.TeamMemberQueryReferenceIF getTeamLeader()
  {
    return getTeamLeader(null);

  }
 
  public dss.vector.solutions.irs.TeamMemberQuery.TeamMemberQueryReferenceIF getTeamLeader(String alias)
  {

    return (dss.vector.solutions.irs.TeamMemberQuery.TeamMemberQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.OperatorSprayView.TEAMLEADER, alias, null);

  }
 
  public dss.vector.solutions.irs.TeamMemberQuery.TeamMemberQueryReferenceIF getTeamLeader(String alias, String displayLabel)
  {

    return (dss.vector.solutions.irs.TeamMemberQuery.TeamMemberQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.OperatorSprayView.TEAMLEADER, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableChar getTeamOperator()
  {
    return getTeamOperator(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getTeamOperator(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.OperatorSprayView.TEAMOPERATOR, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getTeamOperator(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.OperatorSprayView.TEAMOPERATOR, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getTeamSprayWeek()
  {
    return getTeamSprayWeek(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getTeamSprayWeek(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayView.TEAMSPRAYWEEK, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getTeamSprayWeek(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayView.TEAMSPRAYWEEK, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getUsed()
  {
    return getUsed(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getUsed(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayView.USED, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getUsed(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.irs.OperatorSprayView.USED, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends OperatorSprayView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<OperatorSprayView>(this.getMdClassIF(), valueIterator);
  }

}
