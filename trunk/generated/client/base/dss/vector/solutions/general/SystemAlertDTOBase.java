package dss.vector.solutions.general;

@com.terraframe.mojo.business.ClassSignature(hash = 845687922)
public abstract class SystemAlertDTOBase extends com.terraframe.mojo.business.BusinessDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.SystemAlert";
  private static final long serialVersionUID = 845687922;
  
  protected SystemAlertDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected SystemAlertDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DISPLAYNAME = "displayName";
  public static java.lang.String EMAILBCCADDRESSES = "emailBccAddresses";
  public static java.lang.String EMAILBODY = "emailBody";
  public static java.lang.String EMAILCCADDRESSES = "emailCcAddresses";
  public static java.lang.String EMAILFROMADDRESS = "emailFromAddress";
  public static java.lang.String EMAILSUBJECT = "emailSubject";
  public static java.lang.String EMAILTEMPLATEVARIABLES = "emailTemplateVariables";
  public static java.lang.String EMAILTOADDRESSES = "emailToAddresses";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String ID = "id";
  public static java.lang.String ISEMAILACTIVE = "isEmailActive";
  public static java.lang.String ISONSCREENACTIVE = "isOnscreenActive";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TYPE = "type";
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
  
  public dss.vector.solutions.general.SystemAlertDisplayNameDTO getDisplayName()
  {
    return (dss.vector.solutions.general.SystemAlertDisplayNameDTO) this.getAttributeStructDTO(DISPLAYNAME).getStructDTO();
  }
  
  public boolean isDisplayNameWritable()
  {
    return isWritable(DISPLAYNAME);
  }
  
  public boolean isDisplayNameReadable()
  {
    return isReadable(DISPLAYNAME);
  }
  
  public boolean isDisplayNameModified()
  {
    return isModified(DISPLAYNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeStructMdDTO getDisplayNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeStructMdDTO) getAttributeDTO(DISPLAYNAME).getAttributeMdDTO();
  }
  
  public String getEmailBccAddresses()
  {
    return getValue(EMAILBCCADDRESSES);
  }
  
  public void setEmailBccAddresses(String value)
  {
    if(value == null)
    {
      setValue(EMAILBCCADDRESSES, "");
    }
    else
    {
      setValue(EMAILBCCADDRESSES, value);
    }
  }
  
  public boolean isEmailBccAddressesWritable()
  {
    return isWritable(EMAILBCCADDRESSES);
  }
  
  public boolean isEmailBccAddressesReadable()
  {
    return isReadable(EMAILBCCADDRESSES);
  }
  
  public boolean isEmailBccAddressesModified()
  {
    return isModified(EMAILBCCADDRESSES);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeTextMdDTO getEmailBccAddressesMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeTextMdDTO) getAttributeDTO(EMAILBCCADDRESSES).getAttributeMdDTO();
  }
  
  public String getEmailBody()
  {
    return getValue(EMAILBODY);
  }
  
  public void setEmailBody(String value)
  {
    if(value == null)
    {
      setValue(EMAILBODY, "");
    }
    else
    {
      setValue(EMAILBODY, value);
    }
  }
  
  public boolean isEmailBodyWritable()
  {
    return isWritable(EMAILBODY);
  }
  
  public boolean isEmailBodyReadable()
  {
    return isReadable(EMAILBODY);
  }
  
  public boolean isEmailBodyModified()
  {
    return isModified(EMAILBODY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeTextMdDTO getEmailBodyMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeTextMdDTO) getAttributeDTO(EMAILBODY).getAttributeMdDTO();
  }
  
  public String getEmailCcAddresses()
  {
    return getValue(EMAILCCADDRESSES);
  }
  
  public void setEmailCcAddresses(String value)
  {
    if(value == null)
    {
      setValue(EMAILCCADDRESSES, "");
    }
    else
    {
      setValue(EMAILCCADDRESSES, value);
    }
  }
  
  public boolean isEmailCcAddressesWritable()
  {
    return isWritable(EMAILCCADDRESSES);
  }
  
  public boolean isEmailCcAddressesReadable()
  {
    return isReadable(EMAILCCADDRESSES);
  }
  
  public boolean isEmailCcAddressesModified()
  {
    return isModified(EMAILCCADDRESSES);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeTextMdDTO getEmailCcAddressesMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeTextMdDTO) getAttributeDTO(EMAILCCADDRESSES).getAttributeMdDTO();
  }
  
  public String getEmailFromAddress()
  {
    return getValue(EMAILFROMADDRESS);
  }
  
  public void setEmailFromAddress(String value)
  {
    if(value == null)
    {
      setValue(EMAILFROMADDRESS, "");
    }
    else
    {
      setValue(EMAILFROMADDRESS, value);
    }
  }
  
  public boolean isEmailFromAddressWritable()
  {
    return isWritable(EMAILFROMADDRESS);
  }
  
  public boolean isEmailFromAddressReadable()
  {
    return isReadable(EMAILFROMADDRESS);
  }
  
  public boolean isEmailFromAddressModified()
  {
    return isModified(EMAILFROMADDRESS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getEmailFromAddressMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(EMAILFROMADDRESS).getAttributeMdDTO();
  }
  
  public String getEmailSubject()
  {
    return getValue(EMAILSUBJECT);
  }
  
  public void setEmailSubject(String value)
  {
    if(value == null)
    {
      setValue(EMAILSUBJECT, "");
    }
    else
    {
      setValue(EMAILSUBJECT, value);
    }
  }
  
  public boolean isEmailSubjectWritable()
  {
    return isWritable(EMAILSUBJECT);
  }
  
  public boolean isEmailSubjectReadable()
  {
    return isReadable(EMAILSUBJECT);
  }
  
  public boolean isEmailSubjectModified()
  {
    return isModified(EMAILSUBJECT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getEmailSubjectMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(EMAILSUBJECT).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.general.SystemAlertEmailTemplateVariablesDTO getEmailTemplateVariables()
  {
    return (dss.vector.solutions.general.SystemAlertEmailTemplateVariablesDTO) this.getAttributeStructDTO(EMAILTEMPLATEVARIABLES).getStructDTO();
  }
  
  public boolean isEmailTemplateVariablesWritable()
  {
    return isWritable(EMAILTEMPLATEVARIABLES);
  }
  
  public boolean isEmailTemplateVariablesReadable()
  {
    return isReadable(EMAILTEMPLATEVARIABLES);
  }
  
  public boolean isEmailTemplateVariablesModified()
  {
    return isModified(EMAILTEMPLATEVARIABLES);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeStructMdDTO getEmailTemplateVariablesMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeStructMdDTO) getAttributeDTO(EMAILTEMPLATEVARIABLES).getAttributeMdDTO();
  }
  
  public String getEmailToAddresses()
  {
    return getValue(EMAILTOADDRESSES);
  }
  
  public void setEmailToAddresses(String value)
  {
    if(value == null)
    {
      setValue(EMAILTOADDRESSES, "");
    }
    else
    {
      setValue(EMAILTOADDRESSES, value);
    }
  }
  
  public boolean isEmailToAddressesWritable()
  {
    return isWritable(EMAILTOADDRESSES);
  }
  
  public boolean isEmailToAddressesReadable()
  {
    return isReadable(EMAILTOADDRESSES);
  }
  
  public boolean isEmailToAddressesModified()
  {
    return isModified(EMAILTOADDRESSES);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeTextMdDTO getEmailToAddressesMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeTextMdDTO) getAttributeDTO(EMAILTOADDRESSES).getAttributeMdDTO();
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
  
  public Boolean getIsEmailActive()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISEMAILACTIVE));
  }
  
  public void setIsEmailActive(Boolean value)
  {
    if(value == null)
    {
      setValue(ISEMAILACTIVE, "");
    }
    else
    {
      setValue(ISEMAILACTIVE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsEmailActiveWritable()
  {
    return isWritable(ISEMAILACTIVE);
  }
  
  public boolean isIsEmailActiveReadable()
  {
    return isReadable(ISEMAILACTIVE);
  }
  
  public boolean isIsEmailActiveModified()
  {
    return isModified(ISEMAILACTIVE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getIsEmailActiveMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISEMAILACTIVE).getAttributeMdDTO();
  }
  
  public Boolean getIsOnscreenActive()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISONSCREENACTIVE));
  }
  
  public void setIsOnscreenActive(Boolean value)
  {
    if(value == null)
    {
      setValue(ISONSCREENACTIVE, "");
    }
    else
    {
      setValue(ISONSCREENACTIVE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsOnscreenActiveWritable()
  {
    return isWritable(ISONSCREENACTIVE);
  }
  
  public boolean isIsOnscreenActiveReadable()
  {
    return isReadable(ISONSCREENACTIVE);
  }
  
  public boolean isIsOnscreenActiveModified()
  {
    return isModified(ISONSCREENACTIVE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getIsOnscreenActiveMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISONSCREENACTIVE).getAttributeMdDTO();
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
  
  public static dss.vector.solutions.general.SystemAlertDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.general.SystemAlertDTO) dto;
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
  
  public static dss.vector.solutions.general.SystemAlertQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.general.SystemAlertQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.general.SystemAlert", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.general.SystemAlertDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.general.SystemAlertDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.general.SystemAlertDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.general.SystemAlertDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.general.SystemAlertDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.general.SystemAlertDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
