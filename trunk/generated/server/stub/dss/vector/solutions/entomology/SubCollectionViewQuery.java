package dss.vector.solutions.entomology;

@com.terraframe.mojo.business.ClassSignature(hash = -2145336492)
/**
 *
 * @author Autogenerated by TerraFrame
 */
public class SubCollectionViewQuery extends dss.vector.solutions.entomology.SubCollectionViewQueryBase  implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = -2145336492;

  public SubCollectionViewQuery(com.terraframe.mojo.query.QueryFactory queryFactory)
  {
    super(queryFactory);
    this.buildQuery(new DefaultSubCollectionViewBuilder(queryFactory));
  }

  public SubCollectionViewQuery(com.terraframe.mojo.query.QueryFactory queryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(queryFactory, viewQueryBuilder);
  }

  class DefaultSubCollectionViewBuilder extends com.terraframe.mojo.query.ViewQueryBuilder implements com.terraframe.mojo.generation.loader.Reloadable
  {
    SubCollectionQuery query;
    
    public DefaultSubCollectionViewBuilder(com.terraframe.mojo.query.QueryFactory queryFactory)
    {
      super(queryFactory);
      
      query = new SubCollectionQuery(queryFactory);
    }

    protected SubCollectionViewQuery getViewQuery()
    {
      return (SubCollectionViewQuery)super.getViewQuery();
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
      vQuery.map(SubCollectionView.FEMALE, query.getFemale());
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
