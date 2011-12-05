package dss.vector.solutions.generator;

public class FormIdAlreadyExistException extends FormIdAlreadyExistExceptionBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 203728680;
  
  public FormIdAlreadyExistException()
  {
    super();
  }
  
  public FormIdAlreadyExistException(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public FormIdAlreadyExistException(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public FormIdAlreadyExistException(java.lang.Throwable cause)
  {
    super(cause);
  }
  
}
