package dss.vector.solutions;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 *
 * @author Autogenerated by TerraFrame
 */
@com.terraframe.mojo.business.ClassSignature(hash = -1670217810)
public enum SurfacePositionDTO implements com.terraframe.mojo.business.EnumerationDTOIF, com.terraframe.mojo.generation.loader.Reloadable
{
  BOTTOM(),
  
  MIDDLE(),
  
  NOT_APPLICABLE(),
  
  RANDOM(),
  
  TOP();
  
  public final static String CLASS = "dss.vector.solutions.SurfacePosition";
  
  
  public dss.vector.solutions.SurfacePositionMasterDTO item(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    return (dss.vector.solutions.SurfacePositionMasterDTO) clientRequest.getEnumeration(dss.vector.solutions.SurfacePositionDTO.CLASS, this.name());
  }
  
  @java.lang.SuppressWarnings("unchecked")
  public static java.util.List<dss.vector.solutions.SurfacePositionMasterDTO> items(com.terraframe.mojo.constants.ClientRequestIF clientRequest, SurfacePositionDTO ... items)
  {
    java.lang.String[] itemNames = new java.lang.String[items.length];
    for(int i=0; i<items.length; i++)
    {
      itemNames[i] = items[i].name();
    }
    return (java.util.List<dss.vector.solutions.SurfacePositionMasterDTO>) clientRequest.getEnumerations(dss.vector.solutions.SurfacePositionDTO.CLASS, itemNames);
  }
  
  @java.lang.SuppressWarnings("unchecked")
  public static java.util.List<dss.vector.solutions.SurfacePositionMasterDTO> allItems(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    return (java.util.List<dss.vector.solutions.SurfacePositionMasterDTO>) clientRequest.getAllEnumerations(dss.vector.solutions.SurfacePositionDTO.CLASS);
  }
  
  public java.lang.String getName()
  {
    return this.name();
  }
}
