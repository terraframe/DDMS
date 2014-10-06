package dss.vector.solutions.report;

@com.runwaysdk.business.ClassSignature(hash = -756396055)
public abstract class ReportParameterParseExceptionDTOBase extends com.runwaysdk.business.SmartExceptionDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.report.ReportParameterParseException";
  private static final long serialVersionUID = -756396055;
  
  public ReportParameterParseExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected ReportParameterParseExceptionDTOBase(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public ReportParameterParseExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public ReportParameterParseExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public ReportParameterParseExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public ReportParameterParseExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public ReportParameterParseExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public ReportParameterParseExceptionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String PARAMETERNAME = "parameterName";
  public static java.lang.String PARAMETERVALUE = "parameterValue";
  public String getParameterName()
  {
    return getValue(PARAMETERNAME);
  }
  
  public void setParameterName(String value)
  {
    if(value == null)
    {
      setValue(PARAMETERNAME, "");
    }
    else
    {
      setValue(PARAMETERNAME, value);
    }
  }
  
  public boolean isParameterNameWritable()
  {
    return isWritable(PARAMETERNAME);
  }
  
  public boolean isParameterNameReadable()
  {
    return isReadable(PARAMETERNAME);
  }
  
  public boolean isParameterNameModified()
  {
    return isModified(PARAMETERNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getParameterNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(PARAMETERNAME).getAttributeMdDTO();
  }
  
  public String getParameterValue()
  {
    return getValue(PARAMETERVALUE);
  }
  
  public void setParameterValue(String value)
  {
    if(value == null)
    {
      setValue(PARAMETERVALUE, "");
    }
    else
    {
      setValue(PARAMETERVALUE, value);
    }
  }
  
  public boolean isParameterValueWritable()
  {
    return isWritable(PARAMETERVALUE);
  }
  
  public boolean isParameterValueReadable()
  {
    return isReadable(PARAMETERVALUE);
  }
  
  public boolean isParameterValueModified()
  {
    return isModified(PARAMETERVALUE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getParameterValueMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(PARAMETERVALUE).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{id}", this.getId().toString());
    template = template.replace("{parameterName}", this.getParameterName().toString());
    template = template.replace("{parameterValue}", this.getParameterValue().toString());
    
    return template;
  }
  
}
