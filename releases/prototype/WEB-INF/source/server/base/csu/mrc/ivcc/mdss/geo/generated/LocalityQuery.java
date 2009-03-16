package csu.mrc.ivcc.mdss.geo.generated;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to Locality.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class LocalityQuery extends csu.mrc.ivcc.mdss.geo.generated.GeoEntityQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1237219379630L;

  public LocalityQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
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
    return "csu.mrc.ivcc.mdss.geo.generated.Locality";
  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends Locality> getIterator()
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
    return new com.terraframe.mojo.business.BusinessIterator<Locality>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface LocalityQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, csu.mrc.ivcc.mdss.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF
  {


    public com.terraframe.mojo.query.BasicCondition EQ(csu.mrc.ivcc.mdss.geo.generated.Locality locality);

    public com.terraframe.mojo.query.BasicCondition NE(csu.mrc.ivcc.mdss.geo.generated.Locality locality);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class LocalityQueryReference extends csu.mrc.ivcc.mdss.geo.generated.GeoEntityQuery.GeoEntityQueryReference
 implements LocalityQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1237219379728L;

  public LocalityQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(csu.mrc.ivcc.mdss.geo.generated.Locality locality)
    {
      return this.EQ(locality.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(csu.mrc.ivcc.mdss.geo.generated.Locality locality)
    {
      return this.NE(locality.getId());
    }

  }
}
