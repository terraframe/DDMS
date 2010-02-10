package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = -243102153)
public abstract class SprayTeamViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.SprayTeamView";
  private static final long serialVersionUID = -243102153;
  
  protected SprayTeamViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ASSIGNEDOPERATORS = "assignedOperators";
  public static java.lang.String AVAILABLEOPERATORS = "availableOperators";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String CURRENTOPERATORS = "currentOperators";
  public static java.lang.String ID = "id";
  public static java.lang.String SPRAYZONE = "sprayZone";
  public static java.lang.String TEAMID = "teamId";
  public static java.lang.String TEAMLEADER = "teamLeader";
  public String getAssignedOperators()
  {
    return getValue(ASSIGNEDOPERATORS);
  }
  
  public void setAssignedOperators(String value)
  {
    if(value == null)
    {
      setValue(ASSIGNEDOPERATORS, "");
    }
    else
    {
      setValue(ASSIGNEDOPERATORS, value);
    }
  }
  
  public boolean isAssignedOperatorsWritable()
  {
    return isWritable(ASSIGNEDOPERATORS);
  }
  
  public boolean isAssignedOperatorsReadable()
  {
    return isReadable(ASSIGNEDOPERATORS);
  }
  
  public boolean isAssignedOperatorsModified()
  {
    return isModified(ASSIGNEDOPERATORS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getAssignedOperatorsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ASSIGNEDOPERATORS).getAttributeMdDTO();
  }
  
  public String getAvailableOperators()
  {
    return getValue(AVAILABLEOPERATORS);
  }
  
  public void setAvailableOperators(String value)
  {
    if(value == null)
    {
      setValue(AVAILABLEOPERATORS, "");
    }
    else
    {
      setValue(AVAILABLEOPERATORS, value);
    }
  }
  
  public boolean isAvailableOperatorsWritable()
  {
    return isWritable(AVAILABLEOPERATORS);
  }
  
  public boolean isAvailableOperatorsReadable()
  {
    return isReadable(AVAILABLEOPERATORS);
  }
  
  public boolean isAvailableOperatorsModified()
  {
    return isModified(AVAILABLEOPERATORS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getAvailableOperatorsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(AVAILABLEOPERATORS).getAttributeMdDTO();
  }
  
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getConcreteIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CONCRETEID).getAttributeMdDTO();
  }
  
  public String getCurrentOperators()
  {
    return getValue(CURRENTOPERATORS);
  }
  
  public void setCurrentOperators(String value)
  {
    if(value == null)
    {
      setValue(CURRENTOPERATORS, "");
    }
    else
    {
      setValue(CURRENTOPERATORS, value);
    }
  }
  
  public boolean isCurrentOperatorsWritable()
  {
    return isWritable(CURRENTOPERATORS);
  }
  
  public boolean isCurrentOperatorsReadable()
  {
    return isReadable(CURRENTOPERATORS);
  }
  
  public boolean isCurrentOperatorsModified()
  {
    return isModified(CURRENTOPERATORS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getCurrentOperatorsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CURRENTOPERATORS).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.geo.generated.SprayZoneDTO getSprayZone()
  {
    if(getValue(SPRAYZONE) == null || getValue(SPRAYZONE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.SprayZoneDTO.get(getRequest(), getValue(SPRAYZONE));
    }
  }
  
  public void setSprayZone(dss.vector.solutions.geo.generated.SprayZoneDTO value)
  {
    if(value == null)
    {
      setValue(SPRAYZONE, "");
    }
    else
    {
      setValue(SPRAYZONE, value.getId());
    }
  }
  
  public boolean isSprayZoneWritable()
  {
    return isWritable(SPRAYZONE);
  }
  
  public boolean isSprayZoneReadable()
  {
    return isReadable(SPRAYZONE);
  }
  
  public boolean isSprayZoneModified()
  {
    return isModified(SPRAYZONE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getSprayZoneMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SPRAYZONE).getAttributeMdDTO();
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
  
  public String getTeamLeader()
  {
    return getValue(TEAMLEADER);
  }
  
  public void setTeamLeader(String value)
  {
    if(value == null)
    {
      setValue(TEAMLEADER, "");
    }
    else
    {
      setValue(TEAMLEADER, value);
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getTeamLeaderMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TEAMLEADER).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.irs.SprayTeamViewQueryDTO getPage(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{sortAttribute, isAscending, pageSize, pageNumber};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.SprayTeamViewDTO.CLASS, "getPage", _declaredTypes);
    return (dss.vector.solutions.irs.SprayTeamViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static SprayTeamViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (SprayTeamViewDTO) dto;
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
