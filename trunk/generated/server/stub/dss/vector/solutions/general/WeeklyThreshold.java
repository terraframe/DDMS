package dss.vector.solutions.general;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.ConfigurationException;
import com.runwaysdk.generation.CommonGenerationUtil;
import com.runwaysdk.session.Session;

public class WeeklyThreshold extends WeeklyThresholdBase implements com.runwaysdk.generation.loader.Reloadable
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
    if (this.getNotification() != null && ! ( this.getNotification() > 0 ))
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
    if (this.getIdentification() != null && ! ( this.getIdentification() > 0 ))
    {
      ThresholdValueProblem p = new ThresholdValueProblem();
      p.setNotification(this, IDENTIFICATION);
      p.setEntityLabel(this.getParent().getGeoEntity().getLabel());
      p.setThreshold(this.getIdentification());
      p.apply();

      p.throwIt();
    }
  }

  private boolean performedAlert(Object weekOfLastAlert)
  {
    EpiDate currentEpiWeek = EpiDate.getEpiWeek(new Date());

    if (weekOfLastAlert != null && weekOfLastAlert instanceof EpiWeek)
    {
      EpiWeek _weekOfLastAlert = (EpiWeek) weekOfLastAlert;

      boolean period = _weekOfLastAlert.getPeriod() == currentEpiWeek.getPeriod();
      boolean year = _weekOfLastAlert.getYearOfWeek() != currentEpiWeek.getYear();

      return period && year;
    }

    return false;
  }

  public void reachedThreshold(String attribute, Double threshold)
  {
    EpiWeek week = EpiWeek.getEpiWeek(new Date());

    if (!this.isNew())
    {
      this.appLock();
    }

    if (this.getDateThresholdWasReached(attribute) == null)
    {
      this.setDateThresholdWasReached(attribute, new Date());
    }

    this.setActualThreshold(attribute, threshold);
    this.updateThresholdWeek(attribute, week);
    this.apply();
  }

  private void setDateThresholdWasReached(String attribute, Date date)
  {
    try
    {
      String accessor = CommonGenerationUtil.upperFirstCharacter(attribute);

      Class<? extends WeeklyThreshold> clazz = this.getClass();
      Method method = clazz.getMethod("setFirst" + accessor, date.getClass());

      method.invoke(this, date);
    }
    catch (InvocationTargetException e)
    {
      throw new ConfigurationException(e.getTargetException());
    }
    catch (Exception e)
    {
      throw new ConfigurationException(e);
    }
  }

  private void updateThresholdWeek(String attribute, EpiWeek week)
  {
    try
    {
      String accessor = CommonGenerationUtil.upperFirstCharacter(attribute);

      Class<? extends WeeklyThreshold> clazz = this.getClass();
      Method method = clazz.getMethod("setLast" + accessor, week.getClass());

      method.invoke(this, week);
    }
    catch (InvocationTargetException e)
    {
      throw new ConfigurationException(e.getTargetException());
    }
    catch (Exception e)
    {
      throw new ConfigurationException(e);
    }

  }

  public void setActualThreshold(String attribute, Double threshold)
  {
    try
    {
      String accessor = CommonGenerationUtil.upperFirstCharacter(attribute);

      Class<? extends WeeklyThreshold> clazz = this.getClass();
      Method method = clazz.getMethod("setActual" + accessor, Double.class);

      method.invoke(this, threshold);
    }
    catch (InvocationTargetException e)
    {
      throw new ConfigurationException(e.getTargetException());
    }
    catch (Exception e)
    {
      throw new ConfigurationException(e);
    }
  }

  public Double getThreshold(String attribute)
  {
    try
    {
      String accessor = CommonGenerationUtil.upperFirstCharacter(attribute);

      Class<? extends WeeklyThreshold> clazz = this.getClass();
      Method method = clazz.getMethod("get" + accessor);

      return (Double) method.invoke(this);
    }
    catch (InvocationTargetException e)
    {
      throw new ConfigurationException(e.getTargetException());
    }
    catch (Exception e)
    {
      throw new ConfigurationException(e);
    }
  }

  public Double getActualThreshold(String attribute)
  {
    try
    {
      String accessor = CommonGenerationUtil.upperFirstCharacter(attribute);

      Class<? extends WeeklyThreshold> clazz = this.getClass();
      Method method = clazz.getMethod("getActual" + accessor);

      return (Double) method.invoke(this);
    }
    catch (InvocationTargetException e)
    {
      throw new ConfigurationException(e.getTargetException());
    }
    catch (Exception e)
    {
      throw new ConfigurationException(e);
    }
  }

  public Boolean getPerformedAlert(String attribute)
  {
    try
    {
      String accessor = CommonGenerationUtil.upperFirstCharacter(attribute);

      Class<? extends WeeklyThreshold> clazz = this.getClass();
      Method method = clazz.getMethod("getLast" + accessor);

      return this.performedAlert(method.invoke(this));
    }
    catch (InvocationTargetException e)
    {
      throw new ConfigurationException(e.getTargetException());
    }
    catch (Exception e)
    {
      throw new ConfigurationException(e);
    }
  }

  private Date getDateThresholdWasReached(String attribute)
  {
    try
    {
      String accessor = CommonGenerationUtil.upperFirstCharacter(attribute);

      Class<? extends WeeklyThreshold> clazz = this.getClass();
      Method method = clazz.getMethod("getFirst" + accessor);

      Object date = method.invoke(this);

      return ( date != null ? (Date) date : null );
    }
    catch (InvocationTargetException e)
    {
      throw new ConfigurationException(e.getTargetException());
    }
    catch (Exception e)
    {
      throw new ConfigurationException(e);
    }
  }

  public List<WeeklyThresholdView> export()
  {
    Disease current = Disease.getCurrent();
    List<WeeklyThresholdView> list = new LinkedList<WeeklyThresholdView>();

    String[] accessors = { IDENTIFICATION, NOTIFICATION, FACILITYIDENTIFICATION, FACILITYNOTIFICATION };

    String entityLabel = this.getParent().getGeoEntity().getLabel();
    Integer period = this.getChild().getPeriod();
    Integer year = this.getChild().getYearOfWeek();

    for (String accessor : accessors)
    {
      Date date = this.getDateThresholdWasReached(accessor);

      if (date != null)
      {
        Double threshold = this.getActualThreshold(accessor);
        String type = this.getMdAttributeDAO(accessor).getDisplayLabel(Session.getCurrentLocale());

        WeeklyThresholdView view = new WeeklyThresholdView();
        view.setThresholdValue(threshold);
        view.setThresholdDate(date);
        view.setThreshsoldType(type);
        view.setEntityLabel(entityLabel);
        view.setPeriod(period);
        view.setYearOfWeek(year);
        view.setDiseaseLabel(current.getDisplayLabel());

        list.add(view);
      }
    }

    return list;
  }
}
