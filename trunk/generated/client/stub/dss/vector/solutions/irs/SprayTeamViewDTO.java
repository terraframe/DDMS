package dss.vector.solutions.irs;

public class SprayTeamViewDTO extends SprayTeamViewDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = -1402732329;
  
  public SprayTeamViewDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  public boolean getIsTeamLeaderReadable()
  {
    return super.isTeamLeaderReadable();
  }
}
