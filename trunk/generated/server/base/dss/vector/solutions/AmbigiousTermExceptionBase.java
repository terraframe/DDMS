package dss.vector.solutions;

@com.runwaysdk.business.ClassSignature(hash = 1444064436)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AmbigiousTermException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class AmbigiousTermExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.AmbigiousTermException";
  public static java.lang.String ID = "id";
  public static java.lang.String TERMNAME = "termName";
  private static final long serialVersionUID = 1444064436;
  
  public AmbigiousTermExceptionBase()
  {
    super();
  }
  
  public AmbigiousTermExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public AmbigiousTermExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public AmbigiousTermExceptionBase(java.lang.Throwable cause)
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.AmbigiousTermException.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public String getTermName()
  {
    return getValue(TERMNAME);
  }
  
  public void validateTermName()
  {
    this.validateAttribute(TERMNAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getTermNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.AmbigiousTermException.CLASS);
    return mdClassIF.definesAttribute(TERMNAME);
  }
  
  public void setTermName(String value)
  {
    if(value == null)
    {
      setValue(TERMNAME, "");
    }
    else
    {
      setValue(TERMNAME, value);
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
    message = replace(message, "{termName}", this.getTermName());
    return message;
  }
  
}