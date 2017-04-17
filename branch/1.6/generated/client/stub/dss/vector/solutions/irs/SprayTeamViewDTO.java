package dss.vector.solutions.irs;

public class SprayTeamViewDTO extends SprayTeamViewDTOBase
 implements com.runwaysdk.generation.loader.Reloadable{
  private static final long serialVersionUID = -1402732329;
  
  public SprayTeamViewDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  public boolean getIsTeamLeaderReadable()
  {
    return super.isTeamLeaderReadable();
  }
}
