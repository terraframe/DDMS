package dss.vector.solutions;

import com.runwaysdk.AttributeNotificationDTO;
import com.runwaysdk.business.MutableDTO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.transport.metadata.AttributeMdDTO;

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

  public void setNotification(MutableDTO mutable, AttributeMdDTO attribute)
  {
    this.setComponentId(mutable.getId());
    this.setAttributeName(attribute.getName());
    this.setAttributeDisplayLabel(attribute.getDisplayLabel());
    this.setDefiningType(mutable.getType());
    this.setDefiningTypeDisplayLabel(mutable.getMd().getDisplayLabel());
  }
}
