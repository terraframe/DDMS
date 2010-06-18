package dss.vector.solutions.geo;

@com.runwaysdk.business.ClassSignature(hash = 29084146)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to DuplicateEarthException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class DuplicateEarthExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.DuplicateEarthException";
  public static java.lang.String EARTHNAME = "earthName";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = 29084146;
  
  public DuplicateEarthExceptionBase()
  {
    super();
  }
  
  public DuplicateEarthExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public DuplicateEarthExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public DuplicateEarthExceptionBase(java.lang.Throwable cause)
  {
    super(cause);
  }
  
  public String getEarthName()
  {
    return getValue(EARTHNAME);
  }
  
  public void validateEarthName()
  {
    this.validateAttribute(EARTHNAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getEarthNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.DuplicateEarthException.CLASS);
    return mdClassIF.definesAttribute(EARTHNAME);
  }
  
  public void setEarthName(String value)
  {
    if(value == null)
    {
      setValue(EARTHNAME, "");
    }
    else
    {
      setValue(EARTHNAME, value);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.DuplicateEarthException.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{earthName}", this.getEarthName());
    message = replace(message, "{id}", this.getId());
    return message;
  }
  
}
