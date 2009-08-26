package dss.vector.solutions;

import com.terraframe.mojo.generation.loader.Reloadable;

public interface MDSSRoleInfo extends Reloadable
{
  public static final String GUI_VISIBILITY      = "mdss.GUIVisibility";

  public static final String MDSS                = "mdss.MDSS";

  public static final String MDSS_CORRDINATOR    = "mdss.coordinator";

  public static final String ENTOMOLOGIST        = "mdss.entomologist";

  public static final String DATACAPTURER        = "mdss.dataCapturer";

  public static final String MANAGER             = "mdss.manager";

  public static final String OPERATIONAL_MANAGER = "mdss.operationalManager";
}
