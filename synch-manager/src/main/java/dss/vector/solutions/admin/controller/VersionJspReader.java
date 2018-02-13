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
package dss.vector.solutions.admin.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VersionJspReader
{
  private String jspPath;

  public VersionJspReader(String jspPath)
  {
    this.jspPath = jspPath;
  }

  public String getValue(String propertyName)
  {
    File props = new File(jspPath);

    BufferedReader in = null;

    try
    {
      in = new BufferedReader(new FileReader(props));

      String line;

      while ( ( line = in.readLine() ) != null)
      {
        if (line != null && line.contains(propertyName))
        {
          Pattern pattern = Pattern.compile(".*value=\"([\\d|\\.]*)\".*");

          Matcher matcher = pattern.matcher(line);
          matcher.matches();

          String value = matcher.group(1);

          return value;
        }
      }
    }
    catch (Exception e)
    {
    }
    finally
    {
      if (in != null)
      {
        try
        {
          in.close();
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
      }
    }

    return null;
  }
}
