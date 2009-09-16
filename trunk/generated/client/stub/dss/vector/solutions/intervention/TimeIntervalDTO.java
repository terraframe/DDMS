package dss.vector.solutions.intervention;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 *
 * @author Autogenerated by TerraFrame
 */
public enum TimeIntervalDTO implements com.terraframe.mojo.business.EnumerationDTOIF, com.terraframe.mojo.generation.loader.Reloadable
{
  PER_MONTH(),
  
  PER_WEEK(),
  
  PER_YEAR();
  
  public final static String CLASS = "dss.vector.solutions.intervention.TimeInterval";
  
  
  public dss.vector.solutions.intervention.TimeIntervalMasterDTO item(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    return (dss.vector.solutions.intervention.TimeIntervalMasterDTO) clientRequest.getEnumeration("dss.vector.solutions.intervention.TimeInterval", this.name());
  }
  
  @java.lang.SuppressWarnings("unchecked")
  public static java.util.List<dss.vector.solutions.intervention.TimeIntervalMasterDTO> items(com.terraframe.mojo.constants.ClientRequestIF clientRequest, TimeIntervalDTO ... items)
  {
    java.lang.String[] itemNames = new java.lang.String[items.length];
    for(int i=0; i<items.length; i++)
    {
      itemNames[i] = items[i].name();
    }
    return (java.util.List<dss.vector.solutions.intervention.TimeIntervalMasterDTO>) clientRequest.getEnumerations("dss.vector.solutions.intervention.TimeInterval", itemNames);
  }
  
  @java.lang.SuppressWarnings("unchecked")
  public static java.util.List<dss.vector.solutions.intervention.TimeIntervalMasterDTO> allItems(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    return (java.util.List<dss.vector.solutions.intervention.TimeIntervalMasterDTO>) clientRequest.getAllEnumerations("dss.vector.solutions.intervention.TimeInterval");
  }
  
  public java.lang.String getName()
  {
    return this.name();
  }
}
