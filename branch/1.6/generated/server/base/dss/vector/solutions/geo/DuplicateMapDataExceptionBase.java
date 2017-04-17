package dss.vector.solutions.geo;

@com.runwaysdk.business.ClassSignature(hash = 2044652183)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to DuplicateMapDataException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class DuplicateMapDataExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.DuplicateMapDataException";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = 2044652183;
  
  public DuplicateMapDataExceptionBase()
  {
    super();
  }
  
  public DuplicateMapDataExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public DuplicateMapDataExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public DuplicateMapDataExceptionBase(java.lang.Throwable cause)
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.DuplicateMapDataException.CLASS);
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
