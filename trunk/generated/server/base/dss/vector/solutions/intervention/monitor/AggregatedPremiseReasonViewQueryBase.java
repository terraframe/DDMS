package dss.vector.solutions.intervention.monitor;

@com.runwaysdk.business.ClassSignature(hash = -1853494380)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AggregatedPremiseReasonView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class AggregatedPremiseReasonViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public AggregatedPremiseReasonViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public AggregatedPremiseReasonViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonView.CLASS;
  }
  public com.runwaysdk.query.SelectableInteger getAmount()
  {
    return getAmount(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getAmount(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonView.AMOUNT, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getAmount(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonView.AMOUNT, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonView.ID, alias, displayLabel);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTerm()
  {
    return getTerm(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTerm(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonView.TERM, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTerm(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonView.TERM, alias, displayLabel);

  }
  public dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitQuery.AggregatedPremiseVisitQueryReferenceIF getVisit()
  {
    return getVisit(null);

  }
 
  public dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitQuery.AggregatedPremiseVisitQueryReferenceIF getVisit(String alias)
  {

    return (dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitQuery.AggregatedPremiseVisitQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonView.VISIT, alias, null);

  }
 
  public dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitQuery.AggregatedPremiseVisitQueryReferenceIF getVisit(String alias, String displayLabel)
  {

    return (dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitQuery.AggregatedPremiseVisitQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonView.VISIT, alias, displayLabel);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends AggregatedPremiseReasonView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<AggregatedPremiseReasonView>(this.getMdClassIF(), valueIterator);
  }

}
