package dss.vector.solutions.irs;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 *
 * @author Autogenerated by TerraFrame
 */
@com.terraframe.mojo.business.ClassSignature(hash = 391976451)
public enum TargetUnitDTO implements com.terraframe.mojo.business.EnumerationDTOIF, com.terraframe.mojo.generation.loader.Reloadable
{
  HOUSEHOLD(),
  
  ROOM(),
  
  STRUCTURE();
  
  public final static String CLASS = "dss.vector.solutions.irs.TargetUnit";
  
  
  public dss.vector.solutions.irs.TargetUnitMasterDTO item(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    return (dss.vector.solutions.irs.TargetUnitMasterDTO) clientRequest.getEnumeration("dss.vector.solutions.irs.TargetUnit", this.name());
  }
  
  @java.lang.SuppressWarnings("unchecked")
  public static java.util.List<dss.vector.solutions.irs.TargetUnitMasterDTO> items(com.terraframe.mojo.constants.ClientRequestIF clientRequest, TargetUnitDTO ... items)
  {
    java.lang.String[] itemNames = new java.lang.String[items.length];
    for(int i=0; i<items.length; i++)
    {
      itemNames[i] = items[i].name();
    }
    return (java.util.List<dss.vector.solutions.irs.TargetUnitMasterDTO>) clientRequest.getEnumerations("dss.vector.solutions.irs.TargetUnit", itemNames);
  }
  
  @java.lang.SuppressWarnings("unchecked")
  public static java.util.List<dss.vector.solutions.irs.TargetUnitMasterDTO> allItems(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    return (java.util.List<dss.vector.solutions.irs.TargetUnitMasterDTO>) clientRequest.getAllEnumerations("dss.vector.solutions.irs.TargetUnit");
  }
  
  public java.lang.String getName()
  {
    return this.name();
  }
}
