package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = -67147016)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MalariaSeasonDateException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class MalariaSeasonDateExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.MalariaSeasonDateException";
  public static java.lang.String ID = "id";
  public static java.lang.String SEASONNAME = "seasonName";
  public static java.lang.String WEEKDATE = "weekDate";
  private static final long serialVersionUID = -67147016;
  
  public MalariaSeasonDateExceptionBase()
  {
    super();
  }
  
  public MalariaSeasonDateExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public MalariaSeasonDateExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public MalariaSeasonDateExceptionBase(java.lang.Throwable cause)
  {
    super(cause);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.MalariaSeasonDateException.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public String getSeasonName()
  {
    return getValue(SEASONNAME);
  }
  
  public void validateSeasonName()
  {
    this.validateAttribute(SEASONNAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSeasonNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.MalariaSeasonDateException.CLASS);
    return mdClassIF.definesAttribute(SEASONNAME);
  }
  
  public void setSeasonName(String value)
  {
    if(value == null)
    {
      setValue(SEASONNAME, "");
    }
    else
    {
      setValue(SEASONNAME, value);
    }
  }
  
  public java.util.Date getWeekDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(WEEKDATE));
  }
  
  public void validateWeekDate()
  {
    this.validateAttribute(WEEKDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getWeekDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.MalariaSeasonDateException.CLASS);
    return mdClassIF.definesAttribute(WEEKDATE);
  }
  
  public void setWeekDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(WEEKDATE, "");
    }
    else
    {
      setValue(WEEKDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
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
      java.lang.String message = com.runwaysdk.util.LocalizeUtil.getTemplate("dss.vector.solutions.general.MalariaSeasonDateException", locale);
      return this.localize(locale, message);
    }
    catch (java.io.IOException e)
    {
      throw new com.runwaysdk.dataaccess.io.XMLException(e.getLocalizedMessage());
    }
    catch (org.xml.sax.SAXException e)
    {
      throw new com.runwaysdk.dataaccess.io.XMLException(e.getLocalizedMessage());
    }
    catch (javax.xml.parsers.ParserConfigurationException e)
    {
      throw new com.runwaysdk.dataaccess.io.XMLException(e.getLocalizedMessage());
    }
    catch (com.runwaysdk.util.LocalizeException e)
    {
      throw new com.runwaysdk.dataaccess.io.XMLException(e.getLocalizedMessage());
    }
  }
  
  protected java.lang.String localize(java.util.Locale locale, java.lang.String message)
  {
    message = super.localize(locale, message);
    message = replace(message, "{id}", this.getId());
    message = replace(message, "{seasonName}", this.getSeasonName());
    message = replace(message, "{weekDate}", this.getWeekDate());
    return message;
  }
  
}
