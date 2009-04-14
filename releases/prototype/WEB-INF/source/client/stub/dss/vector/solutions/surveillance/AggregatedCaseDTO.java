package dss.vector.solutions.surveillance;

import java.text.DateFormat;
import java.util.Locale;

import com.terraframe.mojo.util.Converter;

import dss.vector.solutions.util.DateConverter;

public class AggregatedCaseDTO extends AggregatedCaseDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1238693161900L;

  public AggregatedCaseDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }

  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  *
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected AggregatedCaseDTO(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }

  public Converter getStartDateConverter()
  {
    String label = getStartDateMd().getDisplayLabel();

    return new DateConverter(label);
  }

  public Converter getEndDateConverter()
  {
    String label = getEndDateMd().getDisplayLabel();

    return new DateConverter(label);
  }

  public String getFormattedStartDate()
  {
    //TODO get the locale
    DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);

    return format.format(this.getStartDate());
  }

  public String getFormattedEndDate()
  {
    //TODO get the locale
    DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);

    return format.format(this.getEndDate());
  }
}
