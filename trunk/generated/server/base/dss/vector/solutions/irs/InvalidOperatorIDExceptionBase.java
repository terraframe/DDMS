package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = -78367334)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InvalidOperatorIDException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class InvalidOperatorIDExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.InvalidOperatorIDException";
  public static java.lang.String ID = "id";
  public static java.lang.String OPERATORID = "operatorId";
  private static final long serialVersionUID = -78367334;
  
  public InvalidOperatorIDExceptionBase()
  {
    super();
  }
  
  public InvalidOperatorIDExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public InvalidOperatorIDExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public InvalidOperatorIDExceptionBase(java.lang.Throwable cause)
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InvalidOperatorIDException.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public String getOperatorId()
  {
    return getValue(OPERATORID);
  }
  
  public void validateOperatorId()
  {
    this.validateAttribute(OPERATORID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getOperatorIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InvalidOperatorIDException.CLASS);
    return mdClassIF.definesAttribute(OPERATORID);
  }
  
  public void setOperatorId(String value)
  {
    if(value == null)
    {
      setValue(OPERATORID, "");
    }
    else
    {
      setValue(OPERATORID, value);
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
    message = replace(message, "{operatorId}", this.getOperatorId());
    return message;
  }
  
}
