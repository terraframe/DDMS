package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = -598913559)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ElevatedImmatureIndexAlert.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class ElevatedImmatureIndexAlertBase extends com.runwaysdk.business.Information implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.ElevatedImmatureIndexAlert";
  public static java.lang.String ACTUALVALUE = "actualValue";
  public static java.lang.String ALERTTYPE = "alertType";
  public static java.lang.String EMAILFAILURE = "emailFailure";
  public static java.lang.String ENDDATE = "endDate";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String ID = "id";
  public static java.lang.String PREMISETYPE = "premiseType";
  public static java.lang.String STARTDATE = "startDate";
  public static java.lang.String TAXON = "taxon";
  public static java.lang.String THRESHOLDTYPE = "thresholdType";
  public static java.lang.String THRESHOLDVALUE = "thresholdValue";
  private static final long serialVersionUID = -598913559;
  
  public ElevatedImmatureIndexAlertBase()
  {
    super();
  }
  
  public java.math.BigDecimal getActualValue()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(ACTUALVALUE));
  }
  
  public void validateActualValue()
  {
    this.validateAttribute(ACTUALVALUE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDecimalDAOIF getActualValueMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ElevatedImmatureIndexAlert.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDecimalDAOIF)mdClassIF.definesAttribute(ACTUALVALUE);
  }
  
  public void setActualValue(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(ACTUALVALUE, "");
    }
    else
    {
      setValue(ACTUALVALUE, value.toString());
    }
  }
  
  public String getAlertType()
  {
    return getValue(ALERTTYPE);
  }
  
  public void validateAlertType()
  {
    this.validateAttribute(ALERTTYPE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getAlertTypeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ElevatedImmatureIndexAlert.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ALERTTYPE);
  }
  
  public void setAlertType(String value)
  {
    if(value == null)
    {
      setValue(ALERTTYPE, "");
    }
    else
    {
      setValue(ALERTTYPE, value);
    }
  }
  
  public Boolean getEmailFailure()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(EMAILFAILURE));
  }
  
  public void validateEmailFailure()
  {
    this.validateAttribute(EMAILFAILURE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF getEmailFailureMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ElevatedImmatureIndexAlert.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF)mdClassIF.definesAttribute(EMAILFAILURE);
  }
  
  public void setEmailFailure(Boolean value)
  {
    if(value == null)
    {
      setValue(EMAILFAILURE, "");
    }
    else
    {
      setValue(EMAILFAILURE, java.lang.Boolean.toString(value));
    }
  }
  
  public java.util.Date getEndDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(ENDDATE));
  }
  
  public void validateEndDate()
  {
    this.validateAttribute(ENDDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDateDAOIF getEndDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ElevatedImmatureIndexAlert.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDateDAOIF)mdClassIF.definesAttribute(ENDDATE);
  }
  
  public void setEndDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(ENDDATE, "");
    }
    else
    {
      setValue(ENDDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public String getGeoEntity()
  {
    return getValue(GEOENTITY);
  }
  
  public void validateGeoEntity()
  {
    this.validateAttribute(GEOENTITY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getGeoEntityMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ElevatedImmatureIndexAlert.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(GEOENTITY);
  }
  
  public void setGeoEntity(String value)
  {
    if(value == null)
    {
      setValue(GEOENTITY, "");
    }
    else
    {
      setValue(GEOENTITY, value);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ElevatedImmatureIndexAlert.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  public String getPremiseType()
  {
    return getValue(PREMISETYPE);
  }
  
  public void validatePremiseType()
  {
    this.validateAttribute(PREMISETYPE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getPremiseTypeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ElevatedImmatureIndexAlert.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(PREMISETYPE);
  }
  
  public void setPremiseType(String value)
  {
    if(value == null)
    {
      setValue(PREMISETYPE, "");
    }
    else
    {
      setValue(PREMISETYPE, value);
    }
  }
  
  public java.util.Date getStartDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(STARTDATE));
  }
  
  public void validateStartDate()
  {
    this.validateAttribute(STARTDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDateDAOIF getStartDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ElevatedImmatureIndexAlert.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDateDAOIF)mdClassIF.definesAttribute(STARTDATE);
  }
  
  public void setStartDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(STARTDATE, "");
    }
    else
    {
      setValue(STARTDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public String getTaxon()
  {
    return getValue(TAXON);
  }
  
  public void validateTaxon()
  {
    this.validateAttribute(TAXON);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getTaxonMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ElevatedImmatureIndexAlert.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(TAXON);
  }
  
  public void setTaxon(String value)
  {
    if(value == null)
    {
      setValue(TAXON, "");
    }
    else
    {
      setValue(TAXON, value);
    }
  }
  
  public String getThresholdType()
  {
    return getValue(THRESHOLDTYPE);
  }
  
  public void validateThresholdType()
  {
    this.validateAttribute(THRESHOLDTYPE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getThresholdTypeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ElevatedImmatureIndexAlert.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(THRESHOLDTYPE);
  }
  
  public void setThresholdType(String value)
  {
    if(value == null)
    {
      setValue(THRESHOLDTYPE, "");
    }
    else
    {
      setValue(THRESHOLDTYPE, value);
    }
  }
  
  public java.math.BigDecimal getThresholdValue()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(THRESHOLDVALUE));
  }
  
  public void validateThresholdValue()
  {
    this.validateAttribute(THRESHOLDVALUE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDecimalDAOIF getThresholdValueMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ElevatedImmatureIndexAlert.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDecimalDAOIF)mdClassIF.definesAttribute(THRESHOLDVALUE);
  }
  
  public void setThresholdValue(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(THRESHOLDVALUE, "");
    }
    else
    {
      setValue(THRESHOLDVALUE, value.toString());
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{actualValue}", this.getActualValue());
    message = replace(message, "{alertType}", this.getAlertType());
    message = replace(message, "{emailFailure}", this.getEmailFailure());
    message = replace(message, "{endDate}", this.getEndDate());
    message = replace(message, "{geoEntity}", this.getGeoEntity());
    message = replace(message, "{id}", this.getId());
    message = replace(message, "{premiseType}", this.getPremiseType());
    message = replace(message, "{startDate}", this.getStartDate());
    message = replace(message, "{taxon}", this.getTaxon());
    message = replace(message, "{thresholdType}", this.getThresholdType());
    message = replace(message, "{thresholdValue}", this.getThresholdValue());
    return message;
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
