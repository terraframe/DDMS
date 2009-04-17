package dss.vector.solutions;

public class RequiredParameterException extends RequiredParameterExceptionBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1239816202982L;
  
  public RequiredParameterException()
  {
    super();
  }
  
  public RequiredParameterException(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public RequiredParameterException(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public RequiredParameterException(java.lang.Throwable cause)
  {
    super(cause);
  }
  
}
