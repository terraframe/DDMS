package csu.mrc.ivcc.mdss.entomology;

import csu.mrc.ivcc.mdss.entomology.AbstractMosquitoCollectionDTOBase;

public abstract class AbstractMosquitoCollectionDTO extends AbstractMosquitoCollectionDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1234288138203L;
  
  public AbstractMosquitoCollectionDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected AbstractMosquitoCollectionDTO(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
}