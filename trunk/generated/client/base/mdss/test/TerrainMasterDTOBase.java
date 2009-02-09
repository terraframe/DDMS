package mdss.test;

public abstract class TerrainMasterDTOBase extends com.terraframe.mojo.system.EnumerationMasterDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "mdss.test.TerrainMaster";
  private static final long serialVersionUID = 1234203356356L;
  
  protected TerrainMasterDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected TerrainMasterDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static mdss.test.TerrainMasterDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (mdss.test.TerrainMasterDTO) dto;
  }
  
  public void apply()
  {
    if(isNewInstance())
    {
      getRequest().createBusiness(this);
    }
    else
    {
      getRequest().update(this);
    }
  }
  public void delete()
  {
    getRequest().delete(this.getId());
  }
  
  public static mdss.test.TerrainMasterQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (mdss.test.TerrainMasterQueryDTO) clientRequest.getAllInstances("mdss.test.TerrainMaster", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static mdss.test.TerrainMasterDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(mdss.test.TerrainMasterDTO.CLASS, "lock", _declaredTypes);
    return (mdss.test.TerrainMasterDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static mdss.test.TerrainMasterDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(mdss.test.TerrainMasterDTO.CLASS, "unlock", _declaredTypes);
    return (mdss.test.TerrainMasterDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
