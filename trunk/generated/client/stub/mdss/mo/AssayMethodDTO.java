package mdss.mo;

import mdss.mo.AssayMethodDTOBase;

public class AssayMethodDTO extends AssayMethodDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  public final static String CLASS = "mdss.entomology.AssayMethod";
  private static final long serialVersionUID = 1234731975948L;
  
  public AssayMethodDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected AssayMethodDTO(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
}
