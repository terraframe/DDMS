package dss.vector.solutions;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.runwaysdk.defaults.RMIContextListener;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.util.ClientInitializer;

import dss.vector.solutions.general.EmailContextListener;



public class ContextListenerManager implements ServletContextListener, Reloadable
{
  RMIContextListener  rMIContextListener;
  EmailContextListener emailContextListener;
  ClientInitializer clientInitializer;
  CleanupContextListener cleanupContextListener;
  
  
  public ContextListenerManager()
  {
    rMIContextListener = new RMIContextListener();
    clientInitializer = new ClientInitializer();
    cleanupContextListener = new CleanupContextListener();
    emailContextListener = new EmailContextListener();
  }
  
  
  public void contextInitialized(ServletContextEvent arg0)
  {
    rMIContextListener.contextInitialized(arg0);
    clientInitializer.contextInitialized(arg0);
    cleanupContextListener.contextInitialized(arg0);
    emailContextListener.contextInitialized(arg0);
  }

  public void contextDestroyed(ServletContextEvent arg0)
  {
    rMIContextListener.contextDestroyed(arg0);
    clientInitializer.contextDestroyed(arg0);
    cleanupContextListener.contextDestroyed(arg0);
    emailContextListener.contextDestroyed(arg0);
  }
  
 
}
