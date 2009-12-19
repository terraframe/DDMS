package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = 607115131)
public abstract class SprayOperatorViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.SprayOperatorView";
  private static final long serialVersionUID = 607115131;
  
  protected SprayOperatorViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ACTORID = "actorId";
  public static java.lang.String FIRSTNAME = "firstName";
  public static java.lang.String ID = "id";
  public static java.lang.String ISASSIGNED = "isAssigned";
  public static java.lang.String LASTNAME = "lastName";
  public static java.lang.String OPERATORID = "operatorId";
  public static java.lang.String TEAMID = "teamId";
  public String getActorId()
  {
    return getValue(ACTORID);
  }
  
  public void setActorId(String value)
  {
    if(value == null)
    {
      setValue(ACTORID, "");
    }
    else
    {
      setValue(ACTORID, value);
    }
  }
  
  public boolean isActorIdWritable()
  {
    return isWritable(ACTORID);
  }
  
  public boolean isActorIdReadable()
  {
    return isReadable(ACTORID);
  }
  
  public boolean isActorIdModified()
  {
    return isModified(ACTORID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getActorIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ACTORID).getAttributeMdDTO();
  }
  
  public String getFirstName()
  {
    return getValue(FIRSTNAME);
  }
  
  public void setFirstName(String value)
  {
    if(value == null)
    {
      setValue(FIRSTNAME, "");
    }
    else
    {
      setValue(FIRSTNAME, value);
    }
  }
  
  public boolean isFirstNameWritable()
  {
    return isWritable(FIRSTNAME);
  }
  
  public boolean isFirstNameReadable()
  {
    return isReadable(FIRSTNAME);
  }
  
  public boolean isFirstNameModified()
  {
    return isModified(FIRSTNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getFirstNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FIRSTNAME).getAttributeMdDTO();
  }
  
  public Boolean getIsAssigned()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISASSIGNED));
  }
  
  public void setIsAssigned(Boolean value)
  {
    if(value == null)
    {
      setValue(ISASSIGNED, "");
    }
    else
    {
      setValue(ISASSIGNED, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsAssignedWritable()
  {
    return isWritable(ISASSIGNED);
  }
  
  public boolean isIsAssignedReadable()
  {
    return isReadable(ISASSIGNED);
  }
  
  public boolean isIsAssignedModified()
  {
    return isModified(ISASSIGNED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getIsAssignedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISASSIGNED).getAttributeMdDTO();
  }
  
  public String getLastName()
  {
    return getValue(LASTNAME);
  }
  
  public void setLastName(String value)
  {
    if(value == null)
    {
      setValue(LASTNAME, "");
    }
    else
    {
      setValue(LASTNAME, value);
    }
  }
  
  public boolean isLastNameWritable()
  {
    return isWritable(LASTNAME);
  }
  
  public boolean isLastNameReadable()
  {
    return isReadable(LASTNAME);
  }
  
  public boolean isLastNameModified()
  {
    return isModified(LASTNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getLastNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(LASTNAME).getAttributeMdDTO();
  }
  
  public String getOperatorId()
  {
    return getValue(OPERATORID);
  }
  
  public void setOperatorId(String value)
  {
    if(value == null)
    {
      setValue(OPERATORID, "");
    }
    else
    {
      setValue(OPERATORID, value);
    }
  }
  
  public boolean isOperatorIdWritable()
  {
    return isWritable(OPERATORID);
  }
  
  public boolean isOperatorIdReadable()
  {
    return isReadable(OPERATORID);
  }
  
  public boolean isOperatorIdModified()
  {
    return isModified(OPERATORID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getOperatorIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(OPERATORID).getAttributeMdDTO();
  }
  
  public String getTeamId()
  {
    return getValue(TEAMID);
  }
  
  public void setTeamId(String value)
  {
    if(value == null)
    {
      setValue(TEAMID, "");
    }
    else
    {
      setValue(TEAMID, value);
    }
  }
  
  public boolean isTeamIdWritable()
  {
    return isWritable(TEAMID);
  }
  
  public boolean isTeamIdReadable()
  {
    return isReadable(TEAMID);
  }
  
  public boolean isTeamIdModified()
  {
    return isModified(TEAMID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getTeamIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TEAMID).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.irs.SprayOperatorViewDTO[] getAll(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.SprayOperatorViewDTO.CLASS, "getAll", _declaredTypes);
    return (dss.vector.solutions.irs.SprayOperatorViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.irs.SprayOperatorViewDTO[] getAllForLocation(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String geoId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{geoId};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.SprayOperatorViewDTO.CLASS, "getAllForLocation", _declaredTypes);
    return (dss.vector.solutions.irs.SprayOperatorViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.irs.SprayOperatorViewDTO[] getAllForTeam(com.terraframe.mojo.constants.ClientRequestIF clientRequest, dss.vector.solutions.irs.SprayTeamDTO sprayTeam)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.irs.SprayTeam"};
    Object[] _parameters = new Object[]{sprayTeam};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.SprayOperatorViewDTO.CLASS, "getAllForTeam", _declaredTypes);
    return (dss.vector.solutions.irs.SprayOperatorViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static SprayOperatorViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (SprayOperatorViewDTO) dto;
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
