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
import com.runwaysdk.manager.general.MainWindow;

import dss.vector.solutions.admin.controller.MasterConfiguration;
import dss.vector.solutions.admin.controller.PropertyReader;
import dss.vector.solutions.admin.controller.DependentConfiguration;

public class SynchronziationManagerLauncher
{
  private static class Arguments
  {
    private Locale locale;

    private String properties;

    private String appName;

    @SuppressWarnings("static-access")
    public Arguments(String[] args) throws ParseException
    {
      this.locale = Locale.getDefault();

      Options options = new Options();
      options.addOption(OptionBuilder.withDescription("Path of the INSTALL property file").hasArg().withArgName("INSTALL").create("i"));
      options.addOption(OptionBuilder.withDescription("LOCALE of app localization").hasArg().withArgName("LOCALE").create("l"));
      options.addOption(OptionBuilder.withDescription("NAME of the application").hasArg().withArgName("NAME").create("a"));

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

      if (cmd.hasOption("a"))
      {
        this.appName = cmd.getOptionValue("a");
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

    public String getAppName()
    {
      return appName;
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
        String shellText = arguments.getAppName() + " " + Localizer.getMessage("APPLICATION_NAME");

        IConfiguration configuration = new DependentConfiguration(shellText);

        if (isMaster(arguments.getProperties()))
        {
          configuration = new MasterConfiguration(shellText);
        }

        try
        {
          Class<?> clazz = LoaderDecorator.load("dss.vector.solutions.admin.MDSSModule");

          IModule module = (IModule) clazz.getConstructor(String.class).newInstance(arguments.getAppName());

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
