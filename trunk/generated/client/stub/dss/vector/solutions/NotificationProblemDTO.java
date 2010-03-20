package dss.vector.solutions;

import com.runwaysdk.AttributeNotificationDTO;
import com.runwaysdk.generation.loader.Reloadable;

public abstract class NotificationProblemDTO extends NotificationProblemDTOBase implements AttributeNotificationDTO, Reloadable
{
  private static final long serialVersionUID = 1238204417274L;

  public NotificationProblemDTO(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }

  public NotificationProblemDTO(com.runwaysdk.constants.ClientRequestIF clientRequest,
      java.util.Locale locale)
  {
    super(clientRequest, locale);
  }

}
