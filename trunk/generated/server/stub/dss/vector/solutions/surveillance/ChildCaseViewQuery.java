package dss.vector.solutions.surveillance;

/**
 *
 * @author Autogenerated by TerraFrame
 */
public class ChildCaseViewQuery extends dss.vector.solutions.surveillance.ChildCaseViewQueryBase  implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1239151378555L;

  public ChildCaseViewQuery(com.terraframe.mojo.query.QueryFactory queryFactory)
  {
    super(queryFactory);
    this.buildQuery(new DefaultChildCaseViewBuilder(queryFactory));
  }

  public ChildCaseViewQuery(com.terraframe.mojo.query.QueryFactory queryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(queryFactory, viewQueryBuilder);
  }

  class DefaultChildCaseViewBuilder extends com.terraframe.mojo.query.ViewQueryBuilder implements com.terraframe.mojo.generation.loader.Reloadable
  {
    public DefaultChildCaseViewBuilder(com.terraframe.mojo.query.QueryFactory queryFactory)
    {
      super(queryFactory);
    }

    protected ChildCaseViewQuery getViewQuery()
    {
      return (ChildCaseViewQuery)super.getViewQuery();
    }

    /**
     * build the select clause
     */
    protected void buildSelectClause()
    {
      String errMsg = "buildSelectClause() method in class DefaultChildCaseViewBuilder needs to be overwritten.";
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
