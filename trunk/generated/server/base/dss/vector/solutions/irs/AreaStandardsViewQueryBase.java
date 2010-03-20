package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = -1444392545)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AreaStandardsView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class AreaStandardsViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = -1444392545;

  public AreaStandardsViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public AreaStandardsViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.irs.AreaStandardsView.CLASS;
  }
  public com.runwaysdk.query.SelectableChar getAreaStandardsId()
  {
    return getAreaStandardsId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getAreaStandardsId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.AreaStandardsView.AREASTANDARDSID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getAreaStandardsId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.AreaStandardsView.AREASTANDARDSID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableMoment getEndDate()
  {
    return getEndDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getEndDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.irs.AreaStandardsView.ENDDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getEndDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.irs.AreaStandardsView.ENDDATE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableFloat getHousehold()
  {
    return getHousehold(null);

  }
 
  public com.runwaysdk.query.SelectableFloat getHousehold(String alias)
  {
    return (com.runwaysdk.query.SelectableFloat)this.getSelectable(dss.vector.solutions.irs.AreaStandardsView.HOUSEHOLD, alias, null);

  }
 
  public com.runwaysdk.query.SelectableFloat getHousehold(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableFloat)this.getSelectable(dss.vector.solutions.irs.AreaStandardsView.HOUSEHOLD, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.AreaStandardsView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.irs.AreaStandardsView.ID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableFloat getRoom()
  {
    return getRoom(null);

  }
 
  public com.runwaysdk.query.SelectableFloat getRoom(String alias)
  {
    return (com.runwaysdk.query.SelectableFloat)this.getSelectable(dss.vector.solutions.irs.AreaStandardsView.ROOM, alias, null);

  }
 
  public com.runwaysdk.query.SelectableFloat getRoom(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableFloat)this.getSelectable(dss.vector.solutions.irs.AreaStandardsView.ROOM, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableMoment getStartDate()
  {
    return getStartDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getStartDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.irs.AreaStandardsView.STARTDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getStartDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.irs.AreaStandardsView.STARTDATE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableFloat getStructureArea()
  {
    return getStructureArea(null);

  }
 
  public com.runwaysdk.query.SelectableFloat getStructureArea(String alias)
  {
    return (com.runwaysdk.query.SelectableFloat)this.getSelectable(dss.vector.solutions.irs.AreaStandardsView.STRUCTUREAREA, alias, null);

  }
 
  public com.runwaysdk.query.SelectableFloat getStructureArea(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableFloat)this.getSelectable(dss.vector.solutions.irs.AreaStandardsView.STRUCTUREAREA, alias, displayLabel);

  }
 
  public dss.vector.solutions.irs.TargetUnitMasterQuery.TargetUnitQueryIF getTargetUnit()
  {
    return getTargetUnit(null);

  }
 
  public dss.vector.solutions.irs.TargetUnitMasterQuery.TargetUnitQueryIF getTargetUnit(String alias)
  {
    return (dss.vector.solutions.irs.TargetUnitMasterQuery.TargetUnitQueryIF)this.getSelectable(dss.vector.solutions.irs.AreaStandardsView.TARGETUNIT, alias, null);

  }
 
  public dss.vector.solutions.irs.TargetUnitMasterQuery.TargetUnitQueryIF getTargetUnit(String alias, String displayLabel)
  {
    return (dss.vector.solutions.irs.TargetUnitMasterQuery.TargetUnitQueryIF)this.getSelectable(dss.vector.solutions.irs.AreaStandardsView.TARGETUNIT, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableFloat getUnitNozzleAreaCoverage()
  {
    return getUnitNozzleAreaCoverage(null);

  }
 
  public com.runwaysdk.query.SelectableFloat getUnitNozzleAreaCoverage(String alias)
  {
    return (com.runwaysdk.query.SelectableFloat)this.getSelectable(dss.vector.solutions.irs.AreaStandardsView.UNITNOZZLEAREACOVERAGE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableFloat getUnitNozzleAreaCoverage(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableFloat)this.getSelectable(dss.vector.solutions.irs.AreaStandardsView.UNITNOZZLEAREACOVERAGE, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends AreaStandardsView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<AreaStandardsView>(this.getMdClassIF(), valueIterator);
  }

}
