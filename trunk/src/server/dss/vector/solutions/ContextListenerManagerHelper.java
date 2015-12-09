package dss.vector.solutions;

import javax.servlet.ServletContextEvent;

import com.runwaysdk.defaults.RMIContextListener;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.util.ClientInitializer;

import dss.vector.solutions.general.EmailContextListener;

public class ContextListenerManagerHelper implements Reloadable
{
  private RMIContextListener rMIContextListener;
  private CleanupContextListener cleanupContextListener;
  private EmailContextListener emailContextListener;
  
  public ContextListenerManagerHelper()
  {
    rMIContextListener = new RMIContextListener();
    cleanupContextListener = new CleanupContextListener();
    emailContextListener = new EmailContextListener();
  }
  
  public void contextInitialized(ServletContextEvent arg0)
  {
    rMIContextListener.contextInitialized(arg0);
    ClientInitializer.init();
    cleanupContextListener.contextInitialized(arg0);
    emailContextListener.contextInitialized(arg0);
  }

  public void contextDestroyed(ServletContextEvent arg0)
  {
    rMIContextListener.contextDestroyed(arg0);
    cleanupContextListener.contextDestroyed(arg0);
    emailContextListener.contextDestroyed(arg0);
  }
}
