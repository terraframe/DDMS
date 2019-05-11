package dss.vector.solutions.odk;

@com.runwaysdk.business.ClassSignature(hash = -655346670)
public abstract class ODKUserDTOBase extends dss.vector.solutions.MDSSUserDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.odk.ODKUser";
  private static final long serialVersionUID = -655346670;
  
  protected ODKUserDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected ODKUserDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ISSYNCED = "isSynced";
  public static java.lang.String ODKPASSWORD = "odkPassword";
  public static java.lang.String ODKUSERNAME = "odkUsername";
  public Boolean getIsSynced()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISSYNCED));
  }
  
  public void setIsSynced(Boolean value)
  {
    if(value == null)
    {
      setValue(ISSYNCED, "");
    }
    else
    {
      setValue(ISSYNCED, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsSyncedWritable()
  {
    return isWritable(ISSYNCED);
  }
  
  public boolean isIsSyncedReadable()
  {
    return isReadable(ISSYNCED);
  }
  
  public boolean isIsSyncedModified()
  {
    return isModified(ISSYNCED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getIsSyncedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISSYNCED).getAttributeMdDTO();
  }
  
  public String getOdkPassword()
  {
    return getValue(ODKPASSWORD);
  }
  
  public void setOdkPassword(String value)
  {
    if(value == null)
    {
      setValue(ODKPASSWORD, "");
    }
    else
    {
      setValue(ODKPASSWORD, value);
    }
  }
  
  public boolean isOdkPasswordWritable()
  {
    return isWritable(ODKPASSWORD);
  }
  
  public boolean isOdkPasswordReadable()
  {
    return isReadable(ODKPASSWORD);
  }
  
  public boolean isOdkPasswordModified()
  {
    return isModified(ODKPASSWORD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getOdkPasswordMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(ODKPASSWORD).getAttributeMdDTO();
  }
  
  public String getOdkUsername()
  {
    return getValue(ODKUSERNAME);
  }
  
  public void setOdkUsername(String value)
  {
    if(value == null)
    {
      setValue(ODKUSERNAME, "");
    }
    else
    {
      setValue(ODKUSERNAME, value);
    }
  }
  
  public boolean isOdkUsernameWritable()
  {
    return isWritable(ODKUSERNAME);
  }
  
  public boolean isOdkUsernameReadable()
  {
    return isReadable(ODKUSERNAME);
  }
  
  public boolean isOdkUsernameModified()
  {
    return isModified(ODKUSERNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getOdkUsernameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(ODKUSERNAME).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.odk.ODKUserDTO getUser(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.odk.ODKUserDTO.CLASS, "getUser", _declaredTypes);
    return (dss.vector.solutions.odk.ODKUserDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static dss.vector.solutions.odk.ODKUserDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.odk.ODKUserDTO) dto;
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
  
  public static dss.vector.solutions.odk.ODKUserQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.odk.ODKUserQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.odk.ODKUserDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.odk.ODKUserDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.odk.ODKUserDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.odk.ODKUserDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.odk.ODKUserDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.odk.ODKUserDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.odk.ODKUserDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
