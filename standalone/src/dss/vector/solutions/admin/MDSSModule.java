package dss.vector.solutions.admin;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Display;

import com.runwaysdk.dataaccess.transaction.IPropertyListener;
import com.runwaysdk.dataaccess.transaction.ITaskListener;
import com.runwaysdk.logging.LogLevel;
import com.runwaysdk.manager.controller.IConfiguration;
import com.runwaysdk.manager.controller.IModule;
import com.runwaysdk.manager.controller.IViewStrategy;
import com.runwaysdk.manager.general.IMenuManager;
import com.runwaysdk.manager.general.IWindow;
import com.runwaysdk.manager.general.Localizer;
import com.runwaysdk.manager.general.MainWindow;
import com.runwaysdk.manager.view.IViewPart;

import dss.vector.solutions.admin.controller.CommandProperties;
import dss.vector.solutions.admin.controller.IControllerListener;
import dss.vector.solutions.admin.controller.IModuleController;
import dss.vector.solutions.admin.controller.MasterConfiguration;
import dss.vector.solutions.admin.controller.ModuleController;
import dss.vector.solutions.admin.controller.PropertyReader;
import dss.vector.solutions.admin.controller.SlaveConfiguration;
import dss.vector.solutions.admin.view.ControlView;

public class MDSSModule implements IModule, IControllerListener, IPropertyListener
{
  public static final String VERSION_PROPERTY = "ddms_manager_version";

  public static final String VERSION          = "1.02";

  class ControlAction extends Action
  {
    public ControlAction()
    {
      this.setText(Localizer.getMessage("CONTROL"));
    }

    @Override
    public void run()
    {
      IViewStrategy strategy = new IViewStrategy()
      {
        public String getTitle()
        {
          return Localizer.getMessage("CONTROL");
        }

        public String getKey()
        {
          return "CONTROL";
        }

        public IViewPart getContent()
        {
          return new ControlView(controller, MDSSModule.this);
        }

        @Override
        public boolean isClosable()
        {
          return false;
        }
      };

      window.show(strategy);
    }
  }

  class AllPathAction extends Action
  {
    public AllPathAction()
    {
      this.setText(Localizer.getMessage("REBUILD_ALL_PATH_TABLES"));
    }

    @Override
    public void run()
    {
      rebuildAllPathTables();
    }
  }

  private IModuleController                  controller;

  private StatusLineManager                  statusManager;

  private IWindow                            window;

  private LinkedHashMap<LogLevel, LogAction> actions;

  public MDSSModule(IModuleController controller)
  {
    this.controller = controller;
    this.controller.addListener(this);

    this.statusManager = new StatusLineManager();
    this.window = null;
    this.actions = new LinkedHashMap<LogLevel, LogAction>();

    this.actions.put(LogLevel.TRACE, new LogAction(Localizer.getMessage("TRACE"), LogLevel.TRACE, controller));
    this.actions.put(LogLevel.DEBUG, new LogAction(Localizer.getMessage("DEBUG"), LogLevel.DEBUG, controller));
    this.actions.put(LogLevel.INFO, new LogAction(Localizer.getMessage("INFO"), LogLevel.INFO, controller));
    this.actions.put(LogLevel.WARN, new LogAction(Localizer.getMessage("WARN"), LogLevel.WARN, controller));
    this.actions.put(LogLevel.ERROR, new LogAction(Localizer.getMessage("ERROR"), LogLevel.ERROR, controller));
    this.actions.put(LogLevel.FATAL, new LogAction(Localizer.getMessage("FATAL"), LogLevel.FATAL, controller));

    this.changeLogLevel(controller.getLogLevel());
  }

  @Override
  public void generateMenu(IMenuManager manager)
  {
    MenuManager fileMenu = manager.getMenu(Localizer.getMessage("FILE_MENU"));
    fileMenu.add(new ControlAction());

    MenuManager toolsMenu = manager.getMenu(Localizer.getMessage("TOOLS_MENU"));

    MenuManager setLogMenu = manager.getMenu(Localizer.getMessage("SET_LOG"));

    for (LogLevel key : actions.keySet())
    {
      setLogMenu.add(actions.get(key));
    }

    toolsMenu.add(setLogMenu);
    toolsMenu.add(new AllPathAction());
    manager.addMenu(toolsMenu);
  }

  @Override
  public StatusLineManager createStatusLineManager()
  {
    return statusManager;
  }

  public void setStatus(final String msg)
  {
    final Display display = Display.getDefault();

    display.asyncExec(new Runnable()
    {
      @Override
      public void run()
      {
        statusManager.setMessage(msg);
      }
    });
  }

  public void clearStatus()
  {
    this.setStatus(null);
  }

  @Override
  public boolean hasStatusLineManager()
  {
    return true;
  }

  @Override
  public void beforeServerStateChange(boolean state)
  {
    setStatus(state ? Localizer.getMessage("START_SERVER") : Localizer.getMessage("STOP_SERVER"));
  }

  @Override
  public void error(String msg)
  {
    // DO NOTHING
  }

  @Override
  public void execute(IRunnableWithProgress runnable) throws InvocationTargetException, InterruptedException
  {
    // DO NOTHING
  }

  @Override
  public void serverStateChange(boolean state)
  {
    clearStatus();
  }

  @Override
  public void message(String msg)
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void init(IWindow window)
  {
    this.window = window;

    IViewStrategy strategy = new IViewStrategy()
    {
      public String getTitle()
      {
        return Localizer.getMessage("CONTROL");
      }

      public String getKey()
      {
        return "CONTROL";
      }

      public IViewPart getContent()
      {
        return new ControlView(controller, MDSSModule.this);
      }

      @Override
      public boolean isClosable()
      {
        return false;
      }
    };

    window.show(strategy);
  }

  public static boolean isMaster()
  {
    PropertyReader reader = new PropertyReader(CommandProperties.getInstall());
    String value = reader.getValue("master");

    return ( value != null && value.contains("true") );
  }

  @Override
  public void validateAction(String actionName)
  {
    if (actionName.contains("IMPORT") || actionName.contains("EXPORT"))
    {
      if (controller.isServerUp())
      {
        String msg = Localizer.getMessage("INVALID_ACTION");

        throw new RuntimeException(msg);
      }
    }
  }

  @Override
  public void changeLogLevel(LogLevel level)
  {
    for (LogLevel key : actions.keySet())
    {
      actions.get(key).setImageDescriptor(null);
    }

    actions.get(level).setImageDescriptor(ImageDescriptor.createFromFile(null, CommandProperties.getIconDirectory() + File.separator + "checkbox.png"));
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
          e.printStackTrace();

          error(e.getCause().getLocalizedMessage());
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();

          error(e.getLocalizedMessage());
        }
      }
    });
  }

  public void rebuildAllPathTables()
  {
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
          e.printStackTrace();

          error(e.getCause().getLocalizedMessage());
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();

          error(e.getLocalizedMessage());
        }
      }
    });
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

        MDSSModule module = new MDSSModule(new ModuleController());

        MainWindow window = new MainWindow(configuration, module);
        window.run();
      }
    });
  }

  @Override
  public void shellCloseEvent()
  {
    this.controller.close();
  }
}
