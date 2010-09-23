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

  public void pollServerState()
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

  public void validateProcessState(final boolean condition, final Runnable runnable)
  {
    try
    {
      String command = CommandProperties.getProccess();
      
      SynchronusProcess process = new SynchronusProcess();
      String output = process.run(command);

      if (output.contains("Bootstrap") == condition)
      {
        runnable.run();
      }
      else
      {
        fireErrorEvent(Localizer.getMessage("TOMCAT_ERROR"));

        pollServerState();
      }

    }
    catch (Exception e)
    {

    }
    
  }

  private void runCommand(final String command)
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
            pollServerState();
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

  public void enableServer(boolean status)
  {
    final String command = status ? CommandProperties.getStartCommand() : CommandProperties.getStopCommand();
    
    Runnable runnable = new Runnable()
    {      
      @Override
      public void run()
      {
        runCommand(command);
      }
    };

    if (status)
    {
      setLastCommand(status);
      
      // Ensure that the tomcat proccess does not already exist
      this.validateProcessState(false, runnable);
    }
    else if(getLastCommand() == null || getLastCommand() != status)
    {
      setLastCommand(status);
      
      this.validateProcessState(true, runnable);
    }
    else
    {
      this.pollServerState();
    }
  }
}
