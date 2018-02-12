/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
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
    logger.error(message, t);
  }

  public static void fatal(String message)
  {
    logger.error(message);
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
    return logger.isErrorEnabled();
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
