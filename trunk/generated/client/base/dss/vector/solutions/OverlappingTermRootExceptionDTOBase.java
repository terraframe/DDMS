package dss.vector.solutions;

@com.runwaysdk.business.ClassSignature(hash = -656672802)
public abstract class OverlappingTermRootExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.OverlappingTermRootException";
  private static final long serialVersionUID = -656672802;
  
  public OverlappingTermRootExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected OverlappingTermRootExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public OverlappingTermRootExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public OverlappingTermRootExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public OverlappingTermRootExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public OverlappingTermRootExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public OverlappingTermRootExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public OverlappingTermRootExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String BROWSERFIELD = "browserField";
  public static java.lang.String BROWSERROOT = "browserRoot";
  public static java.lang.String ID = "id";
  public String getBrowserField()
  {
    return getValue(BROWSERFIELD);
  }
  
  public void setBrowserField(String value)
  {
    if(value == null)
    {
      setValue(BROWSERFIELD, "");
    }
    else
    {
      setValue(BROWSERFIELD, value);
    }
  }
  
  public boolean isBrowserFieldWritable()
  {
    return isWritable(BROWSERFIELD);
  }
  
  public boolean isBrowserFieldReadable()
  {
    return isReadable(BROWSERFIELD);
  }
  
  public boolean isBrowserFieldModified()
  {
    return isModified(BROWSERFIELD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getBrowserFieldMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(BROWSERFIELD).getAttributeMdDTO();
  }
  
  public String getBrowserRoot()
  {
    return getValue(BROWSERROOT);
  }
  
  public void setBrowserRoot(String value)
  {
    if(value == null)
    {
      setValue(BROWSERROOT, "");
    }
    else
    {
      setValue(BROWSERROOT, value);
    }
  }
  
  public boolean isBrowserRootWritable()
  {
    return isWritable(BROWSERROOT);
  }
  
  public boolean isBrowserRootReadable()
  {
    return isReadable(BROWSERROOT);
  }
  
  public boolean isBrowserRootModified()
  {
    return isModified(BROWSERROOT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getBrowserRootMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(BROWSERROOT).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{browserField}", this.getBrowserField().toString());
    template = template.replace("{browserRoot}", this.getBrowserRoot().toString());
    template = template.replace("{id}", this.getId().toString());
    
    return template;
  }
  
}
