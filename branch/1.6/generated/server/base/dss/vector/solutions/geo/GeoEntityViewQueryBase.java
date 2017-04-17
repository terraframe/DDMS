package dss.vector.solutions.geo;

@com.runwaysdk.business.ClassSignature(hash = 1634205837)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to GeoEntityView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class GeoEntityViewQueryBase extends com.runwaysdk.query.GeneratedViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public GeoEntityViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public GeoEntityViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.geo.GeoEntityView.CLASS;
  }
  public com.runwaysdk.query.SelectableBoolean getActivated()
  {
    return getActivated(null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getActivated(String alias)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.geo.GeoEntityView.ACTIVATED, alias, null);

  }
 
  public com.runwaysdk.query.SelectableBoolean getActivated(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableBoolean)this.getSelectable(dss.vector.solutions.geo.GeoEntityView.ACTIVATED, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getEntityLabel()
  {
    return getEntityLabel(null);

  }
 
  public com.runwaysdk.query.SelectableChar getEntityLabel(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.geo.GeoEntityView.ENTITYLABEL, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getEntityLabel(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.geo.GeoEntityView.ENTITYLABEL, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getEntityType()
  {
    return getEntityType(null);

  }
 
  public com.runwaysdk.query.SelectableChar getEntityType(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.geo.GeoEntityView.ENTITYTYPE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getEntityType(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.geo.GeoEntityView.ENTITYTYPE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getGeoEntityId()
  {
    return getGeoEntityId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getGeoEntityId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.geo.GeoEntityView.GEOENTITYID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getGeoEntityId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.geo.GeoEntityView.GEOENTITYID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getGeoId()
  {
    return getGeoId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getGeoId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.geo.GeoEntityView.GEOID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getGeoId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.geo.GeoEntityView.GEOID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getId()
  {
    return getId(null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.geo.GeoEntityView.ID, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getId(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.geo.GeoEntityView.ID, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getMoSubType()
  {
    return getMoSubType(null);

  }
 
  public com.runwaysdk.query.SelectableChar getMoSubType(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.geo.GeoEntityView.MOSUBTYPE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getMoSubType(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.geo.GeoEntityView.MOSUBTYPE, alias, displayLabel);

  }
 
  public com.runwaysdk.query.SelectableChar getTypeDisplayLabel()
  {
    return getTypeDisplayLabel(null);

  }
 
  public com.runwaysdk.query.SelectableChar getTypeDisplayLabel(String alias)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.geo.GeoEntityView.TYPEDISPLAYLABEL, alias, null);

  }
 
  public com.runwaysdk.query.SelectableChar getTypeDisplayLabel(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableChar)this.getSelectable(dss.vector.solutions.geo.GeoEntityView.TYPEDISPLAYLABEL, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends GeoEntityView> getIterator()
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
    return new com.runwaysdk.query.ViewIterator<GeoEntityView>(this.getMdClassIF(), valueIterator);
  }

}
