package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = 1625210613)
public abstract class SupervisorViewDTOBase extends dss.vector.solutions.PersonViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.SupervisorView";
  private static final long serialVersionUID = 1625210613;
  
  protected SupervisorViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String SUPERVISORID = "supervisorId";
  public String getSupervisorId()
  {
    return getValue(SUPERVISORID);
  }
  
  public void setSupervisorId(String value)
  {
    if(value == null)
    {
      setValue(SUPERVISORID, "");
    }
    else
    {
      setValue(SUPERVISORID, value);
    }
  }
  
  public boolean isSupervisorIdWritable()
  {
    return isWritable(SUPERVISORID);
  }
  
  public boolean isSupervisorIdReadable()
  {
    return isReadable(SUPERVISORID);
  }
  
  public boolean isSupervisorIdModified()
  {
    return isModified(SUPERVISORID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSupervisorIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SUPERVISORID).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.irs.SupervisorViewDTO[] getSupervisors(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.SupervisorViewDTO.CLASS, "getSupervisors", _declaredTypes);
    return (dss.vector.solutions.irs.SupervisorViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static SupervisorViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (SupervisorViewDTO) dto;
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
