package dss.vector.solutions.kaleidoscope.data.etl;

@com.runwaysdk.business.ClassSignature(hash = 25188730)
public abstract class TargetFieldBasicBindingDTOBase extends dss.vector.solutions.kaleidoscope.data.etl.TargetFieldBindingDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.kaleidoscope.data.etl.TargetFieldBasicBinding";
  private static final long serialVersionUID = 25188730;
  
  protected TargetFieldBasicBindingDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected TargetFieldBasicBindingDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String SOURCEATTRIBUTE = "sourceAttribute";
  public com.runwaysdk.system.metadata.MdAttributeDTO getSourceAttribute()
  {
    if(getValue(SOURCEATTRIBUTE) == null || getValue(SOURCEATTRIBUTE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.metadata.MdAttributeDTO.get(getRequest(), getValue(SOURCEATTRIBUTE));
    }
  }
  
  public String getSourceAttributeId()
  {
    return getValue(SOURCEATTRIBUTE);
  }
  
  public void setSourceAttribute(com.runwaysdk.system.metadata.MdAttributeDTO value)
  {
    if(value == null)
    {
      setValue(SOURCEATTRIBUTE, "");
    }
    else
    {
      setValue(SOURCEATTRIBUTE, value.getId());
    }
  }
  
  public boolean isSourceAttributeWritable()
  {
    return isWritable(SOURCEATTRIBUTE);
  }
  
  public boolean isSourceAttributeReadable()
  {
    return isReadable(SOURCEATTRIBUTE);
  }
  
  public boolean isSourceAttributeModified()
  {
    return isModified(SOURCEATTRIBUTE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getSourceAttributeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SOURCEATTRIBUTE).getAttributeMdDTO();
  }
  
  public static dss.vector.solutions.kaleidoscope.data.etl.TargetFieldBasicBindingDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.kaleidoscope.data.etl.TargetFieldBasicBindingDTO) dto;
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
  
  public static dss.vector.solutions.kaleidoscope.data.etl.TargetFieldBasicBindingQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.kaleidoscope.data.etl.TargetFieldBasicBindingQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldBasicBindingDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.kaleidoscope.data.etl.TargetFieldBasicBindingDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldBasicBindingDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.kaleidoscope.data.etl.TargetFieldBasicBindingDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.kaleidoscope.data.etl.TargetFieldBasicBindingDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.kaleidoscope.data.etl.TargetFieldBasicBindingDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.kaleidoscope.data.etl.TargetFieldBasicBindingDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
