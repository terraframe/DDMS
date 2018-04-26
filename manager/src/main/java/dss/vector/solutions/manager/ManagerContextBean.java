/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.manager;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;

import dss.vector.solutions.manager.properties.ManagerProperties;
import dss.vector.solutions.manager.server.ServerStatus;

public class ManagerContextBean
{

  /**
   * PropertyChangeSupport
   */
  private PropertyChangeSupport propertyChangeSupport;

  private String                application;

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

  public String getApplication()
  {
    return application;
  }

  public void setApplication(String application)
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

  public String getApplicationClassesPath()
  {
    return ManagerContextBean.getApplicationClassesPath(this.application);
  }

  public String getApplicationPath()
  {
    return ManagerContextBean.getApplicationPath(this.application);
  }

  public String getODKApplicationPath()
  {
    return ManagerContextBean.getODKApplicationPath(this.application);
  }

  public String getApplicationLibPath()
  {
    return ManagerContextBean.getApplicationLibPath(this.application);
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
    return ManagerProperties.getBackupProfiles() + application + File.separator;
  }

  public String getInstallProperties()
  {
    return this.getApplicationClassesPath() + "install.properties";
  }

  public String getOriginalVersion()
  {
    return ManagerContextBean.getApplicationWebInfPath(application) + "originalVersion.jsp";
  }

  public String getBackupProfilesPath()
  {
    return ManagerContextBean.getBackupProfilesPath(application);
  }

  public String getDatabaseProperties()
  {
    return ManagerContextBean.getApplicationClassesPath(this.application) + "database.properties";
  }

  public String getAnalyticsProperties()
  {
    return ManagerContextBean.getApplicationClassesPath(this.application) + "analytics.properties";
  }

  private static String getBackupProfilesPath(String application)
  {
    return ManagerProperties.getBackupProfiles() + application + File.separator;
  }

  private static String getApplicationPath(String application)
  {
    return ManagerProperties.getWebappPath() + application + File.separator;
  }

  private static String getODKApplicationPath(String application)
  {
    return ManagerProperties.getWebappPath() + application + "Mobile" + File.separator;
  }

  private static String getApplicationWebInfPath(String application)
  {
    return ManagerContextBean.getApplicationPath(application) + "WEB-INF" + File.separator;
  }

  public static String getApplicationClassesPath(String application)
  {
    return ManagerContextBean.getApplicationWebInfPath(application) + "classes" + File.separator;
  }

  public static String getApplicationLibPath(String application)
  {
    return ManagerContextBean.getApplicationWebInfPath(application) + "lib" + File.separator;
  }
}
