package dss.vector.solutions.admin.controller;

import java.lang.reflect.InvocationTargetException;
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
        try
        {
          listener.execute(runnable);
        }
        catch (InvocationTargetException e)
        {
          fireErrorEvent(e.getTargetException().getLocalizedMessage());
        }
        catch (InterruptedException e)
        {
          fireErrorEvent(e.getLocalizedMessage());
        }
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
  
  protected void fireMessageEvent(final String msg)
  {
    fireEvent(new IModuleEventStrategy()
    {
      @Override
      public void fireEvent(IControllerListener listener)
      {
        listener.message(msg);
      }
    });
  }
  
  public void fireServerChange(final boolean status)
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
