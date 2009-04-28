package dss.vector.solutions.irs;

/**
 *
 * @author Autogenerated by TerraFrame
 */
public class OperatorSprayViewQuery extends dss.vector.solutions.irs.OperatorSprayViewQueryBase  implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1240853366089L;

  public OperatorSprayViewQuery(com.terraframe.mojo.query.QueryFactory queryFactory)
  {
    super(queryFactory);
    this.buildQuery(new DefaultOperatorSprayViewBuilder(queryFactory));
  }

  public OperatorSprayViewQuery(com.terraframe.mojo.query.QueryFactory queryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(queryFactory, viewQueryBuilder);
  }

  class DefaultOperatorSprayViewBuilder extends com.terraframe.mojo.query.ViewQueryBuilder implements com.terraframe.mojo.generation.loader.Reloadable
  {
    public DefaultOperatorSprayViewBuilder(com.terraframe.mojo.query.QueryFactory queryFactory)
    {
      super(queryFactory);
    }

    protected OperatorSprayViewQuery getViewQuery()
    {
      return (OperatorSprayViewQuery)super.getViewQuery();
    }

    /**
     * build the select clause
     */
    protected void buildSelectClause()
    {
      OperatorSprayViewQuery viewQuery = this.getViewQuery();

      String errMsg = "buildSelectClause() method in class DefaultOperatorSprayViewBuilder needs to be overwritten.";
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
