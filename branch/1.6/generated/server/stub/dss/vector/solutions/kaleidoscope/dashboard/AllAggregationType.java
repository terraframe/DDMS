package dss.vector.solutions.kaleidoscope.dashboard;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 *
 * @author Autogenerated by RunwaySDK
 */
@com.runwaysdk.business.ClassSignature(hash = 833556371)
public enum AllAggregationType implements com.runwaysdk.business.BusinessEnumeration, com.runwaysdk.generation.loader.Reloadable
{
  AVG(),
  
  DISTRIBUTION(),
  
  MAJORITY(),
  
  MAX(),
  
  MIN(),
  
  MINORITY(),
  
  NONE(),
  
  SUM();
  
  public static final java.lang.String CLASS = "dss.vector.solutions.kaleidoscope.dashboard.AllAggregationType";
  private dss.vector.solutions.kaleidoscope.dashboard.AggregationType enumeration;
  
  private synchronized void loadEnumeration()
  {
    dss.vector.solutions.kaleidoscope.dashboard.AggregationType enu = dss.vector.solutions.kaleidoscope.dashboard.AggregationType.getEnumeration(this.name());
    setEnumeration(enu);
  }
  
  private synchronized void setEnumeration(dss.vector.solutions.kaleidoscope.dashboard.AggregationType enumeration)
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
  
  public static AllAggregationType get(String id)
  {
    for (AllAggregationType e : AllAggregationType.values())
    {
      if (e.getId().equals(id))
      {
        return e;
      }
    }
    return null;
  }
  
}
