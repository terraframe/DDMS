package dss.vector.solutions.geo.generated;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to District.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class DistrictQuery extends dss.vector.solutions.geo.generated.GeoEntityQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1237423103157L;

  public DistrictQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
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
    return "dss.vector.solutions.geo.generated.District";
  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends District> getIterator()
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
    return new com.terraframe.mojo.business.BusinessIterator<District>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface DistrictQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF
  {


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.geo.generated.District district);

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.geo.generated.District district);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class DistrictQueryReference extends dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReference
 implements DistrictQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1237423103250L;

  public DistrictQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.geo.generated.District district)
    {
      return this.EQ(district.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.geo.generated.District district)
    {
      return this.NE(district.getId());
    }

  }
}
