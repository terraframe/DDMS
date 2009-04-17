package dss.vector.solutions.intervention.monitor;

import java.text.DateFormat;
import java.util.Locale;

import com.terraframe.mojo.util.Converter;

import dss.vector.solutions.util.DateConverter;

public class SurveyPointDTO extends SurveyPointDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1239641306792L;

  public SurveyPointDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }

  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  *
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected SurveyPointDTO(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }

  public Converter getSurveyDateConverter()
  {
    String label = getSurveyDateMd().getDisplayLabel();

    return new DateConverter(label);
  }

  public String getDisplayLabel()
  {
    //TODO localize the this display label
    DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);

    return this.getGeoEntity().getEntityName() + " - " + format.format(this.getSurveyDate());
  }

}
