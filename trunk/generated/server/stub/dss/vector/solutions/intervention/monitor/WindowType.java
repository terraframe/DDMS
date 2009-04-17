package dss.vector.solutions.intervention.monitor;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 *
 * @author Autogenerated by TerraFrame
 */
public enum WindowType implements com.terraframe.mojo.business.BusinessEnumeration, com.terraframe.mojo.generation.loader.Reloadable
{
  ANY_WINDOW(),
  
  SHUTTERS(),
  
  WINDOW_WITH_CURTAINS(),
  
  WINDOW_WITH_GLASS(),
  
  WINDOW_WITH_SCREENS();
  
  public static final java.lang.String CLASS = "dss.vector.solutions.intervention.monitor.WindowType";
  private dss.vector.solutions.intervention.monitor.WindowMaster enumeration;
  
  private synchronized void loadEnumeration()
  {
    dss.vector.solutions.intervention.monitor.WindowMaster enu = dss.vector.solutions.intervention.monitor.WindowMaster.getEnumeration(this.name());
    setEnumeration(enu);
  }
  
  private synchronized void setEnumeration(dss.vector.solutions.intervention.monitor.WindowMaster enumeration)
  {
    this.enumeration = enumeration;
  }
  
  public java.lang.String getId()
  {
    loadEnumeration();
    return enumeration.getId();
  }
  
  public java.lang.String getEnumName()
  {
    loadEnumeration();
    return enumeration.getEnumName();
  }
  
  public java.lang.String getDisplayLabel()
  {
    loadEnumeration();
    return enumeration.getDisplayLabel();
  }
  
  public static WindowType get(String id)
  {
    for (WindowType e : WindowType.values())
    {
      if (e.getId().equals(id))
      {
        return e;
      }
    }
    return null;
  }
  
}
