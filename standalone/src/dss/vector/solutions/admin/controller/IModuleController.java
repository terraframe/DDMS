package dss.vector.solutions.admin.controller;

import java.io.File;

import com.runwaysdk.logging.LogLevel;

public interface IModuleController
{
  public void addListener(IControllerListener listener);

  public void removeListener(IControllerListener listener);

  public Integer getTimeout();

  public boolean setTimeout(Integer timeout);

  public void start(Integer timeout);

  public void stop();

  public void backup(File file);

  public void restore(File file);

  public void refresh();

  public void setLogLevel(LogLevel level);

  public LogLevel getLogLevel();

  public boolean isServerUp();

  public void rebuildGeoPaths();

  public void rebuildTermPaths();

  public void deleteGeoPaths();

  public void deleteTermPaths();
}
