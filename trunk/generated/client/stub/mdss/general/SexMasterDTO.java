package mdss.general;

public class SexMasterDTO extends SexMasterDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  public final static String CLASS = "mdss.general.SexMaster";
  private static final long serialVersionUID = 1234288149922L;
  
  public SexMasterDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected SexMasterDTO(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
}
