package dss.vector.solutions;

public class FileRequiredException extends FileRequiredExceptionBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -193107591;
  
  public FileRequiredException()
  {
    super();
  }
  
  public FileRequiredException(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public FileRequiredException(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public FileRequiredException(java.lang.Throwable cause)
  {
    super(cause);
  }
  
}
