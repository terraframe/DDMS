package dss.vector.solutions.report;

public class ReportParameterParseExceptionDTO extends ReportParameterParseExceptionDTOBase
 implements com.runwaysdk.generation.loader.Reloadable{
  private static final long serialVersionUID = -107560596;
  
  public ReportParameterParseExceptionDTO(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public ReportParameterParseExceptionDTO(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public ReportParameterParseExceptionDTO(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public ReportParameterParseExceptionDTO(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale,java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public ReportParameterParseExceptionDTO(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public ReportParameterParseExceptionDTO(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public ReportParameterParseExceptionDTO(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public ReportParameterParseExceptionDTO(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
}
