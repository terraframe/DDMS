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
package com.runwaysdk.manager.action;

import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import com.runwaysdk.dataaccess.cache.globalcache.ehcache.CacheShutdown;
import com.runwaysdk.dataaccess.io.Backup;
import com.runwaysdk.manager.BackupManagerWindow;
import com.runwaysdk.manager.EventOutputStream;
import com.runwaysdk.manager.Localizer;
import com.runwaysdk.manager.LogOutputStream;
import com.runwaysdk.manager.Logger;
import com.runwaysdk.manager.RegistryAgent;
import com.runwaysdk.session.Request;

public class BackupAction extends Action
{
  private static final int    SLACK_SPACE = 50;

  private BackupManagerWindow window;

  /**
   * This function is used by the DDMS CLI (ticket 3180)
   * 
   * @param args
   * @throws ParseException
   */
  @SuppressWarnings("static-access")
  public static void main(String[] args) throws ParseException
  {
    Options options = new Options();
    options.addOption("a", "appName", true, "Name of the webapp.");
    options.addOption("f", "file", true, "The path to the file in which to save the backup.");
    options.addOption("r", "noRegistry", true, "Flag indicating the process should NOT backup and restore the registry.");

    CommandLineParser parser = new PosixParser();
    CommandLine cmd = parser.parse(options, args);

    doBackup(new File(cmd.getOptionValue("f")), System.out, System.err, !cmd.hasOption("r"), cmd.getOptionValue("a"));
  }
  
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

              doBackup(file, print, new PrintStream(new LogOutputStream(), true), window.getRegistry(), window.getAppName());

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

  private static void doBackup(final File file, PrintStream print, PrintStream errOut, boolean doRegistry, String appName)
  {
    try
    {
      doBackupInRequest(file, print, errOut, doRegistry, appName);
    }
    finally
    {
      CacheShutdown.shutdown();
    }
  }
  @Request
  private static void doBackupInRequest(final File file, PrintStream print, PrintStream errOut, boolean doRegistry, String appName)
  {
    Backup backup = new Backup(print, errOut, file.getName(), file.getParent(), true, true);
    backup.addAgents(new ODKAgent(appName));

    if (doRegistry)
    {
      backup.addAgents(new RegistryAgent(appName));
    }
    
    backup.backup();
  }
}
