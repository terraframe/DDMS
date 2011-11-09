package dss.vector.solutions.form;

@com.runwaysdk.business.ClassSignature(hash = 1413331163)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ConfirmDeleteMdFieldException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class ConfirmDeleteMdFieldExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.form.ConfirmDeleteMdFieldException";
  public static java.lang.String ID = "id";
  public static java.lang.String MDFIELDDISPLAYLABEL = "mdFieldDisplayLabel";
  public static java.lang.String MDFORMDISPLAYLABEL = "mdFormDisplayLabel";
  private static final long serialVersionUID = 1413331163;
  
  public ConfirmDeleteMdFieldExceptionBase()
  {
    super();
  }
  
  public ConfirmDeleteMdFieldExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public ConfirmDeleteMdFieldExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public ConfirmDeleteMdFieldExceptionBase(java.lang.Throwable cause)
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.form.ConfirmDeleteMdFieldException.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public String getMdFieldDisplayLabel()
  {
    return getValue(MDFIELDDISPLAYLABEL);
  }
  
  public void validateMdFieldDisplayLabel()
  {
    this.validateAttribute(MDFIELDDISPLAYLABEL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getMdFieldDisplayLabelMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.form.ConfirmDeleteMdFieldException.CLASS);
    return mdClassIF.definesAttribute(MDFIELDDISPLAYLABEL);
  }
  
  public void setMdFieldDisplayLabel(String value)
  {
    if(value == null)
    {
      setValue(MDFIELDDISPLAYLABEL, "");
    }
    else
    {
      setValue(MDFIELDDISPLAYLABEL, value);
    }
  }
  
  public String getMdFormDisplayLabel()
  {
    return getValue(MDFORMDISPLAYLABEL);
  }
  
  public void validateMdFormDisplayLabel()
  {
    this.validateAttribute(MDFORMDISPLAYLABEL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getMdFormDisplayLabelMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.form.ConfirmDeleteMdFieldException.CLASS);
    return mdClassIF.definesAttribute(MDFORMDISPLAYLABEL);
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
    message = replace(message, "{mdFieldDisplayLabel}", this.getMdFieldDisplayLabel());
    message = replace(message, "{mdFormDisplayLabel}", this.getMdFormDisplayLabel());
    return message;
  }
  
}
