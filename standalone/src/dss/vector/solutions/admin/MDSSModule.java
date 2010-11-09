package dss.vector.solutions.admin;

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;

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

public class MDSSModule implements IModule, IControllerListener
{
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
          return new ControlView(controller);
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

  private IModuleController controller;

  private StatusLineManager statusManager;

  private IWindow           window;

  public MDSSModule(IModuleController controller)
  {
    this.controller = controller;
    this.controller.addListener(this);
    this.statusManager = new StatusLineManager();
    this.window = null;
  }

  @Override
  public void generateMenu(IMenuManager manager)
  {
    MenuManager fileMenu = manager.getMenu(Localizer.getMessage("FILE_MENU"));
    fileMenu.add(new ControlAction());

    MenuManager logMenu = manager.getMenu(Localizer.getMessage("LOG_MENU"));

    MenuManager setLogMenu = manager.getMenu(Localizer.getMessage("SET_LOG"));    
    setLogMenu.add(new LogAction(Localizer.getMessage("TRACE"), LogLevel.TRACE, controller));
    setLogMenu.add(new LogAction(Localizer.getMessage("DEBUG"), LogLevel.DEBUG, controller));
    setLogMenu.add(new LogAction(Localizer.getMessage("INFO"), LogLevel.INFO, controller));
    setLogMenu.add(new LogAction(Localizer.getMessage("WARN"), LogLevel.WARN, controller));
    setLogMenu.add(new LogAction(Localizer.getMessage("ERROR"), LogLevel.ERROR, controller));
    setLogMenu.add(new LogAction(Localizer.getMessage("FATAL"), LogLevel.FATAL, controller));
    
    logMenu.add(setLogMenu);
    manager.addMenu(logMenu);
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
        return new ControlView(controller);
      }

      @Override
      public boolean isClosable()
      {
        return false;
      }
    };

    window.show(strategy);
  }

  @Override
  public void lock()
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void unlock()
  {
    // TODO Auto-generated method stub
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
        case 2:
          locale = new Locale(localeInfo[0], localeInfo[1]);
        case 3:
          locale = new Locale(localeInfo[0], localeInfo[1], localeInfo[2]);
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
  public void validateAction(String actionName)
  {
    if(actionName.contains("IMPORT") || actionName.contains("EXPORT"))
    {
      if(controller.isServerUp())
      {
        String msg = Localizer.getMessage("INVALID_ACTION");
        
        throw new RuntimeException(msg);
      }
    }
  }
}
