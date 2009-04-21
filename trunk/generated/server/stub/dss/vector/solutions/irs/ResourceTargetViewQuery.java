package dss.vector.solutions.irs;

/**
 *
 * @author Autogenerated by TerraFrame
 */
public class ResourceTargetViewQuery extends dss.vector.solutions.irs.ResourceTargetViewQueryBase  implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1240257027869L;

  public ResourceTargetViewQuery(com.terraframe.mojo.query.QueryFactory queryFactory)
  {
    super(queryFactory);
    this.buildQuery(new DefaultResourceTargetViewBuilder(queryFactory));
  }

  public ResourceTargetViewQuery(com.terraframe.mojo.query.QueryFactory queryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(queryFactory, viewQueryBuilder);
  }

  class DefaultResourceTargetViewBuilder extends com.terraframe.mojo.query.ViewQueryBuilder implements com.terraframe.mojo.generation.loader.Reloadable
  {
    public DefaultResourceTargetViewBuilder(com.terraframe.mojo.query.QueryFactory queryFactory)
    {
      super(queryFactory);
    }

    protected ResourceTargetViewQuery getViewQuery()
    {
      return (ResourceTargetViewQuery)super.getViewQuery();
    }

    /**
     * build the select clause
     */
    protected void buildSelectClause()
    {
      ResourceTargetViewQuery viewQuery = this.getViewQuery();

      String errMsg = "buildSelectClause() method in class DefaultResourceTargetViewBuilder needs to be overwritten.";
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
