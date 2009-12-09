package dss.vector.solutions.entomology;

@com.terraframe.mojo.business.ClassSignature(hash = 1593770432)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MosquitoCollectionView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class MosquitoCollectionViewQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1593770432;

  public MosquitoCollectionViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public MosquitoCollectionViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.entomology.MosquitoCollectionView.CLASS;
  }
  public com.terraframe.mojo.query.AttributeBoolean getAbundance()
  {
    return getAbundance(null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getAbundance(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.ABUNDANCE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeBoolean getAbundance(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeBoolean)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.ABUNDANCE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getCollectionDate()
  {
    return getCollectionDate(null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getCollectionDate(String alias)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.COLLECTIONDATE, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeMoment getCollectionDate(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeMoment)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.COLLECTIONDATE, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getCollectionId()
  {
    return getCollectionId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getCollectionId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.COLLECTIONID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getCollectionId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.COLLECTIONID, alias, displayLabel);

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
  public com.terraframe.mojo.query.AttributeChar getCollectionMethodLabel()
  {
    return getCollectionMethodLabel(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getCollectionMethodLabel(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.COLLECTIONMETHODLABEL, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getCollectionMethodLabel(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.COLLECTIONMETHODLABEL, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getConcreteId()
  {
    return getConcreteId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getConcreteId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.CONCRETEID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getConcreteId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.CONCRETEID, alias, displayLabel);

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
  public com.terraframe.mojo.query.AttributeChar getGeoEntityLabel()
  {
    return getGeoEntityLabel(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getGeoEntityLabel(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.GEOENTITYLABEL, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getGeoEntityLabel(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.GEOENTITYLABEL, alias, displayLabel);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.ID, alias, null);

  }
 
  public com.terraframe.mojo.query.AttributeChar getId(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.AttributeChar)this.getSelectable(dss.vector.solutions.entomology.MosquitoCollectionView.ID, alias, displayLabel);

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
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends MosquitoCollectionView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<MosquitoCollectionView>(this.getMdClassIF(), valueIterator);
  }

}
