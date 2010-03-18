package dss.vector.solutions;

public class MasterOperationExceptionDTO extends MasterOperationExceptionDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1951135541;
  
  public MasterOperationExceptionDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public MasterOperationExceptionDTO(com.terraframe.mojo.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public MasterOperationExceptionDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public MasterOperationExceptionDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale,java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public MasterOperationExceptionDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public MasterOperationExceptionDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public MasterOperationExceptionDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public MasterOperationExceptionDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
}
