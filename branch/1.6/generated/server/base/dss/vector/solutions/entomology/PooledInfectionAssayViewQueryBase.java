package dss.vector.solutions.entomology;

@com.runwaysdk.business.ClassSignature(hash = -746491511)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to PooledInfectionAssayView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class PooledInfectionAssayViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public PooledInfectionAssayViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public PooledInfectionAssayViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.entomology.PooledInfectionAssayView.CLASS;
  }
  public dss.vector.solutions.entomology.MosquitoCollectionQuery.MosquitoCollectionQueryReferenceIF getCollection()
  {
    return getCollection(null);

  }
 
  public dss.vector.solutions.entomology.MosquitoCollectionQuery.MosquitoCollectionQueryReferenceIF getCollection(String alias)
  {

    return (dss.vector.solutions.entomology.MosquitoCollectionQuery.MosquitoCollectionQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.PooledInfectionAssayView.COLLECTION, alias, null);

  }
 
  public dss.vector.solutions.entomology.MosquitoCollectionQuery.MosquitoCollectionQueryReferenceIF getCollection(String alias, String displayLabel)
  {

    return (dss.vector.solutions.entomology.MosquitoCollectionQuery.MosquitoCollectionQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.PooledInfectionAssayView.COLLECTION, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getConcreteId()
  {
    return getConcreteId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.entomology.PooledInfectionAssayView.CONCRETEID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.entomology.PooledInfectionAssayView.CONCRETEID, alias, displayLabel);

  }
 
  public dss.vector.solutions.general.DiseaseQuery.DiseaseQueryReferenceIF getDisease()
  {
    return getDisease(null);

  }
 
  public dss.vector.solutions.general.DiseaseQuery.DiseaseQueryReferenceIF getDisease(String alias)
  {

    return (dss.vector.solutions.general.DiseaseQuery.DiseaseQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.PooledInfectionAssayView.DISEASE, alias, null);

  }
 
  public dss.vector.solutions.general.DiseaseQuery.DiseaseQueryReferenceIF getDisease(String alias, String displayLabel)
  {

    return (dss.vector.solutions.general.DiseaseQuery.DiseaseQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.PooledInfectionAssayView.DISEASE, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.entomology.PooledInfectionAssayView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.entomology.PooledInfectionAssayView.ID, alias, displayLabel);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getIdentMethod()
  {
    return getIdentMethod(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getIdentMethod(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.PooledInfectionAssayView.IDENTMETHOD, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getIdentMethod(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.PooledInfectionAssayView.IDENTMETHOD, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableBoolean getInfected()
  {
    return getInfected(null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getInfected(String alias)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.entomology.PooledInfectionAssayView.INFECTED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getInfected(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.entomology.PooledInfectionAssayView.INFECTED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getMosquitosTested()
  {
    return getMosquitosTested(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getMosquitosTested(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.entomology.PooledInfectionAssayView.MOSQUITOSTESTED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getMosquitosTested(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.entomology.PooledInfectionAssayView.MOSQUITOSTESTED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberPositive()
  {
    return getNumberPositive(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberPositive(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.entomology.PooledInfectionAssayView.NUMBERPOSITIVE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberPositive(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.entomology.PooledInfectionAssayView.NUMBERPOSITIVE, alias, displayLabel);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getParasite()
  {
    return getParasite(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getParasite(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.PooledInfectionAssayView.PARASITE, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getParasite(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.PooledInfectionAssayView.PARASITE, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getPoolId()
  {
    return getPoolId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getPoolId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.entomology.PooledInfectionAssayView.POOLID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getPoolId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.entomology.PooledInfectionAssayView.POOLID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getPoolsTested()
  {
    return getPoolsTested(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPoolsTested(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.entomology.PooledInfectionAssayView.POOLSTESTED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPoolsTested(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.entomology.PooledInfectionAssayView.POOLSTESTED, alias, displayLabel);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSex()
  {
    return getSex(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSex(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.PooledInfectionAssayView.SEX, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSex(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.PooledInfectionAssayView.SEX, alias, displayLabel);

  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSpecies()
  {
    return getSpecies(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSpecies(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.PooledInfectionAssayView.SPECIES, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSpecies(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.PooledInfectionAssayView.SPECIES, alias, displayLabel);

  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTestMethod()
  {
    return getTestMethod(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTestMethod(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.PooledInfectionAssayView.TESTMETHOD, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getTestMethod(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.PooledInfectionAssayView.TESTMETHOD, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getUniqueAssayId()
  {
    return getUniqueAssayId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getUniqueAssayId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.entomology.PooledInfectionAssayView.UNIQUEASSAYID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getUniqueAssayId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.entomology.PooledInfectionAssayView.UNIQUEASSAYID, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends PooledInfectionAssayView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<PooledInfectionAssayView>(this.getMdClassIF(), valueIterator);
  }

}
