package csu.mrc.ivcc.mdss.geo;

/**
 *
 * @author Autogenerated by TerraFrame
 */
public class GeoEntityDefinitionQuery extends csu.mrc.ivcc.mdss.geo.GeoEntityDefinitionQueryBase  implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1236133819598L;

  public GeoEntityDefinitionQuery(com.terraframe.mojo.query.QueryFactory queryFactory)
  {
    super(queryFactory);
    this.buildQuery(new DefaultGeoEntityDefinitionBuilder(queryFactory));
  }

  public GeoEntityDefinitionQuery(com.terraframe.mojo.query.QueryFactory queryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(queryFactory, viewQueryBuilder);
  }

  class DefaultGeoEntityDefinitionBuilder extends com.terraframe.mojo.query.ViewQueryBuilder implements com.terraframe.mojo.generation.loader.Reloadable
  {
    public DefaultGeoEntityDefinitionBuilder(com.terraframe.mojo.query.QueryFactory queryFactory)
    {
      super(queryFactory);
    }

    protected GeoEntityDefinitionQuery getViewQuery()
    {
      return (GeoEntityDefinitionQuery)super.getViewQuery();
    }

    /**
     * build the select clause
     */
    protected void buildSelectClause()
    {
      GeoEntityDefinitionQuery viewQuery = this.getViewQuery();

      String errMsg = "buildSelectClause() method in class DefaultGeoEntityDefinitionBuilder needs to be overwritten.";
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
