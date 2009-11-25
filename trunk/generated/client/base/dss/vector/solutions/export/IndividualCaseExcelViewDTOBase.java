package dss.vector.solutions.export;

@com.terraframe.mojo.business.ClassSignature(hash = 1535311723)
public abstract class IndividualCaseExcelViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.IndividualCaseExcelView";
  private static final long serialVersionUID = 1535311723;
  
  protected IndividualCaseExcelViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ACTIVELYDETECTED = "activelyDetected";
  public static java.lang.String ADMISSIONDATE = "admissionDate";
  public static java.lang.String AGE = "age";
  public static java.lang.String ANAEMIAPATIENT = "anaemiaPatient";
  public static java.lang.String CASEREPORTDATE = "caseReportDate";
  public static java.lang.String CLINICALDIAGNOSIS = "clinicalDiagnosis";
  public static java.lang.String DATEOFBIRTH = "dateOfBirth";
  public static java.lang.String DETECTEDBY = "detectedBy";
  public static java.lang.String DIAGNOSISDATE = "diagnosisDate";
  public static java.lang.String DIEDINFACILITY = "diedInFacility";
  public static java.lang.String FACILITYVISIT = "facilityVisit";
  public static java.lang.String FIRSTNAME = "firstName";
  public static java.lang.String HEALTHFACILITY = "healthFacility";
  public static java.lang.String ID = "id";
  public static java.lang.String LABTEST = "labTest";
  public static java.lang.String LABTESTDATE = "labTestDate";
  public static java.lang.String LASTNAME = "lastName";
  public static java.lang.String MALARIATYPE = "malariaType";
  public static java.lang.String PATIENTCATEGORY = "patientCategory";
  public static java.lang.String PREGNANT = "pregnant";
  public static java.lang.String PROBABLESOURCE = "probableSource";
  public static java.lang.String PROPERLYRELEASE = "properlyRelease";
  public static java.lang.String REFERRALREASON = "referralReason";
  public static java.lang.String REFERREDFROM = "referredFrom";
  public static java.lang.String REFERREDTO = "referredTo";
  public static java.lang.String RELEASEDATE = "releaseDate";
  public static java.lang.String SAMPLETYPE = "sampleType";
  public static java.lang.String SEX = "sex";
  public static java.lang.String SYMPTOMCOMMENTS = "symptomComments";
  public static java.lang.String SYMPTOMONSET = "symptomOnset";
  public static java.lang.String TESTSAMPLEDATE = "testSampleDate";
  public static java.lang.String TREATMENT = "treatment";
  public static java.lang.String TREATMENTMETHOD = "treatmentMethod";
  public static java.lang.String TREATMENTSTARTDATE = "treatmentStartDate";
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
  
  public Integer getAge()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(AGE));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getAgeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(AGE).getAttributeMdDTO();
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
  
  public java.util.Date getCaseReportDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(CASEREPORTDATE));
  }
  
  public void setCaseReportDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(CASEREPORTDATE, "");
    }
    else
    {
      setValue(CASEREPORTDATE, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateMdDTO getCaseReportDateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO(CASEREPORTDATE).getAttributeMdDTO();
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
  
  public String getDetectedBy()
  {
    return getValue(DETECTEDBY);
  }
  
  public void setDetectedBy(String value)
  {
    if(value == null)
    {
      setValue(DETECTEDBY, "");
    }
    else
    {
      setValue(DETECTEDBY, value);
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getDetectedByMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(DETECTEDBY).getAttributeMdDTO();
  }
  
  public java.util.Date getDiagnosisDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(DIAGNOSISDATE));
  }
  
  public void setDiagnosisDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(DIAGNOSISDATE, "");
    }
    else
    {
      setValue(DIAGNOSISDATE, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateMdDTO getDiagnosisDateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO(DIAGNOSISDATE).getAttributeMdDTO();
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
  
  public String getLabTest()
  {
    return getValue(LABTEST);
  }
  
  public void setLabTest(String value)
  {
    if(value == null)
    {
      setValue(LABTEST, "");
    }
    else
    {
      setValue(LABTEST, value);
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getLabTestMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(LABTEST).getAttributeMdDTO();
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
  
  public String getMalariaType()
  {
    return getValue(MALARIATYPE);
  }
  
  public void setMalariaType(String value)
  {
    if(value == null)
    {
      setValue(MALARIATYPE, "");
    }
    else
    {
      setValue(MALARIATYPE, value);
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getMalariaTypeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MALARIATYPE).getAttributeMdDTO();
  }
  
  public String getPatientCategory()
  {
    return getValue(PATIENTCATEGORY);
  }
  
  public void setPatientCategory(String value)
  {
    if(value == null)
    {
      setValue(PATIENTCATEGORY, "");
    }
    else
    {
      setValue(PATIENTCATEGORY, value);
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getPatientCategoryMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PATIENTCATEGORY).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getProbableSourceMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(PROBABLESOURCE).getAttributeMdDTO();
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
  
  public String getReferralReason()
  {
    return getValue(REFERRALREASON);
  }
  
  public void setReferralReason(String value)
  {
    if(value == null)
    {
      setValue(REFERRALREASON, "");
    }
    else
    {
      setValue(REFERRALREASON, value);
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getReferralReasonMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(REFERRALREASON).getAttributeMdDTO();
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
  
  public String getSampleType()
  {
    return getValue(SAMPLETYPE);
  }
  
  public void setSampleType(String value)
  {
    if(value == null)
    {
      setValue(SAMPLETYPE, "");
    }
    else
    {
      setValue(SAMPLETYPE, value);
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getSampleTypeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SAMPLETYPE).getAttributeMdDTO();
  }
  
  public String getSex()
  {
    return getValue(SEX);
  }
  
  public void setSex(String value)
  {
    if(value == null)
    {
      setValue(SEX, "");
    }
    else
    {
      setValue(SEX, value);
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getSexMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SEX).getAttributeMdDTO();
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
  
  public String getTreatment()
  {
    return getValue(TREATMENT);
  }
  
  public void setTreatment(String value)
  {
    if(value == null)
    {
      setValue(TREATMENT, "");
    }
    else
    {
      setValue(TREATMENT, value);
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getTreatmentMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TREATMENT).getAttributeMdDTO();
  }
  
  public String getTreatmentMethod()
  {
    return getValue(TREATMENTMETHOD);
  }
  
  public void setTreatmentMethod(String value)
  {
    if(value == null)
    {
      setValue(TREATMENTMETHOD, "");
    }
    else
    {
      setValue(TREATMENTMETHOD, value);
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getTreatmentMethodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TREATMENTMETHOD).getAttributeMdDTO();
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
  
  public static IndividualCaseExcelViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (IndividualCaseExcelViewDTO) dto;
  }
  
  public void apply()
  {
    if(isNewInstance())
    {
      getRequest().createSessionComponent(this);
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
  
}
