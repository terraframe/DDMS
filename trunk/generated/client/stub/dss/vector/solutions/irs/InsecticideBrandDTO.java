package dss.vector.solutions.irs;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.LabeledDTO;

public class InsecticideBrandDTO extends InsecticideBrandDTOBase implements Reloadable, LabeledDTO
{
  private static final long serialVersionUID = 1240597944499L;

  public InsecticideBrandDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
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
  protected InsecticideBrandDTO(com.terraframe.mojo.business.BusinessDTO businessDTO,
      com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }


  public String getDisplayLabel()
  {
    String ingredientLabel = this.getActiveIngredient().getDisplayLabel();
    String unitLabel = this.getUnits().get(0).item(this.getRequest()).getDisplayLabel().getDefaultLocale();

    return ingredientLabel + " " + this.getAmount() + " " + unitLabel;
  }
}
