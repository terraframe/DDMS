package dss.vector.solutions.report;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 *
 * @author Autogenerated by RunwaySDK
 */
@com.runwaysdk.business.ClassSignature(hash = 1749894746)
public enum OutputFormatDTO implements com.runwaysdk.business.EnumerationDTOIF, com.runwaysdk.generation.loader.Reloadable
{
  HTML(),
  
  PDF();
  
  public final static String CLASS = "dss.vector.solutions.report.OutputFormat";
  
  
  public dss.vector.solutions.report.OutputFormatMasterDTO item(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    return (dss.vector.solutions.report.OutputFormatMasterDTO) clientRequest.getEnumeration(dss.vector.solutions.report.OutputFormatDTO.CLASS, this.name());
  }
  
  @java.lang.SuppressWarnings("unchecked")
  public static java.util.List<dss.vector.solutions.report.OutputFormatMasterDTO> items(com.runwaysdk.constants.ClientRequestIF clientRequest, OutputFormatDTO ... items)
  {
    java.lang.String[] itemNames = new java.lang.String[items.length];
    for(int i=0; i<items.length; i++)
    {
      itemNames[i] = items[i].name();
    }
    return (java.util.List<dss.vector.solutions.report.OutputFormatMasterDTO>) clientRequest.getEnumerations(dss.vector.solutions.report.OutputFormatDTO.CLASS, itemNames);
  }
  
  @java.lang.SuppressWarnings("unchecked")
  public static java.util.List<dss.vector.solutions.report.OutputFormatMasterDTO> allItems(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    return (java.util.List<dss.vector.solutions.report.OutputFormatMasterDTO>) clientRequest.getAllEnumerations(dss.vector.solutions.report.OutputFormatDTO.CLASS);
  }
  
  public java.lang.String getName()
  {
    return this.name();
  }
}
