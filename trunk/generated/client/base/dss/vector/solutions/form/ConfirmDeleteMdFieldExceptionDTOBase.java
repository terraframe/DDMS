package dss.vector.solutions.form;

@com.runwaysdk.business.ClassSignature(hash = -498577477)
public abstract class ConfirmDeleteMdFieldExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.form.ConfirmDeleteMdFieldException";
  private static final long serialVersionUID = -498577477;
  
  public ConfirmDeleteMdFieldExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected ConfirmDeleteMdFieldExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public ConfirmDeleteMdFieldExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public ConfirmDeleteMdFieldExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public ConfirmDeleteMdFieldExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public ConfirmDeleteMdFieldExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public ConfirmDeleteMdFieldExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public ConfirmDeleteMdFieldExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String MDFIELDNAME = "mdFieldName";
  public static java.lang.String MDFORMNAME = "mdFormName";
  public String getMdFieldName()
  {
    return getValue(MDFIELDNAME);
  }
  
  public void setMdFieldName(String value)
  {
    if(value == null)
    {
      setValue(MDFIELDNAME, "");
    }
    else
    {
      setValue(MDFIELDNAME, value);
    }
  }
  
  public boolean isMdFieldNameWritable()
  {
    return isWritable(MDFIELDNAME);
  }
  
  public boolean isMdFieldNameReadable()
  {
    return isReadable(MDFIELDNAME);
  }
  
  public boolean isMdFieldNameModified()
  {
    return isModified(MDFIELDNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getMdFieldNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MDFIELDNAME).getAttributeMdDTO();
  }
  
  public String getMdFormName()
  {
    return getValue(MDFORMNAME);
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
  
  public boolean isMdFormNameWritable()
  {
    return isWritable(MDFORMNAME);
  }
  
  public boolean isMdFormNameReadable()
  {
    return isReadable(MDFORMNAME);
  }
  
  public boolean isMdFormNameModified()
  {
    return isModified(MDFORMNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getMdFormNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MDFORMNAME).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{id}", this.getId().toString());
    template = template.replace("{mdFieldName}", this.getMdFieldName().toString());
    template = template.replace("{mdFormName}", this.getMdFormName().toString());
    
    return template;
  }
  
}
