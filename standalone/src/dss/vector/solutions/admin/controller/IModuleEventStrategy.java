package dss.vector.solutions.admin.controller;

public interface IModuleEventStrategy
{
  public void fireEvent(IControllerListener listener);
}
