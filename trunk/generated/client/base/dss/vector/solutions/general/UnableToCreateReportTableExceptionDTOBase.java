package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = 1310120673)
public abstract class UnableToCreateReportTableExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.UnableToCreateReportTableException";
  private static final long serialVersionUID = 1310120673;
  
  public UnableToCreateReportTableExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected UnableToCreateReportTableExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public UnableToCreateReportTableExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public UnableToCreateReportTableExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public UnableToCreateReportTableExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public UnableToCreateReportTableExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public UnableToCreateReportTableExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public UnableToCreateReportTableExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String VIEWNAME = "viewName";
  public String getViewName()
  {
    return getValue(VIEWNAME);
  }
  
  public void setViewName(String value)
  {
    if(value == null)
    {
      setValue(VIEWNAME, "");
    }
    else
    {
      setValue(VIEWNAME, value);
    }
  }
  
  public boolean isViewNameWritable()
  {
    return isWritable(VIEWNAME);
  }
  
  public boolean isViewNameReadable()
  {
    return isReadable(VIEWNAME);
  }
  
  public boolean isViewNameModified()
  {
    return isModified(VIEWNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getViewNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(VIEWNAME).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{id}", this.getId().toString());
    template = template.replace("{viewName}", this.getViewName().toString());
    
    return template;
  }
  
}
