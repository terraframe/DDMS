package dss.vector.solutions.query;

import com.terraframe.mojo.generation.loader.Reloadable;

public class QueryConstants implements Reloadable
{
  /**
   * The namespace as created in GeoServer for all MDSS generated views.
   */
  public static final String MDSS_NAMESPACE = "mdss";

  public static final String FEATURE_NAMESPACE = "MDSS_maps";

  public static final String THEMATIC_DATA_COLUMN = "data";

  public static final String ENTITY_NAME_COLUMN = "entityname_v";

  public static final String GEOMETRY_NAME_COLUMN = "geometry_v";

  public static final String VIEW_NAME_SUFFIX = "_view";

  // Query types
  public static final String QUERY_AGGREGATED_CASE = "QueryAggregatedCase";

  public static final String QUERY_ENTOMOLOGY = "QueryEntomology";

  public static final String QUERY_INDICATOR_SURVEY ="QueryIndicatorSurvey";

  public static final String QUERY_IRS ="QueryIRS";

  public static final String QUERY_RESISTANCE = "QueryResistance";

  public static final String QUERY_AGGREGATED_IPT = "QueryAggregatedIPT";
}
