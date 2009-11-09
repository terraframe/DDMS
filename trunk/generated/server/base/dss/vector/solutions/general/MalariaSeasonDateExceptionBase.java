package dss.vector.solutions.general;

@com.terraframe.mojo.business.ClassSignature(hash = -55047214)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MalariaSeasonDateException.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class MalariaSeasonDateExceptionBase extends com.terraframe.mojo.business.SmartException implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.MalariaSeasonDateException";
  public static java.lang.String ID = "id";
  public static java.lang.String SEASONNAME = "seasonName";
  public static java.lang.String WEEKDATE = "weekDate";
  private static final long serialVersionUID = -55047214;
  
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
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.MalariaSeasonDateException.CLASS);
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
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSeasonNameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.MalariaSeasonDateException.CLASS);
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
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(WEEKDATE));
  }
  
  public void validateWeekDate()
  {
    this.validateAttribute(WEEKDATE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getWeekDateMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.MalariaSeasonDateException.CLASS);
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
      setValue(WEEKDATE, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.general.MalariaSeasonDateException", locale);
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
    message = replace(message, "{id}", this.getId());
    message = replace(message, "{seasonName}", this.getSeasonName());
    message = replace(message, "{weekDate}", this.getWeekDate());
    return message;
  }
  
}
