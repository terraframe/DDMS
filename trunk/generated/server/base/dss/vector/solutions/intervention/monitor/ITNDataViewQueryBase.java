package dss.vector.solutions.intervention.monitor;

@com.runwaysdk.business.ClassSignature(hash = 531939813)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ITNDataView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class ITNDataViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = 531939813;

  public ITNDataViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public ITNDataViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.intervention.monitor.ITNDataView.CLASS;
  }
  public com.runwaysdk.query.SelectableChar getBatchNumber()
  {
    return getBatchNumber(null);

  }
 
  public com.runwaysdk.query.SelectableChar getBatchNumber(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDataView.BATCHNUMBER, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getBatchNumber(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDataView.BATCHNUMBER, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId()
  {
    return getConcreteId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDataView.CONCRETEID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDataView.CONCRETEID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableDecimal getCurrencyReceived()
  {
    return getCurrencyReceived(null);

  }
 
  public com.runwaysdk.query.SelectableDecimal getCurrencyReceived(String alias)
  {
    return (com.runwaysdk.query.SelectableDecimal)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDataView.CURRENCYRECEIVED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDecimal getCurrencyReceived(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDecimal)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDataView.CURRENCYRECEIVED, alias, displayLabel);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getDisplayNets()
  {
    return getDisplayNets(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getDisplayNets(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDataView.DISPLAYNETS, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getDisplayNets(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDataView.DISPLAYNETS, alias, displayLabel);

  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getDisplayServices()
  {
    return getDisplayServices(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getDisplayServices(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDataView.DISPLAYSERVICES, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getDisplayServices(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDataView.DISPLAYSERVICES, alias, displayLabel);

  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getDisplayTargetGroups()
  {
    return getDisplayTargetGroups(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getDisplayTargetGroups(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDataView.DISPLAYTARGETGROUPS, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getDisplayTargetGroups(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDataView.DISPLAYTARGETGROUPS, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableMoment getEndDate()
  {
    return getEndDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getEndDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDataView.ENDDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getEndDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDataView.ENDDATE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getGeoId()
  {
    return getGeoId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getGeoId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDataView.GEOID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getGeoId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDataView.GEOID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDataView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDataView.ID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberDistributed()
  {
    return getNumberDistributed(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberDistributed(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDataView.NUMBERDISTRIBUTED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberDistributed(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDataView.NUMBERDISTRIBUTED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberSold()
  {
    return getNumberSold(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberSold(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDataView.NUMBERSOLD, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberSold(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDataView.NUMBERSOLD, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getPeriod()
  {
    return getPeriod(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPeriod(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDataView.PERIOD, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPeriod(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDataView.PERIOD, alias, displayLabel);

  }
 
  public dss.vector.solutions.surveillance.PeriodTypeMasterQuery.PeriodTypeQueryIF getPeriodType()
  {
    return getPeriodType(null);

  }
 
  public dss.vector.solutions.surveillance.PeriodTypeMasterQuery.PeriodTypeQueryIF getPeriodType(String alias)
  {
    return (dss.vector.solutions.surveillance.PeriodTypeMasterQuery.PeriodTypeQueryIF)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDataView.PERIODTYPE, alias, null);

  }
 
  public dss.vector.solutions.surveillance.PeriodTypeMasterQuery.PeriodTypeQueryIF getPeriodType(String alias, String displayLabel)
  {
    return (dss.vector.solutions.surveillance.PeriodTypeMasterQuery.PeriodTypeQueryIF)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDataView.PERIODTYPE, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getPeriodYear()
  {
    return getPeriodYear(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPeriodYear(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDataView.PERIODYEAR, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPeriodYear(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDataView.PERIODYEAR, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getReceivedForCommunityResponse()
  {
    return getReceivedForCommunityResponse(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getReceivedForCommunityResponse(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDataView.RECEIVEDFORCOMMUNITYRESPONSE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getReceivedForCommunityResponse(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDataView.RECEIVEDFORCOMMUNITYRESPONSE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getReceivedForTargetGroups()
  {
    return getReceivedForTargetGroups(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getReceivedForTargetGroups(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDataView.RECEIVEDFORTARGETGROUPS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getReceivedForTargetGroups(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDataView.RECEIVEDFORTARGETGROUPS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableBoolean getSearchType()
  {
    return getSearchType(null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getSearchType(String alias)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDataView.SEARCHTYPE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getSearchType(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDataView.SEARCHTYPE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableMoment getStartDate()
  {
    return getStartDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getStartDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDataView.STARTDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getStartDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.intervention.monitor.ITNDataView.STARTDATE, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends ITNDataView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<ITNDataView>(this.getMdClassIF(), valueIterator);
  }

}
