package dss.vector.solutions;

import com.runwaysdk.generation.loader.Reloadable;

public interface MDSSRoleInfo extends Reloadable
{
  public static final String MDSS_PREFIX    = "mdss";

  public static final String GUI_VISIBILITY = MDSS_PREFIX + ".GUIVisibility";

  public static final String SYSTEM         = MDSS_PREFIX + ".System";

  public static final String RUNWAY_ADMIN   = "Administrator";
}
