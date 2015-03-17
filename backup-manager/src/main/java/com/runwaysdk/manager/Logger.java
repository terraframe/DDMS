package com.runwaysdk.manager;

import org.slf4j.LoggerFactory;

public class Logger
{
  // Made final so the Log is read-only
  private static final org.slf4j.Logger logger;

  // Static initialization happens on load in the vm
  static
  {
    logger = LoggerFactory.getLogger("developer");
  }

  public static void debug(String message, Throwable t)
  {
    logger.debug(message, t);
  }

  public static void debug(String message)
  {
    logger.debug(message);
  }

  public static void error(String message, Throwable t)
  {
    logger.error(message, t);
  }

  public static void error(String message)
  {
    logger.error(message);
  }

  public static void fatal(String message, Throwable t)
  {
    logger.fatal(message, t);
  }

  public static void fatal(String message)
  {
    logger.fatal(message);
  }

  public static void info(String message, Throwable t)
  {
    logger.info(message, t);
  }

  public static void info(String message)
  {
    logger.info(message);
  }

  public boolean isDebugEnabled()
  {
    return logger.isDebugEnabled();
  }

  public boolean isErrorEnabled()
  {
    return logger.isErrorEnabled();
  }

  public boolean isFatalEnabled()
  {
    return logger.isFatalEnabled();
  }

  public boolean isInfoEnabled()
  {
    return logger.isInfoEnabled();
  }

  public boolean isTraceEnabled()
  {
    return logger.isTraceEnabled();
  }

  public boolean isWarnEnabled()
  {
    return logger.isWarnEnabled();
  }

  public static void trace(String message, Throwable t)
  {
    logger.trace(message, t);
  }

  public static void trace(String message)
  {
    logger.trace(message);
  }

  public static void warn(String message, Throwable t)
  {
    logger.warn(message, t);
  }

  public static void warn(String message)
  {
    logger.warn(message);
  }

}
