package dss.vector.solutions.etl.dhis2;

@com.runwaysdk.business.ClassSignature(hash = -1571450265)
public abstract class DHIS2HTTPConfigurationDTOBase extends dss.vector.solutions.etl.dhis2.DHIS2AbstractConfigurationDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.etl.dhis2.DHIS2HTTPConfiguration";
  private static final long serialVersionUID = -1571450265;
  
  protected DHIS2HTTPConfigurationDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected DHIS2HTTPConfigurationDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String PAZZWORD = "pazzword";
  public static java.lang.String USERNAME = "username";
  public String getPazzword()
  {
    return getValue(PAZZWORD);
  }
  
  public void setPazzword(String value)
  {
    if(value == null)
    {
      setValue(PAZZWORD, "");
    }
    else
    {
      setValue(PAZZWORD, value);
    }
  }
  
  public boolean isPazzwordWritable()
  {
    return isWritable(PAZZWORD);
  }
  
  public boolean isPazzwordReadable()
  {
    return isReadable(PAZZWORD);
  }
  
  public boolean isPazzwordModified()
  {
    return isModified(PAZZWORD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getPazzwordMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PAZZWORD).getAttributeMdDTO();
  }
  
  public String getUsername()
  {
    return getValue(USERNAME);
  }
  
  public void setUsername(String value)
  {
    if(value == null)
    {
      setValue(USERNAME, "");
    }
    else
    {
      setValue(USERNAME, value);
    }
  }
  
  public boolean isUsernameWritable()
  {
    return isWritable(USERNAME);
  }
  
  public boolean isUsernameReadable()
  {
    return isReadable(USERNAME);
  }
  
  public boolean isUsernameModified()
  {
    return isModified(USERNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getUsernameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(USERNAME).getAttributeMdDTO();
  }
  
  public final void connect()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.etl.dhis2.DHIS2HTTPConfigurationDTO.CLASS, "connect", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void connect(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.etl.dhis2.DHIS2HTTPConfigurationDTO.CLASS, "connect", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.etl.dhis2.DHIS2HTTPConfigurationDTO getInstance(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.etl.dhis2.DHIS2HTTPConfigurationDTO.CLASS, "getInstance", _declaredTypes);
    return (dss.vector.solutions.etl.dhis2.DHIS2HTTPConfigurationDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static dss.vector.solutions.etl.dhis2.DHIS2HTTPConfigurationDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.etl.dhis2.DHIS2HTTPConfigurationDTO) dto;
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
  
  public static dss.vector.solutions.etl.dhis2.DHIS2HTTPConfigurationQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.etl.dhis2.DHIS2HTTPConfigurationQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.etl.dhis2.DHIS2HTTPConfigurationDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.etl.dhis2.DHIS2HTTPConfigurationDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.etl.dhis2.DHIS2HTTPConfigurationDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.etl.dhis2.DHIS2HTTPConfigurationDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.etl.dhis2.DHIS2HTTPConfigurationDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.etl.dhis2.DHIS2HTTPConfigurationDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.etl.dhis2.DHIS2HTTPConfigurationDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
