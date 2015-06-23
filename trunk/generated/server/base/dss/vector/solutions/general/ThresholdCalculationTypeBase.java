package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = 398189443)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ThresholdCalculationType.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class ThresholdCalculationTypeBase extends com.runwaysdk.business.Business implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.ThresholdCalculationType";
  public static java.lang.String CASETYPES = "caseTypes";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DISEASE = "disease";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String ID = "id";
  public static java.lang.String IDENTIFICATIONMINIMUM = "identificationMinimum";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String NOTIFICATIONMINIMUM = "notificationMinimum";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String PRIORYEARS = "priorYears";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String T1METHOD = "t1Method";
  public static java.lang.String T2METHOD = "t2Method";
  public static java.lang.String TYPE = "type";
  public static java.lang.String WEEKSAFTER = "weeksAfter";
  public static java.lang.String WEEKSBEFORE = "weeksBefore";
  public static java.lang.String WEIGHT0 = "weight0";
  public static java.lang.String WEIGHT1 = "weight1";
  public static java.lang.String WEIGHT2 = "weight2";
  public static java.lang.String WEIGHT3 = "weight3";
  public static java.lang.String WEIGHT4 = "weight4";
  public static java.lang.String WEIGHT5 = "weight5";
  public static java.lang.String WEIGHT6 = "weight6";
  public static java.lang.String WEIGHT7 = "weight7";
  public static java.lang.String WEIGHT8 = "weight8";
  public static java.lang.String WEIGHT9 = "weight9";
  private static final long serialVersionUID = 398189443;
  
  public ThresholdCalculationTypeBase()
  {
    super();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.general.ThresholdCalculationCaseTypes> getCaseTypes()
  {
    return (java.util.List<dss.vector.solutions.general.ThresholdCalculationCaseTypes>) getEnumValues(CASETYPES);
  }
  
  public void addCaseTypes(dss.vector.solutions.general.ThresholdCalculationCaseTypes value)
  {
    if(value != null)
    {
      addEnumItem(CASETYPES, value.getId());
    }
  }
  
  public void removeCaseTypes(dss.vector.solutions.general.ThresholdCalculationCaseTypes value)
  {
    if(value != null)
    {
      removeEnumItem(CASETYPES, value.getId());
    }
  }
  
  public void clearCaseTypes()
  {
    clearEnum(CASETYPES);
  }
  
  public void validateCaseTypes()
  {
    this.validateAttribute(CASETYPES);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeEnumerationDAOIF getCaseTypesMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationType.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeEnumerationDAOIF)mdClassIF.definesAttribute(CASETYPES);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationType.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationType.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(CREATEDBY);
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
  
  public String getDiseaseId()
  {
    return getValue(DISEASE);
  }
  
  public void validateDisease()
  {
    this.validateAttribute(DISEASE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getDiseaseMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationType.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(DISEASE);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationType.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationType.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  public Double getIdentificationMinimum()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(IDENTIFICATIONMINIMUM));
  }
  
  public void validateIdentificationMinimum()
  {
    this.validateAttribute(IDENTIFICATIONMINIMUM);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF getIdentificationMinimumMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationType.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF)mdClassIF.definesAttribute(IDENTIFICATIONMINIMUM);
  }
  
  public void setIdentificationMinimum(Double value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATIONMINIMUM, "");
    }
    else
    {
      setValue(IDENTIFICATIONMINIMUM, java.lang.Double.toString(value));
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationType.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationType.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationType.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(LASTUPDATEDBY);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationType.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(LOCKEDBY);
  }
  
  public Double getNotificationMinimum()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(NOTIFICATIONMINIMUM));
  }
  
  public void validateNotificationMinimum()
  {
    this.validateAttribute(NOTIFICATIONMINIMUM);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF getNotificationMinimumMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationType.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF)mdClassIF.definesAttribute(NOTIFICATIONMINIMUM);
  }
  
  public void setNotificationMinimum(Double value)
  {
    if(value == null)
    {
      setValue(NOTIFICATIONMINIMUM, "");
    }
    else
    {
      setValue(NOTIFICATIONMINIMUM, java.lang.Double.toString(value));
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationType.CLASS);
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
  
  public Integer getPriorYears()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PRIORYEARS));
  }
  
  public void validatePriorYears()
  {
    this.validateAttribute(PRIORYEARS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getPriorYearsMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationType.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(PRIORYEARS);
  }
  
  public void setPriorYears(Integer value)
  {
    if(value == null)
    {
      setValue(PRIORYEARS, "");
    }
    else
    {
      setValue(PRIORYEARS, java.lang.Integer.toString(value));
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationType.CLASS);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationType.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(SITEMASTER);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.general.ThresholdCalculationMethod> getT1Method()
  {
    return (java.util.List<dss.vector.solutions.general.ThresholdCalculationMethod>) getEnumValues(T1METHOD);
  }
  
  public void addT1Method(dss.vector.solutions.general.ThresholdCalculationMethod value)
  {
    if(value != null)
    {
      addEnumItem(T1METHOD, value.getId());
    }
  }
  
  public void removeT1Method(dss.vector.solutions.general.ThresholdCalculationMethod value)
  {
    if(value != null)
    {
      removeEnumItem(T1METHOD, value.getId());
    }
  }
  
  public void clearT1Method()
  {
    clearEnum(T1METHOD);
  }
  
  public void validateT1Method()
  {
    this.validateAttribute(T1METHOD);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeEnumerationDAOIF getT1MethodMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationType.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeEnumerationDAOIF)mdClassIF.definesAttribute(T1METHOD);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.general.ThresholdCalculationMethod> getT2Method()
  {
    return (java.util.List<dss.vector.solutions.general.ThresholdCalculationMethod>) getEnumValues(T2METHOD);
  }
  
  public void addT2Method(dss.vector.solutions.general.ThresholdCalculationMethod value)
  {
    if(value != null)
    {
      addEnumItem(T2METHOD, value.getId());
    }
  }
  
  public void removeT2Method(dss.vector.solutions.general.ThresholdCalculationMethod value)
  {
    if(value != null)
    {
      removeEnumItem(T2METHOD, value.getId());
    }
  }
  
  public void clearT2Method()
  {
    clearEnum(T2METHOD);
  }
  
  public void validateT2Method()
  {
    this.validateAttribute(T2METHOD);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeEnumerationDAOIF getT2MethodMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationType.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeEnumerationDAOIF)mdClassIF.definesAttribute(T2METHOD);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationType.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(TYPE);
  }
  
  public Integer getWeeksAfter()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(WEEKSAFTER));
  }
  
  public void validateWeeksAfter()
  {
    this.validateAttribute(WEEKSAFTER);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getWeeksAfterMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationType.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(WEEKSAFTER);
  }
  
  public void setWeeksAfter(Integer value)
  {
    if(value == null)
    {
      setValue(WEEKSAFTER, "");
    }
    else
    {
      setValue(WEEKSAFTER, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getWeeksBefore()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(WEEKSBEFORE));
  }
  
  public void validateWeeksBefore()
  {
    this.validateAttribute(WEEKSBEFORE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getWeeksBeforeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationType.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(WEEKSBEFORE);
  }
  
  public void setWeeksBefore(Integer value)
  {
    if(value == null)
    {
      setValue(WEEKSBEFORE, "");
    }
    else
    {
      setValue(WEEKSBEFORE, java.lang.Integer.toString(value));
    }
  }
  
  public Double getWeight0()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(WEIGHT0));
  }
  
  public void validateWeight0()
  {
    this.validateAttribute(WEIGHT0);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF getWeight0Md()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationType.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF)mdClassIF.definesAttribute(WEIGHT0);
  }
  
  public void setWeight0(Double value)
  {
    if(value == null)
    {
      setValue(WEIGHT0, "");
    }
    else
    {
      setValue(WEIGHT0, java.lang.Double.toString(value));
    }
  }
  
  public Double getWeight1()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(WEIGHT1));
  }
  
  public void validateWeight1()
  {
    this.validateAttribute(WEIGHT1);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF getWeight1Md()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationType.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF)mdClassIF.definesAttribute(WEIGHT1);
  }
  
  public void setWeight1(Double value)
  {
    if(value == null)
    {
      setValue(WEIGHT1, "");
    }
    else
    {
      setValue(WEIGHT1, java.lang.Double.toString(value));
    }
  }
  
  public Double getWeight2()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(WEIGHT2));
  }
  
  public void validateWeight2()
  {
    this.validateAttribute(WEIGHT2);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF getWeight2Md()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationType.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF)mdClassIF.definesAttribute(WEIGHT2);
  }
  
  public void setWeight2(Double value)
  {
    if(value == null)
    {
      setValue(WEIGHT2, "");
    }
    else
    {
      setValue(WEIGHT2, java.lang.Double.toString(value));
    }
  }
  
  public Double getWeight3()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(WEIGHT3));
  }
  
  public void validateWeight3()
  {
    this.validateAttribute(WEIGHT3);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF getWeight3Md()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationType.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF)mdClassIF.definesAttribute(WEIGHT3);
  }
  
  public void setWeight3(Double value)
  {
    if(value == null)
    {
      setValue(WEIGHT3, "");
    }
    else
    {
      setValue(WEIGHT3, java.lang.Double.toString(value));
    }
  }
  
  public Double getWeight4()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(WEIGHT4));
  }
  
  public void validateWeight4()
  {
    this.validateAttribute(WEIGHT4);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF getWeight4Md()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationType.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF)mdClassIF.definesAttribute(WEIGHT4);
  }
  
  public void setWeight4(Double value)
  {
    if(value == null)
    {
      setValue(WEIGHT4, "");
    }
    else
    {
      setValue(WEIGHT4, java.lang.Double.toString(value));
    }
  }
  
  public Double getWeight5()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(WEIGHT5));
  }
  
  public void validateWeight5()
  {
    this.validateAttribute(WEIGHT5);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF getWeight5Md()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationType.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF)mdClassIF.definesAttribute(WEIGHT5);
  }
  
  public void setWeight5(Double value)
  {
    if(value == null)
    {
      setValue(WEIGHT5, "");
    }
    else
    {
      setValue(WEIGHT5, java.lang.Double.toString(value));
    }
  }
  
  public Double getWeight6()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(WEIGHT6));
  }
  
  public void validateWeight6()
  {
    this.validateAttribute(WEIGHT6);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF getWeight6Md()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationType.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF)mdClassIF.definesAttribute(WEIGHT6);
  }
  
  public void setWeight6(Double value)
  {
    if(value == null)
    {
      setValue(WEIGHT6, "");
    }
    else
    {
      setValue(WEIGHT6, java.lang.Double.toString(value));
    }
  }
  
  public Double getWeight7()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(WEIGHT7));
  }
  
  public void validateWeight7()
  {
    this.validateAttribute(WEIGHT7);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF getWeight7Md()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationType.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF)mdClassIF.definesAttribute(WEIGHT7);
  }
  
  public void setWeight7(Double value)
  {
    if(value == null)
    {
      setValue(WEIGHT7, "");
    }
    else
    {
      setValue(WEIGHT7, java.lang.Double.toString(value));
    }
  }
  
  public Double getWeight8()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(WEIGHT8));
  }
  
  public void validateWeight8()
  {
    this.validateAttribute(WEIGHT8);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF getWeight8Md()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationType.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF)mdClassIF.definesAttribute(WEIGHT8);
  }
  
  public void setWeight8(Double value)
  {
    if(value == null)
    {
      setValue(WEIGHT8, "");
    }
    else
    {
      setValue(WEIGHT8, java.lang.Double.toString(value));
    }
  }
  
  public Double getWeight9()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(WEIGHT9));
  }
  
  public void validateWeight9()
  {
    this.validateAttribute(WEIGHT9);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF getWeight9Md()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ThresholdCalculationType.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF)mdClassIF.definesAttribute(WEIGHT9);
  }
  
  public void setWeight9(Double value)
  {
    if(value == null)
    {
      setValue(WEIGHT9, "");
    }
    else
    {
      setValue(WEIGHT9, java.lang.Double.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static ThresholdCalculationTypeQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    ThresholdCalculationTypeQuery query = new ThresholdCalculationTypeQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static ThresholdCalculationType get(String id)
  {
    return (ThresholdCalculationType) com.runwaysdk.business.Business.get(id);
  }
  
  public static ThresholdCalculationType getByKey(String key)
  {
    return (ThresholdCalculationType) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public static ThresholdCalculationType lock(java.lang.String id)
  {
    ThresholdCalculationType _instance = ThresholdCalculationType.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static ThresholdCalculationType unlock(java.lang.String id)
  {
    ThresholdCalculationType _instance = ThresholdCalculationType.get(id);
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
