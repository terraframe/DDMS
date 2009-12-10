package dss.vector.solutions.irs;

public class ResourceTargetViewDTO extends ResourceTargetViewDTOBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long  serialVersionUID = 1240257027851L;

  public static final String TARGET           = "target_";

  public ResourceTargetViewDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }

}
