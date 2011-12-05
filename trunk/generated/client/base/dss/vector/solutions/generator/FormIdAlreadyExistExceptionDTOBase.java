package dss.vector.solutions.generator;

@com.runwaysdk.business.ClassSignature(hash = 1516087653)
public abstract class FormIdAlreadyExistExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.generator.FormIdAlreadyExistException";
  private static final long serialVersionUID = 1516087653;
  
  public FormIdAlreadyExistExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected FormIdAlreadyExistExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public FormIdAlreadyExistExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public FormIdAlreadyExistExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public FormIdAlreadyExistExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public FormIdAlreadyExistExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public FormIdAlreadyExistExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public FormIdAlreadyExistExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ATTRIBUTEDISPLAYLABEL = "attributeDisplayLabel";
  public static java.lang.String ID = "id";
  public static java.lang.String TYPEDISPLAYLABEL = "typeDisplayLabel";
  public static java.lang.String VALUE = "value";
  public String getAttributeDisplayLabel()
  {
    return getValue(ATTRIBUTEDISPLAYLABEL);
  }
  
  public void setAttributeDisplayLabel(String value)
  {
    if(value == null)
    {
      setValue(ATTRIBUTEDISPLAYLABEL, "");
    }
    else
    {
      setValue(ATTRIBUTEDISPLAYLABEL, value);
    }
  }
  
  public boolean isAttributeDisplayLabelWritable()
  {
    return isWritable(ATTRIBUTEDISPLAYLABEL);
  }
  
  public boolean isAttributeDisplayLabelReadable()
  {
    return isReadable(ATTRIBUTEDISPLAYLABEL);
  }
  
  public boolean isAttributeDisplayLabelModified()
  {
    return isModified(ATTRIBUTEDISPLAYLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getAttributeDisplayLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(ATTRIBUTEDISPLAYLABEL).getAttributeMdDTO();
  }
  
  public String getTypeDisplayLabel()
  {
    return getValue(TYPEDISPLAYLABEL);
  }
  
  public void setTypeDisplayLabel(String value)
  {
    if(value == null)
    {
      setValue(TYPEDISPLAYLABEL, "");
    }
    else
    {
      setValue(TYPEDISPLAYLABEL, value);
    }
  }
  
  public boolean isTypeDisplayLabelWritable()
  {
    return isWritable(TYPEDISPLAYLABEL);
  }
  
  public boolean isTypeDisplayLabelReadable()
  {
    return isReadable(TYPEDISPLAYLABEL);
  }
  
  public boolean isTypeDisplayLabelModified()
  {
    return isModified(TYPEDISPLAYLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getTypeDisplayLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(TYPEDISPLAYLABEL).getAttributeMdDTO();
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
    
    template = template.replace("{attributeDisplayLabel}", this.getAttributeDisplayLabel().toString());
    template = template.replace("{id}", this.getId().toString());
    template = template.replace("{typeDisplayLabel}", this.getTypeDisplayLabel().toString());
    template = template.replace("{value}", this.getValue().toString());
    
    return template;
  }
  
}
