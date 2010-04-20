package dss.vector.solutions.entomology;

/**
 *
 * @author Autogenerated by RunwaySDK
 */
public class TimeResponseAssayViewQuery extends dss.vector.solutions.entomology.TimeResponseAssayViewQueryBase  implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = -1961672020;

  public TimeResponseAssayViewQuery(com.runwaysdk.query.QueryFactory queryFactory)
  {
    super(queryFactory);
    this.buildQuery(new DefaultTimeResponseAssayViewBuilder(queryFactory));
  }

  public TimeResponseAssayViewQuery(com.runwaysdk.query.QueryFactory queryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(queryFactory, viewQueryBuilder);
  }

  class DefaultTimeResponseAssayViewBuilder extends com.runwaysdk.query.ViewQueryBuilder implements com.runwaysdk.generation.loader.Reloadable
  {
    private TimeResponseAssayQuery query;
    
    public DefaultTimeResponseAssayViewBuilder(com.runwaysdk.query.QueryFactory queryFactory)
    {
      super(queryFactory);
      
      this.query = new TimeResponseAssayQuery(queryFactory);
    }

    protected TimeResponseAssayViewQuery getViewQuery()
    {
      return (TimeResponseAssayViewQuery)super.getViewQuery();
    }

    /**
     * build the select clause
     */
    protected void buildSelectClause()
    {
      TimeResponseAssayViewQuery vQuery = this.getViewQuery();
      
      vQuery.map(TimeResponseAssayView.CONCRETEID, query.getId());
      vQuery.map(TimeResponseAssayView.COLLECTION, query.getCollection());      
      vQuery.map(TimeResponseAssayView.ASSAY, query.getAssay());
      vQuery.map(TimeResponseAssayView.ACTIVEINGREDIENT, query.getActiveIngredient());
      vQuery.map(TimeResponseAssayView.SPECIES, query.getSpecies());
      vQuery.map(TimeResponseAssayView.LIFESTAGE, query.getLifeStage());
      vQuery.map(TimeResponseAssayView.SYNERGIST, query.getSynergist());
      vQuery.map(TimeResponseAssayView.TESTSTRAINRESULT, query.getTestStrainResult());
      vQuery.map(TimeResponseAssayView.REFERENCESTRAINRESULT, query.getReferenceStrainResult());
    }

    /**
     * Implement only if additional join criteria is required.
     */
    protected void buildWhereClause()
    {

    }

  }
}
