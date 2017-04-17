package dss.vector.solutions.kaleidoscope.dashboard.layer;

public class CategoryIconDTO extends CategoryIconDTOBase
 implements com.runwaysdk.generation.loader.Reloadable{
  private static final long serialVersionUID = -1642452013;
  
  public CategoryIconDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected CategoryIconDTO(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
}
