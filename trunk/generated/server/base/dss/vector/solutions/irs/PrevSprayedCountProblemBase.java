package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = 950319806)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to PrevSprayedCountProblem.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class PrevSprayedCountProblemBase extends dss.vector.solutions.NotificationProblem implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.PrevSprayedCountProblem";
  public static java.lang.String HOUSEHOLDID = "householdId";
  private static final long serialVersionUID = 950319806;
  
  public PrevSprayedCountProblemBase()
  {
    super();
  }
  
  public PrevSprayedCountProblemBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public String getHouseholdId()
  {
    return getValue(HOUSEHOLDID);
  }
  
  public void validateHouseholdId()
  {
    this.validateAttribute(HOUSEHOLDID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getHouseholdIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.PrevSprayedCountProblem.CLASS);
    return mdClassIF.definesAttribute(HOUSEHOLDID);
  }
  
  public void setHouseholdId(String value)
  {
    if(value == null)
    {
      setValue(HOUSEHOLDID, "");
    }
    else
    {
      setValue(HOUSEHOLDID, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{householdId}", this.getHouseholdId());
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
