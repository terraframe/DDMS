package dss.vector.solutions.admin;

import java.util.Locale;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.databinding.swt.SWTObservables;
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
import com.runwaysdk.view.IViewPart;
import com.runwaysdk.widgets.TabManager;

import dss.vector.solutions.admin.controller.IModuleController;
import dss.vector.solutions.admin.controller.ModuleController;
import dss.vector.solutions.admin.view.ControlView;

public class MDSSModule implements IModule
{
  private IModuleController controller;

  private TabManager        manager;

  private StatusLineManager statusManager;

  public MDSSModule(IModuleController controller)
  {
    this.controller = controller;
    this.controller.setModule(this);
    this.statusManager = new StatusLineManager();
  }

  @Override
  public void generateMenu(IMenuManager manager)
  {
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
        IModule module = new MDSSModule(controller);
        MainWindow window = new MainWindow(module);
        window.run();
      }
    });
  }
}
