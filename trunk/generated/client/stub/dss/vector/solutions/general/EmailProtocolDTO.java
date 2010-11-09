package dss.vector.solutions.general;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 *
 * @author Autogenerated by RunwaySDK
 */
@com.runwaysdk.business.ClassSignature(hash = 67730332)
public enum EmailProtocolDTO implements com.runwaysdk.business.EnumerationDTOIF, com.runwaysdk.generation.loader.Reloadable
{
  SMTP(),
  
  SMTPS(),
  
  SMTP_TLS();
  
  public final static String CLASS = "dss.vector.solutions.general.EmailProtocol";
  
  
  public dss.vector.solutions.general.EmailProtocolMasterDTO item(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    return (dss.vector.solutions.general.EmailProtocolMasterDTO) clientRequest.getEnumeration(dss.vector.solutions.general.EmailProtocolDTO.CLASS, this.name());
  }
  
  @java.lang.SuppressWarnings("unchecked")
  public static java.util.List<dss.vector.solutions.general.EmailProtocolMasterDTO> items(com.runwaysdk.constants.ClientRequestIF clientRequest, EmailProtocolDTO ... items)
  {
    java.lang.String[] itemNames = new java.lang.String[items.length];
    for(int i=0; i<items.length; i++)
    {
      itemNames[i] = items[i].name();
    }
    return (java.util.List<dss.vector.solutions.general.EmailProtocolMasterDTO>) clientRequest.getEnumerations(dss.vector.solutions.general.EmailProtocolDTO.CLASS, itemNames);
  }
  
  @java.lang.SuppressWarnings("unchecked")
  public static java.util.List<dss.vector.solutions.general.EmailProtocolMasterDTO> allItems(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    return (java.util.List<dss.vector.solutions.general.EmailProtocolMasterDTO>) clientRequest.getAllEnumerations(dss.vector.solutions.general.EmailProtocolDTO.CLASS);
  }
  
  public java.lang.String getName()
  {
    return this.name();
  }
}
