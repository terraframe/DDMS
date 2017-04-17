package dss.vector.solutions.ontology;

public class InvalidTermIdException extends InvalidTermIdExceptionBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1255652033427L;
  
  public InvalidTermIdException()
  {
    super();
  }
  
  public InvalidTermIdException(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public InvalidTermIdException(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public InvalidTermIdException(java.lang.Throwable cause)
  {
    super(cause);
  }
  
}
