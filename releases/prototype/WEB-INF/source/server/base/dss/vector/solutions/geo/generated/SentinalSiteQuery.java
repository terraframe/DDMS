package dss.vector.solutions.geo.generated;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to SentinalSite.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class SentinalSiteQuery extends dss.vector.solutions.geo.generated.AbstractSiteQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1239517538464L;

  public SentinalSiteQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
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
    return "dss.vector.solutions.geo.generated.SentinalSite";
  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends SentinalSite> getIterator()
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
    return new com.terraframe.mojo.business.BusinessIterator<SentinalSite>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface SentinalSiteQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, dss.vector.solutions.geo.generated.AbstractSiteQuery.AbstractSiteQueryReferenceIF
  {


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.geo.generated.SentinalSite sentinalSite);

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.geo.generated.SentinalSite sentinalSite);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class SentinalSiteQueryReference extends dss.vector.solutions.geo.generated.AbstractSiteQuery.AbstractSiteQueryReference
 implements SentinalSiteQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1239517538745L;

  public SentinalSiteQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(dss.vector.solutions.geo.generated.SentinalSite sentinalSite)
    {
      return this.EQ(sentinalSite.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(dss.vector.solutions.geo.generated.SentinalSite sentinalSite)
    {
      return this.NE(sentinalSite.getId());
    }

  }
}
