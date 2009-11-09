package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = 975570023)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to TeamSprayView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class TeamSprayViewQueryBase extends dss.vector.solutions.irs.ActorSprayViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 975570023;

  public TeamSprayViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public TeamSprayViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.irs.TeamSprayView.CLASS;
  }
  public dss.vector.solutions.irs.SprayTeamQuery.SprayTeamQueryReferenceIF getSprayTeam()
  {
    return getSprayTeam(null);

  }
 
  public dss.vector.solutions.irs.SprayTeamQuery.SprayTeamQueryReferenceIF getSprayTeam(String alias)
  {

    return (dss.vector.solutions.irs.SprayTeamQuery.SprayTeamQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.TeamSprayView.SPRAYTEAM, alias, null);

  }
 
  public dss.vector.solutions.irs.SprayTeamQuery.SprayTeamQueryReferenceIF getSprayTeam(String alias, String displayLabel)
  {

    return (dss.vector.solutions.irs.SprayTeamQuery.SprayTeamQueryReferenceIF)this.getSelectable(dss.vector.solutions.irs.TeamSprayView.SPRAYTEAM, alias, displayLabel);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends TeamSprayView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<TeamSprayView>(this.getMdClassIF(), valueIterator);
  }

}
