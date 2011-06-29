package dss.vector.solutions.admin;

import java.util.Locale;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.widgets.Display;

import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.manager.controller.IConfiguration;
import com.runwaysdk.manager.controller.IModule;
import com.runwaysdk.manager.general.Localizer;
import com.runwaysdk.manager.general.MainWindow;

import dss.vector.solutions.admin.controller.MasterConfiguration;
import dss.vector.solutions.admin.controller.PropertyReader;
import dss.vector.solutions.admin.controller.SlaveConfiguration;

public class SynchronziationManagerLauncher
{
  private static class Arguments
  {
    private Locale locale;

    private String properties;

    @SuppressWarnings("static-access")
    public Arguments(String[] args) throws ParseException
    {
      this.locale = Locale.getDefault();

      Options options = new Options();
      options.addOption(OptionBuilder.withDescription("Path of the INSTALL property file").hasArg().withArgName("INSTALL").create("i"));
      options.addOption(OptionBuilder.withDescription("LOCALE of app localization").hasArg().withArgName("LOCALE").create("l"));

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

      if (cmd.hasOption("i"))
      {
        this.properties = cmd.getOptionValue("i");
      }
    }

    public Locale getLocale()
    {
      return locale;
    }

    public String getProperties()
    {
      return properties;
    }
  }

  public static boolean isMaster(String properties)
  {
    PropertyReader reader = new PropertyReader(properties);
    String value = reader.getValue("master");

    return ( value != null && value.contains("true") );
  }

  public static void main(String[] args) throws ParseException
  {
    final Arguments arguments = new Arguments(args);
    Localizer.setInstance(arguments.getLocale());

    final Display display = Display.getDefault();

    Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable()
    {
      public void run()
      {
        IConfiguration configuration = new SlaveConfiguration();

        if (isMaster(arguments.getProperties()))
        {
          configuration = new MasterConfiguration();
        }

        try
        {
          Class<?> clazz = LoaderDecorator.load("dss.vector.solutions.admin.MDSSModule");

          IModule module = (IModule) clazz.newInstance();

          MainWindow window = new MainWindow(configuration, module);
          window.run();
        }
        catch (RuntimeException e)
        {
          throw e;
        }
        catch (Exception e)
        {
          throw new RuntimeException(e);
        }
      }
    });
  }
}
