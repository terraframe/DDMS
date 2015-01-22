package com.runwaysdk.manager;

import java.util.Locale;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;

import com.runwaysdk.manager.action.BackupAction;
import com.runwaysdk.manager.action.RestoreAction;

public class BackupManagerWindow extends ApplicationWindow
{
  private static class Arguments
  {
    private Locale  locale;

    private String  appName;

    private Boolean registry;

    @SuppressWarnings("static-access")
    public Arguments(String[] args) throws ParseException
    {
      this.appName = "DDMS";
      this.locale = Locale.getDefault();

      Options options = new Options();
      options.addOption(OptionBuilder.withDescription("NAME of webapp").hasArg().withArgName("NAME").create("a"));
      options.addOption(OptionBuilder.withDescription("LOCALE of app localization").hasArg().withArgName("LOCALE").create("l"));
      options.addOption(OptionBuilder.withDescription("Flag indicating the process should NOT backup and restore the registry").create("b"));

      CommandLineParser parser = new PosixParser();
      CommandLine cmd = parser.parse(options, args);

      if (cmd.hasOption("l"))
      {
        String value = cmd.getOptionValue("l");
        String[] localeInfo = value.split("_");

        switch (localeInfo.length)
        {
          case 1:
            locale = new Locale(localeInfo[0]);
            break;
          case 2:
            locale = new Locale(localeInfo[0], localeInfo[1]);
            break;
          case 3:
            locale = new Locale(localeInfo[0], localeInfo[1], localeInfo[2]);
            break;
        }
      }

      if (cmd.hasOption("a"))
      {
        this.appName = cmd.getOptionValue("a");
      }

      this.registry = !cmd.hasOption("b");
    }

    public Locale getLocale()
    {
      return this.locale;
    }

    public String getAppName()
    {
      return this.appName;
    }

    public Boolean getRegistry()
    {
      return this.registry;
    }
  }

  private static final Point DIMENSION        = new Point(200, 100);

  /**
   * Name of the app
   */
  private String             appName;

  /**
   * Flag indicating if the backup and restore proccess should backup the
   * windows registry for the given app.
   */
  private Boolean            registry;

  /**
   * 
   */
  private static final long  serialVersionUID = -6658235304142152484L;

  public BackupManagerWindow(String appName, Boolean registry)
  {
    super(null);

    this.appName = appName;
    this.registry = registry;
  }

  @Override
  protected Control createContents(Composite parent)
  {
    Display display = parent.getDisplay();
    Monitor monitor = display.getPrimaryMonitor();

    parent.getShell().setSize(DIMENSION);
    parent.getShell().setText(appName + " " + Localizer.getMessage("APPLICATION_NAME"));
    parent.getShell().setImage(ImageDescriptor.createFromURL(Object.class.getResource("/icons/favicon.png")).createImage());

    Rectangle windowRect = parent.getShell().getBounds();
    Rectangle monitorRect = monitor.getBounds();

    int x = ( monitorRect.width - windowRect.width ) / 2;
    int y = ( monitorRect.height - windowRect.height ) / 2;

    parent.getShell().setLocation(x, y);

    Composite composite = new Composite(parent, SWT.FILL);
    composite.setSize(DIMENSION);
    composite.setLayout(new FillLayout());

    new ActionContributionItem(new BackupAction(this)).fill(composite);
    new ActionContributionItem(new RestoreAction(this)).fill(composite);

    return parent;
  }

  public void error(final String msg)
  {
    final Shell shell = this.getShell();

    Runnable runnable = new Runnable()
    {
      @Override
      public void run()
      {
        MessageDialog.openError(shell, Localizer.getMessage("ERROR_TITLE"), msg);
      }
    };

    shell.getDisplay().asyncExec(runnable);
  }

  public void error(Throwable throwable)
  {
    error(throwable.getLocalizedMessage());
  }

  public void message(final String msg)
  {
    final Shell shell = this.getShell();

    Runnable runnable = new Runnable()
    {
      @Override
      public void run()
      {
        MessageDialog.openInformation(shell, Localizer.getMessage("MESSAGE_TITLE"), msg);
      }
    };

    shell.getDisplay().asyncExec(runnable);
  }

  public void run()
  {
    // Don't return from open() until window closes
    this.setBlockOnOpen(true);

    // Open the main window
    this.open();

    // Dispose the display
    Display.getCurrent().dispose();
  }

  @Override
  public boolean close()
  {
    return super.close();
  }

  public static void main(String[] args) throws Exception
  {
    final Arguments arguments = new Arguments(args);
    Localizer.setInstance("localization", arguments.getLocale());

    final Display display = Display.getDefault();

    Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable()
    {
      public void run()
      {
        BackupManagerWindow window = new BackupManagerWindow(arguments.getAppName(), arguments.getRegistry());
        window.run();
      }
    });
  }

  public String getAppName()
  {
    return this.appName;
  }

  public Boolean getRegistry()
  {
    return registry;
  }
}
