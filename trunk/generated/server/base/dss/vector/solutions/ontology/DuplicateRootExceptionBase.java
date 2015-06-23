package dss.vector.solutions.ontology;

@com.runwaysdk.business.ClassSignature(hash = -275463343)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to DuplicateRootException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class DuplicateRootExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.ontology.DuplicateRootException";
  public static java.lang.String BROWSERFIELD = "browserField";
  public static java.lang.String BROWSERROOT = "browserRoot";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = -275463343;
  
  public DuplicateRootExceptionBase()
  {
    super();
  }
  
  public DuplicateRootExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public DuplicateRootExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public DuplicateRootExceptionBase(java.lang.Throwable cause)
  {
    super(cause);
  }
  
  public String getBrowserField()
  {
    return getValue(BROWSERFIELD);
  }
  
  public void validateBrowserField()
  {
    this.validateAttribute(BROWSERFIELD);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getBrowserFieldMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.DuplicateRootException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(BROWSERFIELD);
  }
  
  public void setBrowserField(String value)
  {
    if(value == null)
    {
      setValue(BROWSERFIELD, "");
    }
    else
    {
      setValue(BROWSERFIELD, value);
    }
  }
  
  public String getBrowserRoot()
  {
    return getValue(BROWSERROOT);
  }
  
  public void validateBrowserRoot()
  {
    this.validateAttribute(BROWSERROOT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getBrowserRootMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.DuplicateRootException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(BROWSERROOT);
  }
  
  public void setBrowserRoot(String value)
  {
    if(value == null)
    {
      setValue(BROWSERROOT, "");
    }
    else
    {
      setValue(BROWSERROOT, value);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.DuplicateRootException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{browserField}", this.getBrowserField());
    message = replace(message, "{browserRoot}", this.getBrowserRoot());
    message = replace(message, "{id}", this.getId());
    return message;
  }
  
}
