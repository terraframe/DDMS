package dss.vector.solutions.export;

/**
 *
 * @author Autogenerated by TerraFrame
 */
public class KnockDownAssayExcelViewQuery extends dss.vector.solutions.export.KnockDownAssayExcelViewQueryBase  implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = 1245998013329L;

  public KnockDownAssayExcelViewQuery(com.runwaysdk.query.QueryFactory queryFactory)
  {
    super(queryFactory);
    this.buildQuery(new DefaultKnockDownAssayExcelViewBuilder(queryFactory));
  }

  public KnockDownAssayExcelViewQuery(com.runwaysdk.query.QueryFactory queryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(queryFactory, viewQueryBuilder);
  }

  class DefaultKnockDownAssayExcelViewBuilder extends com.runwaysdk.query.ViewQueryBuilder implements com.runwaysdk.generation.loader.Reloadable
  {
    public DefaultKnockDownAssayExcelViewBuilder(com.runwaysdk.query.QueryFactory queryFactory)
    {
      super(queryFactory);
    }

    protected KnockDownAssayExcelViewQuery getViewQuery()
    {
      return (KnockDownAssayExcelViewQuery)super.getViewQuery();
    }

    /**
     * build the select clause
     */
    protected void buildSelectClause()
    {
      String errMsg = "buildSelectClause() method in class DefaultKnockDownAssayExcelViewBuilder needs to be overwritten.";
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
