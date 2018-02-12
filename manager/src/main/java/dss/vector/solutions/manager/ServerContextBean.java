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
package dss.vector.solutions.manager;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import dss.vector.solutions.manager.properties.PropertyReader;
import dss.vector.solutions.manager.properties.PropertyWriter;

public class ServerContextBean
{
  private static final String   SESSION_TIME_PROPERTY = "sessionTime";

  public static final String    CHAINSAW_LOG_PROPERTY = "log4j.appender.com.runwaysdk.ChainsawSocketAppender.Threshold";

  public static final String    LOG_PROPERTY          = "log4j.rootLogger";

  /**
   * PropertyChangeSupport
   */
  private PropertyChangeSupport propertyChangeSupport;

  private int                   timeout;

  private LogLevel              logLevel;

  private ManagerContextBean    context;

  private String                suffix;

  public ServerContextBean(ManagerContextBean context)
  {
    this.context = context;

    this.timeout = ServerContextBean.getTimeout(context);
    this.logLevel = ServerContextBean.getLogLevel(context);
    this.propertyChangeSupport = new PropertyChangeSupport(this);
    this.suffix = ServerContextBean.getRootLoggerSuffix(context);
  }

  public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener)
  {
    propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
  }

  public void removePropertyChangeListener(PropertyChangeListener listener)
  {
    propertyChangeSupport.removePropertyChangeListener(listener);
  }

  public int getTimeout()
  {
    return this.timeout;
  }

  public void setTimeout(int timeout)
  {
    this.propertyChangeSupport.firePropertyChange("timeout", this.timeout, this.timeout = timeout);
  }

  public LogLevel getLogLevel()
  {
    return this.logLevel;
  }

  public void setLogLevel(LogLevel logLevel)
  {
    this.propertyChangeSupport.firePropertyChange("logLevel", this.logLevel, this.logLevel = logLevel);
  }

  public void save()
  {
    new PropertyWriter(context.getLog4jProperties()).write(LOG_PROPERTY, logLevel.name() + suffix);
    new PropertyWriter(context.getLog4jProperties()).write(CHAINSAW_LOG_PROPERTY, logLevel.name());
    new PropertyWriter(context.getCommonProperties()).write(SESSION_TIME_PROPERTY, new Integer(timeout).toString());
  }

  public static LogLevel getLogLevel(ManagerContextBean context)
  {
    PropertyReader reader = new PropertyReader(context.getLog4jProperties());

    String level = reader.getValue(CHAINSAW_LOG_PROPERTY);

    return LogLevel.valueOf(level);
  }

  public static String getRootLoggerSuffix(ManagerContextBean context)
  {
    PropertyReader reader = new PropertyReader(context.getLog4jProperties());

    StringBuffer buffer = new StringBuffer();

    String rootLogger = reader.getValue(LOG_PROPERTY);
    String[] loggers = rootLogger.split(",");

    for (int i = 1; i < loggers.length; i++)
    {
      buffer.append(", " + loggers[i].trim());
    }

    return buffer.toString();
  }

  public static int getTimeout(ManagerContextBean context)
  {
    PropertyReader reader = new PropertyReader(context.getCommonProperties());
    String value = reader.getValue(SESSION_TIME_PROPERTY);

    return Integer.parseInt(value);
  }

}
