package dss.vector.solutions.query;

import com.runwaysdk.constants.CommonProperties;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.MDSSInfo;

public class QueryConstants implements Reloadable
{
  public static final String  CATEGORY_OVERRIDE_PREPEND               = "enable_";

  /**
   * The namespace as created in GeoServer for all MDSS generated views.
   */
  // public static final String MDSS_NAMESPACE = "mdss";

  public static final String  CATEGORY_FACTORY_TYPE                   = "cfType";

  public static final String  CATEGORY_FACTORY_DISPLAY                = "cfDisplay";

  private static final String FEATURE_NAMESPACE                       = "MDSS_maps";

  // public static final String THEMATIC_DATA_COLUMN = "data";

  public static final String  ENTITY_NAME_COLUMN                      = "entityname_v";

  public static final String  GEO_ID_COLUMN                           = "geoid_v";

  public static final String  GEOMETRY_NAME_COLUMN                    = "geometry_v";

  public static final String  VIEW_NAME_SUFFIX                        = "_view";

  public static final String  SLD_WEB_DIR                             = "styles/";

  public static final String  SLD_PREFIX                              = "style_";

  public static final String  SLD_EXTENSION                           = "sld";

  public static final String  MAP_IMAGES_DIR                          = "imgs/maps/";

  // Query types
  public static final String  SELECTED_UNIVERSALS                     = "selectedUniversals";

  public static final String  DOB_CRITERIA                            = "dobCriteria";

  public static final String  NAMESPACE_DELIM                         = ":";

  /*
   * Constant used to query IndividualPremiseVisit.REASONSFORNOTTREATED in the
   * Intervention Control query builder.
   */
  public static final String  REASONS_FOR_NOT_TREATED_PREFIX          = "r__";

  public static final String  RDT_PREVALENCE                          = "rdt_prevalence";

  public static final String  BLOODSLIDE_PREVALENCE                   = "bloodslide_prevalence";

  public static final String  RDT_BLOODSLIDE_PREVALENCE               = "rdt_bloodslide_prevalence";

  public static final String  AGE_LOWEST                              = "age_lowest";

  public static final String  AGE_HIGHEST                             = "age_highest";

  public static final String  SUM_AREA_TARGETS                        = "get_area_spray_target_by_id_and_tar";

  public static final String  ASSAY_TYPE                              = "assay_type";
  
  public static final String  OVERALL_MORTALITY                       = "overall_mortality";

  /*
   * BEGIN InterventionControl query builder constants.
   */
  public static final String  PREMISES_AVAILABLE_FOR_VEHICLE_SPRAYING = "premises_available_for_vehicle_spraying";

  public static final String  PREMISES_INCLUDED_FOR_VEHICLE_SPRAYING  = "premises_included_for_vehicle_spraying";

  public static final String  PERCENT_TREATED_WITH_VEHICLE_SPRAYING   = "percent_treated_with_vehicle_spraying";

  public static final String  TOTAL_PREMISES_AVAILABLE                = "total_premises_available";

  public static final String  TOTAL_PREMISES_VISITED                  = "total_premises_visited";

  public static final String  TOTAL_PREMISES_TREATED                  = "total_premises_treated";

  public static final String  TOTAL_PREMISES_NOT_TREATED              = "total_premises_not_treated";

  public static final String  PERCENT_VISITED_NOT_TREATED             = "percent_visited_not_treated";

  public static final String  PERCENT_PREMISES_VISITED                = "percent_premises_visited";

  public static final String  PERCENT_PREMISES_TREATED                = "percent_premises_treated";

  public static final String  PERCENT_VISITED_TREATED                 = "percent_visited_treated";

  public static String        TYPE_QB                                 = "dss.vector.solutions.querybuilder.TypeQB";

  public static String        FORM_SURVEY_QB                          = "dss.vector.solutions.querybuilder.FormSurveyQB";

  // END

  public enum QueryType implements Reloadable {

    QUERY_IMMATURE_CONTAINER_COLLECTION,

    QUERY_PUPAL_CONTAINER_COLLECTION,

    QUERY_AGGREGATED_CASE,

    QUERY_ENTOMOLOGY("dss.vector.solutions.entomology.InfectionAssay"),

    QUERY_INDICATOR_SURVEY,

    QUERY_STOCK,

    QUERY_LARVACIDE,

    QUERY_IRS,

    QUERY_RESISTANCE("dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay"),

    QUERY_RESISTANCE_BIOASSAY("dss.vector.solutions.entomology.TimeResponseAssay"),

    QUERY_AGGREGATED_IPT,

    QUERY_INDIVIDUAL_IPT,

    QUERY_INDIVIDUAL_CASES,

    QUERY_EFFICACY_ASSAY,

    QUERY_INTERVENTION_CONTROL,

    QUERY_MOSQUITO_COLLECTIONS,

    QUERY_ITN_COMMUNITY_DISTRIBUTION,

    QUERY_ITN_FACILITY_DISTRIBUTION,

    QUERY_AGGREGATED_ITN,

    QUERY_UNIVERSAL,

    QUERY_FORM_SURVEY;

    private String queryClass;

    /**
     * Constructor for the query types that have a different query class (the
     * Java class that processes the XML/JSON for a query) than the domain class
     * that is being queried.
     * 
     * @param queryClass
     */
    private QueryType(String queryClass)
    {
      this();

      this.queryClass = queryClass;
    }

    private QueryType()
    {
      this.queryClass = null;
    }

    public String getQueryClass()
    {
      return this.queryClass;
    }
  }

  public static String getNamespacedDataStore()
  {
    return FEATURE_NAMESPACE + "_" + CommonProperties.getDeployAppName();
  }

  /**
   * Namespaces the query with the qualified class and query type.
   */
  public static String namespaceQuery(String queryClass, QueryConstants.QueryType queryType)
  {
    return queryClass + NAMESPACE_DELIM + queryType.name();
  }

  /**
   * Namespaces the query with the qualified class and query type.
   */
  public static String namespaceQuery(String type, String typeName)
  {
    return type + NAMESPACE_DELIM + typeName;
  }

  /**
   * Tests if a saved query is from the form generator. Note both MERG surveys
   * and normal form surveys are considered to be part of the form generator.
   * 
   * @param namespacedType
   * @return
   */
  public static boolean isFormGeneratorQuery(String namespacedType)
  {
    String queryClass = getQueryClass(namespacedType);

    return queryClass.equals(MDSSInfo.TYPE_QB) || queryClass.equals(MDSSInfo.FORM_SURVEY_QB);
  }

  /**
   * Returns the domain class of the namespaced query type. This should be
   * called if QueryConstants.isFormGeneratorQuery(String) returns true.
   * 
   * @param namespacedType
   * @return
   */
  public static String getFormGeneratorClass(String namespacedType)
  {
    return namespacedType.split(NAMESPACE_DELIM)[0];
  }

  /**
   * Extracts the query class from the string generated with namespaceQuery().
   * 
   * @param namespacedType
   * @return
   */
  public static String getQueryClass(String namespacedType)
  {
    /* 
     * IMPORTANT: We must check the Form Suvey class first because it signifies that it will
     * have a different end point than nomral form query classes.  However, it has the same
     * package as all other form classes.
     */
    if (namespacedType.startsWith(MDSSInfo.ROOT_FORM_SURVEY_CLASS))
    {
      return MDSSInfo.FORM_SURVEY_QB;
    }
    else if (namespacedType.startsWith(MDSSInfo.GENERATED_FORM_BUSINESS_PACKAGE))
    {
      return MDSSInfo.TYPE_QB;
    }

    String[] parts = namespacedType.split(NAMESPACE_DELIM);
    QueryType queryType = QueryType.valueOf(parts[1]);

    return queryType.getQueryClass() != null ? queryType.getQueryClass() : parts[0];
  }

  // public static QueryType getQueryType(String namespacedType)
  // {
  // String name = namespacedType.split(NAMESPACE_DELIM)[1];
  // return QueryType.valueOf(name);
  // }

  public static String createSLDName(String layerId)
  {
    return SLD_PREFIX + layerId.substring(0, 32);
  }
}
