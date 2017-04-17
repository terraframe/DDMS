package dss.vector.solutions.generator;

@com.runwaysdk.business.ClassSignature(hash = -109265764)
public abstract class UnsupportedImportTypeExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.generator.UnsupportedImportTypeException";
  private static final long serialVersionUID = -109265764;
  
  public UnsupportedImportTypeExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected UnsupportedImportTypeExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public UnsupportedImportTypeExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public UnsupportedImportTypeExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public UnsupportedImportTypeExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public UnsupportedImportTypeExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public UnsupportedImportTypeExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public UnsupportedImportTypeExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CLASSTYPE = "classType";
  public static java.lang.String ID = "id";
  public String getClassType()
  {
    return getValue(CLASSTYPE);
  }
  
  public void setClassType(String value)
  {
    if(value == null)
    {
      setValue(CLASSTYPE, "");
    }
    else
    {
      setValue(CLASSTYPE, value);
    }
  }
  
  public boolean isClassTypeWritable()
  {
    return isWritable(CLASSTYPE);
  }
  
  public boolean isClassTypeReadable()
  {
    return isReadable(CLASSTYPE);
  }
  
  public boolean isClassTypeModified()
  {
    return isModified(CLASSTYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getClassTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(CLASSTYPE).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{classType}", this.getClassType().toString());
    template = template.replace("{id}", this.getId().toString());
    
    return template;
  }
  
}
