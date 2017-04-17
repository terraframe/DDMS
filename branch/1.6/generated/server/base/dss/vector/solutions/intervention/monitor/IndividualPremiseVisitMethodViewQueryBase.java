package dss.vector.solutions.intervention.monitor;

@com.runwaysdk.business.ClassSignature(hash = -723145591)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to IndividualPremiseVisitMethodView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class IndividualPremiseVisitMethodViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public IndividualPremiseVisitMethodViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public IndividualPremiseVisitMethodViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodView.CLASS;
  }
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodView.ID, alias, displayLabel);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTerm()
  {
    return getTerm(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTerm(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodView.TERM, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTerm(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodView.TERM, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableBoolean getUsed()
  {
    return getUsed(null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getUsed(String alias)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodView.USED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getUsed(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodView.USED, alias, displayLabel);

  }
 
  public dss.vector.solutions.intervention.monitor.IndividualPremiseVisitQuery.IndividualPremiseVisitQueryReferenceIF getVisit()
  {
    return getVisit(null);

  }
 
  public dss.vector.solutions.intervention.monitor.IndividualPremiseVisitQuery.IndividualPremiseVisitQueryReferenceIF getVisit(String alias)
  {

    return (dss.vector.solutions.intervention.monitor.IndividualPremiseVisitQuery.IndividualPremiseVisitQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodView.VISIT, alias, null);

  }
 
  public dss.vector.solutions.intervention.monitor.IndividualPremiseVisitQuery.IndividualPremiseVisitQueryReferenceIF getVisit(String alias, String displayLabel)
  {

    return (dss.vector.solutions.intervention.monitor.IndividualPremiseVisitQuery.IndividualPremiseVisitQueryReferenceIF)this.getSelectable(dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodView.VISIT, alias, displayLabel);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends IndividualPremiseVisitMethodView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<IndividualPremiseVisitMethodView>(this.getMdClassIF(), valueIterator);
  }

}
