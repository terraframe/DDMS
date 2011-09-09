package dss.vector.solutions;

public interface MDSSInfo
{
  /**
   * Root package shared by all MDSS sub-packages and classes.
   */
  public static final String ROOT_PACKAGE = "dss.vector.solutions";
  
  /**
   * Package for generated GeoEntity types as defined by administrators.
   */
  public static final String GENERATED_GEO_PACKAGE = ROOT_PACKAGE + ".geo.generated";

  public static final String GENERATED_FORM_PACKAGE = ROOT_PACKAGE + ".form";

  public static final String GENERATED_FORM_BUSINESS_PACKAGE = GENERATED_FORM_PACKAGE + ".business";
}
