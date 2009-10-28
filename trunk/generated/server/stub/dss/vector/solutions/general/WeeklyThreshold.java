package dss.vector.solutions.general;

import java.util.Date;

public class WeeklyThreshold extends WeeklyThresholdBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1256576864223L;

  public WeeklyThreshold(String parentId, String childId)
  {
    super(parentId, childId);
  }

  public WeeklyThreshold(dss.vector.solutions.general.ThresholdData parent, dss.vector.solutions.general.EpiWeek child)
  {
    this(parent.getId(), child.getId());
  }
  
  @Override
  public void apply()
  {
    validateNotification();
    validateIdentification();
    
    super.apply();
  }
  
  @Override
  public void validateNotification()
  {
    if(this.getNotification() != null && !(this.getNotification() > 0))
    {
      ThresholdValueProblem p = new ThresholdValueProblem();
      p.setNotification(this, NOTIFICATION);
      p.setEntityLabel(this.getParent().getGeoEntity().getLabel());
      p.setThreshold(this.getNotification());
      p.apply();
      
      p.throwIt();
    }
  }
  
  @Override
  public void validateIdentification()
  {
    if(this.getIdentification() != null && !(this.getIdentification() > 0))
    {
      ThresholdValueProblem p = new ThresholdValueProblem();
      p.setNotification(this, IDENTIFICATION);
      p.setEntityLabel(this.getParent().getGeoEntity().getLabel());
      p.setThreshold(this.getIdentification());
      p.apply();
      
      p.throwIt();
    }
  }

  private boolean performedAlert(EpiWeek weekOfLastAlert)
  {
    EpiDate epiDate = EpiDate.getEpiWeek(new Date());

    if (weekOfLastAlert != null)
    {
      boolean period = weekOfLastAlert.getPeriod() == epiDate.getPeriod();
      boolean year = weekOfLastAlert.getYearOfWeek() != epiDate.getYear();

      return period && year;
    }

    return false;    
  }

  public boolean performedNotificationAlert()
  {
    return this.performedAlert(this.getLastNotification());
  }
  
  public boolean performedIdentificationAlert()
  {
    return this.performedAlert(this.getLastIdentification());
  }
  
  public void updateLastNotification()
  {
    EpiWeek week = EpiWeek.getEpiWeek(new Date());

    this.lock();
    this.setLastNotification(week);
    this.apply();
  }

  public void updateLastIdentification()
  {
    EpiWeek week = EpiWeek.getEpiWeek(new Date());

    this.lock();
    this.setLastIdentification(week);
    this.apply();
  }
}
