package dss.vector.solutions.intervention.monitor;

@com.terraframe.mojo.business.ClassSignature(hash = -1500464132)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to HouseholdView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class HouseholdViewQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -1500464132;

  public HouseholdViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public HouseholdViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.intervention.monitor.HouseholdView.CLASS;
  }
  public com.terraframe.mojo.query.AttributeChar getConcreteId()
  {
    return getConcreteId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getConcreteId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.CONCRETEID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getConcreteId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.CONCRETEID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getDisplayNets()
  {
    return getDisplayNets(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getDisplayNets(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.DISPLAYNETS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getDisplayNets(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.DISPLAYNETS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getHasWindows()
  {
    return getHasWindows(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getHasWindows(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.HASWINDOWS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getHasWindows(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.HASWINDOWS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getHouseholdName()
  {
    return getHouseholdName(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getHouseholdName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.HOUSEHOLDNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getHouseholdName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.HOUSEHOLDNAME, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.ID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getLastSprayed()
  {
    return getLastSprayed(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getLastSprayed(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.LASTSPRAYED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getLastSprayed(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.LASTSPRAYED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getNets()
  {
    return getNets(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getNets(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.NETS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getNets(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.NETS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getNetsUsed()
  {
    return getNetsUsed(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getNetsUsed(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.NETSUSED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getNetsUsed(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.NETSUSED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getPeople()
  {
    return getPeople(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getPeople(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.PEOPLE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getPeople(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.PEOPLE, alias, displayLabel);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getRoof()
  {
    return getRoof(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getRoof(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.ROOF, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getRoof(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.ROOF, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getRoofInfo()
  {
    return getRoofInfo(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getRoofInfo(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.ROOFINFO, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getRoofInfo(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.ROOFINFO, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getRooms()
  {
    return getRooms(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getRooms(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.ROOMS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getRooms(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.ROOMS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getSleptUnderNets()
  {
    return getSleptUnderNets(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getSleptUnderNets(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.SLEPTUNDERNETS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getSleptUnderNets(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.SLEPTUNDERNETS, alias, displayLabel);

  }
 
  public dss.vector.solutions.intervention.monitor.SurveyPointQuery.SurveyPointQueryReferenceIF getSurveyPoint()
  {
    return getSurveyPoint(null);

  }
 
  public dss.vector.solutions.intervention.monitor.SurveyPointQuery.SurveyPointQueryReferenceIF getSurveyPoint(String alias)
  {

    return (dss.vector.solutions.intervention.monitor.SurveyPointQuery.SurveyPointQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.SURVEYPOINT, alias, null);

  }
 
  public dss.vector.solutions.intervention.monitor.SurveyPointQuery.SurveyPointQueryReferenceIF getSurveyPoint(String alias, String displayLabel)
  {

    return (dss.vector.solutions.intervention.monitor.SurveyPointQuery.SurveyPointQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.SURVEYPOINT, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeBoolean getUrban()
  {
    return getUrban(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getUrban(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.URBAN, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getUrban(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.URBAN, alias, displayLabel);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getWall()
  {
    return getWall(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getWall(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.WALL, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getWall(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.WALL, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getWallInfo()
  {
    return getWallInfo(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getWallInfo(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.WALLINFO, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getWallInfo(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.WALLINFO, alias, displayLabel);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getWindowType()
  {
    return getWindowType(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getWindowType(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.WINDOWTYPE, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getWindowType(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.HouseholdView.WINDOWTYPE, alias, displayLabel);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends HouseholdView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<HouseholdView>(this.getMdClassIF(), valueIterator);
  }

}
