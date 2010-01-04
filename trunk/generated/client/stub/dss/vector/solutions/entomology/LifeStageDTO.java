package dss.vector.solutions.entomology;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 *
 * @author Autogenerated by TerraFrame
 */
@com.terraframe.mojo.business.ClassSignature(hash = -700533546)
public enum LifeStageDTO implements com.terraframe.mojo.business.EnumerationDTOIF, com.terraframe.mojo.generation.loader.Reloadable
{
  ADULT(),
  
  EGG(),
  
  IMMATURE();
  
  public final static String CLASS = "dss.vector.solutions.entomology.LifeStage";
  
  
  public dss.vector.solutions.entomology.LifeStageMasterDTO item(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    return (dss.vector.solutions.entomology.LifeStageMasterDTO) clientRequest.getEnumeration("dss.vector.solutions.entomology.LifeStage", this.name());
  }
  
  @java.lang.SuppressWarnings("unchecked")
  public static java.util.List<dss.vector.solutions.entomology.LifeStageMasterDTO> items(com.terraframe.mojo.constants.ClientRequestIF clientRequest, LifeStageDTO ... items)
  {
    java.lang.String[] itemNames = new java.lang.String[items.length];
    for(int i=0; i<items.length; i++)
    {
      itemNames[i] = items[i].name();
    }
    return (java.util.List<dss.vector.solutions.entomology.LifeStageMasterDTO>) clientRequest.getEnumerations("dss.vector.solutions.entomology.LifeStage", itemNames);
  }
  
  @java.lang.SuppressWarnings("unchecked")
  public static java.util.List<dss.vector.solutions.entomology.LifeStageMasterDTO> allItems(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    return (java.util.List<dss.vector.solutions.entomology.LifeStageMasterDTO>) clientRequest.getAllEnumerations("dss.vector.solutions.entomology.LifeStage");
  }
  
  public java.lang.String getName()
  {
    return this.name();
  }
}
