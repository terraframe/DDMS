package dss.vector.solutions.irs;

public class TeamSprayViewDTO extends TeamSprayViewDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1240860676058L;
  
  public TeamSprayViewDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }

  public boolean hasConcrete()
  {
    return (this.getConcreteId() != null && !this.getConcreteId().equals(""));
  }
}
