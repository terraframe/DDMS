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

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DatabaseProperties
{

  private Properties props;

  public DatabaseProperties(String location) throws IOException
  {
    FileInputStream stream = new FileInputStream(location);

    try
    {
      this.props = new Properties();
      this.props.load(stream);
    }
    finally
    {
      stream.close();
    }
  }

  public String getPort()
  {
    return this.props.getProperty("port");
  }
  
  public String getUserName()
  {
    return this.props.getProperty("user");
  }
  
  public String getPassword()
  {
    return this.props.getProperty("password");
  }
  
  public String getDatabaseName()
  {
    return this.props.getProperty("databaseName");
  }
  
  public String getServerName()
  {
    return this.props.getProperty("serverName");
  }
}
