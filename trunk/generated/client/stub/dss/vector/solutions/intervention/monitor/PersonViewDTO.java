package dss.vector.solutions.intervention.monitor;


public class PersonViewDTO extends PersonViewDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1239989843154L;

  public PersonViewDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }

  @Override
  public void delete()
  {
    if(this.getConcreteId() != null)
    {
      PersonDTO.get(this.getRequest(), this.getConcreteId()).delete();
    }
  }

}
