package dss.vector.solutions;

@com.terraframe.mojo.business.ClassSignature(hash = 2139543509)
public abstract class PolygonStyleDTOBase extends dss.vector.solutions.query.GeometryStyleDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.PolygonStyle";
  private static final long serialVersionUID = 2139543509;
  
  protected PolygonStyleDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected PolygonStyleDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String FILL = "fill";
  public String getFill()
  {
    return getValue(FILL);
  }
  
  public void setFill(String value)
  {
    if(value == null)
    {
      setValue(FILL, "");
    }
    else
    {
      setValue(FILL, value);
    }
  }
  
  public boolean isFillWritable()
  {
    return isWritable(FILL);
  }
  
  public boolean isFillReadable()
  {
    return isReadable(FILL);
  }
  
  public boolean isFillModified()
  {
    return isModified(FILL);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getFillMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FILL).getAttributeMdDTO();
  }
  
  public static dss.vector.solutions.PolygonStyleDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.PolygonStyleDTO) dto;
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
  
  public static dss.vector.solutions.PolygonStyleQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.PolygonStyleQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.PolygonStyle", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.PolygonStyleDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.PolygonStyleDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.PolygonStyleDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.PolygonStyleDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.PolygonStyleDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.PolygonStyleDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
