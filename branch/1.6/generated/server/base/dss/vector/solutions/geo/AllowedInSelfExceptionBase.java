package dss.vector.solutions.geo;

@com.runwaysdk.business.ClassSignature(hash = 1634068863)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AllowedInSelfException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class AllowedInSelfExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.AllowedInSelfException";
  public static java.lang.String DISPLAYLABEL = "displayLabel";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = 1634068863;
  
  public AllowedInSelfExceptionBase()
  {
    super();
  }
  
  public AllowedInSelfExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public AllowedInSelfExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public AllowedInSelfExceptionBase(java.lang.Throwable cause)
  {
    super(cause);
  }
  
  public String getDisplayLabel()
  {
    return getValue(DISPLAYLABEL);
  }
  
  public void validateDisplayLabel()
  {
    this.validateAttribute(DISPLAYLABEL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getDisplayLabelMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.AllowedInSelfException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(DISPLAYLABEL);
  }
  
  public void setDisplayLabel(String value)
  {
    if(value == null)
    {
      setValue(DISPLAYLABEL, "");
    }
    else
    {
      setValue(DISPLAYLABEL, value);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.AllowedInSelfException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{displayLabel}", this.getDisplayLabel());
    message = replace(message, "{id}", this.getId());
    return message;
  }
  
}
