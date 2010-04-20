package dss.vector.solutions.entomology;

/**
 *
 * @author Autogenerated by RunwaySDK
 */
public class DiagnosticAssayViewQuery extends dss.vector.solutions.entomology.DiagnosticAssayViewQueryBase  implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = -334682307;

  public DiagnosticAssayViewQuery(com.runwaysdk.query.QueryFactory queryFactory)
  {
    super(queryFactory);
    this.buildQuery(new DefaultDiagnosticAssayViewBuilder(queryFactory));
  }

  public DiagnosticAssayViewQuery(com.runwaysdk.query.QueryFactory queryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(queryFactory, viewQueryBuilder);
  }

  class DefaultDiagnosticAssayViewBuilder extends com.runwaysdk.query.ViewQueryBuilder implements com.runwaysdk.generation.loader.Reloadable
  {
    private DiagnosticAssayQuery query;
    
    public DefaultDiagnosticAssayViewBuilder(com.runwaysdk.query.QueryFactory queryFactory)
    {
      super(queryFactory);
      
      this.query = new DiagnosticAssayQuery(queryFactory);
    }

    protected DiagnosticAssayViewQuery getViewQuery()
    {
      return (DiagnosticAssayViewQuery)super.getViewQuery();
    }

    /**
     * build the select clause
     */
    protected void buildSelectClause()
    {
      DiagnosticAssayViewQuery vQuery = this.getViewQuery();
      
      vQuery.map(DiagnosticAssayView.CONCRETEID, query.getId());
      vQuery.map(DiagnosticAssayView.COLLECTION, query.getCollection());      
      vQuery.map(DiagnosticAssayView.ACTIVEINGREDIENT, query.getActiveIngredient());
      vQuery.map(DiagnosticAssayView.SPECIES, query.getSpecies());
      vQuery.map(DiagnosticAssayView.LIFESTAGE, query.getLifeStage());
      vQuery.map(DiagnosticAssayView.SYNERGIST, query.getSynergist());
      vQuery.map(DiagnosticAssayView.OUTCOME, query.getOutcome());
    }

    /**
     * Implement only if additional join criteria is required.
     */
    protected void buildWhereClause()
    {

    }

  }
}
