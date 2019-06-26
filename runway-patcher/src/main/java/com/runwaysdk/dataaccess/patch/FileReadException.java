package com.runwaysdk.dataaccess.patch;

public class FileReadException extends RuntimeException
{

  /**
   * 
   */
  private static final long serialVersionUID = -170034476822967788L;

  public FileReadException(String message)
  {
    super(message);
  }

  public FileReadException(Throwable cause)
  {
    super(cause);
  }

  public FileReadException(String message, Throwable cause)
  {
    super(message, cause);
  }

}
