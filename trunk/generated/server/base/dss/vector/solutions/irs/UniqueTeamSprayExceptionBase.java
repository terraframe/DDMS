package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = 943239155)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to UniqueTeamSprayException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class UniqueTeamSprayExceptionBase extends dss.vector.solutions.irs.UniqueSprayException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.UniqueTeamSprayException";
  public static java.lang.String SPRAYTEAM = "sprayTeam";
  private static final long serialVersionUID = 943239155;
  
  public UniqueTeamSprayExceptionBase()
  {
    super();
  }
  
  public UniqueTeamSprayExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public UniqueTeamSprayExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public UniqueTeamSprayExceptionBase(java.lang.Throwable cause)
  {
    super(cause);
  }
  
  public String getSprayTeam()
  {
    return getValue(SPRAYTEAM);
  }
  
  public void validateSprayTeam()
  {
    this.validateAttribute(SPRAYTEAM);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSprayTeamMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.UniqueTeamSprayException.CLASS);
    return mdClassIF.definesAttribute(SPRAYTEAM);
  }
  
  public void setSprayTeam(String value)
  {
    if(value == null)
    {
      setValue(SPRAYTEAM, "");
    }
    else
    {
      setValue(SPRAYTEAM, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{sprayTeam}", this.getSprayTeam());
    return message;
  }
  
}
