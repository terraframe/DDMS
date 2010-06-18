package dss.vector.solutions;

@com.runwaysdk.business.ClassSignature(hash = -610464346)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to RequiredAttributeException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class RequiredAttributeExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.RequiredAttributeException";
  public static java.lang.String ATTRIBUTELABEL = "attributeLabel";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = -610464346;
  
  public RequiredAttributeExceptionBase()
  {
    super();
  }
  
  public RequiredAttributeExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public RequiredAttributeExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public RequiredAttributeExceptionBase(java.lang.Throwable cause)
  {
    super(cause);
  }
  
  public String getAttributeLabel()
  {
    return getValue(ATTRIBUTELABEL);
  }
  
  public void validateAttributeLabel()
  {
    this.validateAttribute(ATTRIBUTELABEL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getAttributeLabelMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.RequiredAttributeException.CLASS);
    return mdClassIF.definesAttribute(ATTRIBUTELABEL);
  }
  
  public void setAttributeLabel(String value)
  {
    if(value == null)
    {
      setValue(ATTRIBUTELABEL, "");
    }
    else
    {
      setValue(ATTRIBUTELABEL, value);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.RequiredAttributeException.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{attributeLabel}", this.getAttributeLabel());
    message = replace(message, "{id}", this.getId());
    return message;
  }
  
}
