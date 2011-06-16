package dss.vector.solutions.admin.controller;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.jface.operation.IRunnableWithProgress;

import com.runwaysdk.logging.LogLevel;

import dss.vector.solutions.admin.model.ServerStatus;

public interface IControllerListener
{
  public void execute(IRunnableWithProgress runnable) throws InvocationTargetException, InterruptedException;

  public void error(String msg);

  public void message(String msg);

  public void beforeServerStateChange(boolean state);

  public void serverStateChange(ServerStatus state);
  
  public void changeLogLevel(LogLevel level);
}
