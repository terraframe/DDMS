package dss.vector.solutions.general;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.runwaysdk.business.rbac.Authenticate;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.database.DatabaseException;
import com.runwaysdk.dataaccess.io.FileReadException;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.session.Session;

import dss.vector.solutions.MdssLog;
import dss.vector.solutions.RequiredAttributeException;

public class ReportModuleView extends ReportModuleViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long                  serialVersionUID     = 1511052095;

  private static final String                PROPERTIES_FILE_NAME = "reportmodule.properties";

  public static final String                 IRS_MODULE           = "IRS";

  private static final String[]              MODULE_NAMES         = new String[] { IRS_MODULE };

  private static final Map<String, Progress> map                  = new HashMap<String, Progress>();

  public ReportModuleView()
  {
    super();
  }

  public static ReportModuleView[] getModules()
  {
    List<ReportModuleView> list = new LinkedList<ReportModuleView>();

    for (String moduleName : MODULE_NAMES)
    {
      ReportModuleView view = new ReportModuleView();
      view.setModuleName(moduleName);
      view.applyNoPersist();

      list.add(view);
    }

    return list.toArray(new ReportModuleView[list.size()]);
  }

  @Transaction
  @Authenticate
  public void buildDatabaseViews()
  {
    String moduleName = this.getModuleName();

    if (moduleName == null || moduleName.length() == 0)
    {
      RequiredAttributeException exception = new RequiredAttributeException();
      exception.setAttributeLabel(getModuleNameMd().getDisplayLabel(Session.getCurrentLocale()));
      exception.apply();

      throw exception;
    }

    if (!ReportModuleView.isInProgress(moduleName))
    {
      try
      {
        File file = new File(this.getClass().getResource("/" + PROPERTIES_FILE_NAME).getFile());

        try
        {
          Properties prop = new Properties();
          prop.load(new FileInputStream(file));

          if (prop.containsKey(moduleName))
          {
            String property = prop.getProperty(moduleName);

            String[] views = property.split(",");

            if (views.length > 0)
            {
              ReportModuleView.addProgress(moduleName, new Progress(views.length));

              /*
               * Delete all of the existing module report tables
               */
              ReportModuleView.dropExistingModuleTables(moduleName);

              /*
               * Create the new module report tables
               */
              for (String view : views)
              {
                ReportModuleView.createMaterializedView(moduleName, view.trim());

                ReportModuleView.incrementProgress(moduleName, view);
              }
            }
          }
          else
          {
            String msg = "Unable to find database views for module [" + moduleName + "]";

            UnsupportedReportModuleException e = new UnsupportedReportModuleException(msg);
            e.setModuleName(moduleName);
            e.apply();

            throw e;
          }
        }
        catch (IOException ex)
        {
          throw new FileReadException(file, ex);
        }
      }
      finally
      {
        ReportModuleView.removeProgress(moduleName);
      }
    }
    else
    {
      String msg = "The database views for module [" + moduleName + "] are already in the process of being loaded";

      InProgressReportModuleException e = new InProgressReportModuleException(msg);
      e.setModuleName(moduleName);
      e.apply();

      throw e;
    }
  }

  public static synchronized Integer getProgress(String moduleName)
  {
    if (ReportModuleView.isInProgress(moduleName))
    {
      return map.get(moduleName).getProgress();
    }
    else
    {
      String msg = "The database view for module [" + moduleName + "] are not being loaded";

      UnsupportedReportModuleException e = new UnsupportedReportModuleException(msg);
      e.setModuleName(moduleName);
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
    String prefix = new String();

    if (moduleName.equals(ReportModuleView.IRS_MODULE))
    {
      prefix = "i";
    }
    else
    {
      throw new ProgrammingErrorException("Unsupported module [" + moduleName + "]");
    }

    return "m_" + prefix + "_";
  }

  public static void dropExistingModuleTables(String moduleName)
  {
    String prefix = ReportModuleView.getModulePrefix(moduleName);
    List<String> tableNames = ReportModuleView.getTableNamesByPrefix(prefix);

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
