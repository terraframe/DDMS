package dss.vector.solutions.form.business;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.LabeledDTO;

public class FormHouseholdDTO extends FormHouseholdDTOBase implements Reloadable, LabeledDTO
{
  private static final long  serialVersionUID = 1986664761;

  public static final String FORM_TYPE        = "dss.vector.solutions.form.FormHousehold";

  public FormHouseholdDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
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
  protected FormHouseholdDTO(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }

  @Override
  public String getLabel()
  {
    return this.getOid();
  }

  @Override
  public String getOptionId()
  {
    return this.getId();
  }
}
