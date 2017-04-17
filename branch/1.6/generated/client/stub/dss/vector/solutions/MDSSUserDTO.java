package dss.vector.solutions;

public class MDSSUserDTO extends MDSSUserDTOBase implements com.runwaysdk.generation.loader.Reloadable
 {
  private static final long serialVersionUID = 1240853345960L;
  public static final String DISEASENAME = "diseaseName";
  public static final String DISEASELABEL = "diseaseLabel";
  
  public MDSSUserDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected MDSSUserDTO(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
}
