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
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.runwaysdk.manager.ProgressMonitorDialogAdapter;

/**
 * This class demonstrates JFace's ProgressMonitorDialog class
 */
public class ShowProgress extends ApplicationWindow
{
  /**
   * ShowProgress constructor
   */
  public ShowProgress()
  {
    super(null);
  }

  /**
   * Runs the application
   */
  public void run()
  {
    // Don't return from open() until window closes
    setBlockOnOpen(true);

    // Open the main window
    open();

    // Dispose the display
    Display.getCurrent().dispose();
  }

  /**
   * Configures the shell
   * 
   * @param shell
   *          the shell
   */
  protected void configureShell(Shell shell)
  {
    super.configureShell(shell);

    // Set the title bar text
    shell.setText("Show Progress");
  }

  /**
   * Creates the main window's contents
   * 
   * @param parent
   *          the main window
   * @return Control
   */
  protected Control createContents(Composite parent)
  {
    Composite composite = new Composite(parent, SWT.NONE);
    composite.setLayout(new GridLayout(1, true));

    // Create the indeterminate checkbox
    final Button indeterminate = new Button(composite, SWT.CHECK);
    indeterminate.setText("Indeterminate");

    // Create the ShowProgress button
    Button showProgress = new Button(composite, SWT.NONE);
    showProgress.setText("Show Progress");

    final Shell shell = parent.getShell();

    // Display the ProgressMonitorDialog
    showProgress.addSelectionListener(new SelectionAdapter()
    {
      public void widgetSelected(SelectionEvent event)
      {
        try
        {
          ProgressMonitorDialogAdapter dialog = new ProgressMonitorDialogAdapter(shell);
          dialog.addListener(new Runnable()
          {
            @Override
            public void run()
            {
              MessageDialog.openInformation(shell, "Message", "Finished");
            }
          });

          dialog.run(true, false, new LongRunningOperation(indeterminate.getSelection()));
        }
        catch (InvocationTargetException e)
        {
          MessageDialog.openError(shell, "Error", e.getMessage());
        }
        catch (InterruptedException e)
        {
          MessageDialog.openInformation(shell, "Cancelled", e.getMessage());
        }
      }
    });

    parent.pack();
    return composite;
  }

  /**
   * The application entry point
   * 
   * @param args
   *          the command line arguments
   */
  public static void main(String[] args)
  {
    new ShowProgress().run();
  }
}

/**
 * This class represents a long running operation
 */
class LongRunningOperation implements IRunnableWithProgress
{
  // The total sleep time
  private static final int TOTAL_TIME = 10000;

  // The increment sleep time
  private static final int INCREMENT  = 500;

  private boolean          indeterminate;

  /**
   * LongRunningOperation constructor
   * 
   * @param indeterminate
   *          whether the animation is unknown
   */
  public LongRunningOperation(boolean indeterminate)
  {
    this.indeterminate = indeterminate;
  }

  /**
   * Runs the long running operation
   * 
   * @param monitor
   *          the progress monitor
   */
  public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException
  {
    monitor.beginTask("Running long running operation", indeterminate ? IProgressMonitor.UNKNOWN : TOTAL_TIME);
    for (int total = 0; total < TOTAL_TIME && !monitor.isCanceled(); total += INCREMENT)
    {
      Thread.sleep(INCREMENT);
      monitor.worked(INCREMENT);

      if (total == TOTAL_TIME / 2)
      {
        if (indeterminate)
        {
          throw new RuntimeException("Error");
        }

        monitor.subTask("Doing second half");
      }
    }

    monitor.done();
    if (monitor.isCanceled())
      throw new InterruptedException("The long running operation was cancelled");
  }
}
