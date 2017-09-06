package dss.vector.solutions;

@com.runwaysdk.business.ClassSignature(hash = 1506816395)
public abstract class PersonDTOBase extends com.runwaysdk.business.BusinessDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.Person";
  private static final long serialVersionUID = 1506816395;
  
  protected PersonDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected PersonDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String BIRTHENTITY = "birthEntity";
  public static java.lang.String BIRTHLOCATION = "birthLocation";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DATEOFBIRTH = "dateOfBirth";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String FIRSTNAME = "firstName";
  public static java.lang.String ID = "id";
  public static java.lang.String IDENTIFIER = "identifier";
  public static java.lang.String IPTRECIPIENTDELEGATE = "iptRecipientDelegate";
  public static java.lang.String ITNRECIPIENTDELEGATE = "itnRecipientDelegate";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTNAME = "lastName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String PATIENTDELEGATE = "patientDelegate";
  public static java.lang.String PHYSICIANDELEGATE = "physicianDelegate";
  public static java.lang.String RESIDENTIALGEOENTITY = "residentialGeoEntity";
  public static java.lang.String RESIDENTIALINFORMATION = "residentialInformation";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SEX = "sex";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String STOCKSTAFFDELEGATE = "stockStaffDelegate";
  public static java.lang.String SUPERVISORDELEGATE = "supervisorDelegate";
  public static java.lang.String TEAMMEMBERDELEGATE = "teamMemberDelegate";
  public static java.lang.String TYPE = "type";
  public static java.lang.String USERDELEGATE = "userDelegate";
  public static java.lang.String WORKGEOENTITY = "workGeoEntity";
  public static java.lang.String WORKINFORMATION = "workInformation";
  public dss.vector.solutions.geo.generated.GeoEntityDTO getBirthEntity()
  {
    if(getValue(BIRTHENTITY) == null || getValue(BIRTHENTITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntityDTO.get(getRequest(), getValue(BIRTHENTITY));
    }
  }
  
  public String getBirthEntityId()
  {
    return getValue(BIRTHENTITY);
  }
  
  public void setBirthEntity(dss.vector.solutions.geo.generated.GeoEntityDTO value)
  {
    if(value == null)
    {
      setValue(BIRTHENTITY, "");
    }
    else
    {
      setValue(BIRTHENTITY, value.getId());
    }
  }
  
  public boolean isBirthEntityWritable()
  {
    return isWritable(BIRTHENTITY);
  }
  
  public boolean isBirthEntityReadable()
  {
    return isReadable(BIRTHENTITY);
  }
  
  public boolean isBirthEntityModified()
  {
    return isModified(BIRTHENTITY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getBirthEntityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(BIRTHENTITY).getAttributeMdDTO();
  }
  
  public String getBirthLocation()
  {
    return getValue(BIRTHLOCATION);
  }
  
  public void setBirthLocation(String value)
  {
    if(value == null)
    {
      setValue(BIRTHLOCATION, "");
    }
    else
    {
      setValue(BIRTHLOCATION, value);
    }
  }
  
  public boolean isBirthLocationWritable()
  {
    return isWritable(BIRTHLOCATION);
  }
  
  public boolean isBirthLocationReadable()
  {
    return isReadable(BIRTHLOCATION);
  }
  
  public boolean isBirthLocationModified()
  {
    return isModified(BIRTHLOCATION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getBirthLocationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(BIRTHLOCATION).getAttributeMdDTO();
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
  
  public java.util.Date getDateOfBirth()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(DATEOFBIRTH));
  }
  
  public void setDateOfBirth(java.util.Date value)
  {
    if(value == null)
    {
      setValue(DATEOFBIRTH, "");
    }
    else
    {
      setValue(DATEOFBIRTH, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isDateOfBirthWritable()
  {
    return isWritable(DATEOFBIRTH);
  }
  
  public boolean isDateOfBirthReadable()
  {
    return isReadable(DATEOFBIRTH);
  }
  
  public boolean isDateOfBirthModified()
  {
    return isModified(DATEOFBIRTH);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getDateOfBirthMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(DATEOFBIRTH).getAttributeMdDTO();
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
  
  public String getFirstName()
  {
    return getValue(FIRSTNAME);
  }
  
  public void setFirstName(String value)
  {
    if(value == null)
    {
      setValue(FIRSTNAME, "");
    }
    else
    {
      setValue(FIRSTNAME, value);
    }
  }
  
  public boolean isFirstNameWritable()
  {
    return isWritable(FIRSTNAME);
  }
  
  public boolean isFirstNameReadable()
  {
    return isReadable(FIRSTNAME);
  }
  
  public boolean isFirstNameModified()
  {
    return isModified(FIRSTNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getFirstNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FIRSTNAME).getAttributeMdDTO();
  }
  
  public String getIdentifier()
  {
    return getValue(IDENTIFIER);
  }
  
  public void setIdentifier(String value)
  {
    if(value == null)
    {
      setValue(IDENTIFIER, "");
    }
    else
    {
      setValue(IDENTIFIER, value);
    }
  }
  
  public boolean isIdentifierWritable()
  {
    return isWritable(IDENTIFIER);
  }
  
  public boolean isIdentifierReadable()
  {
    return isReadable(IDENTIFIER);
  }
  
  public boolean isIdentifierModified()
  {
    return isModified(IDENTIFIER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getIdentifierMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(IDENTIFIER).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.intervention.monitor.IPTRecipientDTO getIptRecipientDelegate()
  {
    if(getValue(IPTRECIPIENTDELEGATE) == null || getValue(IPTRECIPIENTDELEGATE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.intervention.monitor.IPTRecipientDTO.get(getRequest(), getValue(IPTRECIPIENTDELEGATE));
    }
  }
  
  public String getIptRecipientDelegateId()
  {
    return getValue(IPTRECIPIENTDELEGATE);
  }
  
  public void setIptRecipientDelegate(dss.vector.solutions.intervention.monitor.IPTRecipientDTO value)
  {
    if(value == null)
    {
      setValue(IPTRECIPIENTDELEGATE, "");
    }
    else
    {
      setValue(IPTRECIPIENTDELEGATE, value.getId());
    }
  }
  
  public boolean isIptRecipientDelegateWritable()
  {
    return isWritable(IPTRECIPIENTDELEGATE);
  }
  
  public boolean isIptRecipientDelegateReadable()
  {
    return isReadable(IPTRECIPIENTDELEGATE);
  }
  
  public boolean isIptRecipientDelegateModified()
  {
    return isModified(IPTRECIPIENTDELEGATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getIptRecipientDelegateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(IPTRECIPIENTDELEGATE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.intervention.monitor.ITNRecipientDTO getItnRecipientDelegate()
  {
    if(getValue(ITNRECIPIENTDELEGATE) == null || getValue(ITNRECIPIENTDELEGATE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.intervention.monitor.ITNRecipientDTO.get(getRequest(), getValue(ITNRECIPIENTDELEGATE));
    }
  }
  
  public String getItnRecipientDelegateId()
  {
    return getValue(ITNRECIPIENTDELEGATE);
  }
  
  public void setItnRecipientDelegate(dss.vector.solutions.intervention.monitor.ITNRecipientDTO value)
  {
    if(value == null)
    {
      setValue(ITNRECIPIENTDELEGATE, "");
    }
    else
    {
      setValue(ITNRECIPIENTDELEGATE, value.getId());
    }
  }
  
  public boolean isItnRecipientDelegateWritable()
  {
    return isWritable(ITNRECIPIENTDELEGATE);
  }
  
  public boolean isItnRecipientDelegateReadable()
  {
    return isReadable(ITNRECIPIENTDELEGATE);
  }
  
  public boolean isItnRecipientDelegateModified()
  {
    return isModified(ITNRECIPIENTDELEGATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getItnRecipientDelegateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ITNRECIPIENTDELEGATE).getAttributeMdDTO();
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
  
  public String getLastName()
  {
    return getValue(LASTNAME);
  }
  
  public void setLastName(String value)
  {
    if(value == null)
    {
      setValue(LASTNAME, "");
    }
    else
    {
      setValue(LASTNAME, value);
    }
  }
  
  public boolean isLastNameWritable()
  {
    return isWritable(LASTNAME);
  }
  
  public boolean isLastNameReadable()
  {
    return isReadable(LASTNAME);
  }
  
  public boolean isLastNameModified()
  {
    return isModified(LASTNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getLastNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(LASTNAME).getAttributeMdDTO();
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
  
  public com.runwaysdk.system.SingleActorDTO getLockedBy()
  {
    if(getValue(LOCKEDBY) == null || getValue(LOCKEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.SingleActorDTO.get(getRequest(), getValue(LOCKEDBY));
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
  
  public dss.vector.solutions.PatientDTO getPatientDelegate()
  {
    if(getValue(PATIENTDELEGATE) == null || getValue(PATIENTDELEGATE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.PatientDTO.get(getRequest(), getValue(PATIENTDELEGATE));
    }
  }
  
  public String getPatientDelegateId()
  {
    return getValue(PATIENTDELEGATE);
  }
  
  public void setPatientDelegate(dss.vector.solutions.PatientDTO value)
  {
    if(value == null)
    {
      setValue(PATIENTDELEGATE, "");
    }
    else
    {
      setValue(PATIENTDELEGATE, value.getId());
    }
  }
  
  public boolean isPatientDelegateWritable()
  {
    return isWritable(PATIENTDELEGATE);
  }
  
  public boolean isPatientDelegateReadable()
  {
    return isReadable(PATIENTDELEGATE);
  }
  
  public boolean isPatientDelegateModified()
  {
    return isModified(PATIENTDELEGATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getPatientDelegateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(PATIENTDELEGATE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.PhysicianDTO getPhysicianDelegate()
  {
    if(getValue(PHYSICIANDELEGATE) == null || getValue(PHYSICIANDELEGATE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.PhysicianDTO.get(getRequest(), getValue(PHYSICIANDELEGATE));
    }
  }
  
  public String getPhysicianDelegateId()
  {
    return getValue(PHYSICIANDELEGATE);
  }
  
  public void setPhysicianDelegate(dss.vector.solutions.PhysicianDTO value)
  {
    if(value == null)
    {
      setValue(PHYSICIANDELEGATE, "");
    }
    else
    {
      setValue(PHYSICIANDELEGATE, value.getId());
    }
  }
  
  public boolean isPhysicianDelegateWritable()
  {
    return isWritable(PHYSICIANDELEGATE);
  }
  
  public boolean isPhysicianDelegateReadable()
  {
    return isReadable(PHYSICIANDELEGATE);
  }
  
  public boolean isPhysicianDelegateModified()
  {
    return isModified(PHYSICIANDELEGATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getPhysicianDelegateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(PHYSICIANDELEGATE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.geo.generated.GeoEntityDTO getResidentialGeoEntity()
  {
    if(getValue(RESIDENTIALGEOENTITY) == null || getValue(RESIDENTIALGEOENTITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntityDTO.get(getRequest(), getValue(RESIDENTIALGEOENTITY));
    }
  }
  
  public String getResidentialGeoEntityId()
  {
    return getValue(RESIDENTIALGEOENTITY);
  }
  
  public void setResidentialGeoEntity(dss.vector.solutions.geo.generated.GeoEntityDTO value)
  {
    if(value == null)
    {
      setValue(RESIDENTIALGEOENTITY, "");
    }
    else
    {
      setValue(RESIDENTIALGEOENTITY, value.getId());
    }
  }
  
  public boolean isResidentialGeoEntityWritable()
  {
    return isWritable(RESIDENTIALGEOENTITY);
  }
  
  public boolean isResidentialGeoEntityReadable()
  {
    return isReadable(RESIDENTIALGEOENTITY);
  }
  
  public boolean isResidentialGeoEntityModified()
  {
    return isModified(RESIDENTIALGEOENTITY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getResidentialGeoEntityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(RESIDENTIALGEOENTITY).getAttributeMdDTO();
  }
  
  public String getResidentialInformation()
  {
    return getValue(RESIDENTIALINFORMATION);
  }
  
  public void setResidentialInformation(String value)
  {
    if(value == null)
    {
      setValue(RESIDENTIALINFORMATION, "");
    }
    else
    {
      setValue(RESIDENTIALINFORMATION, value);
    }
  }
  
  public boolean isResidentialInformationWritable()
  {
    return isWritable(RESIDENTIALINFORMATION);
  }
  
  public boolean isResidentialInformationReadable()
  {
    return isReadable(RESIDENTIALINFORMATION);
  }
  
  public boolean isResidentialInformationModified()
  {
    return isModified(RESIDENTIALINFORMATION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getResidentialInformationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(RESIDENTIALINFORMATION).getAttributeMdDTO();
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
  
  public dss.vector.solutions.ontology.TermDTO getSex()
  {
    if(getValue(SEX) == null || getValue(SEX).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(SEX));
    }
  }
  
  public String getSexId()
  {
    return getValue(SEX);
  }
  
  public void setSex(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(SEX, "");
    }
    else
    {
      setValue(SEX, value.getId());
    }
  }
  
  public boolean isSexWritable()
  {
    return isWritable(SEX);
  }
  
  public boolean isSexReadable()
  {
    return isReadable(SEX);
  }
  
  public boolean isSexModified()
  {
    return isModified(SEX);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getSexMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SEX).getAttributeMdDTO();
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
  
  public dss.vector.solutions.stock.StockStaffDTO getStockStaffDelegate()
  {
    if(getValue(STOCKSTAFFDELEGATE) == null || getValue(STOCKSTAFFDELEGATE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.stock.StockStaffDTO.get(getRequest(), getValue(STOCKSTAFFDELEGATE));
    }
  }
  
  public String getStockStaffDelegateId()
  {
    return getValue(STOCKSTAFFDELEGATE);
  }
  
  public void setStockStaffDelegate(dss.vector.solutions.stock.StockStaffDTO value)
  {
    if(value == null)
    {
      setValue(STOCKSTAFFDELEGATE, "");
    }
    else
    {
      setValue(STOCKSTAFFDELEGATE, value.getId());
    }
  }
  
  public boolean isStockStaffDelegateWritable()
  {
    return isWritable(STOCKSTAFFDELEGATE);
  }
  
  public boolean isStockStaffDelegateReadable()
  {
    return isReadable(STOCKSTAFFDELEGATE);
  }
  
  public boolean isStockStaffDelegateModified()
  {
    return isModified(STOCKSTAFFDELEGATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getStockStaffDelegateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(STOCKSTAFFDELEGATE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.irs.SupervisorDTO getSupervisorDelegate()
  {
    if(getValue(SUPERVISORDELEGATE) == null || getValue(SUPERVISORDELEGATE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.SupervisorDTO.get(getRequest(), getValue(SUPERVISORDELEGATE));
    }
  }
  
  public String getSupervisorDelegateId()
  {
    return getValue(SUPERVISORDELEGATE);
  }
  
  public void setSupervisorDelegate(dss.vector.solutions.irs.SupervisorDTO value)
  {
    if(value == null)
    {
      setValue(SUPERVISORDELEGATE, "");
    }
    else
    {
      setValue(SUPERVISORDELEGATE, value.getId());
    }
  }
  
  public boolean isSupervisorDelegateWritable()
  {
    return isWritable(SUPERVISORDELEGATE);
  }
  
  public boolean isSupervisorDelegateReadable()
  {
    return isReadable(SUPERVISORDELEGATE);
  }
  
  public boolean isSupervisorDelegateModified()
  {
    return isModified(SUPERVISORDELEGATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getSupervisorDelegateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SUPERVISORDELEGATE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.irs.TeamMemberDTO getTeamMemberDelegate()
  {
    if(getValue(TEAMMEMBERDELEGATE) == null || getValue(TEAMMEMBERDELEGATE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.TeamMemberDTO.get(getRequest(), getValue(TEAMMEMBERDELEGATE));
    }
  }
  
  public String getTeamMemberDelegateId()
  {
    return getValue(TEAMMEMBERDELEGATE);
  }
  
  public void setTeamMemberDelegate(dss.vector.solutions.irs.TeamMemberDTO value)
  {
    if(value == null)
    {
      setValue(TEAMMEMBERDELEGATE, "");
    }
    else
    {
      setValue(TEAMMEMBERDELEGATE, value.getId());
    }
  }
  
  public boolean isTeamMemberDelegateWritable()
  {
    return isWritable(TEAMMEMBERDELEGATE);
  }
  
  public boolean isTeamMemberDelegateReadable()
  {
    return isReadable(TEAMMEMBERDELEGATE);
  }
  
  public boolean isTeamMemberDelegateModified()
  {
    return isModified(TEAMMEMBERDELEGATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getTeamMemberDelegateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(TEAMMEMBERDELEGATE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.MDSSUserDTO getUserDelegate()
  {
    if(getValue(USERDELEGATE) == null || getValue(USERDELEGATE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.MDSSUserDTO.get(getRequest(), getValue(USERDELEGATE));
    }
  }
  
  public String getUserDelegateId()
  {
    return getValue(USERDELEGATE);
  }
  
  public void setUserDelegate(dss.vector.solutions.MDSSUserDTO value)
  {
    if(value == null)
    {
      setValue(USERDELEGATE, "");
    }
    else
    {
      setValue(USERDELEGATE, value.getId());
    }
  }
  
  public boolean isUserDelegateWritable()
  {
    return isWritable(USERDELEGATE);
  }
  
  public boolean isUserDelegateReadable()
  {
    return isReadable(USERDELEGATE);
  }
  
  public boolean isUserDelegateModified()
  {
    return isModified(USERDELEGATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getUserDelegateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(USERDELEGATE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.geo.generated.GeoEntityDTO getWorkGeoEntity()
  {
    if(getValue(WORKGEOENTITY) == null || getValue(WORKGEOENTITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntityDTO.get(getRequest(), getValue(WORKGEOENTITY));
    }
  }
  
  public String getWorkGeoEntityId()
  {
    return getValue(WORKGEOENTITY);
  }
  
  public void setWorkGeoEntity(dss.vector.solutions.geo.generated.GeoEntityDTO value)
  {
    if(value == null)
    {
      setValue(WORKGEOENTITY, "");
    }
    else
    {
      setValue(WORKGEOENTITY, value.getId());
    }
  }
  
  public boolean isWorkGeoEntityWritable()
  {
    return isWritable(WORKGEOENTITY);
  }
  
  public boolean isWorkGeoEntityReadable()
  {
    return isReadable(WORKGEOENTITY);
  }
  
  public boolean isWorkGeoEntityModified()
  {
    return isModified(WORKGEOENTITY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getWorkGeoEntityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(WORKGEOENTITY).getAttributeMdDTO();
  }
  
  public String getWorkInformation()
  {
    return getValue(WORKINFORMATION);
  }
  
  public void setWorkInformation(String value)
  {
    if(value == null)
    {
      setValue(WORKINFORMATION, "");
    }
    else
    {
      setValue(WORKINFORMATION, value);
    }
  }
  
  public boolean isWorkInformationWritable()
  {
    return isWritable(WORKINFORMATION);
  }
  
  public boolean isWorkInformationReadable()
  {
    return isReadable(WORKINFORMATION);
  }
  
  public boolean isWorkInformationModified()
  {
    return isModified(WORKINFORMATION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getWorkInformationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(WORKINFORMATION).getAttributeMdDTO();
  }
  
  public final dss.vector.solutions.PersonViewDTO getView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.PersonDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.PersonViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.PersonViewDTO getView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.PersonDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.PersonViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.PersonViewDTO lockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.PersonDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.PersonViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.PersonViewDTO lockView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.PersonDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.PersonViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.PersonWithDelegatesViewQueryDTO searchForDuplicates()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.PersonDTO.CLASS, "searchForDuplicates", _declaredTypes);
    return (dss.vector.solutions.PersonWithDelegatesViewQueryDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.PersonWithDelegatesViewQueryDTO searchForDuplicates(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.PersonDTO.CLASS, "searchForDuplicates", _declaredTypes);
    return (dss.vector.solutions.PersonWithDelegatesViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.runwaysdk.business.ValueQueryDTO searchForPerson(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String value)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{value};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.PersonDTO.CLASS, "searchForPerson", _declaredTypes);
    return (com.runwaysdk.business.ValueQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void unlockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.PersonDTO.CLASS, "unlockView", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void unlockView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.PersonDTO.CLASS, "unlockView", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static dss.vector.solutions.PersonDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.PersonDTO) dto;
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
  
  public static dss.vector.solutions.PersonQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.PersonQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.PersonDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.PersonDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.PersonDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.PersonDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.PersonDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.PersonDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.PersonDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
