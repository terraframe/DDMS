package dss.vector.solutions.etl.dhis2;

public class DHIS2ConnectException extends DHIS2ConnectExceptionBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1618624977;
  
  public DHIS2ConnectException()
  {
    super();
  }
  
  public DHIS2ConnectException(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public DHIS2ConnectException(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public DHIS2ConnectException(java.lang.Throwable cause)
  {
    super(cause);
  }
  
}
