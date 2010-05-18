package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = -559875454)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to SystemAlert.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class SystemAlertBase extends com.runwaysdk.business.Business implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.SystemAlert";
  public static java.lang.String ALERTTYPE = "alertType";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DISEASE = "disease";
  public static java.lang.String EMAILBCCADDRESSES = "emailBccAddresses";
  public static java.lang.String EMAILBODY = "emailBody";
  public static java.lang.String EMAILCCADDRESSES = "emailCcAddresses";
  public static java.lang.String EMAILFROMADDRESS = "emailFromAddress";
  public static java.lang.String EMAILSUBJECT = "emailSubject";
  public static java.lang.String EMAILTOADDRESSES = "emailToAddresses";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String ID = "id";
  public static java.lang.String ISEMAILACTIVE = "isEmailActive";
  public static java.lang.String ISONSCREENACTIVE = "isOnscreenActive";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TYPE = "type";
  private static final long serialVersionUID = -559875454;
  
  public SystemAlertBase()
  {
    super();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.general.SystemAlertType> getAlertType()
  {
    return (java.util.List<dss.vector.solutions.general.SystemAlertType>) getEnumValues(ALERTTYPE);
  }
  
  public void addAlertType(dss.vector.solutions.general.SystemAlertType value)
  {
    if(value != null)
    {
      addEnumItem(ALERTTYPE, value.getId());
    }
  }
  
  public void removeAlertType(dss.vector.solutions.general.SystemAlertType value)
  {
    if(value != null)
    {
      removeEnumItem(ALERTTYPE, value.getId());
    }
  }
  
  public void clearAlertType()
  {
    clearEnum(ALERTTYPE);
  }
  
  public void validateAlertType()
  {
    this.validateAttribute(ALERTTYPE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getAlertTypeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.SystemAlert.CLASS);
    return mdClassIF.definesAttribute(ALERTTYPE);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.SystemAlert.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.SystemAlert.CLASS);
    return mdClassIF.definesAttribute(CREATEDBY);
  }
  
  public dss.vector.solutions.general.Disease getDisease()
  {
    if (getValue(DISEASE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.general.Disease.get(getValue(DISEASE));
    }
  }
  
  public void validateDisease()
  {
    this.validateAttribute(DISEASE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getDiseaseMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.SystemAlert.CLASS);
    return mdClassIF.definesAttribute(DISEASE);
  }
  
  public void setDisease(dss.vector.solutions.general.Disease value)
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
  
  public String getEmailBccAddresses()
  {
    return getValue(EMAILBCCADDRESSES);
  }
  
  public void validateEmailBccAddresses()
  {
    this.validateAttribute(EMAILBCCADDRESSES);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getEmailBccAddressesMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.SystemAlert.CLASS);
    return mdClassIF.definesAttribute(EMAILBCCADDRESSES);
  }
  
  public void setEmailBccAddresses(String value)
  {
    if(value == null)
    {
      setValue(EMAILBCCADDRESSES, "");
    }
    else
    {
      setValue(EMAILBCCADDRESSES, value);
    }
  }
  
  public String getEmailBody()
  {
    return getValue(EMAILBODY);
  }
  
  public void validateEmailBody()
  {
    this.validateAttribute(EMAILBODY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getEmailBodyMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.SystemAlert.CLASS);
    return mdClassIF.definesAttribute(EMAILBODY);
  }
  
  public void setEmailBody(String value)
  {
    if(value == null)
    {
      setValue(EMAILBODY, "");
    }
    else
    {
      setValue(EMAILBODY, value);
    }
  }
  
  public String getEmailCcAddresses()
  {
    return getValue(EMAILCCADDRESSES);
  }
  
  public void validateEmailCcAddresses()
  {
    this.validateAttribute(EMAILCCADDRESSES);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getEmailCcAddressesMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.SystemAlert.CLASS);
    return mdClassIF.definesAttribute(EMAILCCADDRESSES);
  }
  
  public void setEmailCcAddresses(String value)
  {
    if(value == null)
    {
      setValue(EMAILCCADDRESSES, "");
    }
    else
    {
      setValue(EMAILCCADDRESSES, value);
    }
  }
  
  public String getEmailFromAddress()
  {
    return getValue(EMAILFROMADDRESS);
  }
  
  public void validateEmailFromAddress()
  {
    this.validateAttribute(EMAILFROMADDRESS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getEmailFromAddressMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.SystemAlert.CLASS);
    return mdClassIF.definesAttribute(EMAILFROMADDRESS);
  }
  
  public void setEmailFromAddress(String value)
  {
    if(value == null)
    {
      setValue(EMAILFROMADDRESS, "");
    }
    else
    {
      setValue(EMAILFROMADDRESS, value);
    }
  }
  
  public String getEmailSubject()
  {
    return getValue(EMAILSUBJECT);
  }
  
  public void validateEmailSubject()
  {
    this.validateAttribute(EMAILSUBJECT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getEmailSubjectMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.SystemAlert.CLASS);
    return mdClassIF.definesAttribute(EMAILSUBJECT);
  }
  
  public void setEmailSubject(String value)
  {
    if(value == null)
    {
      setValue(EMAILSUBJECT, "");
    }
    else
    {
      setValue(EMAILSUBJECT, value);
    }
  }
  
  public String getEmailToAddresses()
  {
    return getValue(EMAILTOADDRESSES);
  }
  
  public void validateEmailToAddresses()
  {
    this.validateAttribute(EMAILTOADDRESSES);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getEmailToAddressesMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.SystemAlert.CLASS);
    return mdClassIF.definesAttribute(EMAILTOADDRESSES);
  }
  
  public void setEmailToAddresses(String value)
  {
    if(value == null)
    {
      setValue(EMAILTOADDRESSES, "");
    }
    else
    {
      setValue(EMAILTOADDRESSES, value);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.SystemAlert.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.SystemAlert.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public Boolean getIsEmailActive()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISEMAILACTIVE));
  }
  
  public void validateIsEmailActive()
  {
    this.validateAttribute(ISEMAILACTIVE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getIsEmailActiveMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.SystemAlert.CLASS);
    return mdClassIF.definesAttribute(ISEMAILACTIVE);
  }
  
  public void setIsEmailActive(Boolean value)
  {
    if(value == null)
    {
      setValue(ISEMAILACTIVE, "");
    }
    else
    {
      setValue(ISEMAILACTIVE, java.lang.Boolean.toString(value));
    }
  }
  
  public Boolean getIsOnscreenActive()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISONSCREENACTIVE));
  }
  
  public void validateIsOnscreenActive()
  {
    this.validateAttribute(ISONSCREENACTIVE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getIsOnscreenActiveMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.SystemAlert.CLASS);
    return mdClassIF.definesAttribute(ISONSCREENACTIVE);
  }
  
  public void setIsOnscreenActive(Boolean value)
  {
    if(value == null)
    {
      setValue(ISONSCREENACTIVE, "");
    }
    else
    {
      setValue(ISONSCREENACTIVE, java.lang.Boolean.toString(value));
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.SystemAlert.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.SystemAlert.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.SystemAlert.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.SystemAlert.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.SystemAlert.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.SystemAlert.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.SystemAlert.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.SystemAlert.CLASS);
    return mdClassIF.definesAttribute(TYPE);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static SystemAlertQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    SystemAlertQuery query = new SystemAlertQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static SystemAlert get(String id)
  {
    return (SystemAlert) com.runwaysdk.business.Business.get(id);
  }
  
  public static SystemAlert getByKey(String key)
  {
    return (SystemAlert) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public static dss.vector.solutions.general.SystemAlertQuery getAllInstancesForDisease(java.lang.String sortAttribute, java.lang.Boolean ascending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.SystemAlert.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static SystemAlert lock(java.lang.String id)
  {
    SystemAlert _instance = SystemAlert.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static SystemAlert unlock(java.lang.String id)
  {
    SystemAlert _instance = SystemAlert.get(id);
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
