package mdss.ivcc.mrc.csu.entomology;

import mdss.ivcc.mrc.csu.entomology.MosquitoDTOBase;

public class MosquitoDTO extends MosquitoDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  public final static String CLASS = "mdss.ivcc.mrc.csu.entomology.Mosquito";
  private static final long serialVersionUID = 1234288152412L;
  
  public MosquitoDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected MosquitoDTO(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
}
