package dss.vector.solutions;

@com.runwaysdk.business.ClassSignature(hash = 45204981)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to UnknownGeoEntityException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class UnknownGeoEntityExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.UnknownGeoEntityException";
  public static java.lang.String ENTITYNAME = "entityName";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = 45204981;
  
  public UnknownGeoEntityExceptionBase()
  {
    super();
  }
  
  public UnknownGeoEntityExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public UnknownGeoEntityExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public UnknownGeoEntityExceptionBase(java.lang.Throwable cause)
  {
    super(cause);
  }
  
  public String getEntityName()
  {
    return getValue(ENTITYNAME);
  }
  
  public void validateEntityName()
  {
    this.validateAttribute(ENTITYNAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getEntityNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.UnknownGeoEntityException.CLASS);
    return mdClassIF.definesAttribute(ENTITYNAME);
  }
  
  public void setEntityName(String value)
  {
    if(value == null)
    {
      setValue(ENTITYNAME, "");
    }
    else
    {
      setValue(ENTITYNAME, value);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.UnknownGeoEntityException.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    try
    {
      java.lang.String message = com.runwaysdk.util.LocalizeUtil.getTemplate("dss.vector.solutions.UnknownGeoEntityException", locale);
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
    message = replace(message, "{entityName}", this.getEntityName());
    message = replace(message, "{id}", this.getId());
    return message;
  }
  
}
