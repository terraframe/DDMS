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
package dss.vector.solutions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MdssLog
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

  public static boolean isDebugEnabled()
  {
    return log.isDebugEnabled();
  }

  public static boolean isErrorEnabled()
  {
    return log.isErrorEnabled();
  }

  public static boolean isFatalEnabled()
  {
    return log.isFatalEnabled();
  }

  public static boolean isInfoEnabled()
  {
    return log.isInfoEnabled();
  }

  public static boolean isTraceEnabled()
  {
    return log.isTraceEnabled();
  }

  public static boolean isWarnEnabled()
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
