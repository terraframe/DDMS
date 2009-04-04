package dss.vector.solutions;

import com.terraframe.mojo.AttributeNotificationDTO;
import com.terraframe.mojo.generation.loader.Reloadable;

public abstract class NotificationProblemDTO extends NotificationProblemDTOBase implements AttributeNotificationDTO, Reloadable
{
  private static final long serialVersionUID = 1238204417274L;

  public NotificationProblemDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }

  public NotificationProblemDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest,
      java.util.Locale locale)
  {
    super(clientRequest, locale);
  }

}