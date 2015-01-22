package com.runwaysdk.manager.action;

import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import com.runwaysdk.dataaccess.io.Backup;
import com.runwaysdk.manager.BackupManagerWindow;
import com.runwaysdk.manager.EventOutputStream;
import com.runwaysdk.manager.Localizer;
import com.runwaysdk.manager.Logger;
import com.runwaysdk.manager.RegistryAgent;

public class BackupAction extends Action
{
  private static final int    SLACK_SPACE = 50;

  private BackupManagerWindow window;

  public BackupAction(BackupManagerWindow window)
  {
    super(Localizer.getMessage("BACKUP"), ImageDescriptor.createFromURL(Object.class.getResource("/icons/backup.png")));
    this.setToolTipText(Localizer.getMessage("BACKUP"));

    this.window = window;
  }

  @Override
  public void run()
  {
    Shell shell = Display.getCurrent().getActiveShell();
    FileDialog dialog = new FileDialog(shell, SWT.SAVE);
    String path = dialog.open();

    if (path != null && path.length() > 0)
    {
      File temp = new File(path);
      final File file = new File(temp.getParentFile(), temp.getName().replaceAll("\\s", "_"));
      

      /*
       * IMPORTANT: In windows there is a hard restriction on path lengths of 260 characters.
       * We need to ensure that the temp paths used to create the backup do not go over 260
       * characters.  
       */
      int potentialLength = ( file.getParent().length() + ( (file.getName().length() + 10) * 2 ) ) + SLACK_SPACE;

      if (potentialLength >= 250)
      {
        this.window.error(new RuntimeException(Localizer.getMessage("PATH_TO_LONG")));
      }
      else
      {
        try
        {
          ProgressMonitorDialog monitor = new ProgressMonitorDialog(shell);
          monitor.run(true, false, new IRunnableWithProgress()
          {
            @Override
            public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException
            {
              monitor.beginTask(Localizer.getMessage("BACKUP"), IProgressMonitor.UNKNOWN);

              EventOutputStream out = new EventOutputStream(monitor);
              PrintStream print = new PrintStream(out, true);

              Backup backup = new Backup(print, file.getName(), file.getParent(), true, true);

              if (window.getRegistry())
              {
                backup.addAgents(new RegistryAgent(window.getAppName()));
              }
              backup.backup();

              print.close();
            }
          });
        }
        catch (InvocationTargetException e)
        {
          Logger.error("Invocation target exception during backup", e);

          this.window.error(e);
        }
        catch (InterruptedException e)
        {
          Logger.error("Interrupted exception during backup", e);

          this.window.error(e);
        }
      }
    }
  }
}
