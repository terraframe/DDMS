package dss.vector.solutions.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.query.MapUtil;

public class DatabaseUtil
{
  private static boolean IS_MATERIALIZED = false;

  /**
   * Creates a view.
   * 
   * @param viewName
   * @param query
   */
  @Transaction
  public static void createView(String viewName, String query)
  {
    if (IS_MATERIALIZED)
    {
      String statement = "CREATE MATERIALIZED VIEW " + viewName + " AS (" + query + ")";

      Database.executeStatement(statement);
    }
    else
    {
      Database.createView(viewName, query);
    }
  }

  /**
   * Drops a view.
   * 
   * @param viewName
   * @param query
   */
  @Transaction
  public static void dropView(String viewName, String query, Boolean dropOnEndOfTransaction)
  {
    if (IS_MATERIALIZED)
    {
      String statement = "DROP MATERIALIZED VIEW IF EXISTS " + viewName + " CASCADE";

      Database.executeStatement(statement);
    }
    else
    {
      MapUtil.deleteMapView(viewName);
    }
  }

  @Transaction
  public static void dropViews(List<String> viewNames)
  {
    if (IS_MATERIALIZED)
    {
      for (String viewName : viewNames)
      {
        DatabaseUtil.dropView(viewName, null, false);
      }
    }
    else
    {
      Database.dropViews(viewNames);
    }
  }

  @Transaction
  public static List<String> getViewsByPrefix(String prefix)
  {
    if (IS_MATERIALIZED)
    {
      StringBuilder sql = new StringBuilder();
      sql.append("SELECT oid::regclass::text AS viewName");
      sql.append(" FROM pg_class");
      sql.append(" WHERE  relkind = 'm'");

      ResultSet resultSet = Database.query(sql.toString());
      List<String> list = new LinkedList<String>();

      try
      {
        while (resultSet.next())
        {
          String viewName = resultSet.getString("viewName");

          if (viewName.startsWith(prefix))
          {
            list.add(viewName);
          }
        }
      }
      catch (SQLException sqlEx1)
      {
        Database.throwDatabaseException(sqlEx1);
      }
      finally
      {
        try
        {
          java.sql.Statement statement = resultSet.getStatement();
          resultSet.close();
          statement.close();
        }
        catch (SQLException sqlEx2)
        {
          Database.throwDatabaseException(sqlEx2);
        }
      }
      return list;
    }
    else
    {
      return Database.getViewsByPrefix(prefix);
    }
  }

  @Transaction
  public static void refreshView(String viewName)
  {
    if (IS_MATERIALIZED)
    {
      String statement = "REFRESH MATERIALIZED VIEW " + viewName;

      Database.executeStatement(statement);
    }
    else
    {
      throw new UnsupportedOperationException();
    }
  }

}
