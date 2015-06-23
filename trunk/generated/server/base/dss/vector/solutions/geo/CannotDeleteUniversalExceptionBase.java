package dss.vector.solutions.geo;

@com.runwaysdk.business.ClassSignature(hash = 373985342)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to CannotDeleteUniversalException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class CannotDeleteUniversalExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.CannotDeleteUniversalException";
  public static java.lang.String ID = "id";
  public static java.lang.String UNIVERSALLABEL = "universalLabel";
  private static final long serialVersionUID = 373985342;
  
  public CannotDeleteUniversalExceptionBase()
  {
    super();
  }
  
  public CannotDeleteUniversalExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public CannotDeleteUniversalExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public CannotDeleteUniversalExceptionBase(java.lang.Throwable cause)
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.CannotDeleteUniversalException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  public String getUniversalLabel()
  {
    return getValue(UNIVERSALLABEL);
  }
  
  public void validateUniversalLabel()
  {
    this.validateAttribute(UNIVERSALLABEL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getUniversalLabelMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.CannotDeleteUniversalException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(UNIVERSALLABEL);
  }
  
  public void setUniversalLabel(String value)
  {
    if(value == null)
    {
      setValue(UNIVERSALLABEL, "");
    }
    else
    {
      setValue(UNIVERSALLABEL, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{id}", this.getId());
    message = replace(message, "{universalLabel}", this.getUniversalLabel());
    return message;
  }
  
}
