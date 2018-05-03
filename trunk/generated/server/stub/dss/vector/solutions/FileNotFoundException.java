package dss.vector.solutions;

public class FileNotFoundException extends FileNotFoundExceptionBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 159395931;
  
  public FileNotFoundException()
  {
    super();
  }
  
  public FileNotFoundException(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public FileNotFoundException(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public FileNotFoundException(java.lang.Throwable cause)
  {
    super(cause);
  }
  
}
