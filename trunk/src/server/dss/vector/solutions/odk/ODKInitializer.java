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

import org.apache.http.client.CredentialsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Request;

import dss.vector.solutions.migration.Patcher4009;

public class ODKInitializer implements UncaughtExceptionHandler, Reloadable
{
  private static boolean             initialized = false;

  private static final ReentrantLock lock        = new ReentrantLock();

  private static final Logger         initLogger = LoggerFactory.getLogger(ODKInitializer.class);

  public static class InitThread implements Runnable, Reloadable
  {

    private static final Logger logger = LoggerFactory.getLogger(InitThread.class);

    public InitThread()
    {
      super();
    }

    @Override
    public void run()
    {
      boolean isDone = false;
      
      while (!isDone)
      {
        isDone = runInRequest();
        
        try
        {
          Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
          Thread.currentThread().interrupt();
          isDone = true;
        }
      }
    }
    
    @Request
    private boolean runInRequest()
    {
      try
      {
        lock.lock();

        logger.info("Attempting to check existence of odk");

        if (ODKFacade.existODK())
        {
          /*
           * Update the default "aggregate" password to the hard-coded
           * password
           */
          try
          {
            ODKUser odkUser = ODKUser.getUser();
            
            CredentialsProvider provider = ODKFacade.getCredentialsProvider(ODKFacade.USERNAME, "aggregate");
            ODKPasswordExporter exporter = new ODKPasswordExporter(odkUser.getUsername(), odkUser.getOdkPassword(), provider);
            exporter.run();
          }
          catch (Exception e)
          {
            // Ignore: this may fail if the default password has already been
            // changed
            logger.info(e.getMessage());
          }            
          
          ODKPermissionExporter.export(true);

          initialized = true;
          logger.info("ODK initialized.");

          ODKProperties.writeInitialize(false);
          
          return true; // we are done here
        }
        else
        {
          return false;
        }
      }
      catch (Throwable t)
      {
        // we couldn't hit the application correctly, so log the error
        // and quit the loop to avoid excessive logging
        logger.error("Unable to start the application.", t);

        return true;
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

  /**
   * Log the error.
   */
  @Override
  public void uncaughtException(Thread t, Throwable e)
  {
    initLogger.error("Error in ODKInitializer", e);
  }

  public static void setup()
  {
    if (ODKUser.getUser() == null) // Only happens on a fresh install
    {
      Patcher4009.createOdkUser();
    }
    
    if (ODKFacade.isInitialize())
    {
      ODKInitializer init = new ODKInitializer();

      try
      {
        initLogger.info("Attempting to initialize context.");

        // create another thread to avoid blocking the one starting the webapps.
        Thread t = new Thread(new InitThread());
        t.setUncaughtExceptionHandler(init);
        t.setDaemon(true);
        t.start();

        initLogger.info("Context initialized...[" + ODKInitializer.class + "] started.");
      }
      catch (Throwable t)
      {
        initLogger.error("Could not initialize context.", t);
      }
    }
  }
}
