package dss.vector.solutions.manager.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Display;

import dss.vector.solutions.manager.Localizer;
import dss.vector.solutions.manager.ManagerContextBean;
import dss.vector.solutions.manager.SettingDialog;

public class ChangeSettingAction extends Action
{
  private ManagerContextBean context;

  public ChangeSettingAction(ManagerContextBean context)
  {
    super(Localizer.getMessage("CHANGE_SETTINGS"), ImageDescriptor.createFromURL(Object.class.getResource("/icons/settings.png")));

    this.setToolTipText(Localizer.getMessage("CHANGE_SETTINGS"));

    this.context = context;
  }

  @Override
  public void run()
  {
    if (context.hasApplication())
    {
      new SettingDialog(Display.getCurrent().getActiveShell(), context).open();
    }
  }
}
