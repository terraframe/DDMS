package dss.vector.solutions.surveillance;

public class CaseTreatmentStockDTO extends CaseTreatmentStockDTOBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1732424532;

  public CaseTreatmentStockDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
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
  protected CaseTreatmentStockDTO(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
}
