package dss.vector.solutions.irs;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 *
 * @author Autogenerated by RunwaySDK
 */
@com.runwaysdk.business.ClassSignature(hash = 1965429288)
public enum InsecticideBrandUseDTO implements com.runwaysdk.business.EnumerationDTOIF, com.runwaysdk.generation.loader.Reloadable
{
  IRS(),
  
  ITM(),
  
  LIQUID_LARVICIDE(),
  
  OTHER(),
  
  SOLID_LARVICIDE(),
  
  SPACE_SPRAY();
  
  public final static String CLASS = "dss.vector.solutions.irs.InsecticideBrandUse";
  
  
  public dss.vector.solutions.irs.InsecticideBrandUseMasterDTO item(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    return (dss.vector.solutions.irs.InsecticideBrandUseMasterDTO) clientRequest.getEnumeration(dss.vector.solutions.irs.InsecticideBrandUseDTO.CLASS, this.name());
  }
  
  @java.lang.SuppressWarnings("unchecked")
  public static java.util.List<dss.vector.solutions.irs.InsecticideBrandUseMasterDTO> items(com.runwaysdk.constants.ClientRequestIF clientRequest, InsecticideBrandUseDTO ... items)
  {
    java.lang.String[] itemNames = new java.lang.String[items.length];
    for(int i=0; i<items.length; i++)
    {
      itemNames[i] = items[i].name();
    }
    return (java.util.List<dss.vector.solutions.irs.InsecticideBrandUseMasterDTO>) clientRequest.getEnumerations(dss.vector.solutions.irs.InsecticideBrandUseDTO.CLASS, itemNames);
  }
  
  @java.lang.SuppressWarnings("unchecked")
  public static java.util.List<dss.vector.solutions.irs.InsecticideBrandUseMasterDTO> allItems(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    return (java.util.List<dss.vector.solutions.irs.InsecticideBrandUseMasterDTO>) clientRequest.getAllEnumerations(dss.vector.solutions.irs.InsecticideBrandUseDTO.CLASS);
  }
  
  public java.lang.String getName()
  {
    return this.name();
  }
}
