package dss.vector.solutions.irs;

public class NotASprayEntityExceptionDTO extends NotASprayEntityExceptionDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1256927946004L;
  
  public NotASprayEntityExceptionDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public NotASprayEntityExceptionDTO(com.terraframe.mojo.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public NotASprayEntityExceptionDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public NotASprayEntityExceptionDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale,java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public NotASprayEntityExceptionDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public NotASprayEntityExceptionDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public NotASprayEntityExceptionDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public NotASprayEntityExceptionDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
}
