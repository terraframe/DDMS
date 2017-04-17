package dss.vector.solutions.surveillance;

import java.text.DateFormat;
import java.util.Locale;

public class AggregatedCaseDTO extends AggregatedCaseDTOBase
 implements com.runwaysdk.generation.loader.Reloadable{
  private static final long serialVersionUID = 1238693161900L;

  public AggregatedCaseDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }

  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  *
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected AggregatedCaseDTO(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
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
