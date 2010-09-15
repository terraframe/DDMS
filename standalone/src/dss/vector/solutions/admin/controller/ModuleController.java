package dss.vector.solutions.admin.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

import com.runwaysdk.constants.DeployProperties;
import com.runwaysdk.dataaccess.io.Backup;
import com.runwaysdk.dataaccess.io.Restore;
import com.runwaysdk.general.Localizer;

import dss.vector.solutions.admin.MDSSModule;

public class ModuleController implements IModuleController
{
  private static final Integer      DEFAULT_TIMEOUT       = 86400;

  private static final String       SESSION_TIME_PROPERTY = "sessionTime";

  private List<IControllerListener> listeners;

  private MDSSModule                module;

  public ModuleController()
  {
    this.listeners = Collections.synchronizedList(new ArrayList<IControllerListener>());
  }
  
  @Override
  public void setModule(MDSSModule module)
  {
    this.module = module;
  }

  @Override
  public void addListener(IControllerListener listener)
  {
    listeners.add(listener);
  }

  @Override
  public void removeListener(IControllerListener listener)
  {
    listeners.remove(listener);
  }

  private void fireEvent(IModuleEventStrategy strategy)
  {
    for (IControllerListener listener : listeners)
    {
      strategy.fireEvent(listener);
    }
  }

  @Override
  public void backup(final File file)
  {
    if (file != null)
    {
      final IRunnableWithProgress runnable = new IRunnableWithProgress()
      {
        @Override
        public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException
        {
          monitor.beginTask(Localizer.getMessage("BACKUP"), IProgressMonitor.UNKNOWN);

          EventOutputStream out = new EventOutputStream(monitor);
          PrintStream print = new PrintStream(out, true);

          Backup backup = new Backup(print, file.getName(), file.getParent(), true, true);
          backup.backup();

          print.close();
        }
      };

      fireEvent(new IModuleEventStrategy()
      {
        @Override
        public void fireEvent(IControllerListener listener)
        {
          listener.execute(runnable);
        }
      });
    }
  }

  @Override
  public void restore(final File file)
  {
    if (file != null)
    {
      final IRunnableWithProgress runnable = new IRunnableWithProgress()
      {
        @Override
        public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException
        {
          monitor.beginTask(Localizer.getMessage("RESTORE"), IProgressMonitor.UNKNOWN);

          EventOutputStream out = new EventOutputStream(monitor);
          PrintStream print = new PrintStream(out, true);

          Restore restore = new Restore(print, file.getAbsolutePath());
          restore.restore();

          print.close();
        }
      };

      fireEvent(new IModuleEventStrategy()
      {
        @Override
        public void fireEvent(IControllerListener listener)
        {
          listener.execute(runnable);
        }
      });
    }
  }

  @Override
  public void start(Integer timeout)
  {
    if (setTimeout(timeout))
    {
      runCommand(CommandProperties.getStartCommand());
    }
    else
    {
      fireErrorEvent(Localizer.getMessage("error.properties"));
    }
  }

  private void fireErrorEvent(final String msg)
  {
    fireEvent(new IModuleEventStrategy()
    {
      @Override
      public void fireEvent(IControllerListener listener)
      {
        listener.error(msg);
      }
    });
  }

  @Override
  public void stop()
  {
    runCommand(CommandProperties.getStopCommand());
  }

  @Override
  public Integer getTimeout()
  {
    Integer timeout = DEFAULT_TIMEOUT;
    BufferedReader in = null;

    try
    {
      in = new BufferedReader(new FileReader(CommandProperties.getTimeoutLocation()));

      String line;

      while ( ( line = in.readLine() ) != null)
      {
        if (line.trim().startsWith(SESSION_TIME_PROPERTY))
        {
          int equalsPos = line.lastIndexOf('=');
          if (equalsPos > 0)
          {
            String sessionTime = line.substring(equalsPos + 1).trim();

            timeout = Integer.parseInt(sessionTime);
          }
          break;
        }
      }
    }
    catch (IOException e)
    {
      // Do nothing...use default
      // MdssLog.debug(e);
    }
    catch (NumberFormatException e)
    {
      // Do nothing...use default
      // MdssLog.debug(e);
    }
    finally
    {
      if (in != null)
      {
        try
        {
          in.close();
        }
        catch (IOException e)
        {
          // Do nothing
        }
      }
    }
    return timeout;
  }

  @Override
  public boolean setTimeout(Integer timeout)
  {
    String propertiesPath = CommandProperties.getTimeoutLocation();

    return this.writeProperty(propertiesPath, SESSION_TIME_PROPERTY, timeout.toString());
  }

  private boolean writeProperty(String propertyPath, String propertyName, String propertyValue)
  {
    boolean success = false;

    BufferedReader in = null;
    BufferedWriter out = null;

    File newprops = new File(propertyPath + ".new");
    File props = new File(propertyPath);

    try
    {
      in = new BufferedReader(new FileReader(props));
      out = new BufferedWriter(new FileWriter(newprops));
      String line;
      while ( ( line = in.readLine() ) != null)
      {
        if (line.trim().startsWith(propertyName))
        {
          out.write(propertyName + "=" + propertyValue);
        }
        else
        {
          out.write(line);
        }
        out.write("\n");
      }
      success = true;
    }
    catch (Exception e)
    {
      success = false;
    }
    finally
    {
      if (in != null)
      {
        try
        {
          in.close();
        }
        catch (IOException e)
        {
          success = false;
        }
      }
      if (out != null)
      {
        try
        {
          out.close();
        }
        catch (IOException e)
        {
          success = false;
        }
      }
    }

    if (success)
    {
      success = false;
      File oldprops = new File(propertyPath + ".old");

      if (props.renameTo(oldprops))
      {
        if (newprops.renameTo(props))
        {
          oldprops.delete();
          success = true;
        }
      }
    }

    return success;
  }

  private void runCommand(final String command)
  {
    try
    {
      fireEvent(new IModuleEventStrategy()
      {
        @Override
        public void fireEvent(IControllerListener listener)
        {
          listener.beforeServerStateChange();
        }
      });
      
      module.setStatus(Localizer.getMessage("RUNNING_COMMAND") + " " + command);

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

  public void pollServerState()
  {
    Thread thread = new Thread(new Runnable()
    {      
      @Override
      public void run()
      {
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
          
          fireEvent(new IModuleEventStrategy()
          {          
            @Override
            public void fireEvent(IControllerListener listener)
            {
              listener.serverStateChange(true);
            }
          });          
        }
        catch (Exception e)
        {
          fireEvent(new IModuleEventStrategy()
          {          
            @Override
            public void fireEvent(IControllerListener listener)
            {
              listener.serverStateChange(false);
            }
          });
        }
        finally
        {
          module.clearStatus();
        }
      }
    });
    
    thread.setDaemon(true);
    thread.start();
  }
}
