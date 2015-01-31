package dss.vector.solutions.form.business;

public class HumanBloodIndexDTO extends HumanBloodIndexDTOBase
 implements com.runwaysdk.generation.loader.Reloadable{
  private static final long serialVersionUID = 933400920;
  
  public HumanBloodIndexDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected HumanBloodIndexDTO(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
}
