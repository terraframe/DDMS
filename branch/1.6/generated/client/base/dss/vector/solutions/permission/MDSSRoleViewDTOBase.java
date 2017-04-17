package dss.vector.solutions.permission;

@com.runwaysdk.business.ClassSignature(hash = 403006601)
public abstract class MDSSRoleViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.permission.MDSSRoleView";
  private static final long serialVersionUID = 403006601;
  
  protected MDSSRoleViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String DISPLAYLABEL = "displayLabel";
  public static java.lang.String ID = "id";
  public static java.lang.String ROLENAME = "roleName";
  public String getConcreteId()
  {
    return getValue(CONCRETEID);
  }
  
  public void setConcreteId(String value)
  {
    if(value == null)
    {
      setValue(CONCRETEID, "");
    }
    else
    {
      setValue(CONCRETEID, value);
    }
  }
  
  public boolean isConcreteIdWritable()
  {
    return isWritable(CONCRETEID);
  }
  
  public boolean isConcreteIdReadable()
  {
    return isReadable(CONCRETEID);
  }
  
  public boolean isConcreteIdModified()
  {
    return isModified(CONCRETEID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getConcreteIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CONCRETEID).getAttributeMdDTO();
  }
  
  public String getDisplayLabel()
  {
    return getValue(DISPLAYLABEL);
  }
  
  public void setDisplayLabel(String value)
  {
    if(value == null)
    {
      setValue(DISPLAYLABEL, "");
    }
    else
    {
      setValue(DISPLAYLABEL, value);
    }
  }
  
  public boolean isDisplayLabelWritable()
  {
    return isWritable(DISPLAYLABEL);
  }
  
  public boolean isDisplayLabelReadable()
  {
    return isReadable(DISPLAYLABEL);
  }
  
  public boolean isDisplayLabelModified()
  {
    return isModified(DISPLAYLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getDisplayLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(DISPLAYLABEL).getAttributeMdDTO();
  }
  
  public String getRoleName()
  {
    return getValue(ROLENAME);
  }
  
  public void setRoleName(String value)
  {
    if(value == null)
    {
      setValue(ROLENAME, "");
    }
    else
    {
      setValue(ROLENAME, value);
    }
  }
  
  public boolean isRoleNameWritable()
  {
    return isWritable(ROLENAME);
  }
  
  public boolean isRoleNameReadable()
  {
    return isReadable(ROLENAME);
  }
  
  public boolean isRoleNameModified()
  {
    return isModified(ROLENAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getRoleNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ROLENAME).getAttributeMdDTO();
  }
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.permission.MDSSRoleViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.permission.MDSSRoleViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.permission.MDSSRoleViewQueryDTO getPage(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{sortAttribute, isAscending, pageSize, pageNumber};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.permission.MDSSRoleViewDTO.CLASS, "getPage", _declaredTypes);
    return (dss.vector.solutions.permission.MDSSRoleViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.permission.PermissionViewDTO[] getPermissions()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.permission.MDSSRoleViewDTO.CLASS, "getPermissions", _declaredTypes);
    return (dss.vector.solutions.permission.PermissionViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.permission.PermissionViewDTO[] getPermissions(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.permission.MDSSRoleViewDTO.CLASS, "getPermissions", _declaredTypes);
    return (dss.vector.solutions.permission.PermissionViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.permission.UniversalPermissionViewDTO[] getUniversalPermissions()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.permission.MDSSRoleViewDTO.CLASS, "getUniversalPermissions", _declaredTypes);
    return (dss.vector.solutions.permission.UniversalPermissionViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.permission.UniversalPermissionViewDTO[] getUniversalPermissions(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.permission.MDSSRoleViewDTO.CLASS, "getUniversalPermissions", _declaredTypes);
    return (dss.vector.solutions.permission.UniversalPermissionViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void setPermissions(dss.vector.solutions.permission.PermissionViewDTO[] permissions)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.permission.PermissionView;"};
    Object[] _parameters = new Object[]{permissions};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.permission.MDSSRoleViewDTO.CLASS, "setPermissions", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void setPermissions(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, dss.vector.solutions.permission.PermissionViewDTO[] permissions)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "[Ldss.vector.solutions.permission.PermissionView;"};
    Object[] _parameters = new Object[]{id, permissions};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.permission.MDSSRoleViewDTO.CLASS, "setPermissions", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void setUniversalPermissions(dss.vector.solutions.permission.UniversalPermissionViewDTO[] permissions)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.permission.UniversalPermissionView;"};
    Object[] _parameters = new Object[]{permissions};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.permission.MDSSRoleViewDTO.CLASS, "setUniversalPermissions", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void setUniversalPermissions(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, dss.vector.solutions.permission.UniversalPermissionViewDTO[] permissions)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "[Ldss.vector.solutions.permission.UniversalPermissionView;"};
    Object[] _parameters = new Object[]{id, permissions};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.permission.MDSSRoleViewDTO.CLASS, "setUniversalPermissions", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static MDSSRoleViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (MDSSRoleViewDTO) dto;
  }
  
  public void apply()
  {
    if(isNewInstance())
    {
      getRequest().createSessionComponent(this);
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
  
}
