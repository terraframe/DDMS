package dss.vector.solutions.admin.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
    PropertyReader reader = new PropertyReader(CommandProperties.getInstall());
    String value = reader.getValue("master");

    return ( value != null && value.contains("true") );
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
    Runnable runnable = new Runnable()
    {
      @Override
      public void run()
      {
        if (file != null)
        {
          final IRunnableWithProgress progress = new IRunnableWithProgress()
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
              
              fireMessageEvent(Localizer.getMessage("BACKUP_COMPLETE"));
            }
          };
          
          fireExecuteEvent(progress);
        }
      }
    };
    
    server.validateProcessState(false, runnable);
  }

  @Override
  public void restore(final File file)
  {
    Runnable runnable = new Runnable()
    {      
      @Override
      public void run()
      {
        if (file != null)
        {
          final IRunnableWithProgress progress = new IRunnableWithProgress()
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
              
              fireMessageEvent(Localizer.getMessage("RESTORE_COMPLETE"));
            }
          };

          fireExecuteEvent(progress);
        }
      }
    };
    
    server.validateProcessState(false, runnable);
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

    PropertyWriter writer = new PropertyWriter(propertiesPath);

    return writer.write(SESSION_TIME_PROPERTY, timeout.toString());
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
    server.pollURL();
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

  @Override
  public boolean isServerUp()
  {
    return server.isServerUp();
  }

}
