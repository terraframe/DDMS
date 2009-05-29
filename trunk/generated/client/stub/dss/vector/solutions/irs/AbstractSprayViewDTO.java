package dss.vector.solutions.irs;


public abstract class AbstractSprayViewDTO extends AbstractSprayViewDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1240860693804L;
  
  public AbstractSprayViewDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  public boolean hasConcrete()
  {
    return (this.getSprayId() != null && !this.getSprayId().equals(""));
  }
}
