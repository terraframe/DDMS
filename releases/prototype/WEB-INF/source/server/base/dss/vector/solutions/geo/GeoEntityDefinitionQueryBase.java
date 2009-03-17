package dss.vector.solutions.geo;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to GeoEntityDefinition.java
 *
 * @author Autogenerated by TerraFrame
 */
public  abstract  class GeoEntityDefinitionQueryBase extends com.terraframe.mojo.query.GeneratedViewQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1237314867309L;

  public GeoEntityDefinitionQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public GeoEntityDefinitionQueryBase(com.terraframe.mojo.query.QueryFactory componentQueryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return "dss.vector.solutions.geo.GeoEntityDefinition";
  }
  public com.terraframe.mojo.query.AttributeCharIF getDescription()
  {
    return getDescription(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getDescription(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getSelectable(dss.vector.solutions.geo.GeoEntityDefinition.DESCRIPTION, alias);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getDisplayLabel()
  {
    return getDisplayLabel(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getDisplayLabel(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getSelectable(dss.vector.solutions.geo.GeoEntityDefinition.DISPLAYLABEL, alias);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getId()
  {
    return getId(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getSelectable(dss.vector.solutions.geo.GeoEntityDefinition.ID, alias);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getParentGeoHierarchyId()
  {
    return getParentGeoHierarchyId(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getParentGeoHierarchyId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getSelectable(dss.vector.solutions.geo.GeoEntityDefinition.PARENTGEOHIERARCHYID, alias);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getParentTypeId()
  {
    return getParentTypeId(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getParentTypeId(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getSelectable(dss.vector.solutions.geo.GeoEntityDefinition.PARENTTYPEID, alias);

  }
 
  public com.terraframe.mojo.query.AttributeBooleanIF getPolitical()
  {
    return getPolitical(null);

  }
 
  public com.terraframe.mojo.query.AttributeBooleanIF getPolitical(String alias)
  {
    return (com.terraframe.mojo.query.AttributeBooleanIF)this.getSelectable(dss.vector.solutions.geo.GeoEntityDefinition.POLITICAL, alias);

  }
 
  public dss.vector.solutions.geo.SpatialMasterQuery.SpatialTypesQueryIF getSpatialType()
  {
    return getSpatialType(null);

  }
 
  public dss.vector.solutions.geo.SpatialMasterQuery.SpatialTypesQueryIF getSpatialType(String alias)
  {
    return (dss.vector.solutions.geo.SpatialMasterQuery.SpatialTypesQueryIF)this.getSelectable(dss.vector.solutions.geo.GeoEntityDefinition.SPATIALTYPE, alias);

  }
  public com.terraframe.mojo.query.AttributeCharIF getTypeName()
  {
    return getTypeName(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getTypeName(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getSelectable(dss.vector.solutions.geo.GeoEntityDefinition.TYPENAME, alias);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.terraframe.mojo.query.OIterator<? extends GeoEntityDefinition> getIterator()
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
    return new com.terraframe.mojo.query.ViewIterator<GeoEntityDefinition>(this.getMdClassIF(), valueIterator);
  }

}
