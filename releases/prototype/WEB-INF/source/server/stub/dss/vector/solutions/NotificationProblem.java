package dss.vector.solutions;

import com.terraframe.mojo.business.Entity;

public abstract class NotificationProblem extends NotificationProblemBase implements com.terraframe.mojo.generation.loader.Reloadable
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
    this.setAttributeDisplayLabel(entity.getMdAttributeDAO(attributeName).getDisplayLabel());
    this.setDefiningType(entity.getType());
    this.setDefiningTypeDisplayLabel(entity.getClassDisplayLabel());
  }
  
}