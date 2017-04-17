package dss.vector.solutions.surveillance;

public class CaseDiagnosticDTO extends CaseDiagnosticDTOBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 163734580;

  public CaseDiagnosticDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }

  /**
   * Copy Constructor: Duplicates the values and attributes of the given
   * BusinessDTO into a new DTO.
   * 
   * @param businessDTO
   *          The BusinessDTO to duplicate
   * @param clientRequest
   *          The clientRequest this DTO should use to communicate with the
   *          server.
   */
  protected CaseDiagnosticDTO(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }

}
