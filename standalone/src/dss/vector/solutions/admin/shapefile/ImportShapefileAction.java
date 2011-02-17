package dss.vector.solutions.admin.shapefile;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.runwaysdk.manager.general.Localizer;

public class ImportShapefileAction extends Action
{
  public ImportShapefileAction()
  {
    this.setText(Localizer.getMessage("IMPORT_SHAPE_FILE"));
  }

  @Override
  public void run()
  {
    Shell shell = Display.getCurrent().getActiveShell();
    ShapeFileWizard wizard = new ShapeFileWizard();

    WizardDialog dialog = new WizardDialog(shell, wizard);
    dialog.setBlockOnOpen(true);
    dialog.open();

    System.out.println(wizard.getData().toString());
  }
}
