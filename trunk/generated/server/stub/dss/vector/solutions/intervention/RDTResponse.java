package dss.vector.solutions.intervention;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 *
 * @author Autogenerated by TerraFrame
 */
public enum RDTResponse implements com.terraframe.mojo.business.BusinessEnumeration, com.terraframe.mojo.generation.loader.Reloadable
{
  NO(),
  
  REFUSED(),
  
  YES();
  
  public static final java.lang.String CLASS = "dss.vector.solutions.intervention.RDTResponse";
  private dss.vector.solutions.intervention.ResponseMaster enumeration;
  
  private synchronized void loadEnumeration()
  {
    dss.vector.solutions.intervention.ResponseMaster enu = dss.vector.solutions.intervention.ResponseMaster.getEnumeration(this.name());
    setEnumeration(enu);
  }
  
  private synchronized void setEnumeration(dss.vector.solutions.intervention.ResponseMaster enumeration)
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
  
  public static RDTResponse get(String id)
  {
    for (RDTResponse e : RDTResponse.values())
    {
      if (e.getId().equals(id))
      {
        return e;
      }
    }
    return null;
  }
  
}
