package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = -1069559079)
public abstract class TeamSprayDTOBase extends dss.vector.solutions.irs.AbstractSprayDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.TeamSpray";
  private static final long serialVersionUID = -1069559079;
  
  protected TeamSprayDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected TeamSprayDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String BRANDFORINDEX = "brandForIndex";
  public static java.lang.String DISEASE = "disease";
  public static java.lang.String GEOENTITYFORINDEX = "geoEntityForIndex";
  public static java.lang.String SPRAYDATEFORINDEX = "sprayDateForIndex";
  public static java.lang.String SPRAYMETHODFORINDEX = "sprayMethodForIndex";
  public static java.lang.String SPRAYTEAM = "sprayTeam";
  public static java.lang.String SUPERVISOR = "supervisor";
  public static java.lang.String TARGET = "target";
  public static java.lang.String TEAMLEADER = "teamLeader";
  public dss.vector.solutions.irs.InsecticideBrandDTO getBrandForIndex()
  {
    if(getValue(BRANDFORINDEX) == null || getValue(BRANDFORINDEX).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.InsecticideBrandDTO.get(getRequest(), getValue(BRANDFORINDEX));
    }
  }
  
  public String getBrandForIndexId()
  {
    return getValue(BRANDFORINDEX);
  }
  
  public void setBrandForIndex(dss.vector.solutions.irs.InsecticideBrandDTO value)
  {
    if(value == null)
    {
      setValue(BRANDFORINDEX, "");
    }
    else
    {
      setValue(BRANDFORINDEX, value.getId());
    }
  }
  
  public boolean isBrandForIndexWritable()
  {
    return isWritable(BRANDFORINDEX);
  }
  
  public boolean isBrandForIndexReadable()
  {
    return isReadable(BRANDFORINDEX);
  }
  
  public boolean isBrandForIndexModified()
  {
    return isModified(BRANDFORINDEX);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getBrandForIndexMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(BRANDFORINDEX).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.general.DiseaseDTO getDisease()
  {
    if(getValue(DISEASE) == null || getValue(DISEASE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.general.DiseaseDTO.get(getRequest(), getValue(DISEASE));
    }
  }
  
  public String getDiseaseId()
  {
    return getValue(DISEASE);
  }
  
  public void setDisease(dss.vector.solutions.general.DiseaseDTO value)
  {
    if(value == null)
    {
      setValue(DISEASE, "");
    }
    else
    {
      setValue(DISEASE, value.getId());
    }
  }
  
  public boolean isDiseaseWritable()
  {
    return isWritable(DISEASE);
  }
  
  public boolean isDiseaseReadable()
  {
    return isReadable(DISEASE);
  }
  
  public boolean isDiseaseModified()
  {
    return isModified(DISEASE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getDiseaseMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DISEASE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.geo.generated.GeoEntityDTO getGeoEntityForIndex()
  {
    if(getValue(GEOENTITYFORINDEX) == null || getValue(GEOENTITYFORINDEX).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntityDTO.get(getRequest(), getValue(GEOENTITYFORINDEX));
    }
  }
  
  public String getGeoEntityForIndexId()
  {
    return getValue(GEOENTITYFORINDEX);
  }
  
  public void setGeoEntityForIndex(dss.vector.solutions.geo.generated.GeoEntityDTO value)
  {
    if(value == null)
    {
      setValue(GEOENTITYFORINDEX, "");
    }
    else
    {
      setValue(GEOENTITYFORINDEX, value.getId());
    }
  }
  
  public boolean isGeoEntityForIndexWritable()
  {
    return isWritable(GEOENTITYFORINDEX);
  }
  
  public boolean isGeoEntityForIndexReadable()
  {
    return isReadable(GEOENTITYFORINDEX);
  }
  
  public boolean isGeoEntityForIndexModified()
  {
    return isModified(GEOENTITYFORINDEX);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getGeoEntityForIndexMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(GEOENTITYFORINDEX).getAttributeMdDTO();
  }
  
  public java.util.Date getSprayDateForIndex()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(SPRAYDATEFORINDEX));
  }
  
  public void setSprayDateForIndex(java.util.Date value)
  {
    if(value == null)
    {
      setValue(SPRAYDATEFORINDEX, "");
    }
    else
    {
      setValue(SPRAYDATEFORINDEX, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isSprayDateForIndexWritable()
  {
    return isWritable(SPRAYDATEFORINDEX);
  }
  
  public boolean isSprayDateForIndexReadable()
  {
    return isReadable(SPRAYDATEFORINDEX);
  }
  
  public boolean isSprayDateForIndexModified()
  {
    return isModified(SPRAYDATEFORINDEX);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getSprayDateForIndexMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(SPRAYDATEFORINDEX).getAttributeMdDTO();
  }
  
  public String getSprayMethodForIndex()
  {
    return getValue(SPRAYMETHODFORINDEX);
  }
  
  public void setSprayMethodForIndex(String value)
  {
    if(value == null)
    {
      setValue(SPRAYMETHODFORINDEX, "");
    }
    else
    {
      setValue(SPRAYMETHODFORINDEX, value);
    }
  }
  
  public boolean isSprayMethodForIndexWritable()
  {
    return isWritable(SPRAYMETHODFORINDEX);
  }
  
  public boolean isSprayMethodForIndexReadable()
  {
    return isReadable(SPRAYMETHODFORINDEX);
  }
  
  public boolean isSprayMethodForIndexModified()
  {
    return isModified(SPRAYMETHODFORINDEX);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSprayMethodForIndexMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SPRAYMETHODFORINDEX).getAttributeMdDTO();
  }
  
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
  
  public String getSprayTeamId()
  {
    return getValue(SPRAYTEAM);
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getSprayTeamMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SPRAYTEAM).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.irs.SupervisorDTO getSupervisor()
  {
    if(getValue(SUPERVISOR) == null || getValue(SUPERVISOR).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.SupervisorDTO.get(getRequest(), getValue(SUPERVISOR));
    }
  }
  
  public String getSupervisorId()
  {
    return getValue(SUPERVISOR);
  }
  
  public void setSupervisor(dss.vector.solutions.irs.SupervisorDTO value)
  {
    if(value == null)
    {
      setValue(SUPERVISOR, "");
    }
    else
    {
      setValue(SUPERVISOR, value.getId());
    }
  }
  
  public boolean isSupervisorWritable()
  {
    return isWritable(SUPERVISOR);
  }
  
  public boolean isSupervisorReadable()
  {
    return isReadable(SUPERVISOR);
  }
  
  public boolean isSupervisorModified()
  {
    return isModified(SUPERVISOR);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getSupervisorMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SUPERVISOR).getAttributeMdDTO();
  }
  
  public Integer getTarget()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET));
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
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTargetMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.irs.TeamMemberDTO getTeamLeader()
  {
    if(getValue(TEAMLEADER) == null || getValue(TEAMLEADER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.TeamMemberDTO.get(getRequest(), getValue(TEAMLEADER));
    }
  }
  
  public String getTeamLeaderId()
  {
    return getValue(TEAMLEADER);
  }
  
  public void setTeamLeader(dss.vector.solutions.irs.TeamMemberDTO value)
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getTeamLeaderMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(TEAMLEADER).getAttributeMdDTO();
  }
  
  public final dss.vector.solutions.irs.OperatorSprayStatusViewDTO[] getStatusViews()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.TeamSprayDTO.CLASS, "getStatusViews", _declaredTypes);
    return (dss.vector.solutions.irs.OperatorSprayStatusViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.irs.OperatorSprayStatusViewDTO[] getStatusViews(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.TeamSprayDTO.CLASS, "getStatusViews", _declaredTypes);
    return (dss.vector.solutions.irs.OperatorSprayStatusViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.irs.TeamSprayViewDTO getView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.TeamSprayDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.irs.TeamSprayViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.irs.TeamSprayViewDTO getView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.TeamSprayDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.irs.TeamSprayViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.irs.TeamSprayViewDTO lockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.TeamSprayDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.irs.TeamSprayViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.irs.TeamSprayViewDTO lockView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.TeamSprayDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.irs.TeamSprayViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.irs.TeamSprayViewDTO unlockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.TeamSprayDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.irs.TeamSprayViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.irs.TeamSprayViewDTO unlockView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.TeamSprayDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.irs.TeamSprayViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static dss.vector.solutions.irs.TeamSprayDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.irs.TeamSprayDTO) dto;
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
  
  public static dss.vector.solutions.irs.TeamSprayQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.irs.TeamSprayQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.irs.TeamSprayDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.irs.TeamSprayDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.TeamSprayDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.irs.TeamSprayDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.irs.TeamSprayDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.TeamSprayDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.irs.TeamSprayDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
