package dss.vector.solutions.admin.model;

import java.lang.Thread.UncaughtExceptionHandler;
import java.rmi.AccessException;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.rmi.ssl.SslRMIClientSocketFactory;

import org.apache.catalina.Lifecycle;

import com.runwaysdk.manager.general.Localizer;
import com.runwaysdk.tomcat.RemoteLifecycleListenerServer;
import com.runwaysdk.tomcat.RemoteLifecycleListenerServerIF;

import dss.vector.solutions.admin.controller.CommandProperties;
import dss.vector.solutions.admin.controller.EventProvider;

public class Server extends EventProvider implements UncaughtExceptionHandler
{
  /**
   * Amount of time to wait before calling the status call back function
   */
  private static final long               WAIT_TIME = 5000L;

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

  private final void registerServer() throws RemoteException, NotBoundException, AccessException
  {
    int port = CommandProperties.getListenerPort();

    Registry registry = LocateRegistry.getRegistry(null, port, new SslRMIClientSocketFactory());

    this.server = (RemoteLifecycleListenerServerIF) registry.lookup(RemoteLifecycleListenerServer.NAME);
    this.server.addListener(listener);
  }

  public void refresh()
  {
    fireServerChange(this.getServerStatus());
  }

  public void validateProcessState(final ServerStatus condition, final Runnable runnable)
  {
    ServerStatus status = this.getServerStatus();

    if (status.equals(condition))
    {
      runnable.run();
    }
    else
    {
      fireErrorEvent(Localizer.getMessage("TOMCAT_ERROR"));
    }
  }

  private void runCommand(final String command, final Runnable callback)
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

            callback.run();
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
    Runnable runnable = new Runnable()
    {
      @Override
      public void run()
      {
        runCommand(CommandProperties.getStartCommand(), new Runnable()
        {
          @Override
          public void run()
          {
            try
            {
              Server.this.registerServer();
            }
            catch (Exception e)
            {
              // There was a problem connecting to the RMI server even though it
              // should have already just been started. The server must not be
              // started or is being blocked.
              Server.this.fireServerChange(ServerStatus.STOPPED);

              throw new RuntimeException(Localizer.getMessage("RMI_FAILED_TO_START"));
            }
          }
        });
      }
    };

    // Ensure that the tomcat proccess does not already exist
    this.validateProcessState(ServerStatus.STOPPED, runnable);
  }

  public void stopServer()
  {
    Runnable runnable = new Runnable()
    {
      @Override
      public void run()
      {
        runCommand(CommandProperties.getStopCommand(), new Runnable()
        {
          @Override
          public void run()
          {
          }
        });
      }
    };

    this.validateProcessState(ServerStatus.STARTED, runnable);
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

        if (state.equals(Lifecycle.AFTER_START_EVENT))
        {
          return ServerStatus.STARTED;
        }
        else if (state.equals(Lifecycle.AFTER_STOP_EVENT))
        {
          return ServerStatus.STOPPED;
        }

        return ServerStatus.UNAVALIABLE;
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
    this.fireErrorEvent(throwable.getLocalizedMessage());
  }
}
