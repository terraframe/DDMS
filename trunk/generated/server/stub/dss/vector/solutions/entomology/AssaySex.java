package dss.vector.solutions.entomology;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 *
 * @author Autogenerated by TerraFrame
 */
public enum AssaySex implements com.terraframe.mojo.business.BusinessEnumeration, com.terraframe.mojo.generation.loader.Reloadable
{
  FEMALE(),
  
  MALE(),
  
  MIXED(),
  
  UNKNOWN();
  
  public static final java.lang.String CLASS = "dss.vector.solutions.entomology.AssaySex";
  private dss.vector.solutions.entomology.SexMaster enumeration;
  
  private synchronized void loadEnumeration()
  {
    dss.vector.solutions.entomology.SexMaster enu = dss.vector.solutions.entomology.SexMaster.getEnumeration(this.name());
    setEnumeration(enu);
  }
  
  private synchronized void setEnumeration(dss.vector.solutions.entomology.SexMaster enumeration)
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
  
  public static AssaySex get(String id)
  {
    for (AssaySex e : AssaySex.values())
    {
      if (e.getId().equals(id))
      {
        return e;
      }
    }
    return null;
  }
  
}
