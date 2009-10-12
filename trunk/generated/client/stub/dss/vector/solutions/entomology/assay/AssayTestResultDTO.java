package dss.vector.solutions.entomology.assay;

import dss.vector.solutions.ontology.TermDTO;

public abstract class AssayTestResultDTO extends AssayTestResultDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1235750542264L;
  
  public AssayTestResultDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected AssayTestResultDTO(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  /**
   * @return The test result of the AssayTestResult
   */
  public abstract Object getTestResult();

  public TermDTO getTestMethod()
  {
    return null;
  }
}
