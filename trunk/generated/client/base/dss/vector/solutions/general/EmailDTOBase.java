package dss.vector.solutions.general;

@com.terraframe.mojo.business.ClassSignature(hash = 1729379130)
public abstract class EmailDTOBase extends com.terraframe.mojo.business.BusinessDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.Email";
  private static final long serialVersionUID = 1729379130;
  
  protected EmailDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected EmailDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String BCCADDRESSES = "bccAddresses";
  public static java.lang.String BODY = "body";
  public static java.lang.String CCADDRESSES = "ccAddresses";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String ERROR = "error";
  public static java.lang.String FROMADDRESS = "fromAddress";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String SENTDATE = "sentDate";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String SUBJECT = "subject";
  public static java.lang.String TOADDRESSES = "toAddresses";
  public static java.lang.String TYPE = "type";
  public String getBccAddresses()
  {
    return getValue(BCCADDRESSES);
  }
  
  public void setBccAddresses(String value)
  {
    if(value == null)
    {
      setValue(BCCADDRESSES, "");
    }
    else
    {
      setValue(BCCADDRESSES, value);
    }
  }
  
  public boolean isBccAddressesWritable()
  {
    return isWritable(BCCADDRESSES);
  }
  
  public boolean isBccAddressesReadable()
  {
    return isReadable(BCCADDRESSES);
  }
  
  public boolean isBccAddressesModified()
  {
    return isModified(BCCADDRESSES);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeTextMdDTO getBccAddressesMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeTextMdDTO) getAttributeDTO(BCCADDRESSES).getAttributeMdDTO();
  }
  
  public String getBody()
  {
    return getValue(BODY);
  }
  
  public void setBody(String value)
  {
    if(value == null)
    {
      setValue(BODY, "");
    }
    else
    {
      setValue(BODY, value);
    }
  }
  
  public boolean isBodyWritable()
  {
    return isWritable(BODY);
  }
  
  public boolean isBodyReadable()
  {
    return isReadable(BODY);
  }
  
  public boolean isBodyModified()
  {
    return isModified(BODY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeTextMdDTO getBodyMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeTextMdDTO) getAttributeDTO(BODY).getAttributeMdDTO();
  }
  
  public String getCcAddresses()
  {
    return getValue(CCADDRESSES);
  }
  
  public void setCcAddresses(String value)
  {
    if(value == null)
    {
      setValue(CCADDRESSES, "");
    }
    else
    {
      setValue(CCADDRESSES, value);
    }
  }
  
  public boolean isCcAddressesWritable()
  {
    return isWritable(CCADDRESSES);
  }
  
  public boolean isCcAddressesReadable()
  {
    return isReadable(CCADDRESSES);
  }
  
  public boolean isCcAddressesModified()
  {
    return isModified(CCADDRESSES);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeTextMdDTO getCcAddressesMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeTextMdDTO) getAttributeDTO(CCADDRESSES).getAttributeMdDTO();
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
  
  public String getError()
  {
    return getValue(ERROR);
  }
  
  public void setError(String value)
  {
    if(value == null)
    {
      setValue(ERROR, "");
    }
    else
    {
      setValue(ERROR, value);
    }
  }
  
  public boolean isErrorWritable()
  {
    return isWritable(ERROR);
  }
  
  public boolean isErrorReadable()
  {
    return isReadable(ERROR);
  }
  
  public boolean isErrorModified()
  {
    return isModified(ERROR);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeTextMdDTO getErrorMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeTextMdDTO) getAttributeDTO(ERROR).getAttributeMdDTO();
  }
  
  public String getFromAddress()
  {
    return getValue(FROMADDRESS);
  }
  
  public void setFromAddress(String value)
  {
    if(value == null)
    {
      setValue(FROMADDRESS, "");
    }
    else
    {
      setValue(FROMADDRESS, value);
    }
  }
  
  public boolean isFromAddressWritable()
  {
    return isWritable(FROMADDRESS);
  }
  
  public boolean isFromAddressReadable()
  {
    return isReadable(FROMADDRESS);
  }
  
  public boolean isFromAddressModified()
  {
    return isModified(FROMADDRESS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getFromAddressMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FROMADDRESS).getAttributeMdDTO();
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
  
  public java.util.Date getSentDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(SENTDATE));
  }
  
  public void setSentDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(SENTDATE, "");
    }
    else
    {
      setValue(SENTDATE, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATETIME_FORMAT).format(value));
    }
  }
  
  public boolean isSentDateWritable()
  {
    return isWritable(SENTDATE);
  }
  
  public boolean isSentDateReadable()
  {
    return isReadable(SENTDATE);
  }
  
  public boolean isSentDateModified()
  {
    return isModified(SENTDATE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateTimeMdDTO getSentDateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateTimeMdDTO) getAttributeDTO(SENTDATE).getAttributeMdDTO();
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
  
  public String getSubject()
  {
    return getValue(SUBJECT);
  }
  
  public void setSubject(String value)
  {
    if(value == null)
    {
      setValue(SUBJECT, "");
    }
    else
    {
      setValue(SUBJECT, value);
    }
  }
  
  public boolean isSubjectWritable()
  {
    return isWritable(SUBJECT);
  }
  
  public boolean isSubjectReadable()
  {
    return isReadable(SUBJECT);
  }
  
  public boolean isSubjectModified()
  {
    return isModified(SUBJECT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getSubjectMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SUBJECT).getAttributeMdDTO();
  }
  
  public String getToAddresses()
  {
    return getValue(TOADDRESSES);
  }
  
  public void setToAddresses(String value)
  {
    if(value == null)
    {
      setValue(TOADDRESSES, "");
    }
    else
    {
      setValue(TOADDRESSES, value);
    }
  }
  
  public boolean isToAddressesWritable()
  {
    return isWritable(TOADDRESSES);
  }
  
  public boolean isToAddressesReadable()
  {
    return isReadable(TOADDRESSES);
  }
  
  public boolean isToAddressesModified()
  {
    return isModified(TOADDRESSES);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeTextMdDTO getToAddressesMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeTextMdDTO) getAttributeDTO(TOADDRESSES).getAttributeMdDTO();
  }
  
  public static dss.vector.solutions.general.EmailDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.general.EmailDTO) dto;
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
  
  public static dss.vector.solutions.general.EmailQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.general.EmailQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.general.Email", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.general.EmailDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.general.EmailDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.general.EmailDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.general.EmailDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.general.EmailDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.general.EmailDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
