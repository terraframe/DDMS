package dss.vector.solutions.general;

public class UnknownValueException extends UnknownValueExceptionBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 701667122;
  
  public UnknownValueException()
  {
    super();
  }
  
  public UnknownValueException(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public UnknownValueException(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public UnknownValueException(java.lang.Throwable cause)
  {
    super(cause);
  }
  
}
