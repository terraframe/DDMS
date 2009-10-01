package dss.vector.solutions;

import com.terraframe.mojo.AttributeNotification;
import com.terraframe.mojo.business.Entity;
import com.terraframe.mojo.business.View;
import com.terraframe.mojo.generation.loader.Reloadable;
import com.terraframe.mojo.session.Session;

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
