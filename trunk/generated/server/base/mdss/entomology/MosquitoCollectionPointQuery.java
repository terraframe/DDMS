package mdss.entomology;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MosquitoCollectionPoint.java
 *
 * @author Autogenerated by TerraFrame
 */
public  class MosquitoCollectionPointQuery extends mdss.entomology.AbstractMosquitoCollectionQuery
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1234203360520L;

  public MosquitoCollectionPointQuery(com.terraframe.mojo.query.QueryFactory componentQueryFactory)
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
    return "mdss.entomology.MosquitoCollectionPoint";
  }
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  public com.terraframe.mojo.query.OIterator<? extends MosquitoCollectionPoint> getIterator()
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

    java.sql.ResultSet results = com.terraframe.mojo.dataaccess.database.Database.selectEntityAttributes(sqlStmt);
    return new com.terraframe.mojo.business.BusinessIterator<MosquitoCollectionPoint>(this.getComponentQuery().getMdEntityIF(), columnInfoMap, results);
  }


/**
 * Interface that masks all type unsafe query methods and defines all type safe methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public interface MosquitoCollectionPointQueryReferenceIF extends com.terraframe.mojo.generation.loader.Reloadable, mdss.entomology.AbstractMosquitoCollectionQuery.AbstractMosquitoCollectionQueryReferenceIF
  {


    public com.terraframe.mojo.query.BasicCondition EQ(mdss.entomology.MosquitoCollectionPoint mosquitoCollectionPoint);

    public com.terraframe.mojo.query.BasicCondition NE(mdss.entomology.MosquitoCollectionPoint mosquitoCollectionPoint);

  }

/**
 * Implements type safe query methods.
 * This type is used when a join is performed on this class as a reference.
 **/
  public static class MosquitoCollectionPointQueryReference extends mdss.entomology.AbstractMosquitoCollectionQuery.AbstractMosquitoCollectionQueryReference
 implements MosquitoCollectionPointQueryReferenceIF
, com.terraframe.mojo.generation.loader.Reloadable
  {
private static final long serialVersionUID = 1234203360644L;

  public MosquitoCollectionPointQueryReference(com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF mdAttributeIF, String attributeNamespace, String definingTableName, String definingTableAlias, com.terraframe.mojo.dataaccess.MdBusinessDAOIF referenceMdBusinessIF, String referenceTableAlias, com.terraframe.mojo.query.ComponentQuery rootQuery, java.util.Set<com.terraframe.mojo.query.Join> tableJoinSet, String alias)
  {
    super(mdAttributeIF, attributeNamespace, definingTableName, definingTableAlias, referenceMdBusinessIF, referenceTableAlias, rootQuery, tableJoinSet, alias);

  }


    public com.terraframe.mojo.query.BasicCondition EQ(mdss.entomology.MosquitoCollectionPoint mosquitoCollectionPoint)
    {
      return this.EQ(mosquitoCollectionPoint.getId());
    }

    public com.terraframe.mojo.query.BasicCondition NE(mdss.entomology.MosquitoCollectionPoint mosquitoCollectionPoint)
    {
      return this.NE(mosquitoCollectionPoint.getId());
    }

  }
}
