package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = 1773699512)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InvalidPercentageProblem.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class InvalidPercentageProblemBase extends dss.vector.solutions.NotificationProblem implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.InvalidPercentageProblem";
  public static java.lang.String INVALIDPERCENT = "invalidPercent";
  private static final long serialVersionUID = 1773699512;
  
  public InvalidPercentageProblemBase()
  {
    super();
  }
  
  public InvalidPercentageProblemBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public Integer getInvalidPercent()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INVALIDPERCENT));
  }
  
  public void validateInvalidPercent()
  {
    this.validateAttribute(INVALIDPERCENT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getInvalidPercentMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.InvalidPercentageProblem.CLASS);
    return mdClassIF.definesAttribute(INVALIDPERCENT);
  }
  
  public void setInvalidPercent(Integer value)
  {
    if(value == null)
    {
      setValue(INVALIDPERCENT, "");
    }
    else
    {
      setValue(INVALIDPERCENT, java.lang.Integer.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{invalidPercent}", this.getInvalidPercent());
    return message;
  }
  
  public String toString()
  {
    if (this.isNew())
    {
      return "New: "+ this.getClassDisplayLabel();
    }
    else
    {
      return super.toString();
    }
  }
}
