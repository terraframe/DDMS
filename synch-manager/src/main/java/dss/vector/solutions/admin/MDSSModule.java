package dss.vector.solutions.admin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.StatusLineManager;
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
import com.runwaysdk.session.Request;
import com.runwaysdk.util.FileIO;

import dss.vector.solutions.admin.controller.IModuleController;
import dss.vector.solutions.admin.controller.ModuleController;
import dss.vector.solutions.admin.controller.VersionJspReader;

public class MDSSModule implements IModule, IPropertyListener
{
  private final class ErrorRunnable implements Runnable
  {
    private final Throwable t;

    private ErrorRunnable(Throwable t)
    {
      this.t = t;
    }

    @Override
    public void run()
    {
      MDSSModule.this.error(t.getLocalizedMessage());
    }
  }

  private final class SynchronizationTaskListener implements ITaskListener
  {
    @Override
    public void taskStart(String name, int amount)
    {
    }

    @Override
    public void taskProgress(int percent)
    {
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

      updateCSS();
    }
  }

  private final class ProgressRunnable implements Runnable
  {
    private IRunnableWithProgress runnable;

    public ProgressRunnable(IRunnableWithProgress runnable)
    {
      this.runnable = runnable;
    }

    @Override
    public void run()
    {
      try
      {
        ProgressMonitorDialog dialog = new ProgressMonitorDialog(window.getShell());
        dialog.run(true, false, runnable);
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
  }

  private final class DeleteRunnable implements IRunnableWithProgress
  {
    @Request
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException
    {
      monitor.beginTask(Localizer.getMessage("DELETE_ALL_PATHS_GEO"), IProgressMonitor.UNKNOWN);

      controller.deleteGeoPaths();

      monitor.beginTask(Localizer.getMessage("DELETE_ALL_PATHS_TERM"), IProgressMonitor.UNKNOWN);

      controller.deleteTermPaths();

      controller.deletePermissionCache();

      monitor.done();
    }
  }

  private final class RebuildRunnable implements IRunnableWithProgress
  {
    @Request
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException
    {
      monitor.beginTask(Localizer.getMessage("REBUILD_ALL_PATHS_GEO"), IProgressMonitor.UNKNOWN);

      controller.rebuildGeoPaths();

      monitor.beginTask(Localizer.getMessage("REBUILD_ALL_PATHS_TERM"), IProgressMonitor.UNKNOWN);

      controller.rebuildTermPaths();

      monitor.done();
    }
  }

  public static final String  VERSION_PROPERTY         = "ddms_manager_version";

  public static final String  INSTALL_VERSION_PROPERTY = "original_install_version";

  public static final String  VERSION                  = "1.02";

  public static String        DEFAULT_TOMCAT           = "C:/MDSS/tomcat6/";

  private IModuleController   controller;

  private IWindow             window;

  private String              appName;

  private String              tomcatRoot;

  private IConfiguration      configuration;

  private Map<String, String> properties;

  public MDSSModule(String appName)
  {
    this(DEFAULT_TOMCAT, appName);
  }

  public MDSSModule(String tomcatRoot, String appName)
  {
    this.tomcatRoot = tomcatRoot;
    this.appName = appName;
    this.controller = new ModuleController(this);
    this.properties = new HashMap<String, String>();

    // Window is set in the init method which is called from the super class
    this.window = null;
    this.configuration = null;
  }

  @Override
  public void init(IWindow window)
  {
    this.window = window;
    this.configuration = window.getConfiguration();
  }

  public IConfiguration getConfiguration()
  {
    return configuration;
  }

  @Override
  public void generateMenu(IMenuManager manager)
  {
    // MenuManager toolsMenu =
    // manager.getMenu(Localizer.getMessage("TOOLS_MENU"));
    // toolsMenu.add(new AllPathAction(this));
    //
    // manager.addMenu(toolsMenu);
  }

  public void error(String msg)
  {
    MessageDialog.openError(window.getShell(), Localizer.getMessage("ERROR_TITLE"), msg);
  }

  public void asyncError(final Throwable t)
  {
    Display display = this.window.getDisplay();

    display.asyncExec(new ErrorRunnable(t));
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
    ITaskListener listener = new SynchronizationTaskListener();

    return listener;
  }

  @Override
  public Map<String, String> getExportProperties()
  {
    Map<String, String> map = new HashMap<String, String>();

    map.put(MDSSModule.VERSION_PROPERTY, MDSSModule.VERSION.toString());
    map.put(MDSSModule.INSTALL_VERSION_PROPERTY, this.getOriginalVersion());

    return map;
  }

  public String getOriginalVersion()
  {
    String jspPath = this.tomcatRoot + "/webapps/" + this.appName + "/WEB-INF/originalVersion.jsp";
    String propertyName = "original_version";

    return new VersionJspReader(jspPath).getValue(propertyName);
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
    this.properties.put(name, value);
  }

  @Override
  public void handlePropertiesFinished()
  {
    if (!this.properties.containsKey(MDSSModule.VERSION_PROPERTY) || !this.properties.get(MDSSModule.VERSION_PROPERTY).equals(MDSSModule.VERSION))
    {
      String msg = Localizer.getMessage("MANAGER_VERSION_ERROR");

      throw new RuntimeException(msg);
    }

    if (!this.properties.containsKey(MDSSModule.INSTALL_VERSION_PROPERTY) || !this.properties.get(MDSSModule.INSTALL_VERSION_PROPERTY).equals(this.getOriginalVersion()))
    {
      String msg = Localizer.getMessage("ORIGINAL_VERSION_ERROR");

      throw new RuntimeException(msg);
    }
  }

  private void deleteAllPathTables()
  {
    // Before performing an import we need to delete the all paths tables
    window.getDisplay().asyncExec(new ProgressRunnable(new DeleteRunnable()));
  }

  public void rebuildAllPathTables()
  {
    window.getDisplay().asyncExec(new ProgressRunnable(new RebuildRunnable()));
  }

  private void updateCSS()
  {
    try
    {
      String regex = "/\\w+/imgs/";
      String replacement = "/" + appName + "/imgs/";

      File file = new File(tomcatRoot + File.separator + "webapps" + File.separator + appName + File.separator + "css/style.css");
      String data = FileIO.readString(file);
      String replaced = data.replaceAll(regex, replacement);
      FileIO.write(file, replaced);
    }
    catch (IOException e)
    {
      this.error(e.getLocalizedMessage());
    }
  }

}
