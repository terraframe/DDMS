package dss.vector.solutions.query;

public class IncidenceProbableSourceExceptionDTO extends IncidenceProbableSourceExceptionDTOBase
 implements com.runwaysdk.generation.loader.Reloadable{
  private static final long serialVersionUID = 1914664880;
  
  public IncidenceProbableSourceExceptionDTO(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public IncidenceProbableSourceExceptionDTO(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public IncidenceProbableSourceExceptionDTO(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public IncidenceProbableSourceExceptionDTO(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale,java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public IncidenceProbableSourceExceptionDTO(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public IncidenceProbableSourceExceptionDTO(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public IncidenceProbableSourceExceptionDTO(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public IncidenceProbableSourceExceptionDTO(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
}
