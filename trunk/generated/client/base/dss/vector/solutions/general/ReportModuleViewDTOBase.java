package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = -1155057813)
public abstract class ReportModuleViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.ReportModuleView";
  private static final long serialVersionUID = -1155057813;
  
  protected ReportModuleViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String MODULENAME = "moduleName";
  public String getModuleName()
  {
    return getValue(MODULENAME);
  }
  
  public void setModuleName(String value)
  {
    if(value == null)
    {
      setValue(MODULENAME, "");
    }
    else
    {
      setValue(MODULENAME, value);
    }
  }
  
  public boolean isModuleNameWritable()
  {
    return isWritable(MODULENAME);
  }
  
  public boolean isModuleNameReadable()
  {
    return isReadable(MODULENAME);
  }
  
  public boolean isModuleNameModified()
  {
    return isModified(MODULENAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getModuleNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MODULENAME).getAttributeMdDTO();
  }
  
  public final void buildDatabaseViews()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.ReportModuleViewDTO.CLASS, "buildDatabaseViews", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void buildDatabaseViews(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.ReportModuleViewDTO.CLASS, "buildDatabaseViews", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.general.ReportModuleViewDTO[] getModules(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.ReportModuleViewDTO.CLASS, "getModules", _declaredTypes);
    return (dss.vector.solutions.general.ReportModuleViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.lang.Integer getProgress(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String requestId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{requestId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.ReportModuleViewDTO.CLASS, "getProgress", _declaredTypes);
    return (java.lang.Integer) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static ReportModuleViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (ReportModuleViewDTO) dto;
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
