package dss.vector.solutions.entomology;

@com.runwaysdk.business.ClassSignature(hash = 541437881)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to BiochemicalAssayView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class BiochemicalAssayViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = 541437881;

  public BiochemicalAssayViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public BiochemicalAssayViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.entomology.BiochemicalAssayView.CLASS;
  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getAssay()
  {
    return getAssay(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getAssay(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.BiochemicalAssayView.ASSAY, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getAssay(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.BiochemicalAssayView.ASSAY, alias, displayLabel);

  }
  public dss.vector.solutions.entomology.MosquitoCollectionQuery.MosquitoCollectionQueryReferenceIF getCollection()
  {
    return getCollection(null);

  }
 
  public dss.vector.solutions.entomology.MosquitoCollectionQuery.MosquitoCollectionQueryReferenceIF getCollection(String alias)
  {

    return (dss.vector.solutions.entomology.MosquitoCollectionQuery.MosquitoCollectionQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.BiochemicalAssayView.COLLECTION, alias, null);

  }
 
  public dss.vector.solutions.entomology.MosquitoCollectionQuery.MosquitoCollectionQueryReferenceIF getCollection(String alias, String displayLabel)
  {

    return (dss.vector.solutions.entomology.MosquitoCollectionQuery.MosquitoCollectionQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.BiochemicalAssayView.COLLECTION, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getConcreteId()
  {
    return getConcreteId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.entomology.BiochemicalAssayView.CONCRETEID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.entomology.BiochemicalAssayView.CONCRETEID, alias, displayLabel);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getGeneration()
  {
    return getGeneration(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getGeneration(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.BiochemicalAssayView.GENERATION, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getGeneration(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.BiochemicalAssayView.GENERATION, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.entomology.BiochemicalAssayView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.entomology.BiochemicalAssayView.ID, alias, displayLabel);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getIdentMethod()
  {
    return getIdentMethod(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getIdentMethod(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.BiochemicalAssayView.IDENTMETHOD, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getIdentMethod(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.BiochemicalAssayView.IDENTMETHOD, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableBoolean getIsofemale()
  {
    return getIsofemale(null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getIsofemale(String alias)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.entomology.BiochemicalAssayView.ISOFEMALE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getIsofemale(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.entomology.BiochemicalAssayView.ISOFEMALE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getMosquitoId()
  {
    return getMosquitoId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getMosquitoId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.entomology.BiochemicalAssayView.MOSQUITOID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getMosquitoId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.entomology.BiochemicalAssayView.MOSQUITOID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberElevated()
  {
    return getNumberElevated(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberElevated(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.entomology.BiochemicalAssayView.NUMBERELEVATED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberElevated(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.entomology.BiochemicalAssayView.NUMBERELEVATED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberTested()
  {
    return getNumberTested(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberTested(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.entomology.BiochemicalAssayView.NUMBERTESTED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberTested(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.entomology.BiochemicalAssayView.NUMBERTESTED, alias, displayLabel);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSex()
  {
    return getSex(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSex(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.BiochemicalAssayView.SEX, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSex(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.BiochemicalAssayView.SEX, alias, displayLabel);

  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSpecies()
  {
    return getSpecies(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSpecies(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.BiochemicalAssayView.SPECIES, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getSpecies(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.BiochemicalAssayView.SPECIES, alias, displayLabel);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends BiochemicalAssayView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<BiochemicalAssayView>(this.getMdClassIF(), valueIterator);
  }

}
