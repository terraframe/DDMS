package dss.vector.solutions.etl.dhis2;

public class InvalidFieldException extends InvalidFieldExceptionBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1006974168;
  
  public InvalidFieldException()
  {
    super();
  }
  
  public InvalidFieldException(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public InvalidFieldException(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public InvalidFieldException(java.lang.Throwable cause)
  {
    super(cause);
  }
  
}
