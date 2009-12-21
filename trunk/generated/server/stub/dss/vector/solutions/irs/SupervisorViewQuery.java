package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = 628028057)
/**
 *
 * @author Autogenerated by TerraFrame
 */
public class SupervisorViewQuery extends dss.vector.solutions.irs.SupervisorViewQueryBase  implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 628028057;

  public SupervisorViewQuery(com.terraframe.mojo.query.QueryFactory queryFactory)
  {
    super(queryFactory);
    this.buildQuery(new DefaultSupervisorViewBuilder(queryFactory));
  }

  public SupervisorViewQuery(com.terraframe.mojo.query.QueryFactory queryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(queryFactory, viewQueryBuilder);
  }

  class DefaultSupervisorViewBuilder extends com.terraframe.mojo.query.ViewQueryBuilder implements com.terraframe.mojo.generation.loader.Reloadable
  {
    public DefaultSupervisorViewBuilder(com.terraframe.mojo.query.QueryFactory queryFactory)
    {
      super(queryFactory);
    }

    protected SupervisorViewQuery getViewQuery()
    {
      return (SupervisorViewQuery)super.getViewQuery();
    }

    /**
     * build the select clause
     */
    protected void buildSelectClause()
    {
      String errMsg = "buildSelectClause() method in class DefaultSupervisorViewBuilder needs to be overwritten.";
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
