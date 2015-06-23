package dss.vector.solutions.intervention.monitor;

@com.runwaysdk.business.ClassSignature(hash = 1933013634)
public abstract class ITNDistributionDTOBase extends com.runwaysdk.business.BusinessDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.ITNDistribution";
  private static final long serialVersionUID = 1933013634;
  
  protected ITNDistributionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected ITNDistributionDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String BATCHNUMBER = "batchNumber";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String CURRENCYRECEIVED = "currencyReceived";
  public static java.lang.String DISEASE = "disease";
  public static java.lang.String DISTRIBUTIONDATE = "distributionDate";
  public static java.lang.String DISTRIBUTORNAME = "distributorName";
  public static java.lang.String DISTRIBUTORSURNAME = "distributorSurname";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String FACILITY = "facility";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String NET = "net";
  public static java.lang.String NUMBERSOLD = "numberSold";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String RECIPIENT = "recipient";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SERVICE = "service";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TYPE = "type";
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getBatchNumberMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(BATCHNUMBER).getAttributeMdDTO();
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
  
  public Double getCurrencyReceived()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(CURRENCYRECEIVED));
  }
  
  public void setCurrencyReceived(Double value)
  {
    if(value == null)
    {
      setValue(CURRENCYRECEIVED, "");
    }
    else
    {
      setValue(CURRENCYRECEIVED, java.lang.Double.toString(value));
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
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getCurrencyReceivedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(CURRENCYRECEIVED).getAttributeMdDTO();
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
  
  public java.util.Date getDistributionDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(DISTRIBUTIONDATE));
  }
  
  public void setDistributionDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(DISTRIBUTIONDATE, "");
    }
    else
    {
      setValue(DISTRIBUTIONDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isDistributionDateWritable()
  {
    return isWritable(DISTRIBUTIONDATE);
  }
  
  public boolean isDistributionDateReadable()
  {
    return isReadable(DISTRIBUTIONDATE);
  }
  
  public boolean isDistributionDateModified()
  {
    return isModified(DISTRIBUTIONDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getDistributionDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(DISTRIBUTIONDATE).getAttributeMdDTO();
  }
  
  public String getDistributorName()
  {
    return getValue(DISTRIBUTORNAME);
  }
  
  public void setDistributorName(String value)
  {
    if(value == null)
    {
      setValue(DISTRIBUTORNAME, "");
    }
    else
    {
      setValue(DISTRIBUTORNAME, value);
    }
  }
  
  public boolean isDistributorNameWritable()
  {
    return isWritable(DISTRIBUTORNAME);
  }
  
  public boolean isDistributorNameReadable()
  {
    return isReadable(DISTRIBUTORNAME);
  }
  
  public boolean isDistributorNameModified()
  {
    return isModified(DISTRIBUTORNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getDistributorNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(DISTRIBUTORNAME).getAttributeMdDTO();
  }
  
  public String getDistributorSurname()
  {
    return getValue(DISTRIBUTORSURNAME);
  }
  
  public void setDistributorSurname(String value)
  {
    if(value == null)
    {
      setValue(DISTRIBUTORSURNAME, "");
    }
    else
    {
      setValue(DISTRIBUTORSURNAME, value);
    }
  }
  
  public boolean isDistributorSurnameWritable()
  {
    return isWritable(DISTRIBUTORSURNAME);
  }
  
  public boolean isDistributorSurnameReadable()
  {
    return isReadable(DISTRIBUTORSURNAME);
  }
  
  public boolean isDistributorSurnameModified()
  {
    return isModified(DISTRIBUTORSURNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getDistributorSurnameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(DISTRIBUTORSURNAME).getAttributeMdDTO();
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
  
  public dss.vector.solutions.geo.generated.HealthFacilityDTO getFacility()
  {
    if(getValue(FACILITY) == null || getValue(FACILITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.HealthFacilityDTO.get(getRequest(), getValue(FACILITY));
    }
  }
  
  public String getFacilityId()
  {
    return getValue(FACILITY);
  }
  
  public void setFacility(dss.vector.solutions.geo.generated.HealthFacilityDTO value)
  {
    if(value == null)
    {
      setValue(FACILITY, "");
    }
    else
    {
      setValue(FACILITY, value.getId());
    }
  }
  
  public boolean isFacilityWritable()
  {
    return isWritable(FACILITY);
  }
  
  public boolean isFacilityReadable()
  {
    return isReadable(FACILITY);
  }
  
  public boolean isFacilityModified()
  {
    return isModified(FACILITY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getFacilityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(FACILITY).getAttributeMdDTO();
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
  
  public dss.vector.solutions.ontology.TermDTO getNet()
  {
    if(getValue(NET) == null || getValue(NET).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(NET));
    }
  }
  
  public String getNetId()
  {
    return getValue(NET);
  }
  
  public void setNet(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(NET, "");
    }
    else
    {
      setValue(NET, value.getId());
    }
  }
  
  public boolean isNetWritable()
  {
    return isWritable(NET);
  }
  
  public boolean isNetReadable()
  {
    return isReadable(NET);
  }
  
  public boolean isNetModified()
  {
    return isModified(NET);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getNetMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(NET).getAttributeMdDTO();
  }
  
  public Integer getNumberSold()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERSOLD));
  }
  
  public void setNumberSold(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERSOLD, "");
    }
    else
    {
      setValue(NUMBERSOLD, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberSoldWritable()
  {
    return isWritable(NUMBERSOLD);
  }
  
  public boolean isNumberSoldReadable()
  {
    return isReadable(NUMBERSOLD);
  }
  
  public boolean isNumberSoldModified()
  {
    return isModified(NUMBERSOLD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberSoldMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERSOLD).getAttributeMdDTO();
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
  
  public dss.vector.solutions.intervention.monitor.ITNRecipientDTO getRecipient()
  {
    if(getValue(RECIPIENT) == null || getValue(RECIPIENT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.intervention.monitor.ITNRecipientDTO.get(getRequest(), getValue(RECIPIENT));
    }
  }
  
  public String getRecipientId()
  {
    return getValue(RECIPIENT);
  }
  
  public void setRecipient(dss.vector.solutions.intervention.monitor.ITNRecipientDTO value)
  {
    if(value == null)
    {
      setValue(RECIPIENT, "");
    }
    else
    {
      setValue(RECIPIENT, value.getId());
    }
  }
  
  public boolean isRecipientWritable()
  {
    return isWritable(RECIPIENT);
  }
  
  public boolean isRecipientReadable()
  {
    return isReadable(RECIPIENT);
  }
  
  public boolean isRecipientModified()
  {
    return isModified(RECIPIENT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getRecipientMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(RECIPIENT).getAttributeMdDTO();
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
  
  public dss.vector.solutions.ontology.TermDTO getService()
  {
    if(getValue(SERVICE) == null || getValue(SERVICE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(SERVICE));
    }
  }
  
  public String getServiceId()
  {
    return getValue(SERVICE);
  }
  
  public void setService(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(SERVICE, "");
    }
    else
    {
      setValue(SERVICE, value.getId());
    }
  }
  
  public boolean isServiceWritable()
  {
    return isWritable(SERVICE);
  }
  
  public boolean isServiceReadable()
  {
    return isReadable(SERVICE);
  }
  
  public boolean isServiceModified()
  {
    return isModified(SERVICE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getServiceMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SERVICE).getAttributeMdDTO();
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
  
  public static final dss.vector.solutions.intervention.monitor.ITNDistributionViewDTO getView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNDistributionDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNDistributionViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.ITNDistributionViewDTO lockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNDistributionDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNDistributionViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.ITNDistributionViewDTO lockView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNDistributionDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNDistributionViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.ITNDistributionViewDTO unlockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNDistributionDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNDistributionViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.ITNDistributionViewDTO unlockView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNDistributionDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNDistributionViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllTargetGroups()
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllTargetGroups(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO> getAllTargetGroupsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO> getAllTargetGroupsRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO addTargetGroups(dss.vector.solutions.ontology.TermDTO child)
  {
    return (dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO addTargetGroups(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.ontology.TermDTO child)
  {
    return (dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO.CLASS);
  }
  
  public void removeTargetGroups(dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeTargetGroups(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllTargetGroups()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO.CLASS);
  }
  
  public static void removeAllTargetGroups(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.ITNDistributionDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.intervention.monitor.ITNDistributionDTO) dto;
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
  
  public static dss.vector.solutions.intervention.monitor.ITNDistributionQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.intervention.monitor.ITNDistributionQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.intervention.monitor.ITNDistributionDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.intervention.monitor.ITNDistributionDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNDistributionDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNDistributionDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.intervention.monitor.ITNDistributionDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNDistributionDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNDistributionDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
