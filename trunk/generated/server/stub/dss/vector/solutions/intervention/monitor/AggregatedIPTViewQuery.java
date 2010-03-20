package dss.vector.solutions.intervention.monitor;

/**
 *
 * @author Autogenerated by TerraFrame
 */
public class AggregatedIPTViewQuery extends dss.vector.solutions.intervention.monitor.AggregatedIPTViewQueryBase  implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = 1244737040705L;

  public AggregatedIPTViewQuery(com.runwaysdk.query.QueryFactory queryFactory)
  {
    super(queryFactory);
    this.buildQuery(new DefaultAggregatedIPTViewBuilder(queryFactory));
  }

  public AggregatedIPTViewQuery(com.runwaysdk.query.QueryFactory queryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(queryFactory, viewQueryBuilder);
  }

  class DefaultAggregatedIPTViewBuilder extends com.runwaysdk.query.ViewQueryBuilder implements com.runwaysdk.generation.loader.Reloadable
  {
    public DefaultAggregatedIPTViewBuilder(com.runwaysdk.query.QueryFactory queryFactory)
    {
      super(queryFactory);
    }

    protected AggregatedIPTViewQuery getViewQuery()
    {
      return (AggregatedIPTViewQuery)super.getViewQuery();
    }

    /**
     * build the select clause
     */
    protected void buildSelectClause()
    {
      String errMsg = "buildSelectClause() method in class DefaultAggregatedIPTViewBuilder needs to be overwritten.";
      throw new com.runwaysdk.query.QueryException(errMsg);
    }

    /**
     * Implement only if additional join criteria is required.
     */
    protected void buildWhereClause()
    {

    }

  }
}
