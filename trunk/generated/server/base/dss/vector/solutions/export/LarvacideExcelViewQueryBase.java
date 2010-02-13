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
  public com.terraframe.mojo.query.SelectableSingleMoment getCompletionDate()
  {
    return getCompletionDate(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getCompletionDate(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleMoment)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.COMPLETIONDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getCompletionDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleMoment)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.COMPLETIONDATE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getControlMethod()
  {
    return getControlMethod(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getControlMethod(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.CONTROLMETHOD, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getControlMethod(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.CONTROLMETHOD, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getGeoDescription()
  {
    return getGeoDescription(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getGeoDescription(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.GEODESCRIPTION, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getGeoDescription(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.GEODESCRIPTION, alias, displayLabel);

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
  public com.terraframe.mojo.query.SelectableSingleChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getId(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.ID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getNatureOfControl()
  {
    return getNatureOfControl(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getNatureOfControl(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleBoolean)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.NATUREOFCONTROL, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getNatureOfControl(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleBoolean)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.NATUREOFCONTROL, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getPersonCount()
  {
    return getPersonCount(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getPersonCount(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.PERSONCOUNT, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getPersonCount(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.PERSONCOUNT, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getStartDate()
  {
    return getStartDate(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getStartDate(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleMoment)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.STARTDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getStartDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleMoment)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.STARTDATE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getSubstance()
  {
    return getSubstance(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getSubstance(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.SUBSTANCE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getSubstance(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.SUBSTANCE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getTarget()
  {
    return getTarget(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getTarget(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.TARGET, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getTarget(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.TARGET, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getTeamLeaderId()
  {
    return getTeamLeaderId(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getTeamLeaderId(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.TEAMLEADERID, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getTeamLeaderId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.TEAMLEADERID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getTreated()
  {
    return getTreated(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getTreated(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.TREATED, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getTreated(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.TREATED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getUnit()
  {
    return getUnit(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getUnit(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.UNIT, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getUnit(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.UNIT, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getUnitsUsed()
  {
    return getUnitsUsed(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getUnitsUsed(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.UNITSUSED, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getUnitsUsed(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.export.LarvacideExcelView.UNITSUSED, alias, displayLabel);

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
