package dss.vector.solutions.odk;

@com.runwaysdk.business.ClassSignature(hash = 1218077045)
public abstract class MobileDataUploadJobDTOBase extends com.runwaysdk.system.scheduler.ExecutableJobDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.odk.MobileDataUploadJob";
  private static final long serialVersionUID = 1218077045;
  
  protected MobileDataUploadJobDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected MobileDataUploadJobDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String FORMTYPE = "formType";
  public String getFormType()
  {
    return getValue(FORMTYPE);
  }
  
  public void setFormType(String value)
  {
    if(value == null)
    {
      setValue(FORMTYPE, "");
    }
    else
    {
      setValue(FORMTYPE, value);
    }
  }
  
  public boolean isFormTypeWritable()
  {
    return isWritable(FORMTYPE);
  }
  
  public boolean isFormTypeReadable()
  {
    return isReadable(FORMTYPE);
  }
  
  public boolean isFormTypeModified()
  {
    return isModified(FORMTYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getFormTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FORMTYPE).getAttributeMdDTO();
  }
  
  public static dss.vector.solutions.odk.MobileDataUploadJobDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.odk.MobileDataUploadJobDTO) dto;
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
  
  public static dss.vector.solutions.odk.MobileDataUploadJobQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.odk.MobileDataUploadJobQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.odk.MobileDataUploadJobDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.odk.MobileDataUploadJobDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.odk.MobileDataUploadJobDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.odk.MobileDataUploadJobDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.odk.MobileDataUploadJobDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.odk.MobileDataUploadJobDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.odk.MobileDataUploadJobDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
