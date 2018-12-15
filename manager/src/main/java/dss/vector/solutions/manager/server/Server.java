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
package dss.vector.solutions.manager.server;

import java.lang.Thread.UncaughtExceptionHandler;
import java.rmi.AccessException;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.rmi.ssl.SslRMIClientSocketFactory;

import org.apache.log4j.Logger;

import com.runwaysdk.tomcat.RemoteLifecycleListenerServer;
import com.runwaysdk.tomcat.RemoteLifecycleListenerServerIF;

import dss.vector.solutions.manager.Localizer;
import dss.vector.solutions.manager.properties.ManagerProperties;

public class Server extends EventProvider implements UncaughtExceptionHandler, IServer
{
  private static Logger logger = Logger.getLogger(Server.class);
  
  /**
   * Amount of time to wait before calling the status call back function
   */
  static final long               WAIT_TIME    = 5000L;

  /**
   * The maximum number of attempts to try to connect to tomcat before
   * determining that it is not up.
   */
  static final int                MAX_ATTEMPTS = 5;

  /**
   * Name of the service
   */
  private static final String             SERVICE_NAME = "Tomcat";

  private RemoteLifecycleListenerServerIF server;

  private RemoteLifecycleListener         listener;

  public Server()
  {
    super();

    try
    {
      this.listener = new RemoteLifecycleListener(Server.this);

      this.registerServer();
    }
    catch (Exception e)
    {
      // The server is not up yet so it is impossbile to connect to the RMI
      // service.
    }
  }

  final void registerServer() throws RemoteException, NotBoundException, AccessException
  {
    int port = ManagerProperties.getListenerPort();

    Registry registry = LocateRegistry.getRegistry(null, port, new SslRMIClientSocketFactory());

    this.server = (RemoteLifecycleListenerServerIF) registry.lookup(RemoteLifecycleListenerServer.NAME);
    this.server.addListener(listener);
  }

  public void refresh()
  {
    fireServerChange(this.getServerStatus());
  }

  private void runCommand(final String[] command, final Runnable callback)
  {
    try
    {
      Runtime rt = Runtime.getRuntime();
      final Process pr = rt.exec(command);

      Thread outputThread = new Thread()
      {
        public void run()
        {
          try
          {
            pr.waitFor();
          }
          catch (InterruptedException e)
          {
            fireErrorEvent(e.getLocalizedMessage());
          }
          finally
          {
            if (callback != null)
            {
              callback.run();
            }
          }
        }
      };
      outputThread.setUncaughtExceptionHandler(this);
      outputThread.setDaemon(true);
      outputThread.start();
    }
    catch (Exception e)
    {
      this.fireErrorEvent(e.getLocalizedMessage());
    }
  }

  public void startServer()
  {
    ServerStatus status = this.getServerStatus();

    if (status.equals(ServerStatus.STOPPED))
    {
      String[] script = { "cmd.exe", "/c", "sc", "start", SERVICE_NAME };

      runCommand(script, new Runnable()
      {
        @Override
        public void run()
        {
          long start = System.currentTimeMillis();
          
          // Tomcat needs time to start the remote RMI server, before we can
          // connect to it.

          int count = 0;

          while (count < MAX_ATTEMPTS && server == null)
          {
            count++;

            long wait = System.currentTimeMillis() + WAIT_TIME;

            while (System.currentTimeMillis() < wait)
            {
              try
              {
                Thread.sleep(1000L);
              }
              catch (InterruptedException e)
              {
                // Do nothing
              }
            }

            try
            {
              Server.this.registerServer();
              Server.this.refresh();
            }
            catch (Exception e)
            {
              server = null;
            }
          }

          if (server == null)
          {
            // There was a problem connecting to the RMI server even though it
            // should have already just been started. The server must not be
            // started or is being blocked.
            Server.this.fireServerChange(ServerStatus.STOPPED);

            throw new RuntimeException(Localizer.getMessage("RMI_FAILED_TO_START"));
          }
          else
          {
            long end = System.currentTimeMillis();
            
            String msg = "Server bootup in " + (end - start) + "ms.";
            logger.info(msg);
          }
        }
      });
    }
    else
    {
      this.refresh();
    }
  }

  public void stopServer()
  {
    ServerStatus status = this.getServerStatus();

    if (status.equals(ServerStatus.STARTED))
    {
      String[] script = { "cmd.exe", "/c", "sc", "stop", SERVICE_NAME };

      runCommand(script, null);
    }
    else
    {
      throw new RuntimeException(Localizer.getMessage("SERVER_NOT_STARTED"));
    }
  }
  
  public String getEnvInfo()
  {
    if (this.server == null)
    {
      try
      {
        this.registerServer();
      }
      catch (Exception e)
      {
        // The server is not up yet so it is impossbile to connect to the RMI
        // service.
      }
    }

    if (this.server != null)
    {
      try
      {
        return this.server.getEnvInfo();
      }
      catch (RemoteException e)
      {
        // The server is no longer up or is not reachable

        this.server = null;
      }
    }

    return null;
  }
  
  public String getStackDump()
  {
    if (this.server == null)
    {
      try
      {
        this.registerServer();
      }
      catch (Exception e)
      {
        // The server is not up yet so it is impossbile to connect to the RMI
        // service.
      }
    }

    if (this.server != null)
    {
      try
      {
        return this.server.getStackDump();
      }
      catch (RemoteException e)
      {
        // The server is no longer up or is not reachable

        this.server = null;
      }
    }

    return null;
  }

  public ServerStatus getServerStatus()
  {
    if (this.server == null)
    {
      try
      {
        this.registerServer();
      }
      catch (Exception e)
      {
        // The server is not up yet so it is impossbile to connect to the RMI
        // service.
      }
    }

    if (this.server != null)
    {
      try
      {
        String state = this.server.getCurrentState();

        return ServerStatus.getStatus(state);
      }
      catch (RemoteException e)
      {
        // The server is no longer up or is not reachable

        this.server = null;
      }
    }

    return ServerStatus.STOPPED;
  }

  public void closeRemoteServer()
  {
    this.server = null;
  }

  public void enableServer(boolean state)
  {
    if (state)
    {
      this.startServer();
    }
    else
    {
      this.stopServer();
    }
  }

  public void close()
  {
    if (this.server != null && this.listener != null)
    {
      try
      {
        this.server.removeListener(listener);
      }
      catch (RemoteException e)
      {
        // The remote server may no longer be active or is not reachable.
        this.server = null;
      }
    }

    if (this.listener != null)
    {
      try
      {
        UnicastRemoteObject.unexportObject(listener, true);
      }
      catch (NoSuchObjectException e)
      {
        // The exported listener has already been removed from the registry
      }
    }
  }

  @Override
  public void uncaughtException(Thread thread, Throwable throwable)
  {
    this.fireErrorEvent(throwable);
  }
}
