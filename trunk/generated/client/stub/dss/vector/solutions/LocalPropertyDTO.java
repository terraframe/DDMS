package dss.vector.solutions;


public class LocalPropertyDTO extends LocalPropertyDTOBase
 implements com.runwaysdk.generation.loader.Reloadable{
  private static final long serialVersionUID = -1937234002;
  
  public LocalPropertyDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected LocalPropertyDTO(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  public String getDisplayLabel()
  {
    return this.getPropertyLabel().getValue();
  }
  
  public String getDescription()
  {
    return this.getPropertyDescription().getValue();
  }
  
}
