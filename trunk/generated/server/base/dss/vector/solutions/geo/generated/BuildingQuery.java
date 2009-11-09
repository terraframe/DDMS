package dss.vector.solutions.geo.generated;

@com.terraframe.mojo.business.ClassSignature(hash = -690338000)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to Building.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class BuildingQuery extends dss.vector.solutions.geo.generated.GeoEntityQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -690338000;

  public BuildingQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
    if (this.getComponentQuery() == null)
    {
      com.terraframe.mojo.business.BusinessQuery businessQuery = componentQueryFactory.businessQuery(this.getClassType());

       this.setBusinessQuery(businessQuery);
    }
  }

  public String getClassType()
  {
    return dss.vector.solutions.geo.generated.Building.CLASS;
  }
  public com.terraframe.mojo.query.SelectableChar getMultiPolygon()
  {
    return getMultiPolygon(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getMultiPolygon(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getComponentQuery().attributeFactory(dss.vector.solutions.geo.generated.Building.MULTIPOLYGON, "com.terraframe.mojo.system.gis.metadata.MdAttributeMultiPolygon", alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getMultiPolygon(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.getComponentQuery().attributeFactory(dss.vector.solutions.geo.generated.Building.MULTIPOLYGON, "com.terraframe.mojo.system.gis.metadata.MdAttributeMultiPolygon", alias, displayLabel);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends Building> getIterator()
  {
    String sqlStmt;
    if (_limit != null && _skip != null)
    {
      sqlStmt = this.getComponentQuery().getSQL(_limit, _skip);
    }
    else
    {
      sqlStmt = this.getComponentQuery().getSQL();
    }
    java.util.Map<String, com.terraframe.mojo.query.ColumnInfo> columnInfoMap = this.getComponentQuery().getColumnInfoMap();

    java.sql.ResultSet results = com.terraframe.mojo.dataaccess.database.Database.query(sqlStmt);
    return new com.terraframe.mojo.business.BusinessIterator<Building>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface BuildingQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF
  {

    public com.terraframe.mojo.query.SelectableChar getMultiPolygon();
    public com.terraframe.mojo.query.SelectableChar getMultiPolygon(String alias);
    public com.terraframe.mojo.query.SelectableChar getMultiPolygon(String alias, String displayLabel);

    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.geo.generated.Building building);

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.geo.generated.Building building);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class BuildingQueryReference extends dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReference
 implements BuildingQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1052079186;

  public BuildingQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias, String displayLabel)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias, displayLabel);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.geo.generated.Building building)
    {
      return this.EQ(building.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.geo.generated.Building building)
    {
      return this.NE(building.getId());
    }

  public com.terraframe.mojo.query.SelectableChar getMultiPolygon()
  {
    return getMultiPolygon(null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getMultiPolygon(String alias)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.attributeFactory("multiPolygon", "com.terraframe.mojo.system.gis.metadata.MdAttributeMultiPolygon", alias, null);

  }
 
  public com.terraframe.mojo.query.SelectableChar getMultiPolygon(String alias, String displayLabel)
  {
    return (com.terraframe.mojo.query.SelectableChar)this.attributeFactory("multiPolygon", "com.terraframe.mojo.system.gis.metadata.MdAttributeMultiPolygon", alias, displayLabel);

  }
  }
}
