package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = 1138569385)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to DuplicateOperatorSprayImportException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class DuplicateOperatorSprayImportExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.DuplicateOperatorSprayImportException";
  public static java.lang.String HOUSEHOLDID = "householdId";
  public static java.lang.String ID = "id";
  public static java.lang.String INSECTICIDETERM = "insecticideTerm";
  public static java.lang.String OPERATORID = "operatorId";
  public static java.lang.String SPRAYDATE = "sprayDate";
  public static java.lang.String SPRAYMETHOD = "sprayMethod";
  public static java.lang.String STRUCTUREID = "structureId";
  private static final long serialVersionUID = 1138569385;
  
  public DuplicateOperatorSprayImportExceptionBase()
  {
    super();
  }
  
  public DuplicateOperatorSprayImportExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public DuplicateOperatorSprayImportExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public DuplicateOperatorSprayImportExceptionBase(java.lang.Throwable cause)
  {
    super(cause);
  }
  
  public String getHouseholdId()
  {
    return getValue(HOUSEHOLDID);
  }
  
  public void validateHouseholdId()
  {
    this.validateAttribute(HOUSEHOLDID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getHouseholdIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.DuplicateOperatorSprayImportException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(HOUSEHOLDID);
  }
  
  public void setHouseholdId(String value)
  {
    if(value == null)
    {
      setValue(HOUSEHOLDID, "");
    }
    else
    {
      setValue(HOUSEHOLDID, value);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.DuplicateOperatorSprayImportException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  public String getInsecticideTerm()
  {
    return getValue(INSECTICIDETERM);
  }
  
  public void validateInsecticideTerm()
  {
    this.validateAttribute(INSECTICIDETERM);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getInsecticideTermMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.DuplicateOperatorSprayImportException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(INSECTICIDETERM);
  }
  
  public void setInsecticideTerm(String value)
  {
    if(value == null)
    {
      setValue(INSECTICIDETERM, "");
    }
    else
    {
      setValue(INSECTICIDETERM, value);
    }
  }
  
  public String getOperatorId()
  {
    return getValue(OPERATORID);
  }
  
  public void validateOperatorId()
  {
    this.validateAttribute(OPERATORID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getOperatorIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.DuplicateOperatorSprayImportException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(OPERATORID);
  }
  
  public void setOperatorId(String value)
  {
    if(value == null)
    {
      setValue(OPERATORID, "");
    }
    else
    {
      setValue(OPERATORID, value);
    }
  }
  
  public java.util.Date getSprayDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(SPRAYDATE));
  }
  
  public void validateSprayDate()
  {
    this.validateAttribute(SPRAYDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDateDAOIF getSprayDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.DuplicateOperatorSprayImportException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDateDAOIF)mdClassIF.definesAttribute(SPRAYDATE);
  }
  
  public void setSprayDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(SPRAYDATE, "");
    }
    else
    {
      setValue(SPRAYDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public String getSprayMethod()
  {
    return getValue(SPRAYMETHOD);
  }
  
  public void validateSprayMethod()
  {
    this.validateAttribute(SPRAYMETHOD);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getSprayMethodMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.DuplicateOperatorSprayImportException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(SPRAYMETHOD);
  }
  
  public void setSprayMethod(String value)
  {
    if(value == null)
    {
      setValue(SPRAYMETHOD, "");
    }
    else
    {
      setValue(SPRAYMETHOD, value);
    }
  }
  
  public String getStructureId()
  {
    return getValue(STRUCTUREID);
  }
  
  public void validateStructureId()
  {
    this.validateAttribute(STRUCTUREID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getStructureIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.DuplicateOperatorSprayImportException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(STRUCTUREID);
  }
  
  public void setStructureId(String value)
  {
    if(value == null)
    {
      setValue(STRUCTUREID, "");
    }
    else
    {
      setValue(STRUCTUREID, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{householdId}", this.getHouseholdId());
    message = replace(message, "{id}", this.getId());
    message = replace(message, "{insecticideTerm}", this.getInsecticideTerm());
    message = replace(message, "{operatorId}", this.getOperatorId());
    message = replace(message, "{sprayDate}", this.getSprayDate());
    message = replace(message, "{sprayMethod}", this.getSprayMethod());
    message = replace(message, "{structureId}", this.getStructureId());
    return message;
  }
  
}
