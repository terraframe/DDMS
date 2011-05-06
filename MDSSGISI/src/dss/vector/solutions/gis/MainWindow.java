package dss.vector.solutions.gis;

import java.util.List;

import net.sf.ehcache.CacheManager;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.GeoHierarchyView;
import dss.vector.solutions.gis.locatedIn.BuildLocatedInAction;
import dss.vector.solutions.gis.shapefile.ImportShapefileAction;

public class MainWindow extends ApplicationWindow implements Reloadable
{
  public static String LOG_DIR = "logs";

  public static String LOG_EXT = ".log";

  public MainWindow()
  {
    super(null);
  }

  @Override
  protected Control createContents(Composite parent)
  {
    Display display = parent.getDisplay();
    Monitor monitor = display.getPrimaryMonitor();

    parent.getShell().setSize(300, 100);
    parent.getShell().setText(GISAdminLocalizer.getMessage("GISI"));

    Rectangle windowRect = parent.getShell().getBounds();
    Rectangle monitorRect = monitor.getBounds();

    int x = ( monitorRect.width - windowRect.width ) / 2;
    int y = ( monitorRect.height - windowRect.height ) / 2;

    parent.getShell().setLocation(x, y);

    Shell splash = this.createSplash(monitor);
    splash.open();

    GeoHierarchyView[] views = GeoHierarchy.getAllViews();

    Composite container = new Composite(parent, SWT.NONE);
    container.setLayout(new FillLayout());

    new ActionContributionItem(new ImportShapefileAction(views)).fill(container);
    new ActionContributionItem(new BuildLocatedInAction()).fill(container);

    splash.close();

    // Bring this shell to the front
    parent.getShell().forceFocus();

    return parent;
  }

  private Shell createSplash(Monitor monitor)
  {
    final Shell splash = new Shell(SWT.ON_TOP);
    splash.setSize(300, 100);
    splash.setLayout(new FillLayout());

    Label label = new Label(splash, SWT.CENTER);
    label.setText(GISAdminLocalizer.getMessage("GISI_INIT"));

    Rectangle splashRect = splash.getBounds();
    Rectangle monitorRect = monitor.getBounds();

    int x = ( monitorRect.width - splashRect.width ) / 2;
    int y = ( monitorRect.height - splashRect.height ) / 2;

    splash.setLocation(x, y);

    return splash;
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
  protected boolean canHandleShellCloseEvent()
  {

    List<CacheManager> knownCacheManagers = CacheManager.ALL_CACHE_MANAGERS;

    while (!knownCacheManagers.isEmpty())
    {
      ( (CacheManager) CacheManager.ALL_CACHE_MANAGERS.get(0) ).shutdown();
    }

    return super.canHandleShellCloseEvent();
  }

  public static void main(String[] args)
  {
    final Display display = Display.getDefault();

    class WindowRunner implements Runnable, Reloadable
    {
      public void run()
      {
        MainWindow window = new MainWindow();
        window.run();
      }
    }

    Realm.runWithDefault(SWTObservables.getRealm(display), new WindowRunner());
  }
}
