package dss.vector.solutions.query;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 *
 * @author Autogenerated by TerraFrame
 */
@com.terraframe.mojo.business.ClassSignature(hash = 87872226)
public enum FontStylesDTO implements com.terraframe.mojo.business.EnumerationDTOIF, com.terraframe.mojo.generation.loader.Reloadable
{
  BOLD(),
  
  ITALIC(),
  
  NORMAL();
  
  public final static String CLASS = "dss.vector.solutions.query.FontStyles";
  
  
  public dss.vector.solutions.query.FontStyleDTO item(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    return (dss.vector.solutions.query.FontStyleDTO) clientRequest.getEnumeration(dss.vector.solutions.query.FontStylesDTO.CLASS, this.name());
  }
  
  @java.lang.SuppressWarnings("unchecked")
  public static java.util.List<dss.vector.solutions.query.FontStyleDTO> items(com.terraframe.mojo.constants.ClientRequestIF clientRequest, FontStylesDTO ... items)
  {
    java.lang.String[] itemNames = new java.lang.String[items.length];
    for(int i=0; i<items.length; i++)
    {
      itemNames[i] = items[i].name();
    }
    return (java.util.List<dss.vector.solutions.query.FontStyleDTO>) clientRequest.getEnumerations(dss.vector.solutions.query.FontStylesDTO.CLASS, itemNames);
  }
  
  @java.lang.SuppressWarnings("unchecked")
  public static java.util.List<dss.vector.solutions.query.FontStyleDTO> allItems(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    return (java.util.List<dss.vector.solutions.query.FontStyleDTO>) clientRequest.getAllEnumerations(dss.vector.solutions.query.FontStylesDTO.CLASS);
  }
  
  public java.lang.String getName()
  {
    return this.name();
  }
}
