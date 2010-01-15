package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = 1195139679)
public abstract class FontStyleDTOBase extends com.terraframe.mojo.system.EnumerationMasterDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.FontStyle";
  private static final long serialVersionUID = 1195139679;
  
  protected FontStyleDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected FontStyleDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String PRIORITY = "priority";
  public Integer getPriority()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PRIORITY));
  }
  
  public void setPriority(Integer value)
  {
    if(value == null)
    {
      setValue(PRIORITY, "");
    }
    else
    {
      setValue(PRIORITY, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPriorityWritable()
  {
    return isWritable(PRIORITY);
  }
  
  public boolean isPriorityReadable()
  {
    return isReadable(PRIORITY);
  }
  
  public boolean isPriorityModified()
  {
    return isModified(PRIORITY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getPriorityMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PRIORITY).getAttributeMdDTO();
  }
  
  public static dss.vector.solutions.query.FontStyleDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.query.FontStyleDTO) dto;
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
  
  public static dss.vector.solutions.query.FontStyleQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.query.FontStyleQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.query.FontStyle", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.query.FontStyleDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.FontStyleDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.query.FontStyleDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.query.FontStyleDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.FontStyleDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.query.FontStyleDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
