package dss.vector.solutions.intervention.monitor;

@com.runwaysdk.business.ClassSignature(hash = -666624792)
public abstract class IndividualCaseDTOBase extends com.runwaysdk.business.BusinessDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.IndividualCase";
  private static final long serialVersionUID = -666624792;
  
  protected IndividualCaseDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected IndividualCaseDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String AGE = "age";
  public static java.lang.String CASEENTRYDATE = "caseEntryDate";
  public static java.lang.String CASEREPORTDATE = "caseReportDate";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DIAGNOSISDATE = "diagnosisDate";
  public static java.lang.String DISEASE = "disease";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String HEMORRHAGICONSET = "hemorrhagicOnset";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String ORIGIN = "origin";
  public static java.lang.String OTHERSETTLEMENTS = "otherSettlements";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String PATIENT = "patient";
  public static java.lang.String PLASMALEAKAGEONSET = "plasmaLeakageOnset";
  public static java.lang.String PROBABLESOURCE = "probableSource";
  public static java.lang.String PROBABLESOURCETEXT = "probableSourceText";
  public static java.lang.String RESIDENCE = "residence";
  public static java.lang.String RESIDENCETEXT = "residenceText";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String SYMPTOMONSET = "symptomOnset";
  public static java.lang.String TYPE = "type";
  public static java.lang.String WORKPLACE = "workplace";
  public static java.lang.String WORKPLACETEXT = "workplaceText";
  public Integer getAge()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(AGE));
  }
  
  public void setAge(Integer value)
  {
    if(value == null)
    {
      setValue(AGE, "");
    }
    else
    {
      setValue(AGE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isAgeWritable()
  {
    return isWritable(AGE);
  }
  
  public boolean isAgeReadable()
  {
    return isReadable(AGE);
  }
  
  public boolean isAgeModified()
  {
    return isModified(AGE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getAgeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(AGE).getAttributeMdDTO();
  }
  
  public java.util.Date getCaseEntryDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(CASEENTRYDATE));
  }
  
  public void setCaseEntryDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(CASEENTRYDATE, "");
    }
    else
    {
      setValue(CASEENTRYDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isCaseEntryDateWritable()
  {
    return isWritable(CASEENTRYDATE);
  }
  
  public boolean isCaseEntryDateReadable()
  {
    return isReadable(CASEENTRYDATE);
  }
  
  public boolean isCaseEntryDateModified()
  {
    return isModified(CASEENTRYDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getCaseEntryDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(CASEENTRYDATE).getAttributeMdDTO();
  }
  
  public java.util.Date getCaseReportDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(CASEREPORTDATE));
  }
  
  public void setCaseReportDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(CASEREPORTDATE, "");
    }
    else
    {
      setValue(CASEREPORTDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isCaseReportDateWritable()
  {
    return isWritable(CASEREPORTDATE);
  }
  
  public boolean isCaseReportDateReadable()
  {
    return isReadable(CASEREPORTDATE);
  }
  
  public boolean isCaseReportDateModified()
  {
    return isModified(CASEREPORTDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getCaseReportDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(CASEREPORTDATE).getAttributeMdDTO();
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
  
  public java.util.Date getDiagnosisDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(DIAGNOSISDATE));
  }
  
  public void setDiagnosisDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(DIAGNOSISDATE, "");
    }
    else
    {
      setValue(DIAGNOSISDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isDiagnosisDateWritable()
  {
    return isWritable(DIAGNOSISDATE);
  }
  
  public boolean isDiagnosisDateReadable()
  {
    return isReadable(DIAGNOSISDATE);
  }
  
  public boolean isDiagnosisDateModified()
  {
    return isModified(DIAGNOSISDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getDiagnosisDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(DIAGNOSISDATE).getAttributeMdDTO();
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
  
  public java.util.Date getHemorrhagicOnset()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(HEMORRHAGICONSET));
  }
  
  public void setHemorrhagicOnset(java.util.Date value)
  {
    if(value == null)
    {
      setValue(HEMORRHAGICONSET, "");
    }
    else
    {
      setValue(HEMORRHAGICONSET, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isHemorrhagicOnsetWritable()
  {
    return isWritable(HEMORRHAGICONSET);
  }
  
  public boolean isHemorrhagicOnsetReadable()
  {
    return isReadable(HEMORRHAGICONSET);
  }
  
  public boolean isHemorrhagicOnsetModified()
  {
    return isModified(HEMORRHAGICONSET);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getHemorrhagicOnsetMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(HEMORRHAGICONSET).getAttributeMdDTO();
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
  
  public dss.vector.solutions.ontology.TermDTO getOrigin()
  {
    if(getValue(ORIGIN) == null || getValue(ORIGIN).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(ORIGIN));
    }
  }
  
  public String getOriginId()
  {
    return getValue(ORIGIN);
  }
  
  public void setOrigin(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(ORIGIN, "");
    }
    else
    {
      setValue(ORIGIN, value.getId());
    }
  }
  
  public boolean isOriginWritable()
  {
    return isWritable(ORIGIN);
  }
  
  public boolean isOriginReadable()
  {
    return isReadable(ORIGIN);
  }
  
  public boolean isOriginModified()
  {
    return isModified(ORIGIN);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getOriginMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ORIGIN).getAttributeMdDTO();
  }
  
  public String getOtherSettlements()
  {
    return getValue(OTHERSETTLEMENTS);
  }
  
  public void setOtherSettlements(String value)
  {
    if(value == null)
    {
      setValue(OTHERSETTLEMENTS, "");
    }
    else
    {
      setValue(OTHERSETTLEMENTS, value);
    }
  }
  
  public boolean isOtherSettlementsWritable()
  {
    return isWritable(OTHERSETTLEMENTS);
  }
  
  public boolean isOtherSettlementsReadable()
  {
    return isReadable(OTHERSETTLEMENTS);
  }
  
  public boolean isOtherSettlementsModified()
  {
    return isModified(OTHERSETTLEMENTS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getOtherSettlementsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(OTHERSETTLEMENTS).getAttributeMdDTO();
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
  
  public dss.vector.solutions.PatientDTO getPatient()
  {
    if(getValue(PATIENT) == null || getValue(PATIENT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.PatientDTO.get(getRequest(), getValue(PATIENT));
    }
  }
  
  public String getPatientId()
  {
    return getValue(PATIENT);
  }
  
  public void setPatient(dss.vector.solutions.PatientDTO value)
  {
    if(value == null)
    {
      setValue(PATIENT, "");
    }
    else
    {
      setValue(PATIENT, value.getId());
    }
  }
  
  public boolean isPatientWritable()
  {
    return isWritable(PATIENT);
  }
  
  public boolean isPatientReadable()
  {
    return isReadable(PATIENT);
  }
  
  public boolean isPatientModified()
  {
    return isModified(PATIENT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getPatientMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(PATIENT).getAttributeMdDTO();
  }
  
  public java.util.Date getPlasmaLeakageOnset()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(PLASMALEAKAGEONSET));
  }
  
  public void setPlasmaLeakageOnset(java.util.Date value)
  {
    if(value == null)
    {
      setValue(PLASMALEAKAGEONSET, "");
    }
    else
    {
      setValue(PLASMALEAKAGEONSET, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isPlasmaLeakageOnsetWritable()
  {
    return isWritable(PLASMALEAKAGEONSET);
  }
  
  public boolean isPlasmaLeakageOnsetReadable()
  {
    return isReadable(PLASMALEAKAGEONSET);
  }
  
  public boolean isPlasmaLeakageOnsetModified()
  {
    return isModified(PLASMALEAKAGEONSET);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getPlasmaLeakageOnsetMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(PLASMALEAKAGEONSET).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.geo.generated.GeoEntityDTO getProbableSource()
  {
    if(getValue(PROBABLESOURCE) == null || getValue(PROBABLESOURCE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntityDTO.get(getRequest(), getValue(PROBABLESOURCE));
    }
  }
  
  public String getProbableSourceId()
  {
    return getValue(PROBABLESOURCE);
  }
  
  public void setProbableSource(dss.vector.solutions.geo.generated.GeoEntityDTO value)
  {
    if(value == null)
    {
      setValue(PROBABLESOURCE, "");
    }
    else
    {
      setValue(PROBABLESOURCE, value.getId());
    }
  }
  
  public boolean isProbableSourceWritable()
  {
    return isWritable(PROBABLESOURCE);
  }
  
  public boolean isProbableSourceReadable()
  {
    return isReadable(PROBABLESOURCE);
  }
  
  public boolean isProbableSourceModified()
  {
    return isModified(PROBABLESOURCE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getProbableSourceMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(PROBABLESOURCE).getAttributeMdDTO();
  }
  
  public String getProbableSourceText()
  {
    return getValue(PROBABLESOURCETEXT);
  }
  
  public void setProbableSourceText(String value)
  {
    if(value == null)
    {
      setValue(PROBABLESOURCETEXT, "");
    }
    else
    {
      setValue(PROBABLESOURCETEXT, value);
    }
  }
  
  public boolean isProbableSourceTextWritable()
  {
    return isWritable(PROBABLESOURCETEXT);
  }
  
  public boolean isProbableSourceTextReadable()
  {
    return isReadable(PROBABLESOURCETEXT);
  }
  
  public boolean isProbableSourceTextModified()
  {
    return isModified(PROBABLESOURCETEXT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getProbableSourceTextMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(PROBABLESOURCETEXT).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.geo.generated.GeoEntityDTO getResidence()
  {
    if(getValue(RESIDENCE) == null || getValue(RESIDENCE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntityDTO.get(getRequest(), getValue(RESIDENCE));
    }
  }
  
  public String getResidenceId()
  {
    return getValue(RESIDENCE);
  }
  
  public void setResidence(dss.vector.solutions.geo.generated.GeoEntityDTO value)
  {
    if(value == null)
    {
      setValue(RESIDENCE, "");
    }
    else
    {
      setValue(RESIDENCE, value.getId());
    }
  }
  
  public boolean isResidenceWritable()
  {
    return isWritable(RESIDENCE);
  }
  
  public boolean isResidenceReadable()
  {
    return isReadable(RESIDENCE);
  }
  
  public boolean isResidenceModified()
  {
    return isModified(RESIDENCE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getResidenceMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(RESIDENCE).getAttributeMdDTO();
  }
  
  public String getResidenceText()
  {
    return getValue(RESIDENCETEXT);
  }
  
  public void setResidenceText(String value)
  {
    if(value == null)
    {
      setValue(RESIDENCETEXT, "");
    }
    else
    {
      setValue(RESIDENCETEXT, value);
    }
  }
  
  public boolean isResidenceTextWritable()
  {
    return isWritable(RESIDENCETEXT);
  }
  
  public boolean isResidenceTextReadable()
  {
    return isReadable(RESIDENCETEXT);
  }
  
  public boolean isResidenceTextModified()
  {
    return isModified(RESIDENCETEXT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getResidenceTextMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(RESIDENCETEXT).getAttributeMdDTO();
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
  
  public java.util.Date getSymptomOnset()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(SYMPTOMONSET));
  }
  
  public void setSymptomOnset(java.util.Date value)
  {
    if(value == null)
    {
      setValue(SYMPTOMONSET, "");
    }
    else
    {
      setValue(SYMPTOMONSET, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isSymptomOnsetWritable()
  {
    return isWritable(SYMPTOMONSET);
  }
  
  public boolean isSymptomOnsetReadable()
  {
    return isReadable(SYMPTOMONSET);
  }
  
  public boolean isSymptomOnsetModified()
  {
    return isModified(SYMPTOMONSET);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getSymptomOnsetMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(SYMPTOMONSET).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.geo.generated.GeoEntityDTO getWorkplace()
  {
    if(getValue(WORKPLACE) == null || getValue(WORKPLACE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntityDTO.get(getRequest(), getValue(WORKPLACE));
    }
  }
  
  public String getWorkplaceId()
  {
    return getValue(WORKPLACE);
  }
  
  public void setWorkplace(dss.vector.solutions.geo.generated.GeoEntityDTO value)
  {
    if(value == null)
    {
      setValue(WORKPLACE, "");
    }
    else
    {
      setValue(WORKPLACE, value.getId());
    }
  }
  
  public boolean isWorkplaceWritable()
  {
    return isWritable(WORKPLACE);
  }
  
  public boolean isWorkplaceReadable()
  {
    return isReadable(WORKPLACE);
  }
  
  public boolean isWorkplaceModified()
  {
    return isModified(WORKPLACE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getWorkplaceMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(WORKPLACE).getAttributeMdDTO();
  }
  
  public String getWorkplaceText()
  {
    return getValue(WORKPLACETEXT);
  }
  
  public void setWorkplaceText(String value)
  {
    if(value == null)
    {
      setValue(WORKPLACETEXT, "");
    }
    else
    {
      setValue(WORKPLACETEXT, value);
    }
  }
  
  public boolean isWorkplaceTextWritable()
  {
    return isWritable(WORKPLACETEXT);
  }
  
  public boolean isWorkplaceTextReadable()
  {
    return isReadable(WORKPLACETEXT);
  }
  
  public boolean isWorkplaceTextModified()
  {
    return isModified(WORKPLACETEXT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getWorkplaceTextMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(WORKPLACETEXT).getAttributeMdDTO();
  }
  
  public final void applyWithPersonId(java.lang.String personId, dss.vector.solutions.intervention.monitor.IndividualInstanceDTO instance, dss.vector.solutions.ontology.TermDTO[] symptoms)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "dss.vector.solutions.intervention.monitor.IndividualInstance", "[Ldss.vector.solutions.ontology.Term;"};
    Object[] _parameters = new Object[]{personId, instance, symptoms};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.IndividualCaseDTO.CLASS, "applyWithPersonId", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void applyWithPersonId(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String personId, dss.vector.solutions.intervention.monitor.IndividualInstanceDTO instance, dss.vector.solutions.ontology.TermDTO[] symptoms)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "dss.vector.solutions.intervention.monitor.IndividualInstance", "[Ldss.vector.solutions.ontology.Term;"};
    Object[] _parameters = new Object[]{id, personId, instance, symptoms};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.IndividualCaseDTO.CLASS, "applyWithPersonId", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.IndividualInstanceQueryDTO getInstances()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.IndividualCaseDTO.CLASS, "getInstances", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.IndividualInstanceQueryDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.IndividualInstanceQueryDTO getInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.IndividualCaseDTO.CLASS, "getInstances", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.IndividualInstanceQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.IndividualCaseDTO searchForExistingCase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Date diagnosisDate, java.lang.String patientId)
  {
    String[] _declaredTypes = new String[]{"java.util.Date", "java.lang.String"};
    Object[] _parameters = new Object[]{diagnosisDate, patientId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.IndividualCaseDTO.CLASS, "searchForExistingCase", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.IndividualCaseDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static dss.vector.solutions.intervention.monitor.IndividualCaseDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.intervention.monitor.IndividualCaseDTO) dto;
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
  
  public static dss.vector.solutions.intervention.monitor.IndividualCaseQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.intervention.monitor.IndividualCaseQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.intervention.monitor.IndividualCaseDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.intervention.monitor.IndividualCaseDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.IndividualCaseDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.IndividualCaseDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.intervention.monitor.IndividualCaseDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.IndividualCaseDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.IndividualCaseDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
