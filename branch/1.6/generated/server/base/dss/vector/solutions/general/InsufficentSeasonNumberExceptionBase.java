package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = 1323680988)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InsufficentSeasonNumberException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class InsufficentSeasonNumberExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.InsufficentSeasonNumberException";
  public static java.lang.String ID = "id";
  public static java.lang.String NUMBEROFSEASONS = "numberOfSeasons";
  private static final long serialVersionUID = 1323680988;
  
  public InsufficentSeasonNumberExceptionBase()
  {
    super();
  }
  
  public InsufficentSeasonNumberExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public InsufficentSeasonNumberExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public InsufficentSeasonNumberExceptionBase(java.lang.Throwable cause)
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.InsufficentSeasonNumberException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  public Integer getNumberOfSeasons()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBEROFSEASONS));
  }
  
  public void validateNumberOfSeasons()
  {
    this.validateAttribute(NUMBEROFSEASONS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getNumberOfSeasonsMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.InsufficentSeasonNumberException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(NUMBEROFSEASONS);
  }
  
  public void setNumberOfSeasons(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBEROFSEASONS, "");
    }
    else
    {
      setValue(NUMBEROFSEASONS, java.lang.Integer.toString(value));
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
    message = replace(message, "{numberOfSeasons}", this.getNumberOfSeasons());
    return message;
  }
  
}
