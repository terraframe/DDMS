/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.manager;

import java.lang.Thread.UncaughtExceptionHandler;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Display;

import dss.vector.solutions.manager.server.IServer;
import dss.vector.solutions.manager.server.ServerStatus;

public class ServerShutdownThread implements Runnable
{
  private static Logger logger = Logger.getLogger(ServerShutdownThread.class);
  
  /**
   * Manager context bean
   */
  private ManagerContextBean       context;

  /**
   * Server object which handles stopping the server
   */
  private IServer                  server;

  /**
   * ExceptionHandler which handles any uncaught exceptions
   */
  private UncaughtExceptionHandler handler;

  public ServerShutdownThread(ManagerContextBean context, IServer server, UncaughtExceptionHandler handler)
  {
    this.handler = handler;
    this.context = context;
    this.server = server;
  }

  public void run()
  {
    final Display display = Display.getCurrent();

    Thread thread = new Thread(new Runnable()
    {
      @Override
      public void run()
      {
        try
        {
          long start = System.currentTimeMillis();
          
          logger.info("Shutting down tomcat gracefully");
          server.stopServer();
          
          ServerStatus status = ServerStatus.STARTED;
          
          while (!status.equals(ServerStatus.STOPPED) && !Thread.interrupted())
          {
            long now = System.currentTimeMillis();
            
            if ( (now - start) > 10000 ) // Server should be down after 10 seconds. If not, we're killing it.
            {
              logger.warn("Killing server because it took too long to shutdown.");
              server.killServer();
              break;
            }
            
            Thread.sleep(1000);
            
            status = server.getServerStatus();
          }
        }
        catch (InterruptedException e)
        {
          // Do nothing. Just let the thread die.
        }
        finally
        {
          display.asyncExec(new Runnable()
          {
            @Override
            public void run()
            {
              context.setProcessRunning(false);
            }
          });
        }
      }
    });
    thread.setUncaughtExceptionHandler(handler);
    thread.setDaemon(true);
    thread.start();

    context.setProcessRunning(true);
  }
}
