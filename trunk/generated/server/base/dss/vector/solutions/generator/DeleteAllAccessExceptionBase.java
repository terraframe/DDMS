package dss.vector.solutions.generator;

@com.runwaysdk.business.ClassSignature(hash = -621895108)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to DeleteAllAccessException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class DeleteAllAccessExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.generator.DeleteAllAccessException";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = -621895108;
  
  public DeleteAllAccessExceptionBase()
  {
    super();
  }
  
  public DeleteAllAccessExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public DeleteAllAccessExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public DeleteAllAccessExceptionBase(java.lang.Throwable cause)
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.generator.DeleteAllAccessException.CLASS);
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