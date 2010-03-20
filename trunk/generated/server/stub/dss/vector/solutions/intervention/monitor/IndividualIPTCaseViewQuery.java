package dss.vector.solutions.intervention.monitor;


/**
 *
 * @author Autogenerated by TerraFrame
 */
public class IndividualIPTCaseViewQuery extends dss.vector.solutions.intervention.monitor.IndividualIPTCaseViewQueryBase  implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = 1254014386645L;

  public IndividualIPTCaseViewQuery(com.runwaysdk.query.QueryFactory queryFactory)
  {
    super(queryFactory);
    this.buildQuery(new DefaultIndividualIPTCaseViewBuilder(queryFactory));
  }

  public IndividualIPTCaseViewQuery(com.runwaysdk.query.QueryFactory queryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(queryFactory, viewQueryBuilder);
  }

  class DefaultIndividualIPTCaseViewBuilder extends com.runwaysdk.query.ViewQueryBuilder implements com.runwaysdk.generation.loader.Reloadable
  {
    private IndividualIPTCaseQuery query;

    public DefaultIndividualIPTCaseViewBuilder(com.runwaysdk.query.QueryFactory queryFactory)
    {
      super(queryFactory);
      
      query = new IndividualIPTCaseQuery(queryFactory);
    }

    protected IndividualIPTCaseViewQuery getViewQuery()
    {
      return (IndividualIPTCaseViewQuery)super.getViewQuery();
    }

    /**
     * build the select clause
     */
    protected void buildSelectClause()
    {
      IndividualIPTCaseViewQuery vQuery = this.getViewQuery();

      vQuery.map(IndividualIPTCaseView.CONCRETEID, query.getId());
      vQuery.map(IndividualIPTCaseView.PATIENT, query.getPatient().getPerson());
    }

    /**
     * Implement only if additional join criteria is required.
     */
    protected void buildWhereClause()
    {
    }

  }
}
