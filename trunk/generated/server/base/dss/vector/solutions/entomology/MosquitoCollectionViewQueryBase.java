package dss.vector.solutions.entomology;

@com.runwaysdk.business.ClassSignature(hash = -278280918)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MosquitoCollectionView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class MosquitoCollectionViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public MosquitoCollectionViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public MosquitoCollectionViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.entomology.MosquitoCollectionView.CLASS;
  }
  public com.runwaysdk.query.SelectableBoolean getAbundance()
  {
    return getAbundance(null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getAbundance(String alias)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.ABUNDANCE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getAbundance(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.ABUNDANCE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableMoment getCollectionDate()
  {
    return getCollectionDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getCollectionDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.COLLECTIONDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getCollectionDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.COLLECTIONDATE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getCollectionId()
  {
    return getCollectionId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getCollectionId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.COLLECTIONID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getCollectionId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.COLLECTIONID, alias, displayLabel);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getCollectionMethod()
  {
    return getCollectionMethod(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getCollectionMethod(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.COLLECTIONMETHOD, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getCollectionMethod(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.COLLECTIONMETHOD, alias, displayLabel);

  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getCollectionRound()
  {
    return getCollectionRound(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getCollectionRound(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.COLLECTIONROUND, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getCollectionRound(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.COLLECTIONROUND, alias, displayLabel);

  }
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getCollectionType()
  {
    return getCollectionType(null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getCollectionType(String alias)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.COLLECTIONTYPE, alias, null);

  }
 
  public dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF getCollectionType(String alias, String displayLabel)
  {

    return (dss.vector.solutions.ontology.TermQuery.TermQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.COLLECTIONTYPE, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getConcreteId()
  {
    return getConcreteId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.CONCRETEID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getConcreteId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.CONCRETEID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableMoment getDateLastSprayed()
  {
    return getDateLastSprayed(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getDateLastSprayed(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.DATELASTSPRAYED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getDateLastSprayed(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.DATELASTSPRAYED, alias, displayLabel);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity()
  {
    return getGeoEntity(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.GEOENTITY, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias, String displayLabel)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.GEOENTITY, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.ID, alias, displayLabel);

  }
 
  public dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF getInsecticideBrand()
  {
    return getInsecticideBrand(null);

  }
 
  public dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF getInsecticideBrand(String alias)
  {

    return (dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.INSECTICIDEBRAND, alias, null);

  }
 
  public dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF getInsecticideBrand(String alias, String displayLabel)
  {

    return (dss.vector.solutions.irs.InsecticideBrandQuery.InsecticideBrandQueryReferenceIF)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.INSECTICIDEBRAND, alias, displayLabel);

  }
  public dss.vector.solutions.entomology.LifeStageMasterQuery.LifeStageQueryIF getLifeStage()
  {
    return getLifeStage(null);

  }
 
  public dss.vector.solutions.entomology.LifeStageMasterQuery.LifeStageQueryIF getLifeStage(String alias)
  {
    return (dss.vector.solutions.entomology.LifeStageMasterQuery.LifeStageQueryIF)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.LIFESTAGE, alias, null);

  }
 
  public dss.vector.solutions.entomology.LifeStageMasterQuery.LifeStageQueryIF getLifeStage(String alias, String displayLabel)
  {
    return (dss.vector.solutions.entomology.LifeStageMasterQuery.LifeStageQueryIF)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.LIFESTAGE, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableInteger getNumberOfAnimalOccupants()
  {
    return getNumberOfAnimalOccupants(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberOfAnimalOccupants(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.NUMBEROFANIMALOCCUPANTS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberOfAnimalOccupants(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.NUMBEROFANIMALOCCUPANTS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberOfHumanOccupants()
  {
    return getNumberOfHumanOccupants(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberOfHumanOccupants(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.NUMBEROFHUMANOCCUPANTS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberOfHumanOccupants(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.NUMBEROFHUMANOCCUPANTS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberOfLLINs()
  {
    return getNumberOfLLINs(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberOfLLINs(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.NUMBEROFLLINS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberOfLLINs(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.NUMBEROFLLINS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getResistanceAssayComments()
  {
    return getResistanceAssayComments(null);

  }
 
  public com.runwaysdk.query.SelectableChar getResistanceAssayComments(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.RESISTANCEASSAYCOMMENTS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getResistanceAssayComments(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.RESISTANCEASSAYCOMMENTS, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends MosquitoCollectionView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<MosquitoCollectionView>(this.getMdClassIF(), valueIterator);
  }

}
