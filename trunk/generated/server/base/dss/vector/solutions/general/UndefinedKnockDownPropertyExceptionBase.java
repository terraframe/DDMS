package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = 2126785361)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to UndefinedKnockDownPropertyException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class UndefinedKnockDownPropertyExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.UndefinedKnockDownPropertyException";
  public static java.lang.String ID = "id";
  public static java.lang.String INSECTICIDE = "insecticide";
  private static final long serialVersionUID = 2126785361;
  
  public UndefinedKnockDownPropertyExceptionBase()
  {
    super();
  }
  
  public UndefinedKnockDownPropertyExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public UndefinedKnockDownPropertyExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public UndefinedKnockDownPropertyExceptionBase(java.lang.Throwable cause)
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.UndefinedKnockDownPropertyException.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public dss.vector.solutions.general.Insecticide getInsecticide()
  {
    if (getValue(INSECTICIDE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.general.Insecticide.get(getValue(INSECTICIDE));
    }
  }
  
  public void validateInsecticide()
  {
    this.validateAttribute(INSECTICIDE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getInsecticideMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.UndefinedKnockDownPropertyException.CLASS);
    return mdClassIF.definesAttribute(INSECTICIDE);
  }
  
  public void setInsecticide(dss.vector.solutions.general.Insecticide value)
  {
    if(value == null)
    {
      setValue(INSECTICIDE, "");
    }
    else
    {
      setValue(INSECTICIDE, value.getId());
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
    message = replace(message, "{insecticide}", this.getInsecticide());
    return message;
  }
  
}
