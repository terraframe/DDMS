package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = -1144312719)
public abstract class EmailConfigurationExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.EmailConfigurationException";
  private static final long serialVersionUID = -1144312719;
  
  public EmailConfigurationExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected EmailConfigurationExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public EmailConfigurationExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public EmailConfigurationExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public EmailConfigurationExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public EmailConfigurationExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public EmailConfigurationExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public EmailConfigurationExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String EXTRA = "extra";
  public static java.lang.String ID = "id";
  public String getExtra()
  {
    return getValue(EXTRA);
  }
  
  public void setExtra(String value)
  {
    if(value == null)
    {
      setValue(EXTRA, "");
    }
    else
    {
      setValue(EXTRA, value);
    }
  }
  
  public boolean isExtraWritable()
  {
    return isWritable(EXTRA);
  }
  
  public boolean isExtraReadable()
  {
    return isReadable(EXTRA);
  }
  
  public boolean isExtraModified()
  {
    return isModified(EXTRA);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getExtraMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(EXTRA).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{extra}", this.getExtra().toString());
    template = template.replace("{id}", this.getId().toString());
    
    return template;
  }
  
}
