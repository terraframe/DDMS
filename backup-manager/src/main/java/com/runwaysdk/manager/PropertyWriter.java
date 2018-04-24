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
package com.runwaysdk.manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PropertyWriter
{
  private String propertyPath;
  
  public PropertyWriter(String propertyPath)
  {
    this.propertyPath = propertyPath;
  }

  public boolean write(String propertyName, String propertyValue)
  {
    boolean success = false;

    BufferedReader in = null;
    BufferedWriter out = null;

    File newprops = new File(propertyPath + ".new");
    File props = new File(propertyPath);

    try
    {
      in = new BufferedReader(new FileReader(props));
      out = new BufferedWriter(new FileWriter(newprops));
      String line;
      while ( ( line = in.readLine() ) != null)
      {
        if (line.trim().startsWith(propertyName))
        {
          out.write(propertyName + "=" + propertyValue);
        }
        else
        {
          out.write(line);
        }
        out.write("\n");
      }
      success = true;
    }
    catch (Exception e)
    {
      success = false;
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
          success = false;
        }
      }
      if (out != null)
      {
        try
        {
          out.close();
        }
        catch (IOException e)
        {
          success = false;
        }
      }
    }

    if (success)
    {
      success = false;
      File oldprops = new File(propertyPath + ".old");

      if (props.renameTo(oldprops))
      {
        if (newprops.renameTo(props))
        {
          oldprops.delete();
          success = true;
        }
      }
    }

    return success;
  }

}
