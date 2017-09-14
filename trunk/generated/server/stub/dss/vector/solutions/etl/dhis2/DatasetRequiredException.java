package dss.vector.solutions.etl.dhis2;

public class DatasetRequiredException extends DatasetRequiredExceptionBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1359325468;
  
  public DatasetRequiredException()
  {
    super();
  }
  
  public DatasetRequiredException(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public DatasetRequiredException(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public DatasetRequiredException(java.lang.Throwable cause)
  {
    super(cause);
  }
  
}
