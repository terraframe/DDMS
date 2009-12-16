package dss.vector.solutions;

@com.terraframe.mojo.business.ClassSignature(hash = 1782847131)
public abstract class PersonDTOBase extends com.terraframe.mojo.business.BusinessDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.Person";
  private static final long serialVersionUID = 1782847131;
  
  protected PersonDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected PersonDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DATEOFBIRTH = "dateOfBirth";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String FIRSTNAME = "firstName";
  public static java.lang.String ID = "id";
  public static java.lang.String IPTRECIPIENTDELEGATE = "iptRecipientDelegate";
  public static java.lang.String ITNRECIPIENTDELEGATE = "itnRecipientDelegate";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTNAME = "lastName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String PATIENTDELEGATE = "patientDelegate";
  public static java.lang.String RESIDENTIALGEOENTITY = "residentialGeoEntity";
  public static java.lang.String RESIDENTIALINFORMATION = "residentialInformation";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SEX = "sex";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String SPRAYLEADERDELEGATE = "sprayLeaderDelegate";
  public static java.lang.String SPRAYOPERATORDELEGATE = "sprayOperatorDelegate";
  public static java.lang.String STOCKSTAFFDELEGATE = "stockStaffDelegate";
  public static java.lang.String TYPE = "type";
  public static java.lang.String USERDELEGATE = "userDelegate";
  public static java.lang.String WORKGEOENTITY = "workGeoEntity";
  public static java.lang.String WORKINFORMATION = "workInformation";
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
  
  public java.util.Date getDateOfBirth()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(DATEOFBIRTH));
  }
  
  public void setDateOfBirth(java.util.Date value)
  {
    if(value == null)
    {
      setValue(DATEOFBIRTH, "");
    }
    else
    {
      setValue(DATEOFBIRTH, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateMdDTO getDateOfBirthMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO(DATEOFBIRTH).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getFirstNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FIRSTNAME).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getIptRecipientDelegateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(IPTRECIPIENTDELEGATE).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getItnRecipientDelegateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ITNRECIPIENTDELEGATE).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getLastNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(LASTNAME).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getPatientDelegateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(PATIENTDELEGATE).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getResidentialGeoEntityMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(RESIDENTIALGEOENTITY).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getResidentialInformationMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(RESIDENTIALINFORMATION).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getSexMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SEX).getAttributeMdDTO();
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
  
  public dss.vector.solutions.irs.SprayLeaderDTO getSprayLeaderDelegate()
  {
    if(getValue(SPRAYLEADERDELEGATE) == null || getValue(SPRAYLEADERDELEGATE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.SprayLeaderDTO.get(getRequest(), getValue(SPRAYLEADERDELEGATE));
    }
  }
  
  public void setSprayLeaderDelegate(dss.vector.solutions.irs.SprayLeaderDTO value)
  {
    if(value == null)
    {
      setValue(SPRAYLEADERDELEGATE, "");
    }
    else
    {
      setValue(SPRAYLEADERDELEGATE, value.getId());
    }
  }
  
  public boolean isSprayLeaderDelegateWritable()
  {
    return isWritable(SPRAYLEADERDELEGATE);
  }
  
  public boolean isSprayLeaderDelegateReadable()
  {
    return isReadable(SPRAYLEADERDELEGATE);
  }
  
  public boolean isSprayLeaderDelegateModified()
  {
    return isModified(SPRAYLEADERDELEGATE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getSprayLeaderDelegateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SPRAYLEADERDELEGATE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.irs.SprayOperatorDTO getSprayOperatorDelegate()
  {
    if(getValue(SPRAYOPERATORDELEGATE) == null || getValue(SPRAYOPERATORDELEGATE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.SprayOperatorDTO.get(getRequest(), getValue(SPRAYOPERATORDELEGATE));
    }
  }
  
  public void setSprayOperatorDelegate(dss.vector.solutions.irs.SprayOperatorDTO value)
  {
    if(value == null)
    {
      setValue(SPRAYOPERATORDELEGATE, "");
    }
    else
    {
      setValue(SPRAYOPERATORDELEGATE, value.getId());
    }
  }
  
  public boolean isSprayOperatorDelegateWritable()
  {
    return isWritable(SPRAYOPERATORDELEGATE);
  }
  
  public boolean isSprayOperatorDelegateReadable()
  {
    return isReadable(SPRAYOPERATORDELEGATE);
  }
  
  public boolean isSprayOperatorDelegateModified()
  {
    return isModified(SPRAYOPERATORDELEGATE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getSprayOperatorDelegateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SPRAYOPERATORDELEGATE).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getStockStaffDelegateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(STOCKSTAFFDELEGATE).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getUserDelegateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(USERDELEGATE).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getWorkGeoEntityMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(WORKGEOENTITY).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getWorkInformationMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(WORKINFORMATION).getAttributeMdDTO();
  }
  
  public final dss.vector.solutions.PersonViewDTO getView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.PersonDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.PersonViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.PersonViewDTO getView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.PersonDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.PersonViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.PersonViewDTO lockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.PersonDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.PersonViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.PersonViewDTO lockView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.PersonDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.PersonViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.PersonWithDelegatesViewQueryDTO searchForDuplicates()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.PersonDTO.CLASS, "searchForDuplicates", _declaredTypes);
    return (dss.vector.solutions.PersonWithDelegatesViewQueryDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.PersonWithDelegatesViewQueryDTO searchForDuplicates(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.PersonDTO.CLASS, "searchForDuplicates", _declaredTypes);
    return (dss.vector.solutions.PersonWithDelegatesViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.terraframe.mojo.business.ValueQueryDTO searchForPerson(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String value)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{value};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.PersonDTO.CLASS, "searchForPerson", _declaredTypes);
    return (com.terraframe.mojo.business.ValueQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void unlockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.PersonDTO.CLASS, "unlockView", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void unlockView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.PersonDTO.CLASS, "unlockView", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static dss.vector.solutions.PersonDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
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
  
  public static dss.vector.solutions.PersonQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.PersonQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.Person", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.PersonDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.PersonDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.PersonDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.PersonDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.PersonDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.PersonDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
