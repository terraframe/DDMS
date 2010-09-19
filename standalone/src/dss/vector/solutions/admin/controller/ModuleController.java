package dss.vector.solutions.admin.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

import com.runwaysdk.controller.IConfiguration;
import com.runwaysdk.controller.IExportStrategy;
import com.runwaysdk.dataaccess.io.Backup;
import com.runwaysdk.dataaccess.io.Restore;
import com.runwaysdk.general.Localizer;
import com.runwaysdk.model.ExportBean;
import com.runwaysdk.model.IEntityObject;
import com.runwaysdk.model.ImportBean;

import dss.vector.solutions.admin.model.Server;

public class ModuleController extends EventProvider implements IModuleController
{
  private static final Integer DEFAULT_TIMEOUT       = 86400;

  private static final String  SESSION_TIME_PROPERTY = "sessionTime";

  private Server               server;

  private IConfiguration       configuration;

  public ModuleController()
  {
    super();

    this.server = new Server();
    this.configuration = new SlaveConfiguration();

    if (this.isMaster())
    {
      this.configuration = new MasterConfiguration();
    }
  }

  private boolean isMaster()
  {
    boolean success = false;

    File props = new File(CommandProperties.getInstall());

    BufferedReader in = null;
    
    try
    {
      in = new BufferedReader(new FileReader(props));

      String line;

      while ( ( line = in.readLine() ) != null)
      {
        if(line != null && line.contains("master") && line.contains("true"))
        {
          return true;
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
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
          e.printStackTrace();
        }
      }
    }

    return false;
  }

  @Override
  public void addListener(IControllerListener listener)
  {
    super.addListener(listener);

    this.server.addListener(listener);
  }

  @Override
  public void removeListener(IControllerListener listener)
  {
    super.removeListener(listener);

    this.server.removeListener(listener);
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
      enableServer(true);
    }
    else
    {
      fireErrorEvent(Localizer.getMessage("error.properties"));
    }
  }

  @Override
  public void stop()
  {
    enableServer(false);
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

  private void enableServer(final boolean state)
  {
    fireEvent(new IModuleEventStrategy()
    {
      @Override
      public void fireEvent(IControllerListener listener)
      {
        listener.beforeServerStateChange(state);
      }
    });

    server.enableServer(state);
  }

  @Override
  public void refresh()
  {
    server.pollServerState();
  }

  @Override
  public void apply(IEntityObject arg0)
  {
  }

  @Override
  public void delete(IEntityObject arg0)
  {
  }

  @Override
  public void exportTransactions(ExportBean bean)
  {
    File location = new File(bean.getLocation());
    IExportStrategy strategy = bean.getExportStrategy();
    ExportWorker runnable = new ExportWorker(location, strategy, configuration);

    this.fireExecuteEvent(runnable);
  }

  @Override
  public void importTransaction(ImportBean bean)
  {
    File location = new File(bean.getLocation());

    ImportWorker runnable = new ImportWorker(location, configuration);

    this.fireExecuteEvent(runnable);
  }

}
