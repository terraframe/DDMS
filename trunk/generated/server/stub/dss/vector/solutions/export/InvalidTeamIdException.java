package dss.vector.solutions.export;

public class InvalidTeamIdException extends InvalidTeamIdExceptionBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1898564595;
  
  public InvalidTeamIdException()
  {
    super();
  }
  
  public InvalidTeamIdException(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public InvalidTeamIdException(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public InvalidTeamIdException(java.lang.Throwable cause)
  {
    super(cause);
  }
  
}
