package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = -800168435)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to GeoEntityExcelView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class GeoEntityExcelViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public GeoEntityExcelViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public GeoEntityExcelViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.export.GeoEntityExcelView.CLASS;
  }
  public com.runwaysdk.query.SelectableBoolean getActivated()
  {
    return getActivated(null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getActivated(String alias)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.export.GeoEntityExcelView.ACTIVATED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getActivated(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.export.GeoEntityExcelView.ACTIVATED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getEntityLabel()
  {
    return getEntityLabel(null);

  }
 
  public com.runwaysdk.query.SelectableChar getEntityLabel(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.GeoEntityExcelView.ENTITYLABEL, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getEntityLabel(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.GeoEntityExcelView.ENTITYLABEL, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getGeoId()
  {
    return getGeoId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getGeoId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.GeoEntityExcelView.GEOID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getGeoId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.GeoEntityExcelView.GEOID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getGeoType()
  {
    return getGeoType(null);

  }
 
  public com.runwaysdk.query.SelectableChar getGeoType(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.GeoEntityExcelView.GEOTYPE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getGeoType(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.GeoEntityExcelView.GEOTYPE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getGeometryWKT()
  {
    return getGeometryWKT(null);

  }
 
  public com.runwaysdk.query.SelectableChar getGeometryWKT(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.GeoEntityExcelView.GEOMETRYWKT, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getGeometryWKT(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.GeoEntityExcelView.GEOMETRYWKT, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.GeoEntityExcelView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.GeoEntityExcelView.ID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getParentName()
  {
    return getParentName(null);

  }
 
  public com.runwaysdk.query.SelectableChar getParentName(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.GeoEntityExcelView.PARENTNAME, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getParentName(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.GeoEntityExcelView.PARENTNAME, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getParentType()
  {
    return getParentType(null);

  }
 
  public com.runwaysdk.query.SelectableChar getParentType(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.GeoEntityExcelView.PARENTTYPE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getParentType(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.GeoEntityExcelView.PARENTTYPE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getSubType()
  {
    return getSubType(null);

  }
 
  public com.runwaysdk.query.SelectableChar getSubType(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.GeoEntityExcelView.SUBTYPE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getSubType(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.export.GeoEntityExcelView.SUBTYPE, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends GeoEntityExcelView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<GeoEntityExcelView>(this.getMdClassIF(), valueIterator);
  }

}
