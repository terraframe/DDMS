package dss.vector.solutions.gis.shapefile;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.geo.GeoHierarchyView;
import dss.vector.solutions.gis.Localizer;

public class ImportShapefileAction extends Action implements Reloadable
{
  private GeoHierarchyView[] views;

  public ImportShapefileAction(GeoHierarchyView[] views)
  {
    this.views = views;
    
    this.setText(Localizer.getMessage("IMPORT_SHAPE_FILE"));
  }

  @Override
  public void run()
  {
    Shell shell = Display.getCurrent().getActiveShell();
    ShapeFileWizard wizard = new ShapeFileWizard(views);

    WizardDialog dialog = new WizardDialog(shell, wizard);
    dialog.setBlockOnOpen(true);
    dialog.open();
  }
}
