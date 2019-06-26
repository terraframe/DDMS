package com.runwaysdk.dataaccess.database;

public class AmbiguousVersionException extends RuntimeException
{

  /**
   * 
   */
  private static final long serialVersionUID = -8988677688534958792L;

  public AmbiguousVersionException(String message)
  {
    super(message);
  }

  public AmbiguousVersionException(Throwable cause)
  {
    super(cause);
  }

  public AmbiguousVersionException(String message, Throwable cause)
  {
    super(message, cause);
  }

}
