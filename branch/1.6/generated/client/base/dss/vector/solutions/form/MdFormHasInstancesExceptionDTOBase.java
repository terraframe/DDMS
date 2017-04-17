package dss.vector.solutions.form;

@com.runwaysdk.business.ClassSignature(hash = 1296023939)
public abstract class MdFormHasInstancesExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.form.MdFormHasInstancesException";
  private static final long serialVersionUID = 1296023939;
  
  public MdFormHasInstancesExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected MdFormHasInstancesExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public MdFormHasInstancesExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public MdFormHasInstancesExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public MdFormHasInstancesExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public MdFormHasInstancesExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public MdFormHasInstancesExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public MdFormHasInstancesExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String MDFORMDISPLAYLABEL = "mdFormDisplayLabel";
  public String getMdFormDisplayLabel()
  {
    return getValue(MDFORMDISPLAYLABEL);
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
  
  public boolean isMdFormDisplayLabelWritable()
  {
    return isWritable(MDFORMDISPLAYLABEL);
  }
  
  public boolean isMdFormDisplayLabelReadable()
  {
    return isReadable(MDFORMDISPLAYLABEL);
  }
  
  public boolean isMdFormDisplayLabelModified()
  {
    return isModified(MDFORMDISPLAYLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getMdFormDisplayLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MDFORMDISPLAYLABEL).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{id}", this.getId().toString());
    template = template.replace("{mdFormDisplayLabel}", this.getMdFormDisplayLabel().toString());
    
    return template;
  }
  
}
