package dss.vector.solutions.intervention.monitor;

@com.terraframe.mojo.business.ClassSignature(hash = -102746299)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to IndividualIPT.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class IndividualIPTBase extends com.terraframe.mojo.business.Business implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.IndividualIPT";
  public static java.lang.String ADMINISTRATORNAME = "administratorName";
  public static java.lang.String ADMINISTRATORSURNAME = "administratorSurname";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DOSENUMBER = "doseNumber";
  public static java.lang.String DOSETYPE = "doseType";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String FACILITY = "facility";
  public static java.lang.String ID = "id";
  public static java.lang.String IPTCASE = "iptCase";
  public static java.lang.String ISANCVISIT = "isANCVisit";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String NUMBEROFRECEIVEDITNS = "numberOfReceivedITNs";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String PATIENTTYPE = "patientType";
  public static java.lang.String RECEIVEDITN = "receivedITN";
  public static java.lang.String RECEIVEDSUPPLEMENT = "receivedSupplement";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SERVICEDATE = "serviceDate";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TYPE = "type";
  public static java.lang.String VISITNUMBER = "visitNumber";
  private static final long serialVersionUID = -102746299;
  
  public IndividualIPTBase()
  {
    super();
  }
  
  public String getAdministratorName()
  {
    return getValue(ADMINISTRATORNAME);
  }
  
  public void validateAdministratorName()
  {
    this.validateAttribute(ADMINISTRATORNAME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getAdministratorNameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return mdClassIF.definesAttribute(ADMINISTRATORNAME);
  }
  
  public void setAdministratorName(String value)
  {
    if(value == null)
    {
      setValue(ADMINISTRATORNAME, "");
    }
    else
    {
      setValue(ADMINISTRATORNAME, value);
    }
  }
  
  public String getAdministratorSurname()
  {
    return getValue(ADMINISTRATORSURNAME);
  }
  
  public void validateAdministratorSurname()
  {
    this.validateAttribute(ADMINISTRATORSURNAME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getAdministratorSurnameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return mdClassIF.definesAttribute(ADMINISTRATORSURNAME);
  }
  
  public void setAdministratorSurname(String value)
  {
    if(value == null)
    {
      setValue(ADMINISTRATORSURNAME, "");
    }
    else
    {
      setValue(ADMINISTRATORSURNAME, value);
    }
  }
  
  public java.util.Date getCreateDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(CREATEDATE));
  }
  
  public void validateCreateDate()
  {
    this.validateAttribute(CREATEDATE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getCreateDateMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return mdClassIF.definesAttribute(CREATEDATE);
  }
  
  public com.terraframe.mojo.system.SingleActor getCreatedBy()
  {
    if (getValue(CREATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.SingleActor.get(getValue(CREATEDBY));
    }
  }
  
  public void validateCreatedBy()
  {
    this.validateAttribute(CREATEDBY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getCreatedByMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return mdClassIF.definesAttribute(CREATEDBY);
  }
  
  public dss.vector.solutions.ontology.Term getDoseNumber()
  {
    if (getValue(DOSENUMBER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(DOSENUMBER));
    }
  }
  
  public void validateDoseNumber()
  {
    this.validateAttribute(DOSENUMBER);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getDoseNumberMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return mdClassIF.definesAttribute(DOSENUMBER);
  }
  
  public void setDoseNumber(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(DOSENUMBER, "");
    }
    else
    {
      setValue(DOSENUMBER, value.getId());
    }
  }
  
  public dss.vector.solutions.ontology.Term getDoseType()
  {
    if (getValue(DOSETYPE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(DOSETYPE));
    }
  }
  
  public void validateDoseType()
  {
    this.validateAttribute(DOSETYPE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getDoseTypeMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return mdClassIF.definesAttribute(DOSETYPE);
  }
  
  public void setDoseType(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(DOSETYPE, "");
    }
    else
    {
      setValue(DOSETYPE, value.getId());
    }
  }
  
  public com.terraframe.mojo.system.metadata.MdDomain getEntityDomain()
  {
    if (getValue(ENTITYDOMAIN).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.metadata.MdDomain.get(getValue(ENTITYDOMAIN));
    }
  }
  
  public void validateEntityDomain()
  {
    this.validateAttribute(ENTITYDOMAIN);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getEntityDomainMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return mdClassIF.definesAttribute(ENTITYDOMAIN);
  }
  
  public void setEntityDomain(com.terraframe.mojo.system.metadata.MdDomain value)
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
  
  public dss.vector.solutions.geo.generated.HealthFacility getFacility()
  {
    if (getValue(FACILITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.HealthFacility.get(getValue(FACILITY));
    }
  }
  
  public void validateFacility()
  {
    this.validateAttribute(FACILITY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getFacilityMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return mdClassIF.definesAttribute(FACILITY);
  }
  
  public void setFacility(dss.vector.solutions.geo.generated.HealthFacility value)
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public dss.vector.solutions.intervention.monitor.IndividualIPTCase getIptCase()
  {
    if (getValue(IPTCASE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.intervention.monitor.IndividualIPTCase.get(getValue(IPTCASE));
    }
  }
  
  public void validateIptCase()
  {
    this.validateAttribute(IPTCASE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIptCaseMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return mdClassIF.definesAttribute(IPTCASE);
  }
  
  public void setIptCase(dss.vector.solutions.intervention.monitor.IndividualIPTCase value)
  {
    if(value == null)
    {
      setValue(IPTCASE, "");
    }
    else
    {
      setValue(IPTCASE, value.getId());
    }
  }
  
  public Boolean getIsANCVisit()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISANCVISIT));
  }
  
  public void validateIsANCVisit()
  {
    this.validateAttribute(ISANCVISIT);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIsANCVisitMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return mdClassIF.definesAttribute(ISANCVISIT);
  }
  
  public void setIsANCVisit(Boolean value)
  {
    if(value == null)
    {
      setValue(ISANCVISIT, "");
    }
    else
    {
      setValue(ISANCVISIT, java.lang.Boolean.toString(value));
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
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getKeyNameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
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
    return com.terraframe.mojo.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(LASTUPDATEDATE));
  }
  
  public void validateLastUpdateDate()
  {
    this.validateAttribute(LASTUPDATEDATE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getLastUpdateDateMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return mdClassIF.definesAttribute(LASTUPDATEDATE);
  }
  
  public com.terraframe.mojo.system.SingleActor getLastUpdatedBy()
  {
    if (getValue(LASTUPDATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.SingleActor.get(getValue(LASTUPDATEDBY));
    }
  }
  
  public void validateLastUpdatedBy()
  {
    this.validateAttribute(LASTUPDATEDBY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getLastUpdatedByMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return mdClassIF.definesAttribute(LASTUPDATEDBY);
  }
  
  public com.terraframe.mojo.system.Users getLockedBy()
  {
    if (getValue(LOCKEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.Users.get(getValue(LOCKEDBY));
    }
  }
  
  public void validateLockedBy()
  {
    this.validateAttribute(LOCKEDBY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getLockedByMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return mdClassIF.definesAttribute(LOCKEDBY);
  }
  
  public Integer getNumberOfReceivedITNs()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBEROFRECEIVEDITNS));
  }
  
  public void validateNumberOfReceivedITNs()
  {
    this.validateAttribute(NUMBEROFRECEIVEDITNS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getNumberOfReceivedITNsMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return mdClassIF.definesAttribute(NUMBEROFRECEIVEDITNS);
  }
  
  public void setNumberOfReceivedITNs(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBEROFRECEIVEDITNS, "");
    }
    else
    {
      setValue(NUMBEROFRECEIVEDITNS, java.lang.Integer.toString(value));
    }
  }
  
  public com.terraframe.mojo.system.Actor getOwner()
  {
    if (getValue(OWNER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.terraframe.mojo.system.Actor.get(getValue(OWNER));
    }
  }
  
  public void validateOwner()
  {
    this.validateAttribute(OWNER);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getOwnerMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return mdClassIF.definesAttribute(OWNER);
  }
  
  public void setOwner(com.terraframe.mojo.system.Actor value)
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
  
  public dss.vector.solutions.ontology.Term getPatientType()
  {
    if (getValue(PATIENTTYPE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(PATIENTTYPE));
    }
  }
  
  public void validatePatientType()
  {
    this.validateAttribute(PATIENTTYPE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getPatientTypeMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return mdClassIF.definesAttribute(PATIENTTYPE);
  }
  
  public void setPatientType(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(PATIENTTYPE, "");
    }
    else
    {
      setValue(PATIENTTYPE, value.getId());
    }
  }
  
  public Boolean getReceivedITN()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(RECEIVEDITN));
  }
  
  public void validateReceivedITN()
  {
    this.validateAttribute(RECEIVEDITN);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getReceivedITNMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return mdClassIF.definesAttribute(RECEIVEDITN);
  }
  
  public void setReceivedITN(Boolean value)
  {
    if(value == null)
    {
      setValue(RECEIVEDITN, "");
    }
    else
    {
      setValue(RECEIVEDITN, java.lang.Boolean.toString(value));
    }
  }
  
  public Boolean getReceivedSupplement()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(RECEIVEDSUPPLEMENT));
  }
  
  public void validateReceivedSupplement()
  {
    this.validateAttribute(RECEIVEDSUPPLEMENT);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getReceivedSupplementMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return mdClassIF.definesAttribute(RECEIVEDSUPPLEMENT);
  }
  
  public void setReceivedSupplement(Boolean value)
  {
    if(value == null)
    {
      setValue(RECEIVEDSUPPLEMENT, "");
    }
    else
    {
      setValue(RECEIVEDSUPPLEMENT, java.lang.Boolean.toString(value));
    }
  }
  
  public Long getSeq()
  {
    return com.terraframe.mojo.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(SEQ));
  }
  
  public void validateSeq()
  {
    this.validateAttribute(SEQ);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSeqMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return mdClassIF.definesAttribute(SEQ);
  }
  
  public java.util.Date getServiceDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(SERVICEDATE));
  }
  
  public void validateServiceDate()
  {
    this.validateAttribute(SERVICEDATE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getServiceDateMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return mdClassIF.definesAttribute(SERVICEDATE);
  }
  
  public void setServiceDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(SERVICEDATE, "");
    }
    else
    {
      setValue(SERVICEDATE, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public String getSiteMaster()
  {
    return getValue(SITEMASTER);
  }
  
  public void validateSiteMaster()
  {
    this.validateAttribute(SITEMASTER);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSiteMasterMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
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
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getTypeMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return mdClassIF.definesAttribute(TYPE);
  }
  
  public dss.vector.solutions.ontology.Term getVisitNumber()
  {
    if (getValue(VISITNUMBER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(VISITNUMBER));
    }
  }
  
  public void validateVisitNumber()
  {
    this.validateAttribute(VISITNUMBER);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getVisitNumberMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return mdClassIF.definesAttribute(VISITNUMBER);
  }
  
  public void setVisitNumber(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(VISITNUMBER, "");
    }
    else
    {
      setValue(VISITNUMBER, value.getId());
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static IndividualIPTQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    IndividualIPTQuery query = new IndividualIPTQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static IndividualIPT get(String id)
  {
    return (IndividualIPT) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static IndividualIPT getByKey(String key)
  {
    return (IndividualIPT) com.terraframe.mojo.business.Business.get(CLASS, key);
  }
  
  public static dss.vector.solutions.intervention.monitor.IndividualIPTView getView(java.lang.String id)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.IndividualIPT.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public dss.vector.solutions.intervention.monitor.IndividualIPTView lockView()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.IndividualIPT.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.intervention.monitor.IndividualIPTView lockView(java.lang.String id)
  {
    IndividualIPT _instance = IndividualIPT.get(id);
    return _instance.lockView();
  }
  
  public dss.vector.solutions.intervention.monitor.IndividualIPTView unlockView()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.IndividualIPT.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.intervention.monitor.IndividualIPTView unlockView(java.lang.String id)
  {
    IndividualIPT _instance = IndividualIPT.get(id);
    return _instance.unlockView();
  }
  
  public static IndividualIPT lock(java.lang.String id)
  {
    IndividualIPT _instance = IndividualIPT.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static IndividualIPT unlock(java.lang.String id)
  {
    IndividualIPT _instance = IndividualIPT.get(id);
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
