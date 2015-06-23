package dss.vector.solutions.ontology;

@com.runwaysdk.business.ClassSignature(hash = -486156520)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ConfirmParentChangeException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class ConfirmParentChangeExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.ontology.ConfirmParentChangeException";
  public static java.lang.String ID = "id";
  public static java.lang.String PARENTTERM = "parentTerm";
  private static final long serialVersionUID = -486156520;
  
  public ConfirmParentChangeExceptionBase()
  {
    super();
  }
  
  public ConfirmParentChangeExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public ConfirmParentChangeExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public ConfirmParentChangeExceptionBase(java.lang.Throwable cause)
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.ConfirmParentChangeException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  public String getParentTerm()
  {
    return getValue(PARENTTERM);
  }
  
  public void validateParentTerm()
  {
    this.validateAttribute(PARENTTERM);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getParentTermMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.ConfirmParentChangeException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(PARENTTERM);
  }
  
  public void setParentTerm(String value)
  {
    if(value == null)
    {
      setValue(PARENTTERM, "");
    }
    else
    {
      setValue(PARENTTERM, value);
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
    message = replace(message, "{parentTerm}", this.getParentTerm());
    return message;
  }
  
}
