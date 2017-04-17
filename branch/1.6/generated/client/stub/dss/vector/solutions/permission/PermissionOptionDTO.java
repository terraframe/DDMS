package dss.vector.solutions.permission;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 *
 * @author Autogenerated by RunwaySDK
 */
@com.runwaysdk.business.ClassSignature(hash = 1287075330)
public enum PermissionOptionDTO implements com.runwaysdk.business.EnumerationDTOIF, com.runwaysdk.generation.loader.Reloadable
{
  NONE(),
  
  READ(),
  
  WRITE();
  
  public final static String CLASS = "dss.vector.solutions.permission.PermissionOption";
  
  
  public dss.vector.solutions.permission.PermissionOptionMasterDTO item(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    return (dss.vector.solutions.permission.PermissionOptionMasterDTO) clientRequest.getEnumeration(dss.vector.solutions.permission.PermissionOptionDTO.CLASS, this.name());
  }
  
  @java.lang.SuppressWarnings("unchecked")
  public static java.util.List<dss.vector.solutions.permission.PermissionOptionMasterDTO> items(com.runwaysdk.constants.ClientRequestIF clientRequest, PermissionOptionDTO ... items)
  {
    java.lang.String[] itemNames = new java.lang.String[items.length];
    for(int i=0; i<items.length; i++)
    {
      itemNames[i] = items[i].name();
    }
    return (java.util.List<dss.vector.solutions.permission.PermissionOptionMasterDTO>) clientRequest.getEnumerations(dss.vector.solutions.permission.PermissionOptionDTO.CLASS, itemNames);
  }
  
  @java.lang.SuppressWarnings("unchecked")
  public static java.util.List<dss.vector.solutions.permission.PermissionOptionMasterDTO> allItems(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    return (java.util.List<dss.vector.solutions.permission.PermissionOptionMasterDTO>) clientRequest.getAllEnumerations(dss.vector.solutions.permission.PermissionOptionDTO.CLASS);
  }
  
  public java.lang.String getName()
  {
    return this.name();
  }
}
