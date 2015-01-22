package com.runwaysdk.manager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Logger
{
  // Made final so the Log is read-only
  private static final Log log;

  // Static initialization happens on load in the vm
  static
  {
    log = LogFactory.getLog("developer");
  }

  public static void debug(Object message, Throwable t)
  {
    log.debug(message, t);
  }

  public static void debug(Object message)
  {
    log.debug(message);
  }

  public static void error(Object message, Throwable t)
  {
    log.error(message, t);
  }

  public static void error(Object message)
  {
    log.error(message);
  }

  public static void fatal(Object message, Throwable t)
  {
    log.fatal(message, t);
  }

  public static void fatal(Object message)
  {
    log.fatal(message);
  }

  public static void info(Object message, Throwable t)
  {
    log.info(message, t);
  }

  public static void info(Object message)
  {
    log.info(message);
  }

  public boolean isDebugEnabled()
  {
    return log.isDebugEnabled();
  }

  public boolean isErrorEnabled()
  {
    return log.isErrorEnabled();
  }

  public boolean isFatalEnabled()
  {
    return log.isFatalEnabled();
  }

  public boolean isInfoEnabled()
  {
    return log.isInfoEnabled();
  }

  public boolean isTraceEnabled()
  {
    return log.isTraceEnabled();
  }

  public boolean isWarnEnabled()
  {
    return log.isWarnEnabled();
  }

  public static void trace(Object message, Throwable t)
  {
    log.trace(message, t);
  }

  public static void trace(Object message)
  {
    log.trace(message);
  }

  public static void warn(Object message, Throwable t)
  {
    log.warn(message, t);
  }

  public static void warn(Object message)
  {
    log.warn(message);
  }

}
