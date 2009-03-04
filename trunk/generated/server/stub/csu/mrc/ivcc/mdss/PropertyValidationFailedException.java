package csu.mrc.ivcc.mdss;

import csu.mrc.ivcc.mdss.PropertyValidationFailedExceptionBase;

public class PropertyValidationFailedException extends PropertyValidationFailedExceptionBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236023127645L;
  
  public PropertyValidationFailedException()
  {
    super();
  }
  
  public PropertyValidationFailedException(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public PropertyValidationFailedException(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public PropertyValidationFailedException(java.lang.Throwable cause)
  {
    super(cause);
  }
  
}
