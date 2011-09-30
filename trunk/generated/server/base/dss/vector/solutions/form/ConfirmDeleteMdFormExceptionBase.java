package dss.vector.solutions.form;

@com.runwaysdk.business.ClassSignature(hash = 676347561)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ConfirmDeleteMdFormException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class ConfirmDeleteMdFormExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.form.ConfirmDeleteMdFormException";
  public static java.lang.String ID = "id";
  public static java.lang.String MDFORMNAME = "mdFormName";
  private static final long serialVersionUID = 676347561;
  
  public ConfirmDeleteMdFormExceptionBase()
  {
    super();
  }
  
  public ConfirmDeleteMdFormExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public ConfirmDeleteMdFormExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public ConfirmDeleteMdFormExceptionBase(java.lang.Throwable cause)
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.form.ConfirmDeleteMdFormException.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public String getMdFormName()
  {
    return getValue(MDFORMNAME);
  }
  
  public void validateMdFormName()
  {
    this.validateAttribute(MDFORMNAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getMdFormNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.form.ConfirmDeleteMdFormException.CLASS);
    return mdClassIF.definesAttribute(MDFORMNAME);
  }
  
  public void setMdFormName(String value)
  {
    if(value == null)
    {
      setValue(MDFORMNAME, "");
    }
    else
    {
      setValue(MDFORMNAME, value);
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
    message = replace(message, "{mdFormName}", this.getMdFormName());
    return message;
  }
  
}
