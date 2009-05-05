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

  public static final String VIEW_NAME_SUFFIX = "_view";
}
