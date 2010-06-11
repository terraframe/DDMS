package dss.vector.solutions.surveillance;

@com.runwaysdk.business.ClassSignature(hash = -1330672062)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to CaseDiseaseManifestationAmountView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class CaseDiseaseManifestationAmountViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = -1330672062;

  public CaseDiseaseManifestationAmountViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public CaseDiseaseManifestationAmountViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountView.CLASS;
  }
  public com.runwaysdk.query.SelectableInteger getAmount()
  {
    return getAmount(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getAmount(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountView.AMOUNT, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getAmount(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountView.AMOUNT, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountView.ID, alias, displayLabel);

  }
 
  public dss.vector.solutions.surveillance.CaseDiseaseManifestationQuery.CaseDiseaseManifestationQueryReferenceIF getManifestation()
  {
    return getManifestation(null);

  }
 
  public dss.vector.solutions.surveillance.CaseDiseaseManifestationQuery.CaseDiseaseManifestationQueryReferenceIF getManifestation(String alias)
  {

    return (dss.vector.solutions.surveillance.CaseDiseaseManifestationQuery.CaseDiseaseManifestationQueryReferenceIF)this.getSelectable(dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountView.MANIFESTATION, alias, null);

  }
 
  public dss.vector.solutions.surveillance.CaseDiseaseManifestationQuery.CaseDiseaseManifestationQueryReferenceIF getManifestation(String alias, String displayLabel)
  {

    return (dss.vector.solutions.surveillance.CaseDiseaseManifestationQuery.CaseDiseaseManifestationQueryReferenceIF)this.getSelectable(dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountView.MANIFESTATION, alias, displayLabel);

  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTerm()
  {
    return getTerm(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTerm(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountView.TERM, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTerm(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountView.TERM, alias, displayLabel);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends CaseDiseaseManifestationAmountView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<CaseDiseaseManifestationAmountView>(this.getMdClassIF(), valueIterator);
  }

}
