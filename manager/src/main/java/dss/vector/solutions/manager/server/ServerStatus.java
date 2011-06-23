package dss.vector.solutions.manager.server;

import org.apache.catalina.Lifecycle;

public enum ServerStatus {
  STARTING, STARTED, STOPPING, STOPPED, UNAVAILABLE;

  public static ServerStatus getStatus(String state)
  {
    if (state.equals(Lifecycle.AFTER_START_EVENT))
    {
      return ServerStatus.STARTED;
    }
    else if (state.equals(Lifecycle.AFTER_STOP_EVENT))
    {
      return ServerStatus.STOPPED;
    }
    else if (state.equals(Lifecycle.START_EVENT))
    {
      return ServerStatus.STARTING;
    }
    else if (state.equals(Lifecycle.STOP_EVENT))
    {
      return ServerStatus.STOPPING;
    }
    else
    {
      return ServerStatus.UNAVAILABLE;
    }

  }
}
