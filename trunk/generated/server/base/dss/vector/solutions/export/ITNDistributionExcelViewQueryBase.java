package dss.vector.solutions.export;

@com.terraframe.mojo.business.ClassSignature(hash = 1832425658)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ITNDistributionExcelView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class ITNDistributionExcelViewQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1832425658;

  public ITNDistributionExcelViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public ITNDistributionExcelViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.export.ITNDistributionExcelView.CLASS;
  }
  public com.terraframe.mojo.query.AttributeChar getBatchNumber()
  {
    return getBatchNumber(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getBatchNumber(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.BATCHNUMBER, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getBatchNumber(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.BATCHNUMBER, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getCurrencyReceived()
  {
    return getCurrencyReceived(null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getCurrencyReceived(String alias)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.CURRENCYRECEIVED, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeDouble getCurrencyReceived(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeDouble)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.CURRENCYRECEIVED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getDistributionDate()
  {
    return getDistributionDate(null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getDistributionDate(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.DISTRIBUTIONDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getDistributionDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.DISTRIBUTIONDATE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getDistributorName()
  {
    return getDistributorName(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getDistributorName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.DISTRIBUTORNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getDistributorName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.DISTRIBUTORNAME, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getDistributorSurname()
  {
    return getDistributorSurname(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getDistributorSurname(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.DISTRIBUTORSURNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getDistributorSurname(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.DISTRIBUTORSURNAME, alias, displayLabel);

  }
 
  public dss.vector.solutions.geo.generated.HealthFacilityQuery.HealthFacilityQueryReferenceIF getFacility()
  {
    return getFacility(null);

  }
 
  public dss.vector.solutions.geo.generated.HealthFacilityQuery.HealthFacilityQueryReferenceIF getFacility(String alias)
  {

    return (dss.vector.solutions.geo.generated.HealthFacilityQuery.HealthFacilityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.FACILITY, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.HealthFacilityQuery.HealthFacilityQueryReferenceIF getFacility(String alias, String displayLabel)
  {

    return (dss.vector.solutions.geo.generated.HealthFacilityQuery.HealthFacilityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.FACILITY, alias, displayLabel);

  }
  public com.terraframe.mojo.query.AttributeChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.ID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getNet()
  {
    return getNet(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getNet(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.NET, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getNet(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.NET, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getNumberSold()
  {
    return getNumberSold(null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getNumberSold(String alias)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.NUMBERSOLD, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeInteger getNumberSold(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeInteger)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.NUMBERSOLD, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getRecipientDOB()
  {
    return getRecipientDOB(null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getRecipientDOB(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.RECIPIENTDOB, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getRecipientDOB(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.RECIPIENTDOB, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getRecipientFirstName()
  {
    return getRecipientFirstName(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getRecipientFirstName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.RECIPIENTFIRSTNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getRecipientFirstName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.RECIPIENTFIRSTNAME, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getRecipientLastName()
  {
    return getRecipientLastName(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getRecipientLastName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.RECIPIENTLASTNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getRecipientLastName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.RECIPIENTLASTNAME, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getService()
  {
    return getService(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getService(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.SERVICE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getService(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.SERVICE, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends ITNDistributionExcelView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<ITNDistributionExcelView>(this.getMdClassIF(), valueIterator);
  }

}
