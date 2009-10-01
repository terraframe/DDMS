package dss.vector.solutions.ontology;

public class ConfirmDeleteTermExceptionDTO extends ConfirmDeleteTermExceptionDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1254344592565L;
  
  public ConfirmDeleteTermExceptionDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public ConfirmDeleteTermExceptionDTO(com.terraframe.mojo.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public ConfirmDeleteTermExceptionDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public ConfirmDeleteTermExceptionDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale,java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public ConfirmDeleteTermExceptionDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public ConfirmDeleteTermExceptionDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public ConfirmDeleteTermExceptionDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public ConfirmDeleteTermExceptionDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
}
