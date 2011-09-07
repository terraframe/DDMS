package dss.vector.solutions;


public class PropertyAllreadyExistsException extends PropertyAllreadyExistsExceptionBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236023112933L;
  
  public PropertyAllreadyExistsException()
  {
    super();
  }
  
  public PropertyAllreadyExistsException(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public PropertyAllreadyExistsException(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public PropertyAllreadyExistsException(java.lang.Throwable cause)
  {
    super(cause);
  }
  
}
