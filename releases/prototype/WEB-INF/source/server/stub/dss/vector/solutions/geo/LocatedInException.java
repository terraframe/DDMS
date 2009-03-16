package dss.vector.solutions.geo;

import dss.vector.solutions.geo.LocatedInExceptionBase;

public class LocatedInException extends LocatedInExceptionBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236461876637L;
  
  public LocatedInException()
  {
    super();
  }
  
  public LocatedInException(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public LocatedInException(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public LocatedInException(java.lang.Throwable cause)
  {
    super(cause);
  }
  
}