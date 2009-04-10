package dss.vector.solutions;

public class InvalidEpiDateDTO extends InvalidEpiDateDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1239294388698L;
  
  public InvalidEpiDateDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public InvalidEpiDateDTO(com.terraframe.mojo.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public InvalidEpiDateDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public InvalidEpiDateDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale,java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public InvalidEpiDateDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public InvalidEpiDateDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public InvalidEpiDateDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public InvalidEpiDateDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
}
