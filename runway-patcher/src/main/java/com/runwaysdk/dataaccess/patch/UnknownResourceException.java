package com.runwaysdk.dataaccess.patch;

public class UnknownResourceException extends RuntimeException
{

  /**
   * 
   */
  private static final long serialVersionUID = 8465006177560060795L;

  public UnknownResourceException(String msg)
  {
    super(msg);
  }

  public UnknownResourceException(Throwable throwable)
  {
    super(throwable);
  }

  public UnknownResourceException(String msg, Throwable throwable)
  {
    super(msg, throwable);
  }

}
