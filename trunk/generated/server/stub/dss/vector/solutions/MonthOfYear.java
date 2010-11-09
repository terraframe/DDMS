package dss.vector.solutions;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 *
 * @author Autogenerated by RunwaySDK
 */
@com.runwaysdk.business.ClassSignature(hash = 1680541164)
public enum MonthOfYear implements com.runwaysdk.business.BusinessEnumeration, com.runwaysdk.generation.loader.Reloadable
{
  APRIL(),
  
  AUGUST(),
  
  DECEMBER(),
  
  FEBRUARY(),
  
  JANUARY(),
  
  JULY(),
  
  JUNE(),
  
  MARCH(),
  
  MAY(),
  
  NOVEMBER(),
  
  OCTOBER(),
  
  SEPTEMBER();
  
  public static final java.lang.String CLASS = "dss.vector.solutions.MonthOfYear";
  private dss.vector.solutions.MonthOfYearMaster enumeration;
  
  private synchronized void loadEnumeration()
  {
    dss.vector.solutions.MonthOfYearMaster enu = dss.vector.solutions.MonthOfYearMaster.getEnumeration(this.name());
    setEnumeration(enu);
  }
  
  private synchronized void setEnumeration(dss.vector.solutions.MonthOfYearMaster enumeration)
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
    return enumeration.getDisplayLabel().getValue(com.runwaysdk.session.Session.getCurrentLocale());
  }
  
  public static MonthOfYear get(String id)
  {
    for (MonthOfYear e : MonthOfYear.values())
    {
      if (e.getId().equals(id))
      {
        return e;
      }
    }
    return null;
  }
  
}
