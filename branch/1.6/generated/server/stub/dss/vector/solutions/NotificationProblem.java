package dss.vector.solutions;

import com.runwaysdk.AttributeNotification;
import com.runwaysdk.business.Entity;
import com.runwaysdk.business.View;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;

public abstract class NotificationProblem extends NotificationProblemBase implements AttributeNotification,  Reloadable
{
  private static final long serialVersionUID = 1238204417262L;

  public NotificationProblem()
  {
    super();
  }

  public NotificationProblem(java.lang.String developerMessage)
  {
    super(developerMessage);
  }

  public void setNotification(Entity entity, String attributeName)
  {
    this.setComponentId(entity.getId());
    this.setAttributeName(attributeName);
    this.setAttributeDisplayLabel(entity.getMdAttributeDAO(attributeName).getDisplayLabel(Session.getCurrentLocale()));
    this.setDefiningType(entity.getType());
    this.setDefiningTypeDisplayLabel(entity.getClassDisplayLabel());
  }

  public void setNotification(View view, String attributeName)
  {
    this.setComponentId(view.getId());
    this.setAttributeName(attributeName);
    this.setAttributeDisplayLabel(view.getMdAttributeDAO(attributeName).getDisplayLabel(Session.getCurrentLocale()));
    this.setDefiningType(view.getType());
    this.setDefiningTypeDisplayLabel(view.getClassDisplayLabel());
  }

}
