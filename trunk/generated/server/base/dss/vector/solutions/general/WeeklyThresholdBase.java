package dss.vector.solutions.general;

@com.terraframe.mojo.business.ClassSignature(hash = 1507784674)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to WeeklyThreshold.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class WeeklyThresholdBase extends com.terraframe.mojo.business.Relationship implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.WeeklyThreshold";
  public static java.lang.String CALCULATIONTYPE = "calculationType";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String FACILITYIDENTIFICATION = "facilityIdentification";
  public static java.lang.String FACILITYNOTIFICATION = "facilityNotification";
  public static java.lang.String ID = "id";
  public static java.lang.String IDENTIFICATION = "identification";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTFACILITYIDENTIFICATION = "lastFacilityIdentification";
  public static java.lang.String LASTFACILITYNOTIFICATION = "lastFacilityNotification";
  public static java.lang.String LASTIDENTIFICATION = "lastIdentification";
  public static java.lang.String LASTNOTIFICATION = "lastNotification";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String NOTIFICATION = "notification";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TYPE = "type";
  private static final long serialVersionUID = 1507784674;
  
  public WeeklyThresholdBase(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public dss.vector.solutions.general.ThresholdCalculationType getCalculationType()
  {
    if (getValue(CALCULATIONTYPE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.general.ThresholdCalculationType.get(getValue(CALCULATIONTYPE));
    }
  }
  
  public void validateCalculationType()
  {
    this.validateAttribute(CALCULATIONTYPE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getCalculationTypeMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
    return mdClassIF.definesAttribute(CALCULATIONTYPE);
  }
  
  public void setCalculationType(dss.vector.solutions.general.ThresholdCalculationType value)
  {
    if(value == null)
    {
      setValue(CALCULATIONTYPE, "");
    }
    else
    {
      setValue(CALCULATIONTYPE, value.getId());
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
    return mdClassIF.definesAttribute(CREATEDBY);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
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
  
  public Integer getFacilityIdentification()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FACILITYIDENTIFICATION));
  }
  
  public void validateFacilityIdentification()
  {
    this.validateAttribute(FACILITYIDENTIFICATION);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getFacilityIdentificationMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
    return mdClassIF.definesAttribute(FACILITYIDENTIFICATION);
  }
  
  public void setFacilityIdentification(Integer value)
  {
    if(value == null)
    {
      setValue(FACILITYIDENTIFICATION, "");
    }
    else
    {
      setValue(FACILITYIDENTIFICATION, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getFacilityNotification()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FACILITYNOTIFICATION));
  }
  
  public void validateFacilityNotification()
  {
    this.validateAttribute(FACILITYNOTIFICATION);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getFacilityNotificationMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
    return mdClassIF.definesAttribute(FACILITYNOTIFICATION);
  }
  
  public void setFacilityNotification(Integer value)
  {
    if(value == null)
    {
      setValue(FACILITYNOTIFICATION, "");
    }
    else
    {
      setValue(FACILITYNOTIFICATION, java.lang.Integer.toString(value));
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public Integer getIdentification()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(IDENTIFICATION));
  }
  
  public void validateIdentification()
  {
    this.validateAttribute(IDENTIFICATION);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIdentificationMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
    return mdClassIF.definesAttribute(IDENTIFICATION);
  }
  
  public void setIdentification(Integer value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION, "");
    }
    else
    {
      setValue(IDENTIFICATION, java.lang.Integer.toString(value));
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
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
  
  public dss.vector.solutions.general.EpiWeek getLastFacilityIdentification()
  {
    if (getValue(LASTFACILITYIDENTIFICATION).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.general.EpiWeek.get(getValue(LASTFACILITYIDENTIFICATION));
    }
  }
  
  public void validateLastFacilityIdentification()
  {
    this.validateAttribute(LASTFACILITYIDENTIFICATION);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getLastFacilityIdentificationMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
    return mdClassIF.definesAttribute(LASTFACILITYIDENTIFICATION);
  }
  
  public void setLastFacilityIdentification(dss.vector.solutions.general.EpiWeek value)
  {
    if(value == null)
    {
      setValue(LASTFACILITYIDENTIFICATION, "");
    }
    else
    {
      setValue(LASTFACILITYIDENTIFICATION, value.getId());
    }
  }
  
  public dss.vector.solutions.general.EpiWeek getLastFacilityNotification()
  {
    if (getValue(LASTFACILITYNOTIFICATION).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.general.EpiWeek.get(getValue(LASTFACILITYNOTIFICATION));
    }
  }
  
  public void validateLastFacilityNotification()
  {
    this.validateAttribute(LASTFACILITYNOTIFICATION);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getLastFacilityNotificationMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
    return mdClassIF.definesAttribute(LASTFACILITYNOTIFICATION);
  }
  
  public void setLastFacilityNotification(dss.vector.solutions.general.EpiWeek value)
  {
    if(value == null)
    {
      setValue(LASTFACILITYNOTIFICATION, "");
    }
    else
    {
      setValue(LASTFACILITYNOTIFICATION, value.getId());
    }
  }
  
  public dss.vector.solutions.general.EpiWeek getLastIdentification()
  {
    if (getValue(LASTIDENTIFICATION).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.general.EpiWeek.get(getValue(LASTIDENTIFICATION));
    }
  }
  
  public void validateLastIdentification()
  {
    this.validateAttribute(LASTIDENTIFICATION);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getLastIdentificationMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
    return mdClassIF.definesAttribute(LASTIDENTIFICATION);
  }
  
  public void setLastIdentification(dss.vector.solutions.general.EpiWeek value)
  {
    if(value == null)
    {
      setValue(LASTIDENTIFICATION, "");
    }
    else
    {
      setValue(LASTIDENTIFICATION, value.getId());
    }
  }
  
  public dss.vector.solutions.general.EpiWeek getLastNotification()
  {
    if (getValue(LASTNOTIFICATION).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.general.EpiWeek.get(getValue(LASTNOTIFICATION));
    }
  }
  
  public void validateLastNotification()
  {
    this.validateAttribute(LASTNOTIFICATION);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getLastNotificationMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
    return mdClassIF.definesAttribute(LASTNOTIFICATION);
  }
  
  public void setLastNotification(dss.vector.solutions.general.EpiWeek value)
  {
    if(value == null)
    {
      setValue(LASTNOTIFICATION, "");
    }
    else
    {
      setValue(LASTNOTIFICATION, value.getId());
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
    return mdClassIF.definesAttribute(LOCKEDBY);
  }
  
  public Integer getNotification()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NOTIFICATION));
  }
  
  public void validateNotification()
  {
    this.validateAttribute(NOTIFICATION);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getNotificationMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
    return mdClassIF.definesAttribute(NOTIFICATION);
  }
  
  public void setNotification(Integer value)
  {
    if(value == null)
    {
      setValue(NOTIFICATION, "");
    }
    else
    {
      setValue(NOTIFICATION, java.lang.Integer.toString(value));
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
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
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSiteMasterMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
    return mdClassIF.definesAttribute(TYPE);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static WeeklyThresholdQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    WeeklyThresholdQuery query = new WeeklyThresholdQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public dss.vector.solutions.general.ThresholdData getParent()
  {
    return (dss.vector.solutions.general.ThresholdData) super.getParent();
  }
  
  public dss.vector.solutions.general.EpiWeek getChild()
  {
    return (dss.vector.solutions.general.EpiWeek) super.getChild();
  }
  
  public static WeeklyThreshold get(String id)
  {
    return (WeeklyThreshold) com.terraframe.mojo.business.Relationship.get(id);
  }
  
  public static WeeklyThreshold getByKey(String key)
  {
    return (WeeklyThreshold) com.terraframe.mojo.business.Relationship.get(CLASS, key);
  }
  
  public static WeeklyThreshold lock(java.lang.String id)
  {
    WeeklyThreshold _instance = WeeklyThreshold.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static WeeklyThreshold unlock(java.lang.String id)
  {
    WeeklyThreshold _instance = WeeklyThreshold.get(id);
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
