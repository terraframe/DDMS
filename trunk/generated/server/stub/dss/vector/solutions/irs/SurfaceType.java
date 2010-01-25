package dss.vector.solutions.irs;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 *
 * @author Autogenerated by TerraFrame
 */
@com.terraframe.mojo.business.ClassSignature(hash = -1219850411)
public enum SurfaceType implements com.terraframe.mojo.business.BusinessEnumeration, com.terraframe.mojo.generation.loader.Reloadable
{
  NON_POROUS(),
  
  POROUS();
  
  public static final java.lang.String CLASS = "dss.vector.solutions.irs.SurfaceType";
  private dss.vector.solutions.irs.SurfaceTypeMaster enumeration;
  
  private synchronized void loadEnumeration()
  {
    dss.vector.solutions.irs.SurfaceTypeMaster enu = dss.vector.solutions.irs.SurfaceTypeMaster.getEnumeration(this.name());
    setEnumeration(enu);
  }
  
  private synchronized void setEnumeration(dss.vector.solutions.irs.SurfaceTypeMaster enumeration)
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
  
  public static SurfaceType get(String id)
  {
    for (SurfaceType e : SurfaceType.values())
    {
      if (e.getId().equals(id))
      {
        return e;
      }
    }
    return null;
  }
  
}
