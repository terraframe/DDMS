package dss.vector.solutions.manager;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;

import dss.vector.solutions.manager.server.ServerStatus;

public class ManagerContextBean
{

  /**
   * PropertyChangeSupport
   */
  private PropertyChangeSupport propertyChangeSupport;

  private LabeledBean           application;

  private boolean               processRunning;

  private ServerStatus          serverStatus;

  public ManagerContextBean()
  {
    this.application = null;
    this.processRunning = false;

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

  public LabeledBean getApplication()
  {
    return application;
  }

  public void setApplication(LabeledBean application)
  {
    this.propertyChangeSupport.firePropertyChange("application", this.application, this.application = application);
  }

  public boolean isProcessRunning()
  {
    return processRunning;
  }

  public void setProcessRunning(boolean processRunning)
  {
    this.propertyChangeSupport.firePropertyChange("processRunning", this.processRunning, this.processRunning = processRunning);
  }

  public ServerStatus getServerStatus()
  {
    return serverStatus;
  }

  public void setServerStatus(ServerStatus serverStatus)
  {
    this.propertyChangeSupport.firePropertyChange("serverStatus", this.serverStatus, this.serverStatus = serverStatus);
  }

  public boolean hasApplication()
  {
    return ( this.application != null );
  }

  public String getApplicationPath()
  {
    return ManagerProperties.getWebappPath() + application.getValue() + File.separator;
  }

  private String getApplicationWebInfPath()
  {
    return this.getApplicationPath() + "WEB-INF" + File.separator;
  }

  public String getApplicationClassesPath()
  {
    return this.getApplicationWebInfPath() + "classes" + File.separator;
  }

  public String getApplicationLibPath()
  {
    return this.getApplicationWebInfPath() + "lib" + File.separator;
  }

  public String getLog4jProperties()
  {
    return this.getApplicationClassesPath() + "log4j.properties";
  }

  public String getCommonProperties()
  {
    return this.getApplicationClassesPath() + "common.properties";
  }

  public String getBackupProfiles()
  {
    return ManagerProperties.getBackupProfiles() + application.getValue() + File.separator;
  }
}
