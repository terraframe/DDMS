package mdss.entomology;

public class IndentificationMethodMasterDTO extends IndentificationMethodMasterDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  public final static String CLASS = "mdss.entomology.IndentificationMethodMaster";
  private static final long serialVersionUID = 1234288150503L;
  
  public IndentificationMethodMasterDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected IndentificationMethodMasterDTO(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
}
