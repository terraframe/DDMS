package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = -1165154921)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AggregatedCaseExcelView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class AggregatedCaseExcelViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = -1165154921;

  public AggregatedCaseExcelViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public AggregatedCaseExcelViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.export.AggregatedCaseExcelView.CLASS;
  }
  public com.runwaysdk.query.SelectableChar getDisplayLabel()
  {
    return getDisplayLabel(null);

  }
 
  public com.runwaysdk.query.SelectableChar getDisplayLabel(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.AggregatedCaseExcelView.DISPLAYLABEL, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getDisplayLabel(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.AggregatedCaseExcelView.DISPLAYLABEL, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableMoment getEndDate()
  {
    return getEndDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getEndDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.AggregatedCaseExcelView.ENDDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getEndDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.AggregatedCaseExcelView.ENDDATE, alias, displayLabel);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity()
  {
    return getGeoEntity(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.AggregatedCaseExcelView.GEOENTITY, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias, String displayLabel)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.AggregatedCaseExcelView.GEOENTITY, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.AggregatedCaseExcelView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.AggregatedCaseExcelView.ID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableMoment getStartDate()
  {
    return getStartDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getStartDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.AggregatedCaseExcelView.STARTDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getStartDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.AggregatedCaseExcelView.STARTDATE, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends AggregatedCaseExcelView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<AggregatedCaseExcelView>(this.getMdClassIF(), valueIterator);
  }

}
