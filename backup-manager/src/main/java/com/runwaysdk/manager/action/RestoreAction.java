package com.runwaysdk.manager.action;

import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import com.runwaysdk.dataaccess.io.Restore;
import com.runwaysdk.manager.BackupManagerWindow;
import com.runwaysdk.manager.EventOutputStream;
import com.runwaysdk.manager.Localizer;
import com.runwaysdk.manager.Logger;
import com.runwaysdk.manager.ProgressMonitorDialogAdapter;
import com.runwaysdk.manager.PropertiesAgent;
import com.runwaysdk.manager.RegistryAgent;

public class RestoreAction extends Action
{
  private BackupManagerWindow window;
  
  public static String     ROOT_DIRECTORY = "C:/MDSS";

  public static String     DEFAULT_TOMCAT  = ROOT_DIRECTORY+"/tomcat6/";

  public static String     DEFAULT_MANAGER = ROOT_DIRECTORY+"/manager/";
  
  @SuppressWarnings("static-access")
  public static void main(String[] args) throws ParseException
  {
    Options options = new Options();
    options.addOption(OptionBuilder.withDescription("NAME of webapp.").hasArg().withArgName("NAME").create("appName"));
    options.addOption(OptionBuilder.withDescription("The path to the file to save the backup to.").hasArg().withArgName("FILE").create("file"));
    options.addOption(OptionBuilder.withDescription("Flag indicating the process should NOT backup and restore the registry.").create("noRegistry"));

    CommandLineParser parser = new PosixParser();
    CommandLine cmd = parser.parse(options, args);

    doRestore(new File(cmd.getOptionValue("file")), System.out, !cmd.hasOption("noRegistry"), cmd.getOptionValue("appName"));
  }
  
  public RestoreAction(BackupManagerWindow window)
  {
    super(Localizer.getMessage("RESTORE"), ImageDescriptor.createFromURL(Object.class.getResource("/icons/restore.png")));
    this.setToolTipText(Localizer.getMessage("RESTORE"));
    
    this.window = window;
  }
  
  @Override
  public void run()
  {
    Display display = Display.getCurrent();
    Shell shell = display.getActiveShell();

    FileDialog dialog = new FileDialog(shell);
    dialog.setFilterNames(new String[] { Localizer.getMessage("ZIP_FILES") });
    dialog.setFilterExtensions(new String[] { "*.zip" });
    String path = dialog.open();

    if (path != null && path.length() > 0)
    {
      final File file = new File(path);

      try
      {
        ProgressMonitorDialogAdapter monitor = new ProgressMonitorDialogAdapter(shell);
        monitor.addListener(new Runnable()
        {
          @Override
          public void run()
          {
            window.message(Localizer.getMessage("RESTORE_FINISHED"));
          }
        });

        monitor.run(true, false, new IRunnableWithProgress()
        {
          @Override
          public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException
          {
            try
            {
              monitor.beginTask(Localizer.getMessage("RESTORE"), IProgressMonitor.UNKNOWN);

              EventOutputStream out = new EventOutputStream(monitor);
              PrintStream print = new PrintStream(out, true);

              try
              {
                doRestore(file, print, window.getRegistry(), window.getAppName());
              }
              finally
              {
                print.close();
              }
            }
            finally
            {
              monitor.done();
            }
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
  
  private static void doRestore(final File file, PrintStream print, boolean doRegistry, String appName)
  {
    Restore restore = new Restore(print, file.getAbsolutePath());

    if (doRegistry)
    {
      restore.addAgent(new RegistryAgent(appName));
    }
    
    // All this does for now is make sure the RMI port is not in use
    restore.addAgent(new PropertiesAgent(appName));

    restore.restore();
  }
}
