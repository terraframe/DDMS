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
package dss.vector.solutions.kaleidoscope.oda.driver.ui.util;

import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.OdaException;

import dss.vector.solutions.kaleidoscope.oda.driver.Connection;

public class DriverLoader
{

  private DriverLoader()
  {
  }

  public static Connection getConnection(Properties props) throws OdaException
  {
    Connection connection = new Connection();
    connection.open(props);

    return connection;
  }

  public static void testConnection(String url, String userName, String password, Properties props) throws OdaException
  {
    props.setProperty(Constants.ODAURL, url);
    props.setProperty(Constants.ODAUser, userName);
    props.setProperty(Constants.ODAPassword, password);

    Connection connection = DriverLoader.getConnection(props);
    connection.close();
  }

}
