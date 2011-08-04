package dss.vector.solutions.initializer;

import java.lang.reflect.InvocationTargetException;

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
        Builder.this.rebuild(monitor);
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
    catch (InvocationTargetException e)
    {
      e.printStackTrace();

      MessageDialog.openError(new Shell(), Localizer.getMessage("ERROR_TITLE"), e.getCause().getLocalizedMessage());
    }
    catch (Exception e)
    {
      e.printStackTrace();

      MessageDialog.openError(new Shell(), Localizer.getMessage("ERROR_TITLE"), e.getLocalizedMessage());
    }
  }

  @Request
  private void rebuild(IProgressMonitor monitor)
  {
    try
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
    finally
    {
      try
      {
        CacheShutdown.shutdown();
      }
      catch (Exception e)
      {
        // The cache had an exception while trying to shutdown - therefore the
        // the cache still has a live thread. As such, we need to force an exit,
        // otherwise the builder will never exit.
        System.exit(-1);
      }
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
