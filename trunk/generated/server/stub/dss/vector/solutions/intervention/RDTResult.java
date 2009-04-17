package dss.vector.solutions.intervention;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 *
 * @author Autogenerated by TerraFrame
 */
public enum RDTResult implements com.terraframe.mojo.business.BusinessEnumeration, com.terraframe.mojo.generation.loader.Reloadable
{
  MALARIAE_POSITIVE(),
  
  MIXED_POSITIVE(),
  
  NEGATIVE(),
  
  NOT_VALID(),
  
  OVALE_POSITIVE(),
  
  PF_POSITIVE(),
  
  VIVAX_POSITIVE();
  
  public static final java.lang.String CLASS = "dss.vector.solutions.intervention.RDTResult";
  private dss.vector.solutions.intervention.RDTResultMaster enumeration;
  
  private synchronized void loadEnumeration()
  {
    dss.vector.solutions.intervention.RDTResultMaster enu = dss.vector.solutions.intervention.RDTResultMaster.getEnumeration(this.name());
    setEnumeration(enu);
  }
  
  private synchronized void setEnumeration(dss.vector.solutions.intervention.RDTResultMaster enumeration)
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
  
  public static RDTResult get(String id)
  {
    for (RDTResult e : RDTResult.values())
    {
      if (e.getId().equals(id))
      {
        return e;
      }
    }
    return null;
  }
  
}
