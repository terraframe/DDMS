package dss.vector.solutions.intervention;

public class TimeIntervalMasterDTO extends TimeIntervalMasterDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1252970234791L;
  
  public TimeIntervalMasterDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected TimeIntervalMasterDTO(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
}
