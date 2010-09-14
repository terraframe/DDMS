package dss.vector.solutions.admin.controller;

import java.io.File;

import dss.vector.solutions.admin.MDSSModule;

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
  
  public void pollServerState();

  public void setModule(MDSSModule module);
}
