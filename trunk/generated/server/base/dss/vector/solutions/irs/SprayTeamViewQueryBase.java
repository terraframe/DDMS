package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = -337498468)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to SprayTeamView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class SprayTeamViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = -337498468;

  public SprayTeamViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public SprayTeamViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.irs.SprayTeamView.CLASS;
  }
  public com.runwaysdk.query.SelectableChar getAssignedOperators()
  {
    return getAssignedOperators(null);

  }
 
  public com.runwaysdk.query.SelectableChar getAssignedOperators(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.SprayTeamView.ASSIGNEDOPERATORS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getAssignedOperators(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.SprayTeamView.ASSIGNEDOPERATORS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getAvailableOperators()
  {
    return getAvailableOperators(null);

  }
 
  public com.runwaysdk.query.SelectableChar getAvailableOperators(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.SprayTeamView.AVAILABLEOPERATORS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getAvailableOperators(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.SprayTeamView.AVAILABLEOPERATORS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId()
  {
    return getConcreteId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.SprayTeamView.CONCRETEID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.SprayTeamView.CONCRETEID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getCurrentOperators()
  {
    return getCurrentOperators(null);

  }
 
  public com.runwaysdk.query.SelectableChar getCurrentOperators(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.SprayTeamView.CURRENTOPERATORS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getCurrentOperators(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.SprayTeamView.CURRENTOPERATORS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.SprayTeamView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.SprayTeamView.ID, alias, displayLabel);

  }
 
  public dss.vector.solutions.geo.generated.SprayZoneQuery.SprayZoneQueryReferenceIF getSprayZone()
  {
    return getSprayZone(null);

  }
 
  public dss.vector.solutions.geo.generated.SprayZoneQuery.SprayZoneQueryReferenceIF getSprayZone(String alias)
  {

    return (dss.vector.solutions.geo.generated.SprayZoneQuery.SprayZoneQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.SprayTeamView.SPRAYZONE, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.SprayZoneQuery.SprayZoneQueryReferenceIF getSprayZone(String alias, String displayLabel)
  {

    return (dss.vector.solutions.geo.generated.SprayZoneQuery.SprayZoneQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.SprayTeamView.SPRAYZONE, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getTeamId()
  {
    return getTeamId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getTeamId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.SprayTeamView.TEAMID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getTeamId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.SprayTeamView.TEAMID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getTeamLeader()
  {
    return getTeamLeader(null);

  }
 
  public com.runwaysdk.query.SelectableChar getTeamLeader(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.SprayTeamView.TEAMLEADER, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getTeamLeader(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.SprayTeamView.TEAMLEADER, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends SprayTeamView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<SprayTeamView>(this.getMdClassIF(), valueIterator);
  }

}
