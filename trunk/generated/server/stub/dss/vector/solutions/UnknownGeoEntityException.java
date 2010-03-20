package dss.vector.solutions;

public class UnknownGeoEntityException extends UnknownGeoEntityExceptionBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1243447521432L;
  
  public UnknownGeoEntityException()
  {
    super();
  }
  
  public UnknownGeoEntityException(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public UnknownGeoEntityException(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public UnknownGeoEntityException(java.lang.Throwable cause)
  {
    super(cause);
  }
  
}
