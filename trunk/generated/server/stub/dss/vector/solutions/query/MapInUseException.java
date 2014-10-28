package dss.vector.solutions.query;

public class MapInUseException extends MapInUseExceptionBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 14318246;
  
  public MapInUseException()
  {
    super();
  }
  
  public MapInUseException(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public MapInUseException(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public MapInUseException(java.lang.Throwable cause)
  {
    super(cause);
  }
  
}
