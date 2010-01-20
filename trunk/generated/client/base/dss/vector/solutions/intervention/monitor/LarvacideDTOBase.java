package dss.vector.solutions.intervention.monitor;

@com.terraframe.mojo.business.ClassSignature(hash = -109013733)
public abstract class LarvacideDTOBase extends com.terraframe.mojo.business.BusinessDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.Larvacide";
  private static final long serialVersionUID = -109013733;
  
  protected LarvacideDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected LarvacideDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String COMPLETIONDATE = "completionDate";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String GEODESCRIPTION = "geoDescription";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String NATUREOFCONTROL = "natureOfControl";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String PERSONCOUNT = "personCount";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String STARTDATE = "startDate";
  public static java.lang.String TEAMLEADER = "teamLeader";
  public static java.lang.String TYPE = "type";
  public java.util.Date getCompletionDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(COMPLETIONDATE));
  }
  
  public void setCompletionDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(COMPLETIONDATE, "");
    }
    else
    {
      setValue(COMPLETIONDATE, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isCompletionDateWritable()
  {
    return isWritable(COMPLETIONDATE);
  }
  
  public boolean isCompletionDateReadable()
  {
    return isReadable(COMPLETIONDATE);
  }
  
  public boolean isCompletionDateModified()
  {
    return isModified(COMPLETIONDATE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateMdDTO getCompletionDateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO(COMPLETIONDATE).getAttributeMdDTO();
  }
  
  public java.util.Date getCreateDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(CREATEDATE));
  }
  
  public boolean isCreateDateWritable()
  {
    return isWritable(CREATEDATE);
  }
  
  public boolean isCreateDateReadable()
  {
    return isReadable(CREATEDATE);
  }
  
  public boolean isCreateDateModified()
  {
    return isModified(CREATEDATE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateTimeMdDTO getCreateDateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateTimeMdDTO) getAttributeDTO(CREATEDATE).getAttributeMdDTO();
  }
  
  public com.terraframe.mojo.system.SingleActorDTO getCreatedBy()
  {
    if(getValue(CREATEDBY) == null || getValue(CREATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.SingleActorDTO.get(getRequest(), getValue(CREATEDBY));
    }
  }
  
  public boolean isCreatedByWritable()
  {
    return isWritable(CREATEDBY);
  }
  
  public boolean isCreatedByReadable()
  {
    return isReadable(CREATEDBY);
  }
  
  public boolean isCreatedByModified()
  {
    return isModified(CREATEDBY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getCreatedByMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(CREATEDBY).getAttributeMdDTO();
  }
  
  public com.terraframe.mojo.system.metadata.MdDomainDTO getEntityDomain()
  {
    if(getValue(ENTITYDOMAIN) == null || getValue(ENTITYDOMAIN).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.metadata.MdDomainDTO.get(getRequest(), getValue(ENTITYDOMAIN));
    }
  }
  
  public void setEntityDomain(com.terraframe.mojo.system.metadata.MdDomainDTO value)
  {
    if(value == null)
    {
      setValue(ENTITYDOMAIN, "");
    }
    else
    {
      setValue(ENTITYDOMAIN, value.getId());
    }
  }
  
  public boolean isEntityDomainWritable()
  {
    return isWritable(ENTITYDOMAIN);
  }
  
  public boolean isEntityDomainReadable()
  {
    return isReadable(ENTITYDOMAIN);
  }
  
  public boolean isEntityDomainModified()
  {
    return isModified(ENTITYDOMAIN);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getEntityDomainMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ENTITYDOMAIN).getAttributeMdDTO();
  }
  
  public String getGeoDescription()
  {
    return getValue(GEODESCRIPTION);
  }
  
  public void setGeoDescription(String value)
  {
    if(value == null)
    {
      setValue(GEODESCRIPTION, "");
    }
    else
    {
      setValue(GEODESCRIPTION, value);
    }
  }
  
  public boolean isGeoDescriptionWritable()
  {
    return isWritable(GEODESCRIPTION);
  }
  
  public boolean isGeoDescriptionReadable()
  {
    return isReadable(GEODESCRIPTION);
  }
  
  public boolean isGeoDescriptionModified()
  {
    return isModified(GEODESCRIPTION);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeTextMdDTO getGeoDescriptionMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeTextMdDTO) getAttributeDTO(GEODESCRIPTION).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.geo.generated.GeoEntityDTO getGeoEntity()
  {
    if(getValue(GEOENTITY) == null || getValue(GEOENTITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntityDTO.get(getRequest(), getValue(GEOENTITY));
    }
  }
  
  public void setGeoEntity(dss.vector.solutions.geo.generated.GeoEntityDTO value)
  {
    if(value == null)
    {
      setValue(GEOENTITY, "");
    }
    else
    {
      setValue(GEOENTITY, value.getId());
    }
  }
  
  public boolean isGeoEntityWritable()
  {
    return isWritable(GEOENTITY);
  }
  
  public boolean isGeoEntityReadable()
  {
    return isReadable(GEOENTITY);
  }
  
  public boolean isGeoEntityModified()
  {
    return isModified(GEOENTITY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getGeoEntityMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(GEOENTITY).getAttributeMdDTO();
  }
  
  public String getKeyName()
  {
    return getValue(KEYNAME);
  }
  
  public void setKeyName(String value)
  {
    if(value == null)
    {
      setValue(KEYNAME, "");
    }
    else
    {
      setValue(KEYNAME, value);
    }
  }
  
  public boolean isKeyNameWritable()
  {
    return isWritable(KEYNAME);
  }
  
  public boolean isKeyNameReadable()
  {
    return isReadable(KEYNAME);
  }
  
  public boolean isKeyNameModified()
  {
    return isModified(KEYNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getKeyNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(KEYNAME).getAttributeMdDTO();
  }
  
  public java.util.Date getLastUpdateDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(LASTUPDATEDATE));
  }
  
  public boolean isLastUpdateDateWritable()
  {
    return isWritable(LASTUPDATEDATE);
  }
  
  public boolean isLastUpdateDateReadable()
  {
    return isReadable(LASTUPDATEDATE);
  }
  
  public boolean isLastUpdateDateModified()
  {
    return isModified(LASTUPDATEDATE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateTimeMdDTO getLastUpdateDateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateTimeMdDTO) getAttributeDTO(LASTUPDATEDATE).getAttributeMdDTO();
  }
  
  public com.terraframe.mojo.system.SingleActorDTO getLastUpdatedBy()
  {
    if(getValue(LASTUPDATEDBY) == null || getValue(LASTUPDATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.SingleActorDTO.get(getRequest(), getValue(LASTUPDATEDBY));
    }
  }
  
  public boolean isLastUpdatedByWritable()
  {
    return isWritable(LASTUPDATEDBY);
  }
  
  public boolean isLastUpdatedByReadable()
  {
    return isReadable(LASTUPDATEDBY);
  }
  
  public boolean isLastUpdatedByModified()
  {
    return isModified(LASTUPDATEDBY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getLastUpdatedByMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(LASTUPDATEDBY).getAttributeMdDTO();
  }
  
  public com.terraframe.mojo.system.UsersDTO getLockedBy()
  {
    if(getValue(LOCKEDBY) == null || getValue(LOCKEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.UsersDTO.get(getRequest(), getValue(LOCKEDBY));
    }
  }
  
  public boolean isLockedByWritable()
  {
    return isWritable(LOCKEDBY);
  }
  
  public boolean isLockedByReadable()
  {
    return isReadable(LOCKEDBY);
  }
  
  public boolean isLockedByModified()
  {
    return isModified(LOCKEDBY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getLockedByMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(LOCKEDBY).getAttributeMdDTO();
  }
  
  public Boolean getNatureOfControl()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(NATUREOFCONTROL));
  }
  
  public void setNatureOfControl(Boolean value)
  {
    if(value == null)
    {
      setValue(NATUREOFCONTROL, "");
    }
    else
    {
      setValue(NATUREOFCONTROL, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isNatureOfControlWritable()
  {
    return isWritable(NATUREOFCONTROL);
  }
  
  public boolean isNatureOfControlReadable()
  {
    return isReadable(NATUREOFCONTROL);
  }
  
  public boolean isNatureOfControlModified()
  {
    return isModified(NATUREOFCONTROL);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getNatureOfControlMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(NATUREOFCONTROL).getAttributeMdDTO();
  }
  
  public com.terraframe.mojo.system.ActorDTO getOwner()
  {
    if(getValue(OWNER) == null || getValue(OWNER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.ActorDTO.get(getRequest(), getValue(OWNER));
    }
  }
  
  public void setOwner(com.terraframe.mojo.system.ActorDTO value)
  {
    if(value == null)
    {
      setValue(OWNER, "");
    }
    else
    {
      setValue(OWNER, value.getId());
    }
  }
  
  public boolean isOwnerWritable()
  {
    return isWritable(OWNER);
  }
  
  public boolean isOwnerReadable()
  {
    return isReadable(OWNER);
  }
  
  public boolean isOwnerModified()
  {
    return isModified(OWNER);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getOwnerMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(OWNER).getAttributeMdDTO();
  }
  
  public Integer getPersonCount()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PERSONCOUNT));
  }
  
  public void setPersonCount(Integer value)
  {
    if(value == null)
    {
      setValue(PERSONCOUNT, "");
    }
    else
    {
      setValue(PERSONCOUNT, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPersonCountWritable()
  {
    return isWritable(PERSONCOUNT);
  }
  
  public boolean isPersonCountReadable()
  {
    return isReadable(PERSONCOUNT);
  }
  
  public boolean isPersonCountModified()
  {
    return isModified(PERSONCOUNT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getPersonCountMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PERSONCOUNT).getAttributeMdDTO();
  }
  
  public Long getSeq()
  {
    return com.terraframe.mojo.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(SEQ));
  }
  
  public boolean isSeqWritable()
  {
    return isWritable(SEQ);
  }
  
  public boolean isSeqReadable()
  {
    return isReadable(SEQ);
  }
  
  public boolean isSeqModified()
  {
    return isModified(SEQ);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getSeqMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SEQ).getAttributeMdDTO();
  }
  
  public String getSiteMaster()
  {
    return getValue(SITEMASTER);
  }
  
  public boolean isSiteMasterWritable()
  {
    return isWritable(SITEMASTER);
  }
  
  public boolean isSiteMasterReadable()
  {
    return isReadable(SITEMASTER);
  }
  
  public boolean isSiteMasterModified()
  {
    return isModified(SITEMASTER);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getSiteMasterMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SITEMASTER).getAttributeMdDTO();
  }
  
  public java.util.Date getStartDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(STARTDATE));
  }
  
  public void setStartDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(STARTDATE, "");
    }
    else
    {
      setValue(STARTDATE, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isStartDateWritable()
  {
    return isWritable(STARTDATE);
  }
  
  public boolean isStartDateReadable()
  {
    return isReadable(STARTDATE);
  }
  
  public boolean isStartDateModified()
  {
    return isModified(STARTDATE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateMdDTO getStartDateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO(STARTDATE).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getTeamLeaderMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(TEAMLEADER).getAttributeMdDTO();
  }
  
  public final dss.vector.solutions.intervention.monitor.LarvacideInstanceViewDTO[] getInstanceViews()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.LarvacideDTO.CLASS, "getInstanceViews", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.LarvacideInstanceViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.LarvacideInstanceViewDTO[] getInstanceViews(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.LarvacideDTO.CLASS, "getInstanceViews", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.LarvacideInstanceViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.LarvacideInstanceDTO> getAllInstances()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.LarvacideInstanceDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.intervention.monitor.LarvacideAssociationDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.LarvacideInstanceDTO> getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.LarvacideInstanceDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.intervention.monitor.LarvacideAssociationDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.LarvacideAssociationDTO> getAllInstancesRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.LarvacideAssociationDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.intervention.monitor.LarvacideAssociationDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.LarvacideAssociationDTO> getAllInstancesRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.LarvacideAssociationDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.intervention.monitor.LarvacideAssociationDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.LarvacideAssociationDTO addInstances(dss.vector.solutions.intervention.monitor.LarvacideInstanceDTO child)
  {
    return (dss.vector.solutions.intervention.monitor.LarvacideAssociationDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.intervention.monitor.LarvacideAssociationDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.LarvacideAssociationDTO addInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.LarvacideInstanceDTO child)
  {
    return (dss.vector.solutions.intervention.monitor.LarvacideAssociationDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.intervention.monitor.LarvacideAssociationDTO.CLASS);
  }
  
  public void removeInstances(dss.vector.solutions.intervention.monitor.LarvacideAssociationDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.LarvacideAssociationDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllInstances()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.intervention.monitor.LarvacideAssociationDTO.CLASS);
  }
  
  public static void removeAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.intervention.monitor.LarvacideAssociationDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.LarvacideDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.intervention.monitor.LarvacideDTO) dto;
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
  
  public static dss.vector.solutions.intervention.monitor.LarvacideQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.intervention.monitor.LarvacideQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.intervention.monitor.Larvacide", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.intervention.monitor.LarvacideDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.LarvacideDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.LarvacideDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.intervention.monitor.LarvacideDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.LarvacideDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.LarvacideDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
