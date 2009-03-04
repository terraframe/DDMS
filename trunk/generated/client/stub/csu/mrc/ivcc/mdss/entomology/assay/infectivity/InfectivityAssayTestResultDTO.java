package csu.mrc.ivcc.mdss.entomology.assay.infectivity;

import csu.mrc.ivcc.mdss.entomology.assay.infectivity.InfectivityAssayTestResultDTOBase;

public abstract class InfectivityAssayTestResultDTO extends InfectivityAssayTestResultDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1235751242268L;
  
  public InfectivityAssayTestResultDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected InfectivityAssayTestResultDTO(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
}
