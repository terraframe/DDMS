package csu.mrc.ivcc.mdss.entomology.assay.biochemical;

import csu.mrc.ivcc.mdss.entomology.assay.biochemical.MonooxygenaseTestResultDTOBase;

public class MonooxygenaseTestResultDTO extends MonooxygenaseTestResultDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1235751238754L;
  
  public MonooxygenaseTestResultDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected MonooxygenaseTestResultDTO(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
}