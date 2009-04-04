package dss.vector.solutions.entomology;

import com.terraframe.mojo.AttributeNotificationDTO;
import com.terraframe.mojo.generation.loader.Reloadable;

public class InvalidCollectionDateProblemDTO extends InvalidCollectionDateProblemDTOBase implements AttributeNotificationDTO, Reloadable
{
  private static final long serialVersionUID = 1238204390094L;

  public InvalidCollectionDateProblemDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }

  public InvalidCollectionDateProblemDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest,
      java.util.Locale locale)
  {
    super(clientRequest, locale);
  }

}