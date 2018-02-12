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
