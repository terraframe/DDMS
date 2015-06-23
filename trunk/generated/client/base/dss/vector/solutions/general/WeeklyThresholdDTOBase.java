package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = -190271546)
public abstract class WeeklyThresholdDTOBase extends com.runwaysdk.business.RelationshipDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.WeeklyThreshold";
  private static final long serialVersionUID = -190271546;
  
  public WeeklyThresholdDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String parentId, java.lang.String childId)
  {
    super(clientRequest, parentId, childId);
    
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given RelationshipDTO into a new DTO.
  * 
  * @param relationshipDTO The RelationshipDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected WeeklyThresholdDTOBase(com.runwaysdk.business.RelationshipDTO relationshipDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(relationshipDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
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
  public Double getActualFacilityIdentification()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(ACTUALFACILITYIDENTIFICATION));
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
  
  public boolean isActualFacilityIdentificationWritable()
  {
    return isWritable(ACTUALFACILITYIDENTIFICATION);
  }
  
  public boolean isActualFacilityIdentificationReadable()
  {
    return isReadable(ACTUALFACILITYIDENTIFICATION);
  }
  
  public boolean isActualFacilityIdentificationModified()
  {
    return isModified(ACTUALFACILITYIDENTIFICATION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getActualFacilityIdentificationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(ACTUALFACILITYIDENTIFICATION).getAttributeMdDTO();
  }
  
  public Double getActualFacilityNotification()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(ACTUALFACILITYNOTIFICATION));
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
  
  public boolean isActualFacilityNotificationWritable()
  {
    return isWritable(ACTUALFACILITYNOTIFICATION);
  }
  
  public boolean isActualFacilityNotificationReadable()
  {
    return isReadable(ACTUALFACILITYNOTIFICATION);
  }
  
  public boolean isActualFacilityNotificationModified()
  {
    return isModified(ACTUALFACILITYNOTIFICATION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getActualFacilityNotificationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(ACTUALFACILITYNOTIFICATION).getAttributeMdDTO();
  }
  
  public Double getActualIdentification()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(ACTUALIDENTIFICATION));
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
  
  public boolean isActualIdentificationWritable()
  {
    return isWritable(ACTUALIDENTIFICATION);
  }
  
  public boolean isActualIdentificationReadable()
  {
    return isReadable(ACTUALIDENTIFICATION);
  }
  
  public boolean isActualIdentificationModified()
  {
    return isModified(ACTUALIDENTIFICATION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getActualIdentificationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(ACTUALIDENTIFICATION).getAttributeMdDTO();
  }
  
  public Double getActualNotification()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(ACTUALNOTIFICATION));
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
  
  public boolean isActualNotificationWritable()
  {
    return isWritable(ACTUALNOTIFICATION);
  }
  
  public boolean isActualNotificationReadable()
  {
    return isReadable(ACTUALNOTIFICATION);
  }
  
  public boolean isActualNotificationModified()
  {
    return isModified(ACTUALNOTIFICATION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getActualNotificationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(ACTUALNOTIFICATION).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.general.ThresholdCalculationTypeDTO getCalculationType()
  {
    if(getValue(CALCULATIONTYPE) == null || getValue(CALCULATIONTYPE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.general.ThresholdCalculationTypeDTO.get(getRequest(), getValue(CALCULATIONTYPE));
    }
  }
  
  public String getCalculationTypeId()
  {
    return getValue(CALCULATIONTYPE);
  }
  
  public void setCalculationType(dss.vector.solutions.general.ThresholdCalculationTypeDTO value)
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
  
  public boolean isCalculationTypeWritable()
  {
    return isWritable(CALCULATIONTYPE);
  }
  
  public boolean isCalculationTypeReadable()
  {
    return isReadable(CALCULATIONTYPE);
  }
  
  public boolean isCalculationTypeModified()
  {
    return isModified(CALCULATIONTYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getCalculationTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(CALCULATIONTYPE).getAttributeMdDTO();
  }
  
  public java.util.Date getCreateDate()
  {
    return com.runwaysdk.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(CREATEDATE));
  }
  
  public boolean isCreateDateWritable()
  {
    return isWritable(CREATEDATE);
  }
  
  public boolean isCreateDateReadable()
  {
    return isReadable(CREATEDATE);
  }
  
  public boolean isCreateDateModified()
  {
    return isModified(CREATEDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateTimeMdDTO getCreateDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateTimeMdDTO) getAttributeDTO(CREATEDATE).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.SingleActorDTO getCreatedBy()
  {
    if(getValue(CREATEDBY) == null || getValue(CREATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.SingleActorDTO.get(getRequest(), getValue(CREATEDBY));
    }
  }
  
  public String getCreatedById()
  {
    return getValue(CREATEDBY);
  }
  
  public boolean isCreatedByWritable()
  {
    return isWritable(CREATEDBY);
  }
  
  public boolean isCreatedByReadable()
  {
    return isReadable(CREATEDBY);
  }
  
  public boolean isCreatedByModified()
  {
    return isModified(CREATEDBY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getCreatedByMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(CREATEDBY).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.metadata.MdDomainDTO getEntityDomain()
  {
    if(getValue(ENTITYDOMAIN) == null || getValue(ENTITYDOMAIN).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.metadata.MdDomainDTO.get(getRequest(), getValue(ENTITYDOMAIN));
    }
  }
  
  public String getEntityDomainId()
  {
    return getValue(ENTITYDOMAIN);
  }
  
  public void setEntityDomain(com.runwaysdk.system.metadata.MdDomainDTO value)
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
  
  public boolean isEntityDomainWritable()
  {
    return isWritable(ENTITYDOMAIN);
  }
  
  public boolean isEntityDomainReadable()
  {
    return isReadable(ENTITYDOMAIN);
  }
  
  public boolean isEntityDomainModified()
  {
    return isModified(ENTITYDOMAIN);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getEntityDomainMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ENTITYDOMAIN).getAttributeMdDTO();
  }
  
  public Double getFacilityIdentification()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(FACILITYIDENTIFICATION));
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
  
  public boolean isFacilityIdentificationWritable()
  {
    return isWritable(FACILITYIDENTIFICATION);
  }
  
  public boolean isFacilityIdentificationReadable()
  {
    return isReadable(FACILITYIDENTIFICATION);
  }
  
  public boolean isFacilityIdentificationModified()
  {
    return isModified(FACILITYIDENTIFICATION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getFacilityIdentificationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(FACILITYIDENTIFICATION).getAttributeMdDTO();
  }
  
  public Double getFacilityNotification()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(FACILITYNOTIFICATION));
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
  
  public boolean isFacilityNotificationWritable()
  {
    return isWritable(FACILITYNOTIFICATION);
  }
  
  public boolean isFacilityNotificationReadable()
  {
    return isReadable(FACILITYNOTIFICATION);
  }
  
  public boolean isFacilityNotificationModified()
  {
    return isModified(FACILITYNOTIFICATION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getFacilityNotificationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(FACILITYNOTIFICATION).getAttributeMdDTO();
  }
  
  public java.util.Date getFirstFacilityIdentification()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(FIRSTFACILITYIDENTIFICATION));
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
  
  public boolean isFirstFacilityIdentificationWritable()
  {
    return isWritable(FIRSTFACILITYIDENTIFICATION);
  }
  
  public boolean isFirstFacilityIdentificationReadable()
  {
    return isReadable(FIRSTFACILITYIDENTIFICATION);
  }
  
  public boolean isFirstFacilityIdentificationModified()
  {
    return isModified(FIRSTFACILITYIDENTIFICATION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getFirstFacilityIdentificationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(FIRSTFACILITYIDENTIFICATION).getAttributeMdDTO();
  }
  
  public java.util.Date getFirstFacilityNotification()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(FIRSTFACILITYNOTIFICATION));
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
  
  public boolean isFirstFacilityNotificationWritable()
  {
    return isWritable(FIRSTFACILITYNOTIFICATION);
  }
  
  public boolean isFirstFacilityNotificationReadable()
  {
    return isReadable(FIRSTFACILITYNOTIFICATION);
  }
  
  public boolean isFirstFacilityNotificationModified()
  {
    return isModified(FIRSTFACILITYNOTIFICATION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getFirstFacilityNotificationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(FIRSTFACILITYNOTIFICATION).getAttributeMdDTO();
  }
  
  public java.util.Date getFirstIdentification()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(FIRSTIDENTIFICATION));
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
  
  public boolean isFirstIdentificationWritable()
  {
    return isWritable(FIRSTIDENTIFICATION);
  }
  
  public boolean isFirstIdentificationReadable()
  {
    return isReadable(FIRSTIDENTIFICATION);
  }
  
  public boolean isFirstIdentificationModified()
  {
    return isModified(FIRSTIDENTIFICATION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getFirstIdentificationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(FIRSTIDENTIFICATION).getAttributeMdDTO();
  }
  
  public java.util.Date getFirstNotification()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(FIRSTNOTIFICATION));
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
  
  public boolean isFirstNotificationWritable()
  {
    return isWritable(FIRSTNOTIFICATION);
  }
  
  public boolean isFirstNotificationReadable()
  {
    return isReadable(FIRSTNOTIFICATION);
  }
  
  public boolean isFirstNotificationModified()
  {
    return isModified(FIRSTNOTIFICATION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getFirstNotificationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(FIRSTNOTIFICATION).getAttributeMdDTO();
  }
  
  public Double getIdentification()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATION));
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
  
  public boolean isIdentificationWritable()
  {
    return isWritable(IDENTIFICATION);
  }
  
  public boolean isIdentificationReadable()
  {
    return isReadable(IDENTIFICATION);
  }
  
  public boolean isIdentificationModified()
  {
    return isModified(IDENTIFICATION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getIdentificationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(IDENTIFICATION).getAttributeMdDTO();
  }
  
  public String getKeyName()
  {
    return getValue(KEYNAME);
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
  
  public boolean isKeyNameWritable()
  {
    return isWritable(KEYNAME);
  }
  
  public boolean isKeyNameReadable()
  {
    return isReadable(KEYNAME);
  }
  
  public boolean isKeyNameModified()
  {
    return isModified(KEYNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getKeyNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(KEYNAME).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.general.EpiWeekDTO getLastFacilityIdentification()
  {
    if(getValue(LASTFACILITYIDENTIFICATION) == null || getValue(LASTFACILITYIDENTIFICATION).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.general.EpiWeekDTO.get(getRequest(), getValue(LASTFACILITYIDENTIFICATION));
    }
  }
  
  public String getLastFacilityIdentificationId()
  {
    return getValue(LASTFACILITYIDENTIFICATION);
  }
  
  public void setLastFacilityIdentification(dss.vector.solutions.general.EpiWeekDTO value)
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
  
  public boolean isLastFacilityIdentificationWritable()
  {
    return isWritable(LASTFACILITYIDENTIFICATION);
  }
  
  public boolean isLastFacilityIdentificationReadable()
  {
    return isReadable(LASTFACILITYIDENTIFICATION);
  }
  
  public boolean isLastFacilityIdentificationModified()
  {
    return isModified(LASTFACILITYIDENTIFICATION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getLastFacilityIdentificationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(LASTFACILITYIDENTIFICATION).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.general.EpiWeekDTO getLastFacilityNotification()
  {
    if(getValue(LASTFACILITYNOTIFICATION) == null || getValue(LASTFACILITYNOTIFICATION).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.general.EpiWeekDTO.get(getRequest(), getValue(LASTFACILITYNOTIFICATION));
    }
  }
  
  public String getLastFacilityNotificationId()
  {
    return getValue(LASTFACILITYNOTIFICATION);
  }
  
  public void setLastFacilityNotification(dss.vector.solutions.general.EpiWeekDTO value)
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
  
  public boolean isLastFacilityNotificationWritable()
  {
    return isWritable(LASTFACILITYNOTIFICATION);
  }
  
  public boolean isLastFacilityNotificationReadable()
  {
    return isReadable(LASTFACILITYNOTIFICATION);
  }
  
  public boolean isLastFacilityNotificationModified()
  {
    return isModified(LASTFACILITYNOTIFICATION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getLastFacilityNotificationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(LASTFACILITYNOTIFICATION).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.general.EpiWeekDTO getLastIdentification()
  {
    if(getValue(LASTIDENTIFICATION) == null || getValue(LASTIDENTIFICATION).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.general.EpiWeekDTO.get(getRequest(), getValue(LASTIDENTIFICATION));
    }
  }
  
  public String getLastIdentificationId()
  {
    return getValue(LASTIDENTIFICATION);
  }
  
  public void setLastIdentification(dss.vector.solutions.general.EpiWeekDTO value)
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
  
  public boolean isLastIdentificationWritable()
  {
    return isWritable(LASTIDENTIFICATION);
  }
  
  public boolean isLastIdentificationReadable()
  {
    return isReadable(LASTIDENTIFICATION);
  }
  
  public boolean isLastIdentificationModified()
  {
    return isModified(LASTIDENTIFICATION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getLastIdentificationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(LASTIDENTIFICATION).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.general.EpiWeekDTO getLastNotification()
  {
    if(getValue(LASTNOTIFICATION) == null || getValue(LASTNOTIFICATION).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.general.EpiWeekDTO.get(getRequest(), getValue(LASTNOTIFICATION));
    }
  }
  
  public String getLastNotificationId()
  {
    return getValue(LASTNOTIFICATION);
  }
  
  public void setLastNotification(dss.vector.solutions.general.EpiWeekDTO value)
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
  
  public boolean isLastNotificationWritable()
  {
    return isWritable(LASTNOTIFICATION);
  }
  
  public boolean isLastNotificationReadable()
  {
    return isReadable(LASTNOTIFICATION);
  }
  
  public boolean isLastNotificationModified()
  {
    return isModified(LASTNOTIFICATION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getLastNotificationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(LASTNOTIFICATION).getAttributeMdDTO();
  }
  
  public java.util.Date getLastUpdateDate()
  {
    return com.runwaysdk.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(LASTUPDATEDATE));
  }
  
  public boolean isLastUpdateDateWritable()
  {
    return isWritable(LASTUPDATEDATE);
  }
  
  public boolean isLastUpdateDateReadable()
  {
    return isReadable(LASTUPDATEDATE);
  }
  
  public boolean isLastUpdateDateModified()
  {
    return isModified(LASTUPDATEDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateTimeMdDTO getLastUpdateDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateTimeMdDTO) getAttributeDTO(LASTUPDATEDATE).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.SingleActorDTO getLastUpdatedBy()
  {
    if(getValue(LASTUPDATEDBY) == null || getValue(LASTUPDATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.SingleActorDTO.get(getRequest(), getValue(LASTUPDATEDBY));
    }
  }
  
  public String getLastUpdatedById()
  {
    return getValue(LASTUPDATEDBY);
  }
  
  public boolean isLastUpdatedByWritable()
  {
    return isWritable(LASTUPDATEDBY);
  }
  
  public boolean isLastUpdatedByReadable()
  {
    return isReadable(LASTUPDATEDBY);
  }
  
  public boolean isLastUpdatedByModified()
  {
    return isModified(LASTUPDATEDBY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getLastUpdatedByMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(LASTUPDATEDBY).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.UsersDTO getLockedBy()
  {
    if(getValue(LOCKEDBY) == null || getValue(LOCKEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.UsersDTO.get(getRequest(), getValue(LOCKEDBY));
    }
  }
  
  public String getLockedById()
  {
    return getValue(LOCKEDBY);
  }
  
  public boolean isLockedByWritable()
  {
    return isWritable(LOCKEDBY);
  }
  
  public boolean isLockedByReadable()
  {
    return isReadable(LOCKEDBY);
  }
  
  public boolean isLockedByModified()
  {
    return isModified(LOCKEDBY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getLockedByMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(LOCKEDBY).getAttributeMdDTO();
  }
  
  public Double getNotification()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(NOTIFICATION));
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
  
  public boolean isNotificationWritable()
  {
    return isWritable(NOTIFICATION);
  }
  
  public boolean isNotificationReadable()
  {
    return isReadable(NOTIFICATION);
  }
  
  public boolean isNotificationModified()
  {
    return isModified(NOTIFICATION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getNotificationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(NOTIFICATION).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.ActorDTO getOwner()
  {
    if(getValue(OWNER) == null || getValue(OWNER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.ActorDTO.get(getRequest(), getValue(OWNER));
    }
  }
  
  public String getOwnerId()
  {
    return getValue(OWNER);
  }
  
  public void setOwner(com.runwaysdk.system.ActorDTO value)
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
  
  public boolean isOwnerWritable()
  {
    return isWritable(OWNER);
  }
  
  public boolean isOwnerReadable()
  {
    return isReadable(OWNER);
  }
  
  public boolean isOwnerModified()
  {
    return isModified(OWNER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getOwnerMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(OWNER).getAttributeMdDTO();
  }
  
  public Long getSeq()
  {
    return com.runwaysdk.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(SEQ));
  }
  
  public boolean isSeqWritable()
  {
    return isWritable(SEQ);
  }
  
  public boolean isSeqReadable()
  {
    return isReadable(SEQ);
  }
  
  public boolean isSeqModified()
  {
    return isModified(SEQ);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getSeqMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SEQ).getAttributeMdDTO();
  }
  
  public String getSiteMaster()
  {
    return getValue(SITEMASTER);
  }
  
  public boolean isSiteMasterWritable()
  {
    return isWritable(SITEMASTER);
  }
  
  public boolean isSiteMasterReadable()
  {
    return isReadable(SITEMASTER);
  }
  
  public boolean isSiteMasterModified()
  {
    return isModified(SITEMASTER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSiteMasterMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SITEMASTER).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.general.ThresholdDataDTO getParent()
  {
    return dss.vector.solutions.general.ThresholdDataDTO.get(getRequest(), super.getParentId());
  }
  
    public dss.vector.solutions.general.EpiWeekDTO getChild()
  {
    return dss.vector.solutions.general.EpiWeekDTO.get(getRequest(), super.getChildId());
  }
  
  public static dss.vector.solutions.general.WeeklyThresholdDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.RelationshipDTO dto = (com.runwaysdk.business.RelationshipDTO) clientRequest.get(id);
    
    return (dss.vector.solutions.general.WeeklyThresholdDTO) dto;
  }
  
  public static dss.vector.solutions.general.WeeklyThresholdQueryDTO parentQuery(com.runwaysdk.constants.ClientRequestIF clientRequest, String parentId)
  {
    com.runwaysdk.business.RelationshipQueryDTO queryDTO = (com.runwaysdk.business.RelationshipQueryDTO) clientRequest.getQuery(dss.vector.solutions.general.WeeklyThresholdDTO.CLASS);
    queryDTO.addCondition("parent_id", "EQ", parentId);
    return (dss.vector.solutions.general.WeeklyThresholdQueryDTO) clientRequest.queryRelationships(queryDTO);
  }
  public static dss.vector.solutions.general.WeeklyThresholdQueryDTO childQuery(com.runwaysdk.constants.ClientRequestIF clientRequest, String childId)
  {
    com.runwaysdk.business.RelationshipQueryDTO queryDTO = (com.runwaysdk.business.RelationshipQueryDTO) clientRequest.getQuery(dss.vector.solutions.general.WeeklyThresholdDTO.CLASS);
    queryDTO.addCondition("child_id", "EQ", childId);
    return (dss.vector.solutions.general.WeeklyThresholdQueryDTO) clientRequest.queryRelationships(queryDTO);
  }
  public void apply()
  {
    if(isNewInstance())
    {
      getRequest().createRelationship(this);
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
  
  public static dss.vector.solutions.general.WeeklyThresholdQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.general.WeeklyThresholdQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.general.WeeklyThresholdDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.general.WeeklyThresholdDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.WeeklyThresholdDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.general.WeeklyThresholdDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.general.WeeklyThresholdDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.WeeklyThresholdDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.general.WeeklyThresholdDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
