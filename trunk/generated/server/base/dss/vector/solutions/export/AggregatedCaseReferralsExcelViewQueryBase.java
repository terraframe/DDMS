package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = 12684573)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AggregatedCaseReferralsExcelView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class AggregatedCaseReferralsExcelViewQueryBase extends dss.vector.solutions.export.AggregatedCaseExcelViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = 12684573;

  public AggregatedCaseReferralsExcelViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public AggregatedCaseReferralsExcelViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.export.AggregatedCaseReferralsExcelView.CLASS;
  }
  public com.runwaysdk.query.SelectableInteger getCases()
  {
    return getCases(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getCases(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.AggregatedCaseReferralsExcelView.CASES, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getCases(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.AggregatedCaseReferralsExcelView.CASES, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getDeaths()
  {
    return getDeaths(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getDeaths(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.AggregatedCaseReferralsExcelView.DEATHS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getDeaths(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.AggregatedCaseReferralsExcelView.DEATHS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNegativeCases()
  {
    return getNegativeCases(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNegativeCases(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.AggregatedCaseReferralsExcelView.NEGATIVECASES, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNegativeCases(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.AggregatedCaseReferralsExcelView.NEGATIVECASES, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getPositiveCases()
  {
    return getPositiveCases(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPositiveCases(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.AggregatedCaseReferralsExcelView.POSITIVECASES, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPositiveCases(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.AggregatedCaseReferralsExcelView.POSITIVECASES, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends AggregatedCaseReferralsExcelView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<AggregatedCaseReferralsExcelView>(this.getMdClassIF(), valueIterator);
  }

}
