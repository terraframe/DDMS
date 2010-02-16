package dss.vector.solutions.export;

@com.terraframe.mojo.business.ClassSignature(hash = -989970651)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to LarvacideExcelView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class LarvacideExcelViewQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -989970651;

  public LarvacideExcelViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public LarvacideExcelViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.export.LarvacideExcelView.CLASS;
  }
  public com.terraframe.mojo.query.SelectableMoment getCompletionDate()
  {
    return getCompletionDate(null);

  }
 
  public com.terraframe.mojo.query.SelectableMoment getCompletionDate(String alias)
  {
    return (com.terraframe.mojo.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.COMPLETIONDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableMoment getCompletionDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.COMPLETIONDATE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableChar getControlMethod()
  {
    return getControlMethod(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getControlMethod(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.CONTROLMETHOD, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getControlMethod(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.CONTROLMETHOD, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableChar getGeoDescription()
  {
    return getGeoDescription(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getGeoDescription(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.GEODESCRIPTION, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getGeoDescription(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.GEODESCRIPTION, alias, displayLabel);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity()
  {
    return getGeoEntity(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.GEOENTITY, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias, String displayLabel)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.GEOENTITY, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getId(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.ID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableBoolean getNatureOfControl()
  {
    return getNatureOfControl(null);

  }
 
  public com.terraframe.mojo.query.SelectableBoolean getNatureOfControl(String alias)
  {
    return (com.terraframe.mojo.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.NATUREOFCONTROL, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableBoolean getNatureOfControl(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.NATUREOFCONTROL, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getPersonCount()
  {
    return getPersonCount(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getPersonCount(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.PERSONCOUNT, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getPersonCount(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.PERSONCOUNT, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableMoment getStartDate()
  {
    return getStartDate(null);

  }
 
  public com.terraframe.mojo.query.SelectableMoment getStartDate(String alias)
  {
    return (com.terraframe.mojo.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.STARTDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableMoment getStartDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.STARTDATE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableChar getSubstance()
  {
    return getSubstance(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getSubstance(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.SUBSTANCE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getSubstance(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.SUBSTANCE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableChar getTarget()
  {
    return getTarget(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getTarget(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.TARGET, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getTarget(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.TARGET, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableChar getTeamLeaderId()
  {
    return getTeamLeaderId(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getTeamLeaderId(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.TEAMLEADERID, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getTeamLeaderId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.TEAMLEADERID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getTreated()
  {
    return getTreated(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getTreated(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.TREATED, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getTreated(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.TREATED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableChar getUnit()
  {
    return getUnit(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getUnit(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.UNIT, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getUnit(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.UNIT, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getUnitsUsed()
  {
    return getUnitsUsed(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getUnitsUsed(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.UNITSUSED, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getUnitsUsed(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.UNITSUSED, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends LarvacideExcelView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<LarvacideExcelView>(this.getMdClassIF(), valueIterator);
  }

}
