package dss.vector.solutions.admin;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;

import com.runwaysdk.dataaccess.transaction.IPropertyListener;
import com.runwaysdk.dataaccess.transaction.ITaskListener;
import com.runwaysdk.manager.controller.IConfiguration;
import com.runwaysdk.manager.controller.IModule;
import com.runwaysdk.manager.general.IMenuManager;
import com.runwaysdk.manager.general.IWindow;
import com.runwaysdk.manager.general.Localizer;
import com.runwaysdk.manager.general.MainWindow;

import dss.vector.solutions.admin.action.AllPathAction;
import dss.vector.solutions.admin.controller.CommandProperties;
import dss.vector.solutions.admin.controller.IModuleController;
import dss.vector.solutions.admin.controller.MasterConfiguration;
import dss.vector.solutions.admin.controller.ModuleController;
import dss.vector.solutions.admin.controller.PropertyReader;
import dss.vector.solutions.admin.controller.SlaveConfiguration;

public class MDSSModule implements IModule, IPropertyListener
{
  public static final String VERSION_PROPERTY = "ddms_manager_version";

  public static final String VERSION          = "1.02";

  private IModuleController  controller;

  private IWindow            window;

  public MDSSModule()
  {
    this.controller = new ModuleController(this);

    // Window is set in the init method which is called from the super class
    this.window = null;
  }

  @Override
  public void init(IWindow window)
  {
    this.window = window;
  }

  @Override
  public void generateMenu(IMenuManager manager)
  {
    MenuManager toolsMenu = manager.getMenu(Localizer.getMessage("TOOLS_MENU"));
    toolsMenu.add(new AllPathAction(this));

    manager.addMenu(toolsMenu);
  }

  public void error(String msg)
  {
    MessageDialog.openError(window.getShell(), Localizer.getMessage("ERROR_TITLE"), msg);
  }

  public void asyncError(final Throwable t)
  {
    Display display = this.window.getDisplay();

    display.asyncExec(new Runnable()
    {
      @Override
      public void run()
      {
        MDSSModule.this.error(t.getLocalizedMessage());
      }
    });
  }

  @Override
  public void validateAction(String actionName)
  {
  }

  @Override
  public void shellCloseEvent()
  {
  }

  @Override
  public StatusLineManager createStatusLineManager()
  {
    return null;
  }

  @Override
  public boolean hasStatusLineManager()
  {
    return false;
  }

  @Override
  public ITaskListener getExportListener()
  {
    return null;
  }

  @Override
  public ITaskListener getImportListener()
  {
    ITaskListener listener = new ITaskListener()
    {
      @Override
      public void taskStart(String name, int amount)
      {
        // TODO Auto-generated method stub

      }

      @Override
      public void taskProgress(int percent)
      {
        // TODO Auto-generated method stub

      }

      @Override
      public void start()
      {
        deleteAllPathTables();
      }

      @Override
      public void done(boolean success)
      {
        rebuildAllPathTables();
      }
    };

    return listener;
  }

  @Override
  public Map<String, String> getExportProperties()
  {
    Map<String, String> map = new HashMap<String, String>();

    map.put(MDSSModule.VERSION_PROPERTY, MDSSModule.VERSION.toString());

    return map;
  }

  @Override
  public Collection<IPropertyListener> getImportPropertyListeners()
  {
    Collection<IPropertyListener> collection = new LinkedList<IPropertyListener>();
    collection.add(this);

    return collection;
  }

  @Override
  public void handleProperty(String name, String value)
  {
    if (name.equals(MDSSModule.VERSION_PROPERTY) && !value.equals(MDSSModule.VERSION))
    {
      String msg = Localizer.getMessage("MANAGER_VERSION_ERROR");

      throw new RuntimeException(msg);
    }
  }

  private void deleteAllPathTables()
  {
    // Before performing an import we need to delete the all paths tables
    window.getDisplay().syncExec(new Runnable()
    {
      @Override
      public void run()
      {
        ProgressMonitorDialog dialog = new ProgressMonitorDialog(window.getShell());

        try
        {
          dialog.run(true, false, new IRunnableWithProgress()
          {
            @Override
            public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException
            {
              monitor.beginTask(Localizer.getMessage("DELETE_ALL_PATHS_GEO"), IProgressMonitor.UNKNOWN);

              controller.deleteGeoPaths();

              monitor.beginTask(Localizer.getMessage("DELETE_ALL_PATHS_TERM"), IProgressMonitor.UNKNOWN);

              controller.deleteTermPaths();

              monitor.done();
            }
          });
        }
        catch (InvocationTargetException e)
        {
          error(e.getCause().getLocalizedMessage());
        }
        catch (InterruptedException e)
        {
          error(e.getLocalizedMessage());
        }
      }
    });
  }

  public void rebuildAllPathTables()
  {
    // window.getDisplay().syncExec(new Runnable()
    // {
    // @Override
    // public void run()
    // {
    try
    {
      ProgressMonitorDialog dialog = new ProgressMonitorDialog(window.getShell());
      dialog.run(true, false, new IRunnableWithProgress()
      {
        @Override
        public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException
        {
          monitor.beginTask(Localizer.getMessage("REBUILD_ALL_PATHS_GEO"), IProgressMonitor.UNKNOWN);

          controller.rebuildGeoPaths();

          monitor.beginTask(Localizer.getMessage("REBUILD_ALL_PATHS_TERM"), IProgressMonitor.UNKNOWN);

          controller.rebuildTermPaths();

          monitor.done();
        }
      });
    }
    catch (InvocationTargetException e)
    {
      error(e.getCause().getLocalizedMessage());
    }
    catch (InterruptedException e)
    {
      error(e.getLocalizedMessage());
    }
    // }
    // });
  }

  public static boolean isMaster()
  {
    PropertyReader reader = new PropertyReader(CommandProperties.getInstall());
    String value = reader.getValue("master");

    return ( value != null && value.contains("true") );
  }

  public static void main(String[] args)
  {
    Locale locale = Locale.getDefault();

    if (args.length > 0)
    {
      String[] localeInfo = args[0].split("_");
      switch (localeInfo.length)
      {
        case 1:
          locale = new Locale(localeInfo[0]);
          break;
        case 2:
          locale = new Locale(localeInfo[0], localeInfo[1]);
          break;
        case 3:
          locale = new Locale(localeInfo[0], localeInfo[1], localeInfo[2]);
          break;
      }
    }

    Localizer.setInstance(locale);

    final Display display = Display.getDefault();

    Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable()
    {
      public void run()
      {
        IConfiguration configuration = new SlaveConfiguration();

        if (isMaster())
        {
          configuration = new MasterConfiguration();
        }

        MDSSModule module = new MDSSModule();

        MainWindow window = new MainWindow(configuration, module);
        window.run();
      }
    });
  }
}
