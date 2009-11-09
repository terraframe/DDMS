package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = 2114040997)
public abstract class TeamSprayStatusViewDTOBase extends dss.vector.solutions.irs.ActorSprayStatusViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.TeamSprayStatusView";
  private static final long serialVersionUID = 2114040997;
  
  protected TeamSprayStatusViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String SPRAYTEAM = "sprayTeam";
  public static java.lang.String TARGET = "target";
  public static java.lang.String TEAMLABEL = "teamLabel";
  public static java.lang.String TEAMLEADER = "teamLeader";
  public static java.lang.String TEAMSPRAYWEEK = "teamSprayWeek";
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
  
  public Integer getTarget()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET));
  }
  
  public void setTarget(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET, "");
    }
    else
    {
      setValue(TARGET, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTargetWritable()
  {
    return isWritable(TARGET);
  }
  
  public boolean isTargetReadable()
  {
    return isReadable(TARGET);
  }
  
  public boolean isTargetModified()
  {
    return isModified(TARGET);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getTargetMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET).getAttributeMdDTO();
  }
  
  public String getTeamLabel()
  {
    return getValue(TEAMLABEL);
  }
  
  public void setTeamLabel(String value)
  {
    if(value == null)
    {
      setValue(TEAMLABEL, "");
    }
    else
    {
      setValue(TEAMLABEL, value);
    }
  }
  
  public boolean isTeamLabelWritable()
  {
    return isWritable(TEAMLABEL);
  }
  
  public boolean isTeamLabelReadable()
  {
    return isReadable(TEAMLABEL);
  }
  
  public boolean isTeamLabelModified()
  {
    return isModified(TEAMLABEL);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getTeamLabelMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TEAMLABEL).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.irs.SprayOperatorDTO getTeamLeader()
  {
    if(getValue(TEAMLEADER) == null || getValue(TEAMLEADER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.SprayOperatorDTO.get(getRequest(), getValue(TEAMLEADER));
    }
  }
  
  public void setTeamLeader(dss.vector.solutions.irs.SprayOperatorDTO value)
  {
    if(value == null)
    {
      setValue(TEAMLEADER, "");
    }
    else
    {
      setValue(TEAMLEADER, value.getId());
    }
  }
  
  public boolean isTeamLeaderWritable()
  {
    return isWritable(TEAMLEADER);
  }
  
  public boolean isTeamLeaderReadable()
  {
    return isReadable(TEAMLEADER);
  }
  
  public boolean isTeamLeaderModified()
  {
    return isModified(TEAMLEADER);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getTeamLeaderMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(TEAMLEADER).getAttributeMdDTO();
  }
  
  public Integer getTeamSprayWeek()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TEAMSPRAYWEEK));
  }
  
  public void setTeamSprayWeek(Integer value)
  {
    if(value == null)
    {
      setValue(TEAMSPRAYWEEK, "");
    }
    else
    {
      setValue(TEAMSPRAYWEEK, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTeamSprayWeekWritable()
  {
    return isWritable(TEAMSPRAYWEEK);
  }
  
  public boolean isTeamSprayWeekReadable()
  {
    return isReadable(TEAMSPRAYWEEK);
  }
  
  public boolean isTeamSprayWeekModified()
  {
    return isModified(TEAMSPRAYWEEK);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getTeamSprayWeekMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TEAMSPRAYWEEK).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.irs.TeamSprayStatusViewDTO[] applyAll(com.terraframe.mojo.constants.ClientRequestIF clientRequest, dss.vector.solutions.irs.TeamSprayStatusViewDTO[] views)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.irs.TeamSprayStatusView;"};
    Object[] _parameters = new Object[]{views};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.TeamSprayStatusViewDTO.CLASS, "applyAll", _declaredTypes);
    return (dss.vector.solutions.irs.TeamSprayStatusViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.TeamSprayStatusViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.TeamSprayStatusViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static TeamSprayStatusViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (TeamSprayStatusViewDTO) dto;
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
