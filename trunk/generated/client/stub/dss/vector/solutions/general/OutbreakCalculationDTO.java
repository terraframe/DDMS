package dss.vector.solutions.general;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 *
 * @author Autogenerated by TerraFrame
 */
@com.terraframe.mojo.business.ClassSignature(hash = -793929825)
public enum OutbreakCalculationDTO implements com.terraframe.mojo.business.EnumerationDTOIF, com.terraframe.mojo.generation.loader.Reloadable
{
  EPI_WEEK(),
  
  SLIDING_WEEK();
  
  public final static String CLASS = "dss.vector.solutions.general.OutbreakCalculation";
  
  
  public dss.vector.solutions.general.OutbreakCalculationMasterDTO item(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    return (dss.vector.solutions.general.OutbreakCalculationMasterDTO) clientRequest.getEnumeration(dss.vector.solutions.general.OutbreakCalculationDTO.CLASS, this.name());
  }
  
  @java.lang.SuppressWarnings("unchecked")
  public static java.util.List<dss.vector.solutions.general.OutbreakCalculationMasterDTO> items(com.terraframe.mojo.constants.ClientRequestIF clientRequest, OutbreakCalculationDTO ... items)
  {
    java.lang.String[] itemNames = new java.lang.String[items.length];
    for(int i=0; i<items.length; i++)
    {
      itemNames[i] = items[i].name();
    }
    return (java.util.List<dss.vector.solutions.general.OutbreakCalculationMasterDTO>) clientRequest.getEnumerations(dss.vector.solutions.general.OutbreakCalculationDTO.CLASS, itemNames);
  }
  
  @java.lang.SuppressWarnings("unchecked")
  public static java.util.List<dss.vector.solutions.general.OutbreakCalculationMasterDTO> allItems(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    return (java.util.List<dss.vector.solutions.general.OutbreakCalculationMasterDTO>) clientRequest.getAllEnumerations(dss.vector.solutions.general.OutbreakCalculationDTO.CLASS);
  }
  
  public java.lang.String getName()
  {
    return this.name();
  }
}
