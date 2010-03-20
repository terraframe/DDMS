package dss.vector.solutions;

@com.runwaysdk.business.ClassSignature(hash = 1575067330)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to Person.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class PersonBase extends com.runwaysdk.business.Business implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.Person";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DATEOFBIRTH = "dateOfBirth";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String FIRSTNAME = "firstName";
  public static java.lang.String ID = "id";
  public static java.lang.String IPTRECIPIENTDELEGATE = "iptRecipientDelegate";
  public static java.lang.String ITNRECIPIENTDELEGATE = "itnRecipientDelegate";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTNAME = "lastName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String PATIENTDELEGATE = "patientDelegate";
  public static java.lang.String RESIDENTIALGEOENTITY = "residentialGeoEntity";
  public static java.lang.String RESIDENTIALINFORMATION = "residentialInformation";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SEX = "sex";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String STOCKSTAFFDELEGATE = "stockStaffDelegate";
  public static java.lang.String SUPERVISORDELEGATE = "supervisorDelegate";
  public static java.lang.String TEAMMEMBERDELEGATE = "teamMemberDelegate";
  public static java.lang.String TYPE = "type";
  public static java.lang.String USERDELEGATE = "userDelegate";
  public static java.lang.String WORKGEOENTITY = "workGeoEntity";
  public static java.lang.String WORKINFORMATION = "workInformation";
  private static final long serialVersionUID = 1575067330;
  
  public PersonBase()
  {
    super();
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.Person.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.Person.CLASS);
    return mdClassIF.definesAttribute(CREATEDBY);
  }
  
  public java.util.Date getDateOfBirth()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(DATEOFBIRTH));
  }
  
  public void validateDateOfBirth()
  {
    this.validateAttribute(DATEOFBIRTH);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getDateOfBirthMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.Person.CLASS);
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
      setValue(DATEOFBIRTH, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.Person.CLASS);
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
  
  public String getFirstName()
  {
    return getValue(FIRSTNAME);
  }
  
  public void validateFirstName()
  {
    this.validateAttribute(FIRSTNAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getFirstNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.Person.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.Person.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public dss.vector.solutions.intervention.monitor.IPTRecipient getIptRecipientDelegate()
  {
    if (getValue(IPTRECIPIENTDELEGATE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.intervention.monitor.IPTRecipient.get(getValue(IPTRECIPIENTDELEGATE));
    }
  }
  
  public void validateIptRecipientDelegate()
  {
    this.validateAttribute(IPTRECIPIENTDELEGATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getIptRecipientDelegateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.Person.CLASS);
    return mdClassIF.definesAttribute(IPTRECIPIENTDELEGATE);
  }
  
  public void setIptRecipientDelegate(dss.vector.solutions.intervention.monitor.IPTRecipient value)
  {
    if(value == null)
    {
      setValue(IPTRECIPIENTDELEGATE, "");
    }
    else
    {
      setValue(IPTRECIPIENTDELEGATE, value.getId());
    }
  }
  
  public dss.vector.solutions.intervention.monitor.ITNRecipient getItnRecipientDelegate()
  {
    if (getValue(ITNRECIPIENTDELEGATE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.intervention.monitor.ITNRecipient.get(getValue(ITNRECIPIENTDELEGATE));
    }
  }
  
  public void validateItnRecipientDelegate()
  {
    this.validateAttribute(ITNRECIPIENTDELEGATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getItnRecipientDelegateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.Person.CLASS);
    return mdClassIF.definesAttribute(ITNRECIPIENTDELEGATE);
  }
  
  public void setItnRecipientDelegate(dss.vector.solutions.intervention.monitor.ITNRecipient value)
  {
    if(value == null)
    {
      setValue(ITNRECIPIENTDELEGATE, "");
    }
    else
    {
      setValue(ITNRECIPIENTDELEGATE, value.getId());
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.Person.CLASS);
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
  
  public String getLastName()
  {
    return getValue(LASTNAME);
  }
  
  public void validateLastName()
  {
    this.validateAttribute(LASTNAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getLastNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.Person.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.Person.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.Person.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.Person.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.Person.CLASS);
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
  
  public dss.vector.solutions.Patient getPatientDelegate()
  {
    if (getValue(PATIENTDELEGATE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.Patient.get(getValue(PATIENTDELEGATE));
    }
  }
  
  public void validatePatientDelegate()
  {
    this.validateAttribute(PATIENTDELEGATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getPatientDelegateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.Person.CLASS);
    return mdClassIF.definesAttribute(PATIENTDELEGATE);
  }
  
  public void setPatientDelegate(dss.vector.solutions.Patient value)
  {
    if(value == null)
    {
      setValue(PATIENTDELEGATE, "");
    }
    else
    {
      setValue(PATIENTDELEGATE, value.getId());
    }
  }
  
  public dss.vector.solutions.geo.generated.GeoEntity getResidentialGeoEntity()
  {
    if (getValue(RESIDENTIALGEOENTITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntity.get(getValue(RESIDENTIALGEOENTITY));
    }
  }
  
  public void validateResidentialGeoEntity()
  {
    this.validateAttribute(RESIDENTIALGEOENTITY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getResidentialGeoEntityMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.Person.CLASS);
    return mdClassIF.definesAttribute(RESIDENTIALGEOENTITY);
  }
  
  public void setResidentialGeoEntity(dss.vector.solutions.geo.generated.GeoEntity value)
  {
    if(value == null)
    {
      setValue(RESIDENTIALGEOENTITY, "");
    }
    else
    {
      setValue(RESIDENTIALGEOENTITY, value.getId());
    }
  }
  
  public String getResidentialInformation()
  {
    return getValue(RESIDENTIALINFORMATION);
  }
  
  public void validateResidentialInformation()
  {
    this.validateAttribute(RESIDENTIALINFORMATION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getResidentialInformationMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.Person.CLASS);
    return mdClassIF.definesAttribute(RESIDENTIALINFORMATION);
  }
  
  public void setResidentialInformation(String value)
  {
    if(value == null)
    {
      setValue(RESIDENTIALINFORMATION, "");
    }
    else
    {
      setValue(RESIDENTIALINFORMATION, value);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.Person.CLASS);
    return mdClassIF.definesAttribute(SEQ);
  }
  
  public dss.vector.solutions.ontology.Term getSex()
  {
    if (getValue(SEX).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(SEX));
    }
  }
  
  public void validateSex()
  {
    this.validateAttribute(SEX);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSexMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.Person.CLASS);
    return mdClassIF.definesAttribute(SEX);
  }
  
  public void setSex(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(SEX, "");
    }
    else
    {
      setValue(SEX, value.getId());
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSiteMasterMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.Person.CLASS);
    return mdClassIF.definesAttribute(SITEMASTER);
  }
  
  public dss.vector.solutions.stock.StockStaff getStockStaffDelegate()
  {
    if (getValue(STOCKSTAFFDELEGATE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.stock.StockStaff.get(getValue(STOCKSTAFFDELEGATE));
    }
  }
  
  public void validateStockStaffDelegate()
  {
    this.validateAttribute(STOCKSTAFFDELEGATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getStockStaffDelegateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.Person.CLASS);
    return mdClassIF.definesAttribute(STOCKSTAFFDELEGATE);
  }
  
  public void setStockStaffDelegate(dss.vector.solutions.stock.StockStaff value)
  {
    if(value == null)
    {
      setValue(STOCKSTAFFDELEGATE, "");
    }
    else
    {
      setValue(STOCKSTAFFDELEGATE, value.getId());
    }
  }
  
  public dss.vector.solutions.irs.Supervisor getSupervisorDelegate()
  {
    if (getValue(SUPERVISORDELEGATE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.Supervisor.get(getValue(SUPERVISORDELEGATE));
    }
  }
  
  public void validateSupervisorDelegate()
  {
    this.validateAttribute(SUPERVISORDELEGATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSupervisorDelegateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.Person.CLASS);
    return mdClassIF.definesAttribute(SUPERVISORDELEGATE);
  }
  
  public void setSupervisorDelegate(dss.vector.solutions.irs.Supervisor value)
  {
    if(value == null)
    {
      setValue(SUPERVISORDELEGATE, "");
    }
    else
    {
      setValue(SUPERVISORDELEGATE, value.getId());
    }
  }
  
  public dss.vector.solutions.irs.TeamMember getTeamMemberDelegate()
  {
    if (getValue(TEAMMEMBERDELEGATE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.TeamMember.get(getValue(TEAMMEMBERDELEGATE));
    }
  }
  
  public void validateTeamMemberDelegate()
  {
    this.validateAttribute(TEAMMEMBERDELEGATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getTeamMemberDelegateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.Person.CLASS);
    return mdClassIF.definesAttribute(TEAMMEMBERDELEGATE);
  }
  
  public void setTeamMemberDelegate(dss.vector.solutions.irs.TeamMember value)
  {
    if(value == null)
    {
      setValue(TEAMMEMBERDELEGATE, "");
    }
    else
    {
      setValue(TEAMMEMBERDELEGATE, value.getId());
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.Person.CLASS);
    return mdClassIF.definesAttribute(TYPE);
  }
  
  public dss.vector.solutions.MDSSUser getUserDelegate()
  {
    if (getValue(USERDELEGATE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.MDSSUser.get(getValue(USERDELEGATE));
    }
  }
  
  public void validateUserDelegate()
  {
    this.validateAttribute(USERDELEGATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getUserDelegateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.Person.CLASS);
    return mdClassIF.definesAttribute(USERDELEGATE);
  }
  
  public void setUserDelegate(dss.vector.solutions.MDSSUser value)
  {
    if(value == null)
    {
      setValue(USERDELEGATE, "");
    }
    else
    {
      setValue(USERDELEGATE, value.getId());
    }
  }
  
  public dss.vector.solutions.geo.generated.GeoEntity getWorkGeoEntity()
  {
    if (getValue(WORKGEOENTITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntity.get(getValue(WORKGEOENTITY));
    }
  }
  
  public void validateWorkGeoEntity()
  {
    this.validateAttribute(WORKGEOENTITY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getWorkGeoEntityMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.Person.CLASS);
    return mdClassIF.definesAttribute(WORKGEOENTITY);
  }
  
  public void setWorkGeoEntity(dss.vector.solutions.geo.generated.GeoEntity value)
  {
    if(value == null)
    {
      setValue(WORKGEOENTITY, "");
    }
    else
    {
      setValue(WORKGEOENTITY, value.getId());
    }
  }
  
  public String getWorkInformation()
  {
    return getValue(WORKINFORMATION);
  }
  
  public void validateWorkInformation()
  {
    this.validateAttribute(WORKINFORMATION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getWorkInformationMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.Person.CLASS);
    return mdClassIF.definesAttribute(WORKINFORMATION);
  }
  
  public void setWorkInformation(String value)
  {
    if(value == null)
    {
      setValue(WORKINFORMATION, "");
    }
    else
    {
      setValue(WORKINFORMATION, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static PersonQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    PersonQuery query = new PersonQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static Person get(String id)
  {
    return (Person) com.runwaysdk.business.Business.get(id);
  }
  
  public static Person getByKey(String key)
  {
    return (Person) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public dss.vector.solutions.PersonView getView()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.Person.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.PersonView getView(java.lang.String id)
  {
    Person _instance = Person.get(id);
    return _instance.getView();
  }
  
  public dss.vector.solutions.PersonView lockView()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.Person.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.PersonView lockView(java.lang.String id)
  {
    Person _instance = Person.get(id);
    return _instance.lockView();
  }
  
  public dss.vector.solutions.PersonWithDelegatesViewQuery searchForDuplicates()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.Person.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.PersonWithDelegatesViewQuery searchForDuplicates(java.lang.String id)
  {
    Person _instance = Person.get(id);
    return _instance.searchForDuplicates();
  }
  
  public static com.runwaysdk.query.ValueQuery searchForPerson(java.lang.String value)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.Person.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public void unlockView()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.Person.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void unlockView(java.lang.String id)
  {
    Person _instance = Person.get(id);
    _instance.unlockView();
  }
  
  public static Person lock(java.lang.String id)
  {
    Person _instance = Person.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static Person unlock(java.lang.String id)
  {
    Person _instance = Person.get(id);
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
