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
package dss.vector.solutions.manager.properties;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PropertyReader
{
  private String propertyPath;
  
  public PropertyReader(String propertyPath)
  {
    this.propertyPath = propertyPath;
  }
  
  public String getValue(String propertyName)
  {
    File props = new File(propertyPath);

    BufferedReader in = null;
    
    try
    {
      in = new BufferedReader(new FileReader(props));

      String line;

      while ( ( line = in.readLine() ) != null)
      {
        if(line != null && line.contains(propertyName))
        {
          line = line.replace(propertyName, "");
          line = line.replace("=", "");
          
          return line.trim();
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
