/**
 * Created on Sep 17, 2005
 * 
 */
package com.runwaysdk.dataaccess.database;

import java.sql.Connection;

import javax.sql.DataSource;

import org.postgresql.ds.PGPoolingDataSource;
import org.postgresql.ds.PGSimpleDataSource;
import org.postgresql.ds.common.BaseDataSource;

import com.runwaysdk.constants.DatabaseProperties;

/**
 * @author nathan
 * 
 */
public class PostgreSQL extends AbstractDatabase
{

  public PostgreSQL(DatabaseProperties databaseProperties)
  {
    super(databaseProperties);

    // The container is not providing a pooled datasource
    if (this.dataSource == null)
    {
      boolean pooling = databaseProperties.getConnectionPooling();

      int initialDbConnections = databaseProperties.getInitialConnections();
      int maxDbConnections = databaseProperties.getMaxConnections();

      if (maxDbConnections < 2)
      {
        maxDbConnections = 2;
      }

      BaseDataSource pgDataSource;

      if (pooling)
      {
        // If environment is configured for connection pooling, pool connections
        pgDataSource = new PGPoolingDataSource();
        ( (PGPoolingDataSource) pgDataSource ).setInitialConnections(initialDbConnections);
        ( (PGPoolingDataSource) pgDataSource ).setMaxConnections(maxDbConnections);
      }
      // If environment is not configured for connection pooling, do not pool
      // connections
      else
      {
        pgDataSource = new PGSimpleDataSource();
      }

      pgDataSource.setServerName(databaseProperties.getServerName());
      pgDataSource.setPortNumber(databaseProperties.getPort());
      pgDataSource.setDatabaseName(databaseProperties.getDatabaseName());
      pgDataSource.setUser(databaseProperties.getUser());
      pgDataSource.setPassword(databaseProperties.getPassword());

      this.dataSource = (DataSource) pgDataSource;
    }
  }

  /**
   * Returns a java.sql.Connection object for the database to be used for
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
    return this.getConnection();
  }
}
