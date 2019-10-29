package dss.vector.solutions.general;

public class InvalidEmailAddressException extends InvalidEmailAddressExceptionBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1759700403;
  
  public InvalidEmailAddressException()
  {
    super();
  }
  
  public InvalidEmailAddressException(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public InvalidEmailAddressException(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public InvalidEmailAddressException(java.lang.Throwable cause)
  {
    super(cause);
  }
  
}
