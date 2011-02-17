package dss.vector.solutions.admin.shapefile;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.runwaysdk.manager.general.Localizer;

public class BuildLocatedInAction extends Action
{
  public BuildLocatedInAction()
  {
    this.setText(Localizer.getMessage("BUILD_LOCATED_IN"));
  }

  @Override
  public void run()
  {
    Shell shell = Display.getCurrent().getActiveShell();
    LocatedInWizard wizard = new LocatedInWizard();

    WizardDialog dialog = new WizardDialog(shell, wizard);
    dialog.setBlockOnOpen(true);
    dialog.open();

    System.out.println(wizard.getData().toString());
  }
}
