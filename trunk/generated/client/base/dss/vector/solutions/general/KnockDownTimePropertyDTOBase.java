package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = 758480707)
public abstract class KnockDownTimePropertyDTOBase extends com.runwaysdk.business.BusinessDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.KnockDownTimeProperty";
  private static final long serialVersionUID = 758480707;
  
  protected KnockDownTimePropertyDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected KnockDownTimePropertyDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String ID = "id";
  public static java.lang.String INSECTICIDE = "insecticide";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String LOWERPERCENT = "lowerPercent";
  public static java.lang.String LOWERTIME = "lowerTime";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TYPE = "type";
  public static java.lang.String UPPERPERCENT = "upperPercent";
  public static java.lang.String UPPERTIME = "upperTime";
  public java.util.Date getCreateDate()
  {
    return com.runwaysdk.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(CREATEDATE));
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
  
  public final com.runwaysdk.transport.metadata.AttributeDateTimeMdDTO getCreateDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateTimeMdDTO) getAttributeDTO(CREATEDATE).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.SingleActorDTO getCreatedBy()
  {
    if(getValue(CREATEDBY) == null || getValue(CREATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.SingleActorDTO.get(getRequest(), getValue(CREATEDBY));
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getCreatedByMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(CREATEDBY).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.metadata.MdDomainDTO getEntityDomain()
  {
    if(getValue(ENTITYDOMAIN) == null || getValue(ENTITYDOMAIN).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.metadata.MdDomainDTO.get(getRequest(), getValue(ENTITYDOMAIN));
    }
  }
  
  public void setEntityDomain(com.runwaysdk.system.metadata.MdDomainDTO value)
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getEntityDomainMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ENTITYDOMAIN).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.general.InsecticideDTO getInsecticide()
  {
    if(getValue(INSECTICIDE) == null || getValue(INSECTICIDE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.general.InsecticideDTO.get(getRequest(), getValue(INSECTICIDE));
    }
  }
  
  public void setInsecticide(dss.vector.solutions.general.InsecticideDTO value)
  {
    if(value == null)
    {
      setValue(INSECTICIDE, "");
    }
    else
    {
      setValue(INSECTICIDE, value.getId());
    }
  }
  
  public boolean isInsecticideWritable()
  {
    return isWritable(INSECTICIDE);
  }
  
  public boolean isInsecticideReadable()
  {
    return isReadable(INSECTICIDE);
  }
  
  public boolean isInsecticideModified()
  {
    return isModified(INSECTICIDE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getInsecticideMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(INSECTICIDE).getAttributeMdDTO();
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getKeyNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(KEYNAME).getAttributeMdDTO();
  }
  
  public java.util.Date getLastUpdateDate()
  {
    return com.runwaysdk.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(LASTUPDATEDATE));
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
  
  public final com.runwaysdk.transport.metadata.AttributeDateTimeMdDTO getLastUpdateDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateTimeMdDTO) getAttributeDTO(LASTUPDATEDATE).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.SingleActorDTO getLastUpdatedBy()
  {
    if(getValue(LASTUPDATEDBY) == null || getValue(LASTUPDATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.SingleActorDTO.get(getRequest(), getValue(LASTUPDATEDBY));
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getLastUpdatedByMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(LASTUPDATEDBY).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.UsersDTO getLockedBy()
  {
    if(getValue(LOCKEDBY) == null || getValue(LOCKEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.UsersDTO.get(getRequest(), getValue(LOCKEDBY));
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getLockedByMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(LOCKEDBY).getAttributeMdDTO();
  }
  
  public Integer getLowerPercent()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LOWERPERCENT));
  }
  
  public void setLowerPercent(Integer value)
  {
    if(value == null)
    {
      setValue(LOWERPERCENT, "");
    }
    else
    {
      setValue(LOWERPERCENT, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isLowerPercentWritable()
  {
    return isWritable(LOWERPERCENT);
  }
  
  public boolean isLowerPercentReadable()
  {
    return isReadable(LOWERPERCENT);
  }
  
  public boolean isLowerPercentModified()
  {
    return isModified(LOWERPERCENT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getLowerPercentMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(LOWERPERCENT).getAttributeMdDTO();
  }
  
  public Integer getLowerTime()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LOWERTIME));
  }
  
  public void setLowerTime(Integer value)
  {
    if(value == null)
    {
      setValue(LOWERTIME, "");
    }
    else
    {
      setValue(LOWERTIME, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isLowerTimeWritable()
  {
    return isWritable(LOWERTIME);
  }
  
  public boolean isLowerTimeReadable()
  {
    return isReadable(LOWERTIME);
  }
  
  public boolean isLowerTimeModified()
  {
    return isModified(LOWERTIME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getLowerTimeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(LOWERTIME).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.ActorDTO getOwner()
  {
    if(getValue(OWNER) == null || getValue(OWNER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.ActorDTO.get(getRequest(), getValue(OWNER));
    }
  }
  
  public void setOwner(com.runwaysdk.system.ActorDTO value)
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getOwnerMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(OWNER).getAttributeMdDTO();
  }
  
  public Long getSeq()
  {
    return com.runwaysdk.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(SEQ));
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
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getSeqMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SEQ).getAttributeMdDTO();
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSiteMasterMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SITEMASTER).getAttributeMdDTO();
  }
  
  public Integer getUpperPercent()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(UPPERPERCENT));
  }
  
  public void setUpperPercent(Integer value)
  {
    if(value == null)
    {
      setValue(UPPERPERCENT, "");
    }
    else
    {
      setValue(UPPERPERCENT, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isUpperPercentWritable()
  {
    return isWritable(UPPERPERCENT);
  }
  
  public boolean isUpperPercentReadable()
  {
    return isReadable(UPPERPERCENT);
  }
  
  public boolean isUpperPercentModified()
  {
    return isModified(UPPERPERCENT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getUpperPercentMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(UPPERPERCENT).getAttributeMdDTO();
  }
  
  public Integer getUpperTime()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(UPPERTIME));
  }
  
  public void setUpperTime(Integer value)
  {
    if(value == null)
    {
      setValue(UPPERTIME, "");
    }
    else
    {
      setValue(UPPERTIME, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isUpperTimeWritable()
  {
    return isWritable(UPPERTIME);
  }
  
  public boolean isUpperTimeReadable()
  {
    return isReadable(UPPERTIME);
  }
  
  public boolean isUpperTimeModified()
  {
    return isModified(UPPERTIME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getUpperTimeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(UPPERTIME).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.general.KnockDownTimePropertyDTO searchByInsecticide(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.general.InsecticideDTO insecticide)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.general.Insecticide"};
    Object[] _parameters = new Object[]{insecticide};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.KnockDownTimePropertyDTO.CLASS, "searchByInsecticide", _declaredTypes);
    return (dss.vector.solutions.general.KnockDownTimePropertyDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static dss.vector.solutions.general.KnockDownTimePropertyDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.general.KnockDownTimePropertyDTO) dto;
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
  
  public static dss.vector.solutions.general.KnockDownTimePropertyQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.general.KnockDownTimePropertyQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.general.KnockDownTimePropertyDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.general.KnockDownTimePropertyDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.KnockDownTimePropertyDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.general.KnockDownTimePropertyDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.general.KnockDownTimePropertyDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.KnockDownTimePropertyDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.general.KnockDownTimePropertyDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
