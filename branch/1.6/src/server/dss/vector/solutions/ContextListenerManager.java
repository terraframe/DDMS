package dss.vector.solutions;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.runwaysdk.business.generation.LoaderDecoratorException;
import com.runwaysdk.generation.loader.LoaderDecorator;



public class ContextListenerManager implements ServletContextListener
{
  private static final String helper = "dss.vector.solutions.ContextListenerManagerHelper";
  
  private Object helperInst;
  
  public ContextListenerManager()
  {
    try
    {
      helperInst = LoaderDecorator.load(helper).getConstructor().newInstance();
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
    try
    {
      helperInst.getClass().getMethod("contextInitialized", LoaderDecorator.load("javax.servlet.ServletContextEvent")).invoke(helperInst, arg0);
    }
    catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
    {
      throw new LoaderDecoratorException(e);
    }
  }

  public void contextDestroyed(ServletContextEvent arg0)
  {
    try
    {
      helperInst.getClass().getMethod("contextDestroyed", LoaderDecorator.load("javax.servlet.ServletContextEvent")).invoke(helperInst, arg0);
    }
    catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
    {
      throw new LoaderDecoratorException(e);
    }
  }
}
