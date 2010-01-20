package dss.vector.solutions.irs;

public class TeamMemberViewDTO extends TeamMemberViewDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = -674995973;
  
  public TeamMemberViewDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }

  public String getLabel()
  {
    return this.getFirstName() + " " + this.getLastName();
  }
}
