package dss.vector.solutions.export;

@com.terraframe.mojo.business.ClassSignature(hash = -1500872953)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to IndividualCaseExcelView.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class IndividualCaseExcelViewBase extends com.terraframe.mojo.business.View implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.IndividualCaseExcelView";
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
  public static java.lang.String RESIDENCE = "residence";
  public static java.lang.String SAMPLETYPE = "sampleType";
  public static java.lang.String SEX = "sex";
  public static java.lang.String SYMPTOMCOMMENTS = "symptomComments";
  public static java.lang.String SYMPTOMONSET = "symptomOnset";
  public static java.lang.String TESTSAMPLEDATE = "testSampleDate";
  public static java.lang.String TREATMENT = "treatment";
  public static java.lang.String TREATMENTMETHOD = "treatmentMethod";
  public static java.lang.String TREATMENTSTARTDATE = "treatmentStartDate";
  public static java.lang.String WORKPLACE = "workplace";
  private static final long serialVersionUID = -1500872953;
  
  public IndividualCaseExcelViewBase()
  {
    super();
  }
  
  public Boolean getActivelyDetected()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ACTIVELYDETECTED));
  }
  
  public void validateActivelyDetected()
  {
    this.validateAttribute(ACTIVELYDETECTED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getActivelyDetectedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualCaseExcelView.CLASS);
    return mdClassIF.definesAttribute(ACTIVELYDETECTED);
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
  
  public java.util.Date getAdmissionDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(ADMISSIONDATE));
  }
  
  public void validateAdmissionDate()
  {
    this.validateAttribute(ADMISSIONDATE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getAdmissionDateMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualCaseExcelView.CLASS);
    return mdClassIF.definesAttribute(ADMISSIONDATE);
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
  
  public Integer getAge()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(AGE));
  }
  
  public void validateAge()
  {
    this.validateAttribute(AGE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getAgeMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualCaseExcelView.CLASS);
    return mdClassIF.definesAttribute(AGE);
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
  
  public Boolean getAnaemiaPatient()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ANAEMIAPATIENT));
  }
  
  public void validateAnaemiaPatient()
  {
    this.validateAttribute(ANAEMIAPATIENT);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getAnaemiaPatientMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualCaseExcelView.CLASS);
    return mdClassIF.definesAttribute(ANAEMIAPATIENT);
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
  
  public java.util.Date getCaseReportDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(CASEREPORTDATE));
  }
  
  public void validateCaseReportDate()
  {
    this.validateAttribute(CASEREPORTDATE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getCaseReportDateMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualCaseExcelView.CLASS);
    return mdClassIF.definesAttribute(CASEREPORTDATE);
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
  
  public Boolean getClinicalDiagnosis()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(CLINICALDIAGNOSIS));
  }
  
  public void validateClinicalDiagnosis()
  {
    this.validateAttribute(CLINICALDIAGNOSIS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getClinicalDiagnosisMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualCaseExcelView.CLASS);
    return mdClassIF.definesAttribute(CLINICALDIAGNOSIS);
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
  
  public java.util.Date getDateOfBirth()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(DATEOFBIRTH));
  }
  
  public void validateDateOfBirth()
  {
    this.validateAttribute(DATEOFBIRTH);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getDateOfBirthMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualCaseExcelView.CLASS);
    return mdClassIF.definesAttribute(DATEOFBIRTH);
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
  
  public String getDetectedBy()
  {
    return getValue(DETECTEDBY);
  }
  
  public void validateDetectedBy()
  {
    this.validateAttribute(DETECTEDBY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getDetectedByMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualCaseExcelView.CLASS);
    return mdClassIF.definesAttribute(DETECTEDBY);
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
  
  public java.util.Date getDiagnosisDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(DIAGNOSISDATE));
  }
  
  public void validateDiagnosisDate()
  {
    this.validateAttribute(DIAGNOSISDATE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getDiagnosisDateMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualCaseExcelView.CLASS);
    return mdClassIF.definesAttribute(DIAGNOSISDATE);
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
  
  public Boolean getDiedInFacility()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(DIEDINFACILITY));
  }
  
  public void validateDiedInFacility()
  {
    this.validateAttribute(DIEDINFACILITY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getDiedInFacilityMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualCaseExcelView.CLASS);
    return mdClassIF.definesAttribute(DIEDINFACILITY);
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
  
  public java.util.Date getFacilityVisit()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(FACILITYVISIT));
  }
  
  public void validateFacilityVisit()
  {
    this.validateAttribute(FACILITYVISIT);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getFacilityVisitMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualCaseExcelView.CLASS);
    return mdClassIF.definesAttribute(FACILITYVISIT);
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
  
  public String getFirstName()
  {
    return getValue(FIRSTNAME);
  }
  
  public void validateFirstName()
  {
    this.validateAttribute(FIRSTNAME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getFirstNameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualCaseExcelView.CLASS);
    return mdClassIF.definesAttribute(FIRSTNAME);
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
  
  public dss.vector.solutions.geo.generated.HealthFacility getHealthFacility()
  {
    if (getValue(HEALTHFACILITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.HealthFacility.get(getValue(HEALTHFACILITY));
    }
  }
  
  public void validateHealthFacility()
  {
    this.validateAttribute(HEALTHFACILITY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getHealthFacilityMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualCaseExcelView.CLASS);
    return mdClassIF.definesAttribute(HEALTHFACILITY);
  }
  
  public void setHealthFacility(dss.vector.solutions.geo.generated.HealthFacility value)
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
  
  public String getId()
  {
    return getValue(ID);
  }
  
  public void validateId()
  {
    this.validateAttribute(ID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualCaseExcelView.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public String getLabTest()
  {
    return getValue(LABTEST);
  }
  
  public void validateLabTest()
  {
    this.validateAttribute(LABTEST);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getLabTestMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualCaseExcelView.CLASS);
    return mdClassIF.definesAttribute(LABTEST);
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
  
  public java.util.Date getLabTestDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(LABTESTDATE));
  }
  
  public void validateLabTestDate()
  {
    this.validateAttribute(LABTESTDATE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getLabTestDateMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualCaseExcelView.CLASS);
    return mdClassIF.definesAttribute(LABTESTDATE);
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
  
  public String getLastName()
  {
    return getValue(LASTNAME);
  }
  
  public void validateLastName()
  {
    this.validateAttribute(LASTNAME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getLastNameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualCaseExcelView.CLASS);
    return mdClassIF.definesAttribute(LASTNAME);
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
  
  public String getMalariaType()
  {
    return getValue(MALARIATYPE);
  }
  
  public void validateMalariaType()
  {
    this.validateAttribute(MALARIATYPE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getMalariaTypeMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualCaseExcelView.CLASS);
    return mdClassIF.definesAttribute(MALARIATYPE);
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
  
  public String getPatientCategory()
  {
    return getValue(PATIENTCATEGORY);
  }
  
  public void validatePatientCategory()
  {
    this.validateAttribute(PATIENTCATEGORY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getPatientCategoryMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualCaseExcelView.CLASS);
    return mdClassIF.definesAttribute(PATIENTCATEGORY);
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
  
  public Boolean getPregnant()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(PREGNANT));
  }
  
  public void validatePregnant()
  {
    this.validateAttribute(PREGNANT);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getPregnantMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualCaseExcelView.CLASS);
    return mdClassIF.definesAttribute(PREGNANT);
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
  
  public dss.vector.solutions.geo.generated.GeoEntity getProbableSource()
  {
    if (getValue(PROBABLESOURCE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntity.get(getValue(PROBABLESOURCE));
    }
  }
  
  public void validateProbableSource()
  {
    this.validateAttribute(PROBABLESOURCE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getProbableSourceMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualCaseExcelView.CLASS);
    return mdClassIF.definesAttribute(PROBABLESOURCE);
  }
  
  public void setProbableSource(dss.vector.solutions.geo.generated.GeoEntity value)
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
  
  public Boolean getProperlyRelease()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(PROPERLYRELEASE));
  }
  
  public void validateProperlyRelease()
  {
    this.validateAttribute(PROPERLYRELEASE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getProperlyReleaseMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualCaseExcelView.CLASS);
    return mdClassIF.definesAttribute(PROPERLYRELEASE);
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
  
  public String getReferralReason()
  {
    return getValue(REFERRALREASON);
  }
  
  public void validateReferralReason()
  {
    this.validateAttribute(REFERRALREASON);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getReferralReasonMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualCaseExcelView.CLASS);
    return mdClassIF.definesAttribute(REFERRALREASON);
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
  
  public Boolean getReferredFrom()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(REFERREDFROM));
  }
  
  public void validateReferredFrom()
  {
    this.validateAttribute(REFERREDFROM);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getReferredFromMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualCaseExcelView.CLASS);
    return mdClassIF.definesAttribute(REFERREDFROM);
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
  
  public Boolean getReferredTo()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(REFERREDTO));
  }
  
  public void validateReferredTo()
  {
    this.validateAttribute(REFERREDTO);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getReferredToMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualCaseExcelView.CLASS);
    return mdClassIF.definesAttribute(REFERREDTO);
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
  
  public java.util.Date getReleaseDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(RELEASEDATE));
  }
  
  public void validateReleaseDate()
  {
    this.validateAttribute(RELEASEDATE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getReleaseDateMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualCaseExcelView.CLASS);
    return mdClassIF.definesAttribute(RELEASEDATE);
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
  
  public dss.vector.solutions.geo.generated.GeoEntity getResidence()
  {
    if (getValue(RESIDENCE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntity.get(getValue(RESIDENCE));
    }
  }
  
  public void validateResidence()
  {
    this.validateAttribute(RESIDENCE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getResidenceMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualCaseExcelView.CLASS);
    return mdClassIF.definesAttribute(RESIDENCE);
  }
  
  public void setResidence(dss.vector.solutions.geo.generated.GeoEntity value)
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
  
  public String getSampleType()
  {
    return getValue(SAMPLETYPE);
  }
  
  public void validateSampleType()
  {
    this.validateAttribute(SAMPLETYPE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSampleTypeMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualCaseExcelView.CLASS);
    return mdClassIF.definesAttribute(SAMPLETYPE);
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
  
  public String getSex()
  {
    return getValue(SEX);
  }
  
  public void validateSex()
  {
    this.validateAttribute(SEX);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSexMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualCaseExcelView.CLASS);
    return mdClassIF.definesAttribute(SEX);
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
  
  public String getSymptomComments()
  {
    return getValue(SYMPTOMCOMMENTS);
  }
  
  public void validateSymptomComments()
  {
    this.validateAttribute(SYMPTOMCOMMENTS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSymptomCommentsMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualCaseExcelView.CLASS);
    return mdClassIF.definesAttribute(SYMPTOMCOMMENTS);
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
  
  public java.util.Date getSymptomOnset()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(SYMPTOMONSET));
  }
  
  public void validateSymptomOnset()
  {
    this.validateAttribute(SYMPTOMONSET);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSymptomOnsetMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualCaseExcelView.CLASS);
    return mdClassIF.definesAttribute(SYMPTOMONSET);
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
  
  public java.util.Date getTestSampleDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(TESTSAMPLEDATE));
  }
  
  public void validateTestSampleDate()
  {
    this.validateAttribute(TESTSAMPLEDATE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getTestSampleDateMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualCaseExcelView.CLASS);
    return mdClassIF.definesAttribute(TESTSAMPLEDATE);
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
  
  public String getTreatment()
  {
    return getValue(TREATMENT);
  }
  
  public void validateTreatment()
  {
    this.validateAttribute(TREATMENT);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getTreatmentMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualCaseExcelView.CLASS);
    return mdClassIF.definesAttribute(TREATMENT);
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
  
  public String getTreatmentMethod()
  {
    return getValue(TREATMENTMETHOD);
  }
  
  public void validateTreatmentMethod()
  {
    this.validateAttribute(TREATMENTMETHOD);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getTreatmentMethodMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualCaseExcelView.CLASS);
    return mdClassIF.definesAttribute(TREATMENTMETHOD);
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
  
  public java.util.Date getTreatmentStartDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(TREATMENTSTARTDATE));
  }
  
  public void validateTreatmentStartDate()
  {
    this.validateAttribute(TREATMENTSTARTDATE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getTreatmentStartDateMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualCaseExcelView.CLASS);
    return mdClassIF.definesAttribute(TREATMENTSTARTDATE);
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
  
  public dss.vector.solutions.geo.generated.GeoEntity getWorkplace()
  {
    if (getValue(WORKPLACE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntity.get(getValue(WORKPLACE));
    }
  }
  
  public void validateWorkplace()
  {
    this.validateAttribute(WORKPLACE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getWorkplaceMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.IndividualCaseExcelView.CLASS);
    return mdClassIF.definesAttribute(WORKPLACE);
  }
  
  public void setWorkplace(dss.vector.solutions.geo.generated.GeoEntity value)
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
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static IndividualCaseExcelView get(String id)
  {
    return (IndividualCaseExcelView) com.terraframe.mojo.business.View.get(id);
  }
  
  public String toString()
  {
    if (this.isNew())
    {
      return "New: "+ this.getClassDisplayLabel();
    }
    else
    {
      return super.toString();
    }
  }
}
