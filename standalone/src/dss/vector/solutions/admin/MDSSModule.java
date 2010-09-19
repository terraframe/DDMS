package dss.vector.solutions.admin;

import java.util.Locale;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import com.runwaysdk.controller.IModule;
import com.runwaysdk.controller.ITabStrategy;
import com.runwaysdk.general.IMenuManager;
import com.runwaysdk.general.Localizer;
import com.runwaysdk.general.MainWindow;
import com.runwaysdk.session.Request;
import com.runwaysdk.view.ExportDialog;
import com.runwaysdk.view.IViewPart;
import com.runwaysdk.view.ImportDialog;
import com.runwaysdk.widgets.TabManager;

import dss.vector.solutions.admin.controller.IControllerListener;
import dss.vector.solutions.admin.controller.IModuleController;
import dss.vector.solutions.admin.controller.ModuleController;
import dss.vector.solutions.admin.view.ControlView;

public class MDSSModule implements IModule, IControllerListener
{
  class TransactionImportAction extends Action
  {
    public TransactionImportAction()
    {
      this.setText(Localizer.getMessage("IMPORT_TRANSACTION"));
    }

    @Override
    public void run()
    {
      new ImportDialog(window.getShell(), controller).open();
    }
  }

  class TransactionExportAction extends Action
  {
    public TransactionExportAction()
    {
      this.setText(Localizer.getMessage("EXPORT_TRANSACTION"));
    }

    @Override
    public void run()
    {
      new ExportDialog(window.getShell(), controller).open();
    }
  }

  private IModuleController controller;

  private TabManager        manager;

  private StatusLineManager statusManager;

  private MainWindow        window;

  public MDSSModule(IModuleController controller)
  {
    this.controller = controller;
    this.controller.addListener(this);
    this.statusManager = new StatusLineManager();
  }

  @Override
  public void generateMenu(IMenuManager manager)
  {
    MenuManager transactionMenu = manager.getMenu(Localizer.getMessage("TRANSACTION_MENU"));
    transactionMenu.add(new TransactionExportAction());
    transactionMenu.add(new TransactionImportAction());

    manager.addMenu(transactionMenu);
  }

  @Override
  public Composite createContents(Composite parent)
  {
    CTabFolder tabFolder = new CTabFolder(parent, SWT.CLOSE);
    tabFolder.setUnselectedCloseVisible(false);
    tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true, 2, 1));
    tabFolder.setLayout(new FillLayout());
    tabFolder.setSimple(false);

    manager = new TabManager(tabFolder);
    manager.openTab(new ITabStrategy()
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
    });

    parent.pack();

    return parent;
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

  protected void setWindow(MainWindow window)
  {
    this.window = window;
  }

  @Request
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
        IModuleController controller = new ModuleController();
        MDSSModule module = new MDSSModule(controller);
        MainWindow window = new MainWindow(module);

        module.setWindow(window);

        window.run();
      }
    });
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
  public void execute(IRunnableWithProgress runnable)
  {
    // DO NOTHING
  }

  @Override
  public void serverStateChange(boolean state)
  {
    clearStatus();
  }
}
