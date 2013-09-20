package dss.vector.solutions.export;

/**
 *
 * @author Autogenerated by RunwaySDK
 */
public class GeoTargetExcelViewQuery extends dss.vector.solutions.export.GeoTargetExcelViewQueryBase  implements com.runwaysdk.generation.loader.Reloadable
{

  public GeoTargetExcelViewQuery(com.runwaysdk.query.QueryFactory queryFactory)
  {
    super(queryFactory);
    this.buildQuery(new DefaultGeoTargetExcelViewBuilder(queryFactory));
  }

  public GeoTargetExcelViewQuery(com.runwaysdk.query.QueryFactory queryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(queryFactory, viewQueryBuilder);
  }

  class DefaultGeoTargetExcelViewBuilder extends com.runwaysdk.query.ViewQueryBuilder implements com.runwaysdk.generation.loader.Reloadable
  {
    public DefaultGeoTargetExcelViewBuilder(com.runwaysdk.query.QueryFactory queryFactory)
    {
      super(queryFactory);
    }

    protected GeoTargetExcelViewQuery getViewQuery()
    {
      return (GeoTargetExcelViewQuery)super.getViewQuery();
    }

    /**
     * build the select clause
     */
    protected void buildSelectClause()
    {
      String errMsg = "buildSelectClause() method in class DefaultGeoTargetExcelViewBuilder needs to be overwritten.";
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