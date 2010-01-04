package dss.vector.solutions;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 *
 * @author Autogenerated by TerraFrame
 */
@com.terraframe.mojo.business.ClassSignature(hash = 798222706)
public enum SurfacePosition implements com.terraframe.mojo.business.BusinessEnumeration, com.terraframe.mojo.generation.loader.Reloadable
{
  BOTTOM(),
  
  MIDDLE(),
  
  NOT_APPLICABLE(),
  
  RANDOM(),
  
  TOP();
  
  public static final java.lang.String CLASS = "dss.vector.solutions.SurfacePosition";
  private dss.vector.solutions.SurfacePositionMaster enumeration;
  
  private synchronized void loadEnumeration()
  {
    dss.vector.solutions.SurfacePositionMaster enu = dss.vector.solutions.SurfacePositionMaster.getEnumeration(this.name());
    setEnumeration(enu);
  }
  
  private synchronized void setEnumeration(dss.vector.solutions.SurfacePositionMaster enumeration)
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
  
  public static SurfacePosition get(String id)
  {
    for (SurfacePosition e : SurfacePosition.values())
    {
      if (e.getId().equals(id))
      {
        return e;
      }
    }
    return null;
  }
  
}
