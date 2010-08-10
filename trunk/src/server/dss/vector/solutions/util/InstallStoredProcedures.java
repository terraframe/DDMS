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
