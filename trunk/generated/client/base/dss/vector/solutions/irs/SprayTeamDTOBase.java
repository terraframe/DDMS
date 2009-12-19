package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = -2102195184)
public abstract class SprayTeamDTOBase extends dss.vector.solutions.irs.TargeterDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.SprayTeam";
  private static final long serialVersionUID = -2102195184;
  
  protected SprayTeamDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected SprayTeamDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String SPRAYZONE = "sprayZone";
  public static java.lang.String TEAMID = "teamId";
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
  
  public final void create(java.lang.String geoId, java.lang.String leaderId, java.lang.String[] operatorIds)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "[Ljava.lang.String;"};
    Object[] _parameters = new Object[]{geoId, leaderId, operatorIds};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.SprayTeamDTO.CLASS, "create", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void create(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String geoId, java.lang.String leaderId, java.lang.String[] operatorIds)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.String", "[Ljava.lang.String;"};
    Object[] _parameters = new Object[]{id, geoId, leaderId, operatorIds};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.SprayTeamDTO.CLASS, "create", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void edit(java.lang.String geoId, java.lang.String leaderId, java.lang.String[] operatorIds, java.lang.String[] removedIds)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "[Ljava.lang.String;", "[Ljava.lang.String;"};
    Object[] _parameters = new Object[]{geoId, leaderId, operatorIds, removedIds};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.SprayTeamDTO.CLASS, "edit", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void edit(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String geoId, java.lang.String leaderId, java.lang.String[] operatorIds, java.lang.String[] removedIds)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.String", "[Ljava.lang.String;", "[Ljava.lang.String;"};
    Object[] _parameters = new Object[]{id, geoId, leaderId, operatorIds, removedIds};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.SprayTeamDTO.CLASS, "edit", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.irs.SprayTeamDTO[] findByLocation(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.SprayTeamDTO.CLASS, "findByLocation", _declaredTypes);
    return (dss.vector.solutions.irs.SprayTeamDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.irs.SprayOperatorViewDTO[] getTeamMemberViews()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.SprayTeamDTO.CLASS, "getTeamMemberViews", _declaredTypes);
    return (dss.vector.solutions.irs.SprayOperatorViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.irs.SprayOperatorViewDTO[] getTeamMemberViews(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.SprayTeamDTO.CLASS, "getTeamMemberViews", _declaredTypes);
    return (dss.vector.solutions.irs.SprayOperatorViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.irs.SprayOperatorDTO[] getTeamMembers()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.SprayTeamDTO.CLASS, "getTeamMembers", _declaredTypes);
    return (dss.vector.solutions.irs.SprayOperatorDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.irs.SprayOperatorDTO[] getTeamMembers(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.SprayTeamDTO.CLASS, "getTeamMembers", _declaredTypes);
    return (dss.vector.solutions.irs.SprayOperatorDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.irs.SprayOperatorDTO> getAllSprayTeamMembers()
  {
    return (java.util.List<? extends dss.vector.solutions.irs.SprayOperatorDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.irs.InTeamDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.irs.SprayOperatorDTO> getAllSprayTeamMembers(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.irs.SprayOperatorDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.irs.InTeamDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.irs.InTeamDTO> getAllSprayTeamMembersRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.irs.InTeamDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.irs.InTeamDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.irs.InTeamDTO> getAllSprayTeamMembersRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.irs.InTeamDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.irs.InTeamDTO.CLASS);
  }
  
  public dss.vector.solutions.irs.InTeamDTO addSprayTeamMembers(dss.vector.solutions.irs.SprayOperatorDTO child)
  {
    return (dss.vector.solutions.irs.InTeamDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.irs.InTeamDTO.CLASS);
  }
  
  public static dss.vector.solutions.irs.InTeamDTO addSprayTeamMembers(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.irs.SprayOperatorDTO child)
  {
    return (dss.vector.solutions.irs.InTeamDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.irs.InTeamDTO.CLASS);
  }
  
  public void removeSprayTeamMembers(dss.vector.solutions.irs.InTeamDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeSprayTeamMembers(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.irs.InTeamDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllSprayTeamMembers()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.irs.InTeamDTO.CLASS);
  }
  
  public static void removeAllSprayTeamMembers(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.irs.InTeamDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.irs.SprayLeaderDTO> getAllTeamLeader()
  {
    return (java.util.List<? extends dss.vector.solutions.irs.SprayLeaderDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.irs.LeadTeamDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.irs.SprayLeaderDTO> getAllTeamLeader(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.irs.SprayLeaderDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.irs.LeadTeamDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.irs.LeadTeamDTO> getAllTeamLeaderRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.irs.LeadTeamDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.irs.LeadTeamDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.irs.LeadTeamDTO> getAllTeamLeaderRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.irs.LeadTeamDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.irs.LeadTeamDTO.CLASS);
  }
  
  public dss.vector.solutions.irs.LeadTeamDTO addTeamLeader(dss.vector.solutions.irs.SprayLeaderDTO child)
  {
    return (dss.vector.solutions.irs.LeadTeamDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.irs.LeadTeamDTO.CLASS);
  }
  
  public static dss.vector.solutions.irs.LeadTeamDTO addTeamLeader(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.irs.SprayLeaderDTO child)
  {
    return (dss.vector.solutions.irs.LeadTeamDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.irs.LeadTeamDTO.CLASS);
  }
  
  public void removeTeamLeader(dss.vector.solutions.irs.LeadTeamDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeTeamLeader(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.irs.LeadTeamDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllTeamLeader()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.irs.LeadTeamDTO.CLASS);
  }
  
  public static void removeAllTeamLeader(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.irs.LeadTeamDTO.CLASS);
  }
  
  public static dss.vector.solutions.irs.SprayTeamDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.irs.SprayTeamDTO) dto;
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
  
  public static dss.vector.solutions.irs.SprayTeamQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.irs.SprayTeamQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.irs.SprayTeam", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.irs.SprayTeamDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.SprayTeamDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.irs.SprayTeamDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.irs.SprayTeamDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.SprayTeamDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.irs.SprayTeamDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
