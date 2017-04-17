package dss.vector.solutions.surveillance;

@com.runwaysdk.business.ClassSignature(hash = -1997297876)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to CaseTreatmentMethodView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class CaseTreatmentMethodViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public CaseTreatmentMethodViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public CaseTreatmentMethodViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.surveillance.CaseTreatmentMethodView.CLASS;
  }
  public dss.vector.solutions.surveillance.AggregatedCaseQuery.AggregatedCaseQueryReferenceIF getAggregatedCase()
  {
    return getAggregatedCase(null);

  }
 
  public dss.vector.solutions.surveillance.AggregatedCaseQuery.AggregatedCaseQueryReferenceIF getAggregatedCase(String alias)
  {

    return (dss.vector.solutions.surveillance.AggregatedCaseQuery.AggregatedCaseQueryReferenceIF)this.getSelectable(dss.vector.solutions.surveillance.CaseTreatmentMethodView.AGGREGATEDCASE, alias, null);

  }
 
  public dss.vector.solutions.surveillance.AggregatedCaseQuery.AggregatedCaseQueryReferenceIF getAggregatedCase(String alias, String displayLabel)
  {

    return (dss.vector.solutions.surveillance.AggregatedCaseQuery.AggregatedCaseQueryReferenceIF)this.getSelectable(dss.vector.solutions.surveillance.CaseTreatmentMethodView.AGGREGATEDCASE, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getAmount()
  {
    return getAmount(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getAmount(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.surveillance.CaseTreatmentMethodView.AMOUNT, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getAmount(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.surveillance.CaseTreatmentMethodView.AMOUNT, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId()
  {
    return getConcreteId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.surveillance.CaseTreatmentMethodView.CONCRETEID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.surveillance.CaseTreatmentMethodView.CONCRETEID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.surveillance.CaseTreatmentMethodView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.surveillance.CaseTreatmentMethodView.ID, alias, displayLabel);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTerm()
  {
    return getTerm(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTerm(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.surveillance.CaseTreatmentMethodView.TERM, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTerm(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.surveillance.CaseTreatmentMethodView.TERM, alias, displayLabel);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends CaseTreatmentMethodView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<CaseTreatmentMethodView>(this.getMdClassIF(), valueIterator);
  }

}
