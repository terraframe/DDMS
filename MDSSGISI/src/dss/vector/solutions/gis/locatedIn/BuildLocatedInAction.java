package dss.vector.solutions.gis.locatedIn;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.gis.GISAdminLocalizer;

public class BuildLocatedInAction extends Action implements Reloadable
{
  public BuildLocatedInAction()
  {
    this.setText(GISAdminLocalizer.getMessage("BUILD_LOCATED_IN"));
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