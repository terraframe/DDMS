package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = 112905355)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to WeeklyThreshold.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class WeeklyThresholdBase extends com.runwaysdk.business.Relationship implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.WeeklyThreshold";
  public static java.lang.String ACTUALFACILITYIDENTIFICATION = "actualFacilityIdentification";
  public static java.lang.String ACTUALFACILITYNOTIFICATION = "actualFacilityNotification";
  public static java.lang.String ACTUALIDENTIFICATION = "actualIdentification";
  public static java.lang.String ACTUALNOTIFICATION = "actualNotification";
  public static java.lang.String CALCULATIONTYPE = "calculationType";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String FACILITYIDENTIFICATION = "facilityIdentification";
  public static java.lang.String FACILITYNOTIFICATION = "facilityNotification";
  public static java.lang.String FIRSTFACILITYIDENTIFICATION = "firstFacilityIdentification";
  public static java.lang.String FIRSTFACILITYNOTIFICATION = "firstFacilityNotification";
  public static java.lang.String FIRSTIDENTIFICATION = "firstIdentification";
  public static java.lang.String FIRSTNOTIFICATION = "firstNotification";
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
  private static final long serialVersionUID = 112905355;
  
  public WeeklyThresholdBase(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public Double getActualFacilityIdentification()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(ACTUALFACILITYIDENTIFICATION));
  }
  
  public void validateActualFacilityIdentification()
  {
    this.validateAttribute(ACTUALFACILITYIDENTIFICATION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF getActualFacilityIdentificationMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF)mdClassIF.definesAttribute(ACTUALFACILITYIDENTIFICATION);
  }
  
  public void setActualFacilityIdentification(Double value)
  {
    if(value == null)
    {
      setValue(ACTUALFACILITYIDENTIFICATION, "");
    }
    else
    {
      setValue(ACTUALFACILITYIDENTIFICATION, java.lang.Double.toString(value));
    }
  }
  
  public Double getActualFacilityNotification()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(ACTUALFACILITYNOTIFICATION));
  }
  
  public void validateActualFacilityNotification()
  {
    this.validateAttribute(ACTUALFACILITYNOTIFICATION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF getActualFacilityNotificationMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF)mdClassIF.definesAttribute(ACTUALFACILITYNOTIFICATION);
  }
  
  public void setActualFacilityNotification(Double value)
  {
    if(value == null)
    {
      setValue(ACTUALFACILITYNOTIFICATION, "");
    }
    else
    {
      setValue(ACTUALFACILITYNOTIFICATION, java.lang.Double.toString(value));
    }
  }
  
  public Double getActualIdentification()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(ACTUALIDENTIFICATION));
  }
  
  public void validateActualIdentification()
  {
    this.validateAttribute(ACTUALIDENTIFICATION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF getActualIdentificationMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF)mdClassIF.definesAttribute(ACTUALIDENTIFICATION);
  }
  
  public void setActualIdentification(Double value)
  {
    if(value == null)
    {
      setValue(ACTUALIDENTIFICATION, "");
    }
    else
    {
      setValue(ACTUALIDENTIFICATION, java.lang.Double.toString(value));
    }
  }
  
  public Double getActualNotification()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(ACTUALNOTIFICATION));
  }
  
  public void validateActualNotification()
  {
    this.validateAttribute(ACTUALNOTIFICATION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF getActualNotificationMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF)mdClassIF.definesAttribute(ACTUALNOTIFICATION);
  }
  
  public void setActualNotification(Double value)
  {
    if(value == null)
    {
      setValue(ACTUALNOTIFICATION, "");
    }
    else
    {
      setValue(ACTUALNOTIFICATION, java.lang.Double.toString(value));
    }
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
  
  public String getCalculationTypeId()
  {
    return getValue(CALCULATIONTYPE);
  }
  
  public void validateCalculationType()
  {
    this.validateAttribute(CALCULATIONTYPE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getCalculationTypeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(CALCULATIONTYPE);
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
    return com.runwaysdk.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(CREATEDATE));
  }
  
  public void validateCreateDate()
  {
    this.validateAttribute(CREATEDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDateTimeDAOIF getCreateDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(CREATEDBY);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
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
  
  public Double getFacilityIdentification()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(FACILITYIDENTIFICATION));
  }
  
  public void validateFacilityIdentification()
  {
    this.validateAttribute(FACILITYIDENTIFICATION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF getFacilityIdentificationMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF)mdClassIF.definesAttribute(FACILITYIDENTIFICATION);
  }
  
  public void setFacilityIdentification(Double value)
  {
    if(value == null)
    {
      setValue(FACILITYIDENTIFICATION, "");
    }
    else
    {
      setValue(FACILITYIDENTIFICATION, java.lang.Double.toString(value));
    }
  }
  
  public Double getFacilityNotification()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(FACILITYNOTIFICATION));
  }
  
  public void validateFacilityNotification()
  {
    this.validateAttribute(FACILITYNOTIFICATION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF getFacilityNotificationMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF)mdClassIF.definesAttribute(FACILITYNOTIFICATION);
  }
  
  public void setFacilityNotification(Double value)
  {
    if(value == null)
    {
      setValue(FACILITYNOTIFICATION, "");
    }
    else
    {
      setValue(FACILITYNOTIFICATION, java.lang.Double.toString(value));
    }
  }
  
  public java.util.Date getFirstFacilityIdentification()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(FIRSTFACILITYIDENTIFICATION));
  }
  
  public void validateFirstFacilityIdentification()
  {
    this.validateAttribute(FIRSTFACILITYIDENTIFICATION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDateDAOIF getFirstFacilityIdentificationMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDateDAOIF)mdClassIF.definesAttribute(FIRSTFACILITYIDENTIFICATION);
  }
  
  public void setFirstFacilityIdentification(java.util.Date value)
  {
    if(value == null)
    {
      setValue(FIRSTFACILITYIDENTIFICATION, "");
    }
    else
    {
      setValue(FIRSTFACILITYIDENTIFICATION, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public java.util.Date getFirstFacilityNotification()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(FIRSTFACILITYNOTIFICATION));
  }
  
  public void validateFirstFacilityNotification()
  {
    this.validateAttribute(FIRSTFACILITYNOTIFICATION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDateDAOIF getFirstFacilityNotificationMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDateDAOIF)mdClassIF.definesAttribute(FIRSTFACILITYNOTIFICATION);
  }
  
  public void setFirstFacilityNotification(java.util.Date value)
  {
    if(value == null)
    {
      setValue(FIRSTFACILITYNOTIFICATION, "");
    }
    else
    {
      setValue(FIRSTFACILITYNOTIFICATION, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public java.util.Date getFirstIdentification()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(FIRSTIDENTIFICATION));
  }
  
  public void validateFirstIdentification()
  {
    this.validateAttribute(FIRSTIDENTIFICATION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDateDAOIF getFirstIdentificationMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDateDAOIF)mdClassIF.definesAttribute(FIRSTIDENTIFICATION);
  }
  
  public void setFirstIdentification(java.util.Date value)
  {
    if(value == null)
    {
      setValue(FIRSTIDENTIFICATION, "");
    }
    else
    {
      setValue(FIRSTIDENTIFICATION, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public java.util.Date getFirstNotification()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(FIRSTNOTIFICATION));
  }
  
  public void validateFirstNotification()
  {
    this.validateAttribute(FIRSTNOTIFICATION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDateDAOIF getFirstNotificationMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDateDAOIF)mdClassIF.definesAttribute(FIRSTNOTIFICATION);
  }
  
  public void setFirstNotification(java.util.Date value)
  {
    if(value == null)
    {
      setValue(FIRSTNOTIFICATION, "");
    }
    else
    {
      setValue(FIRSTNOTIFICATION, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  public Double getIdentification()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION));
  }
  
  public void validateIdentification()
  {
    this.validateAttribute(IDENTIFICATION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF getIdentificationMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF)mdClassIF.definesAttribute(IDENTIFICATION);
  }
  
  public void setIdentification(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATION, "");
    }
    else
    {
      setValue(IDENTIFICATION, java.lang.Double.toString(value));
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
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
  
  public String getLastFacilityIdentificationId()
  {
    return getValue(LASTFACILITYIDENTIFICATION);
  }
  
  public void validateLastFacilityIdentification()
  {
    this.validateAttribute(LASTFACILITYIDENTIFICATION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getLastFacilityIdentificationMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(LASTFACILITYIDENTIFICATION);
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
  
  public String getLastFacilityNotificationId()
  {
    return getValue(LASTFACILITYNOTIFICATION);
  }
  
  public void validateLastFacilityNotification()
  {
    this.validateAttribute(LASTFACILITYNOTIFICATION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getLastFacilityNotificationMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(LASTFACILITYNOTIFICATION);
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
  
  public String getLastIdentificationId()
  {
    return getValue(LASTIDENTIFICATION);
  }
  
  public void validateLastIdentification()
  {
    this.validateAttribute(LASTIDENTIFICATION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getLastIdentificationMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(LASTIDENTIFICATION);
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
  
  public String getLastNotificationId()
  {
    return getValue(LASTNOTIFICATION);
  }
  
  public void validateLastNotification()
  {
    this.validateAttribute(LASTNOTIFICATION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getLastNotificationMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(LASTNOTIFICATION);
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
    return com.runwaysdk.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(LASTUPDATEDATE));
  }
  
  public void validateLastUpdateDate()
  {
    this.validateAttribute(LASTUPDATEDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDateTimeDAOIF getLastUpdateDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(LOCKEDBY);
  }
  
  public Double getNotification()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(NOTIFICATION));
  }
  
  public void validateNotification()
  {
    this.validateAttribute(NOTIFICATION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF getNotificationMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF)mdClassIF.definesAttribute(NOTIFICATION);
  }
  
  public void setNotification(Double value)
  {
    if(value == null)
    {
      setValue(NOTIFICATION, "");
    }
    else
    {
      setValue(NOTIFICATION, java.lang.Double.toString(value));
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeLongDAOIF)mdClassIF.definesAttribute(SEQ);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.WeeklyThreshold.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(TYPE);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static WeeklyThresholdQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    WeeklyThresholdQuery query = new WeeklyThresholdQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
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
    return (WeeklyThreshold) com.runwaysdk.business.Relationship.get(id);
  }
  
  public static WeeklyThreshold getByKey(String key)
  {
    return (WeeklyThreshold) com.runwaysdk.business.Relationship.get(CLASS, key);
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
