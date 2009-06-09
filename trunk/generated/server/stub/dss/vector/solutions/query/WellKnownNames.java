package dss.vector.solutions.query;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 *
 * @author Autogenerated by TerraFrame
 */
public enum WellKnownNames implements com.terraframe.mojo.business.BusinessEnumeration, com.terraframe.mojo.generation.loader.Reloadable
{
  CIRCLE(),
  
  CROSS(),
  
  SQUARE(),
  
  STAR(),
  
  TRIANGLE();
  
  public static final java.lang.String CLASS = "dss.vector.solutions.query.WellKnownNames";
  private dss.vector.solutions.query.WellKnownNamesMaster enumeration;
  
  private synchronized void loadEnumeration()
  {
    dss.vector.solutions.query.WellKnownNamesMaster enu = dss.vector.solutions.query.WellKnownNamesMaster.getEnumeration(this.name());
    setEnumeration(enu);
  }
  
  private synchronized void setEnumeration(dss.vector.solutions.query.WellKnownNamesMaster enumeration)
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
  
  public static WellKnownNames get(String id)
  {
    for (WellKnownNames e : WellKnownNames.values())
    {
      if (e.getId().equals(id))
      {
        return e;
      }
    }
    return null;
  }
  
}
