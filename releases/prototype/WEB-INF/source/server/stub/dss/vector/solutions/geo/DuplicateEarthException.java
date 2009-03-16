package dss.vector.solutions.geo;

import dss.vector.solutions.geo.DuplicateEarthExceptionBase;

public class DuplicateEarthException extends DuplicateEarthExceptionBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236717610731L;
  
  public DuplicateEarthException()
  {
    super();
  }
  
  public DuplicateEarthException(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public DuplicateEarthException(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public DuplicateEarthException(java.lang.Throwable cause)
  {
    super(cause);
  }
  
}