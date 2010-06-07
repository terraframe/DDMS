package dss.vector.solutions.surveillance;

/**
 *
 * @author Autogenerated by RunwaySDK
 */
public class CaseTreatmentStockViewQuery extends dss.vector.solutions.surveillance.CaseTreatmentStockViewQueryBase  implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = 1769390806;

  public CaseTreatmentStockViewQuery(com.runwaysdk.query.QueryFactory queryFactory)
  {
    super(queryFactory);
    this.buildQuery(new DefaultCaseTreatmentStockViewBuilder(queryFactory));
  }

  public CaseTreatmentStockViewQuery(com.runwaysdk.query.QueryFactory queryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(queryFactory, viewQueryBuilder);
  }

  class DefaultCaseTreatmentStockViewBuilder extends com.runwaysdk.query.ViewQueryBuilder implements com.runwaysdk.generation.loader.Reloadable
  {
    private CaseTreatmentStockQuery query;

    public DefaultCaseTreatmentStockViewBuilder(com.runwaysdk.query.QueryFactory queryFactory)
    {
      super(queryFactory);
      
      this.query = new CaseTreatmentStockQuery(queryFactory);
    }

    protected CaseTreatmentStockViewQuery getViewQuery()
    {
      return (CaseTreatmentStockViewQuery)super.getViewQuery();
    }

    /**
     * build the select clause
     */
    protected void buildSelectClause()
    {
      CaseTreatmentStockViewQuery vQuery = this.getViewQuery();
      
      vQuery.map(CaseTreatmentStockView.CONCRETEID, query.getId());
      vQuery.map(CaseTreatmentStockView.AGGREGATEDCASE, query.getAggregatedCase());
      vQuery.map(CaseTreatmentStockView.TERM, query.getTerm());
      vQuery.map(CaseTreatmentStockView.OUTOFSTOCK, query.getOutOfStock());
    }

    /**
     * Implement only if additional join criteria is required.
     */
    protected void buildWhereClause()
    {

    }
  }
}
