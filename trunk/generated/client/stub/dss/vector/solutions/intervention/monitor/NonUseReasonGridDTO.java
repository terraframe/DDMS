package dss.vector.solutions.intervention.monitor;

public class NonUseReasonGridDTO extends NonUseReasonGridDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1252970223505L;
  
  public NonUseReasonGridDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected NonUseReasonGridDTO(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
}
