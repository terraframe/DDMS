package dss.vector.solutions.admin.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.runwaysdk.constants.DeployProperties;
import com.runwaysdk.general.Localizer;

import dss.vector.solutions.admin.controller.CommandProperties;
import dss.vector.solutions.admin.controller.EventProvider;

public class Server extends EventProvider
{
  /**
   * Amount of time to wait before calling the status call back function
   */
  private static final long WAIT_TIME = 5000L;
  
  private Boolean lastCommand;

  public Server()
  {
    super();

    this.lastCommand = null;
  }

  private synchronized void setLastCommand(boolean state)
  {
    this.lastCommand = state;
  }

  private synchronized Boolean getLastCommand()
  {
    return this.lastCommand;
  }

  public void pollURL()
  {
    Thread thread = new Thread(new Runnable()
    {
      @Override
      public void run()
      {
        boolean status = false;

        try
        {
          String url = DeployProperties.getApplicationURL() + "/status.jsp";
          URL server = new URL(url);
          HttpURLConnection connection = (HttpURLConnection) server.openConnection();
          connection.connect();

          BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
          in.readLine();
          in.close();

          connection.disconnect();

          status = true;
        }
        catch (Exception e)
        {
        }
        finally
        {
          fireServerChange(status);
        }
      }
    });

    thread.setDaemon(true);
    thread.start();
  }

  public void pollProcess()
  {
    fireServerChange(this.isServerUp());
  }
  
  public void validateProcessState(final boolean condition, final Runnable runnable)
  {
    boolean status = this.isServerUp();

    if (status == condition)
    {
      runnable.run();
    }
    else
    {
      fireErrorEvent(Localizer.getMessage("TOMCAT_ERROR"));

      pollURL();
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
            
            while(System.currentTimeMillis() < wait)
            {
              Thread.yield();
            }
            
            callback.run();
          }
        }
      };
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
            pollURL();
          }
        });
      }
    };

    setLastCommand(true);

    // Ensure that the tomcat proccess does not already exist
    this.validateProcessState(false, runnable);
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
            pollURL();
          }
        });
      }
    };

    if (getLastCommand() == null || getLastCommand() != false)
    {
      setLastCommand(false);

      this.validateProcessState(true, runnable);
    }
    else
    {
      this.pollURL();
    }
  }

  public boolean isServerUp()
  {
    try
    {
      String command = CommandProperties.getProccess();

      SynchronusProcess process = new SynchronusProcess();
      String output = process.run(command);

      return output.contains("Bootstrap");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

    return false;
  }

  public void enableServer(boolean state)
  {
    if(state)
    {
      this.startServer();
    }
    else
    {
      this.stopServer();
    }
  }
}
