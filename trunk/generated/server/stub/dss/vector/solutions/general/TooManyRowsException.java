package dss.vector.solutions.general;

public class TooManyRowsException extends TooManyRowsExceptionBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 354710738;
  
  public TooManyRowsException()
  {
    super();
  }
  
  public TooManyRowsException(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public TooManyRowsException(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public TooManyRowsException(java.lang.Throwable cause)
  {
    super(cause);
  }
  
}
