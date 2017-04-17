package dss.vector.solutions.irs;

public class InvalidLeaderIDException extends InvalidLeaderIDExceptionBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 843373565;
  
  public InvalidLeaderIDException()
  {
    super();
  }
  
  public InvalidLeaderIDException(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public InvalidLeaderIDException(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public InvalidLeaderIDException(java.lang.Throwable cause)
  {
    super(cause);
  }
  
}
