package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = 921625903)
public abstract class UnknownValueExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.UnknownValueException";
  private static final long serialVersionUID = 921625903;
  
  public UnknownValueExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected UnknownValueExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public UnknownValueExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public UnknownValueExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public UnknownValueExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public UnknownValueExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public UnknownValueExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public UnknownValueExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ATTRIBUTELABEL = "attributeLabel";
  public static java.lang.String ID = "id";
  public static java.lang.String TYPELABEL = "typeLabel";
  public static java.lang.String VALUE = "value";
  public String getAttributeLabel()
  {
    return getValue(ATTRIBUTELABEL);
  }
  
  public void setAttributeLabel(String value)
  {
    if(value == null)
    {
      setValue(ATTRIBUTELABEL, "");
    }
    else
    {
      setValue(ATTRIBUTELABEL, value);
    }
  }
  
  public boolean isAttributeLabelWritable()
  {
    return isWritable(ATTRIBUTELABEL);
  }
  
  public boolean isAttributeLabelReadable()
  {
    return isReadable(ATTRIBUTELABEL);
  }
  
  public boolean isAttributeLabelModified()
  {
    return isModified(ATTRIBUTELABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getAttributeLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(ATTRIBUTELABEL).getAttributeMdDTO();
  }
  
  public String getTypeLabel()
  {
    return getValue(TYPELABEL);
  }
  
  public void setTypeLabel(String value)
  {
    if(value == null)
    {
      setValue(TYPELABEL, "");
    }
    else
    {
      setValue(TYPELABEL, value);
    }
  }
  
  public boolean isTypeLabelWritable()
  {
    return isWritable(TYPELABEL);
  }
  
  public boolean isTypeLabelReadable()
  {
    return isReadable(TYPELABEL);
  }
  
  public boolean isTypeLabelModified()
  {
    return isModified(TYPELABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getTypeLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(TYPELABEL).getAttributeMdDTO();
  }
  
  public String getValue()
  {
    return getValue(VALUE);
  }
  
  public void setValue(String value)
  {
    if(value == null)
    {
      setValue(VALUE, "");
    }
    else
    {
      setValue(VALUE, value);
    }
  }
  
  public boolean isValueWritable()
  {
    return isWritable(VALUE);
  }
  
  public boolean isValueReadable()
  {
    return isReadable(VALUE);
  }
  
  public boolean isValueModified()
  {
    return isModified(VALUE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getValueMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(VALUE).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{attributeLabel}", this.getAttributeLabel().toString());
    template = template.replace("{id}", this.getId().toString());
    template = template.replace("{typeLabel}", this.getTypeLabel().toString());
    template = template.replace("{value}", this.getValue().toString());
    
    return template;
  }
  
}
