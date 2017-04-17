package dss.vector.solutions.general;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.runwaysdk.business.rbac.Authenticate;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.database.DatabaseException;
import com.runwaysdk.dataaccess.io.FileReadException;
import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.MdssLog;
import dss.vector.solutions.query.SavedSearch;

public class ReportModuleView extends ReportModuleViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long                  serialVersionUID     = 1511052095;

  private static final String                PROPERTIES_FILE_NAME = "reportmodule.properties";

  private static final String                DEFAULT_MODULE_NAME  = "TEMP";

  private static final Map<String, Progress> map                  = new HashMap<String, Progress>();

  public ReportModuleView()
  {
    super();
  }

  public static ReportModuleView[] getModules()
  {
    List<ReportModuleView> list = new LinkedList<ReportModuleView>();

    File file = new File(ReportModuleView.class.getResource("/" + PROPERTIES_FILE_NAME).getFile());

    try
    {
      Properties prop = new Properties();
      prop.load(new FileInputStream(file));

      Set<Object> moduleNames = (Set<Object>) prop.keySet();

      for (Object moduleName : moduleNames)
      {
        if (moduleName instanceof String)
        {
          ReportModuleView view = new ReportModuleView();
          view.setModuleName((String) moduleName);
          view.applyNoPersist();

          list.add(view);
        }
      }

      return list.toArray(new ReportModuleView[list.size()]);
    }
    catch (IOException ex)
    {
      throw new FileReadException(file, ex);
    }
  }

  @Transaction
  @Authenticate
  public void buildDatabaseViews()
  {
    if (!ReportModuleView.isInProgress(DEFAULT_MODULE_NAME))
    {
      try
      {
        List<String> views = this.getViewNames();

        if (views.size() > 0)
        {
          ReportModuleView.addProgress(DEFAULT_MODULE_NAME, new Progress(views.size() + 2));

          /*
           * Ensure all of the views exist
           */
          for (String view : views)
          {
            List<String> existing = Database.getViewsByPrefix(view);

            if (!existing.contains(view))
            {
              String msg = "View [" + view + "] does not exist in the database";
              ViewNotFoundException ex = new ViewNotFoundException(msg);
              ex.setViewName(view);
              ex.apply();

              throw ex;
            }
          }

          ReportModuleView.incrementProgress(DEFAULT_MODULE_NAME, "Validate");

          /*
           * Delete all of the existing module report tables
           */
          ReportModuleView.dropExistingModuleTables(DEFAULT_MODULE_NAME);

          ReportModuleView.incrementProgress(DEFAULT_MODULE_NAME, "Delete");

          /*
           * Create the new module report tables
           */
          for (String view : views)
          {
            ReportModuleView.createMaterializedView(DEFAULT_MODULE_NAME, view.trim());

            ReportModuleView.incrementProgress(DEFAULT_MODULE_NAME, view);
          }
        }
      }
      finally
      {
        ReportModuleView.removeProgress(DEFAULT_MODULE_NAME);
      }
    }
    else
    {
      String msg = "The database tables are already in the process of being created.";

      InProgressReportModuleException e = new InProgressReportModuleException(msg);
      e.setModuleName(DEFAULT_MODULE_NAME);
      e.apply();

      throw e;
    }
  }

  private List<String> getViewNames()
  {
    List<String> list = new LinkedList<String>();

    File file = new File(this.getClass().getResource("/" + PROPERTIES_FILE_NAME).getFile());

    try
    {
      Properties prop = new Properties();
      prop.load(new FileInputStream(file));

      Enumeration<?> moduleNames = prop.propertyNames();

      while (moduleNames.hasMoreElements())
      {
        String moduleName = (String) moduleNames.nextElement();
        String property = prop.getProperty(moduleName);

        String[] views = property.split(",");

        list.addAll(Arrays.asList(views));
      }
    }
    catch (IOException ex)
    {
      throw new FileReadException(file, ex);
    }

    return list;
  }

  public static synchronized Integer getProgress(String moduleName)
  {
    if (ReportModuleView.isInProgress(DEFAULT_MODULE_NAME))
    {
      return map.get(DEFAULT_MODULE_NAME).getProgress();
    }
    else
    {
      String msg = "The materialized tables are not in the process of being created.";

      UnsupportedReportModuleException e = new UnsupportedReportModuleException(msg);
      e.setModuleName(DEFAULT_MODULE_NAME);
      e.apply();

      throw e;
    }
  }

  private static synchronized void incrementProgress(String moduleName, String view)
  {
    map.get(moduleName).increment(view.trim());
  }

  private static synchronized void removeProgress(String moduleName)
  {
    map.remove(moduleName);
  }

  private static synchronized void addProgress(String moduleName, Progress progress)
  {
    map.put(moduleName, progress);
  }

  private static synchronized boolean isInProgress(String moduleName)
  {
    return map.containsKey(moduleName);
  }

  private static String generateMaterializedViewName(String moduleName, String viewName)
  {
    return getModulePrefix(moduleName) + viewName;
  }

  public static void createMaterializedView(String moduleName, String viewName)
  {
    String materializedViewName = ReportModuleView.generateMaterializedViewName(moduleName, viewName);

    StringBuffer sql = new StringBuffer();
    sql.append("CREATE TABLE " + materializedViewName + " AS (SELECT * FROM " + viewName + ");");

    Connection conn = Database.getConnection();

    Statement statement = null;
    try
    {
      statement = conn.createStatement();
      statement.execute(sql.toString());
    }
    catch (SQLException e)
    {
      MdssLog.fatal(e);

      UnableToCreateReportTableException ex = new UnableToCreateReportTableException(e);
      ex.setViewName(viewName);
      ex.apply();

      throw ex;
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
          MdssLog.fatal(e2);
          throw new DatabaseException(e2);
        }
      }
    }
  }

  private static String getModulePrefix(String moduleName)
  {
    return "mt_";
  }

  public static void dropExistingModuleTables(String moduleName)
  {
    String prefix = ReportModuleView.getModulePrefix(moduleName);
    List<String> tableNames = ReportModuleView.getTableNamesByPrefix(prefix + SavedSearch.VIEW_PREFIX);

    StringBuffer sql = new StringBuffer();

    for (String tableName : tableNames)
    {
      sql.append("DROP TABLE IF EXISTS " + tableName + ";");

      Connection conn = Database.getConnection();

      Statement statement = null;
      try
      {
        statement = conn.createStatement();
        statement.execute(sql.toString());
      }
      catch (SQLException e)
      {
        MdssLog.fatal(e);

        UnableToDeleteReportTableException ex = new UnableToDeleteReportTableException(e);
        ex.setModuleName(moduleName);
        ex.apply();

        throw ex;
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
            MdssLog.fatal(e2);
            throw new DatabaseException(e2);
          }
        }
      }
    }
  }

  public static List<String> getTableNamesByPrefix(String prefix)
  {
    StringBuffer sql = new StringBuffer();
    sql.append("SELECT table_name");
    sql.append(" FROM information_schema.tables");
    sql.append(" WHERE table_type='BASE TABLE'");
    sql.append(" AND table_name LIKE '" + prefix + "%';");

    ResultSet resultSet = Database.query(sql.toString());
    List<String> list = new LinkedList<String>();

    try
    {
      while (resultSet.next())
      {
        list.add(resultSet.getString("table_name"));
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
        Statement statement = resultSet.getStatement();

        try
        {
          resultSet.close();
        }
        finally
        {
          statement.close();
        }
      }
      catch (SQLException sqlEx2)
      {
        Database.throwDatabaseException(sqlEx2);
      }
    }

    return list;
  }

}
