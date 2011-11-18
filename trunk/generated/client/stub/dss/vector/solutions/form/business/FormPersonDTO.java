package dss.vector.solutions.form.business;

public class FormPersonDTO extends FormPersonDTOBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long  serialVersionUID = 876983521;

  public static final String FORM_TYPE        = "dss.vector.solutions.form.FormPerson";

  public FormPersonDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
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
  protected FormPersonDTO(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }

}