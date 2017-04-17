package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = -1953279308)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ImmatureCollectionExcelView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class ImmatureCollectionExcelViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public ImmatureCollectionExcelViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public ImmatureCollectionExcelViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.export.ImmatureCollectionExcelView.CLASS;
  }
  public com.runwaysdk.query.SelectableChar getCollectionId()
  {
    return getCollectionId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getCollectionId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.COLLECTIONID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getCollectionId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.COLLECTIONID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getContainerTerm()
  {
    return getContainerTerm(null);

  }
 
  public com.runwaysdk.query.SelectableChar getContainerTerm(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.CONTAINERTERM, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getContainerTerm(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.CONTAINERTERM, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableMoment getEndDate()
  {
    return getEndDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getEndDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.ENDDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getEndDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.ENDDATE, alias, displayLabel);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity()
  {
    return getGeoEntity(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.GEOENTITY, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias, String displayLabel)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.GEOENTITY, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.ID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getNotes()
  {
    return getNotes(null);

  }
 
  public com.runwaysdk.query.SelectableChar getNotes(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.NOTES, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getNotes(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.NOTES, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberContainers()
  {
    return getNumberContainers(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberContainers(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.NUMBERCONTAINERS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberContainers(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.NUMBERCONTAINERS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberDestroyed()
  {
    return getNumberDestroyed(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberDestroyed(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.NUMBERDESTROYED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberDestroyed(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.NUMBERDESTROYED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberExamined()
  {
    return getNumberExamined(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberExamined(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.NUMBEREXAMINED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberExamined(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.NUMBEREXAMINED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberImmatures()
  {
    return getNumberImmatures(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberImmatures(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.NUMBERIMMATURES, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberImmatures(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.NUMBERIMMATURES, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberInhabitants()
  {
    return getNumberInhabitants(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberInhabitants(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.NUMBERINHABITANTS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberInhabitants(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.NUMBERINHABITANTS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberLarvae()
  {
    return getNumberLarvae(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberLarvae(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.NUMBERLARVAE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberLarvae(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.NUMBERLARVAE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberLarvaeCollected()
  {
    return getNumberLarvaeCollected(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberLarvaeCollected(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.NUMBERLARVAECOLLECTED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberLarvaeCollected(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.NUMBERLARVAECOLLECTED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberPupae()
  {
    return getNumberPupae(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberPupae(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.NUMBERPUPAE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberPupae(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.NUMBERPUPAE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberPupaeCollected()
  {
    return getNumberPupaeCollected(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberPupaeCollected(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.NUMBERPUPAECOLLECTED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberPupaeCollected(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.NUMBERPUPAECOLLECTED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberWithImmatures()
  {
    return getNumberWithImmatures(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberWithImmatures(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.NUMBERWITHIMMATURES, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberWithImmatures(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.NUMBERWITHIMMATURES, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberWithLarvae()
  {
    return getNumberWithLarvae(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberWithLarvae(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.NUMBERWITHLARVAE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberWithLarvae(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.NUMBERWITHLARVAE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberWithLarvicide()
  {
    return getNumberWithLarvicide(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberWithLarvicide(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.NUMBERWITHLARVICIDE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberWithLarvicide(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.NUMBERWITHLARVICIDE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberWithPupae()
  {
    return getNumberWithPupae(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberWithPupae(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.NUMBERWITHPUPAE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberWithPupae(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.NUMBERWITHPUPAE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberWithWater()
  {
    return getNumberWithWater(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberWithWater(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.NUMBERWITHWATER, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberWithWater(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.NUMBERWITHWATER, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableDecimal getPremiseSize()
  {
    return getPremiseSize(null);

  }
 
  public com.runwaysdk.query.SelectableDecimal getPremiseSize(String alias)
  {
    return (com.runwaysdk.query.SelectableDecimal)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.PREMISESIZE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDecimal getPremiseSize(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDecimal)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.PREMISESIZE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getPremiseType()
  {
    return getPremiseType(null);

  }
 
  public com.runwaysdk.query.SelectableChar getPremiseType(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.PREMISETYPE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getPremiseType(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.PREMISETYPE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableMoment getStartDate()
  {
    return getStartDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getStartDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.STARTDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getStartDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.STARTDATE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getTaxon()
  {
    return getTaxon(null);

  }
 
  public com.runwaysdk.query.SelectableChar getTaxon(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.TAXON, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getTaxon(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.ImmatureCollectionExcelView.TAXON, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends ImmatureCollectionExcelView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<ImmatureCollectionExcelView>(this.getMdClassIF(), valueIterator);
  }

}
