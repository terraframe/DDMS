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
package dss.vector.solutions.admin.action;

import org.eclipse.jface.action.Action;

import com.runwaysdk.manager.general.Localizer;

import dss.vector.solutions.admin.MDSSModule;

public class AllPathAction extends Action
{
  private MDSSModule module;

  public AllPathAction(MDSSModule module)
  {
    super(Localizer.getMessage("REBUILD_ALL_PATH_TABLES"));

    this.module = module;
  }

  @Override
  public void run()
  {
    this.module.rebuildAllPathTables();
  }
}
