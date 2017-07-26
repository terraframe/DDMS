package dss.vector.solutions.etl.dhis2.response;

public class DHIS2UnexpectedResponseException extends DHIS2UnexpectedResponseExceptionBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -382435011;
  
  public DHIS2UnexpectedResponseException()
  {
    super();
  }
  
  public DHIS2UnexpectedResponseException(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public DHIS2UnexpectedResponseException(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public DHIS2UnexpectedResponseException(java.lang.Throwable cause)
  {
    super(cause);
  }
  
}
