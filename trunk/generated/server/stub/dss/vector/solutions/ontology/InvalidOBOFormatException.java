package dss.vector.solutions.ontology;

public class InvalidOBOFormatException extends InvalidOBOFormatExceptionBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 482512438;
  
  public InvalidOBOFormatException()
  {
    super();
  }
  
  public InvalidOBOFormatException(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public InvalidOBOFormatException(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public InvalidOBOFormatException(java.lang.Throwable cause)
  {
    super(cause);
  }
  
}
