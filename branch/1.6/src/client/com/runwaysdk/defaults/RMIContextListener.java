package com.runwaysdk.defaults;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.runwaysdk.facade.RemoteAdapterServer;
import com.runwaysdk.generation.loader.Reloadable;

public class RMIContextListener implements ServletContextListener, Reloadable
{
  public void contextDestroyed(ServletContextEvent arg0)
  {
    RemoteAdapterServer.stopServer();
  }

  public void contextInitialized(ServletContextEvent arg0)
  {
    RemoteAdapterServer.startServer(null, null);
  }
}
