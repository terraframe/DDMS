package dss.vector.solutions.kaleidoscope.report;

/**
 *
 * @author Autogenerated by RunwaySDK
 */
public class ReportParameterQuery extends dss.vector.solutions.kaleidoscope.report.ReportParameterQueryBase  implements com.runwaysdk.generation.loader.Reloadable
{

  public ReportParameterQuery(com.runwaysdk.query.QueryFactory queryFactory)
  {
    super(queryFactory);
    this.buildQuery(new DefaultReportParameterBuilder(queryFactory));
  }

  public ReportParameterQuery(com.runwaysdk.query.QueryFactory queryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(queryFactory, viewQueryBuilder);
  }

  class DefaultReportParameterBuilder extends com.runwaysdk.query.ViewQueryBuilder implements com.runwaysdk.generation.loader.Reloadable
  {
    public DefaultReportParameterBuilder(com.runwaysdk.query.QueryFactory queryFactory)
    {
      super(queryFactory);
    }

    protected ReportParameterQuery getViewQuery()
    {
      return (ReportParameterQuery)super.getViewQuery();
    }

    /**
     * build the select clause
     */
    protected void buildSelectClause()
    {
      String errMsg = "buildSelectClause() method in class DefaultReportParameterBuilder needs to be overwritten.";
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
