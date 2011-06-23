package dss.vector.solutions.manager.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class EventProvider
{
  private List<IServerListener> listeners;

  public EventProvider()
  {
    this.listeners = Collections.synchronizedList(new ArrayList<IServerListener>());
  }

  public void addListener(IServerListener listener)
  {
    listeners.add(listener);
  }

  public void removeListener(IServerListener listener)
  {
    listeners.remove(listener);
  }

  protected void fireErrorEvent(final String msg)
  {
    for (IServerListener listener : listeners)
    {
      listener.error(msg);
    }
  }
  
  protected void fireErrorEvent(Throwable t)
  {
    for (IServerListener listener : listeners)
    {
      listener.error(t);
    }
  }
  
  public void fireServerChange(ServerStatus status)
  {
    for (IServerListener listener : listeners)
    {
      listener.serverStateChange(status);
    }
  }



}
