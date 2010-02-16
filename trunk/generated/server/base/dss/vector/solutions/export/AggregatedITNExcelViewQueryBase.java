package dss.vector.solutions.export;

@com.terraframe.mojo.business.ClassSignature(hash = -1791455693)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AggregatedITNExcelView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class AggregatedITNExcelViewQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -1791455693;

  public AggregatedITNExcelViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public AggregatedITNExcelViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.export.AggregatedITNExcelView.CLASS;
  }
  public com.terraframe.mojo.query.SelectableChar getBatchNumber()
  {
    return getBatchNumber(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getBatchNumber(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.AggregatedITNExcelView.BATCHNUMBER, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getBatchNumber(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.AggregatedITNExcelView.BATCHNUMBER, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableDecimal getCurrencyReceived()
  {
    return getCurrencyReceived(null);

  }
 
  public com.terraframe.mojo.query.SelectableDecimal getCurrencyReceived(String alias)
  {
    return (com.terraframe.mojo.query.SelectableDecimal)this.getSelectable(dss.vector.solutions.export.AggregatedITNExcelView.CURRENCYRECEIVED, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableDecimal getCurrencyReceived(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableDecimal)this.getSelectable(dss.vector.solutions.export.AggregatedITNExcelView.CURRENCYRECEIVED, alias, displayLabel);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity()
  {
    return getGeoEntity(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.AggregatedITNExcelView.GEOENTITY, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias, String displayLabel)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.AggregatedITNExcelView.GEOENTITY, alias, displayLabel);

  }
  public com.terraframe.mojo.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getId(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.AggregatedITNExcelView.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.AggregatedITNExcelView.ID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getNumberDistributed()
  {
    return getNumberDistributed(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getNumberDistributed(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.AggregatedITNExcelView.NUMBERDISTRIBUTED, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getNumberDistributed(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.AggregatedITNExcelView.NUMBERDISTRIBUTED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getNumberSold()
  {
    return getNumberSold(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getNumberSold(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.AggregatedITNExcelView.NUMBERSOLD, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getNumberSold(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.AggregatedITNExcelView.NUMBERSOLD, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getPeriod()
  {
    return getPeriod(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getPeriod(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.AggregatedITNExcelView.PERIOD, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getPeriod(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.AggregatedITNExcelView.PERIOD, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableChar getPeriodType()
  {
    return getPeriodType(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getPeriodType(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.AggregatedITNExcelView.PERIODTYPE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getPeriodType(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.AggregatedITNExcelView.PERIODTYPE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getPeriodYear()
  {
    return getPeriodYear(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getPeriodYear(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.AggregatedITNExcelView.PERIODYEAR, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getPeriodYear(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.AggregatedITNExcelView.PERIODYEAR, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getReceivedForCommunityResponse()
  {
    return getReceivedForCommunityResponse(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getReceivedForCommunityResponse(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.AggregatedITNExcelView.RECEIVEDFORCOMMUNITYRESPONSE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getReceivedForCommunityResponse(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.AggregatedITNExcelView.RECEIVEDFORCOMMUNITYRESPONSE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getReceivedForTargetGroups()
  {
    return getReceivedForTargetGroups(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getReceivedForTargetGroups(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.AggregatedITNExcelView.RECEIVEDFORTARGETGROUPS, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getReceivedForTargetGroups(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.AggregatedITNExcelView.RECEIVEDFORTARGETGROUPS, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends AggregatedITNExcelView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<AggregatedITNExcelView>(this.getMdClassIF(), valueIterator);
  }

}
