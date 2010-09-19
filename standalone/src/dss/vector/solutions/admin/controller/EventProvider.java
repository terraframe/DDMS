package dss.vector.solutions.admin.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.operation.IRunnableWithProgress;

public abstract class EventProvider
{
  private List<IControllerListener> listeners;

  public EventProvider()
  {
    this.listeners = Collections.synchronizedList(new ArrayList<IControllerListener>());
  }

  public void addListener(IControllerListener listener)
  {
    listeners.add(listener);
  }

  public void removeListener(IControllerListener listener)
  {
    listeners.remove(listener);
  }

  protected void fireEvent(IModuleEventStrategy strategy)
  {
    for (IControllerListener listener : listeners)
    {
      strategy.fireEvent(listener);
    }
  }
  
  protected void fireExecuteEvent(final IRunnableWithProgress runnable)
  {
    fireEvent(new IModuleEventStrategy()
    {
      @Override
      public void fireEvent(IControllerListener listener)
      {
        listener.execute(runnable);
      }
    });
  }

  protected void fireErrorEvent(final String msg)
  {
    fireEvent(new IModuleEventStrategy()
    {
      @Override
      public void fireEvent(IControllerListener listener)
      {
        listener.error(msg);
      }
    });
  }
  
  protected void fireServerChange(final boolean status)
  {
    fireEvent(new IModuleEventStrategy()
    {
      @Override
      public void fireEvent(IControllerListener listener)
      {
        listener.serverStateChange(status);
      }
    });
  }



}
