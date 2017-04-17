package dss.vector.solutions.irs;

public class OperatorSprayViewDTO extends OperatorSprayViewDTOBase
 implements com.runwaysdk.generation.loader.Reloadable{
  private static final long serialVersionUID = 1240853366081L;
  
  public OperatorSprayViewDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }  
  
  public boolean hasConcrete()
  {
    return (this.getConcreteId() != null && !this.getConcreteId().equals(""));
  }
}
