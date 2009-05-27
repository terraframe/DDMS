package dss.vector.solutions;

public class UnknownTermException extends UnknownTermExceptionBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1243454633432L;
  
  public UnknownTermException()
  {
    super();
  }
  
  public UnknownTermException(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public UnknownTermException(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public UnknownTermException(java.lang.Throwable cause)
  {
    super(cause);
  }
  
}
