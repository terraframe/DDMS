package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = -387721109)
public abstract class UnableToDeleteReportTableExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.UnableToDeleteReportTableException";
  private static final long serialVersionUID = -387721109;
  
  public UnableToDeleteReportTableExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected UnableToDeleteReportTableExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public UnableToDeleteReportTableExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public UnableToDeleteReportTableExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public UnableToDeleteReportTableExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public UnableToDeleteReportTableExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public UnableToDeleteReportTableExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public UnableToDeleteReportTableExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String MODULENAME = "moduleName";
  public String getModuleName()
  {
    return getValue(MODULENAME);
  }
  
  public void setModuleName(String value)
  {
    if(value == null)
    {
      setValue(MODULENAME, "");
    }
    else
    {
      setValue(MODULENAME, value);
    }
  }
  
  public boolean isModuleNameWritable()
  {
    return isWritable(MODULENAME);
  }
  
  public boolean isModuleNameReadable()
  {
    return isReadable(MODULENAME);
  }
  
  public boolean isModuleNameModified()
  {
    return isModified(MODULENAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getModuleNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MODULENAME).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{id}", this.getId().toString());
    template = template.replace("{moduleName}", this.getModuleName().toString());
    
    return template;
  }
  
}
