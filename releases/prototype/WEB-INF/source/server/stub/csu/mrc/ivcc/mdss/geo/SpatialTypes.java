package csu.mrc.ivcc.mdss.geo;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 *
 * @author Autogenerated by TerraFrame
 */
public enum SpatialTypes implements com.terraframe.mojo.business.BusinessEnumeration, com.terraframe.mojo.generation.loader.Reloadable
{
  LINE(),
  
  POINT(),
  
  POLYGON();
  
  public static final java.lang.String CLASS = "csu.mrc.ivcc.mdss.geo.SpatialTypes";
  private csu.mrc.ivcc.mdss.geo.SpatialMaster enumeration;
  
  private synchronized void loadEnumeration()
  {
    csu.mrc.ivcc.mdss.geo.SpatialMaster enu = csu.mrc.ivcc.mdss.geo.SpatialMaster.getEnumeration(this.name());
    setEnumeration(enu);
  }
  
  private synchronized void setEnumeration(csu.mrc.ivcc.mdss.geo.SpatialMaster enumeration)
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
    return enumeration.getDisplayLabel();
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
