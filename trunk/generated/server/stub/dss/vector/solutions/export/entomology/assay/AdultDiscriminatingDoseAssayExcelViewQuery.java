package dss.vector.solutions.export.entomology.assay;

@com.terraframe.mojo.business.ClassSignature(hash = 1383312203)
/**
 *
 * @author Autogenerated by TerraFrame
 */
public class AdultDiscriminatingDoseAssayExcelViewQuery extends dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelViewQueryBase  implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1383312203;

  public AdultDiscriminatingDoseAssayExcelViewQuery(com.terraframe.mojo.query.QueryFactory queryFactory)
  {
    super(queryFactory);
    this.buildQuery(new DefaultAdultDiscriminatingDoseAssayExcelViewBuilder(queryFactory));
  }

  public AdultDiscriminatingDoseAssayExcelViewQuery(com.terraframe.mojo.query.QueryFactory queryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(queryFactory, viewQueryBuilder);
  }

  class DefaultAdultDiscriminatingDoseAssayExcelViewBuilder extends com.terraframe.mojo.query.ViewQueryBuilder implements com.terraframe.mojo.generation.loader.Reloadable
  {
    public DefaultAdultDiscriminatingDoseAssayExcelViewBuilder(com.terraframe.mojo.query.QueryFactory queryFactory)
    {
      super(queryFactory);
    }

    protected AdultDiscriminatingDoseAssayExcelViewQuery getViewQuery()
    {
      return (AdultDiscriminatingDoseAssayExcelViewQuery)super.getViewQuery();
    }

    /**
     * build the select clause
     */
    protected void buildSelectClause()
    {
      String errMsg = "buildSelectClause() method in class DefaultAdultDiscriminatingDoseAssayExcelViewBuilder needs to be overwritten.";
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
