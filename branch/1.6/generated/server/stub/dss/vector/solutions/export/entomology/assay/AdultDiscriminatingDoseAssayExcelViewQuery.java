package dss.vector.solutions.export.entomology.assay;

@com.runwaysdk.business.ClassSignature(hash = 1383312203)
/**
 *
 * @author Autogenerated by TerraFrame
 */
public class AdultDiscriminatingDoseAssayExcelViewQuery extends dss.vector.solutions.export.entomology.assay.AdultDiscriminatingDoseAssayExcelViewQueryBase  implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = 1383312203;

  public AdultDiscriminatingDoseAssayExcelViewQuery(com.runwaysdk.query.QueryFactory queryFactory)
  {
    super(queryFactory);
    this.buildQuery(new DefaultAdultDiscriminatingDoseAssayExcelViewBuilder(queryFactory));
  }

  public AdultDiscriminatingDoseAssayExcelViewQuery(com.runwaysdk.query.QueryFactory queryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(queryFactory, viewQueryBuilder);
  }

  class DefaultAdultDiscriminatingDoseAssayExcelViewBuilder extends com.runwaysdk.query.ViewQueryBuilder implements com.runwaysdk.generation.loader.Reloadable
  {
    public DefaultAdultDiscriminatingDoseAssayExcelViewBuilder(com.runwaysdk.query.QueryFactory queryFactory)
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
      throw new com.runwaysdk.query.QueryException(errMsg);
    }

    /**
     * Implement only if additional join criteria is required.
     */
    protected void buildWhereClause()
    {

    }

  }
}
