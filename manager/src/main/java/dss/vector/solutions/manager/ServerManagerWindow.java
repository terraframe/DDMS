/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.manager;

import it.sauronsoftware.junique.AlreadyLockedException;
import it.sauronsoftware.junique.JUnique;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.Thread.UncaughtExceptionHandler;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Locale;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Monitor;

import dss.vector.solutions.manager.action.BackupRestoreAction;
import dss.vector.solutions.manager.action.ChangeSettingAction;
import dss.vector.solutions.manager.action.UninstallAction;
import dss.vector.solutions.manager.action.ExitAction;
import dss.vector.solutions.manager.action.GeoAction;
import dss.vector.solutions.manager.action.SyncAction;
import dss.vector.solutions.manager.server.IServer;
import dss.vector.solutions.manager.server.IServerListener;
import dss.vector.solutions.manager.server.Server;
import dss.vector.solutions.manager.server.ServerStatus;

public class ServerManagerWindow extends ApplicationWindow implements IServerListener, PropertyChangeListener, UncaughtExceptionHandler, IApplicationUninstallManager
{
  private static final Point                 DIMENSION        = new Point(300, 275);

  /**
   * 
   */
  public static final long                   serialVersionUID = -6658235304142152484L;

  private Button                             startButton;

  private Button                             stopButton;

  private Button                             refreshButton;

  private Composite                          composite;

  private IServer                            server;

  private Label                              status;

  private ComboViewer                        application;

  private Collection<ActionContributionItem> items;

  private ManagerContextBean                 context;

  private String[]                           applications;

  private boolean                            hide;

  public ServerManagerWindow()
  {
    super(null);

    this.hide = true;
    this.context = new ManagerContextBean();
    this.context.addPropertyChangeListener("processRunning", this);

    this.server = new Server();
    this.server.addListener(this);

    this.applications = this.loadApplicationInformation();
  }

  @Override
  protected Control createContents(Composite parent)
  {
    Display display = parent.getDisplay();
    Monitor monitor = display.getPrimaryMonitor();

    parent.getShell().setSize(DIMENSION);
    parent.getShell().setText(Localizer.getMessage("APPLICATION_NAME"));
    parent.getShell().setImage(ImageDescriptor.createFromURL(Object.class.getResource("/icons/Manager.jpg")).createImage());

    Rectangle windowRect = parent.getShell().getBounds();
    Rectangle monitorRect = monitor.getBounds();

    int x = ( monitorRect.width - windowRect.width ) / 2;
    int y = ( monitorRect.height - windowRect.height ) / 2;

    parent.getShell().setLocation(x, y);

    SimpleSplash splash = this.createSplash(monitor);
    splash.open();

    try
    {
      composite = new Composite(parent, SWT.FILL);
      composite.setSize(DIMENSION);
      composite.setLayout(new FormLayout());

      FormData serverData = new FormData();
      serverData.top = new FormAttachment(0, 0);
      serverData.bottom = new FormAttachment(35, 0);
      serverData.left = new FormAttachment(0, 0);
      serverData.right = new FormAttachment(100, 0);

      Group serverGroup = new Group(composite, SWT.SHADOW_IN);
      serverGroup.setText(Localizer.getMessage("SERVER"));
      serverGroup.setLayout(new FormLayout());
      serverGroup.setLayoutData(serverData);

      this.createServerContent(serverGroup);

      FormData installData = new FormData();
      installData.top = new FormAttachment(serverGroup);
      installData.bottom = new FormAttachment(100, 0);
      installData.left = new FormAttachment(0, 0);
      installData.right = new FormAttachment(100, 0);

      Group installGroup = new Group(composite, SWT.NONE);
      installGroup.setText(Localizer.getMessage("INSTALL"));
      installGroup.setLayout(new FormLayout());
      installGroup.setLayoutData(installData);

      this.createInstallContent(installGroup);

      this.bind();

      this.loadSelection();
      this.server.refresh();
    }
    catch (RuntimeException e)
    {
      splash.close();

      MessageDialog.openError(splash.getShell(), Localizer.getMessage("ERROR_TITLE"), e.getLocalizedMessage());

      throw e;
    }
    finally
    {
      if (!splash.isDisposed())
      {
        splash.close();
      }
    }

    return parent;
  }

  private void loadSelection()
  {
    try
    {
      Object element = this.application.getElementAt(0);
      this.application.setSelection(new StructuredSelection(element));
    }
    catch (Exception e)
    {
      // Do nothing
    }
  }

  private void createServerContent(Composite parent)
  {
    FormData buttonsData = new FormData();
    buttonsData.top = new FormAttachment(0, 5);

    Composite buttons = new Composite(parent, SWT.FILL);
    buttons.setLayout(new FillLayout());
    buttons.setLayoutData(buttonsData);

    startButton = new Button(buttons, SWT.BORDER);
    startButton.setText(Localizer.getMessage("START"));
    startButton.setImage(ImageDescriptor.createFromURL(Object.class.getResource("/icons/Play-icon.png")).createImage());
    startButton.addListener(SWT.Selection, new Listener()
    {
      @Override
      public void handleEvent(Event arg0)
      {
        start();
      }
    });

    stopButton = new Button(buttons, SWT.BORDER);
    stopButton.setText(Localizer.getMessage("STOP"));
    stopButton.setImage(ImageDescriptor.createFromURL(Object.class.getResource("/icons/Remove-icon.png")).createImage());
    stopButton.addListener(SWT.Selection, new Listener()
    {
      @Override
      public void handleEvent(Event arg0)
      {
        server.stopServer();
      }
    });

    refreshButton = new Button(buttons, SWT.BORDER);
    refreshButton.setText(Localizer.getMessage("REFRESH"));
    refreshButton.setVisible(false);
    refreshButton.addListener(SWT.Selection, new Listener()
    {
      @Override
      public void handleEvent(Event arg0)
      {
        server.refresh();
      }
    });

    FormData statusData = new FormData();
    statusData.bottom = new FormAttachment(100, 0);
    statusData.left = new FormAttachment(0, 0);
    statusData.right = new FormAttachment(100, 0);

    this.status = new Label(parent, SWT.BOLD);
    this.status.setLayoutData(statusData);
  }

  private void createInstallContent(Composite parent)
  {
    FormData compositeData = new FormData();
    compositeData.top = new FormAttachment(0, 5);
    compositeData.left = new FormAttachment(0, 0);
    compositeData.right = new FormAttachment(100, 0);

    Composite composite = new Composite(parent, SWT.NONE);
    composite.setLayout(new GridLayout(2, false));
    composite.setLayoutData(compositeData);

    new Label(composite, SWT.NULL).setText(Localizer.getMessage("APPLICATION") + ": ");

    this.application = new ComboViewer(composite, SWT.BORDER | SWT.READ_ONLY);
    this.application.getControl().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    this.application.setContentProvider(new ArrayContentProvider());
    this.application.setLabelProvider(new StringLabelProvider());
    this.application.setInput(this.applications);

    FormData actionsData = new FormData();
    actionsData.top = new FormAttachment(composite);
    actionsData.bottom = new FormAttachment(100, 0);
    actionsData.left = new FormAttachment(0, 5);
    actionsData.right = new FormAttachment(100, 0);

    Group actions = new Group(parent, SWT.NONE);
    actions.setText(Localizer.getMessage("ACTIONS"));
    actions.setLayoutData(actionsData);
    actions.setLayout(new FillLayout(SWT.HORIZONTAL));

    this.items = new LinkedList<ActionContributionItem>();
    this.items.add(new ActionContributionItem(new BackupRestoreAction(this.context)));
    this.items.add(new ActionContributionItem(new GeoAction(this.context, this)));
    this.items.add(new ActionContributionItem(new SyncAction(this.context)));
    this.items.add(new ActionContributionItem(new ChangeSettingAction(this.context)));
    this.items.add(new ActionContributionItem(new UninstallAction(this.context, this)));

    for (ActionContributionItem item : this.items)
    {
      item.fill(actions);
    }
  }

  private void bind()
  {
    DataBindingContext bindingContext = new DataBindingContext(SWTObservables.getRealm(Display.getDefault()));

    IObservableValue uiElement = ViewersObservables.observeSingleSelection(this.application);
    IObservableValue modelElement = BeanProperties.value(ManagerContextBean.class, "application").observe(context);

    bindingContext.bindValue(uiElement, modelElement, null, null);
  }

  private SimpleSplash createSplash(Monitor monitor)
  {
    return new SimpleSplash(monitor);
  }

  @Override
  protected MenuManager createMenuManager()
  {
    MenuManager fileMenu = new MenuManager(Localizer.getMessage("FILE"));
    fileMenu.add(new ExitAction(this));

    MenuManager main = new MenuManager("");
    main.add(fileMenu);

    return main;
  }

  private void updateState(ServerStatus status)
  {
    this.context.setServerStatus(status);
    this.disableWidgets();

    if (status.equals(ServerStatus.STARTED))
    {
      this.startButton.setEnabled(false);
      this.stopButton.setEnabled(true);
      this.setServerStatus(Localizer.getMessage("STARTED"));
    }
    else if (status.equals(ServerStatus.STOPPED))
    {
      this.startButton.setEnabled(true);
      this.stopButton.setEnabled(false);
      this.application.getCombo().setEnabled(true);

      for (ActionContributionItem item : items)
      {
        ( (Button) item.getWidget() ).setEnabled(true);
      }

      this.setServerStatus(Localizer.getMessage("STOPPED"));
    }
    else if (status.equals(ServerStatus.STARTING))
    {
      this.setServerStatus(Localizer.getMessage("STARTING"));
    }
    else if (status.equals(ServerStatus.STOPPING))
    {
      this.setServerStatus(Localizer.getMessage("STOPPING"));
    }
  }

  private void setServerStatus(String message)
  {
    this.status.setText(Localizer.getMessage("SERVER_STATUS") + " " + message);
  }

  private void disableWidgets()
  {
    this.startButton.setEnabled(false);
    this.stopButton.setEnabled(false);
    this.application.getCombo().setEnabled(false);

    for (ActionContributionItem item : items)
    {
      ( (Button) item.getWidget() ).setEnabled(false);
    }
  }

  private void start()
  {
    try
    {
      ServerManagerWindow.this.disableWidgets();
      ServerManagerWindow.this.setServerStatus(Localizer.getMessage("STARTING"));

      this.hide = false;

      try
      {
        new ServerInitializer(applications, context, server, this).run();
      }
      finally
      {
        this.hide = true;
      }

    }
    catch (Exception e)
    {
      MessageDialog.openError(composite.getShell(), Localizer.getMessage("ERROR_TITLE"), e.getLocalizedMessage());

      server.refresh();
    }
  }

  @Override
  public void error(final String msg)
  {
    Runnable runnable = new Runnable()
    {
      @Override
      public void run()
      {
        MessageDialog.openError(composite.getShell(), Localizer.getMessage("ERROR_TITLE"), msg);
      }
    };

    this.asyncExec(runnable);
  }

  @Override
  public void error(final Throwable throwable)
  {
    this.asyncExec(new Runnable()
    {
      @Override
      public void run()
      {
        error(throwable.getLocalizedMessage());
      }
    });
  }

  @Override
  public synchronized void serverStateChange(final ServerStatus state)
  {
    Runnable runnable = new Runnable()
    {
      @Override
      public void run()
      {
        updateState(state);
      }
    };

    this.asyncExec(runnable);
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt)
  {
    if (this.hide && context.isProcessRunning())
    {
      this.getShell().setVisible(false);
    }
    else
    {
      this.getShell().setVisible(true);
    }
  }

  @Override
  public int getApplicationCount()
  {
    return this.applications.length;
  }

  @Override
  public void onUninstall(String application)
  {
    // OK Button selected do something
    this.applications = this.loadApplicationInformation();
    this.application.setInput(this.applications);
    this.loadSelection();
  }

  @Override
  public String[] getApplications()
  {
    return this.applications;
  }

  private void asyncExec(Runnable runnable)
  {
    Display display = composite.getDisplay();
    display.asyncExec(runnable);
  }

  public void run()
  {
    // Don't return from open() until window closes
    this.setBlockOnOpen(true);

    // Open the main window
    this.open();

    // Dispose the display
    Display.getCurrent().dispose();
  }

  @Override
  protected boolean canHandleShellCloseEvent()
  {
    if (!this.context.isProcessRunning())
    {
      return super.canHandleShellCloseEvent();
    }

    return false;
  }

  @Override
  public boolean close()
  {
    try
    {
      this.server.close();
    }
    catch (Exception e)
    {
      return false;
    }

    return super.close();
  }

  @Override
  public void uncaughtException(Thread t, Throwable e)
  {
    this.error(e);
  }

  private String[] loadApplicationInformation()
  {
    Collection<String> collection = new LinkedList<String>();

    try
    {
      URL resource = Object.class.getResource("/applications.txt");
      File file = new File(resource.toURI());

      BufferedReader reader = new BufferedReader(new FileReader(file));

      try
      {
        while (reader.ready())
        {
          collection.add(reader.readLine().trim());
        }
      }
      finally
      {
        reader.close();
      }
    }
    catch (RuntimeException e)
    {
      throw e;
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }

    return collection.toArray(new String[collection.size()]);
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

    Localizer.setInstance("localization", locale);

    final Display display = Display.getDefault();

    Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable()
    {
      public void run()
      {
        try
        {
          JUnique.acquireLock(ServerManagerWindow.class.getName());

          ServerManagerWindow window = new ServerManagerWindow();
          window.run();
        }
        catch (AlreadyLockedException e)
        {
          String title = Localizer.getMessage("ERROR_TITLE");
          String msg = Localizer.getMessage("MANAGER_ALREADY_OPEN");

          MessageDialog.openError(null, title, msg);
        }
      }
    });
  }
}
