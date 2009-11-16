package dss.vector.solutions.intervention.monitor;

@com.terraframe.mojo.business.ClassSignature(hash = -714542869)
public abstract class ITNCommunityDistributionDTOBase extends com.terraframe.mojo.business.BusinessDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.ITNCommunityDistribution";
  private static final long serialVersionUID = -714542869;
  
  protected ITNCommunityDistributionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected ITNCommunityDistributionDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String AGENTFIRSTNAME = "agentFirstName";
  public static java.lang.String AGENTSURNAME = "agentSurname";
  public static java.lang.String BATCHNUMBER = "batchNumber";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String CURRENCYRECEIVED = "currencyReceived";
  public static java.lang.String DISTRIBUTIONLOCATION = "distributionLocation";
  public static java.lang.String ENDDATE = "endDate";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String ENTRYTYPE = "entryType";
  public static java.lang.String HOUSEHOLDADDRESS = "householdAddress";
  public static java.lang.String HOUSEHOLDNAME = "householdName";
  public static java.lang.String HOUSEHOLDSURNAME = "householdSurname";
  public static java.lang.String ID = "id";
  public static java.lang.String ITNSRECEIVED = "itnsReceived";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String NUMBERRETRIEVED = "numberRetrieved";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String PRETREATED = "pretreated";
  public static java.lang.String RESIDENTS = "residents";
  public static java.lang.String RETRIEVED = "retrieved";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String SOLD = "sold";
  public static java.lang.String STARTDATE = "startDate";
  public static java.lang.String TYPE = "type";
  public String getAgentFirstName()
  {
    return getValue(AGENTFIRSTNAME);
  }
  
  public void setAgentFirstName(String value)
  {
    if(value == null)
    {
      setValue(AGENTFIRSTNAME, "");
    }
    else
    {
      setValue(AGENTFIRSTNAME, value);
    }
  }
  
  public boolean isAgentFirstNameWritable()
  {
    return isWritable(AGENTFIRSTNAME);
  }
  
  public boolean isAgentFirstNameReadable()
  {
    return isReadable(AGENTFIRSTNAME);
  }
  
  public boolean isAgentFirstNameModified()
  {
    return isModified(AGENTFIRSTNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getAgentFirstNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(AGENTFIRSTNAME).getAttributeMdDTO();
  }
  
  public String getAgentSurname()
  {
    return getValue(AGENTSURNAME);
  }
  
  public void setAgentSurname(String value)
  {
    if(value == null)
    {
      setValue(AGENTSURNAME, "");
    }
    else
    {
      setValue(AGENTSURNAME, value);
    }
  }
  
  public boolean isAgentSurnameWritable()
  {
    return isWritable(AGENTSURNAME);
  }
  
  public boolean isAgentSurnameReadable()
  {
    return isReadable(AGENTSURNAME);
  }
  
  public boolean isAgentSurnameModified()
  {
    return isModified(AGENTSURNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getAgentSurnameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(AGENTSURNAME).getAttributeMdDTO();
  }
  
  public String getBatchNumber()
  {
    return getValue(BATCHNUMBER);
  }
  
  public void setBatchNumber(String value)
  {
    if(value == null)
    {
      setValue(BATCHNUMBER, "");
    }
    else
    {
      setValue(BATCHNUMBER, value);
    }
  }
  
  public boolean isBatchNumberWritable()
  {
    return isWritable(BATCHNUMBER);
  }
  
  public boolean isBatchNumberReadable()
  {
    return isReadable(BATCHNUMBER);
  }
  
  public boolean isBatchNumberModified()
  {
    return isModified(BATCHNUMBER);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getBatchNumberMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(BATCHNUMBER).getAttributeMdDTO();
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
  
  public java.math.BigDecimal getCurrencyReceived()
  {
    return com.terraframe.mojo.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(CURRENCYRECEIVED));
  }
  
  public void setCurrencyReceived(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(CURRENCYRECEIVED, "");
    }
    else
    {
      setValue(CURRENCYRECEIVED, value.toString());
    }
  }
  
  public boolean isCurrencyReceivedWritable()
  {
    return isWritable(CURRENCYRECEIVED);
  }
  
  public boolean isCurrencyReceivedReadable()
  {
    return isReadable(CURRENCYRECEIVED);
  }
  
  public boolean isCurrencyReceivedModified()
  {
    return isModified(CURRENCYRECEIVED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDecMdDTO getCurrencyReceivedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDecMdDTO) getAttributeDTO(CURRENCYRECEIVED).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.geo.generated.GeoEntityDTO getDistributionLocation()
  {
    if(getValue(DISTRIBUTIONLOCATION) == null || getValue(DISTRIBUTIONLOCATION).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntityDTO.get(getRequest(), getValue(DISTRIBUTIONLOCATION));
    }
  }
  
  public void setDistributionLocation(dss.vector.solutions.geo.generated.GeoEntityDTO value)
  {
    if(value == null)
    {
      setValue(DISTRIBUTIONLOCATION, "");
    }
    else
    {
      setValue(DISTRIBUTIONLOCATION, value.getId());
    }
  }
  
  public boolean isDistributionLocationWritable()
  {
    return isWritable(DISTRIBUTIONLOCATION);
  }
  
  public boolean isDistributionLocationReadable()
  {
    return isReadable(DISTRIBUTIONLOCATION);
  }
  
  public boolean isDistributionLocationModified()
  {
    return isModified(DISTRIBUTIONLOCATION);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getDistributionLocationMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DISTRIBUTIONLOCATION).getAttributeMdDTO();
  }
  
  public java.util.Date getEndDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(ENDDATE));
  }
  
  public void setEndDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(ENDDATE, "");
    }
    else
    {
      setValue(ENDDATE, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isEndDateWritable()
  {
    return isWritable(ENDDATE);
  }
  
  public boolean isEndDateReadable()
  {
    return isReadable(ENDDATE);
  }
  
  public boolean isEndDateModified()
  {
    return isModified(ENDDATE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateMdDTO getEndDateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO(ENDDATE).getAttributeMdDTO();
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
  
  public Boolean getEntryType()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENTRYTYPE));
  }
  
  public void setEntryType(Boolean value)
  {
    if(value == null)
    {
      setValue(ENTRYTYPE, "");
    }
    else
    {
      setValue(ENTRYTYPE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isEntryTypeWritable()
  {
    return isWritable(ENTRYTYPE);
  }
  
  public boolean isEntryTypeReadable()
  {
    return isReadable(ENTRYTYPE);
  }
  
  public boolean isEntryTypeModified()
  {
    return isModified(ENTRYTYPE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getEntryTypeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ENTRYTYPE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.geo.generated.GeoEntityDTO getHouseholdAddress()
  {
    if(getValue(HOUSEHOLDADDRESS) == null || getValue(HOUSEHOLDADDRESS).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntityDTO.get(getRequest(), getValue(HOUSEHOLDADDRESS));
    }
  }
  
  public void setHouseholdAddress(dss.vector.solutions.geo.generated.GeoEntityDTO value)
  {
    if(value == null)
    {
      setValue(HOUSEHOLDADDRESS, "");
    }
    else
    {
      setValue(HOUSEHOLDADDRESS, value.getId());
    }
  }
  
  public boolean isHouseholdAddressWritable()
  {
    return isWritable(HOUSEHOLDADDRESS);
  }
  
  public boolean isHouseholdAddressReadable()
  {
    return isReadable(HOUSEHOLDADDRESS);
  }
  
  public boolean isHouseholdAddressModified()
  {
    return isModified(HOUSEHOLDADDRESS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getHouseholdAddressMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(HOUSEHOLDADDRESS).getAttributeMdDTO();
  }
  
  public String getHouseholdName()
  {
    return getValue(HOUSEHOLDNAME);
  }
  
  public void setHouseholdName(String value)
  {
    if(value == null)
    {
      setValue(HOUSEHOLDNAME, "");
    }
    else
    {
      setValue(HOUSEHOLDNAME, value);
    }
  }
  
  public boolean isHouseholdNameWritable()
  {
    return isWritable(HOUSEHOLDNAME);
  }
  
  public boolean isHouseholdNameReadable()
  {
    return isReadable(HOUSEHOLDNAME);
  }
  
  public boolean isHouseholdNameModified()
  {
    return isModified(HOUSEHOLDNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getHouseholdNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(HOUSEHOLDNAME).getAttributeMdDTO();
  }
  
  public String getHouseholdSurname()
  {
    return getValue(HOUSEHOLDSURNAME);
  }
  
  public void setHouseholdSurname(String value)
  {
    if(value == null)
    {
      setValue(HOUSEHOLDSURNAME, "");
    }
    else
    {
      setValue(HOUSEHOLDSURNAME, value);
    }
  }
  
  public boolean isHouseholdSurnameWritable()
  {
    return isWritable(HOUSEHOLDSURNAME);
  }
  
  public boolean isHouseholdSurnameReadable()
  {
    return isReadable(HOUSEHOLDSURNAME);
  }
  
  public boolean isHouseholdSurnameModified()
  {
    return isModified(HOUSEHOLDSURNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getHouseholdSurnameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(HOUSEHOLDSURNAME).getAttributeMdDTO();
  }
  
  public Integer getItnsReceived()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(ITNSRECEIVED));
  }
  
  public void setItnsReceived(Integer value)
  {
    if(value == null)
    {
      setValue(ITNSRECEIVED, "");
    }
    else
    {
      setValue(ITNSRECEIVED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isItnsReceivedWritable()
  {
    return isWritable(ITNSRECEIVED);
  }
  
  public boolean isItnsReceivedReadable()
  {
    return isReadable(ITNSRECEIVED);
  }
  
  public boolean isItnsReceivedModified()
  {
    return isModified(ITNSRECEIVED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getItnsReceivedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(ITNSRECEIVED).getAttributeMdDTO();
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
  
  public Integer getNumberRetrieved()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERRETRIEVED));
  }
  
  public void setNumberRetrieved(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERRETRIEVED, "");
    }
    else
    {
      setValue(NUMBERRETRIEVED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberRetrievedWritable()
  {
    return isWritable(NUMBERRETRIEVED);
  }
  
  public boolean isNumberRetrievedReadable()
  {
    return isReadable(NUMBERRETRIEVED);
  }
  
  public boolean isNumberRetrievedModified()
  {
    return isModified(NUMBERRETRIEVED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getNumberRetrievedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERRETRIEVED).getAttributeMdDTO();
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
  
  public Boolean getPretreated()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(PRETREATED));
  }
  
  public void setPretreated(Boolean value)
  {
    if(value == null)
    {
      setValue(PRETREATED, "");
    }
    else
    {
      setValue(PRETREATED, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isPretreatedWritable()
  {
    return isWritable(PRETREATED);
  }
  
  public boolean isPretreatedReadable()
  {
    return isReadable(PRETREATED);
  }
  
  public boolean isPretreatedModified()
  {
    return isModified(PRETREATED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getPretreatedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(PRETREATED).getAttributeMdDTO();
  }
  
  public Integer getResidents()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(RESIDENTS));
  }
  
  public void setResidents(Integer value)
  {
    if(value == null)
    {
      setValue(RESIDENTS, "");
    }
    else
    {
      setValue(RESIDENTS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isResidentsWritable()
  {
    return isWritable(RESIDENTS);
  }
  
  public boolean isResidentsReadable()
  {
    return isReadable(RESIDENTS);
  }
  
  public boolean isResidentsModified()
  {
    return isModified(RESIDENTS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getResidentsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(RESIDENTS).getAttributeMdDTO();
  }
  
  public Boolean getRetrieved()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(RETRIEVED));
  }
  
  public void setRetrieved(Boolean value)
  {
    if(value == null)
    {
      setValue(RETRIEVED, "");
    }
    else
    {
      setValue(RETRIEVED, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isRetrievedWritable()
  {
    return isWritable(RETRIEVED);
  }
  
  public boolean isRetrievedReadable()
  {
    return isReadable(RETRIEVED);
  }
  
  public boolean isRetrievedModified()
  {
    return isModified(RETRIEVED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getRetrievedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(RETRIEVED).getAttributeMdDTO();
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
  
  public Boolean getSold()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(SOLD));
  }
  
  public void setSold(Boolean value)
  {
    if(value == null)
    {
      setValue(SOLD, "");
    }
    else
    {
      setValue(SOLD, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isSoldWritable()
  {
    return isWritable(SOLD);
  }
  
  public boolean isSoldReadable()
  {
    return isReadable(SOLD);
  }
  
  public boolean isSoldModified()
  {
    return isModified(SOLD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getSoldMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(SOLD).getAttributeMdDTO();
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
  
  public static final dss.vector.solutions.intervention.monitor.ITNCommunityDistributionViewDTO getView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNCommunityDistributionViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.ITNCommunityDistributionViewDTO lockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNCommunityDistributionViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.ITNCommunityDistributionViewDTO lockView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNCommunityDistributionViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.ITNCommunityDistributionViewDTO unlockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNCommunityDistributionViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.ITNCommunityDistributionViewDTO unlockView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNCommunityDistributionViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllNets()
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllNets(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO> getAllNetsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO> getAllNetsRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO addNets(dss.vector.solutions.ontology.TermDTO child)
  {
    return (dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO addNets(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.ontology.TermDTO child)
  {
    return (dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO.CLASS);
  }
  
  public void removeNets(dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeNets(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllNets()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO.CLASS);
  }
  
  public static void removeAllNets(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllTargetGroups()
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllTargetGroups(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO> getAllTargetGroupsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO> getAllTargetGroupsRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO addTargetGroups(dss.vector.solutions.ontology.TermDTO child)
  {
    return (dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO addTargetGroups(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.ontology.TermDTO child)
  {
    return (dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO.CLASS);
  }
  
  public void removeTargetGroups(dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeTargetGroups(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllTargetGroups()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO.CLASS);
  }
  
  public static void removeAllTargetGroups(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO) dto;
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
  
  public static dss.vector.solutions.intervention.monitor.ITNCommunityDistributionQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.intervention.monitor.ITNCommunityDistributionQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.intervention.monitor.ITNCommunityDistribution", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
