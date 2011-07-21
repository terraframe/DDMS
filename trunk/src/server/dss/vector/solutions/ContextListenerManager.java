package dss.vector.solutions;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.runwaysdk.business.generation.LoaderDecoratorException;
import com.runwaysdk.generation.loader.LoaderDecorator;



public class ContextListenerManager implements ServletContextListener
{
  ServletContextListener rMIContextListener;
  ServletContextListener emailContextListener;
  ServletContextListener clientInitializer;
  ServletContextListener cleanupContextListener;
  
  private static final String RMI = "com.runwaysdk.defaults.RMIContextListener";
  private static final String EMAIL = "dss.vector.solutions.general.EmailContextListener";
  private static final String CLIENT = "com.runwaysdk.util.ClientInitializer";
  private static final String CLEANUP = "dss.vector.solutions.CleanupContextListener";
  
  
  public ContextListenerManager()
  {
//    rMIContextListener = new RMIContextListener();
//    clientInitializer = new ClientInitializer();
//    cleanupContextListener = new CleanupContextListener();
//    emailContextListener = new EmailContextListener();
    
    try
    {
      rMIContextListener = (ServletContextListener)LoaderDecorator.load(RMI).getConstructor().newInstance();
      clientInitializer = (ServletContextListener)LoaderDecorator.load(CLIENT).getConstructor().newInstance();
      cleanupContextListener = (ServletContextListener)LoaderDecorator.load(CLEANUP).getConstructor().newInstance();
      emailContextListener = (ServletContextListener)LoaderDecorator.load(EMAIL).getConstructor().newInstance();
    }
    catch (InvocationTargetException ite)
    {
      throw new LoaderDecoratorException("InvocationTargetException initializing Context Listeners", ite.getTargetException());
    }
    catch (Exception e)
    {
      throw new LoaderDecoratorException("Exception initializing Context Listeners", e);
    }
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
