package dss.vector.solutions.geo;

import com.runwaysdk.system.metadata.MdBusinessQuery;

import dss.vector.solutions.geo.generated.Earth;

/**
 *
 * @author Autogenerated by TerraFrame
 */
public class GeoHierarchyViewQuery extends dss.vector.solutions.geo.GeoHierarchyViewQueryBase  implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = 1236279045055L;

  private GeoHierarchyQuery geoHierarchyQuery;

  private MdBusinessQuery mdBusinessQuery;

  private MdBusinessQuery parentMdBusinessQuery;

//  private String sortAttribute;

//  private Boolean ascending;

  private Integer pageSize;

  private Integer pageNumber;

  public GeoHierarchyViewQuery(com.runwaysdk.query.QueryFactory queryFactory, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    super(queryFactory);

//    this.sortAttribute = sortAttribute;
//    this.ascending = ascending;
    this.pageSize = pageSize;
    this.pageNumber = pageNumber;

    geoHierarchyQuery = new GeoHierarchyQuery(queryFactory);
    mdBusinessQuery = new MdBusinessQuery(queryFactory);
    parentMdBusinessQuery = new MdBusinessQuery(queryFactory);

    this.buildQuery(new DefaultGeoHierarchyViewBuilder(queryFactory));
  }

  public GeoHierarchyViewQuery(com.runwaysdk.query.QueryFactory queryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(queryFactory, viewQueryBuilder);
  }

  class DefaultGeoHierarchyViewBuilder extends com.runwaysdk.query.ViewQueryBuilder implements com.runwaysdk.generation.loader.Reloadable
  {
    public DefaultGeoHierarchyViewBuilder(com.runwaysdk.query.QueryFactory queryFactory)
    {
      super(queryFactory);
    }

    protected GeoHierarchyViewQuery getViewQuery()
    {
      return (GeoHierarchyViewQuery)super.getViewQuery();
    }

    /**
     * build the select clause
     */
    protected void buildSelectClause()
    {
      GeoHierarchyViewQuery vQuery = this.getViewQuery();

      vQuery.map(GeoHierarchyView.GEOHIERARCHYID, geoHierarchyQuery.getId());
      vQuery.map(GeoHierarchyView.POLITICAL, geoHierarchyQuery.getPolitical());
      vQuery.map(GeoHierarchy.SPRAYTARGETALLOWED, geoHierarchyQuery.getSprayTargetAllowed());

      vQuery.map(GeoHierarchyView.REFERENCEID, mdBusinessQuery.getId());
      vQuery.map(GeoHierarchyView.TYPENAME, mdBusinessQuery.getTypeName());

      vQuery.map(GeoHierarchyView.DISPLAYLABEL, mdBusinessQuery.getDisplayLabel().localize());
      vQuery.map(GeoHierarchyView.DESCRIPTION, mdBusinessQuery.getDescription().localize());
      vQuery.map(GeoHierarchyView.ISADISPLAYLABEL, parentMdBusinessQuery.getDisplayLabel().localize());   
      
    }

    /**
     * Implement only if additional join criteria is required.
     */
    protected void buildWhereClause()
    {
      GeoHierarchyViewQuery vQuery = this.getViewQuery();

      String earthName = Earth.CLASS.substring(Earth.CLASS.lastIndexOf(".")+1);

      vQuery.WHERE(mdBusinessQuery.getIsAbstract().EQ(false));
      vQuery.WHERE(mdBusinessQuery.getTypeName().NE(earthName));
      vQuery.WHERE(geoHierarchyQuery.getGeoEntityClass().EQ(mdBusinessQuery));
      vQuery.WHERE(mdBusinessQuery.getSuperMdBusiness().EQ(parentMdBusinessQuery));
     
      vQuery.ORDER_BY_ASC(mdBusinessQuery.getDisplayLabel().localize());
      
      if(pageSize != null && pageNumber != null)
      {
        vQuery.restrictRows(pageSize, pageNumber);
      }
    }

  }
}
