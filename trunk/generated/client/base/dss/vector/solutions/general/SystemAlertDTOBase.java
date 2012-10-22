package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = -138305316)
public abstract class SystemAlertDTOBase extends com.runwaysdk.business.BusinessDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.SystemAlert";
  private static final long serialVersionUID = -138305316;
  
  protected SystemAlertDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected SystemAlertDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ALERTTYPE = "alertType";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DISEASE = "disease";
  public static java.lang.String EMAILBCCADDRESSES = "emailBccAddresses";
  public static java.lang.String EMAILBODYTEXT = "emailBodyText";
  public static java.lang.String EMAILCCADDRESSES = "emailCcAddresses";
  public static java.lang.String EMAILFROMADDRESS = "emailFromAddress";
  public static java.lang.String EMAILSUBJECTTEXT = "emailSubjectText";
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
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.general.SystemAlertTypeDTO> getAlertType()
  {
    return (java.util.List<dss.vector.solutions.general.SystemAlertTypeDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.general.SystemAlertTypeDTO.CLASS, getEnumNames(ALERTTYPE));
  }
  
  public java.util.List<String> getAlertTypeEnumNames()
  {
    return getEnumNames(ALERTTYPE);
  }
  
  public void addAlertType(dss.vector.solutions.general.SystemAlertTypeDTO enumDTO)
  {
    addEnumItem(ALERTTYPE, enumDTO.toString());
  }
  
  public void removeAlertType(dss.vector.solutions.general.SystemAlertTypeDTO enumDTO)
  {
    removeEnumItem(ALERTTYPE, enumDTO.toString());
  }
  
  public void clearAlertType()
  {
    clearEnum(ALERTTYPE);
  }
  
  public boolean isAlertTypeWritable()
  {
    return isWritable(ALERTTYPE);
  }
  
  public boolean isAlertTypeReadable()
  {
    return isReadable(ALERTTYPE);
  }
  
  public boolean isAlertTypeModified()
  {
    return isModified(ALERTTYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getAlertTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(ALERTTYPE).getAttributeMdDTO();
  }
  
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
  
  public String getCreatedById()
  {
    return getValue(CREATEDBY);
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
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getEmailBccAddressesMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(EMAILBCCADDRESSES).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.general.SystemAlertEmailBodyTextDTO getEmailBodyText()
  {
    return (dss.vector.solutions.general.SystemAlertEmailBodyTextDTO) this.getAttributeStructDTO(EMAILBODYTEXT).getStructDTO();
  }
  
  public boolean isEmailBodyTextWritable()
  {
    return isWritable(EMAILBODYTEXT);
  }
  
  public boolean isEmailBodyTextReadable()
  {
    return isReadable(EMAILBODYTEXT);
  }
  
  public boolean isEmailBodyTextModified()
  {
    return isModified(EMAILBODYTEXT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeLocalTextMdDTO getEmailBodyTextMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeLocalTextMdDTO) getAttributeDTO(EMAILBODYTEXT).getAttributeMdDTO();
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
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getEmailCcAddressesMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(EMAILCCADDRESSES).getAttributeMdDTO();
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getEmailFromAddressMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(EMAILFROMADDRESS).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.general.SystemAlertEmailSubjectTextDTO getEmailSubjectText()
  {
    return (dss.vector.solutions.general.SystemAlertEmailSubjectTextDTO) this.getAttributeStructDTO(EMAILSUBJECTTEXT).getStructDTO();
  }
  
  public boolean isEmailSubjectTextWritable()
  {
    return isWritable(EMAILSUBJECTTEXT);
  }
  
  public boolean isEmailSubjectTextReadable()
  {
    return isReadable(EMAILSUBJECTTEXT);
  }
  
  public boolean isEmailSubjectTextModified()
  {
    return isModified(EMAILSUBJECTTEXT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeLocalTextMdDTO getEmailSubjectTextMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeLocalTextMdDTO) getAttributeDTO(EMAILSUBJECTTEXT).getAttributeMdDTO();
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
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getEmailToAddressesMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(EMAILTOADDRESSES).getAttributeMdDTO();
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
  
  public String getEntityDomainId()
  {
    return getValue(ENTITYDOMAIN);
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
  
  public Boolean getIsEmailActive()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISEMAILACTIVE));
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
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getIsEmailActiveMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISEMAILACTIVE).getAttributeMdDTO();
  }
  
  public Boolean getIsOnscreenActive()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISONSCREENACTIVE));
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
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getIsOnscreenActiveMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISONSCREENACTIVE).getAttributeMdDTO();
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
  
  public String getLastUpdatedById()
  {
    return getValue(LASTUPDATEDBY);
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
  
  public String getLockedById()
  {
    return getValue(LOCKEDBY);
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
  
  public String getOwnerId()
  {
    return getValue(OWNER);
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
  
  public static final dss.vector.solutions.general.SystemAlertQueryDTO getAllInstancesForDisease(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String sortAttribute, java.lang.Boolean ascending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{sortAttribute, ascending, pageSize, pageNumber};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.SystemAlertDTO.CLASS, "getAllInstancesForDisease", _declaredTypes);
    return (dss.vector.solutions.general.SystemAlertQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static dss.vector.solutions.general.SystemAlertDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
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
  
  public static dss.vector.solutions.general.SystemAlertQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.general.SystemAlertQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.general.SystemAlertDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.general.SystemAlertDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.SystemAlertDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.general.SystemAlertDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.general.SystemAlertDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.SystemAlertDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.general.SystemAlertDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
