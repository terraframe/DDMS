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
package dss.vector.solutions.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.session.Request;

public class InstallStoredProcedures
{

  /**
   * @param args
   */
  @Request
  public static void main(String[] args) throws Exception
  {
    for (String procedurePath : args)
    {
      installProcedure(procedurePath);
    }
  }

  private static void installProcedure(String procedurePath) throws Exception
  {
    String storedProcSource = "";

    try
    {
      BufferedReader br = new BufferedReader(new FileReader(procedurePath));
      String line;
      while ((line = br.readLine()) != null)
      {
        storedProcSource += line+"\n";
      }
      br.close();
    }
    catch (FileNotFoundException e)
    {
      throw e;
    }
    catch (IOException e)
    {
      throw e;
    }


    Connection conn = Database.getConnection();
    Statement statement = null;
    try
    {
      statement = conn.createStatement();
      statement.execute(storedProcSource);
    }
    catch (SQLException e)
    {
      throw e;
    }
    finally
    {
      if (statement != null)
      {
        try
        {
          statement.close();
        }
        catch (SQLException e2)
        {
          throw e2;
        }
      }
    }

  }

}
