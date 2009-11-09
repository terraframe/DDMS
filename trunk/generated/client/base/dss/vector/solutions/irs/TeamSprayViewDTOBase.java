package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = -1318905346)
public abstract class TeamSprayViewDTOBase extends dss.vector.solutions.irs.ActorSprayViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.TeamSprayView";
  private static final long serialVersionUID = -1318905346;
  
  protected TeamSprayViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String SPRAYTEAM = "sprayTeam";
  public dss.vector.solutions.irs.SprayTeamDTO getSprayTeam()
  {
    if(getValue(SPRAYTEAM) == null || getValue(SPRAYTEAM).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.SprayTeamDTO.get(getRequest(), getValue(SPRAYTEAM));
    }
  }
  
  public void setSprayTeam(dss.vector.solutions.irs.SprayTeamDTO value)
  {
    if(value == null)
    {
      setValue(SPRAYTEAM, "");
    }
    else
    {
      setValue(SPRAYTEAM, value.getId());
    }
  }
  
  public boolean isSprayTeamWritable()
  {
    return isWritable(SPRAYTEAM);
  }
  
  public boolean isSprayTeamReadable()
  {
    return isReadable(SPRAYTEAM);
  }
  
  public boolean isSprayTeamModified()
  {
    return isModified(SPRAYTEAM);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getSprayTeamMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SPRAYTEAM).getAttributeMdDTO();
  }
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.TeamSprayViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.TeamSprayViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.irs.OperatorSprayStatusViewDTO[] getStatus()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.TeamSprayViewDTO.CLASS, "getStatus", _declaredTypes);
    return (dss.vector.solutions.irs.OperatorSprayStatusViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.irs.OperatorSprayStatusViewDTO[] getStatus(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.TeamSprayViewDTO.CLASS, "getStatus", _declaredTypes);
    return (dss.vector.solutions.irs.OperatorSprayStatusViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.irs.TeamSprayViewDTO searchBySprayData(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String geoId, java.util.Date sprayDate, dss.vector.solutions.irs.SprayMethodDTO sprayMethod, dss.vector.solutions.irs.InsecticideBrandDTO brand, java.lang.String teamId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.util.Date", "dss.vector.solutions.irs.SprayMethod", "dss.vector.solutions.irs.InsecticideBrand", "java.lang.String"};
    Object[] _parameters = new Object[]{geoId, sprayDate, sprayMethod, brand, teamId};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.TeamSprayViewDTO.CLASS, "searchBySprayData", _declaredTypes);
    return (dss.vector.solutions.irs.TeamSprayViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static TeamSprayViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (TeamSprayViewDTO) dto;
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
