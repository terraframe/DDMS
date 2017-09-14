package dss.vector.solutions.etl.dhis2;

public class DatasetRequiredExceptionDTO extends DatasetRequiredExceptionDTOBase
 implements com.runwaysdk.generation.loader.Reloadable{
  private static final long serialVersionUID = 648361884;
  
  public DatasetRequiredExceptionDTO(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public DatasetRequiredExceptionDTO(com.runwaysdk.business.ExceptionDTO exceptionDTO)
  {
    super(exceptionDTO);
  }
  
  public DatasetRequiredExceptionDTO(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale)
  {
    super(clientRequest, locale);
  }
  
  public DatasetRequiredExceptionDTO(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale,java.lang.String developerMessage)
  {
    super(clientRequest, locale, developerMessage);
  }
  
  public DatasetRequiredExceptionDTO(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.Throwable cause)
  {
    super(clientRequest, locale, cause);
  }
  
  public DatasetRequiredExceptionDTO(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Locale locale, java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(clientRequest, locale, developerMessage, cause);
  }
  
  public DatasetRequiredExceptionDTO(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.Throwable cause)
  {
    super(clientRequest, cause);
  }
  
  public DatasetRequiredExceptionDTO(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String msg, java.lang.Throwable cause)
  {
    super(clientRequest, msg, cause);
  }
  
}
