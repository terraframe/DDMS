package dss.vector.solutions.intervention.monitor;

@com.runwaysdk.business.ClassSignature(hash = 1485870820)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to IndividualInstance.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class IndividualInstanceBase extends com.runwaysdk.business.Business implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.IndividualInstance";
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
  private static final long serialVersionUID = 1485870820;
  
  public IndividualInstanceBase()
  {
    super();
  }
  
  public Boolean getActivelyDetected()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ACTIVELYDETECTED));
  }
  
  public void validateActivelyDetected()
  {
    this.validateAttribute(ACTIVELYDETECTED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getActivelyDetectedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualInstance.CLASS);
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
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(ADMISSIONDATE));
  }
  
  public void validateAdmissionDate()
  {
    this.validateAttribute(ADMISSIONDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getAdmissionDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualInstance.CLASS);
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
      setValue(ADMISSIONDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public Boolean getAnaemiaPatient()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ANAEMIAPATIENT));
  }
  
  public void validateAnaemiaPatient()
  {
    this.validateAttribute(ANAEMIAPATIENT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getAnaemiaPatientMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualInstance.CLASS);
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
  
  public Boolean getClinicalDiagnosis()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(CLINICALDIAGNOSIS));
  }
  
  public void validateClinicalDiagnosis()
  {
    this.validateAttribute(CLINICALDIAGNOSIS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getClinicalDiagnosisMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualInstance.CLASS);
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
  
  public java.util.Date getCreateDate()
  {
    return com.runwaysdk.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(CREATEDATE));
  }
  
  public void validateCreateDate()
  {
    this.validateAttribute(CREATEDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getCreateDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualInstance.CLASS);
    return mdClassIF.definesAttribute(CREATEDATE);
  }
  
  public com.runwaysdk.system.SingleActor getCreatedBy()
  {
    if (getValue(CREATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.SingleActor.get(getValue(CREATEDBY));
    }
  }
  
  public void validateCreatedBy()
  {
    this.validateAttribute(CREATEDBY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getCreatedByMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualInstance.CLASS);
    return mdClassIF.definesAttribute(CREATEDBY);
  }
  
  public dss.vector.solutions.ontology.Term getDetectedBy()
  {
    if (getValue(DETECTEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(DETECTEDBY));
    }
  }
  
  public void validateDetectedBy()
  {
    this.validateAttribute(DETECTEDBY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getDetectedByMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualInstance.CLASS);
    return mdClassIF.definesAttribute(DETECTEDBY);
  }
  
  public void setDetectedBy(dss.vector.solutions.ontology.Term value)
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
  
  public Boolean getDiedInFacility()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(DIEDINFACILITY));
  }
  
  public void validateDiedInFacility()
  {
    this.validateAttribute(DIEDINFACILITY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getDiedInFacilityMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualInstance.CLASS);
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
  
  public com.runwaysdk.system.metadata.MdDomain getEntityDomain()
  {
    if (getValue(ENTITYDOMAIN).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.metadata.MdDomain.get(getValue(ENTITYDOMAIN));
    }
  }
  
  public void validateEntityDomain()
  {
    this.validateAttribute(ENTITYDOMAIN);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getEntityDomainMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualInstance.CLASS);
    return mdClassIF.definesAttribute(ENTITYDOMAIN);
  }
  
  public void setEntityDomain(com.runwaysdk.system.metadata.MdDomain value)
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
  
  public java.util.Date getFacilityVisit()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(FACILITYVISIT));
  }
  
  public void validateFacilityVisit()
  {
    this.validateAttribute(FACILITYVISIT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getFacilityVisitMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualInstance.CLASS);
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
      setValue(FACILITYVISIT, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getHealthFacilityMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualInstance.CLASS);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualInstance.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public dss.vector.solutions.intervention.monitor.IndividualCase getIndividualCase()
  {
    if (getValue(INDIVIDUALCASE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.intervention.monitor.IndividualCase.get(getValue(INDIVIDUALCASE));
    }
  }
  
  public void validateIndividualCase()
  {
    this.validateAttribute(INDIVIDUALCASE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getIndividualCaseMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualInstance.CLASS);
    return mdClassIF.definesAttribute(INDIVIDUALCASE);
  }
  
  public void setIndividualCase(dss.vector.solutions.intervention.monitor.IndividualCase value)
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
  
  public String getKeyName()
  {
    return getValue(KEYNAME);
  }
  
  public void validateKeyName()
  {
    this.validateAttribute(KEYNAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getKeyNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualInstance.CLASS);
    return mdClassIF.definesAttribute(KEYNAME);
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
  
  public dss.vector.solutions.ontology.Term getLabTest()
  {
    if (getValue(LABTEST).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(LABTEST));
    }
  }
  
  public void validateLabTest()
  {
    this.validateAttribute(LABTEST);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getLabTestMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualInstance.CLASS);
    return mdClassIF.definesAttribute(LABTEST);
  }
  
  public void setLabTest(dss.vector.solutions.ontology.Term value)
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
  
  public java.util.Date getLabTestDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(LABTESTDATE));
  }
  
  public void validateLabTestDate()
  {
    this.validateAttribute(LABTESTDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getLabTestDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualInstance.CLASS);
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
      setValue(LABTESTDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public java.util.Date getLastUpdateDate()
  {
    return com.runwaysdk.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(LASTUPDATEDATE));
  }
  
  public void validateLastUpdateDate()
  {
    this.validateAttribute(LASTUPDATEDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getLastUpdateDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualInstance.CLASS);
    return mdClassIF.definesAttribute(LASTUPDATEDATE);
  }
  
  public com.runwaysdk.system.SingleActor getLastUpdatedBy()
  {
    if (getValue(LASTUPDATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.SingleActor.get(getValue(LASTUPDATEDBY));
    }
  }
  
  public void validateLastUpdatedBy()
  {
    this.validateAttribute(LASTUPDATEDBY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getLastUpdatedByMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualInstance.CLASS);
    return mdClassIF.definesAttribute(LASTUPDATEDBY);
  }
  
  public com.runwaysdk.system.Users getLockedBy()
  {
    if (getValue(LOCKEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.Users.get(getValue(LOCKEDBY));
    }
  }
  
  public void validateLockedBy()
  {
    this.validateAttribute(LOCKEDBY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getLockedByMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualInstance.CLASS);
    return mdClassIF.definesAttribute(LOCKEDBY);
  }
  
  public dss.vector.solutions.ontology.Term getMalariaType()
  {
    if (getValue(MALARIATYPE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(MALARIATYPE));
    }
  }
  
  public void validateMalariaType()
  {
    this.validateAttribute(MALARIATYPE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getMalariaTypeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualInstance.CLASS);
    return mdClassIF.definesAttribute(MALARIATYPE);
  }
  
  public void setMalariaType(dss.vector.solutions.ontology.Term value)
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
  
  public com.runwaysdk.system.Actor getOwner()
  {
    if (getValue(OWNER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.Actor.get(getValue(OWNER));
    }
  }
  
  public void validateOwner()
  {
    this.validateAttribute(OWNER);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getOwnerMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualInstance.CLASS);
    return mdClassIF.definesAttribute(OWNER);
  }
  
  public void setOwner(com.runwaysdk.system.Actor value)
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
  
  public dss.vector.solutions.ontology.Term getPatientCategory()
  {
    if (getValue(PATIENTCATEGORY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(PATIENTCATEGORY));
    }
  }
  
  public void validatePatientCategory()
  {
    this.validateAttribute(PATIENTCATEGORY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getPatientCategoryMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualInstance.CLASS);
    return mdClassIF.definesAttribute(PATIENTCATEGORY);
  }
  
  public void setPatientCategory(dss.vector.solutions.ontology.Term value)
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
  
  public Boolean getPregnant()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(PREGNANT));
  }
  
  public void validatePregnant()
  {
    this.validateAttribute(PREGNANT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getPregnantMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualInstance.CLASS);
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
  
  public Boolean getProperlyRelease()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(PROPERLYRELEASE));
  }
  
  public void validateProperlyRelease()
  {
    this.validateAttribute(PROPERLYRELEASE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getProperlyReleaseMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualInstance.CLASS);
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
  
  public dss.vector.solutions.ontology.Term getReferralReason()
  {
    if (getValue(REFERRALREASON).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(REFERRALREASON));
    }
  }
  
  public void validateReferralReason()
  {
    this.validateAttribute(REFERRALREASON);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getReferralReasonMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualInstance.CLASS);
    return mdClassIF.definesAttribute(REFERRALREASON);
  }
  
  public void setReferralReason(dss.vector.solutions.ontology.Term value)
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
  
  public Boolean getReferredFrom()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(REFERREDFROM));
  }
  
  public void validateReferredFrom()
  {
    this.validateAttribute(REFERREDFROM);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getReferredFromMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualInstance.CLASS);
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
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(REFERREDTO));
  }
  
  public void validateReferredTo()
  {
    this.validateAttribute(REFERREDTO);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getReferredToMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualInstance.CLASS);
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
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(RELEASEDATE));
  }
  
  public void validateReleaseDate()
  {
    this.validateAttribute(RELEASEDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getReleaseDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualInstance.CLASS);
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
      setValue(RELEASEDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public dss.vector.solutions.ontology.Term getSampleType()
  {
    if (getValue(SAMPLETYPE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(SAMPLETYPE));
    }
  }
  
  public void validateSampleType()
  {
    this.validateAttribute(SAMPLETYPE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSampleTypeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualInstance.CLASS);
    return mdClassIF.definesAttribute(SAMPLETYPE);
  }
  
  public void setSampleType(dss.vector.solutions.ontology.Term value)
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
  
  public Long getSeq()
  {
    return com.runwaysdk.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(SEQ));
  }
  
  public void validateSeq()
  {
    this.validateAttribute(SEQ);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSeqMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualInstance.CLASS);
    return mdClassIF.definesAttribute(SEQ);
  }
  
  public String getSiteMaster()
  {
    return getValue(SITEMASTER);
  }
  
  public void validateSiteMaster()
  {
    this.validateAttribute(SITEMASTER);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSiteMasterMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualInstance.CLASS);
    return mdClassIF.definesAttribute(SITEMASTER);
  }
  
  public dss.vector.solutions.ontology.Term getSymptom()
  {
    if (getValue(SYMPTOM).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(SYMPTOM));
    }
  }
  
  public void validateSymptom()
  {
    this.validateAttribute(SYMPTOM);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSymptomMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualInstance.CLASS);
    return mdClassIF.definesAttribute(SYMPTOM);
  }
  
  public void setSymptom(dss.vector.solutions.ontology.Term value)
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
  
  public String getSymptomComments()
  {
    return getValue(SYMPTOMCOMMENTS);
  }
  
  public void validateSymptomComments()
  {
    this.validateAttribute(SYMPTOMCOMMENTS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSymptomCommentsMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualInstance.CLASS);
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
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(SYMPTOMONSET));
  }
  
  public void validateSymptomOnset()
  {
    this.validateAttribute(SYMPTOMONSET);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSymptomOnsetMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualInstance.CLASS);
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
      setValue(SYMPTOMONSET, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public java.util.Date getTestSampleDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(TESTSAMPLEDATE));
  }
  
  public void validateTestSampleDate()
  {
    this.validateAttribute(TESTSAMPLEDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getTestSampleDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualInstance.CLASS);
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
      setValue(TESTSAMPLEDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public dss.vector.solutions.ontology.Term getTreatment()
  {
    if (getValue(TREATMENT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(TREATMENT));
    }
  }
  
  public void validateTreatment()
  {
    this.validateAttribute(TREATMENT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getTreatmentMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualInstance.CLASS);
    return mdClassIF.definesAttribute(TREATMENT);
  }
  
  public void setTreatment(dss.vector.solutions.ontology.Term value)
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
  
  public dss.vector.solutions.ontology.Term getTreatmentMethod()
  {
    if (getValue(TREATMENTMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(TREATMENTMETHOD));
    }
  }
  
  public void validateTreatmentMethod()
  {
    this.validateAttribute(TREATMENTMETHOD);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getTreatmentMethodMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualInstance.CLASS);
    return mdClassIF.definesAttribute(TREATMENTMETHOD);
  }
  
  public void setTreatmentMethod(dss.vector.solutions.ontology.Term value)
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
  
  public java.util.Date getTreatmentStartDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(TREATMENTSTARTDATE));
  }
  
  public void validateTreatmentStartDate()
  {
    this.validateAttribute(TREATMENTSTARTDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getTreatmentStartDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualInstance.CLASS);
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
      setValue(TREATMENTSTARTDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public String getType()
  {
    return getValue(TYPE);
  }
  
  public void validateType()
  {
    this.validateAttribute(TYPE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getTypeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualInstance.CLASS);
    return mdClassIF.definesAttribute(TYPE);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static IndividualInstanceQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    IndividualInstanceQuery query = new IndividualInstanceQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public dss.vector.solutions.surveillance.IndividualCaseSymptom addSymptoms(dss.vector.solutions.ontology.Term term)
  {
    return (dss.vector.solutions.surveillance.IndividualCaseSymptom) addChild(term, dss.vector.solutions.surveillance.IndividualCaseSymptom.CLASS);
  }
  
  public void removeSymptoms(dss.vector.solutions.ontology.Term term)
  {
    removeAllChildren(term, dss.vector.solutions.surveillance.IndividualCaseSymptom.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends dss.vector.solutions.ontology.Term> getAllSymptoms()
  {
    return (com.runwaysdk.query.OIterator<? extends dss.vector.solutions.ontology.Term>) getChildren(dss.vector.solutions.surveillance.IndividualCaseSymptom.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends dss.vector.solutions.surveillance.IndividualCaseSymptom> getAllSymptomsRel()
  {
    return (com.runwaysdk.query.OIterator<? extends dss.vector.solutions.surveillance.IndividualCaseSymptom>) getChildRelationships(dss.vector.solutions.surveillance.IndividualCaseSymptom.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public dss.vector.solutions.surveillance.IndividualCaseSymptom getSymptomsRel(dss.vector.solutions.ontology.Term term)
  {
    com.runwaysdk.query.OIterator<? extends dss.vector.solutions.surveillance.IndividualCaseSymptom> iterator = (com.runwaysdk.query.OIterator<? extends dss.vector.solutions.surveillance.IndividualCaseSymptom>) getRelationshipsWithChild(term, dss.vector.solutions.surveillance.IndividualCaseSymptom.CLASS);
    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }
      else
      {
        return null;
      }
    }
    finally
    {
      iterator.close();
    }
  }
  
  public static IndividualInstance get(String id)
  {
    return (IndividualInstance) com.runwaysdk.business.Business.get(id);
  }
  
  public static IndividualInstance getByKey(String key)
  {
    return (IndividualInstance) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public void applyAll(dss.vector.solutions.ontology.Term[] symptoms)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.IndividualInstance.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void applyAll(java.lang.String id, dss.vector.solutions.ontology.Term[] symptoms)
  {
    IndividualInstance _instance = IndividualInstance.get(id);
    _instance.applyAll(symptoms);
  }
  
  public dss.vector.solutions.ontology.Term[] getSymptoms()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.IndividualInstance.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.ontology.Term[] getSymptoms(java.lang.String id)
  {
    IndividualInstance _instance = IndividualInstance.get(id);
    return _instance.getSymptoms();
  }
  
  public static IndividualInstance lock(java.lang.String id)
  {
    IndividualInstance _instance = IndividualInstance.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static IndividualInstance unlock(java.lang.String id)
  {
    IndividualInstance _instance = IndividualInstance.get(id);
    _instance.unlock();
    
    return _instance;
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
