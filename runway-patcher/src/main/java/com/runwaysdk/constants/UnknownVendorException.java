package com.runwaysdk.constants;

public class UnknownVendorException extends RuntimeException
{

  /**
   * 
   */
  private static final long serialVersionUID = 2441067211358391714L;

  public UnknownVendorException(String msg)
  {
    super(msg);
  }

  public UnknownVendorException(Throwable throwable)
  {
    super(throwable);
  }

  public UnknownVendorException(String msg, Throwable throwable)
  {
    super(msg, throwable);
  }

}
