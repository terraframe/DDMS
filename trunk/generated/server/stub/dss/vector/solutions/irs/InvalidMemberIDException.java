package dss.vector.solutions.irs;

public class InvalidMemberIDException extends InvalidMemberIDExceptionBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 2048148829;
  
  public InvalidMemberIDException()
  {
    super();
  }
  
  public InvalidMemberIDException(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public InvalidMemberIDException(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public InvalidMemberIDException(java.lang.Throwable cause)
  {
    super(cause);
  }
  
}
