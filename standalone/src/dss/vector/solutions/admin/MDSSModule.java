package dss.vector.solutions.admin;

import org.eclipse.core.databinding.observable.Realm;
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

import dss.vector.solutions.admin.controller.IControllerEvent;
import dss.vector.solutions.admin.controller.IControllerListener;
import dss.vector.solutions.admin.controller.IModuleController;
import dss.vector.solutions.admin.controller.ModuleController;
import dss.vector.solutions.admin.view.ControlView;

public class MDSSModule implements IModule, IControllerListener
{
  private IModuleController controller;

  private TabManager        manager;

  public MDSSModule(IModuleController controller)
  {
    this.controller = controller;
    this.controller.addListener(this);
  }

  @Override
  public void generateMenu(IMenuManager manager)
  {
//    MenuManager transactionMenu = manager.getMenu(Localizer.getMessage("FILE_MENU"));
//    transactionMenu.add(new BackupAction());
//
//    manager.addMenu(transactionMenu);
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
  public void handleEvent(IControllerEvent e)
  {
    if(e.getType() == IControllerEvent.CLOSE_TAB)
    {
      String key = (String) e.getData(IControllerEvent.KEY);

      manager.closeTab(key);
    }
    else if(e.getType() == IControllerEvent.OPEN_TAB)
    {
      ITabStrategy strategy = (ITabStrategy) e.getData(IControllerEvent.OBJECT);

      manager.openTab(strategy);
    }
  }
  
  @Request
  public static void main(String[] args)
  {
    final Display display = Display.getDefault();

    Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable()
    {
      public void run()
      {
        IModuleController controller = new ModuleController(display);
        IModule module = new MDSSModule(controller);
        MainWindow window = new MainWindow(module);
        window.run();
      }
    });
  }
}
