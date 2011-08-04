package dss.vector.solutions.general;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.runwaysdk.business.ClassLoaderException;
import com.runwaysdk.business.email.EmailException;
import com.runwaysdk.constants.CommonProperties;
import com.runwaysdk.dataaccess.MdTypeDAOIF;
import com.runwaysdk.dataaccess.metadata.MdTypeDAO;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.session.Request;

public class EmailContextListener implements ServletContextListener
{
  private static final String EMAILTYPE = "dss.vector.solutions.general.Email";
  private static final String CONFIGTYPE = "dss.vector.solutions.general.EmailConfiguration";

  public void contextInitialized(ServletContextEvent arg0)
  {
    startDaemon();
  }

  public void contextDestroyed(ServletContextEvent arg0)
  {
    stopDaemon();
  }

  private static class EmailDaemon implements Runnable
  {
    private static final long MINUTE_IN_MILLISECONDS = 60000;

    private long              lastRun                = 0;

    private boolean           running                = false;

    public void run()
    {
      int fails = 0;
      while (running)
      {
        try
        {
        	runOnce();
        }
        catch (Throwable t)
        {
          // Catch all errors and try again
          t.printStackTrace(System.err);
          fails++;
          if (fails<50)
          {
            continue;
          }
          else
          {
            // 50 consecutive fails means that something is wrong.
            throw new EmailException("50 consecutive failures to run the e-mail daemon method.  Most recent cause attached.", t);
          }
        }
//        finally
//        {
//          LockHolder.unlock();
//        }
        
        try
        {
          // If we get here, then we have executed successfully.
          fails = 0;
          Thread.sleep(MINUTE_IN_MILLISECONDS);
        }
        catch (InterruptedException e)
        {
          // Do nothing
        }
      }
    }

    @Request
    private void runOnce()
    {
//      Intentionally removing LockHolder access infavor of the "just-try-again" approach
//        LockHolder.lock(this);
        long current = System.currentTimeMillis();
        Object config = configInvoke(null, "getDefault");
        Integer retry = (Integer) configInvoke(config, "getRetry");
        if (this.lastRun == 0 || ( current >= ( this.lastRun + ( retry * MINUTE_IN_MILLISECONDS ) ) ))
        {
          emailInvoke("sendAll");
          this.lastRun = current;
        }
    }

    public void start()
    {
      if (!running)
      {
        Thread t = new Thread(this, CommonProperties.getDeployAppName() + " Email Daemon");
        this.running = true;
        t.start();
      }
    }

    public void stop()
    {
      this.running = false;
    }
  }

  private static EmailDaemon daemon = new EmailDaemon();

  public static void startDaemon()
  {
    daemon.start();
  }

  public static void stopDaemon()
  {
    daemon.stop();
  }

  private static Object emailInvoke(String methodName)
  {
    try
    {
      Class<?> email = LoaderDecorator.load(EMAILTYPE);
      return email.getMethod(methodName).invoke(null);
    }
    catch (Exception e)
    {
      String error = "Problem invoking Email." + methodName + "() through reflection";
      MdTypeDAOIF mdTypeDAO = MdTypeDAO.getMdTypeDAO(EMAILTYPE);
      if (e instanceof InvocationTargetException)
      {
        InvocationTargetException ite = (InvocationTargetException)e;
        throw new ClassLoaderException(error, mdTypeDAO, ite.getTargetException());
      }
      else
      {
        throw new ClassLoaderException(error, mdTypeDAO, e);
      }
    }
  }

  private static Object configInvoke(Object config, String methodName)
  {
    try
    {
      Class<?> configClass = LoaderDecorator.load(CONFIGTYPE);
      return configClass.getMethod(methodName).invoke(config);
    }
    catch (Exception e)
    {
      String error = "Problem invoking EmailConfiguration." + methodName + "() through reflection";
      MdTypeDAOIF mdTypeDAO = MdTypeDAO.getMdTypeDAO(CONFIGTYPE);
      if (e instanceof InvocationTargetException)
      {
        InvocationTargetException ite = (InvocationTargetException)e;
        throw new ClassLoaderException(error, mdTypeDAO, ite.getTargetException());
      }
      else
      {
        throw new ClassLoaderException(error, mdTypeDAO, e);
      }
    }
  }
}
