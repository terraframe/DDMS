package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = -2104278202)
public abstract class TeamMemberDTOBase extends dss.vector.solutions.irs.TargeterDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.TeamMember";
  private static final long serialVersionUID = -2104278202;
  
  protected TeamMemberDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected TeamMemberDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ISSPRAYLEADER = "isSprayLeader";
  public static java.lang.String ISSPRAYOPERATOR = "isSprayOperator";
  public static java.lang.String MEMBERID = "memberId";
  public static java.lang.String PERSON = "person";
  public Boolean getIsSprayLeader()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISSPRAYLEADER));
  }
  
  public void setIsSprayLeader(Boolean value)
  {
    if(value == null)
    {
      setValue(ISSPRAYLEADER, "");
    }
    else
    {
      setValue(ISSPRAYLEADER, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsSprayLeaderWritable()
  {
    return isWritable(ISSPRAYLEADER);
  }
  
  public boolean isIsSprayLeaderReadable()
  {
    return isReadable(ISSPRAYLEADER);
  }
  
  public boolean isIsSprayLeaderModified()
  {
    return isModified(ISSPRAYLEADER);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getIsSprayLeaderMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISSPRAYLEADER).getAttributeMdDTO();
  }
  
  public Boolean getIsSprayOperator()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISSPRAYOPERATOR));
  }
  
  public void setIsSprayOperator(Boolean value)
  {
    if(value == null)
    {
      setValue(ISSPRAYOPERATOR, "");
    }
    else
    {
      setValue(ISSPRAYOPERATOR, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsSprayOperatorWritable()
  {
    return isWritable(ISSPRAYOPERATOR);
  }
  
  public boolean isIsSprayOperatorReadable()
  {
    return isReadable(ISSPRAYOPERATOR);
  }
  
  public boolean isIsSprayOperatorModified()
  {
    return isModified(ISSPRAYOPERATOR);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getIsSprayOperatorMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISSPRAYOPERATOR).getAttributeMdDTO();
  }
  
  public String getMemberId()
  {
    return getValue(MEMBERID);
  }
  
  public void setMemberId(String value)
  {
    if(value == null)
    {
      setValue(MEMBERID, "");
    }
    else
    {
      setValue(MEMBERID, value);
    }
  }
  
  public boolean isMemberIdWritable()
  {
    return isWritable(MEMBERID);
  }
  
  public boolean isMemberIdReadable()
  {
    return isReadable(MEMBERID);
  }
  
  public boolean isMemberIdModified()
  {
    return isModified(MEMBERID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getMemberIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MEMBERID).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.PersonDTO getPerson()
  {
    if(getValue(PERSON) == null || getValue(PERSON).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.PersonDTO.get(getRequest(), getValue(PERSON));
    }
  }
  
  public void setPerson(dss.vector.solutions.PersonDTO value)
  {
    if(value == null)
    {
      setValue(PERSON, "");
    }
    else
    {
      setValue(PERSON, value.getId());
    }
  }
  
  public boolean isPersonWritable()
  {
    return isWritable(PERSON);
  }
  
  public boolean isPersonReadable()
  {
    return isReadable(PERSON);
  }
  
  public boolean isPersonModified()
  {
    return isModified(PERSON);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getPersonMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(PERSON).getAttributeMdDTO();
  }
  
  public final dss.vector.solutions.irs.TeamMemberViewDTO getView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.TeamMemberDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.irs.TeamMemberViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.irs.TeamMemberViewDTO getView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.TeamMemberDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.irs.TeamMemberViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.terraframe.mojo.business.ValueQueryDTO searchForLeader(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String search)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{search};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.TeamMemberDTO.CLASS, "searchForLeader", _declaredTypes);
    return (com.terraframe.mojo.business.ValueQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.irs.SprayTeamDTO> getAllLeadsTeam()
  {
    return (java.util.List<? extends dss.vector.solutions.irs.SprayTeamDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.irs.LeadTeamDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.irs.SprayTeamDTO> getAllLeadsTeam(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.irs.SprayTeamDTO>) clientRequestIF.getParents(id, dss.vector.solutions.irs.LeadTeamDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.irs.LeadTeamDTO> getAllLeadsTeamRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.irs.LeadTeamDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.irs.LeadTeamDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.irs.LeadTeamDTO> getAllLeadsTeamRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.irs.LeadTeamDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.irs.LeadTeamDTO.CLASS);
  }
  
  public dss.vector.solutions.irs.LeadTeamDTO addLeadsTeam(dss.vector.solutions.irs.SprayTeamDTO parent)
  {
    return (dss.vector.solutions.irs.LeadTeamDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.irs.LeadTeamDTO.CLASS);
  }
  
  public static dss.vector.solutions.irs.LeadTeamDTO addLeadsTeam(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.irs.SprayTeamDTO parent)
  {
    return (dss.vector.solutions.irs.LeadTeamDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.irs.LeadTeamDTO.CLASS);
  }
  
  public void removeLeadsTeam(dss.vector.solutions.irs.LeadTeamDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeLeadsTeam(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.irs.LeadTeamDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllLeadsTeam()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.irs.LeadTeamDTO.CLASS);
  }
  
  public static void removeAllLeadsTeam(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.irs.LeadTeamDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.irs.SprayTeamDTO> getAllSprayTeam()
  {
    return (java.util.List<? extends dss.vector.solutions.irs.SprayTeamDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.irs.InTeamDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.irs.SprayTeamDTO> getAllSprayTeam(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.irs.SprayTeamDTO>) clientRequestIF.getParents(id, dss.vector.solutions.irs.InTeamDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.irs.InTeamDTO> getAllSprayTeamRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.irs.InTeamDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.irs.InTeamDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.irs.InTeamDTO> getAllSprayTeamRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.irs.InTeamDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.irs.InTeamDTO.CLASS);
  }
  
  public dss.vector.solutions.irs.InTeamDTO addSprayTeam(dss.vector.solutions.irs.SprayTeamDTO parent)
  {
    return (dss.vector.solutions.irs.InTeamDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.irs.InTeamDTO.CLASS);
  }
  
  public static dss.vector.solutions.irs.InTeamDTO addSprayTeam(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.irs.SprayTeamDTO parent)
  {
    return (dss.vector.solutions.irs.InTeamDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.irs.InTeamDTO.CLASS);
  }
  
  public void removeSprayTeam(dss.vector.solutions.irs.InTeamDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeSprayTeam(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.irs.InTeamDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllSprayTeam()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.irs.InTeamDTO.CLASS);
  }
  
  public static void removeAllSprayTeam(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.irs.InTeamDTO.CLASS);
  }
  
  public static dss.vector.solutions.irs.TeamMemberDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.irs.TeamMemberDTO) dto;
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
  
  public static dss.vector.solutions.irs.TeamMemberQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.irs.TeamMemberQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.irs.TeamMember", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.irs.TeamMemberDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.TeamMemberDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.irs.TeamMemberDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.irs.TeamMemberDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.TeamMemberDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.irs.TeamMemberDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
