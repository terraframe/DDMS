package dss.vector.solutions.general;

@com.terraframe.mojo.business.ClassSignature(hash = -1723333204)
public abstract class WeeklyThresholdDTOBase extends com.terraframe.mojo.business.RelationshipDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.WeeklyThreshold";
  private static final long serialVersionUID = -1723333204;
  
  public WeeklyThresholdDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String parentId, java.lang.String childId)
  {
    super(clientRequest, parentId, childId);
    
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given RelationshipDTO into a new DTO.
  * 
  * @param relationshipDTO The RelationshipDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected WeeklyThresholdDTOBase(com.terraframe.mojo.business.RelationshipDTO relationshipDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(relationshipDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CALCULATIONTYPE = "calculationType";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String ID = "id";
  public static java.lang.String IDENTIFICATION = "identification";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTIDENTIFICATION = "lastIdentification";
  public static java.lang.String LASTNOTIFICATION = "lastNotification";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String NOTIFICATION = "notification";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TYPE = "type";
  public dss.vector.solutions.general.ThresholdCalculationTypeDTO getCalculationType()
  {
    if(getValue(CALCULATIONTYPE) == null || getValue(CALCULATIONTYPE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.general.ThresholdCalculationTypeDTO.get(getRequest(), getValue(CALCULATIONTYPE));
    }
  }
  
  public void setCalculationType(dss.vector.solutions.general.ThresholdCalculationTypeDTO value)
  {
    if(value == null)
    {
      setValue(CALCULATIONTYPE, "");
    }
    else
    {
      setValue(CALCULATIONTYPE, value.getId());
    }
  }
  
  public boolean isCalculationTypeWritable()
  {
    return isWritable(CALCULATIONTYPE);
  }
  
  public boolean isCalculationTypeReadable()
  {
    return isReadable(CALCULATIONTYPE);
  }
  
  public boolean isCalculationTypeModified()
  {
    return isModified(CALCULATIONTYPE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getCalculationTypeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(CALCULATIONTYPE).getAttributeMdDTO();
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
  
  public Integer getIdentification()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(IDENTIFICATION));
  }
  
  public void setIdentification(Integer value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION, "");
    }
    else
    {
      setValue(IDENTIFICATION, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isIdentificationWritable()
  {
    return isWritable(IDENTIFICATION);
  }
  
  public boolean isIdentificationReadable()
  {
    return isReadable(IDENTIFICATION);
  }
  
  public boolean isIdentificationModified()
  {
    return isModified(IDENTIFICATION);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getIdentificationMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(IDENTIFICATION).getAttributeMdDTO();
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
  
  public dss.vector.solutions.general.EpiWeekDTO getLastIdentification()
  {
    if(getValue(LASTIDENTIFICATION) == null || getValue(LASTIDENTIFICATION).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.general.EpiWeekDTO.get(getRequest(), getValue(LASTIDENTIFICATION));
    }
  }
  
  public void setLastIdentification(dss.vector.solutions.general.EpiWeekDTO value)
  {
    if(value == null)
    {
      setValue(LASTIDENTIFICATION, "");
    }
    else
    {
      setValue(LASTIDENTIFICATION, value.getId());
    }
  }
  
  public boolean isLastIdentificationWritable()
  {
    return isWritable(LASTIDENTIFICATION);
  }
  
  public boolean isLastIdentificationReadable()
  {
    return isReadable(LASTIDENTIFICATION);
  }
  
  public boolean isLastIdentificationModified()
  {
    return isModified(LASTIDENTIFICATION);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getLastIdentificationMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(LASTIDENTIFICATION).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.general.EpiWeekDTO getLastNotification()
  {
    if(getValue(LASTNOTIFICATION) == null || getValue(LASTNOTIFICATION).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.general.EpiWeekDTO.get(getRequest(), getValue(LASTNOTIFICATION));
    }
  }
  
  public void setLastNotification(dss.vector.solutions.general.EpiWeekDTO value)
  {
    if(value == null)
    {
      setValue(LASTNOTIFICATION, "");
    }
    else
    {
      setValue(LASTNOTIFICATION, value.getId());
    }
  }
  
  public boolean isLastNotificationWritable()
  {
    return isWritable(LASTNOTIFICATION);
  }
  
  public boolean isLastNotificationReadable()
  {
    return isReadable(LASTNOTIFICATION);
  }
  
  public boolean isLastNotificationModified()
  {
    return isModified(LASTNOTIFICATION);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getLastNotificationMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(LASTNOTIFICATION).getAttributeMdDTO();
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
  
  public Integer getNotification()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NOTIFICATION));
  }
  
  public void setNotification(Integer value)
  {
    if(value == null)
    {
      setValue(NOTIFICATION, "");
    }
    else
    {
      setValue(NOTIFICATION, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNotificationWritable()
  {
    return isWritable(NOTIFICATION);
  }
  
  public boolean isNotificationReadable()
  {
    return isReadable(NOTIFICATION);
  }
  
  public boolean isNotificationModified()
  {
    return isModified(NOTIFICATION);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getNotificationMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NOTIFICATION).getAttributeMdDTO();
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
  
  public dss.vector.solutions.general.ThresholdDataDTO getParent()
  {
    return dss.vector.solutions.general.ThresholdDataDTO.get(getRequest(), super.getParentId());
  }
  
    public dss.vector.solutions.general.EpiWeekDTO getChild()
  {
    return dss.vector.solutions.general.EpiWeekDTO.get(getRequest(), super.getChildId());
  }
  
  public static dss.vector.solutions.general.WeeklyThresholdDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.RelationshipDTO dto = (com.terraframe.mojo.business.RelationshipDTO) clientRequest.get(id);
    
    return (dss.vector.solutions.general.WeeklyThresholdDTO) dto;
  }
  
  public static dss.vector.solutions.general.WeeklyThresholdQueryDTO parentQuery(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String parentId)
  {
    com.terraframe.mojo.business.RelationshipQueryDTO queryDTO = (com.terraframe.mojo.business.RelationshipQueryDTO) clientRequest.getQuery("dss.vector.solutions.general.WeeklyThreshold");
    queryDTO.addCondition("parent_id", "EQ", parentId);
    return (dss.vector.solutions.general.WeeklyThresholdQueryDTO) clientRequest.queryRelationships(queryDTO);
  }
  public static dss.vector.solutions.general.WeeklyThresholdQueryDTO childQuery(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String childId)
  {
    com.terraframe.mojo.business.RelationshipQueryDTO queryDTO = (com.terraframe.mojo.business.RelationshipQueryDTO) clientRequest.getQuery("dss.vector.solutions.general.WeeklyThreshold");
    queryDTO.addCondition("child_id", "EQ", childId);
    return (dss.vector.solutions.general.WeeklyThresholdQueryDTO) clientRequest.queryRelationships(queryDTO);
  }
  public void apply()
  {
    if(isNewInstance())
    {
      getRequest().createRelationship(this);
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
  
  public static dss.vector.solutions.general.WeeklyThresholdQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.general.WeeklyThresholdQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.general.WeeklyThreshold", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.general.WeeklyThresholdDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.general.WeeklyThresholdDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.general.WeeklyThresholdDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.general.WeeklyThresholdDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.general.WeeklyThresholdDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.general.WeeklyThresholdDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
