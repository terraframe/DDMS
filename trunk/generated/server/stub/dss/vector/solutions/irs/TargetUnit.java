package dss.vector.solutions.irs;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 *
 * @author Autogenerated by TerraFrame
 */
@com.runwaysdk.business.ClassSignature(hash = -331188602)
public enum TargetUnit implements com.runwaysdk.business.BusinessEnumeration, com.runwaysdk.generation.loader.Reloadable
{
  HOUSEHOLD(),
  
  ROOM(),
  
  STRUCTURE();
  
  public static final java.lang.String CLASS = "dss.vector.solutions.irs.TargetUnit";
  private dss.vector.solutions.irs.TargetUnitMaster enumeration;
  
  private synchronized void loadEnumeration()
  {
    dss.vector.solutions.irs.TargetUnitMaster enu = dss.vector.solutions.irs.TargetUnitMaster.getEnumeration(this.name());
    setEnumeration(enu);
  }
  
  private synchronized void setEnumeration(dss.vector.solutions.irs.TargetUnitMaster enumeration)
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
  
  public static TargetUnit get(String id)
  {
    for (TargetUnit e : TargetUnit.values())
    {
      if (e.getId().equals(id))
      {
        return e;
      }
    }
    return null;
  }
  
}
