package dss.vector.solutions;

import com.runwaysdk.generation.loader.Reloadable;

public interface MDSSRoleInfo extends Reloadable
{
  public static final String MDSS_PREFIX         = "mdss";
  
  public static final String GUI_VISIBILITY      = MDSS_PREFIX + ".GUIVisibility";

  public static final String MDSS                = MDSS_PREFIX + ".MDSS";

  public static final String MDSS_CORRDINATOR    = MDSS_PREFIX + ".coordinator";

  public static final String ENTOMOLOGIST        = MDSS_PREFIX + ".entomologist";

  public static final String DATACAPTURER        = MDSS_PREFIX + ".dataCapturer";

  public static final String MANAGER             = MDSS_PREFIX + ".manager";

  public static final String OPERATIONAL_MANAGER = MDSS_PREFIX + ".operationalManager";

  public static final String STOCK_STAFF         = MDSS_PREFIX + ".stockStaff";

}
