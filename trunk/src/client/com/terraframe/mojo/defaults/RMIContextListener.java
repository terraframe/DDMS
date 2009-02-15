package com.terraframe.mojo.defaults;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.terraframe.mojo.facade.RemoteAdapterServer;
import com.terraframe.mojo.generation.loader.Reloadable;

public class RMIContextListener implements ServletContextListener, Reloadable
{
  public void contextDestroyed(ServletContextEvent arg0)
  {
    RemoteAdapterServer.stopServer();
  }

  public void contextInitialized(ServletContextEvent arg0)
  {
    RemoteAdapterServer.startServer();
  }
}
