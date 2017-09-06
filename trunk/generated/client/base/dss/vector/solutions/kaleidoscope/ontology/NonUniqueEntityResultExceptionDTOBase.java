package dss.vector.solutions.kaleidoscope.ontology;

@com.runwaysdk.business.ClassSignature(hash = -1593981192)
public abstract class NonUniqueEntityResultExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.kaleidoscope.ontology.NonUniqueEntityResultException";
  private static final long serialVersionUID = -1593981192;
  
  public NonUniqueEntityResultExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected NonUniqueEntityResultExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public NonUniqueEntityResultExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public NonUniqueEntityResultExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public NonUniqueEntityResultExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public NonUniqueEntityResultExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public NonUniqueEntityResultExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public NonUniqueEntityResultExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String LABEL = "label";
  public static java.lang.String PARENT = "parent";
  public static java.lang.String UNIVERSAL = "universal";
  public String getLabel()
  {
    return getValue(LABEL);
  }
  
  public void setLabel(String value)
  {
    if(value == null)
    {
      setValue(LABEL, "");
    }
    else
    {
      setValue(LABEL, value);
    }
  }
  
  public boolean isLabelWritable()
  {
    return isWritable(LABEL);
  }
  
  public boolean isLabelReadable()
  {
    return isReadable(LABEL);
  }
  
  public boolean isLabelModified()
  {
    return isModified(LABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(LABEL).getAttributeMdDTO();
  }
  
  public String getParent()
  {
    return getValue(PARENT);
  }
  
  public void setParent(String value)
  {
    if(value == null)
    {
      setValue(PARENT, "");
    }
    else
    {
      setValue(PARENT, value);
    }
  }
  
  public boolean isParentWritable()
  {
    return isWritable(PARENT);
  }
  
  public boolean isParentReadable()
  {
    return isReadable(PARENT);
  }
  
  public boolean isParentModified()
  {
    return isModified(PARENT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getParentMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(PARENT).getAttributeMdDTO();
  }
  
  public String getUniversal()
  {
    return getValue(UNIVERSAL);
  }
  
  public void setUniversal(String value)
  {
    if(value == null)
    {
      setValue(UNIVERSAL, "");
    }
    else
    {
      setValue(UNIVERSAL, value);
    }
  }
  
  public boolean isUniversalWritable()
  {
    return isWritable(UNIVERSAL);
  }
  
  public boolean isUniversalReadable()
  {
    return isReadable(UNIVERSAL);
  }
  
  public boolean isUniversalModified()
  {
    return isModified(UNIVERSAL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getUniversalMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(UNIVERSAL).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{id}", this.getId().toString());
    template = template.replace("{label}", this.getLabel().toString());
    template = template.replace("{parent}", this.getParent().toString());
    template = template.replace("{universal}", this.getUniversal().toString());
    
    return template;
  }
  
}
