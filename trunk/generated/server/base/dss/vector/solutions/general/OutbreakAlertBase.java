package dss.vector.solutions.general;

@com.terraframe.mojo.business.ClassSignature(hash = 2115832046)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to OutbreakAlert.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class OutbreakAlertBase extends com.terraframe.mojo.business.Information implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.OutbreakAlert";
  public static java.lang.String ALERTTYPE = "alertType";
  public static java.lang.String EMAILFAILURE = "emailFailure";
  public static java.lang.String ENTITYLABEL = "entityLabel";
  public static java.lang.String ID = "id";
  public static java.lang.String THRESHOLD = "threshold";
  public static java.lang.String THRESHOLDTYPE = "thresholdType";
  public static java.lang.String TOTALCASES = "totalCases";
  private static final long serialVersionUID = 2115832046;
  
  public OutbreakAlertBase()
  {
    super();
  }
  
  public String getAlertType()
  {
    return getValue(ALERTTYPE);
  }
  
  public void validateAlertType()
  {
    this.validateAttribute(ALERTTYPE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getAlertTypeMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.OutbreakAlert.CLASS);
    return mdClassIF.definesAttribute(ALERTTYPE);
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
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(EMAILFAILURE));
  }
  
  public void validateEmailFailure()
  {
    this.validateAttribute(EMAILFAILURE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getEmailFailureMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.OutbreakAlert.CLASS);
    return mdClassIF.definesAttribute(EMAILFAILURE);
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
  
  public String getEntityLabel()
  {
    return getValue(ENTITYLABEL);
  }
  
  public void validateEntityLabel()
  {
    this.validateAttribute(ENTITYLABEL);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getEntityLabelMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.OutbreakAlert.CLASS);
    return mdClassIF.definesAttribute(ENTITYLABEL);
  }
  
  public void setEntityLabel(String value)
  {
    if(value == null)
    {
      setValue(ENTITYLABEL, "");
    }
    else
    {
      setValue(ENTITYLABEL, value);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.OutbreakAlert.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public Integer getThreshold()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(THRESHOLD));
  }
  
  public void validateThreshold()
  {
    this.validateAttribute(THRESHOLD);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getThresholdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.OutbreakAlert.CLASS);
    return mdClassIF.definesAttribute(THRESHOLD);
  }
  
  public void setThreshold(Integer value)
  {
    if(value == null)
    {
      setValue(THRESHOLD, "");
    }
    else
    {
      setValue(THRESHOLD, java.lang.Integer.toString(value));
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
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getThresholdTypeMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.OutbreakAlert.CLASS);
    return mdClassIF.definesAttribute(THRESHOLDTYPE);
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
  
  public Long getTotalCases()
  {
    return com.terraframe.mojo.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(TOTALCASES));
  }
  
  public void validateTotalCases()
  {
    this.validateAttribute(TOTALCASES);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getTotalCasesMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.OutbreakAlert.CLASS);
    return mdClassIF.definesAttribute(TOTALCASES);
  }
  
  public void setTotalCases(Long value)
  {
    if(value == null)
    {
      setValue(TOTALCASES, "");
    }
    else
    {
      setValue(TOTALCASES, java.lang.Long.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    try
    {
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.general.OutbreakAlert", locale);
      return this.localize(locale, message);
    }
    catch (java.io.IOException e)
    {
      throw new com.terraframe.mojo.dataaccess.io.XMLException(e.getLocalizedMessage());
    }
    catch (org.xml.sax.SAXException e)
    {
      throw new com.terraframe.mojo.dataaccess.io.XMLException(e.getLocalizedMessage());
    }
    catch (javax.xml.parsers.ParserConfigurationException e)
    {
      throw new com.terraframe.mojo.dataaccess.io.XMLException(e.getLocalizedMessage());
    }
    catch (com.terraframe.mojo.util.LocalizeException e)
    {
      throw new com.terraframe.mojo.dataaccess.io.XMLException(e.getLocalizedMessage());
    }
  }
  
  protected java.lang.String localize(java.util.Locale locale, java.lang.String message)
  {
    message = super.localize(locale, message);
    message = replace(message, "{alertType}", this.getAlertType());
    message = replace(message, "{emailFailure}", this.getEmailFailure());
    message = replace(message, "{entityLabel}", this.getEntityLabel());
    message = replace(message, "{id}", this.getId());
    message = replace(message, "{threshold}", this.getThreshold());
    message = replace(message, "{thresholdType}", this.getThresholdType());
    message = replace(message, "{totalCases}", this.getTotalCases());
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
