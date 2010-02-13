package dss.vector.solutions.export;

@com.terraframe.mojo.business.ClassSignature(hash = 2146807465)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ITNCommunityDistributionExcelView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class ITNCommunityDistributionExcelViewQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 2146807465;

  public ITNCommunityDistributionExcelViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public ITNCommunityDistributionExcelViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.export.ITNCommunityDistributionExcelView.CLASS;
  }
  public com.terraframe.mojo.query.SelectableSingleChar getAgentFirstName()
  {
    return getAgentFirstName(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getAgentFirstName(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.AGENTFIRSTNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getAgentFirstName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.AGENTFIRSTNAME, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getAgentSurname()
  {
    return getAgentSurname(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getAgentSurname(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.AGENTSURNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getAgentSurname(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.AGENTSURNAME, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getBatchNumber()
  {
    return getBatchNumber(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getBatchNumber(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.BATCHNUMBER, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getBatchNumber(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.BATCHNUMBER, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleDecimal getCurrencyReceived()
  {
    return getCurrencyReceived(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleDecimal getCurrencyReceived(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleDecimal)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.CURRENCYRECEIVED, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleDecimal getCurrencyReceived(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleDecimal)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.CURRENCYRECEIVED, alias, displayLabel);

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
  public com.terraframe.mojo.query.SelectableSingleMoment getEndDate()
  {
    return getEndDate(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getEndDate(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleMoment)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.ENDDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getEndDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleMoment)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.ENDDATE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getEntryType()
  {
    return getEntryType(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getEntryType(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleBoolean)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.ENTRYTYPE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getEntryType(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleBoolean)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.ENTRYTYPE, alias, displayLabel);

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
  public com.terraframe.mojo.query.SelectableSingleChar getHouseholdName()
  {
    return getHouseholdName(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getHouseholdName(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.HOUSEHOLDNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getHouseholdName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.HOUSEHOLDNAME, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getHouseholdSurname()
  {
    return getHouseholdSurname(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getHouseholdSurname(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.HOUSEHOLDSURNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getHouseholdSurname(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.HOUSEHOLDSURNAME, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getId(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleChar)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.ID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getNumberRetrieved()
  {
    return getNumberRetrieved(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getNumberRetrieved(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.NUMBERRETRIEVED, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getNumberRetrieved(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.NUMBERRETRIEVED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getPretreated()
  {
    return getPretreated(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getPretreated(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleBoolean)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.PRETREATED, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getPretreated(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleBoolean)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.PRETREATED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getResidents()
  {
    return getResidents(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getResidents(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.RESIDENTS, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleInteger getResidents(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleInteger)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.RESIDENTS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getRetrieved()
  {
    return getRetrieved(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getRetrieved(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleBoolean)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.RETRIEVED, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getRetrieved(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleBoolean)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.RETRIEVED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getSold()
  {
    return getSold(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getSold(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleBoolean)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.SOLD, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleBoolean getSold(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleBoolean)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.SOLD, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getStartDate()
  {
    return getStartDate(null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getStartDate(String alias)
  {
    return (com.terraframe.mojo.query.SelectableSingleMoment)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.STARTDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableSingleMoment getStartDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableSingleMoment)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.STARTDATE, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends ITNCommunityDistributionExcelView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<ITNCommunityDistributionExcelView>(this.getMdClassIF(), valueIterator);
  }

}
