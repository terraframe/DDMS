package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = 178526938)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InvalidMemberIDException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class InvalidMemberIDExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.InvalidMemberIDException";
  public static java.lang.String ID = "id";
  public static java.lang.String MEMBERID = "memberId";
  private static final long serialVersionUID = 178526938;
  
  public InvalidMemberIDExceptionBase()
  {
    super();
  }
  
  public InvalidMemberIDExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public InvalidMemberIDExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public InvalidMemberIDExceptionBase(java.lang.Throwable cause)
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InvalidMemberIDException.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public String getMemberId()
  {
    return getValue(MEMBERID);
  }
  
  public void validateMemberId()
  {
    this.validateAttribute(MEMBERID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getMemberIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.InvalidMemberIDException.CLASS);
    return mdClassIF.definesAttribute(MEMBERID);
  }
  
  public void setMemberId(String value)
  {
    if(value == null)
    {
      setValue(MEMBERID, "");
    }
    else
    {
      setValue(MEMBERID, value);
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
    message = replace(message, "{memberId}", this.getMemberId());
    return message;
  }
  
}
