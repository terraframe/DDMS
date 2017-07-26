package dss.vector.solutions.etl.dhis2.response;

@com.runwaysdk.business.ClassSignature(hash = -1916372840)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to OAuthLoginRequiredException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class OAuthLoginRequiredExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.etl.dhis2.response.OAuthLoginRequiredException";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = -1916372840;
  
  public OAuthLoginRequiredExceptionBase()
  {
    super();
  }
  
  public OAuthLoginRequiredExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public OAuthLoginRequiredExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public OAuthLoginRequiredExceptionBase(java.lang.Throwable cause)
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
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.etl.dhis2.response.OAuthLoginRequiredException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{id}", this.getId());
    return message;
  }
  
}
