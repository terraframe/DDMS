package dss.vector.solutions.intervention.monitor;

import java.text.DateFormat;
import java.util.Locale;

public class SurveyPointDTO extends SurveyPointDTOBase
 implements com.runwaysdk.generation.loader.Reloadable{
  private static final long serialVersionUID = 1239641306792L;

  public SurveyPointDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }

  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  *
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected SurveyPointDTO(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }

  public String getDisplayLabel()
  {
    //TODO localize the this display label
    DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);

    return this.getGeoEntity().getEntityLabel().getValue() + " - " + format.format(this.getSurveyDate());
  }
}
