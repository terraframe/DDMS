package dss.vector.solutions.query;

@com.runwaysdk.business.ClassSignature(hash = 1083501552)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to DatesOnlyException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class DatesOnlyExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.DatesOnlyException";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = 1083501552;
  
  public DatesOnlyExceptionBase()
  {
    super();
  }
  
  public DatesOnlyExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public DatesOnlyExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public DatesOnlyExceptionBase(java.lang.Throwable cause)
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.DatesOnlyException.CLASS);
    return mdClassIF.definesAttribute(ID);
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
