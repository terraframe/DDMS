package dss.vector.solutions.entomology;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 *
 * @author Autogenerated by TerraFrame
 */
@com.terraframe.mojo.business.ClassSignature(hash = 60044630)
public enum LifeStage implements com.terraframe.mojo.business.BusinessEnumeration, com.terraframe.mojo.generation.loader.Reloadable
{
  ADULT(),
  
  EGG(),
  
  IMMATURE();
  
  public static final java.lang.String CLASS = "dss.vector.solutions.entomology.LifeStage";
  private dss.vector.solutions.entomology.LifeStageMaster enumeration;
  
  private synchronized void loadEnumeration()
  {
    dss.vector.solutions.entomology.LifeStageMaster enu = dss.vector.solutions.entomology.LifeStageMaster.getEnumeration(this.name());
    setEnumeration(enu);
  }
  
  private synchronized void setEnumeration(dss.vector.solutions.entomology.LifeStageMaster enumeration)
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
  
  public static LifeStage get(String id)
  {
    for (LifeStage e : LifeStage.values())
    {
      if (e.getId().equals(id))
      {
        return e;
      }
    }
    return null;
  }
  
}
