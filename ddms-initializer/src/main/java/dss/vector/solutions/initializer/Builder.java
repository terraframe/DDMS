package dss.vector.solutions.initializer;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;

import com.runwaysdk.dataaccess.cache.ObjectCache;
import com.runwaysdk.dataaccess.cache.globalcache.ehcache.CacheShutdown;
import com.runwaysdk.dataaccess.transaction.ITaskListener;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Request;

import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.ontology.AllPaths;

public class Builder implements Reloadable
{
  private static Log log = LogFactory.getLog(Builder.class);

  private final class RebuildRunnable implements IRunnableWithProgress, Reloadable
  {
    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException
    {
      monitor.beginTask(Localizer.getMessage("STATUS_CHECK") + " " + appName, IProgressMonitor.UNKNOWN);

      ITaskListener listener = new MonitorTaskListener(monitor);

      ObjectCache.addListener(listener);

      try
      {
        try
        {
          Builder.this.rebuild(monitor);
        }
        finally
        {
          CacheShutdown.shutdown();
        }
      }
      finally
      {
        ObjectCache.removeListener(listener);
      }
    }
  }

  private ProgressMonitorDialog dialog;

  private String                appName;

  public Builder(String appName)
  {
    Display display = new Display();
    Monitor monitor = display.getPrimaryMonitor();

    Shell shell = new Shell(display);

    Rectangle splashRect = shell.getBounds();
    Rectangle displayRect = monitor.getBounds();

    int x = ( displayRect.width - splashRect.width ) / 2;
    int y = ( displayRect.height - splashRect.height ) / 2;

    shell.setLocation(x, y);

    this.appName = appName;
    this.dialog = new LocalizedProgressMonitorDialog(shell);
  }

  public void build()
  {
    try
    {
      IRunnableWithProgress progress = new RebuildRunnable();
      dialog.run(true, false, progress);
    }
    catch (Exception e)
    {
      int errorCode = -1;

      Throwable throwable = e;

      if (throwable instanceof InvocationTargetException)
      {
        throwable = throwable.getCause();
      }

//      if (throwable instanceof CacheException)
//      {
//        if (throwable.getCause() instanceof InvalidClassException)
//        {
//          throwable = new RuntimeException(Localizer.getMessage("CACHE_MISMATCH"), throwable);
//          errorCode = 0;
//        }
//      }

      if (throwable instanceof NoSuchMethodError)
      {
        throwable = new RuntimeException(Localizer.getMessage("RUNWAY_VERSION_MISMATCH"), throwable);
        errorCode = 0;
      }

      log.error(this, throwable);

      MessageDialog.openError(new Shell(), Localizer.getMessage("ERROR_TITLE"), throwable.getLocalizedMessage());

      System.exit(errorCode);
    }
  }

  @Request
  private void rebuild(IProgressMonitor monitor)
  {
    boolean hasAllPaths = this.hasAllPaths();

    if (!hasAllPaths)
    {
      monitor.beginTask(Localizer.getMessage("REBUILD_GEO_PATHS"), IProgressMonitor.UNKNOWN);

      this.rebuildGeoPaths();

      monitor.beginTask(Localizer.getMessage("REBUILD_TERM_PATHS"), IProgressMonitor.UNKNOWN);

      this.rebuildTermPaths();
    }
  }

  public boolean hasAllPaths()
  {
    return ( AllPaths.containsValues() && dss.vector.solutions.geo.AllPaths.containsValues() );
  }

  public void rebuildGeoPaths()
  {
    GeoEntity.buildAllPathsFast();
  }

  private void rebuildTermPaths()
  {
    AllPaths.rebuildAllPaths();
  }
}
