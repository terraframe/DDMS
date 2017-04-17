package dss.vector.solutions.general;

import java.util.List;

import com.runwaysdk.constants.ClientRequestIF;

public class InsecticideDTO extends InsecticideDTOBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1237396178305L;

  public InsecticideDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
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
  protected InsecticideDTO(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }

  public String getDisplayLabel()
  {
    String unitDisplayLabel = this.getUnits().getTermDisplayLabel().getValue();
    String ingredientDisplayLabel = this.getActiveIngredient().getTermDisplayLabel().getValue();

    return ingredientDisplayLabel + " - " + this.getAmount() + " " + unitDisplayLabel;
  }

  public static List<? extends InsecticideDTO> getAll(ClientRequestIF clientRequest)
  {
    return InsecticideDTO.getAllInstances(clientRequest, "keyName", true, 0, 0).getResultSet();
  }
}
