package dss.vector.solutions.intervention.monitor;

@com.runwaysdk.business.ClassSignature(hash = -447147825)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to IndividualCase.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class IndividualCaseBase extends com.runwaysdk.business.Business implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.IndividualCase";
  public static java.lang.String AGE = "age";
  public static java.lang.String CASEENTRYDATE = "caseEntryDate";
  public static java.lang.String CASEREPORTDATE = "caseReportDate";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DIAGNOSISDATE = "diagnosisDate";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String PATIENT = "patient";
  public static java.lang.String PROBABLESOURCE = "probableSource";
  public static java.lang.String PROBABLESOURCETEXT = "probableSourceText";
  public static java.lang.String RESIDENCE = "residence";
  public static java.lang.String RESIDENCETEXT = "residenceText";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TYPE = "type";
  public static java.lang.String WORKPLACE = "workplace";
  public static java.lang.String WORKPLACETEXT = "workplaceText";
  private static final long serialVersionUID = -447147825;
  
  public IndividualCaseBase()
  {
    super();
  }
  
  public Integer getAge()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(AGE));
  }
  
  public void validateAge()
  {
    this.validateAttribute(AGE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getAgeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualCase.CLASS);
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
  
  public java.util.Date getCaseEntryDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(CASEENTRYDATE));
  }
  
  public void validateCaseEntryDate()
  {
    this.validateAttribute(CASEENTRYDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getCaseEntryDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualCase.CLASS);
    return mdClassIF.definesAttribute(CASEENTRYDATE);
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
  
  public java.util.Date getCaseReportDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(CASEREPORTDATE));
  }
  
  public void validateCaseReportDate()
  {
    this.validateAttribute(CASEREPORTDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getCaseReportDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualCase.CLASS);
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
      setValue(CASEREPORTDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualCase.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualCase.CLASS);
    return mdClassIF.definesAttribute(CREATEDBY);
  }
  
  public java.util.Date getDiagnosisDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(DIAGNOSISDATE));
  }
  
  public void validateDiagnosisDate()
  {
    this.validateAttribute(DIAGNOSISDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getDiagnosisDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualCase.CLASS);
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
      setValue(DIAGNOSISDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualCase.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualCase.CLASS);
    return mdClassIF.definesAttribute(ID);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualCase.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualCase.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualCase.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualCase.CLASS);
    return mdClassIF.definesAttribute(LOCKEDBY);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualCase.CLASS);
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
  
  public dss.vector.solutions.Patient getPatient()
  {
    if (getValue(PATIENT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.Patient.get(getValue(PATIENT));
    }
  }
  
  public void validatePatient()
  {
    this.validateAttribute(PATIENT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getPatientMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualCase.CLASS);
    return mdClassIF.definesAttribute(PATIENT);
  }
  
  public void setPatient(dss.vector.solutions.Patient value)
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getProbableSourceMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualCase.CLASS);
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
  
  public String getProbableSourceText()
  {
    return getValue(PROBABLESOURCETEXT);
  }
  
  public void validateProbableSourceText()
  {
    this.validateAttribute(PROBABLESOURCETEXT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getProbableSourceTextMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualCase.CLASS);
    return mdClassIF.definesAttribute(PROBABLESOURCETEXT);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getResidenceMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualCase.CLASS);
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
  
  public String getResidenceText()
  {
    return getValue(RESIDENCETEXT);
  }
  
  public void validateResidenceText()
  {
    this.validateAttribute(RESIDENCETEXT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getResidenceTextMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualCase.CLASS);
    return mdClassIF.definesAttribute(RESIDENCETEXT);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualCase.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualCase.CLASS);
    return mdClassIF.definesAttribute(SITEMASTER);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualCase.CLASS);
    return mdClassIF.definesAttribute(TYPE);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getWorkplaceMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualCase.CLASS);
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
  
  public String getWorkplaceText()
  {
    return getValue(WORKPLACETEXT);
  }
  
  public void validateWorkplaceText()
  {
    this.validateAttribute(WORKPLACETEXT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getWorkplaceTextMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualCase.CLASS);
    return mdClassIF.definesAttribute(WORKPLACETEXT);
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
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static IndividualCaseQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    IndividualCaseQuery query = new IndividualCaseQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static IndividualCase get(String id)
  {
    return (IndividualCase) com.runwaysdk.business.Business.get(id);
  }
  
  public static IndividualCase getByKey(String key)
  {
    return (IndividualCase) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public void applyWithPersonId(java.lang.String personId, dss.vector.solutions.intervention.monitor.IndividualInstance instance, dss.vector.solutions.ontology.Term[] symptoms)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.IndividualCase.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void applyWithPersonId(java.lang.String id, java.lang.String personId, dss.vector.solutions.intervention.monitor.IndividualInstance instance, dss.vector.solutions.ontology.Term[] symptoms)
  {
    IndividualCase _instance = IndividualCase.get(id);
    _instance.applyWithPersonId(personId, instance, symptoms);
  }
  
  public dss.vector.solutions.intervention.monitor.IndividualInstanceQuery getInstances()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.IndividualCase.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.intervention.monitor.IndividualInstanceQuery getInstances(java.lang.String id)
  {
    IndividualCase _instance = IndividualCase.get(id);
    return _instance.getInstances();
  }
  
  public static dss.vector.solutions.intervention.monitor.IndividualCase searchForExistingCase(java.util.Date diagnosisDate, java.lang.String patientId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.IndividualCase.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static IndividualCase lock(java.lang.String id)
  {
    IndividualCase _instance = IndividualCase.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static IndividualCase unlock(java.lang.String id)
  {
    IndividualCase _instance = IndividualCase.get(id);
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
