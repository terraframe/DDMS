package dss.vector.solutions.synchronization;

/**
 *
 * @author Autogenerated by TerraFrame
 */
public class TransactionItemFullViewQuery extends dss.vector.solutions.synchronization.TransactionItemFullViewQueryBase  implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -762047937;

  public TransactionItemFullViewQuery(com.terraframe.mojo.query.QueryFactory queryFactory)
  {
    super(queryFactory);
    this.buildQuery(new DefaultTransactionItemFullViewBuilder(queryFactory));
  }

  public TransactionItemFullViewQuery(com.terraframe.mojo.query.QueryFactory queryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(queryFactory, viewQueryBuilder);
  }

  class DefaultTransactionItemFullViewBuilder extends com.terraframe.mojo.query.ViewQueryBuilder implements com.terraframe.mojo.generation.loader.Reloadable
  {
    public DefaultTransactionItemFullViewBuilder(com.terraframe.mojo.query.QueryFactory queryFactory)
    {
      super(queryFactory);
    }

    protected TransactionItemFullViewQuery getViewQuery()
    {
      return (TransactionItemFullViewQuery)super.getViewQuery();
    }

    /**
     * build the select clause
     */
    protected void buildSelectClause()
    {
      String errMsg = "buildSelectClause() method in class DefaultTransactionItemFullViewBuilder needs to be overwritten.";
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
