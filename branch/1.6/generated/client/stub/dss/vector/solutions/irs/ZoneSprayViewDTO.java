package dss.vector.solutions.irs;

public class ZoneSprayViewDTO extends ZoneSprayViewDTOBase
 implements com.runwaysdk.generation.loader.Reloadable{
  private static final long serialVersionUID = 1240860657660L;
  
  public ZoneSprayViewDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  public boolean hasConcrete()
  {
    return (this.getConcreteId() != null && !this.getConcreteId().equals(""));
  }
}
