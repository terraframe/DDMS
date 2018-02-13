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
package dss.vector.solutions.gis.shapefile;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.geo.GeoHierarchyView;
import dss.vector.solutions.gis.LocalizedWizardDialog;
import dss.vector.solutions.gis.Localizer;

public class ImportShapefileAction extends Action implements Reloadable
{
  private GeoHierarchyView[] views;
  
  private String appName;

  public ImportShapefileAction(String appName, GeoHierarchyView[] views)
  {
    this.appName = appName;
    this.views = views;
    
    this.setText(Localizer.getMessage("IMPORT_SHAPE_FILE"));
  }

  @Override
  public void run()
  {
    Shell shell = Display.getCurrent().getActiveShell();
    ShapeFileWizard wizard = new ShapeFileWizard(appName, views);

    LocalizedWizardDialog dialog = new LocalizedWizardDialog(shell, wizard);
    dialog.setBlockOnOpen(true);
    dialog.open();
  }
}
