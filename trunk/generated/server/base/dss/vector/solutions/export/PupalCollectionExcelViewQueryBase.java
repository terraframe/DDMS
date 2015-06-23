package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = 1222960186)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to PupalCollectionExcelView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class PupalCollectionExcelViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public PupalCollectionExcelViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public PupalCollectionExcelViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.export.PupalCollectionExcelView.CLASS;
  }
  public com.runwaysdk.query.SelectableChar getCollectionId()
  {
    return getCollectionId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getCollectionId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.COLLECTIONID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getCollectionId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.COLLECTIONID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getContainerId()
  {
    return getContainerId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getContainerId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.CONTAINERID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getContainerId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.CONTAINERID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableDecimal getContainerLength()
  {
    return getContainerLength(null);

  }
 
  public com.runwaysdk.query.SelectableDecimal getContainerLength(String alias)
  {
    return (com.runwaysdk.query.SelectableDecimal)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.CONTAINERLENGTH, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDecimal getContainerLength(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDecimal)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.CONTAINERLENGTH, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getContainerType()
  {
    return getContainerType(null);

  }
 
  public com.runwaysdk.query.SelectableChar getContainerType(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.CONTAINERTYPE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getContainerType(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.CONTAINERTYPE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableDecimal getDiameter()
  {
    return getDiameter(null);

  }
 
  public com.runwaysdk.query.SelectableDecimal getDiameter(String alias)
  {
    return (com.runwaysdk.query.SelectableDecimal)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.DIAMETER, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDecimal getDiameter(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDecimal)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.DIAMETER, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getDrawdownFrequency()
  {
    return getDrawdownFrequency(null);

  }
 
  public com.runwaysdk.query.SelectableChar getDrawdownFrequency(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.DRAWDOWNFREQUENCY, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getDrawdownFrequency(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.DRAWDOWNFREQUENCY, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getDrawdownPercent()
  {
    return getDrawdownPercent(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getDrawdownPercent(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.DRAWDOWNPERCENT, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getDrawdownPercent(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.DRAWDOWNPERCENT, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableMoment getEndDate()
  {
    return getEndDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getEndDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.ENDDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getEndDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.ENDDATE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getFillFrequency()
  {
    return getFillFrequency(null);

  }
 
  public com.runwaysdk.query.SelectableChar getFillFrequency(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.FILLFREQUENCY, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getFillFrequency(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.FILLFREQUENCY, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getFillMethod()
  {
    return getFillMethod(null);

  }
 
  public com.runwaysdk.query.SelectableChar getFillMethod(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.FILLMETHOD, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getFillMethod(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.FILLMETHOD, alias, displayLabel);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity()
  {
    return getGeoEntity(null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.GEOENTITY, alias, null);

  }
 
  public dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF getGeoEntity(String alias, String displayLabel)
  {

    return (dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.GEOENTITY, alias, displayLabel);

  }
  public com.runwaysdk.query.SelectableDecimal getHeight()
  {
    return getHeight(null);

  }
 
  public com.runwaysdk.query.SelectableDecimal getHeight(String alias)
  {
    return (com.runwaysdk.query.SelectableDecimal)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.HEIGHT, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDecimal getHeight(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDecimal)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.HEIGHT, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.ID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getLid()
  {
    return getLid(null);

  }
 
  public com.runwaysdk.query.SelectableChar getLid(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.LID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getLid(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.LID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getNotes()
  {
    return getNotes(null);

  }
 
  public com.runwaysdk.query.SelectableChar getNotes(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.NOTES, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getNotes(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.NOTES, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberExamined()
  {
    return getNumberExamined(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberExamined(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.NUMBEREXAMINED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberExamined(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.NUMBEREXAMINED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberInhabitants()
  {
    return getNumberInhabitants(null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberInhabitants(String alias)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.NUMBERINHABITANTS, alias, null);

  }
 
  public com.runwaysdk.query.SelectableInteger getNumberInhabitants(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableInteger)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.NUMBERINHABITANTS, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableDecimal getOpeningDiameter()
  {
    return getOpeningDiameter(null);

  }
 
  public com.runwaysdk.query.SelectableDecimal getOpeningDiameter(String alias)
  {
    return (com.runwaysdk.query.SelectableDecimal)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.OPENINGDIAMETER, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDecimal getOpeningDiameter(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDecimal)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.OPENINGDIAMETER, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableDecimal getOpeningLength()
  {
    return getOpeningLength(null);

  }
 
  public com.runwaysdk.query.SelectableDecimal getOpeningLength(String alias)
  {
    return (com.runwaysdk.query.SelectableDecimal)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.OPENINGLENGTH, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDecimal getOpeningLength(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDecimal)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.OPENINGLENGTH, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableDecimal getOpeningWidth()
  {
    return getOpeningWidth(null);

  }
 
  public com.runwaysdk.query.SelectableDecimal getOpeningWidth(String alias)
  {
    return (com.runwaysdk.query.SelectableDecimal)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.OPENINGWIDTH, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDecimal getOpeningWidth(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDecimal)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.OPENINGWIDTH, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableDecimal getPremiseSize()
  {
    return getPremiseSize(null);

  }
 
  public com.runwaysdk.query.SelectableDecimal getPremiseSize(String alias)
  {
    return (com.runwaysdk.query.SelectableDecimal)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.PREMISESIZE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDecimal getPremiseSize(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDecimal)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.PREMISESIZE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getPremiseType()
  {
    return getPremiseType(null);

  }
 
  public com.runwaysdk.query.SelectableChar getPremiseType(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.PREMISETYPE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getPremiseType(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.PREMISETYPE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getRoof()
  {
    return getRoof(null);

  }
 
  public com.runwaysdk.query.SelectableChar getRoof(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.ROOF, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getRoof(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.ROOF, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getShading()
  {
    return getShading(null);

  }
 
  public com.runwaysdk.query.SelectableChar getShading(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.SHADING, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getShading(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.SHADING, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getShape()
  {
    return getShape(null);

  }
 
  public com.runwaysdk.query.SelectableChar getShape(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.SHAPE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getShape(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.SHAPE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableMoment getStartDate()
  {
    return getStartDate(null);

  }
 
  public com.runwaysdk.query.SelectableMoment getStartDate(String alias)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.STARTDATE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableMoment getStartDate(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableMoment)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.STARTDATE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableDecimal getWidth()
  {
    return getWidth(null);

  }
 
  public com.runwaysdk.query.SelectableDecimal getWidth(String alias)
  {
    return (com.runwaysdk.query.SelectableDecimal)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.WIDTH, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDecimal getWidth(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDecimal)this.getSelectable(dss.vector.solutions.export.PupalCollectionExcelView.WIDTH, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends PupalCollectionExcelView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<PupalCollectionExcelView>(this.getMdClassIF(), valueIterator);
  }

}
