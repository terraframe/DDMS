package dss.vector.solutions.query;

public class ActionNotAllowedException extends ActionNotAllowedExceptionBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1248464987974L;
  
  public ActionNotAllowedException()
  {
    super();
  }
  
  public ActionNotAllowedException(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public ActionNotAllowedException(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public ActionNotAllowedException(java.lang.Throwable cause)
  {
    super(cause);
  }
  
}
