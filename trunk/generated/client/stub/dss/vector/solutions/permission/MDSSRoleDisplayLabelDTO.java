package dss.vector.solutions.permission;

public class MDSSRoleDisplayLabelDTO extends MDSSRoleDisplayLabelDTOBase
 implements com.runwaysdk.generation.loader.Reloadable{
  private static final long serialVersionUID = 1162917950;
  
  public MDSSRoleDisplayLabelDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given LocalStructDTO into a new DTO.
  * 
  * @param localStructDTO The LocalStructDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected MDSSRoleDisplayLabelDTO(com.runwaysdk.business.LocalStructDTO localStructDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(localStructDTO, clientRequest);
  }
  
}
