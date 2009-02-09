package mdss.test;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 *
 * @author Autogenerated by TerraFrame
 */
public enum TerrainDTO implements com.terraframe.mojo.business.EnumerationDTOIF, com.terraframe.mojo.generation.loader.Reloadable
{
  FIXED_TRAP(),
  
  NON_SENTINEL_SITE(),
  
  PERMANENT_WATER_BODY(),
  
  SENTINEL_SITE();
  
  public final static String CLASS = "mdss.test.Terrain";
  
  
  public mdss.test.TerrainMasterDTO item(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    return (mdss.test.TerrainMasterDTO) clientRequest.getEnumeration("mdss.test.Terrain", this.name());
  }
  
  @java.lang.SuppressWarnings("unchecked")
  public static java.util.List<mdss.test.TerrainMasterDTO> items(com.terraframe.mojo.constants.ClientRequestIF clientRequest, TerrainDTO ... items)
  {
    java.lang.String[] itemNames = new java.lang.String[items.length];
    for(int i=0; i<items.length; i++)
    {
      itemNames[i] = items[i].name();
    }
    return (java.util.List<mdss.test.TerrainMasterDTO>) clientRequest.getEnumerations("mdss.test.Terrain", itemNames);
  }
  
  @java.lang.SuppressWarnings("unchecked")
  public static java.util.List<mdss.test.TerrainMasterDTO> allItems(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    return (java.util.List<mdss.test.TerrainMasterDTO>) clientRequest.getAllEnumerations("mdss.test.Terrain");
  }
  
  public java.lang.String getName()
  {
    return this.name();
  }
}
