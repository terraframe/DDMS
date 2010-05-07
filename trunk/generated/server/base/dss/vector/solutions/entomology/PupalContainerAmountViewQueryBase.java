package dss.vector.solutions.entomology;

@com.runwaysdk.business.ClassSignature(hash = -490059083)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to PupalContainerAmountView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class PupalContainerAmountViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = -490059083;

  public PupalContainerAmountViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public PupalContainerAmountViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.entomology.PupalContainerAmountView.CLASS;
  }
  public com.runwaysdk.query.SelectableInteger getAmount()
  {
    return getAmount(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getAmount(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.entomology.PupalContainerAmountView.AMOUNT, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getAmount(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.entomology.PupalContainerAmountView.AMOUNT, alias, displayLabel);

  }
 
  public dss.vector.solutions.entomology.PupalContainerQuery.PupalContainerQueryReferenceIF getContainer()
  {
    return getContainer(null);

  }
 
  public dss.vector.solutions.entomology.PupalContainerQuery.PupalContainerQueryReferenceIF getContainer(String alias)
  {

    return (dss.vector.solutions.entomology.PupalContainerQuery.PupalContainerQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.PupalContainerAmountView.CONTAINER, alias, null);

  }
 
  public dss.vector.solutions.entomology.PupalContainerQuery.PupalContainerQueryReferenceIF getContainer(String alias, String displayLabel)
  {

    return (dss.vector.solutions.entomology.PupalContainerQuery.PupalContainerQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.PupalContainerAmountView.CONTAINER, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.entomology.PupalContainerAmountView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.entomology.PupalContainerAmountView.ID, alias, displayLabel);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTerm()
  {
    return getTerm(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTerm(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.PupalContainerAmountView.TERM, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTerm(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.PupalContainerAmountView.TERM, alias, displayLabel);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends PupalContainerAmountView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<PupalContainerAmountView>(this.getMdClassIF(), valueIterator);
  }

}
