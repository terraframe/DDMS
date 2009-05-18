package dss.vector.solutions.entomology;

/**
 *
 * @author Autogenerated by TerraFrame
 */
public class MosquitoCollectionPointViewQuery extends dss.vector.solutions.entomology.MosquitoCollectionPointViewQueryBase  implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1242677987169L;

  public MosquitoCollectionPointViewQuery(com.terraframe.mojo.query.QueryFactory queryFactory)
  {
    super(queryFactory);
    this.buildQuery(new DefaultMosquitoCollectionPointViewBuilder(queryFactory));
  }

  public MosquitoCollectionPointViewQuery(com.terraframe.mojo.query.QueryFactory queryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(queryFactory, viewQueryBuilder);
  }

  class DefaultMosquitoCollectionPointViewBuilder extends com.terraframe.mojo.query.ViewQueryBuilder implements com.terraframe.mojo.generation.loader.Reloadable
  {
    public DefaultMosquitoCollectionPointViewBuilder(com.terraframe.mojo.query.QueryFactory queryFactory)
    {
      super(queryFactory);
    }

    protected MosquitoCollectionPointViewQuery getViewQuery()
    {
      return (MosquitoCollectionPointViewQuery)super.getViewQuery();
    }

    /**
     * build the select clause
     */
    protected void buildSelectClause()
    {
      String errMsg = "buildSelectClause() method in class DefaultMosquitoCollectionPointViewBuilder needs to be overwritten.";
      throw new com.terraframe.mojo.query.QueryException(errMsg);
    }

    /**
     * Implement only if additional join criteria is required.
     */
    protected void buildWhereClause()
    {

    }

  }
}
