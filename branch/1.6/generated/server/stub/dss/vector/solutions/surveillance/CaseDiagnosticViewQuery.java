package dss.vector.solutions.surveillance;



/**
 *
 * @author Autogenerated by RunwaySDK
 */
public class CaseDiagnosticViewQuery extends dss.vector.solutions.surveillance.CaseDiagnosticViewQueryBase  implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = -174643245;

  public CaseDiagnosticViewQuery(com.runwaysdk.query.QueryFactory queryFactory)
  {
    super(queryFactory);
    this.buildQuery(new DefaultCaseDiagnosticViewBuilder(queryFactory));
  }

  public CaseDiagnosticViewQuery(com.runwaysdk.query.QueryFactory queryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(queryFactory, viewQueryBuilder);
  }

  class DefaultCaseDiagnosticViewBuilder extends com.runwaysdk.query.ViewQueryBuilder implements com.runwaysdk.generation.loader.Reloadable
  {
    private CaseDiagnosticQuery query;

    public DefaultCaseDiagnosticViewBuilder(com.runwaysdk.query.QueryFactory queryFactory)
    {
      super(queryFactory);
      
      this.query = new CaseDiagnosticQuery(queryFactory);
    }

    protected CaseDiagnosticViewQuery getViewQuery()
    {
      return (CaseDiagnosticViewQuery)super.getViewQuery();
    }

    /**
     * build the select clause
     */
    protected void buildSelectClause()
    {
      CaseDiagnosticViewQuery vQuery = this.getViewQuery();
      
      vQuery.map(CaseDiagnosticView.CONCRETEID, query.getId());
      vQuery.map(CaseDiagnosticView.AGGREGATEDCASE, query.getAggregatedCase());
      vQuery.map(CaseDiagnosticView.TERM, query.getTerm());
      vQuery.map(CaseDiagnosticView.AMOUNT, query.getAmount());
      vQuery.map(CaseDiagnosticView.AMOUNTPOSITIVE, query.getAmountPositive());
    }

    /**
     * Implement only if additional join criteria is required.
     */
    protected void buildWhereClause()
    {

    }

  }
}
