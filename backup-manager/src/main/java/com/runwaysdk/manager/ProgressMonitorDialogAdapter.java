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
package com.runwaysdk.manager;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Shell;

public class ProgressMonitorDialogAdapter extends ProgressMonitorDialog
{
  /**
   * List of actions to run when the dialog is closing and the action which was
   * monitored finished successfully.
   */
  private List<Runnable> listeners;

  public ProgressMonitorDialogAdapter(Shell parent)
  {
    super(parent);

    this.listeners = new LinkedList<Runnable>();
  }

  public void addListener(Runnable listener)
  {
    this.listeners.add(listener);
  }

  @Override
  public void run(boolean fork, boolean cancelable, IRunnableWithProgress runnable) throws InvocationTargetException, InterruptedException
  {
    try
    {
      super.run(fork, cancelable, runnable); 

      for (Runnable listener : listeners)
      {
        listener.run();
      }
    }
    catch (InvocationTargetException e)
    {
      throw e;
    }
    catch (InterruptedException e)
    {
      throw e;
    }
    catch (RuntimeException e)
    {
      throw e;
    }
  }
}
