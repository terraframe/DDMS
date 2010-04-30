package dss.vector.solutions.irs;

import com.runwaysdk.constants.ClientRequestIF;

public class GeoTargetViewDTO extends GeoTargetViewDTOBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240267397900L;

  public static final String TARGET           = "target_";

  public GeoTargetViewDTO(ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
}
