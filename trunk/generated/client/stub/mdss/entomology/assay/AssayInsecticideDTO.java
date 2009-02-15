package mdss.entomology.assay;

public class AssayInsecticideDTO extends AssayInsecticideDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  public final static String CLASS = "mdss.entomology.assay.AssayInsecticide";
  private static final long serialVersionUID = 1234731978080L;
  
  public AssayInsecticideDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given StructDTO into a new DTO.
  * 
  * @param structDTO The StructDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected AssayInsecticideDTO(com.terraframe.mojo.business.StructDTO structDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(structDTO, clientRequest);
  }
  
}
