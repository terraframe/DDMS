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
package dss.vector.solutions.gis.locatedIn;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.gis.LocalizedWizardDialog;
import dss.vector.solutions.gis.Localizer;

public class BuildLocatedInAction extends Action implements Reloadable
{
  private String appName;

  public BuildLocatedInAction(String appName)
  {
    this.appName = appName;

    this.setText(Localizer.getMessage("BUILD_LOCATED_IN"));
  }

  @Override
  public void run()
  {
    Shell shell = Display.getCurrent().getActiveShell();
    LocatedInWizard wizard = new LocatedInWizard(appName);

    LocalizedWizardDialog dialog = new LocalizedWizardDialog(shell, wizard);
    dialog.setBlockOnOpen(true);
    dialog.open();

    System.out.println(wizard.getData().toString());
  }
}
