package dss.vector.solutions.etl.dhis2;

public class TooManyDatesException extends TooManyDatesExceptionBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1425136837;
  
  public TooManyDatesException()
  {
    super();
  }
  
  public TooManyDatesException(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public TooManyDatesException(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public TooManyDatesException(java.lang.Throwable cause)
  {
    super(cause);
  }
  
}
