package dss.vector.solutions;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 *
 * @author Autogenerated by TerraFrame
 */
@com.terraframe.mojo.business.ClassSignature(hash = -1399396517)
public enum RefusedResponseDTO implements com.terraframe.mojo.business.EnumerationDTOIF, com.terraframe.mojo.generation.loader.Reloadable
{
  NO(),
  
  REFUSED(),
  
  YES();
  
  public final static String CLASS = "dss.vector.solutions.RefusedResponse";
  
  
  public dss.vector.solutions.ResponseMasterDTO item(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    return (dss.vector.solutions.ResponseMasterDTO) clientRequest.getEnumeration("dss.vector.solutions.RefusedResponse", this.name());
  }
  
  @java.lang.SuppressWarnings("unchecked")
  public static java.util.List<dss.vector.solutions.ResponseMasterDTO> items(com.terraframe.mojo.constants.ClientRequestIF clientRequest, RefusedResponseDTO ... items)
  {
    java.lang.String[] itemNames = new java.lang.String[items.length];
    for(int i=0; i<items.length; i++)
    {
      itemNames[i] = items[i].name();
    }
    return (java.util.List<dss.vector.solutions.ResponseMasterDTO>) clientRequest.getEnumerations("dss.vector.solutions.RefusedResponse", itemNames);
  }
  
  @java.lang.SuppressWarnings("unchecked")
  public static java.util.List<dss.vector.solutions.ResponseMasterDTO> allItems(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    return (java.util.List<dss.vector.solutions.ResponseMasterDTO>) clientRequest.getAllEnumerations("dss.vector.solutions.RefusedResponse");
  }
  
  public java.lang.String getName()
  {
    return this.name();
  }
}
