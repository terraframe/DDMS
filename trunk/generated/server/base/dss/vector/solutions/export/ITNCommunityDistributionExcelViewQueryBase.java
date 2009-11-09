package dss.vector.solutions.export;

@com.terraframe.mojo.business.ClassSignature(hash = 1940961535)
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
private static final long serialVersionUID = 1940961535;

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
  public com.terraframe.mojo.query.AttributeChar getAgentFirstName()
  {
    return getAgentFirstName(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getAgentFirstName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.AGENTFIRSTNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getAgentFirstName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.AGENTFIRSTNAME, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getAgentSurname()
  {
    return getAgentSurname(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getAgentSurname(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.AGENTSURNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getAgentSurname(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.AGENTSURNAME, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getBatchNumber()
  {
    return getBatchNumber(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getBatchNumber(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.BATCHNUMBER, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getBatchNumber(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.BATCHNUMBER, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeDecimal getCurrencyReceived()
  {
    return getCurrencyReceived(null);

  }
 
  public com.terraframe.mojo.query.AttributeDecimal getCurrencyReceived(String alias)
  {
    return (com.terraframe.mojo.query.AttributeDecimal)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.CURRENCYRECEIVED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeDecimal getCurrencyReceived(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeDecimal)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.CURRENCYRECEIVED, alias, displayLabel);

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
  public com.terraframe.mojo.query.AttributeMoment getEndDate()
  {
    return getEndDate(null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getEndDate(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.ENDDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getEndDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.ENDDATE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getEntryType()
  {
    return getEntryType(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getEntryType(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.ENTRYTYPE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getEntryType(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.ENTRYTYPE, alias, displayLabel);

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
  public com.terraframe.mojo.query.AttributeChar getHouseholdName()
  {
    return getHouseholdName(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getHouseholdName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.HOUSEHOLDNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getHouseholdName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.HOUSEHOLDNAME, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getHouseholdSurname()
  {
    return getHouseholdSurname(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getHouseholdSurname(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.HOUSEHOLDSURNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getHouseholdSurname(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.HOUSEHOLDSURNAME, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.ID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getNumberRetrieved()
  {
    return getNumberRetrieved(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getNumberRetrieved(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.NUMBERRETRIEVED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getNumberRetrieved(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.NUMBERRETRIEVED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getPretreated()
  {
    return getPretreated(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getPretreated(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.PRETREATED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getPretreated(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.PRETREATED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getResidents()
  {
    return getResidents(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getResidents(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.RESIDENTS, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getResidents(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.RESIDENTS, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getRetrieved()
  {
    return getRetrieved(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getRetrieved(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.RETRIEVED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getRetrieved(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.RETRIEVED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getSold()
  {
    return getSold(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getSold(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.SOLD, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getSold(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.SOLD, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getStartDate()
  {
    return getStartDate(null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getStartDate(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.STARTDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getStartDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.export.ITNCommunityDistributionExcelView.STARTDATE, alias, displayLabel);

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
