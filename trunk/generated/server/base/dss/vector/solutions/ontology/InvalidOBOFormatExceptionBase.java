package dss.vector.solutions.ontology;

@com.runwaysdk.business.ClassSignature(hash = -63070605)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InvalidOBOFormatException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class InvalidOBOFormatExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.ontology.InvalidOBOFormatException";
  public static java.lang.String FILENAME = "fileName";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = -63070605;
  
  public InvalidOBOFormatExceptionBase()
  {
    super();
  }
  
  public InvalidOBOFormatExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public InvalidOBOFormatExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public InvalidOBOFormatExceptionBase(java.lang.Throwable cause)
  {
    super(cause);
  }
  
  public String getFileName()
  {
    return getValue(FILENAME);
  }
  
  public void validateFileName()
  {
    this.validateAttribute(FILENAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getFileNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.InvalidOBOFormatException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(FILENAME);
  }
  
  public void setFileName(String value)
  {
    if(value == null)
    {
      setValue(FILENAME, "");
    }
    else
    {
      setValue(FILENAME, value);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.InvalidOBOFormatException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{fileName}", this.getFileName());
    message = replace(message, "{id}", this.getId());
    return message;
  }
  
}
