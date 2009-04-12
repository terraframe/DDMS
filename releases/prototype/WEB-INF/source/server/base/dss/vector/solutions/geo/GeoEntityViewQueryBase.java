package dss.vector.solutions.geo;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to GeoEntityView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class GeoEntityViewQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1239517558542L;

  public GeoEntityViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public GeoEntityViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return "dss.vector.solutions.geo.GeoEntityView";
  }
  public com.terraframe.mojo.query.AttributeBooleanIF getActivated()
  {
    return getActivated(null);

  }
 
  public com.terraframe.mojo.query.AttributeBooleanIF getActivated(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBooleanIF)this.getSelectable(dss.vector.solutions.geo.GeoEntityView.ACTIVATED, alias);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getEntityName()
  {
    return getEntityName(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getEntityName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getSelectable(dss.vector.solutions.geo.GeoEntityView.ENTITYNAME, alias);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getEntityType()
  {
    return getEntityType(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getEntityType(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getSelectable(dss.vector.solutions.geo.GeoEntityView.ENTITYTYPE, alias);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getGeoEntityId()
  {
    return getGeoEntityId(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getGeoEntityId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getSelectable(dss.vector.solutions.geo.GeoEntityView.GEOENTITYID, alias);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getGeoId()
  {
    return getGeoId(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getGeoId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getSelectable(dss.vector.solutions.geo.GeoEntityView.GEOID, alias);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getSelectable(dss.vector.solutions.geo.GeoEntityView.ID, alias);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends GeoEntityView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<GeoEntityView>(this.getMdClassIF(), valueIterator);
  }

}
