package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = -837213265)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to UnknownValueException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class UnknownValueExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.UnknownValueException";
  public static java.lang.String ATTRIBUTELABEL = "attributeLabel";
  public static java.lang.String ID = "id";
  public static java.lang.String TYPELABEL = "typeLabel";
  public static java.lang.String VALUE = "value";
  private static final long serialVersionUID = -837213265;
  
  public UnknownValueExceptionBase()
  {
    super();
  }
  
  public UnknownValueExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public UnknownValueExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public UnknownValueExceptionBase(java.lang.Throwable cause)
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
  
  public static com.runwaysdk.dataaccess.MdAttributeTextDAOIF getAttributeLabelMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.UnknownValueException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeTextDAOIF)mdClassIF.definesAttribute(ATTRIBUTELABEL);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.UnknownValueException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  public String getTypeLabel()
  {
    return getValue(TYPELABEL);
  }
  
  public void validateTypeLabel()
  {
    this.validateAttribute(TYPELABEL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeTextDAOIF getTypeLabelMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.UnknownValueException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeTextDAOIF)mdClassIF.definesAttribute(TYPELABEL);
  }
  
  public void setTypeLabel(String value)
  {
    if(value == null)
    {
      setValue(TYPELABEL, "");
    }
    else
    {
      setValue(TYPELABEL, value);
    }
  }
  
  public String getValue()
  {
    return getValue(VALUE);
  }
  
  public void validateValue()
  {
    this.validateAttribute(VALUE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeTextDAOIF getValueMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.UnknownValueException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeTextDAOIF)mdClassIF.definesAttribute(VALUE);
  }
  
  public void setValue(String value)
  {
    if(value == null)
    {
      setValue(VALUE, "");
    }
    else
    {
      setValue(VALUE, value);
    }
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
    message = replace(message, "{typeLabel}", this.getTypeLabel());
    message = replace(message, "{value}", this.getValue());
    return message;
  }
  
}
