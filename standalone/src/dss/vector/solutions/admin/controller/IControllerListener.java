package dss.vector.solutions.admin.controller;

import org.eclipse.jface.operation.IRunnableWithProgress;

public interface IControllerListener
{

  public void execute(IRunnableWithProgress runnable);

  public void error(String msg);

  public void beforeServerStateChange();

  public void serverStateChange(boolean state);
}
