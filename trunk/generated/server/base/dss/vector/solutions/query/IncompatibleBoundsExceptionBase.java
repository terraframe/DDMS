package dss.vector.solutions.query;

@com.runwaysdk.business.ClassSignature(hash = 56665083)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to IncompatibleBoundsException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class IncompatibleBoundsExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.IncompatibleBoundsException";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = 56665083;
  
  public IncompatibleBoundsExceptionBase()
  {
    super();
  }
  
  public IncompatibleBoundsExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public IncompatibleBoundsExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public IncompatibleBoundsExceptionBase(java.lang.Throwable cause)
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.IncompatibleBoundsException.CLASS);
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
