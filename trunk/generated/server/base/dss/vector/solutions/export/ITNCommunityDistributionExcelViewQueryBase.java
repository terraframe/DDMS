package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = 894362975)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ITNCommunityDistributionExcelView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class ITNCommunityDistributionExcelViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = 894362975;

  public ITNCommunityDistributionExcelViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public ITNCommunityDistributionExcelViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.export.ITNCommunityDistributionExcelView.CLASS;
  }
  public com.runwaysdk.query.SelectableChar getAgentFirstName()
  {
    return getAgentFirstName(null);

  }
 
  public com.runwaysdk.query.SelectableChar getAgentFirstName(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.AGENTFIRSTNAME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getAgentFirstName(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.AGENTFIRSTNAME, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getAgentSurname()
  {
    return getAgentSurname(null);

  }
 
  public com.runwaysdk.query.SelectableChar getAgentSurname(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.AGENTSURNAME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getAgentSurname(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.AGENTSURNAME, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getBatchNumber()
  {
    return getBatchNumber(null);

  }
 
  public com.runwaysdk.query.SelectableChar getBatchNumber(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.BATCHNUMBER, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getBatchNumber(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.BATCHNUMBER, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableDecimal getCurrencyReceived()
  {
    return getCurrencyReceived(null);

  }
 
  public com.runwaysdk.query.SelectableDecimal getCurrencyReceived(String alias)
  {
    return (com.runwaysdk.query.SelectableDecimal)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.CURRENCYRECEIVED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDecimal getCurrencyReceived(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDecimal)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.CURRENCYRECEIVED, alias, displayLabel);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getDistributionLocation()
  {
    return getDistributionLocation(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getDistributionLocation(String alias)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.DISTRIBUTIONLOCATION, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getDistributionLocation(String alias, String displayLabel)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.DISTRIBUTIONLOCATION, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableMoment getEndDate()
  {
    return getEndDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getEndDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.ENDDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getEndDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.ENDDATE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableBoolean getEntryType()
  {
    return getEntryType(null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getEntryType(String alias)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.ENTRYTYPE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getEntryType(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.ENTRYTYPE, alias, displayLabel);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getHouseholdAddress()
  {
    return getHouseholdAddress(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getHouseholdAddress(String alias)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.HOUSEHOLDADDRESS, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getHouseholdAddress(String alias, String displayLabel)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.HOUSEHOLDADDRESS, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getHouseholdName()
  {
    return getHouseholdName(null);

  }
 
  public com.runwaysdk.query.SelectableChar getHouseholdName(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.HOUSEHOLDNAME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getHouseholdName(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.HOUSEHOLDNAME, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getHouseholdSurname()
  {
    return getHouseholdSurname(null);

  }
 
  public com.runwaysdk.query.SelectableChar getHouseholdSurname(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.HOUSEHOLDSURNAME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getHouseholdSurname(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.HOUSEHOLDSURNAME, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.ID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberRetrieved()
  {
    return getNumberRetrieved(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberRetrieved(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.NUMBERRETRIEVED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberRetrieved(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.NUMBERRETRIEVED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableBoolean getPretreated()
  {
    return getPretreated(null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getPretreated(String alias)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.PRETREATED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getPretreated(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.PRETREATED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getResidents()
  {
    return getResidents(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getResidents(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.RESIDENTS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getResidents(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.RESIDENTS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableBoolean getRetrieved()
  {
    return getRetrieved(null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getRetrieved(String alias)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.RETRIEVED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getRetrieved(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.RETRIEVED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableBoolean getSold()
  {
    return getSold(null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getSold(String alias)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.SOLD, alias, null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getSold(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.SOLD, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableMoment getStartDate()
  {
    return getStartDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getStartDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.STARTDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getStartDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.STARTDATE, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends ITNCommunityDistributionExcelView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<ITNCommunityDistributionExcelView>(this.getMdClassIF(), valueIterator);
  }

}
