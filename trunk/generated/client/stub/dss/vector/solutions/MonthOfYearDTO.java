package dss.vector.solutions;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 *
 * @author Autogenerated by TerraFrame
 */
@com.terraframe.mojo.business.ClassSignature(hash = -1089539843)
public enum MonthOfYearDTO implements com.terraframe.mojo.business.EnumerationDTOIF, com.terraframe.mojo.generation.loader.Reloadable
{
  APRIL(),
  
  AUGUST(),
  
  DECEMBER(),
  
  FEBRUARY(),
  
  JANUARY(),
  
  JULY(),
  
  JUNE(),
  
  MARCH(),
  
  MAY(),
  
  NOVEMBER(),
  
  OCTOBER(),
  
  SEPTEMBER();
  
  public final static String CLASS = "dss.vector.solutions.MonthOfYear";
  
  
  public dss.vector.solutions.MonthOfYearMasterDTO item(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    return (dss.vector.solutions.MonthOfYearMasterDTO) clientRequest.getEnumeration("dss.vector.solutions.MonthOfYear", this.name());
  }
  
  @java.lang.SuppressWarnings("unchecked")
  public static java.util.List<dss.vector.solutions.MonthOfYearMasterDTO> items(com.terraframe.mojo.constants.ClientRequestIF clientRequest, MonthOfYearDTO ... items)
  {
    java.lang.String[] itemNames = new java.lang.String[items.length];
    for(int i=0; i<items.length; i++)
    {
      itemNames[i] = items[i].name();
    }
    return (java.util.List<dss.vector.solutions.MonthOfYearMasterDTO>) clientRequest.getEnumerations("dss.vector.solutions.MonthOfYear", itemNames);
  }
  
  @java.lang.SuppressWarnings("unchecked")
  public static java.util.List<dss.vector.solutions.MonthOfYearMasterDTO> allItems(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    return (java.util.List<dss.vector.solutions.MonthOfYearMasterDTO>) clientRequest.getAllEnumerations("dss.vector.solutions.MonthOfYear");
  }
  
  public java.lang.String getName()
  {
    return this.name();
  }
}
