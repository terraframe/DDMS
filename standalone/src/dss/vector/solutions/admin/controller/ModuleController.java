package dss.vector.solutions.admin.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

import com.runwaysdk.dataaccess.io.Backup;
import com.runwaysdk.dataaccess.io.Restore;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.logging.LogLevel;
import com.runwaysdk.manager.general.Localizer;
import com.runwaysdk.session.Request;
import com.runwaysdk.system.metadata.MdBusiness;

import dss.vector.solutions.admin.LogConstants;
import dss.vector.solutions.admin.model.Server;

public class ModuleController extends EventProvider implements IModuleController
{
  private static final Integer DEFAULT_TIMEOUT       = 86400;

  private static final String  SESSION_TIME_PROPERTY = "sessionTime";

  private Server               server;

  public ModuleController()
  {
    super();

    this.server = new Server();
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
              backup.addAgents(new RegistryAgent());
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
              restore.addAgent(new RegistryAgent());
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
  public void setLogLevel(final LogLevel level)
  {
    PropertyWriter writer = new PropertyWriter(CommandProperties.getLogLocation());

    boolean success = writer.write(LogConstants.THRESHOLD_PROPERTY, level.name());

    if (success)
    {
      fireEvent(new IModuleEventStrategy()
      {
        @Override
        public void fireEvent(IControllerListener listener)
        {
          listener.changeLogLevel(level);
        }
      });
    }
    else
    {
      fireErrorEvent(Localizer.getMessage("FAIL_CHANGE_LOG"));
    }
  }

  public LogLevel getLogLevel()
  {
    PropertyReader reader = new PropertyReader(CommandProperties.getLogLocation());

    String level = reader.getValue(LogConstants.THRESHOLD_PROPERTY);

    return LogLevel.valueOf(level);
  }

  @Override
  public boolean isServerUp()
  {
    return server.isServerUp();
  }

  @Override
  @Request
  public void rebuildGeoPaths()
  {
    rebuildGeoPathsInTransaction();
  }

  @Transaction
  public void rebuildGeoPathsInTransaction()
  {
    String className = "dss.vector.solutions.geo.generated.GeoEntity";
    String methodName = "buildAllPathsFastInner";

    try
    {
      Class<?> clazz = LoaderDecorator.load(className);
      Method method = clazz.getMethod(methodName);
      method.invoke(null);
    }
    catch (InvocationTargetException e)
    {
      e.printStackTrace();

      fireErrorEvent(e.getCause().getLocalizedMessage());
    }
    catch (Exception e)
    {
      e.printStackTrace();

      fireErrorEvent(e.getLocalizedMessage());
    }
  }

  @Override
  @Request
  public void rebuildTermPaths()
  {
    rebuildTermPathsInTransaction();
  }

  @Transaction
  public void rebuildTermPathsInTransaction()
  {
    String className = "dss.vector.solutions.ontology.AllPaths";
    String methodName = "rebuildAllPathsInner";

    try
    {
      Class<?> clazz = LoaderDecorator.load(className);
      Method method = clazz.getMethod(methodName);
      method.invoke(null);
    }
    catch (InvocationTargetException e)
    {
      fireErrorEvent(e.getCause().getLocalizedMessage());
    }
    catch (Exception e)
    {
      fireErrorEvent(e.getLocalizedMessage());
    }
  }

  @Override
  @Request
  public void deleteGeoPaths()
  {
    this.deleteGeoPathsInTransaction();
  }

  @Transaction
  public void deleteGeoPathsInTransaction()
  {
    String className = "dss.vector.solutions.geo.AllPaths";

    MdBusiness mdBusiness = MdBusiness.getMdBusiness(className);
    mdBusiness.deleteAllTableRecords();
  }

  @Override
  @Request
  public void deleteTermPaths()
  {
    this.deleteTermPathsInTransaction();
  }

  @Transaction
  public void deleteTermPathsInTransaction()
  {
    String className = "dss.vector.solutions.ontology.AllPaths";

    MdBusiness mdBusiness = MdBusiness.getMdBusiness(className);
    mdBusiness.deleteAllTableRecords();
  }

  @Request
  public boolean hasAllPathTables()
  {
    return ( geoPathsContainsValues() && termPathsContainsValues() );
  }

  private boolean termPathsContainsValues()
  {
    String className = "dss.vector.solutions.ontology.AllPaths";
    String methodName = "containsValues";

    try
    {
      Class<?> clazz = LoaderDecorator.load(className);
      Method method = clazz.getMethod(methodName);

      return (Boolean) method.invoke(null);
    }
    catch (InvocationTargetException e)
    {
      fireErrorEvent(e.getCause().getLocalizedMessage());
    }
    catch (Exception e)
    {
      fireErrorEvent(e.getLocalizedMessage());
    }

    return false;
  }

  private boolean geoPathsContainsValues()
  {
    String className = "dss.vector.solutions.geo.AllPaths";
    String methodName = "containsValues";

    try
    {
      Class<?> clazz = LoaderDecorator.load(className);
      Method method = clazz.getMethod(methodName);

      return (Boolean) method.invoke(null);
    }
    catch (InvocationTargetException e)
    {
      fireErrorEvent(e.getCause().getLocalizedMessage());
    }
    catch (Exception e)
    {
      fireErrorEvent(e.getLocalizedMessage());
    }

    return false;
  }

}
