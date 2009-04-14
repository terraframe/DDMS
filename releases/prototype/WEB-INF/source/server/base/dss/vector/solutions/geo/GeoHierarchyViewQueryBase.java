package dss.vector.solutions.geo;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to GeoHierarchyView.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class GeoHierarchyViewQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1239670223245L;

  public GeoHierarchyViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public GeoHierarchyViewQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return "dss.vector.solutions.geo.GeoHierarchyView";
  }
  public com.terraframe.mojo.query.AttributeCharIF getDescription()
  {
    return getDescription(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getDescription(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getSelectable(dss.vector.solutions.geo.GeoHierarchyView.DESCRIPTION, alias);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getDisplayLabel()
  {
    return getDisplayLabel(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getDisplayLabel(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getSelectable(dss.vector.solutions.geo.GeoHierarchyView.DISPLAYLABEL, alias);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getGeoHierarchyId()
  {
    return getGeoHierarchyId(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getGeoHierarchyId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getSelectable(dss.vector.solutions.geo.GeoHierarchyView.GEOHIERARCHYID, alias);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getSelectable(dss.vector.solutions.geo.GeoHierarchyView.ID, alias);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getIsADisplayLabel()
  {
    return getIsADisplayLabel(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getIsADisplayLabel(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getSelectable(dss.vector.solutions.geo.GeoHierarchyView.ISADISPLAYLABEL, alias);

  }
 
  public com.terraframe.mojo.query.AttributeBooleanIF getPolitical()
  {
    return getPolitical(null);

  }
 
  public com.terraframe.mojo.query.AttributeBooleanIF getPolitical(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBooleanIF)this.getSelectable(dss.vector.solutions.geo.GeoHierarchyView.POLITICAL, alias);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getReferenceId()
  {
    return getReferenceId(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getReferenceId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getSelectable(dss.vector.solutions.geo.GeoHierarchyView.REFERENCEID, alias);

  }
 
  public com.terraframe.mojo.query.AttributeBooleanIF getSprayTargetAllowed()
  {
    return getSprayTargetAllowed(null);

  }
 
  public com.terraframe.mojo.query.AttributeBooleanIF getSprayTargetAllowed(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBooleanIF)this.getSelectable(dss.vector.solutions.geo.GeoHierarchyView.SPRAYTARGETALLOWED, alias);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getTypeName()
  {
    return getTypeName(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getTypeName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getSelectable(dss.vector.solutions.geo.GeoHierarchyView.TYPENAME, alias);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends GeoHierarchyView> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<GeoHierarchyView>(this.getMdClassIF(), valueIterator);
  }

}
