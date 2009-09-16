package dss.vector.solutions.intervention;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 *
 * @author Autogenerated by TerraFrame
 */
public enum TimeInterval implements com.terraframe.mojo.business.BusinessEnumeration, com.terraframe.mojo.generation.loader.Reloadable
{
  PER_MONTH(),
  
  PER_WEEK(),
  
  PER_YEAR();
  
  public static final java.lang.String CLASS = "dss.vector.solutions.intervention.TimeInterval";
  private dss.vector.solutions.intervention.TimeIntervalMaster enumeration;
  
  private synchronized void loadEnumeration()
  {
    dss.vector.solutions.intervention.TimeIntervalMaster enu = dss.vector.solutions.intervention.TimeIntervalMaster.getEnumeration(this.name());
    setEnumeration(enu);
  }
  
  private synchronized void setEnumeration(dss.vector.solutions.intervention.TimeIntervalMaster enumeration)
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
    return enumeration.getDisplayLabel().getValue(com.terraframe.mojo.session.Session.getCurrentLocale());
  }
  
  public static TimeInterval get(String id)
  {
    for (TimeInterval e : TimeInterval.values())
    {
      if (e.getId().equals(id))
      {
        return e;
      }
    }
    return null;
  }
  
}
