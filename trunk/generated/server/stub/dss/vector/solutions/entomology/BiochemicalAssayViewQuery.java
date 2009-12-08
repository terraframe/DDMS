package dss.vector.solutions.entomology;

@com.terraframe.mojo.business.ClassSignature(hash = -441276206)
/**
 *
 * @author Autogenerated by TerraFrame
 */
public class BiochemicalAssayViewQuery extends dss.vector.solutions.entomology.BiochemicalAssayViewQueryBase  implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -441276206;

  public BiochemicalAssayViewQuery(com.terraframe.mojo.query.QueryFactory queryFactory)
  {
    super(queryFactory);
    this.buildQuery(new DefaultBiochemicalAssayViewBuilder(queryFactory));
  }

  public BiochemicalAssayViewQuery(com.terraframe.mojo.query.QueryFactory queryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(queryFactory, viewQueryBuilder);
  }

  class DefaultBiochemicalAssayViewBuilder extends com.terraframe.mojo.query.ViewQueryBuilder implements com.terraframe.mojo.generation.loader.Reloadable
  {
    private BiochemicalAssayQuery query;
    
    public DefaultBiochemicalAssayViewBuilder(com.terraframe.mojo.query.QueryFactory queryFactory)
    {
      super(queryFactory);
      
      query = new BiochemicalAssayQuery(queryFactory);
    }

    protected BiochemicalAssayViewQuery getViewQuery()
    {
      return (BiochemicalAssayViewQuery)super.getViewQuery();
    }

    /**
     * build the select clause
     */
    protected void buildSelectClause()
    {
      BiochemicalAssayViewQuery vQuery = this.getViewQuery();
      
      vQuery.map(BiochemicalAssayView.CONCRETEID, query.getId());
      vQuery.map(BiochemicalAssayView.COLLECTION, query.getCollection());
      vQuery.map(BiochemicalAssayView.IDENTMETHOD, query.getIdentMethod());
      vQuery.map(BiochemicalAssayView.ISOFEMALE, query.getIsofemale());
      vQuery.map(BiochemicalAssayView.MOSQUITOID, query.getMosquitoId());
      vQuery.map(BiochemicalAssayView.NUMBERELEVATED, query.getNumberElevated());
      vQuery.map(BiochemicalAssayView.NUMBERTESTED, query.getNumberTested());
      vQuery.map(BiochemicalAssayView.GENERATION, query.getGeneration());
      vQuery.map(BiochemicalAssayView.SEX, query.getSex());
      vQuery.map(BiochemicalAssayView.SPECIES, query.getSpecies());
      vQuery.map(BiochemicalAssayView.ASSAY, query.getAssay());
    }
    /**
     * Implement only if additional join criteria is required.
     */
    protected void buildWhereClause()
    {

    }

  }
}
