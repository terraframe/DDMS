package dss.vector.solutions.ontology;

@com.runwaysdk.business.ClassSignature(hash = -526158732)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to SameChildParentTermException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class SameChildParentTermExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.ontology.SameChildParentTermException";
  public static java.lang.String ID = "id";
  public static java.lang.String TERM = "term";
  private static final long serialVersionUID = -526158732;
  
  public SameChildParentTermExceptionBase()
  {
    super();
  }
  
  public SameChildParentTermExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public SameChildParentTermExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public SameChildParentTermExceptionBase(java.lang.Throwable cause)
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.SameChildParentTermException.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public String getTerm()
  {
    return getValue(TERM);
  }
  
  public void validateTerm()
  {
    this.validateAttribute(TERM);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getTermMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.SameChildParentTermException.CLASS);
    return mdClassIF.definesAttribute(TERM);
  }
  
  public void setTerm(String value)
  {
    if(value == null)
    {
      setValue(TERM, "");
    }
    else
    {
      setValue(TERM, value);
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
    message = replace(message, "{term}", this.getTerm());
    return message;
  }
  
}
