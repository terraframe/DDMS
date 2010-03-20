package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = -1826707730)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to UniqueSprayException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class UniqueSprayExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.UniqueSprayException";
  public static java.lang.String BRAND = "brand";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String ID = "id";
  public static java.lang.String SPRAYDATE = "sprayDate";
  public static java.lang.String SPRAYMETHOD = "sprayMethod";
  private static final long serialVersionUID = -1826707730;
  
  public UniqueSprayExceptionBase()
  {
    super();
  }
  
  public UniqueSprayExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public UniqueSprayExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public UniqueSprayExceptionBase(java.lang.Throwable cause)
  {
    super(cause);
  }
  
  public String getBrand()
  {
    return getValue(BRAND);
  }
  
  public void validateBrand()
  {
    this.validateAttribute(BRAND);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getBrandMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.UniqueSprayException.CLASS);
    return mdClassIF.definesAttribute(BRAND);
  }
  
  public void setBrand(String value)
  {
    if(value == null)
    {
      setValue(BRAND, "");
    }
    else
    {
      setValue(BRAND, value);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getGeoEntityMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.UniqueSprayException.CLASS);
    return mdClassIF.definesAttribute(GEOENTITY);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.UniqueSprayException.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public java.util.Date getSprayDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(SPRAYDATE));
  }
  
  public void validateSprayDate()
  {
    this.validateAttribute(SPRAYDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSprayDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.UniqueSprayException.CLASS);
    return mdClassIF.definesAttribute(SPRAYDATE);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSprayMethodMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.UniqueSprayException.CLASS);
    return mdClassIF.definesAttribute(SPRAYMETHOD);
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
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    try
    {
      java.lang.String message = com.runwaysdk.util.LocalizeUtil.getTemplate("dss.vector.solutions.irs.UniqueSprayException", locale);
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
    message = replace(message, "{brand}", this.getBrand());
    message = replace(message, "{geoEntity}", this.getGeoEntity());
    message = replace(message, "{id}", this.getId());
    message = replace(message, "{sprayDate}", this.getSprayDate());
    message = replace(message, "{sprayMethod}", this.getSprayMethod());
    return message;
  }
  
}
