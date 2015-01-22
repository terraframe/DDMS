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
