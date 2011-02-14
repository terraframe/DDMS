package dss.vector.solutions.export;

public class InsufficientPatientDataException extends InsufficientPatientDataExceptionBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 899781923;
  
  public InsufficientPatientDataException()
  {
    super();
  }
  
  public InsufficientPatientDataException(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public InsufficientPatientDataException(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public InsufficientPatientDataException(java.lang.Throwable cause)
  {
    super(cause);
  }
  
}
