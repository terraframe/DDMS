package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = -1181040016)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InvalidTeamIdException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class InvalidTeamIdExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.InvalidTeamIdException";
  public static java.lang.String ID = "id";
  public static java.lang.String TEAMID = "teamId";
  private static final long serialVersionUID = -1181040016;
  
  public InvalidTeamIdExceptionBase()
  {
    super();
  }
  
  public InvalidTeamIdExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public InvalidTeamIdExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public InvalidTeamIdExceptionBase(java.lang.Throwable cause)
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.InvalidTeamIdException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  public String getTeamId()
  {
    return getValue(TEAMID);
  }
  
  public void validateTeamId()
  {
    this.validateAttribute(TEAMID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getTeamIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.InvalidTeamIdException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(TEAMID);
  }
  
  public void setTeamId(String value)
  {
    if(value == null)
    {
      setValue(TEAMID, "");
    }
    else
    {
      setValue(TEAMID, value);
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
    message = replace(message, "{teamId}", this.getTeamId());
    return message;
  }
  
}
