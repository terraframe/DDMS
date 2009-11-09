package dss.vector.solutions.geo;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 *
 * @author Autogenerated by TerraFrame
 */
@com.terraframe.mojo.business.ClassSignature(hash = 68488826)
public enum SpatialTypes implements com.terraframe.mojo.business.BusinessEnumeration, com.terraframe.mojo.generation.loader.Reloadable
{
  LINE(),
  
  MULTI_LINE(),
  
  MULTI_POINT(),
  
  MULTI_POLYGON(),
  
  POINT(),
  
  POLYGON();
  
  public static final java.lang.String CLASS = "dss.vector.solutions.geo.SpatialTypes";
  private dss.vector.solutions.geo.SpatialMaster enumeration;
  
  private synchronized void loadEnumeration()
  {
    dss.vector.solutions.geo.SpatialMaster enu = dss.vector.solutions.geo.SpatialMaster.getEnumeration(this.name());
    setEnumeration(enu);
  }
  
  private synchronized void setEnumeration(dss.vector.solutions.geo.SpatialMaster enumeration)
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
  
  public static SpatialTypes get(String id)
  {
    for (SpatialTypes e : SpatialTypes.values())
    {
      if (e.getId().equals(id))
      {
        return e;
      }
    }
    return null;
  }
  
}
