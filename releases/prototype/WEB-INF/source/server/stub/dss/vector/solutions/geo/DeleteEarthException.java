package dss.vector.solutions.geo;

import dss.vector.solutions.geo.DeleteEarthExceptionBase;

public class DeleteEarthException extends DeleteEarthExceptionBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236717594474L;
  
  public DeleteEarthException()
  {
    super();
  }
  
  public DeleteEarthException(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public DeleteEarthException(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public DeleteEarthException(java.lang.Throwable cause)
  {
    super(cause);
  }
  
}