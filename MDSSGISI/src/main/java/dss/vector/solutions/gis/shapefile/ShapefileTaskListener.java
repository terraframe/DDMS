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

import org.eclipse.core.runtime.IProgressMonitor;

import com.runwaysdk.dataaccess.transaction.ITaskListener;

import dss.vector.solutions.gis.Localizer;

public class ShapefileTaskListener implements ITaskListener
{
  protected IProgressMonitor monitor;

  public ShapefileTaskListener(IProgressMonitor monitor)
  {
    this.monitor = monitor;
  }

  @Override
  public void start()
  {
    // Do nothing
  }

  public IProgressMonitor getMonitor()
  {
    return monitor;
  }

  @Override
  public void taskStart(String name, int amount)
  {
    getMonitor().beginTask(Localizer.getMessage(name), ( amount != -1 ) ? amount : IProgressMonitor.UNKNOWN);
  }

  @Override
  public void taskProgress(int worked)
  {
    getMonitor().worked(worked);
  }

  @Override
  public void done(boolean success)
  {
    getMonitor().done();
  }
}
