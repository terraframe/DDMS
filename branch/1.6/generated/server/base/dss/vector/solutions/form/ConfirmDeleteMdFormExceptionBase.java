package dss.vector.solutions.form;

@com.runwaysdk.business.ClassSignature(hash = 998379778)
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
  public static java.lang.String MDFORMDISPLAYLABEL = "mdFormDisplayLabel";
  private static final long serialVersionUID = 998379778;
  
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
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.form.ConfirmDeleteMdFormException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  public String getMdFormDisplayLabel()
  {
    return getValue(MDFORMDISPLAYLABEL);
  }
  
  public void validateMdFormDisplayLabel()
  {
    this.validateAttribute(MDFORMDISPLAYLABEL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getMdFormDisplayLabelMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.form.ConfirmDeleteMdFormException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(MDFORMDISPLAYLABEL);
  }
  
  public void setMdFormDisplayLabel(String value)
  {
    if(value == null)
    {
      setValue(MDFORMDISPLAYLABEL, "");
    }
    else
    {
      setValue(MDFORMDISPLAYLABEL, value);
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
    message = replace(message, "{mdFormDisplayLabel}", this.getMdFormDisplayLabel());
    return message;
  }
  
}
