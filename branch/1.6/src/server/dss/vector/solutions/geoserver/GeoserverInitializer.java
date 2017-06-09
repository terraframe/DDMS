package dss.vector.solutions.geoserver;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.runwaysdk.generation.loader.Reloadable;

public class GeoserverInitializer implements UncaughtExceptionHandler, Reloadable
{
  private static boolean               initialized = false;

  private static final ReentrantLock   lock        = new ReentrantLock();

  private static final Log             initLog     = LogFactory.getLog(GeoserverInitializer.class);

  private static final CleanupRunnable cleanup     = new CleanupRunnable();

  public static class CheckThread implements Runnable, Reloadable
  {

    private static final Log log = LogFactory.getLog(CheckThread.class);

    public CheckThread()
    {
      super();
    }

    @Override
    public void run()
    {
      try
      {
        lock.lock();

        log.debug("Attempting to check existence of geoserver");

        if (GeoserverFacade.existGeoserver())
        {
          log.debug("Geoserver available.");
          
          // To prevent a problem if the database connection information of the
          // datastore ever changes we must delete and recreate the store and workspace.
          if (GeoserverFacade.workspaceExists())
          {
            GeoserverFacade.removeWorkspace();
            GeoserverFacade.removeStore();
          }

          GeoserverFacade.publishWorkspace();
          GeoserverFacade.publishStore();
          
          LocalBasemapBuilder.configureGeoserverForOSM();
          LocalBasemapBuilder.importAllBasemapData();
          LocalBasemapBuilder.buildOSMGeoserverServices();
          
          initialized = true;
          log.debug("Geoserver initialized.");
          return; // we are done here
        }
        else
        {
          try
          {
            log.debug("Waiting for Geoserver to start.");
            Thread.sleep(1000);
          }
          catch (InterruptedException e)
          {
            // allow another try
            log.warn(e);
          }
        }
      }
      catch (Throwable t)
      {
        // we couldn't hit the application correctly, so log the error
        // and quit the loop to avoid excessive logging
        log.error("Unable to start the application.", t);
        return;
      }
      finally
      {
        lock.unlock();
      }
    }
  }
  
  public static boolean isInitialized()
  {
    try
    {
      lock.lock();

      return initialized;
    }
    finally
    {
      lock.unlock();
    }
  }

  public static void setup()
  {
    GeoserverInitializer init = new GeoserverInitializer();

    try
    {
      initLog.debug("Attempting to initialize context.");

      // create another thread to avoid blocking the one starting the webapps.
      Thread t = new Thread(new CheckThread());
      t.setUncaughtExceptionHandler(init);
      t.setDaemon(true);
      t.start();

      initLog.debug("Context initialized...[" + GeoserverInitializer.class + "] started.");
    }
    catch (Throwable t)
    {
      initLog.error("Could not initialize context.", t);
    }

    // Start the mapping database view cleanup thread
    Thread t = new Thread(cleanup);
    t.setUncaughtExceptionHandler(init);
    t.setDaemon(true);
    t.start();

    // scheduler.scheduleWithFixedDelay(new CleanupRunnable(), 1, 5, TimeUnit.MINUTES);
  }

  /**
   * Log the error.
   */
  @Override
  public void uncaughtException(Thread t, Throwable e)
  {
    initLog.error(t, e);
  }

  public static void shutdown()
  {
    // Shutdown the mapping database view cleanup thread
    cleanup.shutdown();
  }
  
}