package dss.vector.solutions.intervention.monitor;

@com.runwaysdk.business.ClassSignature(hash = -556466934)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to IndividualIPT.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class IndividualIPTBase extends com.runwaysdk.business.Business implements com.runwaysdk.generation.loader.Reloadable
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
  private static final long serialVersionUID = -556466934;
  
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
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getAdministratorNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ADMINISTRATORNAME);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getAdministratorSurnameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ADMINISTRATORSURNAME);
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
    return com.runwaysdk.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(CREATEDATE));
  }
  
  public void validateCreateDate()
  {
    this.validateAttribute(CREATEDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDateTimeDAOIF getCreateDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDateTimeDAOIF)mdClassIF.definesAttribute(CREATEDATE);
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
  
  public String getCreatedById()
  {
    return getValue(CREATEDBY);
  }
  
  public void validateCreatedBy()
  {
    this.validateAttribute(CREATEDBY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getCreatedByMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(CREATEDBY);
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
  
  public String getDoseNumberId()
  {
    return getValue(DOSENUMBER);
  }
  
  public void validateDoseNumber()
  {
    this.validateAttribute(DOSENUMBER);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getDoseNumberMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(DOSENUMBER);
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
  
  public String getDoseTypeId()
  {
    return getValue(DOSETYPE);
  }
  
  public void validateDoseType()
  {
    this.validateAttribute(DOSETYPE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getDoseTypeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(DOSETYPE);
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
  
  public String getEntityDomainId()
  {
    return getValue(ENTITYDOMAIN);
  }
  
  public void validateEntityDomain()
  {
    this.validateAttribute(ENTITYDOMAIN);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getEntityDomainMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(ENTITYDOMAIN);
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
  
  public String getFacilityId()
  {
    return getValue(FACILITY);
  }
  
  public void validateFacility()
  {
    this.validateAttribute(FACILITY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getFacilityMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(FACILITY);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
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
  
  public String getIptCaseId()
  {
    return getValue(IPTCASE);
  }
  
  public void validateIptCase()
  {
    this.validateAttribute(IPTCASE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getIptCaseMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(IPTCASE);
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
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISANCVISIT));
  }
  
  public void validateIsANCVisit()
  {
    this.validateAttribute(ISANCVISIT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF getIsANCVisitMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF)mdClassIF.definesAttribute(ISANCVISIT);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getKeyNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(KEYNAME);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDateTimeDAOIF getLastUpdateDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDateTimeDAOIF)mdClassIF.definesAttribute(LASTUPDATEDATE);
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
  
  public String getLastUpdatedById()
  {
    return getValue(LASTUPDATEDBY);
  }
  
  public void validateLastUpdatedBy()
  {
    this.validateAttribute(LASTUPDATEDBY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getLastUpdatedByMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(LASTUPDATEDBY);
  }
  
  public com.runwaysdk.system.SingleActor getLockedBy()
  {
    if (getValue(LOCKEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.SingleActor.get(getValue(LOCKEDBY));
    }
  }
  
  public String getLockedById()
  {
    return getValue(LOCKEDBY);
  }
  
  public void validateLockedBy()
  {
    this.validateAttribute(LOCKEDBY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getLockedByMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(LOCKEDBY);
  }
  
  public Integer getNumberOfReceivedITNs()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBEROFRECEIVEDITNS));
  }
  
  public void validateNumberOfReceivedITNs()
  {
    this.validateAttribute(NUMBEROFRECEIVEDITNS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getNumberOfReceivedITNsMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(NUMBEROFRECEIVEDITNS);
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
  
  public String getOwnerId()
  {
    return getValue(OWNER);
  }
  
  public void validateOwner()
  {
    this.validateAttribute(OWNER);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getOwnerMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(OWNER);
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
  
  public String getPatientTypeId()
  {
    return getValue(PATIENTTYPE);
  }
  
  public void validatePatientType()
  {
    this.validateAttribute(PATIENTTYPE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getPatientTypeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(PATIENTTYPE);
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
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(RECEIVEDITN));
  }
  
  public void validateReceivedITN()
  {
    this.validateAttribute(RECEIVEDITN);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF getReceivedITNMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF)mdClassIF.definesAttribute(RECEIVEDITN);
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
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(RECEIVEDSUPPLEMENT));
  }
  
  public void validateReceivedSupplement()
  {
    this.validateAttribute(RECEIVEDSUPPLEMENT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF getReceivedSupplementMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF)mdClassIF.definesAttribute(RECEIVEDSUPPLEMENT);
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
    return com.runwaysdk.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(SEQ));
  }
  
  public void validateSeq()
  {
    this.validateAttribute(SEQ);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeLongDAOIF getSeqMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeLongDAOIF)mdClassIF.definesAttribute(SEQ);
  }
  
  public java.util.Date getServiceDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(SERVICEDATE));
  }
  
  public void validateServiceDate()
  {
    this.validateAttribute(SERVICEDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDateDAOIF getServiceDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDateDAOIF)mdClassIF.definesAttribute(SERVICEDATE);
  }
  
  public void setServiceDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(SERVICEDATE, "");
    }
    else
    {
      setValue(SERVICEDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
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
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getSiteMasterMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(SITEMASTER);
  }
  
  public String getType()
  {
    return getValue(TYPE);
  }
  
  public void validateType()
  {
    this.validateAttribute(TYPE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getTypeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(TYPE);
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
  
  public String getVisitNumberId()
  {
    return getValue(VISITNUMBER);
  }
  
  public void validateVisitNumber()
  {
    this.validateAttribute(VISITNUMBER);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getVisitNumberMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.intervention.monitor.IndividualIPT.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(VISITNUMBER);
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
    IndividualIPTQuery query = new IndividualIPTQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static IndividualIPT get(String id)
  {
    return (IndividualIPT) com.runwaysdk.business.Business.get(id);
  }
  
  public static IndividualIPT getByKey(String key)
  {
    return (IndividualIPT) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public static dss.vector.solutions.intervention.monitor.IndividualIPTView getView(java.lang.String id)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.IndividualIPT.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public dss.vector.solutions.intervention.monitor.IndividualIPTView lockView()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.IndividualIPT.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.intervention.monitor.IndividualIPTView lockView(java.lang.String id)
  {
    IndividualIPT _instance = IndividualIPT.get(id);
    return _instance.lockView();
  }
  
  public dss.vector.solutions.intervention.monitor.IndividualIPTView unlockView()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.intervention.monitor.IndividualIPT.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
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
