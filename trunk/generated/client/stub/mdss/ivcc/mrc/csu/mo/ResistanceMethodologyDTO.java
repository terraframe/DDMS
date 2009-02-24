package mdss.ivcc.mrc.csu.mo;

import mdss.ivcc.mrc.csu.mo.ResistanceMethodologyDTOBase;

public class ResistanceMethodologyDTO extends ResistanceMethodologyDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  public final static String CLASS = "mdss.ivcc.mrc.csu.mo.AssayMethod";
  private static final long serialVersionUID = 1234731975948L;
  
  public ResistanceMethodologyDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected ResistanceMethodologyDTO(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
}
