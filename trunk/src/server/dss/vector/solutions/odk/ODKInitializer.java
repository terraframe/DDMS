/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.odk;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.CredentialsProvider;

import com.runwaysdk.generation.loader.Reloadable;

public class ODKInitializer implements UncaughtExceptionHandler, Reloadable
{
  private static boolean             initialized = false;

  private static final ReentrantLock lock        = new ReentrantLock();

  private static final Log           initLog     = LogFactory.getLog(ODKInitializer.class);

  public static class InitThread implements Runnable, Reloadable
  {

    private static final Log log = LogFactory.getLog(InitThread.class);

    public InitThread()
    {
      super();
    }

    @Override
    public void run()
    {
      while (true)
      {
        try
        {
          lock.lock();

          log.debug("Attempting to check existence of odk");

          if (ODKFacade.existODK())
          {
            /*
             * Update the default "aggregate" password to the hard-coded
             * password
             */
            try
            {
              CredentialsProvider provider = ODKFacade.getCredentialsProvider(ODKFacade.USERNAME, "aggregate");
              ODKPasswordExporter exporter = new ODKPasswordExporter(ODKFacade.USERNAME, ODKFacade.PASSWORD, provider);
              exporter.run();
            }
            catch (Exception e)
            {
              // Ignore: this may fail if the default password has already been
              // changed
              log.debug(e.getMessage());
            }            
            
            ODKPermissionExporter.export(true);

            initialized = true;
            log.debug("ODK initialized.");

            ODKProperties.writeInitialize(false);

            return; // we are done here
          }
          else
          {
            try
            {
              log.debug("Waiting for ODK to start.");
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

  /**
   * Log the error.
   */
  @Override
  public void uncaughtException(Thread t, Throwable e)
  {
    initLog.error(t, e);
  }

  public static void setup()
  {
    if (ODKFacade.isInitialize())
    {
      ODKInitializer init = new ODKInitializer();

      try
      {
        initLog.debug("Attempting to initialize context.");

        // create another thread to avoid blocking the one starting the webapps.
        Thread t = new Thread(new InitThread());
        t.setUncaughtExceptionHandler(init);
        t.setDaemon(true);
        t.start();

        initLog.debug("Context initialized...[" + ODKInitializer.class + "] started.");
      }
      catch (Throwable t)
      {
        initLog.error("Could not initialize context.", t);
      }
    }
  }
}
