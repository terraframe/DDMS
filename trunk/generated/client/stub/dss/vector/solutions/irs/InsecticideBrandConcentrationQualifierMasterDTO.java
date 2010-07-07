package dss.vector.solutions.irs;

public class InsecticideBrandConcentrationQualifierMasterDTO extends InsecticideBrandConcentrationQualifierMasterDTOBase
 implements com.runwaysdk.generation.loader.Reloadable{
  private static final long serialVersionUID = -827877553;
  
  public InsecticideBrandConcentrationQualifierMasterDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected InsecticideBrandConcentrationQualifierMasterDTO(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
}
