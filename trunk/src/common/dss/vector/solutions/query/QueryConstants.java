package dss.vector.solutions.query;

import com.terraframe.mojo.generation.loader.Reloadable;

public class QueryConstants implements Reloadable
{
  /**
   * The namespace as created in GeoServer for all MDSS generated views.
   */
  public static final String MDSS_NAMESPACE = "mdss";
  
  public static final String CATEGORY_FACTORY_TYPE = "cfType";
  
  public static final String CATEGORY_FACTORY_DISPLAY = "cfDisplay";

  public static final String FEATURE_NAMESPACE = "MDSS_maps";

  public static final String THEMATIC_DATA_COLUMN = "data";

  public static final String ENTITY_NAME_COLUMN = "entityname_v";
  
  public static final String GEO_ID_COLUMN = "geoid_v";

  public static final String GEOMETRY_NAME_COLUMN = "geometry_v";

  public static final String VIEW_NAME_SUFFIX = "_view";
  
  public static final String SLD_WEB_DIR = "styles/";
  
  public static final String SLD_PREFIX = "style_";
  
  public static final String SLD_EXTENSION = "sld";
  
  public static final String MAP_IMAGES_DIR = "imgs/maps/";
  
  // Query types
  public static final String SELECTED_UNIVERSALS = "selectedUniversals";
  
  public static final String DOB_CRITERIA = "dobCriteria";

  private static final String NAMESPACE_DELIM = ":";

  public enum QueryType implements Reloadable {
    
    QUERY_AGGREGATED_CASE,
    
    QUERY_ENTOMOLOGY,
    
    QUERY_INDICATOR_SURVEY,
    
    QUERY_STOCK,
    
    QUERY_LARVACIDE,
    
    QUERY_IRS,

    QUERY_RESISTANCE,
    
    QUERY_AGGREGATED_IPT,
    
    QUERY_INDIVIDUAL_IPT,
    
    QUERY_INDIVIDUAL_CASES,
    
    QUERY_EFFICACY_ASSAY,
    
    QUERY_MOSQUITO_COLLECTIONS,
    
    QUERY_ITN_COMMUNITY_DISTRIBUTION,
    
    QUERY_ITN_FACILITY_DISTRIBUTION,
    
    QUERY_AGGREGATED_ITN,
    
    QUERY_UNIVERSAL
    
  }
  
  /**
   * Namespaces the query with the qualified class and query type.
   */
  public static String namespaceQuery(String queryClass, QueryConstants.QueryType queryType)
  {
    return queryClass + NAMESPACE_DELIM + queryType.name();
  }
  
  /**
   * Extracts the query class from the string generated with namespaceQuery().
   * 
   * @param namespacedType
   * @return
   */
  public static String getQueryClass(String namespacedType)
  {
    return namespacedType.split(NAMESPACE_DELIM)[0];
  }
  
  public static QueryType getQueryType(String namespacedType)
  {
    String name = namespacedType.split(NAMESPACE_DELIM)[1];
    return QueryType.valueOf(name);
  }
  
  public static String createSLDName(String layerId)
  {
    return SLD_PREFIX+layerId.substring(0, 32);
  }
}
