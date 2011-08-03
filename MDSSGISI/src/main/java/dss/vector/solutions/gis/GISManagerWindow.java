package dss.vector.solutions.gis;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;

import com.runwaysdk.dataaccess.cache.globalcache.ehcache.CacheShutdown;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Request;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.GeoHierarchyView;
import dss.vector.solutions.gis.locatedIn.BuildLocatedInAction;
import dss.vector.solutions.gis.shapefile.ImportShapefileAction;

public class GISManagerWindow extends ApplicationWindow implements Reloadable
{
  private final class ShutdownRunnable implements IRunnableWithProgress, Reloadable
  {
    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException
    {
      monitor.beginTask(Localizer.getMessage("MANAGER_SHUTDOWN"), IProgressMonitor.UNKNOWN);

      CacheShutdown.shutdown();

      monitor.done();
    }
  }

  public static String LOG_DIR = "logs";

  public static String LOG_EXT = ".log";

  private String       appName;

  public GISManagerWindow()
  {
    this("WERP-A-DERP");
  }

  public GISManagerWindow(String appName)
  {
    super(null);

    this.appName = appName;
  }

  @Override
  protected Control createContents(Composite parent)
  {
    Display display = parent.getDisplay();
    Monitor monitor = display.getPrimaryMonitor();

    parent.getShell().setSize(300, 100);
    parent.getShell().setText(appName + " " + Localizer.getMessage("GISI"));
    parent.getShell().setImage(ImageDescriptor.createFromURL(Object.class.getResource("/icons/globe.png")).createImage());

    Rectangle windowRect = parent.getShell().getBounds();
    Rectangle monitorRect = monitor.getBounds();

    int x = ( monitorRect.width - windowRect.width ) / 2;
    int y = ( monitorRect.height - windowRect.height ) / 2;

    parent.getShell().setLocation(x, y);

    Splash splash = this.createSplash(monitor);
    splash.open();

    try
    {
      GeoHierarchyView[] views = this.getGeoHierarchy();

      Composite container = new Composite(parent, SWT.NONE);
      container.setLayout(new FillLayout());

      new ActionContributionItem(new ImportShapefileAction(appName, views)).fill(container);
      new ActionContributionItem(new BuildLocatedInAction(appName)).fill(container);
    }
    catch (Exception e)
    {
      MessageDialog.openError(this.getShell(), Localizer.getMessage("ERROR_TITLE"), e.getLocalizedMessage());

      e.printStackTrace();

      CacheShutdown.shutdown();

      System.exit(-1);
    }
    finally
    {
      splash.close();
    }

    // Bring this shell to the front
    parent.getShell().forceFocus();

    return parent;
  }

  @Request
  private GeoHierarchyView[] getGeoHierarchy()
  {
    return GeoHierarchy.getAllViews();
  }

  private Splash createSplash(Monitor monitor)
  {
    return new Splash(monitor);
  }

  public void run()
  {
    // Don't return from open() until window closes
    this.setBlockOnOpen(true);

    this.addMenuBar();

    // Open the main window
    this.open();

    // Dispose the display
    Display.getCurrent().dispose();
  }

  @Override
  public boolean close()
  {
    ProgressMonitorDialog dialog = new ProgressMonitorDialog(this.getShell());

    try
    {
      IRunnableWithProgress runnable = new ShutdownRunnable();
      dialog.run(true, false, runnable);
    }
    catch (Exception e)
    {
      MessageDialog.openError(this.getShell(), Localizer.getMessage("ERROR_TITLE"), e.getLocalizedMessage());

      return false;
    }

    return super.close();
  }
}
