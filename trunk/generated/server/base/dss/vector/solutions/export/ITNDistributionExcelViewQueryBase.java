package dss.vector.solutions.export;

@com.terraframe.mojo.business.ClassSignature(hash = -1335726309)
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
private static final long serialVersionUID = -1335726309;

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
  public com.terraframe.mojo.query.SelectableChar getBatchNumber()
  {
    return getBatchNumber(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getBatchNumber(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.BATCHNUMBER, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getBatchNumber(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.BATCHNUMBER, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableDouble getCurrencyReceived()
  {
    return getCurrencyReceived(null);

  }
 
  public com.terraframe.mojo.query.SelectableDouble getCurrencyReceived(String alias)
  {
    return (com.terraframe.mojo.query.SelectableDouble)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.CURRENCYRECEIVED, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableDouble getCurrencyReceived(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableDouble)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.CURRENCYRECEIVED, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableMoment getDistributionDate()
  {
    return getDistributionDate(null);

  }
 
  public com.terraframe.mojo.query.SelectableMoment getDistributionDate(String alias)
  {
    return (com.terraframe.mojo.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.DISTRIBUTIONDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableMoment getDistributionDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.DISTRIBUTIONDATE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableChar getDistributorName()
  {
    return getDistributorName(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getDistributorName(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.DISTRIBUTORNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getDistributorName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.DISTRIBUTORNAME, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableChar getDistributorSurname()
  {
    return getDistributorSurname(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getDistributorSurname(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.DISTRIBUTORSURNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getDistributorSurname(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.DISTRIBUTORSURNAME, alias, displayLabel);

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
  public com.terraframe.mojo.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getId(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.ID, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableChar getNet()
  {
    return getNet(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getNet(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.NET, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getNet(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.NET, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getNumberSold()
  {
    return getNumberSold(null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getNumberSold(String alias)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.NUMBERSOLD, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableInteger getNumberSold(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.NUMBERSOLD, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableMoment getRecipientDOB()
  {
    return getRecipientDOB(null);

  }
 
  public com.terraframe.mojo.query.SelectableMoment getRecipientDOB(String alias)
  {
    return (com.terraframe.mojo.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.RECIPIENTDOB, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableMoment getRecipientDOB(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.RECIPIENTDOB, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableChar getRecipientFirstName()
  {
    return getRecipientFirstName(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getRecipientFirstName(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.RECIPIENTFIRSTNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getRecipientFirstName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.RECIPIENTFIRSTNAME, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableChar getRecipientLastName()
  {
    return getRecipientLastName(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getRecipientLastName(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.RECIPIENTLASTNAME, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getRecipientLastName(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.RECIPIENTLASTNAME, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.SelectableChar getService()
  {
    return getService(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getService(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.SERVICE, alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getService(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ITNDistributionExcelView.SERVICE, alias, displayLabel);

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
