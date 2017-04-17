package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = 1131335240)
public abstract class ThresholdCalculationTypeDTOBase extends com.runwaysdk.business.BusinessDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.ThresholdCalculationType";
  private static final long serialVersionUID = 1131335240;
  
  protected ThresholdCalculationTypeDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected ThresholdCalculationTypeDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CASETYPES = "caseTypes";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DISEASE = "disease";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String ID = "id";
  public static java.lang.String IDENTIFICATIONMINIMUM = "identificationMinimum";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String NOTIFICATIONMINIMUM = "notificationMinimum";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String PRIORYEARS = "priorYears";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String T1METHOD = "t1Method";
  public static java.lang.String T2METHOD = "t2Method";
  public static java.lang.String TYPE = "type";
  public static java.lang.String WEEKSAFTER = "weeksAfter";
  public static java.lang.String WEEKSBEFORE = "weeksBefore";
  public static java.lang.String WEIGHT0 = "weight0";
  public static java.lang.String WEIGHT1 = "weight1";
  public static java.lang.String WEIGHT2 = "weight2";
  public static java.lang.String WEIGHT3 = "weight3";
  public static java.lang.String WEIGHT4 = "weight4";
  public static java.lang.String WEIGHT5 = "weight5";
  public static java.lang.String WEIGHT6 = "weight6";
  public static java.lang.String WEIGHT7 = "weight7";
  public static java.lang.String WEIGHT8 = "weight8";
  public static java.lang.String WEIGHT9 = "weight9";
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.general.ThresholdCalculationCaseTypesDTO> getCaseTypes()
  {
    return (java.util.List<dss.vector.solutions.general.ThresholdCalculationCaseTypesDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.general.ThresholdCalculationCaseTypesDTO.CLASS, getEnumNames(CASETYPES));
  }
  
  public java.util.List<String> getCaseTypesEnumNames()
  {
    return getEnumNames(CASETYPES);
  }
  
  public void addCaseTypes(dss.vector.solutions.general.ThresholdCalculationCaseTypesDTO enumDTO)
  {
    addEnumItem(CASETYPES, enumDTO.toString());
  }
  
  public void removeCaseTypes(dss.vector.solutions.general.ThresholdCalculationCaseTypesDTO enumDTO)
  {
    removeEnumItem(CASETYPES, enumDTO.toString());
  }
  
  public void clearCaseTypes()
  {
    clearEnum(CASETYPES);
  }
  
  public boolean isCaseTypesWritable()
  {
    return isWritable(CASETYPES);
  }
  
  public boolean isCaseTypesReadable()
  {
    return isReadable(CASETYPES);
  }
  
  public boolean isCaseTypesModified()
  {
    return isModified(CASETYPES);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getCaseTypesMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(CASETYPES).getAttributeMdDTO();
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
  
  public Double getIdentificationMinimum()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATIONMINIMUM));
  }
  
  public void setIdentificationMinimum(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATIONMINIMUM, "");
    }
    else
    {
      setValue(IDENTIFICATIONMINIMUM, java.lang.Double.toString(value));
    }
  }
  
  public boolean isIdentificationMinimumWritable()
  {
    return isWritable(IDENTIFICATIONMINIMUM);
  }
  
  public boolean isIdentificationMinimumReadable()
  {
    return isReadable(IDENTIFICATIONMINIMUM);
  }
  
  public boolean isIdentificationMinimumModified()
  {
    return isModified(IDENTIFICATIONMINIMUM);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentificationMinimumMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATIONMINIMUM).getAttributeMdDTO();
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
  
  public Double getNotificationMinimum()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(NOTIFICATIONMINIMUM));
  }
  
  public void setNotificationMinimum(Double value)
  {
    if(value == null)
    {
      setValue(NOTIFICATIONMINIMUM, "");
    }
    else
    {
      setValue(NOTIFICATIONMINIMUM, java.lang.Double.toString(value));
    }
  }
  
  public boolean isNotificationMinimumWritable()
  {
    return isWritable(NOTIFICATIONMINIMUM);
  }
  
  public boolean isNotificationMinimumReadable()
  {
    return isReadable(NOTIFICATIONMINIMUM);
  }
  
  public boolean isNotificationMinimumModified()
  {
    return isModified(NOTIFICATIONMINIMUM);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getNotificationMinimumMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(NOTIFICATIONMINIMUM).getAttributeMdDTO();
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
  
  public Integer getPriorYears()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PRIORYEARS));
  }
  
  public void setPriorYears(Integer value)
  {
    if(value == null)
    {
      setValue(PRIORYEARS, "");
    }
    else
    {
      setValue(PRIORYEARS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPriorYearsWritable()
  {
    return isWritable(PRIORYEARS);
  }
  
  public boolean isPriorYearsReadable()
  {
    return isReadable(PRIORYEARS);
  }
  
  public boolean isPriorYearsModified()
  {
    return isModified(PRIORYEARS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getPriorYearsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PRIORYEARS).getAttributeMdDTO();
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
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.general.ThresholdCalculationMethodDTO> getT1Method()
  {
    return (java.util.List<dss.vector.solutions.general.ThresholdCalculationMethodDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.general.ThresholdCalculationMethodDTO.CLASS, getEnumNames(T1METHOD));
  }
  
  public java.util.List<String> getT1MethodEnumNames()
  {
    return getEnumNames(T1METHOD);
  }
  
  public void addT1Method(dss.vector.solutions.general.ThresholdCalculationMethodDTO enumDTO)
  {
    addEnumItem(T1METHOD, enumDTO.toString());
  }
  
  public void removeT1Method(dss.vector.solutions.general.ThresholdCalculationMethodDTO enumDTO)
  {
    removeEnumItem(T1METHOD, enumDTO.toString());
  }
  
  public void clearT1Method()
  {
    clearEnum(T1METHOD);
  }
  
  public boolean isT1MethodWritable()
  {
    return isWritable(T1METHOD);
  }
  
  public boolean isT1MethodReadable()
  {
    return isReadable(T1METHOD);
  }
  
  public boolean isT1MethodModified()
  {
    return isModified(T1METHOD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getT1MethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(T1METHOD).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.general.ThresholdCalculationMethodDTO> getT2Method()
  {
    return (java.util.List<dss.vector.solutions.general.ThresholdCalculationMethodDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.general.ThresholdCalculationMethodDTO.CLASS, getEnumNames(T2METHOD));
  }
  
  public java.util.List<String> getT2MethodEnumNames()
  {
    return getEnumNames(T2METHOD);
  }
  
  public void addT2Method(dss.vector.solutions.general.ThresholdCalculationMethodDTO enumDTO)
  {
    addEnumItem(T2METHOD, enumDTO.toString());
  }
  
  public void removeT2Method(dss.vector.solutions.general.ThresholdCalculationMethodDTO enumDTO)
  {
    removeEnumItem(T2METHOD, enumDTO.toString());
  }
  
  public void clearT2Method()
  {
    clearEnum(T2METHOD);
  }
  
  public boolean isT2MethodWritable()
  {
    return isWritable(T2METHOD);
  }
  
  public boolean isT2MethodReadable()
  {
    return isReadable(T2METHOD);
  }
  
  public boolean isT2MethodModified()
  {
    return isModified(T2METHOD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getT2MethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(T2METHOD).getAttributeMdDTO();
  }
  
  public Integer getWeeksAfter()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(WEEKSAFTER));
  }
  
  public void setWeeksAfter(Integer value)
  {
    if(value == null)
    {
      setValue(WEEKSAFTER, "");
    }
    else
    {
      setValue(WEEKSAFTER, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isWeeksAfterWritable()
  {
    return isWritable(WEEKSAFTER);
  }
  
  public boolean isWeeksAfterReadable()
  {
    return isReadable(WEEKSAFTER);
  }
  
  public boolean isWeeksAfterModified()
  {
    return isModified(WEEKSAFTER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getWeeksAfterMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(WEEKSAFTER).getAttributeMdDTO();
  }
  
  public Integer getWeeksBefore()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(WEEKSBEFORE));
  }
  
  public void setWeeksBefore(Integer value)
  {
    if(value == null)
    {
      setValue(WEEKSBEFORE, "");
    }
    else
    {
      setValue(WEEKSBEFORE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isWeeksBeforeWritable()
  {
    return isWritable(WEEKSBEFORE);
  }
  
  public boolean isWeeksBeforeReadable()
  {
    return isReadable(WEEKSBEFORE);
  }
  
  public boolean isWeeksBeforeModified()
  {
    return isModified(WEEKSBEFORE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getWeeksBeforeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(WEEKSBEFORE).getAttributeMdDTO();
  }
  
  public Double getWeight0()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(WEIGHT0));
  }
  
  public void setWeight0(Double value)
  {
    if(value == null)
    {
      setValue(WEIGHT0, "");
    }
    else
    {
      setValue(WEIGHT0, java.lang.Double.toString(value));
    }
  }
  
  public boolean isWeight0Writable()
  {
    return isWritable(WEIGHT0);
  }
  
  public boolean isWeight0Readable()
  {
    return isReadable(WEIGHT0);
  }
  
  public boolean isWeight0Modified()
  {
    return isModified(WEIGHT0);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getWeight0Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(WEIGHT0).getAttributeMdDTO();
  }
  
  public Double getWeight1()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(WEIGHT1));
  }
  
  public void setWeight1(Double value)
  {
    if(value == null)
    {
      setValue(WEIGHT1, "");
    }
    else
    {
      setValue(WEIGHT1, java.lang.Double.toString(value));
    }
  }
  
  public boolean isWeight1Writable()
  {
    return isWritable(WEIGHT1);
  }
  
  public boolean isWeight1Readable()
  {
    return isReadable(WEIGHT1);
  }
  
  public boolean isWeight1Modified()
  {
    return isModified(WEIGHT1);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getWeight1Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(WEIGHT1).getAttributeMdDTO();
  }
  
  public Double getWeight2()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(WEIGHT2));
  }
  
  public void setWeight2(Double value)
  {
    if(value == null)
    {
      setValue(WEIGHT2, "");
    }
    else
    {
      setValue(WEIGHT2, java.lang.Double.toString(value));
    }
  }
  
  public boolean isWeight2Writable()
  {
    return isWritable(WEIGHT2);
  }
  
  public boolean isWeight2Readable()
  {
    return isReadable(WEIGHT2);
  }
  
  public boolean isWeight2Modified()
  {
    return isModified(WEIGHT2);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getWeight2Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(WEIGHT2).getAttributeMdDTO();
  }
  
  public Double getWeight3()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(WEIGHT3));
  }
  
  public void setWeight3(Double value)
  {
    if(value == null)
    {
      setValue(WEIGHT3, "");
    }
    else
    {
      setValue(WEIGHT3, java.lang.Double.toString(value));
    }
  }
  
  public boolean isWeight3Writable()
  {
    return isWritable(WEIGHT3);
  }
  
  public boolean isWeight3Readable()
  {
    return isReadable(WEIGHT3);
  }
  
  public boolean isWeight3Modified()
  {
    return isModified(WEIGHT3);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getWeight3Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(WEIGHT3).getAttributeMdDTO();
  }
  
  public Double getWeight4()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(WEIGHT4));
  }
  
  public void setWeight4(Double value)
  {
    if(value == null)
    {
      setValue(WEIGHT4, "");
    }
    else
    {
      setValue(WEIGHT4, java.lang.Double.toString(value));
    }
  }
  
  public boolean isWeight4Writable()
  {
    return isWritable(WEIGHT4);
  }
  
  public boolean isWeight4Readable()
  {
    return isReadable(WEIGHT4);
  }
  
  public boolean isWeight4Modified()
  {
    return isModified(WEIGHT4);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getWeight4Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(WEIGHT4).getAttributeMdDTO();
  }
  
  public Double getWeight5()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(WEIGHT5));
  }
  
  public void setWeight5(Double value)
  {
    if(value == null)
    {
      setValue(WEIGHT5, "");
    }
    else
    {
      setValue(WEIGHT5, java.lang.Double.toString(value));
    }
  }
  
  public boolean isWeight5Writable()
  {
    return isWritable(WEIGHT5);
  }
  
  public boolean isWeight5Readable()
  {
    return isReadable(WEIGHT5);
  }
  
  public boolean isWeight5Modified()
  {
    return isModified(WEIGHT5);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getWeight5Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(WEIGHT5).getAttributeMdDTO();
  }
  
  public Double getWeight6()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(WEIGHT6));
  }
  
  public void setWeight6(Double value)
  {
    if(value == null)
    {
      setValue(WEIGHT6, "");
    }
    else
    {
      setValue(WEIGHT6, java.lang.Double.toString(value));
    }
  }
  
  public boolean isWeight6Writable()
  {
    return isWritable(WEIGHT6);
  }
  
  public boolean isWeight6Readable()
  {
    return isReadable(WEIGHT6);
  }
  
  public boolean isWeight6Modified()
  {
    return isModified(WEIGHT6);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getWeight6Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(WEIGHT6).getAttributeMdDTO();
  }
  
  public Double getWeight7()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(WEIGHT7));
  }
  
  public void setWeight7(Double value)
  {
    if(value == null)
    {
      setValue(WEIGHT7, "");
    }
    else
    {
      setValue(WEIGHT7, java.lang.Double.toString(value));
    }
  }
  
  public boolean isWeight7Writable()
  {
    return isWritable(WEIGHT7);
  }
  
  public boolean isWeight7Readable()
  {
    return isReadable(WEIGHT7);
  }
  
  public boolean isWeight7Modified()
  {
    return isModified(WEIGHT7);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getWeight7Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(WEIGHT7).getAttributeMdDTO();
  }
  
  public Double getWeight8()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(WEIGHT8));
  }
  
  public void setWeight8(Double value)
  {
    if(value == null)
    {
      setValue(WEIGHT8, "");
    }
    else
    {
      setValue(WEIGHT8, java.lang.Double.toString(value));
    }
  }
  
  public boolean isWeight8Writable()
  {
    return isWritable(WEIGHT8);
  }
  
  public boolean isWeight8Readable()
  {
    return isReadable(WEIGHT8);
  }
  
  public boolean isWeight8Modified()
  {
    return isModified(WEIGHT8);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getWeight8Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(WEIGHT8).getAttributeMdDTO();
  }
  
  public Double getWeight9()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(WEIGHT9));
  }
  
  public void setWeight9(Double value)
  {
    if(value == null)
    {
      setValue(WEIGHT9, "");
    }
    else
    {
      setValue(WEIGHT9, java.lang.Double.toString(value));
    }
  }
  
  public boolean isWeight9Writable()
  {
    return isWritable(WEIGHT9);
  }
  
  public boolean isWeight9Readable()
  {
    return isReadable(WEIGHT9);
  }
  
  public boolean isWeight9Modified()
  {
    return isModified(WEIGHT9);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getWeight9Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(WEIGHT9).getAttributeMdDTO();
  }
  
  public static dss.vector.solutions.general.ThresholdCalculationTypeDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.general.ThresholdCalculationTypeDTO) dto;
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
  
  public static dss.vector.solutions.general.ThresholdCalculationTypeQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.general.ThresholdCalculationTypeQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.general.ThresholdCalculationTypeDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.general.ThresholdCalculationTypeDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.ThresholdCalculationTypeDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.general.ThresholdCalculationTypeDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.general.ThresholdCalculationTypeDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.ThresholdCalculationTypeDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.general.ThresholdCalculationTypeDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
