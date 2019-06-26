package com.runwaysdk.dataaccess.database;

public class DatabaseException extends RuntimeException
{
  /**
   * 
   */
  private static final long serialVersionUID = -6520324553346126738L;

  public DatabaseException()
  {
    super();
  }

  public DatabaseException(String message, Throwable cause)
  {
    super(message, cause);
  }

  public DatabaseException(String message)
  {
    super(message);
  }

  public DatabaseException(Throwable cause)
  {
    super(cause);
  }
}
