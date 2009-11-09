package dss.vector.solutions.entomology;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 *
 * @author Autogenerated by TerraFrame
 */
@com.terraframe.mojo.business.ClassSignature(hash = -1160506685)
public enum SexDTO implements com.terraframe.mojo.business.EnumerationDTOIF, com.terraframe.mojo.generation.loader.Reloadable
{
  FEMALE(),
  
  MALE(),
  
  UNKNOWN();
  
  public final static String CLASS = "dss.vector.solutions.entomology.Sex";
  
  
  public dss.vector.solutions.entomology.SexMasterDTO item(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    return (dss.vector.solutions.entomology.SexMasterDTO) clientRequest.getEnumeration("dss.vector.solutions.entomology.Sex", this.name());
  }
  
  @java.lang.SuppressWarnings("unchecked")
  public static java.util.List<dss.vector.solutions.entomology.SexMasterDTO> items(com.terraframe.mojo.constants.ClientRequestIF clientRequest, SexDTO ... items)
  {
    java.lang.String[] itemNames = new java.lang.String[items.length];
    for(int i=0; i<items.length; i++)
    {
      itemNames[i] = items[i].name();
    }
    return (java.util.List<dss.vector.solutions.entomology.SexMasterDTO>) clientRequest.getEnumerations("dss.vector.solutions.entomology.Sex", itemNames);
  }
  
  @java.lang.SuppressWarnings("unchecked")
  public static java.util.List<dss.vector.solutions.entomology.SexMasterDTO> allItems(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    return (java.util.List<dss.vector.solutions.entomology.SexMasterDTO>) clientRequest.getAllEnumerations("dss.vector.solutions.entomology.Sex");
  }
  
  public java.lang.String getName()
  {
    return this.name();
  }
}
