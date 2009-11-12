package dss.vector.solutions.intervention.monitor;

@com.terraframe.mojo.business.ClassSignature(hash = -1474821690)
public abstract class IndividualInstanceDTOBase extends com.terraframe.mojo.business.BusinessDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.IndividualInstance";
  private static final long serialVersionUID = -1474821690;
  
  protected IndividualInstanceDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected IndividualInstanceDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ACTIVELYDETECTED = "activelyDetected";
  public static java.lang.String ADMISSIONDATE = "admissionDate";
  public static java.lang.String ANAEMIAPATIENT = "anaemiaPatient";
  public static java.lang.String CLINICALDIAGNOSIS = "clinicalDiagnosis";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DETECTEDBY = "detectedBy";
  public static java.lang.String DIEDINFACILITY = "diedInFacility";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String FACILITYVISIT = "facilityVisit";
  public static java.lang.String HEALTHFACILITY = "healthFacility";
  public static java.lang.String ID = "id";
  public static java.lang.String INDIVIDUALCASE = "individualCase";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LABTEST = "labTest";
  public static java.lang.String LABTESTDATE = "labTestDate";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String MALARIATYPE = "malariaType";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String PATIENTCATEGORY = "patientCategory";
  public static java.lang.String PREGNANT = "pregnant";
  public static java.lang.String PROPERLYRELEASE = "properlyRelease";
  public static java.lang.String REFERRALREASON = "referralReason";
  public static java.lang.String REFERREDFROM = "referredFrom";
  public static java.lang.String REFERREDTO = "referredTo";
  public static java.lang.String RELEASEDATE = "releaseDate";
  public static java.lang.String SAMPLETYPE = "sampleType";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String SYMPTOM = "symptom";
  public static java.lang.String SYMPTOMCOMMENTS = "symptomComments";
  public static java.lang.String SYMPTOMONSET = "symptomOnset";
  public static java.lang.String TESTSAMPLEDATE = "testSampleDate";
  public static java.lang.String TREATMENT = "treatment";
  public static java.lang.String TREATMENTMETHOD = "treatmentMethod";
  public static java.lang.String TREATMENTSTARTDATE = "treatmentStartDate";
  public static java.lang.String TYPE = "type";
  public Boolean getActivelyDetected()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ACTIVELYDETECTED));
  }
  
  public void setActivelyDetected(Boolean value)
  {
    if(value == null)
    {
      setValue(ACTIVELYDETECTED, "");
    }
    else
    {
      setValue(ACTIVELYDETECTED, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isActivelyDetectedWritable()
  {
    return isWritable(ACTIVELYDETECTED);
  }
  
  public boolean isActivelyDetectedReadable()
  {
    return isReadable(ACTIVELYDETECTED);
  }
  
  public boolean isActivelyDetectedModified()
  {
    return isModified(ACTIVELYDETECTED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getActivelyDetectedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ACTIVELYDETECTED).getAttributeMdDTO();
  }
  
  public java.util.Date getAdmissionDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(ADMISSIONDATE));
  }
  
  public void setAdmissionDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(ADMISSIONDATE, "");
    }
    else
    {
      setValue(ADMISSIONDATE, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isAdmissionDateWritable()
  {
    return isWritable(ADMISSIONDATE);
  }
  
  public boolean isAdmissionDateReadable()
  {
    return isReadable(ADMISSIONDATE);
  }
  
  public boolean isAdmissionDateModified()
  {
    return isModified(ADMISSIONDATE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateMdDTO getAdmissionDateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO(ADMISSIONDATE).getAttributeMdDTO();
  }
  
  public Boolean getAnaemiaPatient()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ANAEMIAPATIENT));
  }
  
  public void setAnaemiaPatient(Boolean value)
  {
    if(value == null)
    {
      setValue(ANAEMIAPATIENT, "");
    }
    else
    {
      setValue(ANAEMIAPATIENT, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isAnaemiaPatientWritable()
  {
    return isWritable(ANAEMIAPATIENT);
  }
  
  public boolean isAnaemiaPatientReadable()
  {
    return isReadable(ANAEMIAPATIENT);
  }
  
  public boolean isAnaemiaPatientModified()
  {
    return isModified(ANAEMIAPATIENT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getAnaemiaPatientMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ANAEMIAPATIENT).getAttributeMdDTO();
  }
  
  public Boolean getClinicalDiagnosis()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(CLINICALDIAGNOSIS));
  }
  
  public void setClinicalDiagnosis(Boolean value)
  {
    if(value == null)
    {
      setValue(CLINICALDIAGNOSIS, "");
    }
    else
    {
      setValue(CLINICALDIAGNOSIS, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isClinicalDiagnosisWritable()
  {
    return isWritable(CLINICALDIAGNOSIS);
  }
  
  public boolean isClinicalDiagnosisReadable()
  {
    return isReadable(CLINICALDIAGNOSIS);
  }
  
  public boolean isClinicalDiagnosisModified()
  {
    return isModified(CLINICALDIAGNOSIS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getClinicalDiagnosisMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(CLINICALDIAGNOSIS).getAttributeMdDTO();
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
  
  public dss.vector.solutions.ontology.TermDTO getDetectedBy()
  {
    if(getValue(DETECTEDBY) == null || getValue(DETECTEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(DETECTEDBY));
    }
  }
  
  public void setDetectedBy(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(DETECTEDBY, "");
    }
    else
    {
      setValue(DETECTEDBY, value.getId());
    }
  }
  
  public boolean isDetectedByWritable()
  {
    return isWritable(DETECTEDBY);
  }
  
  public boolean isDetectedByReadable()
  {
    return isReadable(DETECTEDBY);
  }
  
  public boolean isDetectedByModified()
  {
    return isModified(DETECTEDBY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getDetectedByMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DETECTEDBY).getAttributeMdDTO();
  }
  
  public Boolean getDiedInFacility()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(DIEDINFACILITY));
  }
  
  public void setDiedInFacility(Boolean value)
  {
    if(value == null)
    {
      setValue(DIEDINFACILITY, "");
    }
    else
    {
      setValue(DIEDINFACILITY, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isDiedInFacilityWritable()
  {
    return isWritable(DIEDINFACILITY);
  }
  
  public boolean isDiedInFacilityReadable()
  {
    return isReadable(DIEDINFACILITY);
  }
  
  public boolean isDiedInFacilityModified()
  {
    return isModified(DIEDINFACILITY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getDiedInFacilityMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(DIEDINFACILITY).getAttributeMdDTO();
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
  
  public java.util.Date getFacilityVisit()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(FACILITYVISIT));
  }
  
  public void setFacilityVisit(java.util.Date value)
  {
    if(value == null)
    {
      setValue(FACILITYVISIT, "");
    }
    else
    {
      setValue(FACILITYVISIT, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isFacilityVisitWritable()
  {
    return isWritable(FACILITYVISIT);
  }
  
  public boolean isFacilityVisitReadable()
  {
    return isReadable(FACILITYVISIT);
  }
  
  public boolean isFacilityVisitModified()
  {
    return isModified(FACILITYVISIT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateMdDTO getFacilityVisitMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO(FACILITYVISIT).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.geo.generated.HealthFacilityDTO getHealthFacility()
  {
    if(getValue(HEALTHFACILITY) == null || getValue(HEALTHFACILITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.HealthFacilityDTO.get(getRequest(), getValue(HEALTHFACILITY));
    }
  }
  
  public void setHealthFacility(dss.vector.solutions.geo.generated.HealthFacilityDTO value)
  {
    if(value == null)
    {
      setValue(HEALTHFACILITY, "");
    }
    else
    {
      setValue(HEALTHFACILITY, value.getId());
    }
  }
  
  public boolean isHealthFacilityWritable()
  {
    return isWritable(HEALTHFACILITY);
  }
  
  public boolean isHealthFacilityReadable()
  {
    return isReadable(HEALTHFACILITY);
  }
  
  public boolean isHealthFacilityModified()
  {
    return isModified(HEALTHFACILITY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getHealthFacilityMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(HEALTHFACILITY).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.intervention.monitor.IndividualCaseDTO getIndividualCase()
  {
    if(getValue(INDIVIDUALCASE) == null || getValue(INDIVIDUALCASE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.intervention.monitor.IndividualCaseDTO.get(getRequest(), getValue(INDIVIDUALCASE));
    }
  }
  
  public void setIndividualCase(dss.vector.solutions.intervention.monitor.IndividualCaseDTO value)
  {
    if(value == null)
    {
      setValue(INDIVIDUALCASE, "");
    }
    else
    {
      setValue(INDIVIDUALCASE, value.getId());
    }
  }
  
  public boolean isIndividualCaseWritable()
  {
    return isWritable(INDIVIDUALCASE);
  }
  
  public boolean isIndividualCaseReadable()
  {
    return isReadable(INDIVIDUALCASE);
  }
  
  public boolean isIndividualCaseModified()
  {
    return isModified(INDIVIDUALCASE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getIndividualCaseMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(INDIVIDUALCASE).getAttributeMdDTO();
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
  
  public dss.vector.solutions.ontology.TermDTO getLabTest()
  {
    if(getValue(LABTEST) == null || getValue(LABTEST).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(LABTEST));
    }
  }
  
  public void setLabTest(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(LABTEST, "");
    }
    else
    {
      setValue(LABTEST, value.getId());
    }
  }
  
  public boolean isLabTestWritable()
  {
    return isWritable(LABTEST);
  }
  
  public boolean isLabTestReadable()
  {
    return isReadable(LABTEST);
  }
  
  public boolean isLabTestModified()
  {
    return isModified(LABTEST);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getLabTestMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(LABTEST).getAttributeMdDTO();
  }
  
  public java.util.Date getLabTestDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(LABTESTDATE));
  }
  
  public void setLabTestDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(LABTESTDATE, "");
    }
    else
    {
      setValue(LABTESTDATE, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isLabTestDateWritable()
  {
    return isWritable(LABTESTDATE);
  }
  
  public boolean isLabTestDateReadable()
  {
    return isReadable(LABTESTDATE);
  }
  
  public boolean isLabTestDateModified()
  {
    return isModified(LABTESTDATE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateMdDTO getLabTestDateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO(LABTESTDATE).getAttributeMdDTO();
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
  
  public dss.vector.solutions.ontology.TermDTO getMalariaType()
  {
    if(getValue(MALARIATYPE) == null || getValue(MALARIATYPE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(MALARIATYPE));
    }
  }
  
  public void setMalariaType(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(MALARIATYPE, "");
    }
    else
    {
      setValue(MALARIATYPE, value.getId());
    }
  }
  
  public boolean isMalariaTypeWritable()
  {
    return isWritable(MALARIATYPE);
  }
  
  public boolean isMalariaTypeReadable()
  {
    return isReadable(MALARIATYPE);
  }
  
  public boolean isMalariaTypeModified()
  {
    return isModified(MALARIATYPE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getMalariaTypeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(MALARIATYPE).getAttributeMdDTO();
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
  
  public dss.vector.solutions.ontology.TermDTO getPatientCategory()
  {
    if(getValue(PATIENTCATEGORY) == null || getValue(PATIENTCATEGORY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(PATIENTCATEGORY));
    }
  }
  
  public void setPatientCategory(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(PATIENTCATEGORY, "");
    }
    else
    {
      setValue(PATIENTCATEGORY, value.getId());
    }
  }
  
  public boolean isPatientCategoryWritable()
  {
    return isWritable(PATIENTCATEGORY);
  }
  
  public boolean isPatientCategoryReadable()
  {
    return isReadable(PATIENTCATEGORY);
  }
  
  public boolean isPatientCategoryModified()
  {
    return isModified(PATIENTCATEGORY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getPatientCategoryMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(PATIENTCATEGORY).getAttributeMdDTO();
  }
  
  public Boolean getPregnant()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(PREGNANT));
  }
  
  public void setPregnant(Boolean value)
  {
    if(value == null)
    {
      setValue(PREGNANT, "");
    }
    else
    {
      setValue(PREGNANT, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isPregnantWritable()
  {
    return isWritable(PREGNANT);
  }
  
  public boolean isPregnantReadable()
  {
    return isReadable(PREGNANT);
  }
  
  public boolean isPregnantModified()
  {
    return isModified(PREGNANT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getPregnantMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(PREGNANT).getAttributeMdDTO();
  }
  
  public Boolean getProperlyRelease()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(PROPERLYRELEASE));
  }
  
  public void setProperlyRelease(Boolean value)
  {
    if(value == null)
    {
      setValue(PROPERLYRELEASE, "");
    }
    else
    {
      setValue(PROPERLYRELEASE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isProperlyReleaseWritable()
  {
    return isWritable(PROPERLYRELEASE);
  }
  
  public boolean isProperlyReleaseReadable()
  {
    return isReadable(PROPERLYRELEASE);
  }
  
  public boolean isProperlyReleaseModified()
  {
    return isModified(PROPERLYRELEASE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getProperlyReleaseMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(PROPERLYRELEASE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getReferralReason()
  {
    if(getValue(REFERRALREASON) == null || getValue(REFERRALREASON).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(REFERRALREASON));
    }
  }
  
  public void setReferralReason(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(REFERRALREASON, "");
    }
    else
    {
      setValue(REFERRALREASON, value.getId());
    }
  }
  
  public boolean isReferralReasonWritable()
  {
    return isWritable(REFERRALREASON);
  }
  
  public boolean isReferralReasonReadable()
  {
    return isReadable(REFERRALREASON);
  }
  
  public boolean isReferralReasonModified()
  {
    return isModified(REFERRALREASON);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getReferralReasonMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(REFERRALREASON).getAttributeMdDTO();
  }
  
  public Boolean getReferredFrom()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(REFERREDFROM));
  }
  
  public void setReferredFrom(Boolean value)
  {
    if(value == null)
    {
      setValue(REFERREDFROM, "");
    }
    else
    {
      setValue(REFERREDFROM, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isReferredFromWritable()
  {
    return isWritable(REFERREDFROM);
  }
  
  public boolean isReferredFromReadable()
  {
    return isReadable(REFERREDFROM);
  }
  
  public boolean isReferredFromModified()
  {
    return isModified(REFERREDFROM);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getReferredFromMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(REFERREDFROM).getAttributeMdDTO();
  }
  
  public Boolean getReferredTo()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(REFERREDTO));
  }
  
  public void setReferredTo(Boolean value)
  {
    if(value == null)
    {
      setValue(REFERREDTO, "");
    }
    else
    {
      setValue(REFERREDTO, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isReferredToWritable()
  {
    return isWritable(REFERREDTO);
  }
  
  public boolean isReferredToReadable()
  {
    return isReadable(REFERREDTO);
  }
  
  public boolean isReferredToModified()
  {
    return isModified(REFERREDTO);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getReferredToMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(REFERREDTO).getAttributeMdDTO();
  }
  
  public java.util.Date getReleaseDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(RELEASEDATE));
  }
  
  public void setReleaseDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(RELEASEDATE, "");
    }
    else
    {
      setValue(RELEASEDATE, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isReleaseDateWritable()
  {
    return isWritable(RELEASEDATE);
  }
  
  public boolean isReleaseDateReadable()
  {
    return isReadable(RELEASEDATE);
  }
  
  public boolean isReleaseDateModified()
  {
    return isModified(RELEASEDATE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateMdDTO getReleaseDateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO(RELEASEDATE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getSampleType()
  {
    if(getValue(SAMPLETYPE) == null || getValue(SAMPLETYPE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(SAMPLETYPE));
    }
  }
  
  public void setSampleType(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(SAMPLETYPE, "");
    }
    else
    {
      setValue(SAMPLETYPE, value.getId());
    }
  }
  
  public boolean isSampleTypeWritable()
  {
    return isWritable(SAMPLETYPE);
  }
  
  public boolean isSampleTypeReadable()
  {
    return isReadable(SAMPLETYPE);
  }
  
  public boolean isSampleTypeModified()
  {
    return isModified(SAMPLETYPE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getSampleTypeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SAMPLETYPE).getAttributeMdDTO();
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
  
  public dss.vector.solutions.ontology.TermDTO getSymptom()
  {
    if(getValue(SYMPTOM) == null || getValue(SYMPTOM).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(SYMPTOM));
    }
  }
  
  public void setSymptom(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(SYMPTOM, "");
    }
    else
    {
      setValue(SYMPTOM, value.getId());
    }
  }
  
  public boolean isSymptomWritable()
  {
    return isWritable(SYMPTOM);
  }
  
  public boolean isSymptomReadable()
  {
    return isReadable(SYMPTOM);
  }
  
  public boolean isSymptomModified()
  {
    return isModified(SYMPTOM);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getSymptomMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SYMPTOM).getAttributeMdDTO();
  }
  
  public String getSymptomComments()
  {
    return getValue(SYMPTOMCOMMENTS);
  }
  
  public void setSymptomComments(String value)
  {
    if(value == null)
    {
      setValue(SYMPTOMCOMMENTS, "");
    }
    else
    {
      setValue(SYMPTOMCOMMENTS, value);
    }
  }
  
  public boolean isSymptomCommentsWritable()
  {
    return isWritable(SYMPTOMCOMMENTS);
  }
  
  public boolean isSymptomCommentsReadable()
  {
    return isReadable(SYMPTOMCOMMENTS);
  }
  
  public boolean isSymptomCommentsModified()
  {
    return isModified(SYMPTOMCOMMENTS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeTextMdDTO getSymptomCommentsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeTextMdDTO) getAttributeDTO(SYMPTOMCOMMENTS).getAttributeMdDTO();
  }
  
  public java.util.Date getSymptomOnset()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(SYMPTOMONSET));
  }
  
  public void setSymptomOnset(java.util.Date value)
  {
    if(value == null)
    {
      setValue(SYMPTOMONSET, "");
    }
    else
    {
      setValue(SYMPTOMONSET, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateMdDTO getSymptomOnsetMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO(SYMPTOMONSET).getAttributeMdDTO();
  }
  
  public java.util.Date getTestSampleDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(TESTSAMPLEDATE));
  }
  
  public void setTestSampleDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(TESTSAMPLEDATE, "");
    }
    else
    {
      setValue(TESTSAMPLEDATE, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isTestSampleDateWritable()
  {
    return isWritable(TESTSAMPLEDATE);
  }
  
  public boolean isTestSampleDateReadable()
  {
    return isReadable(TESTSAMPLEDATE);
  }
  
  public boolean isTestSampleDateModified()
  {
    return isModified(TESTSAMPLEDATE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateMdDTO getTestSampleDateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO(TESTSAMPLEDATE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getTreatment()
  {
    if(getValue(TREATMENT) == null || getValue(TREATMENT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(TREATMENT));
    }
  }
  
  public void setTreatment(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(TREATMENT, "");
    }
    else
    {
      setValue(TREATMENT, value.getId());
    }
  }
  
  public boolean isTreatmentWritable()
  {
    return isWritable(TREATMENT);
  }
  
  public boolean isTreatmentReadable()
  {
    return isReadable(TREATMENT);
  }
  
  public boolean isTreatmentModified()
  {
    return isModified(TREATMENT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getTreatmentMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(TREATMENT).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getTreatmentMethod()
  {
    if(getValue(TREATMENTMETHOD) == null || getValue(TREATMENTMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(TREATMENTMETHOD));
    }
  }
  
  public void setTreatmentMethod(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(TREATMENTMETHOD, "");
    }
    else
    {
      setValue(TREATMENTMETHOD, value.getId());
    }
  }
  
  public boolean isTreatmentMethodWritable()
  {
    return isWritable(TREATMENTMETHOD);
  }
  
  public boolean isTreatmentMethodReadable()
  {
    return isReadable(TREATMENTMETHOD);
  }
  
  public boolean isTreatmentMethodModified()
  {
    return isModified(TREATMENTMETHOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getTreatmentMethodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(TREATMENTMETHOD).getAttributeMdDTO();
  }
  
  public java.util.Date getTreatmentStartDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(TREATMENTSTARTDATE));
  }
  
  public void setTreatmentStartDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(TREATMENTSTARTDATE, "");
    }
    else
    {
      setValue(TREATMENTSTARTDATE, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isTreatmentStartDateWritable()
  {
    return isWritable(TREATMENTSTARTDATE);
  }
  
  public boolean isTreatmentStartDateReadable()
  {
    return isReadable(TREATMENTSTARTDATE);
  }
  
  public boolean isTreatmentStartDateModified()
  {
    return isModified(TREATMENTSTARTDATE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateMdDTO getTreatmentStartDateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO(TREATMENTSTARTDATE).getAttributeMdDTO();
  }
  
  public final void applyAll(dss.vector.solutions.surveillance.IndividualCaseSymptomDTO[] symptoms)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.surveillance.IndividualCaseSymptom;"};
    Object[] _parameters = new Object[]{symptoms};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.IndividualInstanceDTO.CLASS, "applyAll", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void applyAll(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id, dss.vector.solutions.surveillance.IndividualCaseSymptomDTO[] symptoms)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "[Ldss.vector.solutions.surveillance.IndividualCaseSymptom;"};
    Object[] _parameters = new Object[]{id, symptoms};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.IndividualInstanceDTO.CLASS, "applyAll", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.surveillance.IndividualCaseSymptomDTO[] getSymptoms()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.IndividualInstanceDTO.CLASS, "getSymptoms", _declaredTypes);
    return (dss.vector.solutions.surveillance.IndividualCaseSymptomDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.surveillance.IndividualCaseSymptomDTO[] getSymptoms(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.IndividualInstanceDTO.CLASS, "getSymptoms", _declaredTypes);
    return (dss.vector.solutions.surveillance.IndividualCaseSymptomDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllSymptoms()
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.surveillance.IndividualCaseSymptomDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllSymptoms(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.surveillance.IndividualCaseSymptomDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.surveillance.IndividualCaseSymptomDTO> getAllSymptomsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.IndividualCaseSymptomDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.surveillance.IndividualCaseSymptomDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.surveillance.IndividualCaseSymptomDTO> getAllSymptomsRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.surveillance.IndividualCaseSymptomDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.surveillance.IndividualCaseSymptomDTO.CLASS);
  }
  
  public dss.vector.solutions.surveillance.IndividualCaseSymptomDTO addSymptoms(dss.vector.solutions.ontology.TermDTO child)
  {
    return (dss.vector.solutions.surveillance.IndividualCaseSymptomDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.surveillance.IndividualCaseSymptomDTO.CLASS);
  }
  
  public static dss.vector.solutions.surveillance.IndividualCaseSymptomDTO addSymptoms(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.ontology.TermDTO child)
  {
    return (dss.vector.solutions.surveillance.IndividualCaseSymptomDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.surveillance.IndividualCaseSymptomDTO.CLASS);
  }
  
  public void removeSymptoms(dss.vector.solutions.surveillance.IndividualCaseSymptomDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeSymptoms(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.surveillance.IndividualCaseSymptomDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllSymptoms()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.surveillance.IndividualCaseSymptomDTO.CLASS);
  }
  
  public static void removeAllSymptoms(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.surveillance.IndividualCaseSymptomDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.IndividualInstanceDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.intervention.monitor.IndividualInstanceDTO) dto;
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
  
  public static dss.vector.solutions.intervention.monitor.IndividualInstanceQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.intervention.monitor.IndividualInstanceQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.intervention.monitor.IndividualInstance", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.intervention.monitor.IndividualInstanceDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.IndividualInstanceDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.IndividualInstanceDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.intervention.monitor.IndividualInstanceDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.IndividualInstanceDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.IndividualInstanceDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
