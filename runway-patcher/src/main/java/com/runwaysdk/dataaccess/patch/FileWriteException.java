package com.runwaysdk.dataaccess.patch;

public class FileWriteException extends RuntimeException
{

  /**
   * 
   */
  private static final long serialVersionUID = 7673717967197321150L;

  public FileWriteException(String message)
  {
    super(message);
  }

  public FileWriteException(Throwable cause)
  {
    super(cause);
  }

  public FileWriteException(String message, Throwable cause)
  {
    super(message, cause);
  }

}
