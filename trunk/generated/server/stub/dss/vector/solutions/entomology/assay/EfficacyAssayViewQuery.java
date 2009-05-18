package dss.vector.solutions.entomology.assay;

/**
 *
 * @author Autogenerated by TerraFrame
 */
public class EfficacyAssayViewQuery extends dss.vector.solutions.entomology.assay.EfficacyAssayViewQueryBase  implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1242609508401L;

  public EfficacyAssayViewQuery(com.terraframe.mojo.query.QueryFactory queryFactory)
  {
    super(queryFactory);
    this.buildQuery(new DefaultEfficacyAssayViewBuilder(queryFactory));
  }

  public EfficacyAssayViewQuery(com.terraframe.mojo.query.QueryFactory queryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(queryFactory, viewQueryBuilder);
  }

  class DefaultEfficacyAssayViewBuilder extends com.terraframe.mojo.query.ViewQueryBuilder implements com.terraframe.mojo.generation.loader.Reloadable
  {
    public DefaultEfficacyAssayViewBuilder(com.terraframe.mojo.query.QueryFactory queryFactory)
    {
      super(queryFactory);
    }

    protected EfficacyAssayViewQuery getViewQuery()
    {
      return (EfficacyAssayViewQuery)super.getViewQuery();
    }

    /**
     * build the select clause
     */
    protected void buildSelectClause()
    {
      String errMsg = "buildSelectClause() method in class DefaultEfficacyAssayViewBuilder needs to be overwritten.";
      throw new com.terraframe.mojo.query.QueryException(errMsg);
    }

    /**
     * Implement only if additional join criteria is required.
     */
    protected void buildWhereClause()
    {

    }

  }
}
