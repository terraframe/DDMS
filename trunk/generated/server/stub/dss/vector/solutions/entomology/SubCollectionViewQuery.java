package dss.vector.solutions.entomology;

@com.runwaysdk.business.ClassSignature(hash = -2145336492)
/**
 *
 * @author Autogenerated by TerraFrame
 */
public class SubCollectionViewQuery extends dss.vector.solutions.entomology.SubCollectionViewQueryBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -2145336492;

  public SubCollectionViewQuery(com.runwaysdk.query.QueryFactory queryFactory)
  {
    super(queryFactory);
    this.buildQuery(new DefaultSubCollectionViewBuilder(queryFactory));
  }

  public SubCollectionViewQuery(com.runwaysdk.query.QueryFactory queryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(queryFactory, viewQueryBuilder);
  }

  class DefaultSubCollectionViewBuilder extends com.runwaysdk.query.ViewQueryBuilder implements com.runwaysdk.generation.loader.Reloadable
  {
    SubCollectionQuery query;

    public DefaultSubCollectionViewBuilder(com.runwaysdk.query.QueryFactory queryFactory)
    {
      super(queryFactory);

      query = new SubCollectionQuery(queryFactory);
    }

    protected SubCollectionViewQuery getViewQuery()
    {
      return (SubCollectionViewQuery) super.getViewQuery();
    }

    /**
     * build the select clause
     */
    protected void buildSelectClause()
    {
      SubCollectionViewQuery vQuery = this.getViewQuery();

      vQuery.map(SubCollectionView.CONCRETEID, query.getId());
      vQuery.map(SubCollectionView.COLLECTION, query.getCollection());
      vQuery.map(SubCollectionView.EGGS, query.getEggs());
      vQuery.map(SubCollectionView.FEMALESFED, query.getFemalesFed());
      vQuery.map(SubCollectionView.FEMALESGRAVID, query.getFemalesGravid());
      vQuery.map(SubCollectionView.FEMALESHALFGRAVID, query.getFemalesHalfGravid());
      vQuery.map(SubCollectionView.FEMALESUNFED, query.getFemalesUnfed());
      vQuery.map(SubCollectionView.FEMALESUNKNOWN, query.getFemalesUnknown());
      vQuery.map(SubCollectionView.FEMALESTOTAL, query.getFemalesTotal());
      vQuery.map(SubCollectionView.IDENTMETHOD, query.getIdentMethod());
      vQuery.map(SubCollectionView.LARVAE, query.getLarvae());
      vQuery.map(SubCollectionView.MALE, query.getMale());
      vQuery.map(SubCollectionView.PUPAE, query.getPupae());
      vQuery.map(SubCollectionView.SUBCOLLECTIONID, query.getSubCollectionId());
      vQuery.map(SubCollectionView.TAXON, query.getTaxon());
      vQuery.map(SubCollectionView.TOTAL, query.getTotal());
      vQuery.map(SubCollectionView.UNKNOWNS, query.getUnknowns());
    }

    /**
     * Implement only if additional join criteria is required.
     */
    protected void buildWhereClause()
    {

    }

  }
}
