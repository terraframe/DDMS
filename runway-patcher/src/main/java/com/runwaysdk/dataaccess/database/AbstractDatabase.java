/*
 * Created on Jun 23, 2005
 */
package com.runwaysdk.dataaccess.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.runwaysdk.RunwayVersion;
import com.runwaysdk.constants.DatabaseProperties;

/**
 * The abstract root of the Database class family. Contains general SQL syntax
 * for interaction with the database. Concrete implementations will override
 * methods in this class to allow for specific databases.
 * 
 * @author Justin
 * @version $Revision 1.0 $
 * @since
 */
public abstract class AbstractDatabase
{

  /**
   * The name of the table containing the current version
   */
  public static final String  PROPERTIES_TABLE        = "dynamic_properties";

  /**
   * The name of the version number column on the properties
   */
  public static final String  VERSION_NUMBER          = "version_number";

  public static final String  RUNWAY_VERSION_PROPERTY = "000000000000000000001";

  protected DataSource        dataSource;

  protected DataSource        rootDataSource;

  private final ReentrantLock connlock;

  private DatabaseProperties  databaseProperties;

  /**
   */
  protected AbstractDatabase(DatabaseProperties databaseProperties)
  {
    this.connlock = new ReentrantLock();
    this.databaseProperties = databaseProperties;

    try
    {
      Context initContext = new InitialContext();
      Context envContext = (Context) initContext.lookup("java:/comp/env");

      if (envContext != null)
      {
        dataSource = (DataSource) envContext.lookup(databaseProperties.getJNDIDataSource());
      }
    }
    catch (NamingException e)
    {
      // If a jndiDataSource has not been configured with the app server, the
      // subclasses will initialize the datasource.
    }
  }

  public DatabaseProperties getDatabaseProperties()
  {
    return databaseProperties;
  }

  /**
   *Returns a java.sql.Connection object for the database.
   * 
   * <br/>
   * <b>Precondition:</b> database is running. <br/>
   * <b>Precondition:</b> database.properities file contains correct DB
   * connection settings. <br/>
   * <b>Postcondition:</b> true
   * 
   * @return java.sql.Connection object
   */
  public Connection getConnection()
  {
    this.connlock.lock();
    try
    {
      Connection conn = null;

      try
      {
        conn = this.dataSource.getConnection();
        conn.setAutoCommit(false);
        conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
      }
      catch (SQLException ex)
      {
        throw new DatabaseException(ex);
      }
      return conn;
    }
    finally
    {
      this.connlock.unlock();
    }
  }

  /**
   *Returns a java.sql.Connection object for the database to be used for
   * database DDL statements.
   * 
   * <br/>
   * <b>Precondition:</b> database is running. <br/>
   * <b>Precondition:</b> database.properities file contains correct DB
   * connection settings. <br/>
   * <b>Postcondition:</b> true
   * 
   * @return java.sql.Connection object
   */
  public Connection getDDLConnection()
  {
    this.connlock.lock();
    try
    {
      Connection conn = null;
      try
      {
        conn = this.dataSource.getConnection();
        conn.setAutoCommit(false);

        conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

      }
      catch (SQLException ex)
      {
        throw new DatabaseException(ex);
      }
      return conn;
    }
    finally
    {
      this.connlock.unlock();
    }
  }

  /**
   * All connections managed by the framework need to be closed using this
   * method. This is a hook method for the session management aspect.
   * 
   * @param conn
   * @throws SQLException
   */
  protected void closeConnection(Connection conn) throws SQLException
  {
    conn.close();
  }

  /**
   * Executes a SQL query (which is assumed to be valid) against the database,
   * returning a RowSet object the result. It is up to the client to close the
   * <code>ResultSet</code>, the statement, and the connection. <br>
   * <b>Precondition: </b> statement != null <br>
   * <b>Precondition: </b> sqlStmt != null <br>
   * <b>Precondition: </b> !sqlStmt.trim().equals("") <br>
   * <b>Postcondition: </b> return value may not be null
   * 
   * @param sqlStmt
   *          SQL query statement
   */
  protected ResultSet query(String sqlStmt)
  {
    Connection conx = this.getConnection();
    String selectStatement = sqlStmt;

    Statement statement = null;
    ResultSet resultSet = null;

    try
    {
      statement = conx.createStatement();
      resultSet = statement.executeQuery(selectStatement);
    }
    catch (SQLException ex)
    {
      this.throwDatabaseException(ex, sqlStmt);
    }
    finally
    {
      try
      {
        this.closeConnection(conx);
      }
      catch (SQLException e)
      {
        this.throwDatabaseException(e);
      }
    }

    return resultSet;
  }

  /**
   * Executes a statement in the database. Any result set from the execution is
   * discarded. The statement is assumed to be valid and is not checked before
   * execution.
   * 
   * @param stmt
   *          The SQL statement to be executed in the database.
   */
  public void execute(String stmt)
  {
    Connection conx = this.getConnection();

    Statement statement = null;
    try
    {
      statement = conx.createStatement();
      statement.executeUpdate(stmt);
      conx.commit();
    }
    catch (SQLException ex)
    {
      this.throwDatabaseException(ex, stmt);
    }
    finally
    {
      try
      {
        if (statement != null)
        {
          statement.close();
        }
        this.closeConnection(conx);
      }
      catch (SQLException ex)
      {
        this.throwDatabaseException(ex);
      }
    }
  }

  /**
   * Builds an SQL statement to delete an entry row.
   * 
   * @param table
   *          The table name where the row to delete can be found.
   * @param id
   *          The id of the record to delete.
   * @return The SQL delete statement.
   */
  protected String buildPropertyDeleteStatement(String id)
  {
    return "DELETE FROM " + PROPERTIES_TABLE + " WHERE id = '" + id + "'";
  }

  protected String buildPropertyInsertStatement(String id, String value)
  {
    return "INSERT INTO " + PROPERTIES_TABLE + " (id, version_number) VALUES ('" + id + "','" + value + "');";
  }

  protected String buildPropertyUpdateStatement(String id, String value)
  {
    return "UPDATE " + PROPERTIES_TABLE + " SET version_number = '" + value + "' WHERE id = '" + id + "'";
  }

  protected ResultSet selectFromWhere(String columnName, String table, String condition)
  {
    return query("SELECT " + columnName + " FROM " + table + " WHERE " + condition);
  }

  protected List<String> getPropertyValue(String propertyId)
  {
    List<String> results = new LinkedList<String>();

    String condition = "id = '" + propertyId + "'";

    ResultSet resultSet = this.selectFromWhere(VERSION_NUMBER, PROPERTIES_TABLE, condition);

    try
    {
      while (resultSet.next())
      {
        results.add(resultSet.getString(VERSION_NUMBER).trim());
      }
    }
    catch (SQLException sqlEx1)
    {
      this.throwDatabaseException(sqlEx1);
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
        this.throwDatabaseException(sqlEx2);
      }
    }

    return results;
  }

  public boolean hasRunwayVersion()
  {
    return ( this.getPropertyValue(RUNWAY_VERSION_PROPERTY).size() > 0 );
  }

  public RunwayVersion getRunwayVersion()
  {
    List<String> values = this.getPropertyValue(RUNWAY_VERSION_PROPERTY);

    if (values.size() == 0)
    {
      return null;
    }

    if (values.size() > 1)
    {
      throw new AmbiguousVersionException("More than one runway version");
    }

    return new RunwayVersion(values.get(0));
  }

  public void setRunwayVersion(RunwayVersion version)
  {
    if (version == null)
    {
      this.deleteRunwayVersion();
    }
    else if (this.hasRunwayVersion())
    {
      this.execute(this.buildPropertyUpdateStatement(RUNWAY_VERSION_PROPERTY, version.toString()));
    }
    else
    {
      this.execute(this.buildPropertyInsertStatement(RUNWAY_VERSION_PROPERTY, version.toString()));
    }
  }

  public void deleteRunwayVersion()
  {
    this.execute(this.buildPropertyDeleteStatement(RUNWAY_VERSION_PROPERTY));
  }

  protected void throwDatabaseException(SQLException ex)
  {
    throw new DatabaseException(ex);
  }

  protected void throwDatabaseException(SQLException ex, String error)
  {
    throw new DatabaseException(error, ex);
  }
}
