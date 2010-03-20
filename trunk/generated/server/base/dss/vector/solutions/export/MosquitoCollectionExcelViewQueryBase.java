package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = -533339993)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MosquitoCollectionExcelView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class MosquitoCollectionExcelViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = -533339993;

  public MosquitoCollectionExcelViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public MosquitoCollectionExcelViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.export.MosquitoCollectionExcelView.CLASS;
  }
  public com.runwaysdk.query.SelectableBoolean getAbundance()
  {
    return getAbundance(null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getAbundance(String alias)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.export.MosquitoCollectionExcelView.ABUNDANCE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getAbundance(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.export.MosquitoCollectionExcelView.ABUNDANCE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableMoment getCollectionDate()
  {
    return getCollectionDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getCollectionDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.MosquitoCollectionExcelView.COLLECTIONDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getCollectionDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.MosquitoCollectionExcelView.COLLECTIONDATE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getCollectionId()
  {
    return getCollectionId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getCollectionId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.MosquitoCollectionExcelView.COLLECTIONID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getCollectionId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.MosquitoCollectionExcelView.COLLECTIONID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getCollectionMethod()
  {
    return getCollectionMethod(null);

  }
 
  public com.runwaysdk.query.SelectableChar getCollectionMethod(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.MosquitoCollectionExcelView.COLLECTIONMETHOD, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getCollectionMethod(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.MosquitoCollectionExcelView.COLLECTIONMETHOD, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getEggs()
  {
    return getEggs(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getEggs(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.MosquitoCollectionExcelView.EGGS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getEggs(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.MosquitoCollectionExcelView.EGGS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getFemale()
  {
    return getFemale(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getFemale(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.MosquitoCollectionExcelView.FEMALE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getFemale(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.MosquitoCollectionExcelView.FEMALE, alias, displayLabel);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity()
  {
    return getGeoEntity(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.MosquitoCollectionExcelView.GEOENTITY, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias, String displayLabel)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.MosquitoCollectionExcelView.GEOENTITY, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.MosquitoCollectionExcelView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.MosquitoCollectionExcelView.ID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getIdentMethod()
  {
    return getIdentMethod(null);

  }
 
  public com.runwaysdk.query.SelectableChar getIdentMethod(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.MosquitoCollectionExcelView.IDENTMETHOD, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getIdentMethod(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.MosquitoCollectionExcelView.IDENTMETHOD, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getLarvae()
  {
    return getLarvae(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getLarvae(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.MosquitoCollectionExcelView.LARVAE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getLarvae(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.MosquitoCollectionExcelView.LARVAE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getLifeStage()
  {
    return getLifeStage(null);

  }
 
  public com.runwaysdk.query.SelectableChar getLifeStage(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.MosquitoCollectionExcelView.LIFESTAGE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getLifeStage(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.MosquitoCollectionExcelView.LIFESTAGE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getMale()
  {
    return getMale(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getMale(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.MosquitoCollectionExcelView.MALE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getMale(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.MosquitoCollectionExcelView.MALE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getPupae()
  {
    return getPupae(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPupae(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.MosquitoCollectionExcelView.PUPAE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getPupae(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.MosquitoCollectionExcelView.PUPAE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getSubCollectionId()
  {
    return getSubCollectionId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getSubCollectionId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.MosquitoCollectionExcelView.SUBCOLLECTIONID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getSubCollectionId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.MosquitoCollectionExcelView.SUBCOLLECTIONID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getTaxon()
  {
    return getTaxon(null);

  }
 
  public com.runwaysdk.query.SelectableChar getTaxon(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.MosquitoCollectionExcelView.TAXON, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getTaxon(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.MosquitoCollectionExcelView.TAXON, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getUnknowns()
  {
    return getUnknowns(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getUnknowns(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.MosquitoCollectionExcelView.UNKNOWNS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getUnknowns(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.MosquitoCollectionExcelView.UNKNOWNS, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends MosquitoCollectionExcelView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<MosquitoCollectionExcelView>(this.getMdClassIF(), valueIterator);
  }

}
