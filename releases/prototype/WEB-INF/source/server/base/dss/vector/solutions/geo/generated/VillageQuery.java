package dss.vector.solutions.geo.generated;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to Village.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class VillageQuery extends dss.vector.solutions.geo.generated.PopulatedAreaQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1238826365606L;

  public VillageQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
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
    return "dss.vector.solutions.geo.generated.Village";
  }
  public com.terraframe.mojo.query.AttributeCharIF getMultiPoint()
  {
    return getMultiPoint(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getMultiPoint(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.getComponentQuery().attributeFactory(dss.vector.solutions.geo.generated.Village.MULTIPOINT, "com.terraframe.mojo.system.gis.metadata.MdAttributeMultiPoint", alias);

  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends Village> getIterator()
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
    return new com.terraframe.mojo.business.BusinessIterator<Village>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface VillageQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, dss.vector.solutions.geo.generated.PopulatedAreaQuery.PopulatedAreaQueryReferenceIF
  {

    public com.terraframe.mojo.query.AttributeCharIF getMultiPoint();
    public com.terraframe.mojo.query.AttributeCharIF getMultiPoint(String alias);

    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.geo.generated.Village village);

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.geo.generated.Village village);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class VillageQueryReference extends dss.vector.solutions.geo.generated.PopulatedAreaQuery.PopulatedAreaQueryReference
 implements VillageQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1238826365756L;

  public VillageQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.geo.generated.Village village)
    {
      return this.EQ(village.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.geo.generated.Village village)
    {
      return this.NE(village.getId());
    }

  public com.terraframe.mojo.query.AttributeCharIF getMultiPoint()
  {
    return getMultiPoint(null);

  }
 
  public com.terraframe.mojo.query.AttributeCharIF getMultiPoint(String alias)
  {
    return (com.terraframe.mojo.query.AttributeCharIF)this.attributeFactory("multiPoint", "com.terraframe.mojo.system.gis.metadata.MdAttributeMultiPoint", alias);

  }
  }
}
