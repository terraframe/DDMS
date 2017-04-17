package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = -739462922)
public abstract class SystemAlertTypeMasterDTOBase extends com.runwaysdk.system.EnumerationMasterDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.SystemAlertTypeMaster";
  private static final long serialVersionUID = -739462922;
  
  protected SystemAlertTypeMasterDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected SystemAlertTypeMasterDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String EMAILBODYTEXT = "emailBodyText";
  public static java.lang.String EMAILTEMPLATEVARIABLES = "emailTemplateVariables";
  public dss.vector.solutions.general.SystemAlertTypeMasterEmailBodyTextDTO getEmailBodyText()
  {
    return (dss.vector.solutions.general.SystemAlertTypeMasterEmailBodyTextDTO) this.getAttributeStructDTO(EMAILBODYTEXT).getStructDTO();
  }
  
  public boolean isEmailBodyTextWritable()
  {
    return isWritable(EMAILBODYTEXT);
  }
  
  public boolean isEmailBodyTextReadable()
  {
    return isReadable(EMAILBODYTEXT);
  }
  
  public boolean isEmailBodyTextModified()
  {
    return isModified(EMAILBODYTEXT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeLocalTextMdDTO getEmailBodyTextMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeLocalTextMdDTO) getAttributeDTO(EMAILBODYTEXT).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariablesDTO getEmailTemplateVariables()
  {
    return (dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariablesDTO) this.getAttributeStructDTO(EMAILTEMPLATEVARIABLES).getStructDTO();
  }
  
  public boolean isEmailTemplateVariablesWritable()
  {
    return isWritable(EMAILTEMPLATEVARIABLES);
  }
  
  public boolean isEmailTemplateVariablesReadable()
  {
    return isReadable(EMAILTEMPLATEVARIABLES);
  }
  
  public boolean isEmailTemplateVariablesModified()
  {
    return isModified(EMAILTEMPLATEVARIABLES);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeLocalTextMdDTO getEmailTemplateVariablesMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeLocalTextMdDTO) getAttributeDTO(EMAILTEMPLATEVARIABLES).getAttributeMdDTO();
  }
  
  public static dss.vector.solutions.general.SystemAlertTypeMasterDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.general.SystemAlertTypeMasterDTO) dto;
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
  
  public static dss.vector.solutions.general.SystemAlertTypeMasterQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.general.SystemAlertTypeMasterQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.general.SystemAlertTypeMasterDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.general.SystemAlertTypeMasterDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.SystemAlertTypeMasterDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.general.SystemAlertTypeMasterDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.general.SystemAlertTypeMasterDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.SystemAlertTypeMasterDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.general.SystemAlertTypeMasterDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
