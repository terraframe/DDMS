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
package dss.vector.solutions.initializer;

import java.util.Locale;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import com.runwaysdk.generation.loader.LoaderDecorator;

public class BuilderLauncher
{
  private static class BuilderArguments
  {
    private Locale locale;

    private String appName;

    @SuppressWarnings("static-access")
    public BuilderArguments(String[] args) throws ParseException
    {
      this.appName = "DDMS";
      this.locale = Locale.getDefault();

      Options options = new Options();
      options.addOption(OptionBuilder.withDescription("NAME of webapp").hasArg().withArgName("NAME").create("a"));
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

      if (cmd.hasOption("a"))
      {
        this.appName = cmd.getOptionValue("a");
      }
    }

    public Locale getLocale()
    {
      return locale;
    }

    public String getAppName()
    {
      return appName;
    }
  }

  public static void main(String[] args) throws Exception
  {
    final BuilderArguments arguments = new BuilderArguments(args);

    Localizer.setInstance("localization", arguments.getLocale());

    try
    {
      Class<?> clazz = LoaderDecorator.load("dss.vector.solutions.initializer.Builder");
      Object instance = clazz.getConstructor(String.class).newInstance(arguments.getAppName());
      clazz.getMethod("build").invoke(instance);
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
}
