/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.manager.action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import dss.vector.solutions.manager.IApplicationUninstallManager;
import dss.vector.solutions.manager.Localizer;
import dss.vector.solutions.manager.LogLevel;
import dss.vector.solutions.manager.LogOutputStream;
import dss.vector.solutions.manager.Logger;
import dss.vector.solutions.manager.ManagerContextBean;
import dss.vector.solutions.manager.properties.DatabaseProperties;
import dss.vector.solutions.manager.properties.ManagerProperties;
import dss.vector.solutions.manager.properties.RegistryProperties;

public class UninstallAction extends Action
{
  /**
   * Directory containing the DDMS shortcuts
   */
  private ManagerContextBean           context;

  private IApplicationUninstallManager manager;

  public UninstallAction(ManagerContextBean context, IApplicationUninstallManager manager)
  {
    super(Localizer.getMessage("DELETE"), ImageDescriptor.createFromURL(Object.class.getResource("/icons/delete.png")));
    this.setToolTipText(Localizer.getMessage("DELETE"));

    this.context = context;
    this.manager = manager;
  }

  @Override
  public void run()
  {
    if (context.hasApplication())
    {
      if (manager.getApplicationCount() > 1)
      {
        boolean delete = this.confirmDelete(context.getApplication());

        if (delete)
        {
          try
          {
            Shell shell = Display.getCurrent().getActiveShell();
            new ProgressMonitorDialog(shell).run(true, true, new IRunnableWithProgress()
            {
              @Override
              public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException
              {
                monitor.beginTask(Localizer.getMessage("UNINSTALLING_APPLICATION", context.getApplication()), IProgressMonitor.UNKNOWN);

                try
                {
                  /*
                   * 1) Drop the database
                   */
                  dropDatabase();

                  /*
                   * 2) Delete the webapp from the tomcat directory
                   */
                  FileUtils.deleteDirectory(new File(context.getApplicationPath()));

                  /*
                   * 2.1) Delete the odk webapp from the tomcat directory
                   */
                  FileUtils.deleteDirectory(new File(context.getODKApplicationPath()));

                  /*
                   * 3) Delete the profiles from the backup directory
                   */
                  FileUtils.deleteDirectory(new File(context.getBackupProfilesPath()));

                  /*
                   * 4) Update the windows registry
                   */
                  updateWindowsRegistry();

                  /*
                   * 5) Update the applications text file
                   */
                  updateApplicationProperties(context.getApplication());

                  /*
                   * 6) Delete the index files
                   */
                  removeIndexFiles();

                  /*
                   * 7) Delete the menu shortcuts
                   */
                  File shortcut = new File(ManagerProperties.getShortcutDirectory() + "Open " + context.getApplication() + ".lnk");

                  FileUtils.deleteQuietly(shortcut);
                }
                catch (Exception e)
                {
                  throw new InvocationTargetException(e);
                }

                monitor.done();
              }
            });
          }
          catch (InterruptedException e)
          {
            Logger.error("Unabled to uninstall", e);

            String message = Localizer.getMessage("UNABLE_TO_DELETE");
            String details = Localizer.getMessage("CANCELLED");

            this.handleError(message, details);
          }
          catch (InvocationTargetException e)
          {
            Logger.error("Unabled to uninstall", e);

            String message = Localizer.getMessage("UNABLE_TO_DELETE");

            this.handleError(message, e.getCause().getLocalizedMessage());
          }
          catch (Exception e)
          {
            Logger.error("Unabled to uninstall", e);

            String message = Localizer.getMessage("UNABLE_TO_DELETE");

            this.handleError(message, e.getLocalizedMessage());
          }

          /*
           * 7) Notify all listeners the app has been uninstalled
           */
          manager.onUninstall(context.getApplication());
        }
        else
        {
          // Cancel Button: Do nothing
        }
      }
      else
      {
        String message = Localizer.getMessage("UNABLE_TO_DELETE_LAST");
        String details = Localizer.getMessage("UNABLE_TO_DELETE_MESSAGE");

        this.handleError(message, details);
      }
    }
  }

  public void dropDatabase() throws IOException, SQLException
  {
    Logger.info("Start of dropping database");
    
    DatabaseProperties props = new DatabaseProperties(context.getDatabaseProperties());

    String port = props.getPort();

    String command = ManagerProperties.getDropCommand() + " -p " + port + " -U postgres " + props.getDatabaseName();

    int results = this.execWait(command);

    if (results != 0)
    {
      throw new RuntimeException(Localizer.getMessage("UNABLE_TO_DROP_DATABASE", props.getDatabaseName()));
    }

    /*
     * Drop ODK Schema
     */
    command = ManagerProperties.getPsqlCommand() + " -p " + port + " -h " + props.getServerName() + " -U postgres -c \"DROP SCHEMA IF EXISTS " + props.getDatabaseName() + ";\" odk";

    results = this.execWait(command);

    if (results != 0)
    {
      throw new RuntimeException(Localizer.getMessage("UNABLE_TO_DROP_ODK_SCHEMA", props.getDatabaseName()));
    }

    /*
     * Revoke ODK user permissions
     */
    command = ManagerProperties.getPsqlCommand() + " -p " + port + " -h " + props.getServerName() + " -U postgres -c \"REVOKE ALL ON DATABASE odk FROM " + props.getDatabaseName() + "_mobile;\" odk";

    results = this.execWait(command);

    if (results != 0)
    {
      throw new RuntimeException(Localizer.getMessage("UNABLE_TO_REVOKE_PERMISSIONS", props.getDatabaseName()));
    }

    /*
     * Drop ODK user
     */
    command = ManagerProperties.getPsqlCommand() + " -p " + port + " -h " + props.getServerName() + " -U postgres -c \"DROP USER IF EXISTS " + props.getDatabaseName() + "_mobile;\" odk";

    results = this.execWait(command);

    if (results != 0)
    {
      throw new RuntimeException(Localizer.getMessage("UNABLE_TO_DROP_ODK_USER", props.getDatabaseName()));
    }
    
    Logger.info("End of dropping database");
  }

  private void removeIndexFiles()
  {
    /*
     * Index files are located in the root tomcat directory
     */
    File webapp = new File(ManagerProperties.getWebappPath());
    File tomcat = webapp.getParentFile();

    String[] extensions = new String[] { ".data", ".index" };

    for (String extension : extensions)
    {
      String filename = this.context.getApplication() + extension;
      File file = new File(tomcat, filename);

      /*
       * It is possible the cache files will not exist if the system was shut
       * down incorrectly.
       */
      if (file.exists())
      {
        FileUtils.deleteQuietly(file);
      }
    }

  }

  public void updateWindowsRegistry() throws IOException
  {
    int results = 1;
    String[] roots = new String[] { RegistryProperties.getRegistryRoot64(), RegistryProperties.getRegistryRoot32() };

    for (String root : roots)
    {
      String command = RegistryProperties.getDeleteCommand() + " " + root + context.getApplication() + " /f";

      int result = this.execWait(command);

      results = Math.min(results, result);
    }

    if (results != 0)
    {
      throw new RuntimeException(Localizer.getMessage("UNABLE_TO_UPDATE_REGISTRY"));
    }
  }

  private void handleError(String message, String details)
  {
    String title = Localizer.getMessage("ERROR");
    Status status = new Status(IStatus.ERROR, "My Plug-in ID", 0, message, null);

    ErrorDialog.openError(Display.getCurrent().getActiveShell(), title, details, status);
  }

  private boolean confirmDelete(String application)
  {
    String title = Localizer.getMessage("CONFIRM_TITLE");
    String message = Localizer.getMessage("CONFIRM_DELETE", application);

    return MessageDialog.openConfirm(Display.getCurrent().getActiveShell(), title, message);
  }

  private int execWait(String command) throws IOException
  {
    try
    {
      Process exec = Runtime.getRuntime().exec(command);

      Thread err = consume(exec.getErrorStream(), new LogOutputStream(LogLevel.ERROR));
      Thread std = consume(exec.getInputStream(), new LogOutputStream(LogLevel.INFO));

      int results = exec.waitFor();

      err.join();
      std.join();

      return results;
    }
    catch (InterruptedException e)
    {
      // Do nothing
    }

    return 1;
  }

  private Thread consume(final InputStream stream, final OutputStream out)
  {
    Thread result = new Thread()
    {
      public void run()
      {
        PrintWriter pw = new PrintWriter(out, true);
        try (Scanner sc = new Scanner(stream))
        {
          while (sc.hasNext())
          {
            pw.println(sc.nextLine());
          }
        }
      }
    };
    result.setDaemon(true);
    result.start();
    return result;
  }

  private void updateApplicationProperties(String exclude) throws IOException, URISyntaxException
  {
    List<String> applications = new ArrayList<String>(Arrays.asList(this.manager.getApplications()));
    applications.remove(exclude);

    URL resource = Object.class.getResource("/applications.txt");
    File file = new File(resource.toURI());

    BufferedWriter writer = new BufferedWriter(new FileWriter(file));

    try
    {
      for (int i = 0; i < applications.size(); i++)
      {
        if (i > 0)
        {
          writer.newLine();
        }

        writer.write(applications.get(i));
      }
    }
    finally
    {
      writer.close();
    }
  }
}
