package dss.vector.solutions.manager;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import dss.vector.solutions.manager.properties.PropertyReader;
import dss.vector.solutions.manager.properties.PropertyWriter;

public class ServerContextBean
{
  private static final String   SESSION_TIME_PROPERTY = "sessionTime";

  public static final String    LOG_PROPERTY          = "log4j.appender.com.runwaysdk.ChainsawSocketAppender.Threshold";

  /**
   * PropertyChangeSupport
   */
  private PropertyChangeSupport propertyChangeSupport;

  private int                   timeout;

  private LogLevel              logLevel;

  private ManagerContextBean    context;

  public ServerContextBean(ManagerContextBean context)
  {
    this.context = context;

    this.timeout = ServerContextBean.getTimeout(context);
    this.logLevel = ServerContextBean.getLogLevel(context);
    this.propertyChangeSupport = new PropertyChangeSupport(this);
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
    new PropertyWriter(context.getLog4jProperties()).write(LOG_PROPERTY, logLevel.name());
    new PropertyWriter(context.getCommonProperties()).write(SESSION_TIME_PROPERTY, new Integer(timeout).toString());
  }

  public static LogLevel getLogLevel(ManagerContextBean context)
  {
    PropertyReader reader = new PropertyReader(context.getLog4jProperties());

    String level = reader.getValue(LOG_PROPERTY);

    return LogLevel.valueOf(level);
  }

  public static int getTimeout(ManagerContextBean context)
  {
    PropertyReader reader = new PropertyReader(context.getCommonProperties());
    String value = reader.getValue(SESSION_TIME_PROPERTY);

    return Integer.parseInt(value);
  }

}
